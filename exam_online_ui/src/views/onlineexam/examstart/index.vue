<template>
<div>
  <div v-if= "showUi === 1">
    <div class="welcome-panel" >
      <el-row :gutter="20">
        <el-col :span="12">
          <div v-if = "isRun">
            <div  class="questions-panel">
              <div class= "content_col">
                <div class = "title_panel">
                  <div class = "photo-area">
                    <img class ="title-photo" :src="hosturl + examTitle.pictureUrl"/>
                  </div>
                  <div class = "describe-area">
                    <div class = "title-name">{{examTitle.examName}}</div>
                    <div class = "title-describe">
                      <div class = "describe-text">{{examTitle.examDescribe}}</div>
                      <div class="create-time">{{ dateFormat(examTitle.createTime) }}</div>
                    </div>
                  </div>
                </div>
                <div class="date-panel">
                  <div>
                    <el-statistic title="考试开始时间">
                      <template slot="prefix">
                        <i class="el-icon-date" style="color: blue"></i>
                      </template>
                      <template slot="formatter"> {{startTimeText}} </template>
                    </el-statistic>
                  </div>
                  <div>
                    <el-statistic title="考试结束时间">
                      <template slot="prefix">
                        <i class="el-icon-date" style="color: green"></i>
                      </template>
                      <template slot="formatter"> {{endTimeText}} </template>
                    </el-statistic>
                  </div>
                  <div>
                    <el-statistic title="考试时长">
                      <template slot="prefix">
                        <i class="el-icon-timer" style="color: red"></i>
                      </template>
                      <template slot="formatter"> {{examTitle.examDuration}} </template>
                    </el-statistic>
                  </div>
                </div>
                <div class="score-panel">
                  <div>
                    <el-statistic title="总分数">
                      <template slot="prefix">
                        <i class="el-icon-monitor" style="color: blue"></i>
                      </template>
                      <template slot="formatter"> {{examTitle.questionsScore}} </template>
                    </el-statistic>
                  </div>
                  <div>
                    <el-statistic title="判断题数">
                      <template slot="prefix">
                        <i class="el-icon-document-checked" style="color: blue"></i>
                      </template>
                      <template slot="formatter"> {{examTitle.judgeNumber}} </template>
                    </el-statistic>
                  </div>
                  <div>
                    <el-statistic title="单选题数">
                      <template slot="prefix">
                        <i class="el-icon-folder-checked" style="color: blue"></i>
                      </template>
                      <template slot="formatter"> {{examTitle.radioNumber}} </template>
                    </el-statistic>
                  </div>
                  <div>
                    <el-statistic title="多选题数">
                      <template slot="prefix">
                        <i class="el-icon-folder-checked" style="color: blue"></i>
                      </template>
                      <template slot="formatter"> {{examTitle.choiceNumber}} </template>
                    </el-statistic>
                  </div>                                                      
                </div>
              </div>
            </div>
          </div>
          <div v-else class = "show-msg">当前无考试</div>
        </el-col>
        <el-col :span="12">
          <div class = "readme-panel">
            <div class = "readme-title">
              <p>在线考试说明:</p>
            </div>
            <div class="readme-content" v-for="item of contentItem" :key="item.id">
              <span class = "text-id">{{item.id}}.</span>
              <span class = "readme-text">{{item.value}}</span>
            </div>
          </div>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <div v-if = "isRun">
        <div v-if = "!isStart">
          <el-statistic
              ref="statistic"
              @finish="runStart"
              format="HH:mm:ss"
              :value="currentTime"
              prefix="距考试开始还有:"
              :value-style="statisticStyle"
              time-indices
            >
          </el-statistic>
        </div>
        <div v-else class = "button-area">
          <span class="tips-text">考试已经开始，点击按钮进入</span>
          <el-button type="success" class = "into-button" @click = "doEnter">进入考试</el-button>
        </div>
      </div>
    </div>     
  </div>
  <div v-else-if = "showUi === 2">
    <start-answer ref="answerRef" @refreshAnswer ="answertDoneHandle"></start-answer>
  </div>
</div>
</template>


