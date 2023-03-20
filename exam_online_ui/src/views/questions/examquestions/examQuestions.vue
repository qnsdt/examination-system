<template>
  <div>
    <div style ="margin-left: 15px">
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
	        <el-button 
	        @click="onClose"
	        plain
	        icon="el-icon-s-home"
	        size="mini"
	        >返回</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            @click="handleSave"
          >保存</el-button>
        </el-col>
      </el-row>
    </div>
    <div class="form-panel">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="试题类型" prop="questionsType">
            <el-select v-model="form.questionsType" @change = "onChoice" placeholder="请选择试题类型">
              <el-option
                v-for="dict in questionsTypeOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="parseInt(dict.dictValue)"
              ></el-option>
            </el-select>
          </el-form-item>
          <div class = "ueditor-area">
            <span class = "star-text">*</span>
            <span class= "ueditor-title">试题题目</span>
          </div>
          <vue-ueditor-wrap v-model="textValue" :config="editConfig" @ready="onEditorReady"  :destroy="true"></vue-ueditor-wrap>
          <br>
          <el-form-item label="题目分数" prop="questionsScore">
            <el-input-number v-model="form.questionsScore" :min="1" :max="20" label="请输入题目分数"></el-input-number>
          </el-form-item>
          <el-form-item label="复杂度 " prop="rateNumber">
            <div class = "rate-area">
              <el-rate
                v-model="form.rateNumber"
                show-score
                text-color="#ff9900">
              </el-rate>
            </div>
          </el-form-item>
        </el-form>
        <div class = "table-panel">
          <div class = "ueditor-area">
            <span class = "star-text">*</span>
            <span class= "ueditor-title">{{choiceText}}</span>
          </div>
          <el-table :data="answerList" >
          <el-table-column label="选项" width="50px" align="center" prop="optionCode" />
          <el-table-column label="选项描述" align="center" prop="optionDescribe">
            <template slot-scope="scope">
              <el-input v-model="scope.row.optionDescribe" placeholder="请输入题目选项" />
            </template>
          </el-table-column>
          <el-table-column label="操作" width="100px" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button v-if = "choiceNumber > 1 && answerList.indexOf(scope.row) === answerList.length - 1"
                size="mini"
                type="text"
                icon="el-icon-plus"
                @click="handleAdd()"
              >增加</el-button>
              <el-button v-if = "answerList.indexOf(scope.row) > 1"
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        </div>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <template v-if = "isRadio">
            <el-form-item label="正确答案" prop="rightAnswer">
              <el-radio-group v-model="radioValue">
                <el-radio
                  v-for="item in answerList"
                  :key="item.orderId"
                  :label="item.optionCode"
                >{{item.optionCode}} 选项</el-radio>
              </el-radio-group>
            </el-form-item>
          </template>
          <template v-else>
            <el-form-item label="正确答案" prop="rightAnswer">
              <el-checkbox-group v-model="checkItem">
                <el-checkbox
                  v-for="item in answerList"
                  :key="item.orderId"
                  :label="item.optionCode"
                >{{item.optionCode}} 选项</el-checkbox>
              </el-checkbox-group>
          </el-form-item>
          </template>
          <el-form-item label="答案分析" prop="answerAnalyse">
            <el-input v-model="form.answerAnalyse" type="textarea" placeholder="请输入答案分析" />
          </el-form-item>
          <el-form-item label="状态">
            <el-radio-group v-model="form.status">
              <el-radio
                v-for="dict in statusOptions"
                :key="dict.dictValue"
                :label="parseInt(dict.dictValue)"
              >{{dict.dictLabel}}</el-radio>
            </el-radio-group>
          </el-form-item>          
        </el-form>
    </div>
   </div>
</template>

<script>
import VueUeditorWrap from 'vue-ueditor-wrap'
import { baseApiUrl, uiUrl } from '@/config/sysinfo'
import { newS4Guid, newGuid } from '@/utils/guidtool'
import { listExamquestions, getExamquestions, delExamquestions, addExamquestions, updateExamquestions, exportExamquestions } from "@/api/questions/examquestions";
import { listQuestionscontent, getQuestionscontent, delQuestionscontent, addQuestionscontent, updateQuestionscontent, exportQuestionscontent } from "@/api/questions/questionscontent";
import { listAnswer, getAnswer, delAnswer, addAnswer, updateAnswer, exportAnswer } from "@/api/questions/answer";

