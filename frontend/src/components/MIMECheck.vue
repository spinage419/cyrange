<template>
        <div>
          <el-row>
            <el-col :span="19">
              <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto" @scroll="handleScroll">
                <div id="part0" style="height: 100px;"></div>
                <div id="part2" style="height: 650px;">
                  <el-text size="large" style="color:#fff; line-height: 3.6;">
                    <div id="p2" style="height: 1150px;">
                      <h1 class="title">MIME类型检验绕过</h1>
                      <el-divider />
                      <h2 id="p2-0" class="sub-title">MIME类型</h2>
                      在这里,我们需要明确两个概念,分别是<el-tag
                        style="color:#0b915d" type="info"
                        size="default">MIME类型</el-tag>和<el-tag
                        style="color:#0b915d" type="info"
                        size="default">content-type</el-tag>:<br>
                      <el-tag
                        style="color:#0b915d" type="info"
                        size="default">MIME类型</el-tag>是一种标准,用于标识文件的类型和格式,由表示文件类别的类型和表示具体格式的子类型两部分组成<br>这两部分通过<el-tag
                        style="color:#e1567b" type="info"
                        size="default">/</el-tag>分开<br>
                      比如<el-tag
                        style="color:#e1567b" type="info"
                        size="default">image/jpeg</el-tag>中的<el-tag
                        style="color:#e1567b" type="info"
                        size="default">image</el-tag>为类型,<el-tag
                        style="color:#e1567b" type="info"
                        size="default">jpeg</el-tag>为子类型
                      <br>
                      <br>
      
                      <h2 id="p2-1" class="sub-title">content-type</h2>
                      <el-tag
                        style="color:#0b915d" type="info"
                        size="default">content-type</el-tag>:是<el-tag
                        style="color:#0b915d" type="info"
                        size="default">HTTP</el-tag>协议中的一个头部字段,用于指示资源的<el-tag
                        style="color:#0b915d" type="info"
                        size="default">MIME类型</el-tag><br>
                      它是<el-tag
                        style="color:#0b915d" type="info"
                        size="default">HTTP</el-tag>请求或响应的一部分,用于告诉客户端或服务器资源的类型<br>我们可以通过<el-tag
                        style="color:#fff" type="info"
                        size="default">f12</el-tag>或者<el-tag
                        style="color:#0b915d" type="info"
                        size="default">BurpSuite</el-tag>抓包,查看请求头和响应头中的<el-tag
                        style="color:#0b915d" type="info"
                        size="default">content-type</el-tag>
                      <br>
                      <br>
      
                      <h2 id="p2-2" class="sub-title">漏洞产生原因</h2>
                      文件上传功能仅根据<el-tag
                        style="color:#0b915d" type="info"
                        size="default">HTTP</el-tag>请求的<el-tag
                        style="color:#0b915d" type="info"
                        size="default">content-type</el-tag>字段进行文件类型校验<br>
                      但是<el-tag
                        style="color:#0b915d" type="info"
                        size="default">content-type</el-tag>字段是可以通过抓包修改的,例如我们可以将php文件的<el-tag
                        style="color:#0b915d" type="info"
                        size="default">content-type</el-tag>修改为<el-tag
                        style="color:#e1567b" type="info"
                        size="default">image/jpeg</el-tag><br>
                      绕过根据<el-tag
                        style="color:#0b915d" type="info"
                        size="default">content-type</el-tag>对文件合法性的校验,从而达到攻击目的
                      <br>
                      <br>
                    </div>
                    <div id="p3" style="height: 900px;">
                      <h1 class="title">绕过手段</h1>
                      <el-divider />
                      <div id="p3-1" style="height: 550px;">
                        <h2 class="sub-title">抓包测试</h2>
                        <!-- <img src="../assets/mime_demo0.png" style="width: 400px;height: auto;align-content: center;"><br> -->
                        <img src="../assets/mime_demo1.png" style="width: 900px;height: auto;align-content: center;"><br>
                        在<el-tag
                          style="color:#0b915d" type="info"
                          size="default">BurpSuite</el-tag>中打开代理(Proxy),将抓到的上传文件的数据发送到Repeater(右键选择Repeater)<br>
                        <img src="../assets/mime_demo2.png" style="width: 1000px;height: auto;align-content: center;"><br>
                        1.找到<el-tag
                          style="color:#0b915d" type="info"
                          size="default">content-type</el-tag>字段,尝试只修改该字段为允许上传的类型(如<el-tag
                          style="color:#e1567b" type="info"
                          size="default">image/jpeg</el-tag>)<br>
                        <img src="../assets/mime_demo3.png" style="width: 800px;height: auto;align-content: center;"><br>
      
                        2.点击send<br>
                        3.查看软件中显示的页面响应是否与上传正常文件的响应一致
                      </div>
                      <div id="part4" style="height: 200px;">
      
                      </div>
                    </div>
                  </el-text>
                </div>
              </div>
            </el-col>
            <el-col :span="5">
              <el-menu
                :default-active="activeAnchor" 
                class="tech-anchor" 
                mode="vertical"
                @select="handleAnchorSelect"
              >
                <el-menu-item-group title="本页目录">
                  <el-menu-item index="p2">MIME类型检验绕过</el-menu-item>
                  <el-menu-item index="p2-0">MIME类型</el-menu-item>
                  <el-menu-item index="p2-1">content-type</el-menu-item>
                  <el-menu-item index="p3">绕过手段</el-menu-item>
                  <el-menu-item index="p3-1">抓包测试</el-menu-item>
                </el-menu-item-group>
              </el-menu>
            </el-col>
          </el-row>
        </div>
      </template>
      
      <script>
      export default {
        data() {
          return {
            activeAnchor: 'p2',
            containerRef: null,
            input: '',
            anchorIds: ['p2', 'p2-0', 'p2-1', 'p3', 'p3-1']
          };
        },
        mounted() {
          this.containerRef = this.$refs.containerRef;
          this.handleScroll();
        },
        methods: {
          handleAnchorSelect(index) {
            this.activeAnchor = index;
            const element = document.getElementById(index);
            if (element) {
              const container = this.containerRef;
              const offsetTop = element.offsetTop - container.offsetTop;
              container.scrollTop = offsetTop;
            }
          },
          handleScroll() {
            const container = this.containerRef;
            const scrollTop = container.scrollTop;
            let closestAnchor = null;
            let minDistance = Infinity;
      
            this.anchorIds.forEach((id) => {
              const element = document.getElementById(id);
              if (element) {
                const offsetTop = element.offsetTop - container.offsetTop;
                const distance = Math.abs(offsetTop - scrollTop);
                if (distance < minDistance) {
                  minDistance = distance;
                  closestAnchor = id;
                }
              }
            });
      
            if (closestAnchor) {
              this.activeAnchor = closestAnchor;
            }
          },
          handleClick(e) {
            e.preventDefault();
          },
          error1() {
            this.$message({
              message: "You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''' at line 1",
              type: 'error'
            });
          },
          error2() {
            this.$message({
              message: 'You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near """ at line 1',
              type: 'error'
            });
          }
        }
      };
      </script>
      
      <style lang="less" scoped>
      code {
      font-family: "Cascadia Code";
      }
      
      .code-block {
      color: #c9d1d9;
      font-family: 'Cascadia Code', Consolas, 'Liberation Mono', Menlo, monospace;
      font-size: 15px;
      margin: 0;
      }
      
      .token.comment {
      color: #8b949e;
      }
      
      .token.keyword {
      color: #ff7b72;
      }
      
      .token.function {
        color: #d2a8ff;
      }
      
      .token.variable {
      color: #79c0ff;
      }
      
      .token.string {
      color: #a5d6ff;
      }
      
      .token.punctuation {
      color: #c9d1d9;
      }
      
      .token.operator {
        // 原样式内容，可根据实际情况补充
        color: #ff7b72;
      }
      
      /* el-tag 样式 */
      .el-tag {
      background-color: #3d3b3b;
      color: #fff;
      border-color: #282C34;
      }
      
      /* el-card 样式 */
      .el-card {
      background-color: #282C34;
      color: #fff;
      border-color: #282C34;
      }
      
      /* el-text 样式 */
      .el-text {
      color: #fff;
      }
      
      h1.title {
        margin: 0 0 3rem;
        font-size: 38px;
        line-height: 1.4;
        letter-spacing: -.02em;
        font-weight: 800;
        text-align: left;
        font-family: 'Orbitron','微软雅黑';
      }
      
      h2.sub-title {
        margin: 0 0 3rem;
        font-size: 25px;
        line-height: 1.4;
        letter-spacing: -.02em;
        font-weight: 800;
        text-align: left;
        font-family: 'Orbitron','微软雅黑';
      }
      
      h3.sub-sub-title {
        margin: 0 0 3rem;
        font-size: 20px;
        line-height: 1.4;
        letter-spacing: -.02em;
        font-weight: 800;
        text-align: left;
        font-family: 'Orbitron','微软雅黑';
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
        /* color:#434040 */
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
        color:rgb(99, 97, 97);
        font-family: 'Cascadia Code';
        font-size: 15px
      }
      
      .num {
        color: #8e40ce;
        font-family: 'Cascadia Code';
      }
      
      /* el-tag 样式 */
      .el-tag {
        background-color: #3d3b3b;
        color: #fff;
        border-color: #282C34;
      }
      
      /* el-card 样式 */
      .el-card {
        background-color: #282C34;
        color: #fff;
        border-color: #282C34;
      }
      
      /* el-text 样式 */
      .el-text {
        color: #fff;
      }
      
      /* 菜单容器样式 */
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
      
      /* 菜单标题样式 */
      .tech-anchor .el-menu-item-group__title {
        color: #d1e7ff !important;
        font-family: 'Cascadia Code', '微软雅黑';
        margin: 0 0 15px 0;
        padding-bottom: 10px;
        border-bottom: 1px solid rgba(0, 123, 255, 0.3);
        text-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
      }
      
      /* 菜单项基础样式 */
      .tech-anchor .el-menu-item {
        color: #a0c4ff !important;
        font-family: 'Cascadia Code', '微软雅黑';
        font-size: 0.9em;
        padding: 5px 10px;
        border-radius: 3px;
        transition: all 0.3s ease;
        position: relative;
      }
      
      /* 菜单项悬停效果 */
      .tech-anchor .el-menu-item:hover {
        color: #ffffff !important;
        background: rgba(0, 123, 255, 0.2);
        text-shadow: 0 0 5px #007bff;
        transform: translateX(5px);
      }
      
      .tech-anchor .el-menu-item:hover::before {
        content: '>';
        position: absolute;
        left: -10px;
        color: #00a2ff;
        opacity: 0;
        animation: blink-caret 0.8s step-end infinite alternate;
      }
      
      /* 激活菜单项样式 */
      .tech-anchor .el-menu-item.is-active {
        color: #ffffff !important;
        background: rgba(0, 123, 255, 0.3);
        text-shadow: 0 0 10px #00a2ff;
        border-left: 2px solid #007bff;
        padding-left: 15px;
        animation: pulse-glow 2s infinite alternate;
      }
      
      /* 响应式调整 */
      @media (max-width: 768px) {
        .tech-anchor {
          padding: 10px;
        }
      
        .tech-anchor .el-menu-item:hover::before {
          display: none;
        }
      }
      </style>