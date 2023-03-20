<template>
  <div class="app-container">
    <div v-if="choiceVisible" >
      <el-button
        type="warning"
        plain
        icon="el-icon-s-promotion"
        size="mini"
        @click="doin"
      >进入</el-button>
    </div>
    <div v-else >
      <!-- 弹窗, 新增 / 修改 -->
      <choice-user  ref="choiceRef" @refreshChoiceData="choiceDoneHandle"></choice-user>
    </div>    
  </div>  
</template>

<script>
import choiceUser from '@/components/ChoiceUser/index'

export default {
  name: "Choice",
  components: { choiceUser },
  data() {
    return {
      choiceVisible: true,
      total:3,
      userList: []
    };
  },
  created() {
  },
  methods: {
    doin() {
      this.choiceVisible = false
      this.$nextTick(() => {
        this.$refs.choiceRef.initData(this.userList)
      })
    },
    choiceDoneHandle(selectData) {
      this.choiceVisible = true      
    }
  }
};
</script>