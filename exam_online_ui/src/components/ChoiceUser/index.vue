<template>
  <div >
    <div>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            plain
            icon="el-icon-s-home"
            size="mini"
            @click="handleBack"
          >返回</el-button>
        </el-col>
      </el-row>      
    </div>
    <el-row :gutter="30">
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="deptName"
            placeholder="请输入部门名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="deptOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <el-col :span="20" :xs="24">
        <el-row>
          <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" >
            <el-form-item label="用户名称" prop="nickName">
              <el-input
                v-model="queryParams.nickName"
                placeholder="请输入用户名称"
                clearable
                size="small"
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="手机号码" prop="phonenumber">
              <el-input
                v-model="queryParams.phonenumber"
                placeholder="请输入手机号码"
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
        </el-row>
        <el-row :gutter="30">
          <!--用户数据-->
          <el-col :span="12" :xs="24">
            <el-row :gutter="10" class="mb8">
              <el-col :span="1.5">
                <el-button
                  type="primary"
                  plain
                  icon="el-icon-plus"
                  size="mini"
                  @click="handleUsersIn"
                >加入</el-button>
              </el-col>
            </el-row>

            <el-table v-loading="loading" :data="userList"  @selection-change="handleSelectionChange"  ref="multipleTable">
              <el-table-column type="selection"  width="50" align="center" />
              <el-table-column label="用户账号"  width="80" align="center" key="userName" prop="userName" v-if="columns[1].visible" :show-overflow-tooltip="true" />
              <el-table-column label="名称"  width="80" align="center" key="nickName" prop="nickName" v-if="columns[2].visible" :show-overflow-tooltip="true" />
              <el-table-column label="部门" align="center" key="deptName" prop="deptName" v-if="columns[3].visible" :show-overflow-tooltip="true" />
              <el-table-column
                label="操作"
                align="center"
                class-name="small-padding fixed-width"
              >
                <template slot-scope="scope" v-if="scope.row.userId !== 1">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-plus"
                    @click="handleUserIn(scope.row)"
                  >加入</el-button>
                </template>
              </el-table-column>
            </el-table>

            <sim-pagination
              v-show="total>0"
              :total="total"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              @pagination="getList"
            />
          </el-col>
          <!--用户数据-->
          <el-col :span="12" :xs="24">
            <el-row :gutter="10" class="mb8">
              <el-col :span="1.5">
                <el-button
                  type="warning"
                  plain
                  icon="el-icon-minus"
                  size="mini"
                  @click="handleUsersCancel"
                >取消</el-button>
              </el-col>
            </el-row>
            <el-table v-loading="choiceLoading" :data="pageList" @selection-change="handleNotSelectionChange">
              <el-table-column type="selection" width="50" align="center" />
              <el-table-column label="用户账号"  width="80" align="center" key="userName" prop="userName" v-if="columns[1].visible" :show-overflow-tooltip="true" />
              <el-table-column label="名称" align="center" key="nickName" prop="nickName" v-if="columns[2].visible" :show-overflow-tooltip="true" />
              <el-table-column label="部门" align="center" key="deptName" prop="deptName" v-if="columns[3].visible" :show-overflow-tooltip="true" />
              <el-table-column
                label="操作"
                align="center"
                class-name="small-padding fixed-width"
              >
                <template slot-scope="scope" v-if="scope.row.userId !== 1">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-minus"
                    @click="handleUserCancel(scope.row)"
                  >取消</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              v-show="choiceTotal>0"
              :total="choiceTotal"
              layout="total, prev, pager, next"
              @current-change="handleCurrentChange"
             >
             </el-pagination>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { listUser,listChoiceUser } from "@/api/system/userdata";
import { getToken } from "@/utils/auth";
import { treeselect } from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import simPagination from "@/components/paging/index";
import { addUserchoice,batchAddUser,updateUserchoice,delUserchoice,exportUserchoice } from "@/api/system/userchoice";
import { newS4Guid, newGuid } from '@/utils/guidtool'


