<template>
  <div class="exam-panel" >
    <el-row >
      <el-col :span="5">
        <div class= "left-area">
          <legend-case></legend-case>
          <div class = "legend-case-title">判断题</div>
          <div class = "question-number-list">
            <div v-for="(item, index) in judgeQuestionsList" :key="index">
              <question-number :paras = "item" @refreshClick ="clickHandle"></question-number>
            </div>          
          </div>
          <div class = "legend-case-title">单选题</div>
          <div class = "question-number-list">
            <div v-for="(item, index) in radioQuestionsList" :key="index">
              <question-number :paras = "item" @refreshClick ="clickHandle"></question-number>
            </div>          
          </div>
          <div class = "legend-case-title">多选题</div>
          <div class = "question-number-list">
            <div v-for="(item, index) in choiceQuestionsList" :key="index">
              <question-number :paras = "item" @refreshClick ="clickHandle"></question-number>
            </div>          
          </div>

        </div>
      </el-col>
      <el-col :span="19">
        <div class="content-area" :style = "PanelStyle">
          <div class = "question-title">
            <div>
              <span class = "question-number">{{ questionsContentData.questionsNumberText }}</span>
              <span class = "question-score"> ({{ questionsContentData.questionsScore }} 分)：</span>
            </div>
            <div class = "time-area"><el-statistic
                  ref="statistic"
                  @finish="doEnd"
                  format="HH:mm:ss"
                  :value="currentTime"
                  prefix="距考试结束还有:"
                  :value-style="statisticStyle"
                  time-indices
                >
                </el-statistic></div>               
          </div>
          <div>
            <div class="question-content" :style = "contentStyle" v-html="questionsContentData.questionsContent" />
          </div>
          <div class="question-option" :style = "optionStyle">
            <template v-if="isRadio">
              <div 
                v-for="(item, index) in questionsOptionList" :key="index"
              >
                <span class = "option-code">{{item.optionCode}}</span>
                <input class="option-radio" type="radio" id="item.orderId" :value="item.optionCode" v-model="radioValue"  @click ="doRadio(item)"/>
                <span class="option-text">{{item.optionDescribe}}</span>
              </div>
            </template>
            <template v-else>
              <div 
                v-for="(item, index) in questionsOptionList" :key="index"
              >
                <span class = "option-code">{{item.optionCode}}</span>
                <input class="option-radio" type="checkbox" v-model="item.remark" value="item.optionCode" @click ="doChecked(item)"/>
                <span class="option-text">{{item.optionDescribe}}</span>
              </div>
            </template>        
          </div>
        </div>
        <div class = "footer-area" >
          <el-row :gutter="20">
            <el-col :span="18">
              <div class = "direction-area">
                <el-button type="primary" plain :disabled = "leftDisabled" icon="el-icon-arrow-left" @click="goPrevious">上一题</el-button>
                <el-button type="warning" plain icon="el-icon-message-solid" @click="doSign">{{markButtonText}}</el-button>
                <el-button type="primary" plain :disabled = "rightDisabled" icon="el-icon-arrow-right el-icon--right" @click="goNext">下一题</el-button>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="done-area">
                <el-button type="primary" @click = "doSubmit">交 卷</el-button>
              </div>
            </el-col>
          </el-row>
        </div>        
      </el-col>
    </el-row>  
  </div>
</template>


