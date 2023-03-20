<template>
  <div >
    <div v-if="choiceVisible">
      <div class = "content-area" :style = "ui_style">
      <el-button
        @click="onClose"
        plain
        icon="el-icon-delete"
        size="mini"
      >返回</el-button>
      <el-button
        type="primary"
        plain
        icon="el-icon-edit"
        size="mini"
        @click="onSaveOrBack"
        >保存并返回</el-button>
      <el-button 
        type="success"
        plain
        icon="el-icon-check"
        size="mini"
        @click="onSave"
        >保存</el-button>      
        <div class="form-area">
        <el-form ref="dataForm" :model="dataForm" :rules="rules" label-width="80px">
            <el-form-item label="题库名称" prop="bankName">
            <el-input v-model="dataForm.bankName" placeholder="请输入题库名称" />
            </el-form-item>
            <el-form-item label="题库描述" prop="bankDescribe">
            <el-input v-model="dataForm.bankDescribe" placeholder="请输入题库描述" />
            </el-form-item>
            <el-row :gutter="20">
              <el-col :span="12" :xs="24">
                <el-form-item label="题库分类" prop="examType">
                  <treeselect v-model="dataForm.examType" :options="examTypeOptions" :show-count="true" placeholder="请选择题库分类" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12" :xs="24">
                <el-form-item label="题库版本" prop="bankVersion">
                  <el-input v-model="dataForm.bankVersion" placeholder="请输入题库版本" />
                </el-form-item>
              </el-col>
              <el-col :span="12" :xs="24">
                <el-form-item label="上线日期" prop="onlineDate">
                <el-date-picker clearable size="small"
                    v-model="dataForm.onlineDate"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format= "yyyy-MM-dd"
                    placeholder="选择上线日期">
                </el-date-picker>
                </el-form-item>
               </el-col>
            </el-row>
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
              <el-col :span="8" :xs="24">
                <el-form-item label="单选分数" prop="radioScore">
                  <el-input-number v-model="dataForm.radioScore" :min="1" :max="20" label="请输入单选分数"></el-input-number>
                </el-form-item>
              </el-col>
              <el-col :span="8" :xs="24">
                <el-form-item label="多选分数" prop="choiceScore">
                <el-input-number v-model="dataForm.choiceScore" :min="1" :max="20" label="请输入多选分数"></el-input-number>
                </el-form-item>
              </el-col>
              <el-col :span="8" :xs="24">
                <el-form-item label="判断分数" prop="judgeScore">
                <el-input-number v-model="dataForm.judgeScore" :min="1" :max="20" label="请输入判断分数"></el-input-number>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label="状态">
            <el-radio-group v-model="dataForm.status">
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
    </div>  
    <div v-else >
    </div>    
  </div>
</template>

<script>
import { newS4Guid, newGuid } from '@/utils/guidtool'
import { baseApiUrl, uiUrl } from '@/config/sysinfo'
import { listQuestionsbank, getQuestionsbank, delQuestionsbank, addQuestionsbank, updateQuestionsbank, exportQuestionsbank } from "@/api/questions/questionsbank";
import { listExamtype, getExamtype, typeTreeSelect, delExamtype, addExamtype, updateExamtype, exportExamtype } from "@/api/questions/examtype";
import CropperImage from './CropperImage'
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  components: {
    Treeselect,
    CropperImage    
  },  
  data () {
    return {
      choiceVisible: true,
      isNew: true,
      dataForm: {},
      ui_style : null,
      // 表单校验
      rules: {
        bankName: [
          { required: true, message: "题库名称不能为空", trigger: "blur" }
        ],
        examType: [
          { required: true, message: "考试类型不能为空", trigger: "blur" }
        ],      
      },
      // 考试类型字典
      examTypeOptions: [],
      // 状态字典
      statusOptions: [],
      imageUrl: null,
      url: '',
      upLoadData: {
        guidCode: ''
      },
      cropperModel: false,
      cropperName: '',
      newCode: null ,
      hosturl: ''                 
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
    init_data (row) {
      if (row === undefined) {
        this.isNew = true
        this.dataForm.radioScore = 2
        this.dataForm.choiceScore = 5
        this.dataForm.judgeScore = 2
        this.dataForm.onlineDate = new Date()
        this.dataForm.status = 0
        this.dataForm.bankCode = newGuid()
        this.newCode = this.dataForm.bankCode
      } else {
        this.dataForm = row
        this.newCode = this.dataForm.bankCode
        this.imageUrl =  this.hosturl + this.dataForm.pictureUrl
        this.isNew = false
      }
      this.getTypeTreeselect()
    },
    /** 查询部门下拉树结构 */
    getTypeTreeselect() {
      typeTreeSelect().then(response => {
        this.examTypeOptions = response.data
      });
    },
    beforeAvatarUpload (file) {
      this.cropperModel = true
      this.cropperName = name
      this.$nextTick(() => {
        this.$refs.child.selectImg(this.newCode, file)
      })      
    },
    // 图片上传成功后
    handleUploadSuccess (data) {
      this.imageUrl =  this.hosturl + data.photoUrl
      this.dataForm.pictureUrl = data.photoUrl
      this.cropperModel = false
    },            
    onClose () {
      this.$emit('refreshDataList')            
    },    
    onSaveOrBack () {
      this.submitForm()  
      this.$nextTick(() => {
        this.$emit('refreshDataList')            
      })
    }, 
    onSave () {
      this.submitForm()
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          if (!this.isNew) {
            updateQuestionsbank(this.dataForm).then(response => {
              this.msgSuccess("修改成功");
            });
          } else {
            addQuestionsbank(this.dataForm).then(response => {
              this.msgSuccess("保存成功");
            });
          }
        }
      });
    }          
  }
};
</script>

<style scoped lang="scss">
.content-area {
   background-color: #f7f7f7;
}

.form-area {
  padding-top: 1.6rem;
  background-color: #fff;  
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
  background-color: #f7f7f7;
}
.avatar {
  width: 128px;
  height: 128px;
  display: block;
}
</style>


