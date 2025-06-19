<template>
    <div>
      <el-row>
        <el-col :span="19">
          <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
            <div id="part0" style="height: 100px;"></div>
            
            <div id="part2" style="height: 650px;">
              <el-text size="large" style="color:#fff; line-height: 3.6;">
                <div id="p2" style="height: 450px;">
                  <h1 class="title">未授权文件任意下载漏洞</h1>
                  <el-divider />
                  未授权文件任意下载漏洞是一种严重的安全漏洞，攻击者可以在未经过授权的情况下，通过特定的漏洞绕过应用程序的访问控制机制，直接下载服务器上的任意文件。这种漏洞可能导致敏感信息泄露，如用户数据、配置文件、密钥等，给系统带来巨大的安全风险。<br>
  
                  该漏洞通常是由于应用程序没有对用户请求进行严格的权限验证和输入过滤，或者系统内部的访问控制策略存在缺陷等原因导致的。攻击者可以构造恶意的请求，利用这些漏洞来获取他们本来无权访问的文件。<br>
                  <br>
                </div>
                <div id="p3-0" style="height:400px">
                  <h1 class="title" style="font-family: 'Cascadia Code';">攻击原理与常见手段</h1>
                  <el-divider />
                  攻击者通常会通过以下几种方式利用未授权文件任意下载漏洞：
                  <ul>
                    <li>目录遍历攻击：利用 `../` 等目录跳转符号，绕过应用程序的目录限制，访问上级目录中的敏感文件。</li>
                    <li>参数篡改：修改请求中的文件路径参数，指向服务器上的敏感文件。</li>
                    <li>利用默认配置：某些应用程序可能存在默认的文件下载接口，攻击者可以利用这些接口下载敏感文件。</li>
                  </ul>
                </div>
                <div id="p3-1" style="height: 1100px;">
                  <h1 class="title">常见敏感文件存放路径</h1>
                  <ul>
                    <li>
                      <h2 class="sub-title">Windows</h2>
                      <el-card style="max-width:750px;
                            background-color:#282C34;
                            max-height: 1900px;"
                            body-style="line-height: 2">
                        C:\boot.ini&nbsp;<span class="cmt">#存放系统版本信息</span>
                        <br>
                        C:\Windows\System32\drivers\etc\hosts&nbsp;<span class="cmt">#主机名与IP的映射文件</span>
                        <br>
                        C:\Windows\System32\inetsrv\MetaBase.xml&nbsp;<span class="cmt">#IIS配置文件,包含虚拟主机配置信息</span>
                        <br>
                        C:\Windows\php.ini <span class="cmt">#php配置信息</span>
                        <br>
                        C:\mysql\data\mysql\user.MYD<span class=cmt>存储MySQL用户表中的数据库连接密码</span>
                        <br>
                        C:\Program Files\mysql\my.ini<span class="cmt">MySQL配置文件,可能包含管理员登录信息</span>
                        <br>
                      </el-card>
                      <br>
                    </li>
                    <br>
                    <li>
                      <h2 class="sub-title">Linux</h2>
                      <el-card style="max-width:750px;
                            background-color:#282C34;
                            max-height: 1900px;"
                            body-style="line-height: 2">
                        /etc/profile<span class="cmt">#用于设置全局的用户环境变量和启动脚本</span>
                        <br>
                        /etc/passwd<span class="cmt">#存储用户账户信息,包括用户名、用户ID(UID)、用户主目录、登录Shell等</span>
                        <br>
                        /etc/shadow<span class="cmt">#存储用户密码信息</span>
                        <br>
                        /etc/docker/daemon.json<span class="cmt">#docker的配置文件</span>
                        <br>
                        /etc/nginx/*<span class="cmt">#nginx的一系列配置文件</span>
                        <br>
                        /etc/mysql/*<span class="cmt">#mysql的一系列配置文件</span>
                        <br>
                        /dev/*<span class="cmt">#一系列设备文件</span>
                        <br>
                      </el-card>
                    </li>
                    <br>
                  </ul>
                </div>
                <div id="p3-2" style="height: 3800px;">
                  <h1 class="title">与文件操作相关的 函数&nbsp; | &nbsp;关键字</h1>
                  <el-divider />
                  <br>
                  <ul>
                    <li>
                      <h2 class="sub-title">fopen</h2>
                      <el-card style="max-width:750px;
                            background-color:#282C34;
                            max-height: 1900px;" body-style="line-height: 2">
                        <span class="key">function</span>
                        <span class="func"> fopen</span><span class="lev0">(</span><span class="code">$file_path</span><span class="lev0">,</span>
                        <span class="code">$mode</span><span class="lev0">)</span><span class="lev0">;</span>
                        <br><br>
                        <span class="cmt">/**<br>
                          * 打开文件或URL<br>
                          * <span class="key">@param</span> string $file_path 文件路径<br>
                          * <span class="key">@param</span> string $mode 打开模式(r/r+/w/w+/a/a+/x/x+/c/c+等)<br>
                          * <span class="key">@return</span> resource|false 成功返回文件指针资源,失败返回false<br>
                          */</span>
                        <br><br>
                        <span class="cmt">// 示例:读取文件内容</span><br>
                        <span class="code">$handle</span> <span class="key">= </span> <span class="func">fopen</span><span class="lev0">(</span><span class="code">'example.txt'</span><span class="lev0">,</span>
                        <span class="code">'r'</span><span class="lev0">)</span><span class="lev0">;</span><br>
                        <span class="key">if</span> <span class="lev0">(</span><span class="code">$handle</span><span class="lev0">)</span> <span class="lev0">{</span><br>
                        &nbsp;&nbsp;<span class="code">$content</span> <span class="key">= </span> <span class="func">fread</span><span class="lev0">(</span><span class="code">$handle</span><span class="lev0">,</span>
                        <span class="func">filesize</span><span class="lev0">(</span><span class="code">'example.txt'</span><span class="lev0">)</span><span class="lev0">)</span><span class="lev0">;</span><br>
                        &nbsp;&nbsp;<span class="func">fclose</span><span class="lev0">(</span><span class="code">$handle</span><span class="lev0">)</span><span class="lev0">;</span><br>
                        <span class="lev0">}</span>
                      </el-card>
                    </li>
                    <br>
                    <li>
                      <h2 class="sub-title">fread</h2>
                      <el-card style="max-width:750px;
                            background-color:#282C34;
                            max-height: 1900px;" body-style="line-height: 2">
                        <span class="key">function</span>
                        <span class="func"> fread</span><span class="lev0">(</span><span class="code">$handle</span><span class="lev0">,</span>
                        <span class="code">$length</span><span class="lev0">)</span><span class="lev0">;</span>
                        <br><br>
                        <span class="cmt">/**<br>
                          * 读取文件内容(二进制安全)<br>
                          * <span class="key">@param</span> resource $handle fopen()返回的文件指针<br>
                          * <span class="key">@param</span> int $length 要读取的最大字节数<br>
                          * <span class="key">@return</span> string|false 成功返回读取的字符串,失败返回false<br>
                          */</span>
                        <br><br>
                        <span class="cmt">// 示例:读取文件前100字节</span><br>
                        <span class="code">$handle</span> <span class="key">= </span> <span class="func">fopen</span><span class="lev0">(</span><span class="code">'data.bin'</span><span class="lev0">,</span>
                        <span class="code">'rb'</span><span class="lev0">)</span><span class="lev0">;</span><br>
                        <span class="code">$data</span> <span class="key">= </span> <span class="func">fread</span><span class="lev0">(</span><span class="code">$handle</span><span class="lev0">,</span>
                        <span class="num">100</span><span class="lev0">)</span><span class="lev0">;</span><br>
                        <span class="func">fclose</span><span class="lev0">(</span><span class="code">$handle</span><span class="lev0">)</span><span class="lev0">;</span>
                      </el-card>
                    </li>
                    <br>
                    <li>
                      <h2 class="sub-title">include</h2>
                      <el-card style="max-width:750px;
                            background-color:#282C34;
                            max-height: 1900px;" body-style="line-height: 2">
                        <span class="key">include</span> <span class="code"> $filename</span><span class="lev0">;</span>
                        <br><br>
                        <span class="cmt">/**<br>
                          * 包含并运行指定文件<br>
                          * <span class="key">@param</span> string $filename 要包含的文件路径<br>
                          * <span class="key">@return</span> mixed 成功返回1,失败返回false(但会继续执行脚本)<br>
                          */</span>
                        <br><br>
                        <span class="cmt">// 示例:包含配置文件</span><br>
                        <span class="key">include</span> <span class="code">'config.php'</span><span class="lev0">;</span><br>
                        <span class="cmt">// 使用config.php中定义的变量</span><br>
                        <span class="code">$db</span> <span class="key">= </span> <span class="key">new </span> <span class="func">PDO</span><span class="lev0">(</span><span class="code">$db_dsn</span><span class="lev0">,</span>
                        <span class="code">$db_user</span><span class="lev0">,</span> <span class="code">$db_pass</span><span class="lev0">)</span><span class="lev0">;</span>
                      </el-card>
                    </li>
                    <br>
                    <li>
                      <h2 class="sub-title">readfile</h2>
                      <el-card style="max-width:750px;
                            background-color:#282C34;
                            max-height: 1900px;" body-style="line-height: 2">
                        <span class="key">function</span>
                        <span class="func"> readfile</span><span class="lev0">(</span><span class="code">$filename</span><span class="lev0">)</span><span class="lev0">;</span>
                        <br><br>
                        <span class="cmt">/**<br>
                          * 读取文件并输出到浏览器<br>
                          * <span class="key">@param</span> string $filename 要读取的文件路径<br>
                          * <span class="key">@return</span> int|false 成功返回读取的字节数,失败返回false<br>
                          */</span>
                        <br><br>
                        <span class="cmt">// 示例:下载文件</span><br>
                        <span class="key">if</span> <span class="lev0">(</span><span class="func">file_exists</span><span class="lev0">(</span><span class="code">'download.zip'</span><span class="lev0">)</span><span class="lev0">)</span> <span class="lev0">{</span><br>
                        &nbsp;&nbsp;<span class="key">header</span><span class="lev0">(</span><span class="code">'Content-Type: application/zip'</span><span class="lev0">)</span><span class="lev0">;</span><br>
                        &nbsp;&nbsp;<span class="key">header</span><span class="lev0">(</span><span class="code">'Content-Disposition: attachment; filename="download.zip"'</span><span class="lev0">)</span><span class="lev0">;</span><br>
                        &nbsp;&nbsp;<span class="func">readfile</span><span class="lev0">(</span><span class="code">'download.zip'</span><span class="lev0">)</span><span class="lev0">;</span><br>
                        <span class="lev0">}</span>
                      </el-card>
                    </li>
                  </ul>
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
              <el-menu-item index="p2">漏洞概述</el-menu-item>
              <el-menu-item index="p3-0">攻击原理</el-menu-item>
              <el-menu-item index="p3-1">敏感文件路径</el-menu-item>
              <el-menu-item index="p3-2">相关函数</el-menu-item>
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
        containerRef: null
      }
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
        const scrollPosition = this.containerRef.scrollTop;
        
        for (let i = sections.length - 1; i >= 0; i--) {
          const section = document.getElementById(sections[i]);
          if (section && scrollPosition >= section.offsetTop - 100) {
            this.activeAnchor = sections[i];
            break;
          }
        }
      }
    },
    mounted() {
      this.containerRef = this.$refs.containerRef;
      if (this.containerRef) {
        this.containerRef.addEventListener('scroll', this.handleScroll);
      }
    },
    beforeDestroy() {
      if (this.containerRef) {
        this.containerRef.removeEventListener('scroll', this.handleScroll);
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
  
  .token {
    &.comment {
      color: #8b949e;
    }
    &.keyword {
      color: #ff7b72;
    }
    &.function {
      color: #d2a8ff;
    }
    &.variable {
      color: #79c0ff;
    }
    &.string {
      color: #a5d6ff;
    }
    &.punctuation {
      color: #c9d1d9;
    }
    &.operator {
      color: #ff7b72;
    }
    &.delimiter {
      color: #ff7b72;
    }
  }
  
  /* Card styling */
  .el-card {
    border: 1px solid #30363d;
    margin: 10px 0;
  }
  
  /* Anchor styling */
  .tech-anchor {
    position: fixed;
    right: 20px;
    background-color: #0d1117;
    padding: 15px;
    border-radius: 6px;
    border: 1px solid #30363d;
    
    .el-menu-item-group__title {
      color: #d1e7ff !important;
      font-family: 'Cascadia Code', '微软雅黑';
      margin: 0 0 15px 0;
      padding-bottom: 10px;
      border-bottom: 1px solid rgba(0, 123, 255, 0.3);
      text-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    }
    
    .el-menu-item {
      color: #a0c4ff !important;
      font-family: 'Cascadia Code', '微软雅黑';
      font-size: 0.9em;
      padding: 5px 10px;
      border-radius: 3px;
      transition: all 0.3s ease;
      position: relative;
      height: auto;
      line-height: 1.5;
      margin: 8px 0;
      
      &:hover {
        color: #ffffff !important;
        background: rgba(0, 123, 255, 0.2);
        text-shadow: 0 0 5px #007bff;
        transform: translateX(5px);
        
        &::before {
          content: '>';
          position: absolute;
          left: -10px;
          color: #00a2ff;
          opacity: 0;
          animation: blink-caret 0.8s step-end infinite alternate;
        }
      }
      
      &.is-active {
        color: #ffffff !important;
        background: rgba(0, 123, 255, 0.3);
        text-shadow: 0 0 10px #00a2ff;
        border-left: 2px solid #007bff;
        padding-left: 15px;
        animation: pulse-glow 2s infinite alternate;
      }
    }
  }
  
  /* 科技按钮样式 */
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
    font-size: 15px;
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
      
      .el-menu-item:hover::before {
        display: none;
      }
    }
  }
  
  @keyframes blink-caret {
    from, to { opacity: 0; }
    50% { opacity: 1; }
  }
  
  @keyframes pulse-glow {
    0% { box-shadow: 0 0 5px rgba(0, 123, 255, 0.5); }
    100% { box-shadow: 0 0 20px rgba(0, 123, 255, 0.8); }
  }
  </style>