<script>
import legendCase from './legendCase'
import questionNumber from './questionNumber'
import { getContent, getQuestionsData } from "@/api/onlineexam/onlineexamdata";
import { getQuestionsList } from "@/api/onlineexam/questionslist"
import { addTaskanswer, deleteExamtask } from "@/api/onlineexam/taskanswer"
import { saveFinishanswer } from "@/api/onlineexam/finishanswer"
import { updateStartTime } from "@/api/exam/taskperson";
import { dateToString , dateTextToDateString, dateRemoveSeconds, dateTextToDatetimeString } from "@/utils/dateUtils";
export default {
  name: "startAnswer",
  components: {
    legendCase,
    questionNumber    
  },  
  data() {
    return {
      // 查询参数
      questionsQuery: {
        examCode: null,
        questionsCode: null
      },
      questionsData: {},
      questionsContentData: {},
      questionsOptionList: [],
      questionsTitle: '',
      contentStyle: null,
      optionStyle: null,
      PanelStyle: null,
      isRadio: true,
      checkItem: [],
      judgeQuestionsList: [],
      radioQuestionsList: [],
      choiceQuestionsList: [],
      questionsList: [],
      examCode: null,
      currentQuestionsNumber: -1,
      previousQuestionsNumber: -1,
      markButtonText: '标注',
      leftDisabled: false,
      rightDisabled: false,
      radioValue: false,
      isChange: false,
      examData: {},
      currentTime: null,
      statisticStyle: {
       color: "#1481fb"
      }
    }
  },
  created() {
    this.isRadio = false
    const s_width = document.body.clientWidth
    const s_height = document.body.clientHeight
    // 计算最小高度及最大高度
    const contentMinHeight = parseInt((s_height - 260) * 0.20)
    const contentMaxHeight = parseInt((s_height - 260) * 0.62)
    this.contentStyle = 'max-height:' + contentMaxHeight + 'px;'
    const optionMinHeight = parseInt((s_height - 260) * 0.20)
    const optionMaxHeight = parseInt((s_height - 260) * 0.35)
    this.optionStyle = 'max-height:' + optionMaxHeight + 'px;'
    const contentHeight = s_height - 160
    this.PanelStyle = 'height:' + contentHeight + 'px;'
  },
  mounted () {
  },
  methods: {
    init_data (titledata) {
      this.examData = titledata
      this.setTimeShow()
      this.examCode = this.examData.examCode
      this.setExamTime()
      this.getList()
    },
    setTimeShow() {
      const endTime = new Date(this.examData.endTime).getTime()
      this.currentTime = endTime + Number(this.examData.diffTime)
    },
    /** 查询题目列表 */
    getList () {
      const queryPara = {
        examCode: this.examCode,
        userCode: this.$store.state.user.userCode
      }
      getQuestionsList(queryPara).then(response => {
        this.questionsList = response.data
        this.setFirstShow()
      });
    },
    createQuestionsPanel() {
      this.judgeQuestionsList= []
      this.radioQuestionsList= []
      this.choiceQuestionsList= []
      for (let i = 0; i < this.questionsList.length; i ++) {
        let ismark = false
        if (this.questionsList[i].isMark === 0) {
          ismark = true
        }

        let isdone = false
        if (this.questionsList[i].questionsAnswer !== null) {
          isdone = true
        }

        let currentstyle = ''
        if (this.questionsList[i].isCurrent === 0) {
          currentstyle = 'border: 1px solid  red;'
        }

        if (this.questionsList[i].questionsType === 1) {
          this.judgeQuestionsList.push({
            questionNumber: i + 1,
            isDone: isdone,
            isMark: ismark,
            currentStyle: currentstyle
          })
        }  else if (this.questionsList[i].questionsType === 2) {
          this.radioQuestionsList.push({
            questionNumber: i + 1,
            isDone: isdone,
            isMark: ismark,
            currentStyle: currentstyle            
          })
        } else if (this.questionsList[i].questionsType === 3) {
          this.choiceQuestionsList.push({
            questionNumber: i + 1,
            isDone: isdone,
            isMark: ismark,
            currentStyle: currentstyle            
          })          
        }
      }
    },
    setExamTime() {
      const data = {
        examCode: this.examCode,
        userCode: this.$store.state.user.userCode
      }      
      updateStartTime(data).then(response => {
      })
    },
    setFirstShow() {
      // 设置第一条数据为当前
      this.currentQuestionsNumber = 1
      this.questionsList[0].isCurrent = 0
      this.leftDisabled = true
      this.questionsQuery = {
        examCode: this.examCode,
        questionsCode: this.questionsList[0].questionsCode
      }
      this.createQuestionsPanel()
      this.getData()

    },
    /** 查询考试题目答案列表 */
    getData() {
      this.questionsOptionList = []
      getQuestionsData(this.questionsQuery).then(response => {
        this.questionsContentData = response.data.examQuestionsContentData
        this.questionsTitle = this.questionsContentData.questionsNumberText + '(' + this.questionsContentData.questionsScore + '分)：'
        this.questionsOptionList = response.data.examQuestionsOptionList

        if (this.questionsContentData.questionsType === 1 || this.questionsContentData.questionsType === 2) {
          this.isRadio = true
          if (this.questionsList[this.currentQuestionsNumber - 1].questionsAnswer !== null) {
            this.radioValue = this.questionsList[this.currentQuestionsNumber - 1].questionsAnswer
          }
        } else {
          this.isRadio = false
          if (this.questionsList[this.currentQuestionsNumber - 1].questionsAnswer !== null) {
            const answerItem = this.questionsList[this.currentQuestionsNumber - 1].questionsAnswer.split('')
            for (let i = 0; i < answerItem.length; i ++) {
              for (let j = 0; j < this.questionsOptionList.length; j ++) {
                if (answerItem[i] === this.questionsOptionList[j].optionCode) {                  
                  this.questionsOptionList[j].remark = true
                }
              }
            }
          }
        }
      });
    },
    doChecked(item) {
      this.isChange = true
    },
    doRadio(item) {
      this.radioValue = item.optionCode
      this.isChange = true
    },
    doSign () {
      if (this.questionsList[this.currentQuestionsNumber - 1].isMark === 0) {
        this.questionsList[this.currentQuestionsNumber - 1].isMark = 1
      } else {
        this.questionsList[this.currentQuestionsNumber - 1].isMark = 0
      }

      
      this.createQuestionsPanel()
      this.setMrakButton()
    },
    setMrakButton() {
      if (this.markButtonText === '标注') {
        this.markButtonText = '取消标注'
      } else {
        this.markButtonText = '标注'
      }

      if (!this.isChange) {
        this.isChange = true
      }
    },
    goPage(num) {
      if (this.isChange) {
        this.saveData()
      }
      this.showPage(num)
    },
    showPage(num) {
      this.previousQuestionsNumber = this.currentQuestionsNumber
      this.currentQuestionsNumber = num

      if (this.currentQuestionsNumber === 1) {
        this.leftDisabled = true
      } else if (this.leftDisabled) {
        this.leftDisabled = false
      } 
      
      if (this.currentQuestionsNumber === this.questionsList.length ) {
        this.rightDisabled = true
      } else if (this.rightDisabled) {
        this.rightDisabled = false
      }

      this.questionsQuery.questionsCode= this.questionsList[num - 1].questionsCode
      this.questionsList[this.previousQuestionsNumber - 1].isCurrent = 1
      this.questionsList[this.currentQuestionsNumber - 1].isCurrent = 0
      if (this.questionsList[this.currentQuestionsNumber - 1].isMark === 0) {
        this.markButtonText = '取消标注'
      } else {
        this.markButtonText = '标注'
      }
      this.radioValue = null
      this.createQuestionsPanel()
      this.getData()
    },
    goPrevious() {
      this.goPage(this.currentQuestionsNumber - 1)
    },
    goNext() {
      this.goPage(this.currentQuestionsNumber + 1)
    },
    clickHandle(paras) {
      this.goPage(paras.questionNumber)
    },
    saveData() {
      let answerval = ''
      // 处理值
      if (this.questionsContentData.questionsType === 1 || this.questionsContentData.questionsType === 2) {
        answerval = this.radioValue  
      } else {
        for (let i = 0; i < this.questionsOptionList.length; i ++) {
          if (this.questionsOptionList[i].remark !== null && this.questionsOptionList[i].remark) {
            answerval += this.questionsOptionList[i].optionCode
          }
        }  
      }
      this.questionsList[this.currentQuestionsNumber - 1].questionsAnswer = answerval
      const questionData = this.questionsList[this.currentQuestionsNumber - 1]

      const saveQuestion = {
        examCode: questionData.examCode,
        userCode: this.$store.state.user.userCode,
        questionsCode: questionData.questionsCode,
        questionsNumber: questionData.questionsNumber,
        questionsType: questionData.questionsType,
        questionsAnswer: answerval,
        isMark: questionData.isMark,
        isCurrent: 1
      }

      addTaskanswer(saveQuestion).then(response => {
        this.isChange = false
        this.createQuestionsPanel()
      });
    },
    doSubmit () {
      if (this.isChange) {
        this.saveData()
      }

      let msg = '试题已做完，是否确认交卷?，点”确认“交卷'
      for (let i = 0; i < this.questionsList.length; i ++) {
        if (this.questionsList[i].questionsAnswer === null) {
          msg = '还有题未完成，是否确认交卷?，点”确认“交卷'
        }
      }

      const submitData = {
        examCode: this.examCode,
        userCode: this.$store.state.user.userCode
      }
      this.$confirm(msg, "询问", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return saveFinishanswer(submitData)
      }).then(response => {
        this.$alert('试卷已提交，点击确定关闭窗口!', '提示', {
          confirmButtonText: '确定',
          callback: action => {
            this.$store.dispatch("tagsView/delView", this.$route);
            this.$router.go(-1)
          }
        })        
      }).catch(() => {
        this.$message.error("提交试卷出错");
      });
      
    },
    doEnd () {
      this.msgSuccess("考试结束");
    }      
  }
};
</script>

