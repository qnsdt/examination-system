<template>
  <div class = "legend-case">
    <div >
        <div class = "mark-init"></div>
        <div class = "mark-text">未答</div>
    </div>
    <div >
        <div class = "mark-current"></div>
        <div class = "mark-text">当前</div>
    </div>
    <div >
        <div class = "mark-anwser" ></div>
        <div class = "mark-text">已答</div>
    </div>
    <div >
        <div class = "mark-dot"></div>
        <div class = "mark-init"></div>
        <div class = "mark-text">标记</div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.legend-case {
  width: 100%;
  display: flex;
  margin-top: 0.3rem;
  margin-bottom: 0.3rem;
  padding-left: 0.2rem;
  padding-right: 1.4rem;
  justify-content: space-between;  
}

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

.mark-text {
  line-height: 1.8rem ;
  font-size: 1rem;
  margin-left: -0.2rem;
}

</style>