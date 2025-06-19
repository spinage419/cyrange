<template>
        <div>
          <el-row>
            <!-- 主要内容区域 -->
            <el-col :span="19">
              <div ref="containerRef" style="color: #fff; height: 650px; overflow-y: auto">
                <!-- Part 0 placeholder -->
                <div id="part0" style="height: 100px;"></div>
      
                <!-- Main content -->
                <div id="part2" style="height: auto;">
                  <el-text size="large" style="color:#fff; line-height: 3.6;">
                    <div id="p2" style="height: auto;">
                      <h1 class="title">命令执行漏洞</h1>
                      <el-divider />
                      <br>
      
                      <!-- Vulnerability Principle Section -->
                      <h2 class="sub-title" id="p2-1">漏洞原理</h2>
                      <p>命令执行漏洞是指当应用程序需要调用一些外部程序(如系统命令)来完成特定功能时,<br>
                      如果应用程序没有对用户输入进行严格的过滤和验证,攻击者就可以通过构造恶意的输入,将恶意命令注入到正常的命令中,<br>
                      从而使服务器执行恶意命令,获取服务器的敏感信息或控制服务器.
                      </p>
                      <br>
      
                      <!-- PHP Command Execution Functions Section -->
                      <h2 class="sub-title" id="p2-2">PHP 中命令执行相关的函数</h2>
                      <ul>
                        <li>
                          <h3 class="sub-sub-title">system</h3>
                          <p>该函数用于执行外部程序,并且会把执行结果输出到浏览器,同时返回命令执行后的最后一行结果.<br>
                          以下是其标准注释形式的函数声明:
                          </p>
                          <el-card style="max-width:750px; background-color:#161b22;"
                            body-style="line-height: 2">
                            <pre class="code-block">
      <span class="token comment">/**
      * 执行外部命令并将结果输出到浏览器,返回命令执行的最后一行结果<br>
      *<br>
      * @param string $command 要执行的命令<br>
      * @param int|null &$return_var 可选参数,用于存储命令执行的返回状态码<br>
      * @return string 返回命令执行结果的最后一行<br>
      */</span><br>
      <span class="token keyword">string</span> <span class="token function">system</span><span class="token punctuation">(</span><span class="token keyword">string</span> <span class="token variable">$command</span><span class="token punctuation">[</span><span class="token punctuation">,</span> <span class="token keyword">int</span> <span class="token operator">&</span><span class="token variable">$return_var</span><span class="token punctuation">]</span><span class="token punctuation">)</span></pre>
                          </el-card>
                          <p>其中,<el-tag style="color:#79c0ff" type="info">$command</el-tag> 是要执行的命令,<el-tag style="color:#79c0ff" type="info">$return_var</el-tag>
                          是可选参数,用于存储命令执行后的返回状态码.<br>
                          示例代码如下:
                          </p>
                          <el-card style="max-width:750px; background-color:#161b22;"
                            body-style="line-height: 2">
                            <pre class="code-block">
      <span class="token comment">// 执行系统命令 ls<br></span>
      <span class="token delimiter">&lt;?php</span><br>
              <span class="token variable">$output</span> <span class="token operator">=</span> <span class="token function">system</span><span class="token punctuation">(</span><span class="token string">'ls -l'</span><span class="token punctuation">)</span><span class="token punctuation">;</span><br>
              <span class="token keyword">echo</span> <span class="token variable">$output</span><span class="token punctuation">;</span><br>
      <span class="token delimiter">?&gt;</span></pre>
                          </el-card>
                          <p>在这个示例中,<el-tag style="color:#d2a8ff" type="info">system()</el-tag> 函数执行了 <el-tag style="color:#a5d6ff" type="info">ls -l</el-tag>
                            命令,将文件列表输出到浏览器,并且把最后一行结果赋值给
                            <el-tag style="color:#79c0ff" type="info">$output</el-tag> 变量.</p>
                        </li>
      
                        <!-- Other functions in similar format -->
                        <br>
                        <li>
                          <h3 class="sub-sub-title">exec</h3>
                          <p>该函数用于执行外部程序,它会将执行结果以数组的形式返回,数组中的每个元素代表结果的一行.<br>
                          以下是其标准注释形式的函数声明:
                          </p>
                          <el-card style="max-width:750px; background-color:#161b22;"
                            body-style="line-height: 2">
                            <pre class="code-block">
      <span class="token comment">/**
      * 执行外部命令并将结果以数组形式返回<br>
      *<br>
      * @param string $command 要执行的命令<br>
      * @param array|null &$output 可选参数,用于存储命令执行结果的数组<br>
      * @param int|null &$return_var 可选参数,用于存储命令执行的返回状态码<br>
      * @return string 返回命令执行结果的最后一行<br>
      */</span><br>
      <span class="token keyword">string</span> <span class="token function">exec</span><span class="token punctuation">(</span><span class="token keyword">string</span> <span class="token variable">$command</span><span class="token punctuation">[</span><span class="token punctuation">,</span> <span class="token keyword">array</span> <span class="token operator">&</span><span class="token variable">$output</span><span class="token punctuation">[</span><span class="token punctuation">,</span> <span class="token keyword">int</span> <span class="token operator">&</span><span class="token variable">$return_var</span><span class="token punctuation">]</span><span class="token punctuation">]</span><span class="token punctuation">)</span></pre>
                          </el-card>
                          <p>示例代码:</p>
                          <el-card style="max-width:750px; background-color:#161b22;"
                            body-style="line-height: 2">
                            <pre class="code-block">
      <span class="token comment">// 执行系统命令 ls<br></span>
      <span class="token delimiter">&lt;?php</span><br>
              <span class="token variable">$output</span> <span class="token operator">=</span> <span class="token keyword">array</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">;</span><br>
              <span class="token function">exec</span><span class="token punctuation">(</span><span class="token string">'ls -l'</span><span class="token punctuation">,</span> <span class="token variable">$output</span><span class="token punctuation">)</span><span class="token punctuation">;</span><br>
              <span class="token keyword">foreach</span> <span class="token punctuation">(</span><span class="token variable">$output</span> <span class="token keyword">as</span> <span class="token variable">$line</span><span class="token punctuation">)</span> <span class="token punctuation">{</span><br>
                      <span class="token keyword">echo</span> <span class="token variable">$line</span> <span class="token operator">.</span> <span class="token string">"&lt;br&gt;"</span><span class="token punctuation">;</span><br>
              <span class="token punctuation">}</span><br>
      <span class="token delimiter">?&gt;</span></pre>
                          </el-card>
                        </li>
      
                        <!-- Continue with other functions in same format -->
                      </ul>
                      <br>
      
                      <!-- Attack Methods Section -->
                      <h2 class="sub-title" id="p2-3">攻击方法</h2>
                      <p>攻击者通常会利用命令执行漏洞来执行恶意命令,例如查看服务器的文件系统、获取敏感信息、上传后门程序等.<br>
                        以下是一个简单的攻击示例:
                      </p>
                      <p>假设存在一个 PHP 页面,代码如下:</p>
                      <el-card style="max-width:750px; background-color:#161b22;"
                        body-style="line-height: 2">
                        <pre class="code-block">
      <span class="token comment">// command.php<br></span>
      <span class="token delimiter">&lt;?php</span><br>
              <span class="token variable">$cmd</span> <span class="token operator">=</span> <span class="token variable">$_GET</span><span class="token punctuation">[</span><span class="token string">'cmd'</span><span class="token punctuation">]</span><span class="token punctuation">;</span><br>
              <span class="token function">system</span><span class="token punctuation">(</span><span class="token variable">$cmd</span><span class="token punctuation">)</span><span class="token punctuation">;</span><br>
      <span class="token delimiter">?&gt;</span></pre>
                      </el-card>
                      <p>攻击者可以通过构造恶意的 URL 来执行任意命令,例如:</p>
                      <el-card style="max-width:750px; background-color:#161b22;"
                        body-style="line-height: 2">
                        <pre class="code-block">
            http://example.com/command.php?cmd=ls -al</pre>
                      </el-card>
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
                  <el-menu-item index="p2-1">漏洞原理</el-menu-item>
                  <el-menu-item index="p2-2">PHP 命令执行函数</el-menu-item>
                  <el-menu-item index="p2-3">攻击方法</el-menu-item>
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
            activeAnchor: 'p2-1'
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
            const sections = ['p2-1', 'p2-2', 'p2-3'];
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
      
      /* 函数名 */
      .func {
        color: #84D49C;
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
      </style>