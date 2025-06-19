<template>
  <div class="common-layout">
    <el-main>
      <div class="title-section">
        <h1 class="cyber-title">
          <span class="title-text">CYBER</span>
          <span class="title-accent">RANGE</span>
          <span class="title-cursor">_</span>
        </h1>
        <p class="subtitle">网络安全实战训练平台</p>
      </div>

      <!-- 增强版数据看板 -->
      <div class="cyber-station">
        <div class="terminal-hud">
          <div class="hud-grid">
            <div v-for="stat in stats" :key="stat.id" class="data-node"
                 :class="`node-${stat.id}`">
              <div class="node-header">
                <div class="node-icon">
                  <div class="node-pulse"></div>
                  <i :class="stat.iconClass"></i>
                </div>
                <div class="node-label">{{ stat.label }}</div>
              </div>
              <div class="node-value">
                <animated-number 
                  :value="stat.value" 
                  :duration="1800"
                  :formatValue="formatNumber" />
              </div>
              <div class="node-connector">
                <div class="connector-line"></div>
                <div class="connector-dot"></div>
              </div>
            </div>
          </div>
          <div class="terminal-footer">
            <div class="scanline"></div>
            <div class="status-text">SYSTEM STATUS:
              <span class="status-active">●&nbsp;Active(Running)</span>
            </div>
          </div>
        </div>
      </div>
    </el-main>
  </div>
</template>

<script>
import AnimatedNumber from 'animated-number-vue';

export default {
  components: {
    AnimatedNumber
  },
  data() {
    return {
      stats: [
        { id: 1, iconClass: 'el-icon-view', value: 0, label: "- 今日挑战 -" },
        { id: 2, iconClass: 'el-icon-trophy', value: 0, label: "- 夺旗成功 -" },
        { id: 3, iconClass: 'el-icon-user', value: 0, label: "- 在线用户 -" }
      ],
      isLoading: true,
      error: null,
      timer: null
    }
  },
  methods: {
    formatNumber(value) {
      return Math.floor(value).toLocaleString();
    },
    async fetchStats() {
      try {
        const response = await this.$axios.get('/api/stats');
        if (response.data.code === 1) {
          const { todayChallenges, successfulBreaches, onlineUsers } = response.data.data;
          this.stats = [
            { id: 1, iconClass: 'el-icon-view', value: todayChallenges, label: "今日挑战" },
            { id: 2, iconClass: 'el-icon-trophy', value: successfulBreaches, label: "成功攻破" },
            { id: 3, iconClass: 'el-icon-user', value: onlineUsers, label: "在线用户" }
          ];
        } else {
          this.error = "获取数据失败";
        }
      } catch (err) {
        this.error = "网络请求失败,请稍后重试";
        console.error("获取统计数据失败:", err);
      } finally {
        this.isLoading = false;
      }
    }
  },
  mounted() {
    this.fetchStats();
    this.timer = setInterval(this.fetchStats, 60000);
  },
  beforeDestroy() {
    clearInterval(this.timer);
  }
}
</script>


