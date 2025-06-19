<template>
  <el-header class="header-container">
    <el-menu
      :default-active="activeIndex"
      class="nav-menu"
      mode="horizontal"
      background-color="transparent"
      text-color="#d1e7ff"
      active-text-color="#6ab7ff"
      @select="handleSelect"
    >
      <!-- 左侧品牌和导航 -->
      <div class="header-left">
        <router-link to="/" class="brand-link" @click.native="goHome">
          <img src="@/assets/cyrange-logo.png" class="logo-img" alt="logo">
          <img src="@/assets/cyrange-logo4.png" class="brand-text" alt="brand">
        </router-link>

        <el-menu-item index="/" @click="goHome">
          <i class="el-icon-s-home"></i>
          <span class="nav-text">首页</span>
        </el-menu-item>
        
        <el-menu-item index="/vul" @click="goVuls">
          <i class="el-icon-warning-outline"></i>
          <span class="nav-text">漏洞原理</span>
        </el-menu-item>

        <el-menu-item index="/challenges" @click="goChallenges">
          <i class="el-icon-aim"></i>
          <span class="nav-text">练习场</span>
        </el-menu-item>
      </div>

      <!-- 右侧用户状态 -->
      <div class="header-right">
        <template v-if="isAuthenticated">
          <el-dropdown trigger="click">
            <el-button type="text" class="user-btn">
              <span class="user-name">{{ userName }}</span>
              <i class="el-icon-arrow-down el-icon--right"></i>
            </el-button>
            <el-dropdown-menu slot="dropdown" class="user-dropdown">
              <el-dropdown-item @click.native="goProfile">
                <i class="el-icon-user"></i>个人中心
              </el-dropdown-item>
              <el-dropdown-item @click.native="goSettings">
                <i class="el-icon-setting"></i>设置
              </el-dropdown-item>
              <el-dropdown-item divided @click.native="handleLogout">
                <i class="el-icon-switch-button"></i>退出登录
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>

        <template v-else>
          <el-button 
            type="text" 
            class="auth-btn"
            @click="openLogin"
          >
            <i class="el-icon-user"></i>
            登录
          </el-button>
          <el-divider direction="vertical"></el-divider>
          <el-button 
            type="text" 
            class="auth-btn"
            @click="openRegister"
          >
            <i class="el-icon-edit"></i>
            注册
          </el-button>
        </template>
      </div>
    </el-menu>
  </el-header>
</template>


<script>
import { mapState, mapActions } from 'vuex'

export default {
  computed: {
    ...mapState('auth',[ 'userName','isAuthenticated'])
  },
  mounted() {
    this.checkAuth()
    this.setupAutoLogout()
  },created() {
  this.$store.dispatch('auth/checkAuth'); // 触发检查 localStorage 的逻辑
},
  methods: {
    ...mapActions('auth',['logout', 'checkAuth']),

    // 自动登出检测
    setupAutoLogout() {
      setInterval(() => {
        if (this.isAuthenticated) {
          const exp = this.$store.state.auth.exp
          if (exp && Date.now() > exp * 1000) {
            this.logout()
            this.$message.warning('登录已过期，请重新登录')
            this.$router.push('/login')
          }
        }
      }, 60000) // 每分钟检查一次
    },

    openLogin() {
      this.$router.push('/login').catch(err => {
 if (err.name !== 'NavigationDuplicated') {
 throw err;
 }});
    },

    openRegister() {
      this.$router.push('/register').catch(err => {
         if (err.name !== 'NavigationDuplicated') {
                throw err;
     }});
    },

    goProfile() {
      this.$router.push('/profile').catch(err => {
    if (err.name !== 'NavigationDuplicated') {
   throw err;
    }});
    },

    async handleLogout() {
      await this.logout()
      this.$message.success('已退出登录')
      this.$router.push('/').catch(err => {
  if (err.name !== 'NavigationDuplicated') {
    throw err;
  }});
    },

    goSettings() {
      this.$router.push('/settings').catch(err => {
    if (err.name !== 'NavigationDuplicated') {
   throw err;
    }});
    },
     // 新增导航方法
     goHome() {
      this.$router.push('/')
    },
    goVuls() {
      this.$router.push('/vul')
    },
    goChallenges() {
      this.$router.push('/challenges')
    },
    goRanking() {
      this.$router.push('/ranking')
    },
    handleSelect(index) {
      this.activeIndex = index
    },
  }
}
</script>


<style lang="less" scoped>
.header-container {
  background: linear-gradient(to right, #0a1128, #001f3f, #007bff);
  border-bottom: 1px solid rgba(0, 123, 255, 0.3);
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.8);
  height: 60px;

  .nav-menu {
    border-bottom: none;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .header-left {
      display: flex;
      align-items: center;
      flex-grow: 1;

      .brand-link {
        display: flex;
        align-items: center;
        margin-right: 30px;
        
        .logo-img {
          width: 50px;
          margin-left: 8px;
        }
        .brand-text {
          width: 115px;
          margin-left: 8px;
        }
      }

      .el-menu-item {
        margin: 0 10px;
      }
    }

    .el-menu-item {
      height: 60px;
      line-height: 60px;
      color: #d1e7ff !important;
      font-family: 'Orbitron', '微软雅黑';
      transition: all 0.3s;
      background: transparent !important;

      &:hover {
        background: rgba(106, 183, 255, 0.1) !important;
        color: #fff !important;
        text-shadow: 0 0 10px #007bff;
      }

      &.is-active {
        border-bottom: 3px solid #6ab7ff !important;
        color: #fff !important;
        animation: pulse-glow 1.5s infinite alternate;
      }
    }
  }

  .header-right {
    display: flex;
    align-items: center;
    margin-left: auto;
    padding-right: 20px;

    .user-btn {
      color: #fff;
      display: flex;
      align-items: center;
      padding: 0 12px;

      .user-name {
        margin-right: 8px;
        font-family: 'Orbitron';
        font-size: 14px;
      }
    }

    .auth-btn {
      color: #d1e7ff;
      font-family: 'Orbitron';
      padding: 0 12px;

      &:hover {
        color: #6ab7ff;
        text-shadow: 0 0 10px #007bff;
      }
    }

    .el-divider--vertical {
      background-color: rgba(209, 231, 255, 0.3);
      height: 1.5em;
    }
  }
}

@keyframes pulse-glow {
  0% { text-shadow: 0 0 10px #00a2ff; }
  100% { text-shadow: 0 0 30px #00a2ff; }
}

.user-dropdown {
  background: #0a1929 !important;
  border: 1px solid #2a4d6e !important;
  min-width: 120px;

  .el-dropdown-menu__item {
    color: #fff !important;
    font-family: 'Orbitron';
    font-size: 13px;
    padding: 8px 16px;

    &:hover {
      background: rgba(106, 183, 255, 0.1) !important;
    }

    i {
      margin-right: 8px;
      color: #6ab7ff;
    }
  }

  // 修改分隔线样式
  .el-dropdown-menu__item--divided {
    &:before {
      background-color: rgba(106, 183, 255, 0.3) !important;
      margin: 0 12px;
      height: 1px;
    }
  }

  .popper__arrow {
    border-bottom-color: #2a4d6e !important;
    &::after {
      border-bottom-color: #0a1929 !important;
    }
  }
}
</style>