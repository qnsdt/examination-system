<template>
  <div class="app-container">
    <div v-if="editVisible">
    <el-row :gutter="20">
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="typeName"
            placeholder="请输入类型名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="examtypeList"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <!--用户数据-->
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="题库名称" prop="bankName">
            <el-input
              v-model="queryParams.bankName"
              placeholder="请输入题库名称"
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
        <div class="banklist">
          <div >
            <ul style="padding: 0">
              <li style = "list-style-type:none;"
                v-for="(item,index) in questionsbankList"
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
                        <div class="name-text">{{item.bankName}}</div>
                        <div class="type-text">{{item.typeName}}</div>
                        <div class = "nickname-text">{{item.createBy}}</div>
                      </div>
                      <div class = "data-area">
                        <div class= "memo-text" >判断题:{{item.judgeNumber}}题 单选题:{{item.radioNumber}}题 多选题:{{item.choiceNumber}}题</div>
                        <div class = "button-area">
                          <el-button
                            size="mini"
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(item)"
                            v-hasPermi="['questions:questionsbank:edit']"
                          >编辑</el-button>
                        </div> 
                      </div>
                    </div>
                  </div>
                </el-col>
                <el-col :span="12" v-if="index + 1 < questionsbankList.length" >
                  <div  class="bank-panel">
                    <div class = "photo-area">
                      <img class ="title-photo" :src="hosturl + questionsbankList[index + 1].pictureUrl"/>
                    </div>
                    <div class = "describe-area">
                      <div class = "title-area">
                        <div class="name-text">{{questionsbankList[index + 1].bankName}}</div>
                        <div class="type-text">{{questionsbankList[index + 1].typeName}}</div>
                        <div class = "nickname-text">{{questionsbankList[index + 1].createBy}}</div>
                      </div>
                      <div class = "data-area">
                        <div class= "memo-text" >判断题:{{questionsbankList[index + 1].judgeNumber}}题 单选题:{{questionsbankList[index + 1].radioNumber}}题 多选题:{{questionsbankList[index + 1].choiceNumber}}题</div>
                        <div class = "button-area">
                          <el-button
                            size="mini"
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(questionsbankList[index + 1])"
                            v-hasPermi="['questions:questionsbank:edit']"
                          >编辑</el-button>
                        </div>
                      </div>
                    </div>
                  </div>
                </el-col>
              </el-row>              
              </li>
            </ul>
          </div>
        </div>        
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>
    </el-row>
    </div>
    <div v-else >
      <questions-list  ref="listRef" @refreshDataList="listDoneHandle"></questions-list>
    </div>


  </div>
</template>

<script>
import { listQuestionsbank, getQuestionsbank, delQuestionsbank, addQuestionsbank, updateQuestionsbank, exportQuestionsbank } from "@/api/questions/questionsbank";
import { listExamtype, getExamtype, typeTreeSelect, delExamtype, addExamtype, updateExamtype, exportExamtype } from "@/api/questions/examtype";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import QuestionsList from './questionsList'
import { baseApiUrl } from '@/config/sysinfo'

export default {
  name: "Questionsbank",
  components: {
    Treeselect,
    QuestionsList
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
      // 题库管理表格数据
      questionsbankList: [],
      // 弹出层标题
      title: "",
      editVisible: true,
      // 是否显示弹出层
      open: false,
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 题库id字典
      bankIdOptions: [],
      // 题库代码字典
      bankCodeOptions: [],
      // 题库名称字典
      bankNameOptions: [],
      // 题库描述字典
      bankDescribeOptions: [],
      // 题库版本字典
      bankVersionOptions: [],
      // 考试类型字典
      examTypeOptions: [],
      // 上线日期字典
      onlineDateOptions: [],
      // 图片链接字典
      pictureUrlOptions: [],
      // 单选分数字典
      radioScoreOptions: [],
      // 多选分数字典
      choiceScoreOptions: [],
      // 判断分数字典
      judgeScoreOptions: [],
      // 状态字典
      statusOptions: [],
      // 考试分类表格数据
      examtypeList: [],
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
        bankName: null,
        examType: null,
      },
      // 表单参数
      form: {},
      typeName: null,
      hosturl: ''
    };
  },
  created() {
    this.hosturl = baseApiUrl
    this.getList();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  watch: {
    // 根据名称筛选部门树
    typeName(val) {
      this.$refs.tree.filter(val);
    }
  },
  methods: {
    /** 查询题库管理列表 */
    getList() {
      this.loading = true;
      listQuestionsbank(this.queryParams).then(response => {
        this.questionsbankList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.getTypeTreeselect()
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.examType = data.id;
      this.getList();
    },    
    /** 查询部门下拉树结构 */
    getTypeTreeselect() {
      typeTreeSelect().then(response => {
        this.examtypeList = response.data;
      });
    },
    /** 转换考试分类数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.typeId,
        label: node.typeName,
        children: node.children
      };
    },
	  /** 查询考试分类下拉树结构 */
    getTreeselect() {
      listExamtype().then(response => {
        this.examtypeOptions = [];
        const data = { typeId: 0, typeName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "typeId", "parentId");
        this.examtypeOptions.push(data);
      });
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
        bankId: null,
        bankCode: null,
        bankName: null,
        bankDescribe: null,
        bankVersion: null,
        examType: null,
        onlineDate: null,
        pictureUrl: null,
        radioScore: null,
        choiceScore: null,
        judgeScore: null,
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
      this.ids = selection.map(item => item.bankCode)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    handleEdit (row) {
      this.editVisible = false
      this.$nextTick(() => {
       this.$refs.listRef.init_data(row)
      })
    },
    listDoneHandle () {
      this.editVisible = true
      this.getList();
    },
  }
};
</script>

<style scoped lang="scss">

  .el-form-item {
      margin-bottom: -1px;
  }

  .bank-panel {
    margin: 0px 0px 12px 0px;
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
    font-size: 13 px;
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
    -webkit-box-flex: 6;
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
    /* align-items: right; */
  }
</style>
