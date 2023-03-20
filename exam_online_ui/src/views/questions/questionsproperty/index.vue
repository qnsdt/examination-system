<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="试题题目" prop="questionsTitle">
        <el-input
          v-model="queryParams.questionsTitle"
          placeholder="请输入试题题目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="试题类型" prop="questionsType">
        <el-select v-model="queryParams.questionsType" placeholder="请选择试题类型" clearable size="small">
          <el-option
            v-for="dict in questionsTypeOptions"
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
          v-hasPermi="['questions:questionsproperty:add']"
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
          v-hasPermi="['questions:questionsproperty:edit']"
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
          v-hasPermi="['questions:questionsproperty:remove']"
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
          v-hasPermi="['questions:questionsproperty:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="questionspropertyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="题库代码" align="center" prop="bankCode" :formatter="bankCodeFormat" />
      <el-table-column label="试题id" align="center" prop="questionsId" :formatter="questionsIdFormat" />
      <el-table-column label="试题代码" align="center" prop="questionsCode" />
      <el-table-column label="试题题目" align="center" prop="questionsTitle" :formatter="questionsTitleFormat" />
      <el-table-column label="试题类型" align="center" prop="questionsType" :formatter="questionsTypeFormat" />
      <el-table-column label="题目分数" align="center" prop="questionsScore" :formatter="questionsScoreFormat" />
      <el-table-column label="复杂度" align="center" prop="rateNumber" :formatter="rateNumberFormat" />
      <el-table-column label="正确答案" align="center" prop="rightAnswer" :formatter="rightAnswerFormat" />
      <el-table-column label="答案分析" align="center" prop="answerAnalyse" :formatter="answerAnalyseFormat" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['questions:questionsproperty:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['questions:questionsproperty:remove']"
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

    <!-- 添加或修改题目属性对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="题库代码" prop="bankCode">
          <el-input v-model="form.bankCode" placeholder="请输入题库代码" />
        </el-form-item>
        <el-form-item label="试题id" prop="questionsId">
          <el-input v-model="form.questionsId" placeholder="请输入试题id" />
        </el-form-item>
        <el-form-item label="试题题目" prop="questionsTitle">
          <el-input v-model="form.questionsTitle" placeholder="请输入试题题目" />
        </el-form-item>
        <el-form-item label="试题类型" prop="questionsType">
          <el-select v-model="form.questionsType" placeholder="请选择试题类型">
            <el-option
              v-for="dict in questionsTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="题目分数" prop="questionsScore">
          <el-input v-model="form.questionsScore" placeholder="请输入题目分数" />
        </el-form-item>
        <el-form-item label="复杂度" prop="rateNumber">
          <el-input v-model="form.rateNumber" placeholder="请输入复杂度" />
        </el-form-item>
        <el-form-item label="正确答案" prop="rightAnswer">
          <el-input v-model="form.rightAnswer" placeholder="请输入正确答案" />
        </el-form-item>
        <el-form-item label="答案分析" prop="answerAnalyse">
          <el-input v-model="form.answerAnalyse" placeholder="请输入答案分析" />
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
        <el-form-item label="创建部门" prop="createDept">
          <el-input v-model="form.createDept" placeholder="请输入创建部门" />
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
import { listQuestionsproperty, getQuestionsproperty, delQuestionsproperty, addQuestionsproperty, updateQuestionsproperty, exportQuestionsproperty } from "@/api/questions/questionsproperty";

export default {
  name: "Questionsproperty",
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
      // 题目属性表格数据
      questionspropertyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 题库代码字典
      bankCodeOptions: [],
      // 试题id字典
      questionsIdOptions: [],
      // 试题代码字典
      questionsCodeOptions: [],
      // 试题题目字典
      questionsTitleOptions: [],
      // 试题类型字典
      questionsTypeOptions: [],
      // 题目分数字典
      questionsScoreOptions: [],
      // 复杂度字典
      rateNumberOptions: [],
      // 正确答案字典
      rightAnswerOptions: [],
      // 答案分析字典
      answerAnalyseOptions: [],
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
        questionsTitle: null,
        questionsType: null,
      },
      // 表单参数
      form: {},
      // 表单校验
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
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("${column.dictType}").then(response => {
      this.bankCodeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.questionsIdOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.questionsCodeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.questionsTitleOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.questionsTypeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.questionsScoreOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.rateNumberOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.rightAnswerOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.answerAnalyseOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.createByOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.createDeptOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.createTimeOptions = response.data;
    });
  },
  methods: {
    /** 查询题目属性列表 */
    getList() {
      this.loading = true;
      listQuestionsproperty(this.queryParams).then(response => {
        this.questionspropertyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 题库代码字典翻译
    bankCodeFormat(row, column) {
      return this.selectDictLabel(this.bankCodeOptions, row.bankCode);
    },
    // 试题id字典翻译
    questionsIdFormat(row, column) {
      return this.selectDictLabel(this.questionsIdOptions, row.questionsId);
    },
    // 试题代码字典翻译
    questionsCodeFormat(row, column) {
      return this.selectDictLabel(this.questionsCodeOptions, row.questionsCode);
    },
    // 试题题目字典翻译
    questionsTitleFormat(row, column) {
      return this.selectDictLabel(this.questionsTitleOptions, row.questionsTitle);
    },
    // 试题类型字典翻译
    questionsTypeFormat(row, column) {
      return this.selectDictLabel(this.questionsTypeOptions, row.questionsType);
    },
    // 题目分数字典翻译
    questionsScoreFormat(row, column) {
      return this.selectDictLabel(this.questionsScoreOptions, row.questionsScore);
    },
    // 复杂度字典翻译
    rateNumberFormat(row, column) {
      return this.selectDictLabel(this.rateNumberOptions, row.rateNumber);
    },
    // 正确答案字典翻译
    rightAnswerFormat(row, column) {
      return this.selectDictLabel(this.rightAnswerOptions, row.rightAnswer);
    },
    // 答案分析字典翻译
    answerAnalyseFormat(row, column) {
      return this.selectDictLabel(this.answerAnalyseOptions, row.answerAnalyse);
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
        bankCode: null,
        questionsId: null,
        questionsCode: null,
        questionsTitle: null,
        questionsType: null,
        questionsScore: null,
        rateNumber: null,
        rightAnswer: null,
        answerAnalyse: null,
        status: 0,
        createBy: null,
        createDept: null,
        createTime: null
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
      this.ids = selection.map(item => item.questionsCode)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加题目属性";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const questionsCode = row.questionsCode || this.ids
      getQuestionsproperty(questionsCode).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改题目属性";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.questionsCode != null) {
            updateQuestionsproperty(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuestionsproperty(this.form).then(response => {
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
      const questionsCodes = row.questionsCode || this.ids;
      this.$confirm('是否确认删除题目属性编号为"' + questionsCodes + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delQuestionsproperty(questionsCodes);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有题目属性数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportQuestionsproperty(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
