<template>
  <div class = "mark-questions" @click="doClick">
    <div v-if = "!paras.isDone">
        <div v-show = "paras.isMark" class = "mark-dot"></div>
        <div class = "mark-init" :style = "paras.currentStyle">{{ paras.questionNumber }}</div>
    </div>
    <div v-else>
        <div v-show = "paras.isMark" class = "mark-dot"></div>
        <div class = "mark-anwser" :style = "paras.currentStyle">{{ paras.questionNumber }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    paras: {
      type: Object,
      default:{}        
    }
  },    
  data() {
    return {
    }
  },
  created() {
  },
  mounted () {
  },
  methods: {
    doClick() {
      this.$emit('refreshClick',this.paras)
    }   
  }
};
</script>

<style scoped lang="scss">
.mark-init {
  border: 1px solid #c6c6c6;
  width: 1.6rem;
  height: 1.6rem;
  border-radius: 50%;
  line-height: 1.6rem;
  text-align: center;
  font-size: 0.8rem;
  font-weight:bold;
}

.mark-current {
  border: 1px solid  red;
  width: 1.6rem;
  height: 1.6rem;
  border-radius: 50%;
  line-height: 1.6rem;
  text-align: center;
  font-size: 0.8rem;
  font-weight:bold;
}

.mark-dot {
  width: 0.35rem;
  height: 0.35rem;
  content: " ";
  position: relative;
  margin-top: -0.35rem;
  background-color: red;
  border-radius: 50%;
  top: 0.2rem;
  left: 1.3rem;
}

.mark-anwser {
  width: 1.6rem;
  height: 1.6rem;
  border-radius: 50%;
  line-height: 1.6rem;
  text-align: center;
  font-size: 0.8rem;
  font-weight:bold;
  background-color: greenyellow;
}

.mark-questions {
  cursor: pointer;
  border: 1px solid #c6c6c6;
  margin: 0.45rem;
  width: 1.6rem;
  height: 1.6rem;
  border-radius: 50%;
  line-height: 1.6rem;
  text-align: center;
  font-size: 0.8rem;
  font-weight:bold;
}

</style>