<template>
  <div class="app-container">
    <div v-if="choiceVisible">
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="分组名称" prop="groupName">
          <el-input
            v-model="queryParams.groupName"
            placeholder="请输入分组名称"
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
            v-hasPermi="['system:group:add']"
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
            v-hasPermi="['system:group:edit']"
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
            v-hasPermi="['system:group:remove']"
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
            v-hasPermi="['system:group:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="groupList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="分组序号" align="center" prop="groupId" />
        <!-- <el-table-column label="分组代码" align="center" prop="groupCode"  /> -->
        <el-table-column label="分组名称" align="center" prop="groupName"  />
        <el-table-column label="分组描述" align="center" prop="groupDescribe"  />
        <el-table-column label="人数" align="center" prop="personNumber"  />
        <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-user"
              @click="handleGroup(scope.row)"
            >选人</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:group:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:group:remove']"
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

      <!-- 添加或修改人员分组对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="分组名称" prop="groupName">
            <el-input v-model="form.groupName" placeholder="请输入分组名称" />
          </el-form-item>
          <el-form-item label="分组描述" prop="groupDescribe">
            <el-input v-model="form.groupDescribe" placeholder="请输入分组描述" />
          </el-form-item>
          <el-form-item label="状态">
            <el-radio-group v-model="form.status">
              <el-radio
                v-for="dict in statusOptions"
                :key="dict.dictValue"
                :label="dict.dictValue"
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
    <div v-else>
      <choice-user  ref="choiceRef" @refreshChoiceData="choiceDoneHandle"></choice-user>
    </div>
  </div>

</template>

<script>
import { listChoiceuser } from "@/api/system/sysuserchoice";
import { listGroup, getGroup, delGroup, addGroup, updateGroup, exportGroup } from "@/api/system/group";
import choiceUser from '@/components/ChoiceUser/index'
import { delGroupperson, addGroupperson } from "@/api/system/groupperson";

export default {
  name: "Group",
  components: { choiceUser },
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
      // 人员分组表格数据
      groupList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 分组序号字典
      groupIdOptions: [],
      // 分组代码字典
      groupCodeOptions: [],
      // 分组名称字典
      groupNameOptions: [],
      // 分组描述字典
      groupDescribeOptions: [],
      // 状态字典
      statusOptions: [],
      // 创建者字典
      createByOptions: [],
      // 创建时间字典
      createTimeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        groupName: null,
        status: 'T'
      },
      // 表单参数
      form: {},
      userList: [],
      choiceCode: null,
      count: 0,
      choiceVisible: true,
      // 表单校验
      rules: {
        groupName: [
          { required: true, message: "分组名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询人员分组列表 */
    getList() {
      this.loading = true;
      listGroup(this.queryParams).then(response => {
        this.groupList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        groupId: null,
        groupCode: null,
        groupName: null,
        groupDescribe: null,
        status: "0",
        createBy: null,
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
      this.ids = selection.map(item => item.groupId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加人员分组";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const groupId = row.groupId || this.ids
      getGroup(groupId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改人员分组";
      });
    },
    handleGroup(row) {
      this.getChoiceList(row.groupCode)
    },
    getChoiceList(choicecode) {
      this.choiceCode = choicecode
      const sysUser = {
        choiceCode: choicecode
      }
      listChoiceuser(sysUser).then(response => {
        this.userList= response.data;
        if (this.userList !== null && this.userList.length > 0) {
          this.count = this.userList.length
        }
        this.openSelect()
      });
    },
    openSelect() {
      const _self = this
      _self.choiceVisible = false
      _self.$nextTick(() => {
        _self.$refs.choiceRef.initData(_self.userList)
      })      
    },
    choiceDoneHandle(selectData) {
      this.choiceVisible = true
      if (selectData !== null && selectData.length >0) {
        this.userList = selectData
        let personList = []
        for (let i = 0; i < selectData.length; i ++) {
          personList.push({
            groupCode: this.choiceCode,
            personCode: selectData[i].userCode
          })
        }
        this.loading = true;
        addGroupperson(personList).then(response => {
          this.loading = false;
          this.getList();
        });
      } else if (this.count > 0) {
        this.loading = true;
        delGroupperson(this.choiceCode).then(response => {
          this.loading = false;
          this.getList();
        });       
      }  
    },
    doSave () {

    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.groupId != null) {
            updateGroup(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGroup(this.form).then(response => {
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
      const groupIds = row.groupId || this.ids;
      this.$confirm('是否确认删除人员分组编号为"' + groupIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delGroup(groupIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有人员分组数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportGroup(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
