<template>
  <div >
    <div v-if="choiceVisible">
      <div class = "content-area" :style = "ui_style">
        <div class = "button-panel" v-if="isUpdate">
          <el-button
            @click="onClose"
            plain
            icon="el-icon-delete"
            size="mini"
          >返回</el-button>
          <el-button
            type="success"
            plain
            icon="el-icon-check"
            size="mini"
            @click="onSave"
            >保存</el-button>
         </div>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="考试名称" prop="examName">
            <el-input v-model="form.examName" placeholder="请输入考试名称" />
          </el-form-item>
          <el-form-item label="考试说明" prop="examDescribe">
            <el-input v-model="form.examDescribe" placeholder="请输入考试说明" />
          </el-form-item>
          <el-row :gutter="20">
            <el-col :span="12" :xs="24">
              <el-form-item label="组卷方式" prop="buildType">
                <el-select v-model="form.buildType" placeholder="请选择组卷方式">
                  <el-option
                    v-for="dict in buildTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" :xs="24">
              <el-form-item label="强制抽卷" prop="forceDone">
                <el-radio-group v-model="form.forceDone">
                  <el-radio
                    v-for="dict in forceDoneOptions"
                    :key="dict.dictValue"
                    :label="parseInt(dict.dictValue)"
                  >{{dict.dictLabel}}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="选择题库" prop="examBank">
            <el-row >
              <el-col :span="20" >
                <el-input type="textarea" :rows="2" readonly v-model="form.examBankText"  />
              </el-col>
              <el-col :span="4" >
                <div class = "choice-button">
                  <el-button type="primary" icon="el-icon-folder-checked" size="mini" @click="doChoice()">选择</el-button>
                </div>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="标题图片">
            <el-upload
              class="avatar-uploader"
              :action="url"
              :data="upLoadData"
              :show-file-list="false"
              :before-upload="beforeAvatarUpload"
            >
              <img
                v-if="imageUrl"
                :src="imageUrl"
                class="avatar"
              >
              <i
                v-else
                class="el-icon-plus avatar-uploader-icon"
              ></i>
            </el-upload>
          </el-form-item>          
          <el-row :gutter="20">
            <el-col :span="12" :xs="24">
		          <el-form-item label="开始时间" >
		            <el-date-picker
		              v-model="startDate"
		              value-format="yyyy-MM-dd HH:mm:ss"
		              type="datetime"
		              placeholder="选择考试开始日期时间">
		            </el-date-picker>
		          </el-form-item>        
            </el-col>
            <el-col :span="12" :xs="24">
		          <el-form-item label="考试时长" prop="examDuration">
		            <el-time-select
		              v-model="form.examDuration" 
		              :picker-options="{
		                start: '00:15',
		                step: '00:15',
		                end: '9:30'
		              }"
                  @change="onTimeChange"
		              placeholder="选择时间">
		            </el-time-select>
		          </el-form-item>  
            </el-col>
          </el-row>
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
        <!-- 剪裁组件弹窗 -->
        <el-dialog
          title="裁切题库封面"
          :visible.sync="cropperModel"
          width="950px"
          center
        >
          <cropper-image
            :Name="cropperName"
            @uploadImgSuccess="handleUploadSuccess"
            ref="child"
          >
          </cropper-image>
        </el-dialog>        
      </div>      
    </div>      
    <div v-else >
      <exam-select  ref="selectRef" @refreshSelect ="selectDoneHandle"></exam-select>      
    </div>    
  </div>
</template>

<script>
import { baseApiUrl, uiUrl } from '@/config/sysinfo'
import examSelect from '@/components/ExamSelect/index'
import CropperImage from './CropperImage'
import { listQuestionsbankBycode } from "@/api/questions/questionsbank";
import { updateExamtask } from "@/api/exam/examtask";
import { dateToString, dateTextToDateString, dateRemoveSeconds, dateTextToDatetimeString } from "@/utils/dateUtils";