<style lang="less" scoped>
.common-layout {
  min-height: 100vh; // 确保布局至少占满整个视口高度

}
.title-section {
        text-align: center;
        margin: 4rem 0;
        position: relative;
        overflow: hidden;

        &::before {
                content: '';
                position: absolute;
                top: 0;
                left: 50%;
                transform: translateX(-50%);
                width: 80%;
                height: 1px;
                background: linear-gradient(90deg, transparent, rgba(0, 123, 255, 0.6), transparent);
                animation: scanline 6s linear infinite;
                opacity: 0.3;
        }

        .cyber-title {
                font-family: 'Orbitron', sans-serif;
                font-style: italic;
                font-size: 4.5rem;
                margin-bottom: 1.5rem;
                letter-spacing: 2px;
                position: relative;
                display: inline-block;
                padding: 0 2rem;

                &::after {
                        content: '';
                        position: absolute;
                        bottom: -10px;
                        left: 50%;
                        transform: translateX(-50%);
                        width: 60%;
                        height: 2px;
                        background: linear-gradient(90deg, transparent, #007bff, transparent);
                }

                .title-text {
                        color: #6ab7ff;
                        text-shadow: 0 0 20px rgba(106, 183, 255, 0.5);
                        position: relative;
                        animation: text-flicker 3s infinite alternate;
                }

                .title-accent {
                        color: #34fe97;
                        margin-left: 1rem;
                        text-shadow: 0 0 20px rgba(52, 254, 151, 0.5);
                        position: relative;
                        animation: text-flicker 3s infinite alternate-reverse;
                }

                .title-cursor {
                        color: #34fe97;
                        animation: blink-caret 0.8s step-end infinite;
                        margin-left: 5px;
                        font-weight: normal;
                }
        }

        .subtitle {
                color: #d1e7ff;
                font-size: 1.4rem;
                letter-spacing: 4px;
                margin-bottom: 3rem;
                text-shadow: 0 0 10px rgba(209, 231, 255, 0.3);
                font-family: 'Orbitron', sans-serif;
                position: relative;

                &::before,
                &::after {
                        content: '//';
                        color: rgba(0, 123, 255, 0.5);
                        margin: 0 15px;
                        font-family: 'Courier New', monospace;
                }
        }
}

.cyber-station {
        max-width: 1200px;
        margin: 3rem auto;
        padding: 0 2rem;
        perspective: 1000px;

        @media (max-width: 768px) {
                padding: 0 1rem;
                margin: 2rem auto;
        }
}

.terminal-hud {
        background: rgba(8, 16, 24, 0.85);
        border: 1px solid rgba(0, 123, 255, 0.4);
        border-radius: 8px;
        box-shadow:
                0 0 30px rgba(0, 123, 255, 0.2),
                inset 0 0 20px rgba(0, 123, 255, 0.1);
        overflow: hidden;
        position: relative;

        &::before {
                content: '';
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background:
                        linear-gradient(rgba(0, 123, 255, 0.03) 1px, transparent 1px),
                        linear-gradient(90deg, rgba(0, 123, 255, 0.03) 1px, transparent 1px);
                background-size: 15px 15px;
                pointer-events: none;
        }
}

.hud-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
        gap: 1px;
        background: rgba(0, 123, 255, 0.15);

        @media (max-width: 768px) {
                grid-template-columns: 1fr;
        }
}

.data-node {
        padding: 1.8rem;
        background: rgba(11, 22, 34, 0.6);
        position: relative;
        transition: all 0.4s ease;
        overflow: hidden;

        &:hover {
                background: rgba(16, 32, 48, 0.7);
                transform: translateY(-3px);

                .node-value {
                        text-shadow: 0 0 15px rgba(52, 254, 151, 0.5);
                }

                .node-connector .connector-line {
                        background: #34fe97;
                        box-shadow: 0 0 10px rgba(52, 254, 151, 0.5);
                }
        }

        &.node-1 {
                border-top: 1px solid rgba(106, 183, 255, 0.3);
        }

        &.node-2 {
                border-top: 1px solid rgba(52, 254, 151, 0.3);
        }

        &.node-3 {
                border-top: 1px solid rgba(255, 107, 107, 0.3);
        }
}

.node-header {
        display: flex;
        align-items: center;
        margin-bottom: 1.2rem;
}

.node-icon {
        position: relative;
        width: 40px;
        height: 40px;
        margin-right: 1rem;
        display: flex;
        align-items: center;
        justify-content: center;

        .icon-svg {
                position: relative;
                width: 1.8em;
                height: 1.8em;
                z-index: 2;
        }

        .node-pulse {
                position: absolute;
                width: 100%;
                height: 100%;
                background: rgba(106, 183, 255, 0.2);
                border-radius: 50%;
                animation: pulse 3s infinite;
                z-index: 1;
        }
}

.node-1 .node-icon {
        .icon-svg {
                color: #6ab7ff;
        }

        .node-pulse {
                background: rgba(106, 183, 255, 0.2);
        }
}

