<template>
        <div>
          <el-row>
            <el-col :span="19">
              <div ref="containerRef" style="color: #fff;height: 100%; overflow-y: auto">
                <div id="part1" style="height: 480px;">
                  <br>
                  <h1 class="title">从网页注释中获取</h1>
                  <el-divider />
                  <el-text size="large" style="color:#fff; line-height: 3.6;">
                    <p>有些时候,这类题目的形式往往让人摸不着头脑.它可能是一个熟悉的网站,比如教务系统；也可能是一个网页小游戏.</p>
                    <p>听起来好像很难拿到 <el-tag style="color:#1bac74" type="info" size="small">flag</el-tag></p>
                    <p>实则不然</p>
                    <p>这样的 <el-tag style="color:#1bac74" type="info" size="small">flag</el-tag>往往藏于注释中</p>
                    <p>你可以使用 <el-tag style="color:#fff" type="info" size="small">f12</el-tag>或者
                      <el-tag style="color:#fff" type="info" size="small">ctrl+u</el-tag>查看页面的源代码,也许
                      <el-tag style="color:#1bac74" type="info" size="small">flag</el-tag>就蕴藏其中
                    </p>
                  </el-text>
                  <el-divider />
                </div>
                <div id="part2" style="height: 300px;">
                  <el-text size="large" style="color:#fff; line-height: 3.6;">
                    <p>快看看页面中有没有惊喜</p>
                  </el-text>
                  <el-input v-model.trim="input" style="width: 240px" placeholder="找找看flag藏哪儿啦" />
                  &nbsp;
                  <el-button class="tech-submit-btn" type="primary" @click="submit" :round="true">
                    <span class="btn-content">
                      <i class="el-icon-upload2 btn-icon"></i>
                      <span class="btn-text">SUBMIT</span>
                    </span>
                  </el-button>
                </div>
              </div>
            </el-col>
            <el-col :span="5">
              <el-anchor :container="containerRef" direction="vertical" type="default" :offset="60"
                @click="handleClick" class="tech-anchor">
                <el-anchor-link>
                  <h3>本页目录</h3>
                </el-anchor-link>
                <el-anchor-link href="#part1" title="从网页注释中获取" />
              </el-anchor>
            </el-col>
          </el-row>
        </div>
      </template>
      
      <script>
      export default {
        data() {
          return {
            input: '',
            containerRef: null
          }
        },
        mounted() {
          this.containerRef = this.$refs.containerRef;
        },
        methods: {
          handleClick(e) {
            e.preventDefault();
          },
          submit() {
            console.log(this.input);
      
            if (this.input == 'cyrange{we1c03e-t0-Cyber-Range}') {
              this.$message({
                message: '恭喜,答对啦~',
                type: 'success',
              });
            } else {
              this.$message({
                message: '不对噢~',
                type: 'error',
              });
            }
          }
        }
      }
      </script>
      
      <style lang="less" scoped>
      h1.title {
        margin: 0 0 3rem;
        font-size: 38px;
        line-height: 1.4;
        letter-spacing: -.02em;
        font-weight: 800;
        text-align: left;
        font-family: 'Orbitron', '微软雅黑';
      }
      
      h2.sub-title {
        margin: 0 0 3rem;
        font-size: 25px;
        line-height: 1.4;
        letter-spacing: -.02em;
        font-weight: 800;
        text-align: left;
        font-family: 'Orbitron', '微软雅黑';
      }
      
      h3.sub-sub-title {
        margin: 0 0 3rem;
        font-size: 20px;
        line-height: 1.4;
        letter-spacing: -.02em;
        font-weight: 800;
        text-align: left;
        font-family: 'Orbitron', '微软雅黑';
      }
      
      .tech-submit-btn {
        border: none;
        padding: 12px 24px;
        font-weight: bold;
        letter-spacing: 1px;
        text-transform: uppercase;
        position: relative;
        overflow: hidden;
        transition: all 0.3s ease;
        display: inline-flex;
        align-items: center;
        justify-content: center;
        background: linear-gradient(135deg, #00a1ff 0%, #0066ff 50%, #0033cc 100%);
        color: white !important;
        box-shadow: 0 0 10px rgba(0, 150, 255, 0.5),
                    0 0 20px rgba(0, 100, 255, 0.3),
                    inset 0 0 5px rgba(255, 255, 255, 0.2);
      }
      
      .tech-submit-btn:hover {
        box-shadow: 0 0 15px rgba(0, 150, 255, 0.8),
                    0 0 30px rgba(0, 100, 255, 0.5),
                    inset 0 0 10px rgba(255, 255, 255, 0.3);
        transform: scale(1.05);
        background: linear-gradient(135deg, #00a1ff 0%, #0066ff 50%, #0033cc 100%);
      }
      
      .tech-submit-btn:active {
        transform: scale(0.98);
        box-shadow: 0 0 5px rgba(0, 150, 255, 0.8),
                    inset 0 0 10px rgba(0, 0, 0, 0.3);
      }
      
      .tech-submit-btn::before {
        content: '';
        position: absolute;
        top: -50%;
        left: -50%;
        width: 200%;
        height: 200%;
        background: linear-gradient(
          to bottom right,
          rgba(255, 255, 255, 0) 0%,
          rgba(255, 255, 255, 0.1) 50%,
          rgba(255, 255, 255, 0) 100%
        );
        transform: rotate(45deg);
        animation: shine 3s infinite;
        opacity: 0;
      }
      
      .tech-submit-btn:hover::before {
        opacity: 1;
      }
      
      .btn-content {
        position: relative;
        z-index: 1;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
      }
      
      .btn-icon {
        font-size: 16px;
        transition: all 0.3s ease;
        margin-right: 0px;
      }
      
      .btn-text {
        transition: all 0.3s ease;
      }
      
      .tech-submit-btn:hover .btn-icon {
        transform: translateY(-2px);
      }
      
      @keyframes shine {
        0% {
          left: -50%;
          top: -50%;
        }
        20% {
          left: 100%;
          top: 100%;
        }
        100% {
          left: 100%;
          top: 100%;
        }
      }
      
      .v {
        color: #4cbcec;
        font-family: 'Cascadia Code';
      }
      
      .key {
        color: #e1567b;
        font-family: 'Cascadia Code';
      }
      
      .lev0 {
        color: #f0de18;
        font-family: 'Cascadia Code';
      }
      
      .lev1 {
        color: rgb(238, 65, 195);
        font-family: 'Cascadia Code';
      }
      
      .lev2 {
        color: #239af5;
        font-family: 'Cascadia Code';
      }
      
      .func {
        color: #84D49C;
        font-family: 'Cascadia Code';
      }
      
      .txt {
        font-family: 'Cascadia Code';
      }
      
      .code {
        color: #fff;
        font-family: 'Cascadia Code';
      }
      
      .cmt {
        color: rgb(99, 97, 97);
        font-family: 'Cascadia Code';
        font-size: 15px
      }
      
      .num {
        color: #8e40ce;
        font-family: 'Cascadia Code';
      }
      
      .el-tag {
        background-color: #3d3b3b;
        color: #fff;
        border-color: #282C34;
      }
      
      .el-card {
        background-color: #282C34;
        color: #fff;
        border-color: #282C34;
      }
      
      .el-text {
        color: #fff;
      }
      
      .tech-anchor {
        --el-anchor-padding: 0 0 0 20px;
        --el-anchor-border-width: 0;
        --el-anchor-marker-height: 100%;
        --el-anchor-marker-width: 2px;
        background: rgba(10, 17, 40, 0.7);
        border-radius: 4px;
        padding: 15px;
        box-shadow: 0 0 15px rgba(0, 123, 255, 0.1);
        border-left: 1px solid rgba(0, 123, 255, 0.3);
      }
      
      .anchor-title {
        color: #d1e7ff !important;
        font-family: 'Cascadia Code', '微软雅黑';
        margin: 0 0 15px 0;
        padding-bottom: 10px;
        border-bottom: 1px solid rgba(0, 123, 255, 0.3);
        text-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
      }
      
      .tech-anchor /deep/ .el-anchor-link {
        margin: 8px 0;
      }
      
      .tech-anchor /deep/ .el-anchor-link__title {
        color: #a0c4ff !important;
        font-family: 'Cascadia Code', '微软雅黑';
        font-size: 0.9em;
        padding: 5px 10px;
        border-radius: 3px;
        transition: all 0.3s ease;
        position: relative;
      }
      
      .tech-anchor /deep/ .el-anchor-link__title:hover {
        color: #ffffff !important;
        background: rgba(0, 123, 255, 0.2);
        text-shadow: 0 0 5px #007bff;
        transform: translateX(5px);
      }
      
      .tech-anchor /deep/ .el-anchor-link__title:hover::before {
        content: '>';
        position: absolute;
        left: -10px;
        color: #00a2ff;
        opacity: 0;
        animation: blink-caret 0.8s step-end infinite alternate;
      }
      
      .tech-anchor /deep/ .el-anchor-link.active .el-anchor-link__title {
        color: #ffffff !important;
        background: rgba(0, 123, 255, 0.3);
        text-shadow: 0 0 10px #00a2ff;
        border-left: 2px solid #007bff;
        padding-left: 15px;
        animation: pulse-glow 2s infinite alternate;
      }
      
      .tech-anchor /deep/ .el-anchor-link .el-anchor-link {
        margin-left: 20px;
      }
      
      .tech-anchor /deep/ .el-anchor-link .el-anchor-link__title {
        font-size: 0.85em;
        color: #8ab4f8 !important;
      }
      
      .tech-anchor /deep/ .el-anchor__marker {
        background: linear-gradient(to bottom, transparent, #007bff, transparent);
        opacity: 0.7;
        width: 2px !important;
        left: 0 !important;
      }
      
      @media (max-width: 768px) {
        .tech-anchor {
          padding: 10px;
        }
      
        .tech-anchor /deep/ .el-anchor-link__title:hover::before {
          display: none;
        }
      }
      
      @keyframes blink-caret {
        0%, 100% {
          opacity: 1;
        }
        50% {
          opacity: 0;
        }
      }
      
      @keyframes pulse-glow {
        0% {
          text-shadow: 0 0 10px #00a2ff;
        }
        100% {
          text-shadow: 0 0 30px #00a2ff;
        }
      }
      </style>