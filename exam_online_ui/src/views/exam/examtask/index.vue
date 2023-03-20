<template>
  <div class="app-container">
    <div v-if="showUi === 1">
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="考试名称" prop="examName">
          <el-input
            v-model="queryParams.examName"
            placeholder="请输入考试名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="组卷方式" prop="buildType">
          <el-select v-model="queryParams.buildType" placeholder="请选择组卷方式" clearable size="small">
            <el-option
              v-for="dict in buildTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="考试时间">
          <el-date-picker
            v-model="daterangeDate"
            size="small"
            style="width: 240px"
            value-format="yyyy-MM-dd"
            type="daterange"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
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
            v-hasPermi="['exam:examtask:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
        :loading="exportLoading"
            @click="handleExport"
            v-hasPermi="['exam:examtask:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
      <div >
        <ul style="padding: 0">
          <li style = "list-style-type:none;"
            v-for="(item,index) in examtaskList"
            :key="index"
            class="new border-1px"
          >
            <el-row :gutter="20" :key="index" v-if='index % 2 == 0'>
              <el-col :span="12" >
                <div  class="bank-panel">
                  <div class = "photo-area">
                    <img class ="title-photo" :src="hosturl + item.pictureUrl"/>
                  </div>
                  <div class = "describe-area">
                    <div class = "title-area">
                      <div class="name-text">{{item.examName}}</div>
                      <div class="type-text">{{showLabel(item.buildType)}}</div>
                      <div class = "nickname-text">{{ parseTime(item.startTime, '{y}-{m}-{d}') }}</div>
                    </div>
                    <div class = "data-area">
                      <div class= "memo-text" >{{item.examDescribe}}</div>
                      <div class = "button-area">
                        <el-button
                          size="mini"
                          type="text"
                          icon="el-icon-edit"
                          @click="handleUpdateContent(item)"
                          v-hasPermi="['exam:examtask:edit']"
                        >内容</el-button>
                        <el-button
                          size="mini"
                          type="text"
                          icon="el-icon-date"
                          @click="handleUpdateQuestions(item)"
                          v-hasPermi="['exam:examtask:edit']"
                        >组卷</el-button>                        
                        <el-button
                          size="mini"
                          type="text"
                          icon="el-icon-user"
                          @click="handleUpdateUsers(item)"
                          v-hasPermi="['exam:examtask:edit']"
                        >选人</el-button>                        
                        <el-button
                          size="mini"
                          type="text"
                          icon="el-icon-delete"
                          @click="handleDelete(item)"
                          v-hasPermi="['exam:examtask:remove']"
                        >删除</el-button>
                      </div>
                    </div>
                  </div>
                </div>
              </el-col>
              <el-col :span="12" v-if="index + 1 < examtaskList.length" >
                <div  class="bank-panel">
                  <div class = "photo-area">
                    <img class ="title-photo" :src="hosturl + examtaskList[index + 1].pictureUrl"/>
                  </div>
                  <div class = "describe-area">
                    <div class = "title-area">
                      <div class="name-text">{{examtaskList[index + 1].examName}}</div>
                      <div class="type-text">{{showLabel(examtaskList[index + 1].buildType)}}</div>
                      <div class = "nickname-text">{{parseTime(examtaskList[index + 1].startTime, '{y}-{m}-{d}')}}</div>
                    </div>
                    <div class = "data-area">
                      <div class= "memo-text" >{{examtaskList[index + 1].examDescribe}}</div>
                      <div class = "button-area">
                        <el-button
                          size="mini"
                          type="text"
                          icon="el-icon-edit"
                          @click="handleUpdateContent(examtaskList[index + 1])"
                          v-hasPermi="['exam:examtask:edit']"
                        >内容</el-button>
                        <el-button
                          size="mini"
                          type="text"
                          icon="el-icon-date"
                          @click="handleUpdateQuestions(examtaskList[index + 1])"
                          v-hasPermi="['exam:examtask:edit']"
                        >组卷</el-button>                        
                        <el-button
                          size="mini"
                          type="text"
                          icon="el-icon-user"
                          @click="handleUpdateUsers(examtaskList[index + 1])"
                          v-hasPermi="['exam:examtask:edit']"
                        >选人</el-button>                        
                        <el-button
                          size="mini"
                          type="text"
                          icon="el-icon-delete"
                          @click="handleDelete(examtaskList[index + 1])"
                          v-hasPermi="['exam:examtask:remove']"
                        >删除</el-button>
                      </div>
                    </div>
                  </div>
                </div>
              </el-col>
            </el-row>              
          </li>
        </ul>
      </div>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>
    <div v-else-if= "showUi === 2">
      <edit  ref="editRef" @refreshEdit="editDoneHandle"></edit>
    </div>
    <div v-else-if= "showUi === 3">
      <content-edit ref="contentRef" @refreshContent ="contentDoneHandle"></content-edit>
    </div>    
    <div v-else-if= "showUi === 4">
      <select-questions ref="questionsRef" @refreshQuestions ="questionsDoneHandle"></select-questions>
    </div>
    <div v-else-if= "showUi === 5">
      <choice-user ref="groupRef" @refreshGroup ="groupDoneHandle"></choice-user>
    </div>        
  </div>