.node-2 .node-icon {
        .icon-svg {
                color: #34fe97;
        }

        .node-pulse {
                background: rgba(52, 254, 151, 0.2);
        }
}

.node-3 .node-icon {
        .icon-svg {
                color: #ff6b6b;
        }

        .node-pulse {
                background: rgba(255, 107, 107, 0.2);
        }
}

.node-label {
        font-family: 'Courier New', monospace;
        color: #d1e7ff;
        font-size: 0.95rem;
        letter-spacing: 2px;
        text-transform: uppercase;
        opacity: 0.9;
}

.node-value {
        font-family: 'Orbitron', sans-serif;
        font-size: 2.8rem;
        font-weight: bold;
        color: #6ab7ff;
        text-shadow: 0 0 10px rgba(106, 183, 255, 0.3);
        line-height: 1;
        margin-left: 50px;
        transition: all 0.3s ease;
}

.node-2 .node-value {
        color: #34fe97;
        text-shadow: 0 0 10px rgba(52, 254, 151, 0.3);
}

.node-3 .node-value {
        color: #ff6b6b;
        text-shadow: 0 0 10px rgba(255, 107, 107, 0.3);
}

.node-connector {
        position: absolute;
        bottom: 0;
        left: 0;
        width: 100%;
        height: 2px;

        .connector-line {
                height: 100%;
                width: 100%;
                background: rgba(0, 123, 255, 0.5);
                transform-origin: left center;
                transform: scaleX(0.7);
                transition: all 0.4s ease;
        }

        .connector-dot {
                position: absolute;
                right: 10%;
                top: 50%;
                transform: translateY(-50%);
                width: 6px;
                height: 6px;
                border-radius: 50%;
                background: #6ab7ff;
                box-shadow: 0 0 8px rgba(106, 183, 255, 0.5);
        }
}

.node-2 .connector-dot {
        background: #34fe97;
        box-shadow: 0 0 8px rgba(52, 254, 151, 0.5);
}

.node-3 .connector-dot {
        background: #ff6b6b;
        box-shadow: 0 0 8px rgba(255, 107, 107, 0.5);
}

.terminal-footer {
        position: relative;
        padding: 1rem 2rem;
        background: rgba(0, 20, 40, 0.5);
        border-top: 1px solid rgba(0, 123, 255, 0.2);
        font-family: 'Courier New', monospace;
        color: #6ab7ff;
        font-size: 0.9rem;
        letter-spacing: 1px;

        .scanline {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 1px;
                background: linear-gradient(90deg, transparent, rgba(0, 123, 255, 0.8), transparent);
                animation: scanline 5s linear infinite;
        }

        .status-text {
                .status-active {
                        color: #34fe97;
                        text-shadow: 0 0 8px rgba(52, 254, 151, 0.3);
                        animation: text-flicker 4s infinite;
                }
        }
}

@keyframes pulse {
        0% {
                transform: scale(0.8);
                opacity: 0.6;
        }

        50% {
                transform: scale(1.2);
                opacity: 0.2;
        }

        100% {
                transform: scale(0.8);
                opacity: 0.6;
        }
}

@keyframes scanline {
        0% {
                transform: translateY(-100%);
        }

        100% {
                transform: translateY(100%);
        }
}

@keyframes text-flicker {

        0%,
        19%,
        21%,
        23%,
        25%,
        54%,
        56%,
        100% {
                opacity: 1;
                text-shadow: 0 0 8px currentColor;
        }

        20%,
        24%,
        55% {
                opacity: 0.7;
                text-shadow: none;
        }
}

@keyframes blink-caret {

        0%,
        100% {
                opacity: 1;
        }

        50% {
                opacity: 0;
        }
}

@media (max-width: 768px) {
        .title-section {
                margin: 2rem 0;

                .cyber-title {
                        font-size: 2.5rem;
                        padding: 0 1rem;
                }

                .subtitle {
                        font-size: 1rem;
                        letter-spacing: 2px;

                        &::before,
                        &::after {
                                margin: 0 8px;
                        }
                }
        }
}
</style>