export default {
  name: "Examquestions",
  components: { VueUeditorWrap},  
  data() {
    return {
      // 表单参数
      form: {},
      anylyseForm: {},
      // 试题类型字典
      questionsTypeOptions: [
        {dictValue: 1, dictLabel: '判断题'},
        {dictValue: 2, dictLabel: '单选题'},
        {dictValue: 3, dictLabel: '多选题'}
        ],
      // 状态字典
      statusOptions: [],
      bankData: {},
      questionsData: {},
      rules: {
        questionsTitle: [
          { required: true, message: "试题题目不能为空", trigger: "blur" }
        ],
        questionsType: [
          { required: true, message: "试题类型不能为空", trigger: "change" }
        ],
        questionsScore: [
          { required: true, message: "题目分数不能为空", trigger: "blur" }
        ],
        rateNumber: [
          { required: true, message: "复杂度不能为空", trigger: "blur" }
        ],
        rightAnswer: [
          { required: true, message: "正确答案不能为空", trigger: "blur" }
        ],
      },
      // 考试题目答案表格数据
      answerList: [],
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      optionItem: ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'],
      choiceText: '题目选项',
      choiceNumber: 0,
      isRadio: true,
      radioValue: null,
      textValue: null,
      checkItem: [],
      editConfig: {
        // 编辑器不自动被内容撑高
        autoHeightEnabled: false,
        // 初始容器高度
        initialFrameHeight: 200,
        // 初始容器宽度
        initialFrameWidth: '100%',
        // 上传文件接口（这个地址是我为了方便各位体验文件上传功能搭建的临时接口，请勿在生产环境使用！！！）
        serverUrl: baseApiUrl + '/ueditor/config',
        // UEditor 资源文件的存放路径，如果你使用的是 vue-cli 生成的项目，通常不需要设置该选项，vue-ueditor-wrap 会自动处理常见的情况，如果需要特殊配置，参考下方的常见问题2
        UEDITOR_HOME_URL: '/exam_online_ui/UEditor/'
      },
      Ueditor: null,
      isNew: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        questionsCode: null,
        optionDescribe: null,
      },            
    }
  },
  created() {
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询考试题目列表 */
    init_data (bankrow, row) {
      this.bankData = bankrow
      if (row === null) {
        this.form.status = 0
        this.form.bankCode = bankrow.bankCode
        this.form.questionsCode = newGuid()
        this.isNew = true
      } else {
        this.form = row
        this.isNew = false
        this.queryParams.questionsCode = this.form.questionsCode
        this.getList()
      }
    },
    // 表单重置
    reset() {
      this.form = {
        bankCode: this.bankData.bankCode,
        questionsId: null,
        questionsCode: newGuid(),
        questionsTitle: null,
        questionsType:null,
        questionsScore:null,
        rateNumber:null,
        rightAnswer:null,
        answerAnalyse:null,
        status: 0,
        createDept:null
      }
      this.resetForm("form");
    },   
    /** 查询考试题目答案列表 */
    getList() {
      listAnswer(this.queryParams).then(response => {
        this.answerList = response.rows;
        this.showData()
        this.getContent()
      });
    },
    showData () {
      if (this.form.questionsType === 1 || this.form.questionsType === 2) {
        // 是判断题或者说是选择题
        this.isRadio = true       
        this.radioValue = this.form.rightAnswer        
      } else if (this.form.questionsType === 3) {
        this.isRadio = false
        this.checkItem = this.form.rightAnswer.split(",")
      }
    },
    getContent() {
      getQuestionscontent(this.form.questionsCode).then(response => {
        this.textValue = response.data.questionsContent
      });
    },        
    onClose () {
      this.$emit('refreshData')            
    },
    checkData () {
      if (this.form.questionsType === undefined || this.form.questionsType === null) {
        this.$message.error('未选择题目类型')
        return false
      }

      if (this.Ueditor.getContentTxt() === null || this.Ueditor.getContentTxt() === '') {
        this.$message.error('未输入试题题目')
        return false  
      }
      this.form.questionsTitle = this.Ueditor.getContentTxt()
      this.form.questionsContent = this.textValue
      if (this.form.questionsScore === undefined || this.form.questionsScore === null || this.form.questionsScore <= 0) {
        this.$message.error('题目分数不能为零')
        return false  
      }

      if (this.form.rateNumber === 0) {
        this.$message.error('复杂度需要维护')
        return false  
      }
      
      // 循环判断题目项目内容
      for (let i = 0; i < this.answerList.length; i ++) {
        if (this.answerList[i].optionDescribe === null) {
          this.$message.error('题目选项描述不能为空')
          return false  
        }
      }
      
      // 判断正确答案是否填入
      if (this.form.questionsType === 1 || this.form.questionsType === 2) {
        // 是判断题或者说是选择题
        if (this.radioValue === null) {
          this.$message.error('判断题或单选题答案必须选择')
          return false  
        }       
      } else if (this.form.questionsType === 3) {
        if (this.checkItem === [] || this.checkItem.length === 0) {
          this.$message.error('多选题答案必须选择')
          return false  
        }  
      } else {
        this.$message.error('系统不识别的题目类型')
        return false         
      }

      return true
    },
    handleData () {
      if (this.form.questionsType === 1 || this.form.questionsType === 2) {
        // 是判断题或者说是选择题
       
        this.form.rightAnswer = this.radioValue
        for (let i = 0; i < this.answerList.length; i ++) {
          if (this.answerList[i].optionCode === this.radioValue) {
            this.answerList[i].isRight = 0
            break
          }
        }       
      } else if (this.form.questionsType === 3) {
        let count = 0
        for (let i = 0; i < this.checkItem.length; i ++) {
          for (let j = 0; j < this.answerList.length; j ++) {
            if (this.checkItem[i] === this.answerList[j].optionCode) {
              this.answerList[j].isRight = 0
              count ++
            }
          }
        }
        
        this.form.rightAnswer = ''
        for (let i = 0; i < this.answerList.length; i ++) {
          if (this.answerList[i].isRight === 0) {
            this.form.rightAnswer +=  this.answerList[i].optionCode

            if (count > 1) {
              this.form.rightAnswer += ','
            }
            count --
          }
        }
      }
    },
    handleSave () { 
      this.doSave(1)
    },
    handleSaveOrExit () {
      this.doSave(2)
    },
    doSave(type) {
      if (this.checkData()) {
        this.handleData()
        this.form.answerList = this.answerList
        if (this.isNew) {
          addExamquestions(this.form).then(response => {
            this.msgSuccess("保存成功");
          });
          console.log('清除数据')
        } else {
          updateExamquestions(this.form).then(response => {
            this.msgSuccess("修改成功");
          });
        }
      }
    },   
    onChoice (choice) {
      
      if (choice === 1 ) {
        if (this.bankData.judgeScore !== undefined && this.bankData.judgeScore !== null ) {
          this.form.questionsScore = this.bankData.judgeScore
          this.choiceText = '判断题选项'
          this.choiceNumber = 1
          this.init_Table()
          this.isRadio = true
        }
      } else if (choice === 2) {
        // 是单选题
        if (this.bankData.radioScore !== undefined && this.bankData.radioScore !== null ) {
          this.form.questionsScore = this.bankData.radioScore
          this.choiceText = '单选题选项'
          this.choiceNumber = 2
          this.init_Table()
          this.isRadio = true
        }
      }  else {
        // 是多选题
        if (this.bankData.choiceScore !== undefined && this.bankData.choiceScore !== null ) {
          this.form.questionsScore = this.bankData.choiceScore
          this.choiceText = '多选题选项'
          this.choiceNumber = 3
          this.init_Table()
          this.isRadio = false
        }        
      }   
    },
    init_Table () {
      this.answerList = []
      const answerData = {
        questionsCode : this.form.questionsCode,
        orderId: 1,
        optionCode: 'A',
        isRight: 1,
        optionDescribe: null
      }

      this.answerList.push(answerData)

      const answerData1 = {
        questionsCode : this.form.questionsCode,
        orderId: 2,
        optionCode: 'B',
        isRight: 1,
        optionDescribe: null
      }

      this.answerList.push(answerData1)
    },
    handleAdd () {
      if (this.answerList > 10) {
        this.$message.error('题目选项不能超过10项')        
        return
      }

      for (let i = 0; i < this.answerList.length; i ++) {
        if (this.answerList[i].optionDescribe === null) {
          this.$message.error('题目选项描述不能为空')        
          return
        }
      }

      const answerData = {
        questionsCode : this.form.questionsCode,
        orderId: this.answerList.length + 1,
        optionCode: this.optionItem[this.answerList.length],
        isRight: 1,
        optionDescribe: null
      }

      this.answerList.push(answerData)
      
    },
    handleDelete (row) {
      this.answerList.splice(this.answerList.indexOf(row), 1) 
      this.resetOption()
    },
    resetOption () {
      for (let i = 0; i < this.answerList.length; i ++) {
        this.answerList[i].orderId = i + 1
        this.answerList[i].isRight = 1
        this.answerList[i].optionCode = this.optionItem[i]
      }
    },
    onEditorReady (editor) {      
      this.Ueditor = editor
    }                   
  }
};
</script>

<style scoped lang="scss">
.form-panel {
  margin:  20px 0px 20px 15px;
}

.rate-area {
  padding: 8px 0px 0px 0px;
}

.table-panel {
  margin:  0px 0px 20px 0px;
}

.option-title {
    text-align: right;
    vertical-align: middle;
    float: left;
    font-size: 14px;
    color: #606266;
    font-weight:bold;
    line-height: 18px;
    padding: 0 0 10px 10px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}


.ueditor-title {
    font-size: 14px;
    color: #606266;
    font-weight:bold;
    line-height: 18px;
    padding: 0 0 10px 0px;
}

.ueditor-area {
 margin:  0px 0px 10px 0px; 
}

.star-text {
    color: #ff4949;
    margin-right: 4px;  
}
</style>