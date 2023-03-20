<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分组代码" prop="groupCode">
        <el-input
          v-model="queryParams.groupCode"
          placeholder="请输入分组代码"
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
          v-hasPermi="['system:groupperson:add']"
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
          v-hasPermi="['system:groupperson:edit']"
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
          v-hasPermi="['system:groupperson:remove']"
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
          v-hasPermi="['system:groupperson:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="grouppersonList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="分组代码" align="center" prop="groupCode" />
      <el-table-column label="人员代码" align="center" prop="personCode" />
      <el-table-column label="组内排序" align="center" prop="orderId" :formatter="orderIdFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:groupperson:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:groupperson:remove']"
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

    <!-- 添加或修改分组用户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="组内排序" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入组内排序" />
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
import { listGroupperson, getGroupperson, delGroupperson, addGroupperson, updateGroupperson, exportGroupperson } from "@/api/system/groupperson";

export default {
  name: "Groupperson",
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
      // 分组用户表格数据
      grouppersonList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 分组代码字典
      groupCodeOptions: [],
      // 人员代码字典
      personCodeOptions: [],
      // 组内排序字典
      orderIdOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        groupCode: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        groupCode: [
          { required: true, message: "分组代码不能为空", trigger: "blur" }
        ],
        personCode: [
          { required: true, message: "人员代码不能为空", trigger: "blur" }
        ],
        orderId: [
          { required: true, message: "组内排序不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("${column.dictType}").then(response => {
      this.groupCodeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.personCodeOptions = response.data;
    });
    this.getDicts("${column.dictType}").then(response => {
      this.orderIdOptions = response.data;
    });
  },
  methods: {
    /** 查询分组用户列表 */
    getList() {
      this.loading = true;
      listGroupperson(this.queryParams).then(response => {
        this.grouppersonList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 分组代码字典翻译
    groupCodeFormat(row, column) {
      return this.selectDictLabel(this.groupCodeOptions, row.groupCode);
    },
    // 人员代码字典翻译
    personCodeFormat(row, column) {
      return this.selectDictLabel(this.personCodeOptions, row.personCode);
    },
    // 组内排序字典翻译
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
        groupCode: null,
        personCode: null,
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
      this.ids = selection.map(item => item.groupCode)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加分组用户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const groupCode = row.groupCode || this.ids
      getGroupperson(groupCode).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改分组用户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.groupCode != null) {
            updateGroupperson(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGroupperson(this.form).then(response => {
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
      const groupCodes = row.groupCode || this.ids;
      this.$confirm('是否确认删除分组用户编号为"' + groupCodes + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delGroupperson(groupCodes);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有分组用户数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportGroupperson(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