<script>
import { getTitle } from "@/api/onlineexam/examtitle";
import { dateToString , dateTextToDateString, dateRemoveSeconds, dateTextToDatetimeString } from "@/utils/dateUtils";
import startAnswer from './startAnswer'
import { baseApiUrl } from '@/config/sysinfo'
export default {
  name: "examStart",
  components: {
    startAnswer    
  },  
  data() {
    return {
      examTitle: {},
      examStartTime: null,
      currentDate: null,
      isStart: false,
      isRun:false,
      showUi: 1,
      statisticStyle: {
       fontSize: "2.5rem", 
       color: "#27da70"
      },
      hosturl: '',
      startTimeText: null,
      endTimeText: null,
      contentItem: [
        { id: 1, value: '在线考试根据事先制定好的考试时间运行，只有等到达原定的考试时间，才可以进入答题页面，提前进入只能读秒等待，系统设定，读秒结束后将自动进入答题页面' },
        { id: 2, value: '在线考试支持判断、单选、多选三种题型，考生只需要勾选正确答案，判断、单选只有一个正确答案，勾对给分，多选必须勾选全部正确答案，多勾少勾都不给分' },
        { id: 3, value: '答题页面左边为操控板，显示题号以及每道题未答、已答、标记、当前等状态，点击题号可以直接切换到相应题目，或者也可以按上一题、下一题按钮进入，标记按钮在已标记状态再次按下将取消已有标记' },
        { id: 4, value: '碰到网络及其他因素意外退出，只要尚未点击是“交卷”按钮，可以再次进入答题，此时读秒已结束，可直接点击“进入考试”，已答题答案仍然存在' },
      ]
    }
  },
  created() {
  },
  mounted () {
    this.hosturl = baseApiUrl
    this.getData()
  },
  methods: {
    /** 查询题目列表 */
    getData () {
      getTitle(this.$store.state.user.userCode).then(response => {
        if (response.data !== undefined && response.data !== null) {
          this.examTitle = response.data
          this.isRun = true
          this.setShow()
        } else {
          this.isRun = false
          this.msgSuccess("当天没有考试或者考试已结束");
        }
      });
    },
    setShow () {
      this.currentDate = new Date(this.examTitle.currentDate).getTime()
      this.examStartTime = new Date(this.examTitle.startTime).getTime()
      this.startTimeText = dateTextToDatetimeString(this.examTitle.startTime) 
      this.endTimeText = dateTextToDatetimeString(this.examTitle.endTime) 
      this.currentTime = this.examStartTime + this.currentDate - Date.now()
      const diffTime = this.currentDate - Date.now()
      this.examTitle.diffTime = diffTime
      
      const intervaltime = (this.examStartTime - this.currentDate) / 1000
      if (intervaltime > 0) {
        // 时间未到，还需等待
        this.isStart = false
      } else {
        this.isStart = true
      }
    },
    runStart () {
      if (this.isRun) {
        this.handleEnter()        
      }

    },
    doEnter () {
      this.handleEnter()
    },
    handleEnter () {
      this.showUi = 2
      this.$nextTick(() => {
       this.$refs.answerRef.init_data(this.examTitle)
      })
    },
    answertDoneHandle () {

    },
    dateFormat(str) {
      return dateTextToDateString(str)
    }      
  }
}
</script>

<style scoped lang="scss">
.welcome-panel {
  margin-top: 1.2rem;
  margin-left: 1.0rem;
}

.questions-panel {
  margin: 12px 0px 12px 0px;
  padding: 8px 0px 12px 0px;
  display: -webkit-box;
  display: flex;
  -webkit-align-items: center;
  align-items: center;
  background-color: #fafafa;
  width: 100%;
  padding: 0.5rem;
  border-radius: 12px;
  background-color: #fafafa;
  box-shadow: 5px 8px 5px #d7d7d7;
}

.show-msg {
  margin: 12px 0px 12px 0px;
  padding: 8px 0px 12px 0px;
  width: 100%;
  color: #d1d5da;
  font-size: 5rem; 
  line-height: 26rem;
  font-weight:bold;
  text-align: center;
  height: 100%;
}

.content_col {
  width: 100%; 
}

.title_panel {
  display: flex;
  -webkit-box-orient: horizontal;
  width: 100%;

}

.photo-area {
  -webkit-box-align: left;
  margin-left: 0 px;
}

.title-photo {
  margin-left: 0.6rem;
  width: 12.5rem;
  height: 10rem;
}

.describe-area {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  padding-left: 0.8rem;
  width: 100%

}

.title-name {
  font-size: 1.8rem; 
  line-height: 6rem;
  font-weight:bold;
  color: #1890ff;
}

.describe-text {
  color: #8c939d;
}

.title-describe {
  display: flex;
  justify-content: space-between; 
  padding-top: 1rem; 
}

.create-time {
  padding-top: 0.18rem;
  margin-right: 1rem;
  font-size: 0.9rem;
  color: #8c939d;
}

.date-panel {
  display: flex;
  justify-content: space-between; 
  padding-top: 1rem; 
  margin: 0.6rem 0.8rem 0  0.8rem;
}

.score-panel {
  display: flex;
  justify-content: space-between; 
  padding-top: 1rem; 
  margin: 1rem 0.8rem 0  0.8rem;

}

.readme-panel {
  padding: 1rem;
  width: 100%;
}

.readme-title {
  font-size: 1.1rem; 
  font-weight:bold;
  color: #1890ff;
}

.readme-content {
  line-height: 2.5rem;
  color: #8c939d;
}

.text-id {
  color: #1890ff;
  font-size: 1.1rem;
  margin-right: 0.2rem;
}

.readme-text {
  color: #8c939d;
} 

.button-area {
  width: 100%;
  text-align: center;
}

.into-button {
  text-align: center;
}

.tips-text {
  font-size: 15px; 
  margin-right: 0.5rem;
  color: #303133;
}
</style>
