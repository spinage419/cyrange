<template>
  <div class="challenges-container">
          <!-- 顶部标题 -->
          <h1 class="page-title">
                  <span class="title-text">Challenges</span>
                  <span class="title-decoration"></span>
          </h1>

          <div class="main-content">
                  <!-- 左侧题目类型选择 -->
                  <div class="category-sidebar">
                          <h3 class="sidebar-title">题目分类</h3>
                          <el-menu :default-active="selectedCategory" @select="handleCategorySelect"
                                  class="category-menu">
                                  <el-menu-item v-for="category in categories" :key="category.id" :index="category.id">
                                          <i class="el-icon-folder-opened"></i>
                                          <span>{{ category.name }}</span>
                                          <el-tag size="mini" type="info">{{ category.count }}</el-tag>
                                  </el-menu-item>
                          </el-menu>
                  </div>

                  <!-- 中间题目区域 -->
                  <div class="challenges-main">
                          <div class="button-grid" :style="{ gridTemplateColumns: `repeat(${columnsPerRow}, 1fr)` }">
                                  <div v-for="(buttonData, index) in chunkedButtons" :key="index" class="button-cell">
                                          <el-button class="challenge-card" :class="{ 'solved': buttonData.solved }"
                                                  :type="'primary'" @click="openDialog(buttonData)">
                                                  <div class="challenge-inner">
                                                          <div class="card-badge" v-if="buttonData.difficulty">
                                                                  {{ buttonData.difficulty }}
                                                          </div>
                                                          <el-icon class="card-icon">
                                                                  <trophy />
                                                          </el-icon>
                                                          <p class="card-title">{{ buttonData.title }}</p>
                                                          <div class="card-stats">
                                                                  <span class="stat-item">
                                                                          <el-icon>
                                                                                  <user />
                                                                          </el-icon>
                                                                          {{ buttonData.points || 10 }}分
                                                                  </span>
                                                                  <span class="stat-item">
                                                                          <el-icon>
                                                                                  <star />
                                                                          </el-icon>
                                                                          {{ buttonData.participants || 0 }}
                                                                  </span>
                                                          </div>
                                                  </div>
                                          </el-button>
                                  </div>
                          </div>
                  </div>
          </div>

          <!-- 题目详情弹窗 -->
          <el-dialog :visible.sync="dialogVisible"   width="55%" @close="closeDialog"  append-to-body>
                  <template #header>
                          <h2 class="dialog-title">{{ currentButtonData?.title }}</h2>
                  </template>

                  <div class="dialog-content">
                          <!-- 描述区域 -->
                          <div class="description-container">
                                  <p class="description-text">{{ currentButtonMoreData?.description }}</p>
                                  <p class="author-info">by {{ currentButtonMoreData?.author }}</p>
                          </div>

                          <!-- 靶场操作区 -->
                          <div class="target-info">
                                  <div class="button-container" v-if="!currentButtonMoreData?.start">
                                          <el-button type="primary" size="medium" icon="el-icon-caret-right"
                                                  @click="startContainer">
                                                  启动靶场
                                          </el-button>
                                  </div>

                                  <div v-if="currentButtonMoreData?.start" class="operation-panel">
                                          <div class="url-container">
                                                  <el-link type="primary" :href="containerUrl" target="_blank"
                                                          class="access-url">
                                                          <i class="el-icon-link"></i>
                                                          靶机访问地址:{{ containerUrl }}
                                                  </el-link>
                                          </div>

                                          <div class="action-buttons">
                                                  <el-button type="warning" size="small" icon="el-icon-delete"
                                                          @click="handleDestroy" class="destroy-btn">
                                                          销毁靶机
                                                  </el-button>

                                                  <el-button type="info" size="small" icon="el-icon-time"
                                                          @click="handleExtendTime" class="extend-btn">
                                                          延长60秒
                                                  </el-button>

                                                  <div class="countdown-container">
                                                          <div class="countdown-box">
                                                                  <i class="el-icon-alarm-clock"></i>
                                                                  <span class="countdown-text">{{ remainingTime }}s</span>
                                                          </div>
                                                          <div class="countdown-progress">
                                                                  <el-progress
                                                                          :percentage="(remainingTime / totalTime) * 100"
                                                                          :show-text="false" :stroke-width="4"
                                                                          color="#6ab7ff" />
                                                          </div>
                                                  </div>
                                          </div>
                                  </div>
                          </div>
                  </div>
                  <span slot="footer" class="dialog-footer">
    <el-input 
      v-model="input" 
      placeholder="请输入flag" 
      class="flag-input"
      clearable
    >
      <template #prefix>
        <i class="el-icon-flag"></i>
      </template>
    </el-input>
    <el-button 
      type="success" 
      class="submit-button"
      @click="handleSubmit"
    >
      提交验证
    </el-button>
  </span>
