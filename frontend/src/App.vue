<template>
  <div class="main-bkg">
    <top></top>
    <router-view></router-view>
    <!-- 模拟数据流的元素 -->
    <div class="data-stream">
      <span v-for="i in 20" :key="i"></span>
    </div>
  </div>
</template>

<script>
import Top from './views/Top.vue'

export default {
  components: {
    Top
  },
  // Vue2 选项式API
  data() {
    return {
      // 可以在这里添加需要的响应式数据
    }
  },
  mounted() {
    // 组件挂载后可以执行的逻辑
  },
  methods: {
    // 可以在这里添加方法
  }
}
</script>

<style lang="less" scoped>
.main-bkg {
  
  height: 100vh;
  margin: 0;
  padding: 0;
  overflow: hidden;
  top: 0;
left: 0;
width: 100%;
height: 100%;
  /* 加深背景颜色 */
  background: radial-gradient(ellipse at bottom, #020613 0%, #000c1a 70%, #000000 100%);
  position: relative;
  font-family: 'Cascadia Code', sans-serif;
  z-index: 2;
}

/* 移除动态线条样式 */
.dynamic-lines {
  display: none;
}

/* 添加新的动态光影效果 */
.main-bkg::before {
  content: '';
  position: absolute;
  top: -50%;
  left: -50%;
  width: 200%;
  height: 200%;
  background: radial-gradient(ellipse at center, rgba(0, 123, 255, 0.2) 0%, rgba(0, 0, 0, 0) 70%);
  animation: lightMove 20s linear infinite;
  z-index: -1;
}

@keyframes lightMove {
  0% {
    transform: translate(-20%, -20%) rotate(0deg);
  }
  100% {
    transform: translate(20%, 20%) rotate(360deg);
  }
}

/* 模拟数据流效果 */
.data-stream {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: -1;
}

.data-stream span {
  position: absolute;
  width: 2px;
  height: 10px;
  background: rgba(0, 123, 255, 0.5);
  animation: streamMove linear infinite;
  opacity: 0.8;
}

@keyframes streamMove {
  0% {
    transform: translateY(-100%);
  }
  100% {
    transform: translateY(100vh);
  }
}

/* 随机生成不同位置和动画时长的数据流 */
.data-stream span:nth-child(1) {
  left: 5%;
  animation-duration: 12s;
}

.data-stream span:nth-child(2) {
  left: 15%;
  animation-duration: 10s;
}

.data-stream span:nth-child(3) {
  left: 25%;
  animation-duration: 14s;
}

.data-stream span:nth-child(4) {
  left: 35%;
  animation-duration: 9s;
}

.data-stream span:nth-child(5) {
  left: 45%;
  animation-duration: 11s;
}

.data-stream span:nth-child(6) {
  left: 55%;
  animation-duration: 13s;
}

.data-stream span:nth-child(7) {
  left: 65%;
  animation-duration: 8s;
}

.data-stream span:nth-child(8) {
  left: 75%;
  animation-duration: 15s;
}

.data-stream span:nth-child(9) {
  left: 85%;
  animation-duration: 10s;
}

.data-stream span:nth-child(10) {
  left: 95%;
  animation-duration: 12s;
}

.data-stream span:nth-child(11) {
  left: 2%;
  animation-duration: 13s;
}

.data-stream span:nth-child(12) {
  left: 8%;
  animation-duration: 11s;
}

.data-stream span:nth-child(13) {
  left: 18%;
  animation-duration: 9s;
}

.data-stream span:nth-child(14) {
  left: 28%;
  animation-duration: 14s;
}

.data-stream span:nth-child(15) {
  left: 38%;
  animation-duration: 10s;
}

.data-stream span:nth-child(16) {
  left: 48%;
  animation-duration: 12s;
}

.data-stream span:nth-child(17) {
  left: 58%;
  animation-duration: 8s;
}

.data-stream span:nth-child(18) {
  left: 68%;
  animation-duration: 15s;
}

.data-stream span:nth-child(19) {
  left: 78%;
  animation-duration: 11s;
}

.data-stream span:nth-child(20) {
  left: 88%;
  animation-duration: 13s;
}

@media (max-width: 768px) {
  /* 可以根据小屏幕调整动画参数 */
  @keyframes lightMove {
    0% {
      transform: translate(-10%, -10%) rotate(0deg);
    }
    100% {
      transform: translate(10%, 10%) rotate(360deg);
    }
  }
}
::v-deep .el-dialog__wrapper {
  .v-modal {
    background-color: rgba(0, 0, 0, 0.3) !important; // 调低透明度
    backdrop-filter: blur(2px); // 添加模糊效果
  }
  
  // 确保弹窗内容在最上层
  .el-dialog {
    z-index: 2001 !important;
  }
}
 ::v-deep .v-modal {
  display: none !important;
}
</style>