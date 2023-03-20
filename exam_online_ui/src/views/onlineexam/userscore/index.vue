<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="考试代码" prop="examCode">
        <el-input
          v-model="queryParams.examCode"
          placeholder="请输入考试代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考试人员" prop="userCode">
        <el-input
          v-model="queryParams.userCode"
          placeholder="请输入考试人员"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="题目分数" prop="questionsScore">
        <el-input
          v-model="queryParams.questionsScore"
          placeholder="请输入题目分数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
          v-hasPermi="['onlineexam:userscore:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['onlineexam:userscore:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['onlineexam:userscore:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['onlineexam:userscore:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userscoreList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="考试代码" align="center" prop="examCode" />
      <el-table-column label="考试人员" align="center" prop="userCode" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分数" align="center" prop="examScore" :formatter="examScoreFormat" />
      <el-table-column label="题目分数" align="center" prop="questionsScore" :formatter="questionsScoreFormat" />
      <el-table-column label="题目数" align="center" prop="examNumber" :formatter="examNumberFormat" />
      <el-table-column label="答题数" align="center" prop="answeredNumber" :formatter="answeredNumberFormat" />
      <el-table-column label="未答题数" align="center" prop="notAnswered" :formatter="notAnsweredFormat" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['onlineexam:userscore:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['onlineexam:userscore:remove']"
          >删除</el-button>
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

    <!-- 添加或修改考试成绩对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable size="small"
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable size="small"
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="分数" prop="examScore">
          <el-input v-model="form.examScore" placeholder="请输入分数" />
        </el-form-item>
        <el-form-item label="题目分数" prop="questionsScore">
          <el-input v-model="form.questionsScore" placeholder="请输入题目分数" />
        </el-form-item>
        <el-form-item label="题目数" prop="examNumber">
          <el-input v-model="form.examNumber" placeholder="请输入题目数" />
        </el-form-item>
        <el-form-item label="答题数" prop="answeredNumber">
          <el-input v-model="form.answeredNumber" placeholder="请输入答题数" />
        </el-form-item>
        <el-form-item label="未答题数" prop="notAnswered">
          <el-input v-model="form.notAnswered" placeholder="请输入未答题数" />
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
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUserscore, getUserscore, delUserscore, addUserscore, updateUserscore, exportUserscore } from "@/api/onlineexam/userscore";

export default {
  name: "Userscore",
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
      // 考试成绩表格数据
      userscoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 考试代码字典
      examCodeOptions: [],
      // 考试人员字典
      userCodeOptions: [],
      // 开始时间字典
      startTimeOptions: [],
      // 结束时间字典
      endTimeOptions: [],
      // 分数字典
      examScoreOptions: [],
      // 题目分数字典
      questionsScoreOptions: [],
      // 题目数字典
      examNumberOptions: [],
      // 答题数字典
      answeredNumberOptions: [],
      // 未答题数字典
      notAnsweredOptions: [],
      // 状态字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        examCode: null,
        userCode: null,
        questionsScore: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        examCode: [
          { required: true, message: "考试代码不能为空", trigger: "blur" }
        ],
        userCode: [
          { required: true, message: "考试人员不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("${column.dictType}").then(response => {
      this.examCodeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.userCodeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.startTimeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.endTimeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.examScoreOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.questionsScoreOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.examNumberOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.answeredNumberOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.notAnsweredOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询考试成绩列表 */
    getList() {
      this.loading = true;
      listUserscore(this.queryParams).then(response => {
        this.userscoreList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 考试代码字典翻译
    examCodeFormat(row, column) {
      return this.selectDictLabel(this.examCodeOptions, row.examCode);
    },
    // 考试人员字典翻译
    userCodeFormat(row, column) {
      return this.selectDictLabel(this.userCodeOptions, row.userCode);
    },
    // 开始时间字典翻译
    startTimeFormat(row, column) {
      return this.selectDictLabel(this.startTimeOptions, row.startTime);
    },
    // 结束时间字典翻译
    endTimeFormat(row, column) {
      return this.selectDictLabel(this.endTimeOptions, row.endTime);
    },
    // 分数字典翻译
    examScoreFormat(row, column) {
      return this.selectDictLabel(this.examScoreOptions, row.examScore);
    },
    // 题目分数字典翻译
    questionsScoreFormat(row, column) {
      return this.selectDictLabel(this.questionsScoreOptions, row.questionsScore);
    },
    // 题目数字典翻译
    examNumberFormat(row, column) {
      return this.selectDictLabel(this.examNumberOptions, row.examNumber);
    },
    // 答题数字典翻译
    answeredNumberFormat(row, column) {
      return this.selectDictLabel(this.answeredNumberOptions, row.answeredNumber);
    },
    // 未答题数字典翻译
    notAnsweredFormat(row, column) {
      return this.selectDictLabel(this.notAnsweredOptions, row.notAnswered);
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
        examCode: null,
        userCode: null,
        startTime: null,
        endTime: null,
        examScore: null,
        questionsScore: null,
        examNumber: null,
        answeredNumber: null,
        notAnswered: null,
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
      this.ids = selection.map(item => item.examCode)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加考试成绩";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const examCode = row.examCode || this.ids
      getUserscore(examCode).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改考试成绩";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.examCode != null) {
            updateUserscore(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUserscore(this.form).then(response => {
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
      this.$confirm('是否确认删除考试成绩编号为"' + examCodes + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delUserscore(examCodes);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有考试成绩数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportUserscore(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
