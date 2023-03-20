<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文件标题" prop="fileTitle">
        <el-input
          v-model="queryParams.fileTitle"
          placeholder="请输入文件标题"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['fms:files:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="filesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文件序号" width="55" align="center" prop="fileId" />
      <el-table-column label="文件标题" align="center" prop="fileTitle"  />
      <el-table-column label="文件描述" align="center" prop="fileDescribe"  />
      <el-table-column label="上传者" align="center" prop="uploadName" />
      <el-table-column label="上传时间" align="center" prop="uploadTime" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" >
        <template slot-scope="scope">
            <el-button v-if = showStatus(scope.row.uploadName)
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['fms:files:edit']"
            >修改</el-button>
            <el-button v-if = showStatus(scope.row.uploadName)
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['fms:files:remove']"
            >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
            @click="handleDownload(scope.row)"
            v-hasPermi="['fms:files:export']"
          >下载</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
            @click="handleFileUrl(scope.row)"
            v-hasPermi="['fms:files:export']"
          >下载链接</el-button>          
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改文件管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文件标题" prop="fileTitle">
          <el-input v-model="form.fileTitle" placeholder="请输入文件标题" />
        </el-form-item>
        <el-form-item label="文件描述" prop="fileDescribe">
          <el-input v-model="form.fileDescribe" placeholder="请输入文件描述" />
        </el-form-item>
        <el-form-item label="文件名称" prop="fileName">
          <el-input v-model="form.fileName" placeholder="文件名称"  :readonly="true"/>
        </el-form-item>
        <el-form-item label="文件类型" prop="fileSuffix">
          <el-input v-model="form.fileSuffix" placeholder="文件类型" :readonly="true"/>
        </el-form-item>
        <el-form-item label="上传者" prop="uploadName">
          <el-input v-model="form.uploadName" placeholder="上传者" :readonly="true"/>
        </el-form-item>
        <el-form-item label="上传时间" prop="uploadTime">
          <el-date-picker clearable size="small"
            v-model="form.uploadTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择上传时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 文件上传功能 -->
    <el-dialog :title="title" :visible.sync="uploadvisible" width="500px" append-to-body>
      <uploader :key="uploader_key" :options="options" class="uploader-example"
                :autoStart="false"
                @file-success="onFileSuccess"
                @file-added="filesAdded">
        <uploader-unsupport></uploader-unsupport>
        <uploader-drop>
          <uploader-btn :single="true" :attrs="attrs">选择上传文件</uploader-btn>
        </uploader-drop>
        <uploader-list></uploader-list>
      </uploader>
    </el-dialog>
  </div>
</template>

<script>
import SparkMD5 from 'spark-md5'
import { listFiles, getFiles, delFiles, addFiles, updateFiles, exportFiles } from "@/api/fms/files";
import { S4 } from '@/utils/guidtool'
import { baseApiUrl } from '@/config/sysinfo'

export default {
  name: "Files",
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
      total: 0,
      // 文件管理表格数据
      filesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 文件序号字典
      fileIdOptions: [],
      // 文件标题字典
      fileTitleOptions: [],
      // 文件描述字典
      fileDescribeOptions: [],
      // 文件名称字典
      fileNameOptions: [],
      // 文件路径字典
      filePathOptions: [],
      // 文件链接字典
      fileUrlOptions: [],
      // 文件类型字典
      fileSuffixOptions: [],
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
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fileTitle: null,
      },
      pathguid: null,
      // 是否打开文件上传
      uploadvisible: false,
      // 文件上传参数
      uploader_key: new Date().getTime(),
      options: {
        target: 'null',
        testChunks: false
      },
      attrs: {
        accept: '.*'
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        fileTitle: [
          { required: true, message: "文件标题不能为空", trigger: "blur" }
        ],
        fileName: [
          { required: true, message: "文件名称不能为空", trigger: "blur" }
        ],
        filePath: [
          { required: true, message: "文件路径不能为空", trigger: "blur" }
        ],
        fileUrl: [
          { required: true, message: "文件链接不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.createPath();
    this.getList();
    this.getDicts("${column.dictType}").then(response => {
      this.fileIdOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.fileTitleOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.fileDescribeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.fileNameOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.filePathOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.fileUrlOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.fileSuffixOptions = response.data;
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
  methods: {
    /** 查询文件管理列表 */
    getList() {
      this.loading = true;
      listFiles(this.queryParams).then(response => {
        this.filesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    showStatus (username) {
      if (this.$store.getters.name === username) {
        return true
      } else {
        return false
      }
    },    
    // 文件序号字典翻译
    fileIdFormat(row, column) {
      return this.selectDictLabel(this.fileIdOptions, row.fileId);
    },
    // 文件标题字典翻译
    fileTitleFormat(row, column) {
      return this.selectDictLabel(this.fileTitleOptions, row.fileTitle);
    },
    // 文件描述字典翻译
    fileDescribeFormat(row, column) {
      return this.selectDictLabel(this.fileDescribeOptions, row.fileDescribe);
    },
    // 文件名称字典翻译
    fileNameFormat(row, column) {
      return this.selectDictLabel(this.fileNameOptions, row.fileName);
    },
    // 文件路径字典翻译
    filePathFormat(row, column) {
      return this.selectDictLabel(this.filePathOptions, row.filePath);
    },
    // 文件链接字典翻译
    fileUrlFormat(row, column) {
      return this.selectDictLabel(this.fileUrlOptions, row.fileUrl);
    },
    // 文件类型字典翻译
    fileSuffixFormat(row, column) {
      return this.selectDictLabel(this.fileSuffixOptions, row.fileSuffix);
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
        fileId: null,
        fileTitle: null,
        fileDescribe: null,
        fileName: null,
        filePath: null,
        fileUrl: null,
        fileSuffix: null,
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.fileId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.uploadvisible = true
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fileId = row.fileId || this.ids
      getFiles(fileId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文件管理";
      });
    },
    /** 文件上传 */
    onFileSuccess: function (rootFile, file, response, chunk) {
      this.getList();
      this.createPath()
    },
    createPath () {
      this.pathguid = S4() + S4() + S4()
      this.options.target = baseApiUrl + '/fms/files/chunkupload/' + this.pathguid + '/'  + this.$store.getters.name
    },
    computeMD5 (file) {
      const loading = this.$loading({
        lock: true,
        text: '正在计算MD5',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      const fileReader = new FileReader()
      const time = new Date().getTime()
      const blobSlice = File.prototype.slice || File.prototype.mozSlice || File.prototype.webkitSlice
      let currentChunk = 0
      const chunkSize = 10 * 1024 * 1000
      const chunks = Math.ceil(file.size / chunkSize)
      const spark = new SparkMD5.ArrayBuffer()
      file.pause()

      loadNext()

      fileReader.onload = e => {
        spark.append(e.target.result)
        if (currentChunk < chunks) {
          currentChunk++
          loadNext()
          this.$nextTick(() => {
          })
        } else {
          const md5 = spark.end()
          loading.close()
          this.computeMD5Success(md5, file)
        }
      }
      fileReader.onerror = function () {
        this.error(`文件${file.name}读取出错，请检查该文件`)
        loading.close()
        file.cancel()
      }

      function loadNext () {
        const start = currentChunk * chunkSize
        const end = ((start + chunkSize) >= file.size) ? file.size : start + chunkSize
        fileReader.readAsArrayBuffer(blobSlice.call(file.file, start, end))
      }
    },
    computeMD5Success (md5, file) {
      file.uniqueIdentifier = md5// 把md5值作为文件的识别码
      file.resume()// 开始上传
    },
    filesAdded (file, event) {
      var filesize = file.size / 1024 / 1024
      // 大小判断

      if (filesize > 80) {
        this.$message.error('error.error_upload_file_max')
      } else {
        this.computeMD5(file)
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fileId != null) {
            updateFiles(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFiles(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const fileIds = row.fileId || this.ids;
      this.$confirm('是否确认删除文件管理编号为"' + fileIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delFiles(fileIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    handleDownload(row) {
      let fileurl = baseApiUrl + row.fileUrl + '/' + row.fileName
      window.location.href = fileurl
    },
    handleFileUrl(row) {
      let fileurl = baseApiUrl + row.fileUrl + '/' + row.fileName
      this.$alert(fileurl, '下载链接显示', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `取链接完成`
          });
        }
      });
    },    
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有文件管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportFiles(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
