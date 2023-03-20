<template>
<div>
  <div   v-if="editVisible">
    <div style ="margin-left: 15px">
      <el-button 
        @click="onClose"
        plain
        icon="el-icon-s-home"
        size="mini"
      >返回</el-button>
    </div>

    <div class="form-panel">
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
            v-hasPermi="['questions:examquestions:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['questions:examquestions:remove']"
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
            v-hasPermi="['questions:examquestions:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="examquestionsList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="序号" width = "60" align="center" prop="rownum"  />
        <el-table-column label="试题题目"  align="center"  prop="questionsTitle"  />
        <el-table-column label="试题类型" width = "80" align="center" prop="questionsType" :formatter="questionsTypeFormat" />
        <el-table-column label="分数" width = "60" align="center" prop="questionsScore" />
        <el-table-column label="复杂度" width = "150" align="center" >
          <template slot-scope="scope">
            <el-rate
              v-model="scope.row.rateNumber"
              disabled
              text-color="#ff9900">
            </el-rate>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width = "100" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['questions:examquestions:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['questions:examquestions:remove']"
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
    </div>
  </div>
  <div  v-else >
    <exam-questions  ref="editRef" @refreshData="editDoneHandle"></exam-questions>
  </div>
</div>

</template>

<script>
import { listQuestionsproperty, getQuestionsproperty, delQuestionsproperty, addQuestionsproperty, updateQuestionsproperty, exportQuestionsproperty } from "@/api/questions/questionsproperty";
import { listExamquestions, getExamquestions, delExamquestions, addExamquestions, updateExamquestions, exportExamquestions } from "@/api/questions/examquestions";
import ExamQuestions from './examQuestions'

export default {
  name: "Questionslist",
  components: {
    ExamQuestions
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
      bankData: null,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      editVisible: true,
      // 考试题目表格数据
      examquestionsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 试题id字典
      questionsIdOptions: [],
      // 试题代码字典
      questionsCodeOptions: [],
      // 试题题目字典
      questionsTitleOptions: [],
      // 试题内容字典
      questionsContentOptions: [],
      // 试题类型字典
      questionsTypeOptions: [
        {dictValue: 1, dictLabel: '判断题'},
        {dictValue: 2, dictLabel: '单选题'},
        {dictValue: 3, dictLabel: '多选题'}
        ],
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
        bankCode: null,
        questionsTitle: null,
        questionsType: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        questionsCode: [
          { required: true, message: "试题代码不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询考试题目列表 */
    init_data (row) {
      this.bankData = row
      this.queryParams.bankCode = this.bankData.bankCode
      this.getList()
    },    
    getList() {
      this.loading = true;
      listQuestionsproperty(this.queryParams).then(response => {
        this.examquestionsList = response.rows;
        // console.log('this.examquestionsList:' + JSON.stringify(this.examquestionsList))
        this.total = response.total;
        this.loading = false;
      });      
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
    // 试题内容字典翻译
    questionsContentFormat(row, column) {
      return this.selectDictLabel(this.questionsContentOptions, row.questionsContent);
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
        questionsId: null,
        questionsCode: null,
        questionsTitle: null,
        questionsContent: null,
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
    editDoneHandle () {
      this.editVisible = true
      this.getList();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.editVisible = false
      this.$nextTick(() => {
       this.$refs.editRef.init_data(this.bankData, null)
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.editVisible = false
      this.$nextTick(() => {
       this.$refs.editRef.init_data(this.bankData, row)
      })      
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.questionsCode != null) {
            updateExamquestions(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExamquestions(this.form).then(response => {
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
      this.$confirm('是否确认删除考试题目编号为"' + questionsCodes + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delExamquestions(questionsCodes);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有考试题目数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportExamquestions(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },
    onClose () {
      this.$emit('refreshDataList')            
    }    
  }
};
</script>


<style scoped lang="scss">
.form-panel {
    margin:  10px 0px 20px 15px;
}
</style>

