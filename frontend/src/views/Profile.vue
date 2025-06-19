<template>
    <div class="profile-container">
      <!-- 用户基本信息 -->
      <div class="user-info-card cyber-card">
      <div class="avatar-section">
        <el-avatar :size="80" :src="userInfo.avatar" class="cyber-avatar"></el-avatar>
      </div>
      <div class="info-section">
        <h2 class="cyber-title">{{ userInfo.username }}</h2>
        <p class="cyber-text">用户ID: {{ userInfo.userId }}</p>
        <p class="cyber-text">总得分: <span class="score">{{ userInfo.totalScore }}</span></p>
      </div>
    </div>
  
      <!-- 统计图表 -->
      <div class="chart-container">
        <div class="chart-item">
          <h3>题目分类分布</h3>
          <div ref="pieChart" style="height: 300px;"></div>
        </div>
        <div class="chart-item">
          <h3>解题时间趋势</h3>
          <div ref="lineChart" style="height: 300px;"></div>
        </div>
      </div>
  
      <!-- 题目表格 -->
      <div class="submission-table">
        <el-table
          :data="tableData"
          stripe
          style="width: 100%">
          <el-table-column prop="challengeName" label="题目名称"></el-table-column>
          <el-table-column prop="categoryName" label="分类"></el-table-column>
          <el-table-column prop="point" label="分数" width="100"></el-table-column>
          <el-table-column prop="finishTime" label="解题时间" width="180"></el-table-column>
        </el-table>
  
        <el-pagination
          @current-change="handlePageChange"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="total"
          layout="total, prev, pager, next, jumper">
        </el-pagination>
      </div>
    </div>
  </template>
  
  <script>
  import * as echarts from 'echarts'
  import { getUserProfile, getSolveStats, getSubmissions } from '@/api/user'
  
  export default {
    data() {
      return {
        userInfo: {
          nickname: '',
          userId: '',
          totalScore: 0,
          avatar: require('@/assets/default-avatar.png')
        },
        pieChart: null,
        lineChart: null,
        tableData: [],
        currentPage: 1,
        pageSize: 10,
        total: 0
      }
    },
  
    async mounted() {
      await this.loadUserProfile()
      await this.loadChartData()
      await this.loadSubmissionData()
    },
  
    methods: {
      async loadUserProfile() {
        try {
          const res = await getUserProfile()
          this.userInfo = { ...this.userInfo, ...res.data }
        } catch (error) {
          console.error(error)
        }
      },
  
      async loadChartData() {
        try {
          const res = await getSolveStats()
          this.initPieChart(res.data.categoryDistribution)
          this.initLineChart(res.data.timeTrend)
        } catch (error) {
          console.error(error)
        }
      },
  
      initPieChart(data) {
        this.pieChart = echarts.init(this.$refs.pieChart)
        const option = {
          tooltip: { trigger: 'item' },
          series: [{
            type: 'pie',
            radius: '60%',
            data: data,
            emphasis: { itemStyle: { shadowBlur: 10 } }
          }]
        }
        this.pieChart.setOption(option)
      },
  
      initLineChart(data) {
        this.lineChart = echarts.init(this.$refs.lineChart)
        const option = {
          xAxis: { type: 'category', data: data.dates },
          yAxis: { type: 'value' },
          series: [{
            data: data.scores,
            type: 'line',
            smooth: true
          }]
        }
        this.lineChart.setOption(option)
      },
  
      async loadSubmissionData() {
        try {
          const params = {
            page: this.currentPage,
            pageSize: this.pageSize
          }
          const res = await getSubmissions(params)
          this.tableData = res.data.records
          this.total = res.data.total
        } catch (error) {
          console.error(error)
        }
      },
  
      handlePageChange(page) {
        this.currentPage = page
        this.loadSubmissionData()
      }
    }
  }
  </script>
  
<style lang="less" scoped>
@keyframes neonPulse {
  0% { opacity: 0.8; }
  50% { opacity: 1; }
  100% { opacity: 0.8; }
}