export default {
  name: "User",
  components: { Treeselect,simPagination },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      notIds: [],
      // 选中的数据组
      choiceUsers: [],
      notChoiceUsers: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      choiceUserList: [],
      choiceTotal: 0,
      allList: [],
      totalPage: 0,
      // 用户表格数据
      userList: null,
      // 弹出层标题
      title: "",
      // 部门树选项
      deptOptions: undefined,
      // 是否显示弹出层
      open: false,
      // 部门名称
      deptName: undefined,
      // 默认密码
      initPassword: undefined,
      // 日期范围
      dateRange: [],
      // 状态数据字典
      statusOptions: [],
      // 性别状态字典
      sexOptions: [],
      // 岗位选项
      postOptions: [],
      // 角色选项
      roleOptions: [],
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        nickName: undefined,
        phonenumber: undefined,
        status: undefined,
        deptId: undefined
      },
      // 查询参数
      choiceQueryParams: {
        pageNum: 1,
        pageSize: 10,
        currentPage: 1,
        choiceCode: undefined,
        userCode: undefined
      },
      choiceCode: undefined,
      choiceLoading: null,
      pageList: [],
      // 列信息
      columns: [
        { key: 0, label: `用户编号`, visible: true },
        { key: 1, label: `用户名称`, visible: true },
        { key: 2, label: `用户昵称`, visible: true },
        { key: 3, label: `部门`, visible: true },
        { key: 4, label: `手机号码`, visible: true },
        { key: 5, label: `状态`, visible: true },
        { key: 6, label: `创建时间`, visible: true }
      ]
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getList();
    this.getTreeselect();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_user_sex").then(response => {
      this.sexOptions = response.data;
    });
    this.getConfigKey("sys.user.initPassword").then(response => {
      this.initPassword = response.msg;
    });
  },
  methods: {
    /** 查询用户列表 */
    initData (selectList) {
      if (selectList !== null) {
        this.allList = selectList
        this.getPageData()
        this.choiceQueryParams.currentPage = 1
        this.packagePage()
      }
    },   
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        this.userList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.setChoice()
      }
      );
    },   
    /** 查询部门下拉树结构 */
    getTreeselect() {
      treeselect().then(response => {
        this.deptOptions = response.data;
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.getList();
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.queryParams = {
        pageNum: 1,
        pageSize: 10
      }
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId);
      this.choiceUsers = selection
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    handleNotSelectionChange(selection) {
      this.notIds = selection.map(item => item.userId);
      this.notChoiceUsers = selection
    },
    handleUsersIn () {
      for (let i = 0; i < this.choiceUsers.length; i ++) {
        // 开始判重
        let is_find = false;
        for (let j = 0; j < this.allList.length; j ++) {
          if (this.allList[j].userId === this.choiceUsers[i].userId) {
            is_find = true
          }
        }
        if (!is_find) {
          this.allList.push(this.choiceUsers[i])
          this.getPageData()
          this.packagePage()
        }
      }

    },
    handleUserIn (row) {
      let is_find = false
      for (let i = 0; i < this.allList.length; i ++) {
        if (this.allList[i].userId === row.userId) {
          is_find = true
        }
      }
      
      if (!is_find) {
        this.allList.push(row)
        this.getPageData()
        this.packagePage()

      }
      
      this.$refs.multipleTable.toggleRowSelection(row, true);
    },
    handleUsersCancel () {
      if (this.notIds === null || this.notIds.length === 0) {
        this.$message('未选中!');
        return
      }

      for (let i = 0; i < this.notChoiceUsers.length; i ++ ) {
        for (let j = 0; j < this.userList.length; j ++ ) {
          if (this.notChoiceUsers[i].userId === this.userList[j].userId) {
            this.$refs.multipleTable.toggleRowSelection(this.userList[j], false);
            break
          }
        }
        
        for (let k = 0; k < this.allList.length; k ++) {
          if (this.notChoiceUsers[i].userId === this.allList[k].userId) {
            this.allList.splice(k,1)
            this.getPageData()
            this.packagePage()
            break
          }
        }
      }
    },
    handleUserCancel(row) {
      for (let i = 0; i < this.userList.length; i ++ ) {
        if (this.userList[i].userId === row.userId) {
          this.$refs.multipleTable.toggleRowSelection(this.userList[i], false);
          break
        }
      }

      for (let i = 0; i < this.allList.length; i ++) {
        if (this.allList[i].userId === row.userId) {
          this.allList.splice(i,1)
          this.getPageData()
          this.packagePage()
          break
        }
      }
    },  
    setChoice () {      
      if (this.userList === null || this.userList.length === 0) {
        return
      }

      if (this.choiceUserList === null || this.choiceUserList === 0) {
        return
      }
      for (let i = 0; i < this.allList.length; i ++ ) {
        for (let j = 0; j < this.userList.length; j ++ ) {
          if (this.allList[i].userId === this.userList[j].userId) {
            this.$nextTick(() => {
              this.$refs.multipleTable.toggleRowSelection(this.userList[j], true)
            })
            break
          }
        }
      }
    },
    getPageData () {
      if (this.allList === undefined || this.allList === null) {
        this.allList = []
        this.choiceTotal = 0
        this.totalPage = 0
      } else {
        this.choiceTotal = this.allList.length
        this.totalPage = this.getPageSize()
      }
    },
    getPageSize () {
      if (this.choiceTotal === 0) {
        return 0
      }
      
      return this.choiceTotal % this.choiceQueryParams.pageSize == 0 ? this.choiceTotal / this.choiceQueryParams.pageSize : Math.ceil(this.choiceTotal / this.choiceQueryParams.pageSize)
    },
    packagePage () {
      this.pageList = []

      if (this.allList.length > 0) {
        const pos = (this.choiceQueryParams.currentPage - 1) * this.choiceQueryParams.pageSize
        // console.log('pos:' + pos)
        for (let i = pos; i < pos + this.choiceQueryParams.pageSize; i ++) {
          if ( i >= this.choiceTotal ) {
            break;
          }

          this.pageList.push(this.allList[i])
          // console.log('i:' + i)
          // console.log('当前数据:' + JSON.stringify(this.allList[i]))
        }
      }
    },
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`);
      // console.log('this.choiceTotal:' + this.choiceTotal)
      // console.log('this.totalPage:' + this.totalPage)
      this.choiceQueryParams.currentPage = val
      this.packagePage()      
    },    
    handleBack() {
      // console.log('选中的数据项' + JSON.stringify(this.allList))
      this.$emit('refreshChoiceData', this.allList)
    }    
  }
};
</script>