</el-dialog>
  </div>

</template>

<script>
export default {
  data() {
    return {
      categories: [],
      selectedCategory: '0',
      dialogVisible: false,
      buttons: [], // 用于存储从后端获取的按钮信息
      columnsPerRow: 4, // 每行按钮数量
      currentButtonData: null, // 当前点击的按钮数据
      currentButtonMoreData: null,//当前点击的按钮的详细数据
      showHint: false,
      remainingTime: 0,  // 剩余时间（秒）
      timer: null ,//倒计时
      totalTime: 0,
      containerUrl: '', // 新增靶机地址 
      input: null//flag
    };
  },
  computed: {
    chunkedButtons() {
      // 根据选择分类过滤题目
      return this.selectedCategory === '0' 
        ? this.buttons 
        : this.buttons.filter(btn => btn.category === this.selectedCategory);
    },
    formattedTime() {
      const minutes = Math.floor(this.remainingTime / 60);
      const seconds = this.remainingTime % 60;
      return `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    }
  },
  created() {
    this.fetchCategories();
    this.fetchButtons();
  },
  methods: {
    async fetchCategories() {
      try {
        const response = await this.$axios.get('api/challenges/categories',  {  
            });
        this.categories = response.data.data;
      } catch (error) {
        console.error('Error fetching categories:', error);
      }
    },
    handleCategorySelect(index) {
      this.selectedCategory = index;
    },
    async fetchButtons() {
    try {
    const response = await this.$axios.get('api/challenges', {});
    this.buttons = response.data.data.map(button => ({
      ...button,
      solved: button.solved || false // 确保每个按钮都有 solved 字段
    }));
  } catch (error) {
    console.error('Error fetching buttons:', error);
  }
  },
  async openDialog(buttonData) {
      this.currentButtonData = buttonData;
      this.dialogVisible = true;
      try {
      const response = await this.$axios.get('api/challenges/'+this.currentButtonData.questionId,  {  
            });
      this.currentButtonMoreData = response.data.data;
      console.error(response.data.data);
      if(this.currentButtonData.start!=true){
        this.remainingTime = response.data.data.remainingSeconds;
        this.containerUrl = response.data.data.accessUrl; 
        this.totalTime = this.remainingTime;
        this.startCountdown();
      }
    } catch (error) {
      console.error( error);
    }
    },
  async startContainer() {
   
        const response = await this.$axios.get(`api/challenges/container/${this.currentButtonData.questionId}`,  {  
            });
        if(response.data.code==0){
          this.$message.error(response.data.msg);
          return;
        }
        this.openDialog(this.currentButtonData);
        this.startCountdown();
        
    },
    startCountdown() {
      // 先清除已有定时器
      if (this.timer) clearInterval(this.timer);
      
      this.timer = setInterval(() => {
        if (this.remainingTime > 0) {
          this.remainingTime--;
        } else {
          clearInterval(this.timer);
          this.handleTimeout();
        }
      }, 1000);
    },

    handleTimeout() {
      if(this.currentButtonData.start==true)
      this.$message.warning('靶场时间已到期');
      // 这里可以添加到期后的自动操作，例如自动提交
    },
    async handleDestroy() {
      try {
        const response=await this.$axios.delete(`api/challenges/${this.currentButtonData.questionId}`,  {  
            });
          if(response.data.code==0){
          this.$message.error(response.data.msg);
          return;
        }
        this.$message.success('靶机已销毁');
        this.openDialog(this.currentButtonData);
      } catch (error) {
        this.$message.error('销毁失败');
      }
    },

    async handleExtendTime() {
      try {
        const confirm = await this.$confirm('确定延长一次吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        });
        
        const res = await this.$axios.get(`api/challenges/container/extend/${this.currentButtonData.questionId}`, {
        });
        this.openDialog(this.currentButtonData);
        if(res.data.code==0){
          this.$message.error(res.data.msg);
          return;
        }
        this.$message.success('成功延长');
      } catch (error) {
          this.$message.error('延长失败');
      }
    },
    closeDialog() {
      // 清除定时器
      if (this.timer) clearInterval(this.timer);
      // 重置剩余时间
      this.remainingTime = 0;
      this.dialogVisible = false;
      this.currentButtonData=null;
      this.input=null;
      if (this.timer) clearInterval(this.timer);
    },
  async handleSubmit(){
    const response = await this.$axios.post("api/challenges/attempt", {
    questionId: this.currentButtonData.questionId,
    submission: this.input
});
        if(response.data.code==0){
          this.$message.error(response.data.msg);
          return;
        }
        this.$message.success("恭喜答对了！");
        const index = this.buttons.findIndex(button => button.questionId === this.currentButtonData.questionId);
  if (index !== -1) {
  const updatedButton = { ...this.buttons[index], solved: true };
  this.buttons.splice(index, 1, updatedButton);
   }
        this.openDialog(this.currentButtonData);
        this.startCountdown();
        
  }
  }
};
</script>

<style lang="less" scoped>
.challenges-container {
        --primary-color: #6ab7ff;
        --hover-color: #4a90e2;
        --bg-gradient: linear-gradient(to right, #0a1128, #001f3f);
        --text-glow: 0 0 10px rgba(106, 183, 255, 0.5);
        --card-bg: rgba(16, 42, 87, 0.7);

        background: var(--bg-gradient);
        min-height: 100vh;
        padding: 20px;
        color: #d1e7ff;
        font-family: 'Orbitron', 'Microsoft YaHei', sans-serif;

        .page-title {
                position: relative;
                text-align: center;
                margin: 30px 0 50px;
                padding-bottom: 15px;

                .title-text {
                        font-size: 2.2rem;
                        font-weight: 600;
                        background: linear-gradient(135deg, #6ab7ff 0%, #1286f3 100%);
                        -webkit-background-clip: text;
                        -webkit-text-fill-color: transparent;
                        text-shadow: var(--text-glow);
                        letter-spacing: 3px;
                        position: relative;
                        display: inline-block;

                        &::after {
                                content: '';
                                position: absolute;
                                bottom: -10px;
                                left: 50%;
                                transform: translateX(-50%);
                                width: 80px;
                                height: 3px;
                                background: var(--primary-color);
                                border-radius: 3px;
                                box-shadow: 0 0 15px var(--primary-color);
                        }
                }
        }

        .main-content {
                display: flex;
                max-width: 1400px;
                margin: 0 auto;
                background: rgba(10, 17, 40, 0.5);
                border-radius: 12px;
                box-shadow: 0 0 30px rgba(0, 123, 255, 0.1);
                backdrop-filter: blur(5px);
                border: 1px solid rgba(0, 123, 255, 0.2);
                overflow: hidden;
        }

        .category-sidebar {
                width: 280px;
                padding: 20px;
                background: rgba(5, 12, 30, 0.5);
                border-right: 1px solid rgba(0, 123, 255, 0.2);

                .sidebar-title {
                        font-size: 1.3rem;
                        color: #6ab7ff;
                        padding: 15px;
                        margin: 0 0 15px;
                        text-align: center;
                        border-bottom: 1px solid rgba(0, 123, 255, 0.3);
                        text-shadow: var(--text-glow);
                }

                .category-menu {
                        border-right: none;
                        background: transparent;

                        .el-menu-item {
                                height: 50px;
                                line-height: 50px;
                                margin: 5px 0;
                                border-radius: 6px;
                                color: #a8c6ff;
                                background: rgba(16, 42, 87, 0.3);
                                transition: all 0.3s cubic-bezier(0.68, -0.55, 0.27, 1.55);

                                i {
                                        color: #6ab7ff;
                                        font-size: 1.2rem;
                                        margin-right: 12px;
                                }

                                .el-tag {
                                        background: rgba(106, 183, 255, 0.1);
                                        color: #6ab7ff;
                                        border-color: rgba(106, 183, 255, 0.3);
                                }

                                &:hover {
                                        background: rgba(0, 123, 255, 0.1);
                                        transform: translateX(5px);
                                        color: #fff;
                                }

                                &.is-active {
                                        background: linear-gradient(90deg, rgba(0, 123, 255, 0.3), transparent);
                                        border-left: 3px solid var(--primary-color);
                                        color: #fff;
                                        box-shadow: 0 0 15px rgba(0, 123, 255, 0.2);

                                        .el-tag {
                                                background: rgba(255, 255, 255, 0.2);
                                                color: #fff;
                                        }
                                }
                        }
                }
        }

        .challenges-main {
                flex: 1;
                padding: 25px;

                .button-grid {
                        display: grid;
                        gap: 20px;
                        grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                }
        }

        .challenge-card {
          
                width: 100%;
                height: 160px;
                border: none !important;
                border-radius: 10px !important;
                background: var(--card-bg) !important;
                backdrop-filter: blur(5px);
                border: 1px solid rgba(0, 123, 255, 0.2) !important;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
                position: relative;
                overflow: hidden;
                transition: all 0.4s cubic-bezier(0.68, -0.55, 0.27, 1.55);
  


@keyframes iconFloat {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-5px); }
}

                &::before {
                        content: '';
                        position: absolute;
                        top: 0;
                        left: -100%;
                        width: 100%;
                        height: 100%;
                        background: linear-gradient(90deg,
                                        transparent,
                                        rgba(0, 123, 255, 0.1),
                                        transparent);
                        transition: 0.5s;
                }

                &:hover {
                  transform: translateY(-8px) rotateZ(1deg);
                        box-shadow: 0 8px 25px rgba(0, 123, 255, 0.3);
                        border-color: var(--primary-color) !important;

                        &::before {
                                left: 100%;
                        }

                        .card-icon {
                          animation: iconFloat 1.5s ease-in-out infinite;
                                transform: scale(1.1);
                                filter: drop-shadow(0 0 8px rgba(106, 183, 255, 0.8));
                        }
                }

                &.solved {
                        background: linear-gradient(135deg, rgba(106, 183, 255, 0.3), rgba(16, 42, 87, 0.7)) !important;
                        border-color: var(--primary-color) !important;

                        .card-icon {
                                color: var(--primary-color);
                        }
                }

                .challenge-inner {
                        padding: 20px;
                        position: relative;
                        z-index: 1;
                        height: 100%;
                        display: flex;
                        flex-direction: column;
                        align-items: center;
                        justify-content: center;
                }

                .card-badge {
                        position: absolute;
                        top: 10px;
                        right: -25px;
                        background: #e74c3c;
                        color: white;
                        padding: 4px 25px;
                        font-size: 12px;
                        transform: rotate(45deg);
                        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
                        z-index: 2;
                }

                .card-icon {
                        font-size: 2.8rem;
                        color: rgba(106, 183, 255, 0.8);
                        margin-bottom: 12px;
                        transition: all 0.3s;
                }

                .card-title {
                        margin: 8px 0;
                        font-size: 1.1rem;
                        font-weight: 600;
                        color: #fff;
                        text-align: center;
                        text-shadow: 0 0 8px rgba(106, 183, 255, 0.5);
                }

                .card-stats {
                        display: flex;
                        gap: 20px;
                        margin-top: auto;

                        .stat-item {
                                display: flex;
                                align-items: center;
                                font-size: 0.9rem;
                                color: #a8c6ff;

                                .el-icon {
                                        color: var(--primary-color);
                                        margin-right: 5px;
                                        font-size: 1.1rem;
                                }
                        }
                }
                &.el-button--primary {
    background: linear-gradient(135deg, rgba(106, 183, 255, 0.3), rgba(16, 42, 87, 0.7)) !important;
    border: 1px solid rgba(106, 183, 255, 0.5) !important;
    
    &:hover {
      box-shadow: 0 0 15px rgba(106, 183, 255, 0.4);
    }
  }

  // 已完成题目样式
  &.solved {
    background: linear-gradient(135deg, rgba(76, 175, 80, 0.3), rgba(16, 42, 87, 0.7)) !important;
    border: 1px solid rgba(76, 175, 80, 0.7) !important;
    
    .card-icon {
      color: #4CAF50 !important;
      filter: drop-shadow(0 0 8px rgba(76, 175, 80, 0.5));
    }

    &::after {
      content: '✓';
      position: absolute;
      top: 12px;
      right: 12px;
      color: #4CAF50;
      font-size: 1.4em;
      text-shadow: 0 0 8px rgba(76, 175, 80, 0.5);
    }

    &:hover {
      box-shadow: 0 0 15px rgba(76, 175, 80, 0.4);
    }
  }

  // 难度徽章颜色系统
  .card-badge {
    &[difficulty="简单"] { background: #4CAF50; }
    &[difficulty="中等"] { background: #FFC107; }
    &[difficulty="困难"] { background: #F44336; }
  }
        }

.main-dialog {
          
  /* 对话框整体样式 */
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.dialog-title {
  color: #2c3e50;
  text-align: center;
  margin: 0;
  padding: 20px 0;
  border-bottom: 1px solid #eee;
}

.dialog-content {
  padding: 20px 30px;
}

.description-container {
  position: relative;
  min-height: 80px;
  margin-bottom: 20px;
  padding: 15px;
  background: #f8f9fa;
  border-radius: 8px;
}

.description-text {
  color: #34495e;
  line-height: 1.6;
  margin: 0;
}

.author-info {
  position: absolute;
  left: 15px;
  bottom: 10px;
  color: #7f8c8d;
  font-size: 0.9em;
  font-style: italic;
  margin: 0;
}

.section-title {
  color: #2c3e50;
  font-size: 1.1em;
  margin: 20px 0 15px;
  padding-bottom: 8px;
  border-bottom: 2px solid #eee;
}

.target-info {
  padding: 20px 0;
  border-top: 1px solid #eee;
}
.button-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
  margin: 20px 0;
}

.hint-area {
  background: #fff9e6;
  border-radius: 6px;
  padding: 15px;
  margin-top: 20px;
}

.dialog-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 30px;
  border-top: 1px solid #eee;
}

.flag-input {
  flex-grow: 1;
  margin-right: 20px;
}

.submit-button {
  padding: 12px 30px;
  font-weight: bold;
  letter-spacing: 1px;
}
.operation-panel {
  background: rgba(245, 247, 250, 0.9);
  border-radius: 8px;
  padding: 15px;
  margin-top: 15px;
}

.url-container {
  margin-bottom: 15px;
  .access-url {
    font-size: 14px;
    i {
      margin-right: 8px;
    }
    &:hover {
      color: #6ab7ff;
      text-decoration: underline;
    }
  }
}

.action-buttons {
  display: flex;
  align-items: center;
  gap: 15px;

  .destroy-btn {
    background: rgba(255, 152, 0, 0.1);
    border-color: #ff9800;
    color: #ff9800;
    &:hover {
      background: rgba(255, 152, 0, 0.2);
    }
  }

  .extend-btn {
    background: rgba(106, 183, 255, 0.1);
    border-color: #6ab7ff;
    color: #6ab7ff;
    &:hover {
      background: rgba(106, 183, 255, 0.2);
    }
  }

  .countdown-container {
  margin-top: 15px;
  width: 200px;
  background: rgba(106, 183, 255, 0.1);
  border-radius: 8px;
  padding: 12px;
  border: 1px solid rgba(106, 183, 255, 0.2);
  animation: pulseGlow 2s infinite;

  .countdown-box {
    display: flex;
    align-items: center;
    color: #6ab7ff;

    i {
      font-size: 18px;
      margin-right: 10px;
    }

    .countdown-text {
      font-size: 16px;
      font-weight: bold;
      letter-spacing: 1px;
      text-shadow: 0 0 8px rgba(106, 183, 255, 0.3);
    }
  }

  .countdown-progress {
    margin-top: 8px;
    /deep/ .el-progress-bar__outer {
      background-color: rgba(106, 183, 255, 0.1);
    }
  }
}

@keyframes pulseGlow {
  0% { box-shadow: 0 0 0 0 rgba(106, 183, 255, 0.1); }
  50% { box-shadow: 0 0 0 4px rgba(106, 183, 255, 0.1); }
  100% { box-shadow: 0 0 0 0 rgba(106, 183, 255, 0.1); }
}
}



        /* 响应式调整 */
        @media (max-width: 992px) {
                .main-content {
                        flex-direction: column;
                }

                .category-sidebar {
                        width: 100%;
                        border-right: none;
                        border-bottom: 1px solid rgba(0, 123, 255, 0.2);
                }

                .button-grid {
                        grid-template-columns: repeat(2, 1fr) !important;
                }
        }

        @media (max-width: 576px) {
                .button-grid {
                        grid-template-columns: 1fr !important;
                }

                .page-title .title-text {
                        font-size: 1.8rem;
                }

                .challenge-card {
                        height: 140px;
                }
        }
}
</style>
<style lang="less">
::-webkit-scrollbar {
  display: none;
}
.main-dialog {
          
          /* 对话框整体样式 */
          border-radius: 12px;
          box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);

        }
        
        .dialog-title {
          color: #2c3e50;
          text-align: center;
          margin: 0;
          padding: 20px 0;
          border-bottom: 1px solid #eee;
        }
        
        .dialog-content {
          padding: 20px 30px;
        }
        
        .description-container {
          position: relative;
          min-height: 80px;
          margin-bottom: 20px;
          padding: 15px;
          background: #f8f9fa;
          border-radius: 8px;
        }
        
        .description-text {
          color: #34495e;
          line-height: 1.6;
          margin: 0;
        }
        
        .author-info {
          position: absolute;
          left: 15px;
          bottom: 10px;
          color: #7f8c8d;
          font-size: 0.9em;
          font-style: italic;
          margin: 0;
        }
        
        .section-title {
          color: #2c3e50;
          font-size: 1.1em;
          margin: 20px 0 15px;
          padding-bottom: 8px;
          border-bottom: 2px solid #eee;
        }
        
        .target-info {
          padding: 20px 0;
          border-top: 1px solid #eee;
        }
        .button-container {
          display: flex;
          justify-content: center;
          align-items: center;
          height: 100px;
          margin: 20px 0;
        }
        
        .hint-area {
          background: #fff9e6;
          border-radius: 6px;
          padding: 15px;
          margin-top: 20px;
        }
        
        .dialog-footer {
          display: flex;
          justify-content: space-between;
          align-items: center;
          padding: 15px 30px;
          border-top: 1px solid #eee;
        }
        
        .flag-input {
          flex-grow: 1;
          margin-right: 20px;
        }
        
        .submit-button {
          padding: 12px 30px;
          font-weight: bold;
          letter-spacing: 1px;
        }
        .operation-panel {
          background: rgba(245, 247, 250, 0.9);
          border-radius: 8px;
          padding: 15px;
          margin-top: 15px;
        }
        
        .url-container {
          margin-bottom: 15px;
          .access-url {
            font-size: 14px;
            i {
              margin-right: 8px;
            }
            &:hover {
              color: #6ab7ff;
              text-decoration: underline;
            }
          }
        }
        
        .action-buttons {
          display: flex;
          align-items: center;
          gap: 15px;
        
          .destroy-btn {
            background: rgba(255, 152, 0, 0.1);
            border-color: #ff9800;
            color: #ff9800;
            &:hover {
              background: rgba(255, 152, 0, 0.2);
            }
          }
        
          .extend-btn {
            background: rgba(106, 183, 255, 0.1);
            border-color: #6ab7ff;
            color: #6ab7ff;
            &:hover {
              background: rgba(106, 183, 255, 0.2);
            }
          }
        
          .countdown-container {
          margin-top: 15px;
          width: 200px;
          background: rgba(106, 183, 255, 0.1);
          border-radius: 8px;
          padding: 12px;
          border: 1px solid rgba(106, 183, 255, 0.2);
          animation: pulseGlow 2s infinite;
        
          .countdown-box {
            display: flex;
            align-items: center;
            color: #6ab7ff;
        
            i {
              font-size: 18px;
              margin-right: 10px;
            }
        
            .countdown-text {
              font-size: 16px;
              font-weight: bold;
              letter-spacing: 1px;
              text-shadow: 0 0 8px rgba(106, 183, 255, 0.3);
            }
          }
        
          .countdown-progress {
            margin-top: 8px;
            /deep/ .el-progress-bar__outer {
              background-color: rgba(106, 183, 255, 0.1);
            }
          }
        }
        
        @keyframes pulseGlow {
          0% { box-shadow: 0 0 0 0 rgba(106, 183, 255, 0.1); }
          50% { box-shadow: 0 0 0 4px rgba(106, 183, 255, 0.1); }
          100% { box-shadow: 0 0 0 0 rgba(106, 183, 255, 0.1); }
        }
        }


        /* 响应式调整 */
        @media (max-width: 992px) {
                .main-content {
                        flex-direction: column;
                }

                .category-sidebar {
                        width: 100%;
                        border-right: none;
                        border-bottom: 1px solid rgba(0, 123, 255, 0.2);
                }

                .button-grid {
                        grid-template-columns: repeat(2, 1fr) !important;
                }
        }

        @media (max-width: 576px) {
                .button-grid {
                        grid-template-columns: 1fr !important;
                }

                .page-title .title-text {
                        font-size: 1.8rem;
                }

                .challenge-card {
                        height: 140px;
                }
        }
</style>