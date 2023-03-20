<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类别名称" prop="typeName">
        <el-input
          v-model="queryParams.typeName"
          placeholder="请输入类别名称"
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
          v-hasPermi="['questions:examtype:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="examtypeList"
      row-key="typeId"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="上级类别ID" align="center" prop="parentId"  />
      <el-table-column label="类别代码" align="center" prop="typeCode"  />
      <el-table-column label="类别名称" align="center" prop="typeName"  />
      <el-table-column label="顺序Id" align="center" prop="orderId"  />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['questions:examtype:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['questions:examtype:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['questions:examtype:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改考试分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="上级类别ID" prop="parentId">
          <treeselect v-model="form.parentId" :options="examtypeOptions" :normalizer="normalizer" placeholder="请选择上级类别ID" />
        </el-form-item>
        <el-form-item label="类别代码" prop="typeCode">
          <el-input v-model="form.typeCode" placeholder="请输入类别代码" />
        </el-form-item>
        <el-form-item label="类别名称" prop="typeName">
          <el-input v-model="form.typeName" placeholder="请输入类别名称" />
        </el-form-item>
        <el-form-item label="顺序Id" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入顺序Id" />
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
import { listExamtype, getExamtype, delExamtype, addExamtype, updateExamtype, exportExamtype } from "@/api/questions/examtype";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Examtype",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 考试分类表格数据
      examtypeList: [],
      // 考试分类树选项
      examtypeOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 类别id字典
      typeIdOptions: [],
      // 上级类别ID字典
      parentIdOptions: [],
      // 类别代码字典
      typeCodeOptions: [],
      // 类别名称字典
      typeNameOptions: [],
      // 顺序Id字典
      orderIdOptions: [],
      // 状态字典
      statusOptions: [],
      // 创建者字典
      createByOptions: [],
      // 创建时间字典
      createTimeOptions: [],
      // 查询参数
      queryParams: {
        typeName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        typeName: [
          { required: true, message: "类别名称不能为空", trigger: "blur" }
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
    /** 查询考试分类列表 */
    getList() {
      this.loading = true;
      listExamtype(this.queryParams).then(response => {
        this.examtypeList = this.handleTree(response.data, "typeId", "parentId");
        this.loading = false;
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
    // 类别id字典翻译
    typeIdFormat(row, column) {
      return this.selectDictLabel(this.typeIdOptions, row.typeId);
    },
    // 上级类别ID字典翻译
    parentIdFormat(row, column) {
      return this.selectDictLabel(this.parentIdOptions, row.parentId);
    },
    // 类别代码字典翻译
    typeCodeFormat(row, column) {
      return this.selectDictLabel(this.typeCodeOptions, row.typeCode);
    },
    // 类别名称字典翻译
    typeNameFormat(row, column) {
      return this.selectDictLabel(this.typeNameOptions, row.typeName);
    },
    // 顺序Id字典翻译
    orderIdFormat(row, column) {
      return this.selectDictLabel(this.orderIdOptions, row.orderId);
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 创建者字典翻译
    createByFormat(row, column) {
      return this.selectDictLabel(this.createByOptions, row.createBy);
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
        typeId: null,
        parentId: null,
        typeCode: null,
        typeName: null,
        orderId: null,
        status: 0,
        createBy: null,
        createTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.typeId) {
        this.form.parentId = row.typeId;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加考试分类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.parentId = row.typeId;
      }
      getExamtype(row.typeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改考试分类";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.typeId != null) {
            updateExamtype(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExamtype(this.form).then(response => {
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
      this.$confirm('是否确认删除考试分类编号为"' + row.typeId + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delExamtype(row.typeId);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    }
  }
};
</script>