<style scoped lang="scss">
.exam-panel {
   margin-top: 1.2rem;
   margin-left: 1.0rem;
}

.question-title {
  display: flex;
  justify-content: space-between;  
  line-height: 2.5rem;
  background-color: #efefef;
  padding-left: 0.3rem;

}

.time-area {
  padding-top: 0.5rem;
}
.content-area {
  margin-left: 1rem;
  background-color: #fff;
}

.done-button {
  text-align: center;
}

.question-number {
  font-size: 1.1rem;
  font-weight:bold;
}

.question-score {
  font-size:1.0rem;
  color: #8c939d;
}

.question-content {
  margin-top: 10px;
  padding: 10px;
  background-color: #fff;
  overflow-y:auto;
}

.question-option {
  padding-top: 1rem;
  line-height: 2.8rem ;
  background-color: #fff;
}

.option-code {
  font-size: 1.2rem;
  line-height: 2.8rem ;
  font-weight:bold;
}

.option-radio {
 line-height: 2.8rem ;
 margin-left: 0.7rem;
 margin-right: 0.7rem;
}

.option-text {
  line-height: 2.8rem ;
  font-size:1.1rem;
}

.footer-area { 
  position: fixed;
  width: 70.2%;
  padding-top: 0.6rem;
  background-color: #efefef;
  line-height: 2.8rem ;
  padding-bottom: 0.2rem;
  border-radius: 12px;
  box-shadow: 5px 8px 5px #d7d7d7;
} 

.left-area {
  width: 100%;
  padding: 0.5rem;
  border-radius: 12px;
  background-color: #fafafa;
  box-shadow: 5px 8px 5px #d7d7d7;
}

.legend-case-title {  
  line-height: 2.5rem;
  background-color: #a3d3ff;
  font-size: 1rem;
  color: #fff;
  font-weight: bold;
  padding-left: 0.3rem;
  margin-left: -0.5rem;
  margin-right: -0.5rem;
}

.question-number-list {
  display: flex;
  width: 100%;
  flex-wrap: wrap;
  margin-left: -0.3rem;
  margin-right: -1.2rem;
}

.direction-area {
  display: flex;
  justify-content:space-around;
}

.done-area {
  text-align: center;
  margin-top: -0.3rem;
}

</style>



