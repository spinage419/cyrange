<template>
    <div class="settings-container">
      <el-card class="profile-card" shadow="hover">
        <div class="user-avatar">
          <i class="el-icon-user-solid"></i>
        </div>
        <h2 class="profile-title">用户信息设置</h2>
        
        <el-form 
          :model="form" 
          :rules="rules" 
          ref="userForm"
          label-position="top"
          class="profile-form">
          <!-- 不可修改信息 -->
          <el-form-item label="用户ID">
            <el-input :value="userInfo.userId" disabled></el-input>
          </el-form-item>
  
          <!-- 可编辑信息 -->
          <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
  
          <el-form-item label="邮箱" prop="mail">
            <el-input v-model="form.mail"></el-input>
          </el-form-item>
  
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>
  
          <el-form-item>
            <el-button 
              type="primary" 
              @click="submitForm"
              :loading="loading">
              保存更改
            </el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script>
  
  export default {
    data() {
      // 验证规则
      const validatePhone = (rule, value, callback) => {
        if (value && !/^1[1-9]\d{9}$/.test(value)) {
          callback(new Error('请输入有效的手机号码'));
        } else {
          callback();
        }
      };
  
      return {
        userInfo: {
          userId: '',
          username: '',
          mail: '',
          phone: ''
        },
        form: {
          username: '',
          mail: '',
          phone: ''
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
          ],
          mail: [
            { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
          ],
          phone: [
            { validator: validatePhone, trigger: 'blur' }
          ]
        },
        loading: false
      };
    },
    created() {
      this.fetchUserInfo();
    },
    methods: {
      async fetchUserInfo() {
        try {
          const { data } = await this.$axios.get('/api/user/profile');
          this.userInfo = data.data;
          // 填充表单数据
          this.form = {
            username: data.data.username,
            mail: data.data.mail,
            phone: data.data.phone
          };
        } catch (error) {
          this.$message.error('获取用户信息失败');
        }
      },
      submitForm() {
        this.$refs.userForm.validate(async (valid) => {
          if (valid) {
            this.loading = true;
            try {
              const { data }=await this.$axios.put('/api/user/profile', this.form);
              if (data.code === 1) {
                this.$message.success('信息更新成功');
                this.fetchUserInfo(); // 刷新最新数据
        } else {
          this.$message.error(data.msg);
          this.fetchUserInfo();
        }
            } catch (error) {
              this.$message.error('更新失败');
            } finally {
              this.loading = false;
            }
          }
        });
      }
    }
  };
  </script>
  
  <style lang="less" scoped>
  @primary-color: #409EFF;
  @card-bg: #1f2d3d;
  
  .settings-container {
    padding: 20px;
    min-height: 100vh;
    background: linear-gradient(145deg, #0f172a, #1e293b);
    
    .profile-card {
      max-width: 600px;
      margin: 20px auto;
      background: @card-bg;
      border: 1px solid #304156;
      
      /deep/ .el-card__body {
        padding: 40px;
      }
    }
  
    .user-avatar {
      text-align: center;
      margin-bottom: 20px;
      
      i {
        font-size: 80px;
        color: @primary-color;
        background: rgba(255,255,255,0.1);
        border-radius: 50%;
        padding: 20px;
      }
    }
  
    .profile-title {
      color: #fff;
      text-align: center;
      margin-bottom: 30px;
      font-weight: 500;
    }
  
    .profile-form {
      /deep/ .el-form-item__label {
        color: #fff;
      }
  
      /deep/ .el-input__inner {
        background: #2d3a4b;
        border: 1px solid #3b4958;
        color: #fff;
      }
    }
  }
  </style>