<template>
    <div class="auth-container">
      <div class="auth-card">
        <h2 class="auth-title">用户注册</h2>
        <el-form 
          :model="registerForm" 
          :rules="registerRules" 
          ref="registerForm" 
          @submit.native.prevent="handleRegister"
        >
          <!-- 用户名 -->
          <el-form-item prop="username">
            <el-input
              v-model="registerForm.username"
              placeholder="请输入用户名"
              prefix-icon="el-icon-user"
              clearable
            ></el-input>
          </el-form-item>
  
          <!-- 邮箱 -->
          <el-form-item prop="email">
            <el-input
              v-model="registerForm.email"
              placeholder="请输入邮箱"
              prefix-icon="el-icon-message"
              clearable
            ></el-input>
          </el-form-item>
  
          <!-- 密码 -->
          <el-form-item prop="password">
            <el-input
              v-model="registerForm.password"
              type="password"
              placeholder="请输入密码"
              prefix-icon="el-icon-lock"
              clearable
              show-password
            ></el-input>
            <div class="password-strength">
              <span :class="strengthClass">{{ passwordStrength }}</span>
            </div>
          </el-form-item>
  
          <!-- 确认密码 -->
          <el-form-item prop="confirmPassword">
            <el-input
              v-model="registerForm.confirmPassword"
              type="password"
              placeholder="请确认密码"
              prefix-icon="el-icon-lock"
              clearable
              show-password
            ></el-input>
          </el-form-item>
  
          <!-- 验证码 -->
          <el-form-item prop="captcha">
            <div class="captcha-wrapper">
              <el-input
                v-model="registerForm.captcha"
                placeholder="请输入验证码"
                prefix-icon="el-icon-key"
                style="width: 60%"
                maxlength="4"
              ></el-input>
              <div class="captcha-img-wrapper" @click="refreshCaptcha">
                <img 
                  v-if="captchaImage" 
                  :src="captchaImage" 
                  class="captcha-img"
                  alt="验证码"
                >
                <div v-else class="captcha-loading">
                  <i class="el-icon-loading"></i>
                </div>
              </div>
            </div>
          </el-form-item>
  
          <!-- 注册按钮 -->
          <el-form-item>
            <el-button 
              type="primary" 
              native-type="submit" 
              class="auth-button"
              :loading="loading"
            >
              立即注册
            </el-button>
          </el-form-item>
        </el-form>
  
        <div class="auth-footer">
          <span>已有账号？</span>
          <el-link type="primary" @click="goLogin">立即登录</el-link>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      const validatePassword = (rule, value, callback) => {
        if (value !== this.registerForm.password) {
          callback(new Error('两次输入密码不一致'))
        } else {
          callback()
        }
      }
  
      return {
        registerForm: {
          username: '',
          email: '',
          password: '',
          confirmPassword: '',
          captcha: ''
        },
        registerRules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 16, message: '长度在3到16个字符', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '请输入邮箱', trigger: 'blur' },
            { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, message: '密码长度至少6位', trigger: 'blur' },
            { validator: this.checkPasswordStrength, trigger: 'blur' }
          ],
          confirmPassword: [
            { required: true, message: '请确认密码', trigger: 'blur' },
            { validator: validatePassword, trigger: 'blur' }
          ],
          captcha: [
            { required: true, message: '请输入验证码', trigger: 'blur' },
            { pattern: /^[A-Za-z0-9]{4}$/, message: '验证码格式不正确' }
          ]
        },
        captchaImage: '',
        captchaToken: '',
        loading: false,
        passwordStrength: '弱',
        strengthClass: 'weak'
      }
    },
    mounted() {
      this.refreshCaptcha()
    },
    methods: {
      // 密码强度检测
      checkPasswordStrength(rule, value, callback) {
        let strength = 0
        if (value.length >= 6) strength++
        if (/[A-Z]/.test(value)) strength++
        if (/[0-9]/.test(value)) strength++
        if (/[^A-Za-z0-9]/.test(value)) strength++
  
        switch(strength) {
          case 0:
          case 1:
            this.passwordStrength = '弱'
            this.strengthClass = 'weak'
            break
          case 2:
            this.passwordStrength = '中'
            this.strengthClass = 'medium'
            break
          case 3:
          case 4:
            this.passwordStrength = '强'
            this.strengthClass = 'strong'
        }
        callback()
      },
  
      // 刷新验证码
      async refreshCaptcha() {
        try {
          const { data } = await this.$axios.get('api/captcha', {
          })
          this.captchaImage = 'data:image/png;base64,' + data.data.image
          this.captchaToken = data.data.token
        } catch (error) {
          this.$message.error('获取验证码失败')
        }
      },
  
      // 处理注册
      async handleRegister() {
        try {
          this.loading = true
          await this.$refs.registerForm.validate()
  
          // 调用注册接口
          const { data } = await this.$axios.post('/api/register', {
            ...this.registerForm,
            captchaToken: this.captchaToken
          })
          if (data.code === 1) {
            this.$message.success('注册成功')
            this.$router.push('/login')
        } else {
          this.$message.error(data.msg)
          this.refreshCaptcha()
        }
        } catch (error) {
          if (error.response) {
            this.$message.error(error.response.data.message)
            this.refreshCaptcha()
          }
        } finally {
          this.loading = false
        }
      },
  
      goLogin() {
        this.$router.push('/login')
      }
    }
  }
  </script>
  
  <style lang="less" scoped>

.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: radial-gradient(circle at center, #001520, #000);
}

.auth-card {
  width: 400px;
  padding: 40px;
  background: rgba(255, 255, 255, 0.05);
  border-radius: 10px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
}

.auth-title {
  text-align: center;
  color: #fff;
  margin-bottom: 30px;
  font-size: 24px;
  font-weight: bold;
}

.captcha-wrapper {
  display: flex;
  justify-content: space-between;
  width: 100%;

  .captcha-img-wrapper {
    width: 35%;
    height: 40px;
    border-radius: 4px;
    overflow: hidden;
    cursor: pointer;
    background: rgba(255, 255, 255, 0.1);
    display: flex;
    align-items: center;
    justify-content: center;

    .captcha-img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }

    .captcha-loading {
      color: #fff;
      font-size: 20px;
    }
  }
}

.auth-button {
  width: 100%;
  height: 45px;
  margin-top: 20px;
  background: linear-gradient(45deg, #6ab7ff, #3480fe);
  border: none;
  transition: all 0.3s;

  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 5px 15px rgba(106, 183, 255, 0.3);
  }
}

.auth-footer {
  text-align: center;
  color: rgba(255, 255, 255, 0.7);
  margin-top: 20px;

  .el-link {
    margin-left: 10px;
  }
}

::v-deep .el-input__inner {
  background: rgba(255, 255, 255, 0.05);
  border-color: rgba(255, 255, 255, 0.2);
  color: #fff;

  &:focus {
    border-color: #6ab7ff;
  }
}
  .password-strength {
    margin-top: 8px;
    font-size: 12px;
  
    span {
      padding: 2px 8px;
      border-radius: 4px;
  
      &.weak {
        color: #ff4d4f;
        background: rgba(255, 77, 79, 0.1);
      }
      &.medium {
        color: #faad14;
        background: rgba(250, 173, 20, 0.1);
      }
      &.strong {
        color: #52c41a;
        background: rgba(82, 196, 26, 0.1);
      }
    }
  }
  </style>