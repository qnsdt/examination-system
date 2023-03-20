<template>
<div>
<div  v-if="isUpdate">
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
  </div>
  <div class="form-panel">
      <el-table v-loading="loading" :data="examquestionsList" @selection-change="handleSelectionChange" ref="multipleTable">
        <el-table-column type="selection"  width="50" align="center" />
        <el-table-column label="序号" width = "50" align="center" prop="rownum"  />
        <el-table-column label="试题题目"  align="left"  prop="questionsTitle"  />
        <el-table-column label="题库名称" width = "140" align="center" prop="bankName"  />
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
      </el-table>
      <div >
        <el-pagination
          v-show="total>0"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="queryParams.pageSize"
          :current-page="queryParams.pageNum"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
          >
        </el-pagination>
      </div>      
  </div>
</div>

</template>

<script>
import { listQuestions, chiceListQuestions } from "@/api/questions/questionslist";
import { batchUpdateTaskquestions } from "@/api/exam/taskquestions";
export default {
  name: "selectQuestions",
  data() {
    return {
      // 遮罩层
      loading: false,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      examData: null,
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
        questionsTitle: null,
        questionsType: null,
        bankCodes: []
      },
      choiceQuestions: [],
      // 表单参数
      form: {},
      saveList: [],
      bankData: null,
      bankCodes: [],
      // 表单校验
      rules: {
        questionsCode: [
          { required: true, message: "试题代码不能为空", trigger: "blur" }
        ],
      },
      isUpdate: false
    };
  },
  created() {
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询考试题目列表 */
    init_data (contentdata, selectdata) {
      this.bankData = contentdata
      this.saveList = selectdata
      this.bankCodes = []
      this.bankCodes = this.bankData.examBank

      this.queryParams.bankCodes = this.bankCodes
      this.getList()
    },    
    update_data (row) {
      this.isUpdate = true
      this.examData = row
      console.log('row:' + JSON.stringify(row))
      this.bankCodes = []
      this.bankCodes = row.examBank

      this.queryParams.bankCodes = this.bankCodes
      this.getList()
      this.getselectList()
    },
    getList() {
      this.loading = true;
      listQuestions(this.queryParams).then(response => {
        this.examquestionsList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.setChoice()
      });      
    },
    getselectList () {
      chiceListQuestions(this.examData.examCode).then(response => {
        this.saveList = response.data
      });      
    },
    // 试题类型字典翻译
    questionsTypeFormat(row, column) {
      return this.selectDictLabel(this.questionsTypeOptions, row.questionsType);
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
      this.ids = selection.map(item => item.questionsId)
      this.choiceQuestions = selection
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.getSelect()
      this.$nextTick(() => {
        this.getList();
      })
    },
    handleSizeChange(val) {
      this.queryParams.pageSize = val
      this.getSelect()
      this.$nextTick(() => {
        this.getList();
      })    
    },
    getSelect() {
      if (this.examquestionsList === null || this.examquestionsList.length == 0) {
         return
      }
      // 第一遍循环置状态
      let is_find = false
      for (let i = 0; i < this.examquestionsList.length; i ++) {
        is_find = false
        if (this.choiceQuestions !== null || this.choiceQuestions.length > 0) {
          for (let j = 0; j < this.choiceQuestions.length; j ++) {
            if (this.examquestionsList[i].questionsId === this.choiceQuestions[j].questionsId) {
              is_find = true
            }
          }
        }
        
        if (is_find) {
          this.examquestionsList[i].remark = 'T'
        } else {
          this.examquestionsList[i].remark = 'F'
        }
      }

      // 第二遍循环操作缓存列表数据
      if (this.saveList === null) {
        this.saveList = []
      }

      for (let i = 0; i < this.examquestionsList.length; i ++) {
        is_find = false
        let order_id = -1
        for (let j = 0; j < this.saveList.length; j ++) {
            if (this.examquestionsList[i].questionsId === this.saveList[j].questionsId) {
              is_find = true
              order_id = j
            }
        }

        if (this.examquestionsList[i].remark === 'T' && !is_find) {
          this.saveList.push(this.examquestionsList[i])
        } else if (this.examquestionsList[i].remark === 'F' && is_find) {
          this.saveList.splice(order_id,1)
        }
      }
    },
    onClose () {
      this.$emit('refreshQuestions')
    },    
    onSaveOrBack () {
      this.saveData()
      this.$emit('refreshQuestions')
    },     
    onSave () {
      this.saveData()
    },
    saveData () {
      this.getSelect()
      this.$nextTick(() => {
        if (this.saveList === null || this.saveList.length === 0) {
          this.$message.error('需要先选中题目')
          return
        }

        let questionsSaveList = []
        for (let i = 0; i < this.saveList.length; i ++) {
          questionsSaveList.push(
            {
              examCode: this.examData.examCode,
              questionsCode: this.saveList[i].questionsCode,
              questionsNumber: i + 1
            }
          )
        }
        batchUpdateTaskquestions(questionsSaveList).then(response => {
          this.$message.success('保存成功!')
        });

      })
    },        
    setChoice () {      
      if (this.saveList === null || this.saveList.length === 0) {
        return
      }

      if (this.examquestionsList === null || this.examquestionsList === 0) {
        return
      }
      for (let i = 0; i < this.examquestionsList.length; i ++ ) {
        for (let j = 0; j < this.saveList.length; j ++ ) {
          if (this.examquestionsList[i].questionsId === this.saveList[j].questionsId) {
            this.$nextTick(() => {
              this.$refs.multipleTable.toggleRowSelection(this.examquestionsList[i], true)
            })
            break
          }
        }
      }
    },           
    getData() {
      this.getSelect()
      return this.saveList      
    }       
  }
};
</script>


<style scoped lang="scss">
.button-panel {
  padding-bottom: 1.6rem;
  background-color: #fff; 
}

.form-panel {
    margin:  18px 0px 20px 15px;
}

.el-pagination {
  white-space: nowrap;
  padding: 12px 5px 2px 5px;
  color: #303133;
  font-weight: bold;
  text-align:  right;
}
</style>

