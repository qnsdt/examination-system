<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="选项描述" prop="optionDescribe">
        <el-input
          v-model="queryParams.optionDescribe"
          placeholder="请输入选项描述"
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
          v-hasPermi="['questions:answer:add']"
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
          v-hasPermi="['questions:answer:edit']"
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
          v-hasPermi="['questions:answer:remove']"
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
          v-hasPermi="['questions:answer:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="answerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="试题代码" align="center" prop="questionsCode" />
      <el-table-column label="试题选项" align="center" prop="optionCode" />
      <el-table-column label="选项描述" align="center" prop="optionDescribe" :formatter="optionDescribeFormat" />
      <el-table-column label="是否正确" align="center" prop="isRight" :formatter="isRightFormat" />
      <el-table-column label="排序编号" align="center" prop="orderId" :formatter="orderIdFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['questions:answer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['questions:answer:remove']"
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

    <!-- 添加或修改考试题目答案对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="选项描述" prop="optionDescribe">
          <el-input v-model="form.optionDescribe" placeholder="请输入选项描述" />
        </el-form-item>
        <el-form-item label="是否正确" prop="isRight">
          <el-input v-model="form.isRight" placeholder="请输入是否正确" />
        </el-form-item>
        <el-form-item label="排序编号" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入排序编号" />
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
import { listAnswer, getAnswer, delAnswer, addAnswer, updateAnswer, exportAnswer } from "@/api/questions/answer";

export default {
  name: "Answer",
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
      // 考试题目答案表格数据
      answerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 试题代码字典
      questionsCodeOptions: [],
      // 试题选项字典
      optionCodeOptions: [],
      // 选项描述字典
      optionDescribeOptions: [],
      // 是否正确字典
      isRightOptions: [],
      // 排序编号字典
      orderIdOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        optionDescribe: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        questionsCode: [
          { required: true, message: "试题代码不能为空", trigger: "blur" }
        ],
        optionCode: [
          { required: true, message: "试题选项不能为空", trigger: "blur" }
        ],
        optionDescribe: [
          { required: true, message: "选项描述不能为空", trigger: "blur" }
        ],
        isRight: [
          { required: true, message: "是否正确不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("${column.dictType}").then(response => {
      this.questionsCodeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.optionCodeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.optionDescribeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.isRightOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.orderIdOptions = response.data;
    });
  },
  methods: {
    /** 查询考试题目答案列表 */
    getList() {
      this.loading = true;
      listAnswer(this.queryParams).then(response => {
        this.answerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 试题代码字典翻译
    questionsCodeFormat(row, column) {
      return this.selectDictLabel(this.questionsCodeOptions, row.questionsCode);
    },
    // 试题选项字典翻译
    optionCodeFormat(row, column) {
      return this.selectDictLabel(this.optionCodeOptions, row.optionCode);
    },
    // 选项描述字典翻译
    optionDescribeFormat(row, column) {
      return this.selectDictLabel(this.optionDescribeOptions, row.optionDescribe);
    },
    // 是否正确字典翻译
    isRightFormat(row, column) {
      return this.selectDictLabel(this.isRightOptions, row.isRight);
    },
    // 排序编号字典翻译
    orderIdFormat(row, column) {
      return this.selectDictLabel(this.orderIdOptions, row.orderId);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        questionsCode: null,
        optionCode: null,
        optionDescribe: null,
        isRight: null,
        orderId: null
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
      this.title = "添加考试题目答案";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const questionsCode = row.questionsCode || this.ids
      getAnswer(questionsCode).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改考试题目答案";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.questionsCode != null) {
            updateAnswer(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAnswer(this.form).then(response => {
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
      this.$confirm('是否确认删除考试题目答案编号为"' + questionsCodes + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAnswer(questionsCodes);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有考试题目答案数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportAnswer(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
