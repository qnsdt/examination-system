<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图片名称" prop="originalName">
        <el-input
          v-model="queryParams.originalName"
          placeholder="请输入图片名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <div class = "uploadpanel">
      <el-upload
        class="avatar-uploader"
        :action="serverUrl"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <el-button 
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
        >上传图片</el-button>
      </el-upload>  
    </div>

    <el-row :gutter="20" >
      <el-col :span="6" v-for="(item, index) in photoList" :key="item.photoId" style="padding: 14px;">
        <el-card :body-style="{ padding: '0px' }">
          <el-image
          :style="imgStyle"
          :src= "creatUrl(item.photoUrl)"
          :fit="fits"></el-image>
          <div style="padding: 14px;">
            <span>{{item.originalName}}</span>
            <div class="bottom clearfix">
              <time class="time">{{item.uploadTime}}</time>
              <div v-if = showStatus(item.uploadName)>
                 <el-button type="text" class="button" 
                 @click="handleDelete(item.photoId)"
                 v-hasPermi="['fms:photo:remove']">删除</el-button>              
              </div>
              <el-button type="text" class="button" @click="downloadPic(item.photoUrl, item.originalName)">下载</el-button>              
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <div class="form-footer">
      <el-pagination
        @current-change="getList"
        :current-page.sync="queryParams.pageNum"
        :page-size="queryParams.pageSize"
        layout="prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>



  </div>
</template>

<script>
import { listPhoto, getPhoto, delPhoto, addPhoto, updatePhoto, exportPhoto } from "@/api/fms/photo";
import { baseApiUrl } from '@/config/sysinfo'

export default {
  name: "Photo",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 8,
      // 图片管理表格数据
      photoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 图片序号字典
      photoIdOptions: [],
      // 文件名称字典
      fileNameOptions: [],
      // 图片路径字典
      photoPathOptions: [],
      // 图片链接字典
      photoUrlOptions: [],
      // 原始名称字典
      originalNameOptions: [],
      // 上传者字典
      uploadNameOptions: [],
      // 上传部门字典
      uploadDeptOptions: [],
      // 上传时间字典
      uploadTimeOptions: [],
      // 状态字典
      statusOptions: [],
      
      fits: 'fill',

      imgStyle: 'width: 100%; height: 200px',
      
      // 服务器地址
      serverUrl: null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 8,
        originalName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        fileName: [
          { required: true, message: "文件名称不能为空", trigger: "blur" }
        ],
        photoPath: [
          { required: true, message: "图片路径不能为空", trigger: "blur" }
        ],
        photoUrl: [
          { required: true, message: "图片链接不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.serverUrl = baseApiUrl + '/fms/photo/add/' + this.$store.getters.name
    this.getHeight(); 
    this.getList();
    this.getDicts("${column.dictType}").then(response => {
      this.photoIdOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.fileNameOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.photoPathOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.photoUrlOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.originalNameOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.uploadNameOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.uploadDeptOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.uploadTimeOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });

  },
  mounted() {
    // 监听窗口变化，使得轮播图高度自适应图片高度
    window.addEventListener("resize", () => {
      this.getHeight()
    });
  } ,  
  methods: {
    getHeight() {
      let hsize = parseInt((document.body.clientHeight - 200) / 4)
      this.imgStyle = 'width: 100%; height: ' + hsize + 'px'
    },
    creatUrl (urlpath) {
      return baseApiUrl + urlpath
    },
    showStatus (username) {
      if (this.$store.getters.name === username) {
        return true
      } else {
        return false
      }

    },
    /** 查询图片管理列表 */
    getList() {
      this.loading = true;
      listPhoto(this.queryParams).then(response => {
        this.photoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    downloadPic(picurl,photoname) {
      var image = new Image()
      // 解决跨域 Canvas 污染问题
      image.setAttribute('crossOrigin', 'anonymous')
      image.onload = function() {
        var canvas = document.createElement('canvas')
        canvas.width = image.width
        canvas.height = image.height
        var context = canvas.getContext('2d')
        context.drawImage(image, 0, 0, image.width, image.height)

        var index = photoname.lastIndexOf(".");
        var suffix = photoname.substr(index+1);
        var url 
        if (suffix === 'png') {
          url = canvas.toDataURL('image/png') //得到图片的base64编码数据
        } else {
          url = canvas.toDataURL('image/jpeg') //得到图片的base64编码数据
        }
        var a = document.createElement('a') // 生成一个a元素
        var event = new MouseEvent('click') // 创建一个单击事件
        a.download = photoname || 'photo' // 设置图片名称
        a.href = url // 将生成的URL设置为a.href属性
        a.dispatchEvent(event) // 触发a的单击事件
      }
      image.src = baseApiUrl + picurl        
    },
    // 图片序号字典翻译
    photoIdFormat(row, column) {
      return this.selectDictLabel(this.photoIdOptions, row.photoId);
    },
    // 文件名称字典翻译
    fileNameFormat(row, column) {
      return this.selectDictLabel(this.fileNameOptions, row.fileName);
    },
    // 图片路径字典翻译
    photoPathFormat(row, column) {
      return this.selectDictLabel(this.photoPathOptions, row.photoPath);
    },
    // 图片链接字典翻译
    photoUrlFormat(row, column) {
      return this.selectDictLabel(this.photoUrlOptions, row.photoUrl);
    },
    // 原始名称字典翻译
    originalNameFormat(row, column) {
      return this.selectDictLabel(this.originalNameOptions, row.originalName);
    },
    // 上传者字典翻译
    uploadNameFormat(row, column) {
      return this.selectDictLabel(this.uploadNameOptions, row.uploadName);
    },
    // 上传部门字典翻译
    uploadDeptFormat(row, column) {
      return this.selectDictLabel(this.uploadDeptOptions, row.uploadDept);
    },
    // 上传时间字典翻译
    uploadTimeFormat(row, column) {
      return this.selectDictLabel(this.uploadTimeOptions, row.uploadTime);
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        photoId: null,
        fileName: null,
        photoPath: null,
        photoUrl: null,
        originalName: null,
        uploadName: null,
        uploadDept: null,
        uploadTime: null,
        status: 0
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 修改按钮操作 */
    handleAvatarSuccess(res, file) {
      this.queryParams.pageNum = parseInt(this.total  / this.queryParams.pageSize + 1);

      this.getList();
      this.msgSuccess("上传成功");

    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isPNG = file.type === 'image/png';
      const isLt6M = file.size / 1024 / 1024 < 6;

      if (!isJPG && !isPNG) {
        this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
      }
      
      if (!isLt6M) {
        this.$message.error('上传头像图片大小不能超过 6MB!');
      }

     
      return (isJPG || isPNG) && isLt6M;
    },
    /** 删除按钮操作 */
    handleDelete(photoid) {
      this.$confirm('是否确认删除图片管理编号为"' + photoid + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPhoto(photoid);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    }    
  } 
};
</script>

<style>
  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    margin-left: 10px;
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }

  .uploadpanel {
    margin-left: 10px;
  }

  .form-footer {
    text-align: right;
  }
</style>