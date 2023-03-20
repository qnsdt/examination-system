<template>
  <div >
    <div class = "content-area" :style = "ui_style">
    <div class = "button-panel" v-if="isUpdate">
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
      <el-table v-loading="loading" :data="groupList" @selection-change="handleSelectionChange" ref="multipleTable"> 
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="分组序号" align="center" prop="groupId" />
        <el-table-column label="分组名称" align="center" prop="groupName"  />
        <el-table-column label="分组描述" align="center" prop="groupDescribe"  />
        <el-table-column label="人数" align="center" prop="personNumber"  />
      </el-table>      
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
</template>

<script>
import { listGroup, listChoiceGroup, getGroup, delGroup, addGroup, updateGroup, exportGroup } from "@/api/system/group";
import { batchUpdateTaskgroup } from "@/api/exam/taskgroup";

export default {
  data () {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      examData: null,
      // 总条数
      total: 0,
      // 人员分组表格数据
      groupList: [],
      // 选中人员分组
      choiceGroupList: [],
      choiceGroups: [],
      saveList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        groupName: null,
        status: null
      },
      bankData: null,
      isUpdate: false
   }
  },
  created () {
    const s_width = document.body.clientWidth
    if (s_width < 1000) {
       this.ui_style = 'margin-left: 2%; margin-right: 2%;'    
    } else if (s_width >= 1000 && s_width < 1366) {
       this.ui_style = 'margin-left: 8%; margin-right: 8%;'    
    } else if (s_width >= 1366 && s_width < 1566) {
       this.ui_style = 'margin-left: 12%; margin-right: 12%;'    
    } else if (s_width >= 1566 && s_width < 1766) {
       this.ui_style = 'margin-left: 18%; margin-right: 18%;'    
    } else {
       this.ui_style = 'margin-left: 24%; margin-right: 24%;'  
    }
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });    
  },
  methods: {
    init_data (contentdata, selectdata) {
      this.bankData = contentdata
      this.saveList = selectdata

      this.getList()
    },
    update_data (row) {
      this.isUpdate = true
      this.examData = row
      this.getList()
      this.getChoiceList()
    },    
    getList() {
      this.loading = true;
      listGroup(this.queryParams).then(response => {
        this.groupList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.setChoice()
      });
    },
    getChoiceList() {
      this.loading = true;
      listChoiceGroup(this.examData.examCode).then(response => {
        this.saveList = response.rows;
        this.loading = false;
      });
    },
     // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.groupId)
      this.choiceGroups = selection
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
    onClose () {
      this.$emit('refreshGroup')
    },    
    onSaveOrBack () {
      this.saveData()
      this.$emit('refreshGroup')
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

        let groupSaveList = []
        for (let i = 0; i < this.saveList.length; i ++) {
          groupSaveList.push(
            {
              examCode: this.examData.examCode,
              groupCode: this.saveList[i].groupCode
            }
          )
        }
        batchUpdateTaskgroup(groupSaveList).then(response => {
          this.$message.success('保存成功!')
        });
      })
    },
    getSelect() {
      if (this.groupList === null || this.groupList.length == 0) {
         return
      }
      // 第一遍循环置状态
      let is_find = false
      for (let i = 0; i < this.groupList.length; i ++) {
        is_find = false
        if (this.choiceGroups !== null || this.choiceGroups.length > 0) {
          for (let j = 0; j < this.choiceGroups.length; j ++) {
            if (this.groupList[i].groupId === this.choiceGroups[j].groupId) {
              is_find = true
            }
          }
        }
        
        if (is_find) {
          this.groupList[i].remark = 'T'
        } else {
          this.groupList[i].remark = 'F'
        }
      }

      // 第二遍循环操作缓存列表数据
      if (this.saveList === null) {
        this.saveList = []
      }

      for (let i = 0; i < this.groupList.length; i ++) {
        is_find = false
        let order_id = -1

        for (let j = 0; j < this.saveList.length; j ++) {
            if (this.groupList[i].groupId === this.saveList[j].groupId) {
              is_find = true
              order_id = j
            }
        }

        if (this.groupList[i].remark === 'T' && !is_find) {
          this.saveList.push(this.groupList[i])
        } else if (this.groupList[i].remark === 'F' && is_find) {
          this.saveList.splice(order_id,1)
        }
      }
    },
    setChoice () {      
      if (this.saveList === null || this.saveList.length === 0) {
        return
      }

      if (this.groupList === null || this.groupList === 0) {
        return
      }
      for (let i = 0; i < this.groupList.length; i ++ ) {
        for (let j = 0; j < this.saveList.length; j ++ ) {
          if (this.groupList[i].groupId === this.saveList[j].groupId) {
            this.$nextTick(() => {
              this.$refs.multipleTable.toggleRowSelection(this.groupList[i], true)
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
.content-area {
   margin-top: 1.6rem;
   background-color: #f7f7f7;
}

.button-panel {
  padding-bottom: 1.6rem;
  background-color: #fff; 
}

.form-area {
  padding-top: 1.6rem;
  background-color: #fff;  
}

.choice-button {
  text-align: center;
}

.el-pagination {
  white-space: nowrap;
  padding: 12px 5px 2px 5px;
  color: #303133;
  font-weight: bold;
  text-align:  right;
}
</style>



