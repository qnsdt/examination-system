<template>
  <div >
    <div v-if= "this.$store.state.exam.showtitle">
      <el-steps  :active="active" simple finish-status="success">
        <el-step title="内 容" icon="el-icon-edit"></el-step>
        <el-step title="组 卷" icon="el-icon-date"></el-step>
        <el-step title="选 人" icon="el-icon-user"></el-step>
      </el-steps>
      <div class = "button-panel">
        <el-button  icon="el-icon-arrow-left" type="primary" size="mini"  :disabled = "leftDisabled" plain @click="goPrevious">上一步</el-button>          
        <el-button  icon="el-icon-s-home" plain size="mini"  @click="goBack">返回</el-button>  
        <div v-show="!rightDisabled">
          <el-button   icon="el-icon-arrow-right" type="primary" size="mini" :disabled = "rightDisabled" plain @click="goNext">下一步</el-button>  
        </div>
        <div v-show="rightDisabled">
          <el-button   icon="el-icon-coin" type="primary" size="mini"  plain @click="doSave">保存</el-button>  
        </div>
      </div>  
    </div>
    <div v-if = "showUi === 1">
      <content-edit ref="contentRef" @refreshContent ="editDoneHandle"></content-edit>
    </div>
    <div v-else-if= "showUi === 2">
      <select-questions ref="questionsRef" @refreshQuestions ="questionsDoneHandle"></select-questions>
    </div>
    <div v-else-if= "showUi === 3">
      <choice-user ref="groupRef" @refreshGroup ="groupDoneHandle"></choice-user>
    </div>
  </div>
</template>

<script>
import { newS4Guid, newGuid } from '@/utils/guidtool'
import contentEdit from './contentEdit'
import selectQuestions from './selectQuestions'
import { saveData } from "@/api/exam/examdata";
import choiceUser from './choiceUser'

export default {
  components: {
    choiceUser,
    contentEdit,
    selectQuestions
  },  
  data() {
    return {
      showUi: 1,
      active: 1,
      examCode: null,
      examData: null,
      leftDisabled: true,
      rightDisabled: false,
      currentId: -1,
      lastId: -1,
      contentData: null,
      questionsList: null,
      groupList: null
    }
  },
  created() {
    this.$store.commit("SET_SHOWTITLE", true)
  },
  methods: {
    init_data () {
      this.examCode = newGuid()
      this.contentData = {
         examCode: this.examCode,
         forceDone: 1,
         status: 0        
      }
      this.changePage(1)      
    },
    goPrevious () {
      this.lastId = this.active
      if (this.active-- <= 1) {
        this.active = 3
      }

      if (this.active === 2) {
        this.rightDisabled = false
      } else if (this.active === 1) {
        this.leftDisabled = true
      }

      this.changePage(this.active)
    },
    goNext () {
      this.lastId = this.active
      if (this.active++ > 2) {
        this.active = 1
      } 
      
      if (this.active === 3) {
        this.rightDisabled = true
      } else if (this.active > 1) {
        this.leftDisabled = false
      }
      this.changePage(this.active)
    },
    doSave() {
      this.groupList = this.$refs.groupRef.getData()

      if (this.groupList === null || this.groupList.length === 0 ) {
        this.$message.error('需要先选中人员分组')
        return
      }
      // 封装数据
      let questionsSaveList = []
      for (let i = 0; i < this.questionsList.length; i ++) {
        questionsSaveList.push(
          {
            examCode: this.contentData.examCode,
            questionsCode: this.questionsList[i].questionsCode,
            questionsNumber: i + 1
          }
        )
      }

      let groupSaveList = []
      for (let i = 0; i < this.groupList.length; i ++) {
        groupSaveList.push (
          {
            examCode: this.contentData.examCode,
            groupCode: this.groupList[i].groupCode
          }
        )
      }
      let allData = {
        examTaskManager: this.contentData,
        taskQuestionsList: questionsSaveList,
        taskGroupList: groupSaveList
      }

      saveData(allData).then(response => {
        this.$message.success('保存成功!')
      });
    },
    changePage(id) {
      if (this.lastId === 1) {
        const contentdata = this.$refs.contentRef.getData()
        if (contentdata === null) {
          this.active = 1
          return
        }
        this.contentData = contentdata.examData
        this.choiceList = contentdata.choiceList
      } else if (this.lastId === 2 ) {
        this.questionsList = this.$refs.questionsRef.getData()
        if (id !== 1 && (this.questionsList === null || this.questionsList.length === 0 )) {
          this.$message.error('需要先选中题目')
          this.active = 2
          this.rightDisabled = false
          return
        }
      } else if (this.lastId === 3 ) {
        this.groupList = this.$refs.groupRef.getData()
      }

      this.showUi = id
      if (id === 1) {
        this.$nextTick(() => {
          this.$refs.contentRef.init_data(this.contentData, this.choiceList)
        })
      } else if (id === 2) {
        this.$nextTick(() => {
          this.$refs.questionsRef.init_data(this.contentData, this.questionsList)
        })
      } else if (id === 3) {
         this.$nextTick(() => {
          this.$refs.groupRef.init_data(this.contentData, this.groupList)
        })       
      }

    },
    editDoneHandle () {
      console.log('editDoneHandle')
    },
    questionsDoneHandle () {
      console.log('questionsDoneHandle')
    },
    groupDoneHandle () {
      console.log('groupDoneHandle')
    },     
    goBack () {
      this.$emit('refreshEdit')
    }          
  }
}
</script>

<style scoped lang="scss">
.button-panel {
  margin-top: 12px;    
  display: flex;
  justify-content: space-between;  
}
</style>