.profile-container {
  max-width: 1200px;
  margin: 20px auto;
  padding: 20px;
  position: relative;
  z-index: 2;
  
  .cyber-card {
    background: rgba(8, 16, 24, 0.85);
    border: 1px solid rgba(0, 123, 255, 0.4);
    border-radius: 8px;
    box-shadow: 0 0 30px rgba(0, 123, 255, 0.2),
                inset 0 0 20px rgba(0, 123, 255, 0.1);
    padding: 20px;
    margin-bottom: 30px;
    position: relative;
    overflow: hidden;

    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: linear-gradient(rgba(0, 123, 255, 0.03) 1px, transparent 1px),
                  linear-gradient(90deg, rgba(0, 123, 255, 0.03) 1px, transparent 1px);
      background-size: 15px 15px;
    }
  }

  .user-info-card {
    display: flex;
    align-items: center;
    animation: neonPulse 3s infinite;

    .cyber-avatar {
      border: 2px solid #6ab7ff;
      box-shadow: 0 0 15px rgba(106, 183, 255, 0.5);
      margin-right: 30px;
    }

    .info-section {
      .cyber-title {
        color: #6ab7ff;
        text-shadow: 0 0 20px rgba(106, 183, 255, 0.5);
        font-family: 'Orbitron', sans-serif;
        margin: 0 0 15px;
      }

      .cyber-text {
        color: #d1e7ff;
        font-family: 'Cascadia Code', monospace;
        margin: 8px 0;
        
        .score {
          color: #34fe97;
          text-shadow: 0 0 10px rgba(52, 254, 151, 0.3);
        }
      }
    }
  }

  .chart-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 20px;

    .chart-item {
      .cyber-card();
      
      h3 {
        color: #6ab7ff;
        font-family: 'Orbitron', sans-serif;
        border-bottom: 1px solid rgba(0, 123, 255, 0.3);
        padding-bottom: 10px;
      }
    }
  }

  .submission-table {
  .cyber-card();
  
  ::v-deep .el-table {
    background: transparent;
    --el-table-border-color: rgba(0, 123, 255, 0.2);
    --el-table-header-bg-color: rgba(0, 123, 255, 0.15);
    --el-table-tr-bg-color: rgba(16, 32, 48, 0.5);
    --el-table-row-hover-bg-color: rgba(0, 123, 255, 0.1);
    --el-table-current-row-bg-color: rgba(0, 123, 255, 0.2);

    th {
      background: rgba(0, 123, 255, 0.15) !important;
      color: #6ab7ff !important;
      font-family: 'Orbitron', sans-serif;
      font-size: 14px;
      border-bottom: 1px solid rgba(0, 123, 255, 0.3) !important;
    }

    tr {
      background: rgba(16, 32, 48, 0.5) !important;
      color: #d1e7ff !important;
      
      &:hover td {
        background: rgba(0, 123, 255, 0.1) !important;
      }
    }

    td {
      border-bottom: 1px solid rgba(0, 123, 255, 0.1) !important;
      font-family: 'Cascadia Code', monospace;
    }

    &.el-table--striped {
      .el-table__body {
        tr.el-table__row--striped {
          background: rgba(16, 32, 48, 0.5) !important;
          
          td {
            background: rgba(16, 32, 48, 0.5) !important;
          }
        }
      }
    }
  }

  /* 分页器暗色风格 */
  ::v-deep .el-pagination {
    margin-top: 20px;
    justify-content: flex-end;

    .btn-prev,
    .btn-next,
    .number,
    .more {
      background: rgba(16, 32, 48, 0.8) !important;
      color: #6ab7ff !important;
      border: 1px solid rgba(0, 123, 255, 0.3) !important;
      margin: 0 3px;
      min-width: 32px;
      border-radius: 4px;
      transition: all 0.3s;
      
      &:hover {
        background: rgba(0, 123, 255, 0.2) !important;
        color: #34fe97 !important;
        border-color: #34fe97 !important;
      }
    }

    .active {
      background: #6ab7ff !important;
      color: #0a1128 !important;
      font-weight: bold;
      border-color: #6ab7ff !important;
    }

    .el-pagination__total,
    .el-pagination__jump {
      color: #d1e7ff !important;
      font-family: 'Cascadia Code', monospace;
    }

    .el-input__inner {
      background: rgba(16, 32, 48, 0.8) !important;
      border: 1px solid rgba(0, 123, 255, 0.3) !important;
      color: #6ab7ff !important;
      
      &:focus {
        border-color: #34fe97 !important;
      }
    }

    .el-select .el-input {
      .el-input__inner {
        background: rgba(16, 32, 48, 0.8) !important;
        border: 1px solid rgba(0, 123, 255, 0.3) !important;
        color: #6ab7ff !important;
      }
    }
  }
}
}

@media (max-width: 768px) {
  .profile-container {
    padding: 10px;
    
    .chart-container {
      grid-template-columns: 1fr;
    }
    
    .user-info-card {
      flex-direction: column;
      text-align: center;
      
      .avatar-section {
        margin: 0 0 15px 0;
      }
    }
  }
}
</style>