</template>

<script>
import { baseApiUrl } from '@/config/sysinfo'
import { listExamtask, getExamtask, delExamtask, addExamtask, updateExamtask, exportExamtask } from "@/api/exam/examtask";
import Edit from './edit'
import examSelect from '@/components/ExamSelect/index'
import contentEdit from './contentEdit'
import selectQuestions from './selectQuestions'
import choiceUser from './choiceUser'

export default {
  name: "Examtask",
  components: {
    Edit,
    examSelect,
    contentEdit,
    selectQuestions,
    choiceUser
  },  
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
      // 创建考试表格数据
      examtaskList: [],
      // 弹出层标题
      // title: "",
      // 是否显示弹出层
      // open: false,
      // 考试序号字典
      examIdOptions: [],
      // 考试代码字典
      examCodeOptions: [],
      // 考试名称字典
      examNameOptions: [],
      // 考试说明字典
      examDescribeOptions: [],
      // 组卷方式字典
      buildTypeOptions: [],
      // 强制抽卷字典
      forceDoneOptions: [],
      // 考试题库字典
      examBankOptions: [],
      // 图片链接字典
      pictureUrlOptions: [],
      // 开始时间字典
      startTimeOptions: [],
      // 结束时间字典
      endTimeOptions: [],
      // 考试时长字典
      examDurationOptions: [],
      // 状态字典
      statusOptions: [],
      // 创建者字典
      createByOptions: [],
      // 创建部门字典
      createDeptOptions: [],
      // 创建时间字典
      createTimeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        examName: null,
        buildType: null,
        startTime: null,
        endTime: null
      },
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
      },
      hosturl: '',
      buildTypeOptions: [
        {dictValue: 1, dictLabel: "人工组卷"},
        {dictValue: 2, dictLabel: "随机抽题"},
        {dictValue: 3, dictLabel: "考时随机组卷"}
      ],
      // 查询日期期间
      daterangeDate: [],
      showUi: 1,
      choiceList: null
    };
  },
  created() {
    this.hosturl = baseApiUrl
    this.getList();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询创建考试列表 */
    getList() {
      this.loading = true;
      if (null != this.daterangeDate && '' != this.daterangeDate) {
        this.queryParams.startTime = this.daterangeDate[0];
        this.queryParams.endTime = this.daterangeDate[1];
      }      
      listExamtask(this.queryParams).then(response => {
        this.examtaskList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 考试序号字典翻译
    examIdFormat(row, column) {
      return this.selectDictLabel(this.examIdOptions, row.examId);
    },
    // 考试代码字典翻译
    examCodeFormat(row, column) {
      return this.selectDictLabel(this.examCodeOptions, row.examCode);
    },
    // 考试名称字典翻译
    examNameFormat(row, column) {
      return this.selectDictLabel(this.examNameOptions, row.examName);
    },
    // 考试说明字典翻译
    examDescribeFormat(row, column) {
      return this.selectDictLabel(this.examDescribeOptions, row.examDescribe);
    },
    // 组卷方式字典翻译
    buildTypeFormat(row, column) {
      return this.selectDictLabel(this.buildTypeOptions, row.buildType);
    },
    // 强制抽卷字典翻译
    forceDoneFormat(row, column) {
      return this.selectDictLabel(this.forceDoneOptions, row.forceDone);
    },
    // 考试题库字典翻译
    examBankFormat(row, column) {
      return this.selectDictLabel(this.examBankOptions, row.examBank);
    },
    // 图片链接字典翻译
    pictureUrlFormat(row, column) {
      return this.selectDictLabel(this.pictureUrlOptions, row.pictureUrl);
    },
    // 开始时间字典翻译
    startTimeFormat(row, column) {
      return this.selectDictLabel(this.startTimeOptions, row.startTime);
    },
    // 结束时间字典翻译
    endTimeFormat(row, column) {
      return this.selectDictLabel(this.endTimeOptions, row.endTime);
    },
    // 考试时长字典翻译
    examDurationFormat(row, column) {
      return this.selectDictLabel(this.examDurationOptions, row.examDuration);
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 创建者字典翻译
    createByFormat(row, column) {
      return this.selectDictLabel(this.createByOptions, row.createBy);
    },
    // 创建部门字典翻译
    createDeptFormat(row, column) {
      return this.selectDictLabel(this.createDeptOptions, row.createDept);
    },
    // 创建时间字典翻译
    createTimeFormat(row, column) {
      return this.selectDictLabel(this.createTimeOptions, row.createTime);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        examId: null,
        examCode: null,
        examName: null,
        examDescribe: null,
        buildType: null,
        forceDone: null,
        examBank: null,
        pictureUrl: null,
        startTime: null,
        endTime: null,
        examDuration: null,
        status: 0,
        createBy: null,
        createDept: null,
        createTime: null
      };
      this.resetForm("form");
    },
    showLabel(typeval) {     
      let val = '未识别'
      if (typeval === '1') {
        val = '人工组卷'
      } else if (typeval === '2') {
        val = '随机抽题'
      } else if (typeval === '3') {
        val = '考时随机组卷'
      }

      return val
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
      this.ids = selection.map(item => item.examCode)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.showUi = 2
      this.$nextTick(() => {
       this.$refs.editRef.init_data(null)
      })      
    },
    editDoneHandle () {
      this.showUi = 1
      this.getList()
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
    },
    handleUpdateContent(row) {
      this.showUi = 3
      this.$nextTick(() => {
        this.$refs.contentRef.update_data(row)
      })
    },
    handleUpdateQuestions(row) {
      this.showUi = 4
      this.$nextTick(() => {
        this.$refs.questionsRef.update_data(row)
      })
    },
    handleUpdateUsers(row) {
      this.showUi = 5
      this.$nextTick(() => {
        this.$refs.groupRef.update_data(row)
      })
    },
    contentDoneHandle () {
      this.getList()
      this.showUi = 1
    },
    questionsDoneHandle () {
      this.getList()
      this.showUi = 1
    },
    groupDoneHandle () {
      this.getList()
      this.showUi = 1
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.examCode != null) {
            updateExamtask(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExamtask(this.form).then(response => {
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
      const examCodes = row.examCode || this.ids;
      this.$confirm('是否确认删除创建考试编号为"' + examCodes + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delExamtask(examCodes);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有创建考试数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportExamtask(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>

<style scoped lang="scss">
  .bank-panel {
    margin: 12px 0px 12px 0px;
    padding: 8px 0px 1px 0px;
    display: -webkit-box;
    display: flex;
    -webkit-align-items: center;
    align-items: center;
    background-color: #f7f7f7;
  }

  .photo-area {
    -webkit-box-align: left;
    margin-left: 0 px;
  }

  .title-photo {
    margin-left: 0.6rem;
    width: 6.25rem;
    height: 5rem;
  }
  
  .describe-area {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-box-align: justify;
    -webkit-align-items: center;
    align-items: center;
    padding-left: 0.8rem;
  }

.title-area {
    display: -webkit-box;
    -webkit-box-orient: horizontal;
    -webkit-box-align: center;
    -webkit-align-items: center;
    align-items: left;
    margin: 2px 0px 10px 0px;
    padding: -5px 0px 0px 0px;
}

  .name-text {
    -webkit-box-flex: 5;
    -webkit-box-align: left;
	  font-size: 1.2 rem;
	  font-weight: bold;    
  }

  .type-text{
    -webkit-box-flex: 1;
    -webkit-box-align: center;
    font-size: 13px;
    align-items: center;
    text-align: center;
    margin: 0px 10px 0px 10px;
    color: #fff;  
    border: 1px solid #7acc9b;  
    background-color: #7acc9b; 
    border-radius: 6px;
  }

	.nickname-text{
    -webkit-box-flex: 1;
    -webkit-box-align: right;
    font-size: 12 px;
    color: #8c939d;
  }

  .data-area {
     display: -webkit-box;
    -webkit-box-orient: horizontal;
    -webkit-align-items: center;
    align-items: center;
    margin-top: 0.6rem;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;   
  }
  
  .memo-text {
    -webkit-box-flex: 5;
    -webkit-box-align: center;
    font-size: 12px;
    color: #8c939d;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;

  }

  .button-area {
    overflow: hidden;
    text-align: right;
    vertical-align: middle;
  }
</style>