export default {
  components: {
    examSelect,
    CropperImage    
  },    
  data () {
    return {
      choiceVisible: true,
      // 状态字典
      statusOptions: [],
      ui_style : null,
      hosturl: '',
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        examCode: [
          { required: true, message: "考试代码不能为空", trigger: "blur" }
        ],
        examName: [
          { required: true, message: "考试名称不能为空", trigger: "blur" }
        ],
        buildType: [
          { required: true, message: "组卷方式不能为空", trigger: "change" }
        ],
        examBank: [
          { required: true, message: "题库必须选择", trigger: "change" }
        ],
        examDuration: [
          { required: true, message: "考试时长不能为空", trigger: "change" }
        ]
      },
      buildTypeOptions: [
        {dictValue: 1, dictLabel: "人工组卷"},
        {dictValue: 2, dictLabel: "随机抽题"},
        {dictValue: 3, dictLabel: "考时随机组卷"}
      ],
      choiceList: null,
      forceDoneOptions: [
        {dictValue: 0, dictLabel: "强制抽卷"},
        {dictValue: 1, dictLabel: "不强制"}
      ],
      imageUrl: null,
      startDate: null,
      url: '',
      upLoadData: {
        guidCode: ''
      },
      cropperModel: false,
      cropperName: '',
      isUpdate: false
    }
  },
  created () {
    const s_width = document.body.clientWidth
    if (s_width < 1000) {
       this.ui_style = 'margin-left: 2%; margin-right: 2%;'    
    } else if (s_width >= 1000 && s_width < 1366) {
       this.ui_style = 'margin-left: 8%; margin-right: 8%;'    
    } else if (s_width >= 1366 && s_width < 1566) {
       this.ui_style = 'margin-left: 12%; margin-right: 12%;'    
    } else if (s_width >= 1566 && s_width < 1766) {
       this.ui_style = 'margin-left: 18%; margin-right: 18%;'    
    } else {
       this.ui_style = 'margin-left: 24%; margin-right: 24%;'  
    }
    this.hosturl = baseApiUrl
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });    
  },
  methods: {
    init_data (contentdata, choicelist) {
      this.isUpdate = false
      if (contentdata.examCode !== undefined && contentdata.examCode !== null ) {
        this.form =  contentdata
        if (this.form.pictureUrl !== undefined && this.form.pictureUrl !== null) {
          this.imageUrl =  this.hosturl + this.form.pictureUrl
        }
      }

      if (choicelist !== undefined && choicelist !== null) {
        this.choiceList = choicelist
      } 
    },
    update_data (row) {
      this.isUpdate = true
      row.forceDone = Number(row.forceDone)
      row.buildType = Number(row.buildType)
      
      this.form = row
      if (this.form.pictureUrl !== undefined && this.form.pictureUrl !== null) {
        this.imageUrl =  this.hosturl + this.form.pictureUrl
      }
      this.startDate = row.startTime
      listQuestionsbankBycode(row.examBank).then(response => {
        this.choiceList = response.data
      });
    },
    doChoice () {
      this.choiceVisible = false
      this.$store.commit("SET_SHOWTITLE", false)
      this.$nextTick(() => {
       this.$refs.selectRef.init_data(this.choiceList)
      })
    },
    selectDoneHandle (saveList) {
      this.$store.commit("SET_SHOWTITLE", true)
      this.choiceList = saveList
      this.choiceVisible = true
      
      let nametext = ''
      let codetext = ''
      if (this.choiceList !== null && this.choiceList.length > 0) {
        for (let i = 0; i < this.choiceList.length; i ++) {
          if (i < this.choiceList.length - 1) {
            nametext += this.choiceList[i].bankName + ','
            codetext += this.choiceList[i].bankCode + ','
          } else {
            nametext += this.choiceList[i].bankName
            codetext += this.choiceList[i].bankCode
          }
        }
      }
      this.form.examBankText = nametext
      this.form.examBank = codetext
    },
    onTimeChange () {
      if (this.startDate === undefined || this.startDate === null) {
        this.form.examDuration = undefined
        this.$message.error('请先输入考试开始时间')
        return
      }
      this.set_time()
    },
    set_time () {
      let timeitem = this.form.examDuration.split(":")
      const addhour = Number(timeitem[0]) * 60 * 60 * 1000
      const addminute = Number(timeitem[1]) * 60 * 1000
      const datestr = dateRemoveSeconds(new Date(this.startDate))
      this.form.startTime = Number(new Date(datestr).getTime())
      this.form.endTime =  this.form.startTime + addhour + addminute
    },
    getData () {
      if (this.onCheck()) {
        const saveData = {
          choiceList: this.choiceList,
          examData: this.form
        }
        return saveData
      } else {
        return null
      }
    },
    saveData () {
      if (this.onCheck()) {
        this.set_time ()
        updateExamtask(this.form).then(response => {
          this.msgSuccess("修改成功");
          return true
        });   
      } else {
        return false
      }
    },
    onClose () {
      this.$emit('refreshContent')
    },    
    onSaveOrBack () {
      if (this.saveData()) {
        this.$emit('refreshContent')
      }
    },     
    onSave () {
      this.saveData()
    },
    onCheck () {
      if (this.form.examName === undefined || this.form.examName === null) {
        this.$message.error('需要输入考试名称')
        return false
      }

      if (this.form.buildType === undefined || this.form.buildType === null) {
        this.$message.error('需要先选择组卷方式')
        return false
      }

      if (this.form.examBankText === undefined || this.form.examBankText === null) {
        this.$message.error('需要先选择题库')
        return false
      }

      if (this.form.pictureUrl === undefined || this.form.pictureUrl === null) {
        this.$message.error('需要先加入标题图片')
        return false
      }          

      if (this.startDate === undefined || this.startDate === null) {
        this.$message.error('需要输入考试开始时间')
        return false
      }

      if (this.form.examDuration === undefined || this.form.examDuration === null) {
        this.$message.error('需要输入考试时长')
        return false
      }
      
      if (this.form.startTime === undefined || this.form.startTime === null) {
        this.set_time()
      }

      return true
    },
    beforeAvatarUpload (file) {
      this.cropperModel = true
      this.cropperName = name
      this.$nextTick(() => {
        this.$refs.child.selectImg(this.form.examCode, file)
      })      
    },
    // 图片上传成功后
    handleUploadSuccess (data) {
      this.imageUrl =  this.hosturl + data.photoUrl
      this.form.pictureUrl = data.photoUrl
      this.cropperModel = false
    }
  }
};
</script>

<style scoped lang="scss">
.content-area {
   margin-top: 1.6rem;
   background-color: #f7f7f7;
}

.button-panel {
  padding-bottom: 1.6rem;
  background-color: #fff; 
}

.form-area {
  padding-top: 1.6rem;
  background-color: #fff;  
}

.choice-button {
  text-align: center;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 128px;
  height: 128px;
  line-height: 128px;
  text-align: center;
  background-color: #fff;
}
.avatar {
  width: 128px;
  height: 128px;
  display: block;
}
</style>


