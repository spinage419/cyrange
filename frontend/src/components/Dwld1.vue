<template>
        <div>
          <el-row>
            <!-- 主要内容区域 -->
            <el-col :span="19">
              <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                <div id="part0" style="height: 100px;"></div>
      
                <div id="part2" style="height: 650px;">
                  <el-text size="large" style="color:#fff; line-height: 3.6;">
                    <div id="p2" style="height: 450px;">
                      <h1 class="title">任意文件下载中的路径遍历</h1>
                      <el-divider />
                      路径遍历是任意文件下载漏洞中常用的一种攻击技术.攻击者通过构造特殊的请求,利用应用程序对用户输入的文件路径缺乏充分验证和过滤的漏洞,绕过正常的访问限制,访问并下载服务器上的任意文件.
      
                      这种攻击方式通常依赖于文件系统的目录结构和路径表示方法,特别是 `./` 和 `../` 这两个符号.
                      <br>
                      <br>
                    </div>
                    <div id="p3-0" style="height:400px">
                      <h1 class="title" style="font-family: 'Cascadia Code';">
                        目录穿越符号 `./` 与 `../`</h1>
                      <el-divider />
                      - `./`:表示当前目录.在路径中使用 `./`
                      通常可以省略,因为默认就是当前目录.例如,`./file.txt` 等同于 `file.txt`.
                      - `../`:表示父级目录.通过多次使用
                      `../`,攻击者可以向上移动目录层级,从而突破应用程序原本的访问限制.例如,`../../etc/passwd`
                      可以从当前目录向上移动两级,然后访问 `/etc/passwd` 文件.
      
                      在任意文件下载漏洞利用中,我们往往需要结合这两个符号进行目录跳跃,来获取我们想要的敏感文件.
                    </div>
                    <div id="p3-1" style="height: 1100px;">
                      <h1 class="title">路径遍历攻击示例</h1>
                      <ul>
                        <li>
                          <h2 class="sub-title">Windows 系统示例</h2>
                          <el-card style="max-width:750px; background-color:#282C34; max-height: 1900px;" 
                            body-style="line-height: 2">
                            假设一个 Web 应用程序允许用户下载文件,其 URL 格式为
                            `http://example.com/download.php?file=filename.txt`.攻击者可以通过构造如下恶意请求来进行路径遍历攻击:
                            <br>
                            - `http://example.com/download.php?file=../../boot.ini`:尝试下载
                            `C:\boot.ini` 文件,该文件存放系统版本信息.
                            - `http://example.com/download.php?file=../../Windows/System32/drivers/etc/hosts`:尝试下载
                            `C:\Windows\System32\drivers\etc\hosts`
                            文件,该文件是主机名与 IP 的映射文件.
                            <br>
                          </el-card>
                          <br>
                        </li>
                        <br>
                        <li>
                          <h2 class="sub-title">Linux 系统示例</h2>
                          <el-card style="max-width:750px; background-color:#282C34; max-height: 1900px;" 
                            body-style="line-height: 2">
                            同样假设 Web 应用程序的下载 URL 为
                            `http://example.com/download.php?file=filename.txt`.攻击者可以构造如下请求:
                            <br>
                            - `http://example.com/download.php?file=../../etc/passwd`:尝试下载
                            `/etc/passwd`
                            文件,该文件存储用户账户信息,包括用户名、用户 ID
                            (UID)、用户主目录、登录 Shell 等.
                            - `http://example.com/download.php?file=../../etc/shadow`:尝试下载
                            `/etc/shadow` 文件,该文件存储用户密码信息.
                            <br>
                          </el-card>
                        </li>
                        <br>
                      </ul>
                    </div>
                    <div id="p3-2" style="height: 3800px;">
                      <h1 class="title">防御路径遍历攻击的方法</h1>
                      <el-divider />
                      <br>
                      <ul>
                        <li>
                          <h2 class="sub-title">输入验证和过滤</h2>
                          <el-card style="max-width:750px; background-color:#282C34; max-height: 1900px;" 
                            body-style="line-height: 2">
                            应用程序应该对用户输入的文件路径进行严格的验证和过滤,去除其中可能包含的恶意字符,如
                            `../`、`./`
                            等.可以使用白名单机制,只允许用户访问指定目录下的文件.
                            <br><br>
                            <span class="cmt">// PHP 示例代码</span><br>
                            <span class="code">$allowed_dir = '/path/to/allowed/dir/';</span><br>
                            <span class="code">$requested_file = $_GET['file'];</span><br>
                            <span class="key">if</span> <span class="lev0">(</span><span class="code">strpos($requested_file, '../') === false</span><span class="lev0">)</span> <span class="lev0">{</span><br>
                            &nbsp;&nbsp;<span class="code">$full_path = $allowed_dir . $requested_file;</span><br>
                            &nbsp;&nbsp;<span class="key">if</span> <span class="lev0">(</span><span class="code">file_exists($full_path)</span><span class="lev0">)</span> <span class="lev0">{</span><br>
                            &nbsp;&nbsp;&nbsp;&nbsp;<span class="func">readfile($full_path)</span><span class="lev0">;</span><br>
                            &nbsp;&nbsp;<span class="lev0">}</span><br>
                            <span class="lev0">}</span>
                          </el-card>
                        </li>
                        <br>
                        <li>
                          <h2 class="sub-title">使用绝对路径</h2>
                          <el-card style="max-width:750px; background-color:#282C34; max-height: 1900px;" 
                            body-style="line-height: 2">
                            在处理文件路径时,尽量使用绝对路径,避免使用相对路径.这样可以减少路径遍历攻击的风险.
                            <br><br>
                            <span class="cmt">// PHP 示例代码</span><br>
                            <span class="code">$absolute_path = '/path/to/allowed/dir/filename.txt';</span><br>
                            <span class="key">if</span> <span class="lev0">(</span><span class="code">file_exists($absolute_path)</span><span class="lev0">)</span> <span class="lev0">{</span><br>
                            &nbsp;&nbsp;<span class="func">readfile($absolute_path)</span><span class="lev0">;</span><br>
                            <span class="lev0">}</span>
                          </el-card>
                        </li>
                        <br>
                        <li>
                          <h2 class="sub-title">权限管理</h2>
                          <el-card style="max-width:750px; background-color:#282C34; max-height: 1900px;" 
                            body-style="line-height: 2">
                            确保服务器上的文件和目录具有适当的访问权限,只允许应用程序以最小权限访问必要的文件.避免将敏感文件放置在
                            Web 可访问的目录下.
                          </el-card>
                        </li>
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
                  <el-menu-item index="p2">任意文件下载中的路径遍历</el-menu-item>
                  <el-menu-item index="p3-0">↳ 目录穿越符号</el-menu-item>
                  <el-menu-item index="p3-1">↳ 路径遍历攻击示例</el-menu-item>
                  <el-menu-item index="p3-2">↳ 防御方法</el-menu-item>
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
            const sections = ['p2', 'p3-0', 'p3-1', 'p3-2'];
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
      </style>