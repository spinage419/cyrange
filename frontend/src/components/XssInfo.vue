<template>
        <div>
        <div id="xss-test"></div>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;">
                                        </div>

                                        <div id="part2" style="height: 650px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2" style="height: 450px;">
                                                                <h1 class="title">XSS-跨站脚本攻击</h1>
                                                                <el-divider />
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="large">XSS</el-tag>攻击指的是:<br>
                                                                攻击者利用网站程序对用户输入过滤不严,构造输入可以显示在页面上对其他用户造成影响的脚本代码<br>
                                                                通过在用户端注入恶意的可执行脚本,若服务器对用户的输入处理不严谨,则浏览器就会直接执行用户注入的恶意脚本<br>
                                                                达到窃取身份等的目的
                                                                <br>
                                                                <br>
                                                        </div>
                                                        <div id="p3" style="height: 2650px;">
                                                                <h1 class="title">常见on事件属性</h1>
                                                                <el-divider />
                                                                <br>
                                                                <!-- <h2 class="sub-title">无过滤</h2> -->
                                                                <ul>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-0">onclick
                                                                                </h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                点击触发 | 支持:
                                                                                                &lt;<span
                                                                                                        class="func">button</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">a</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">div</span>&gt;,
                                                                                                等几乎所有可见元素.
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-1">onfocus
                                                                                        / onblur
                                                                                </h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                焦点获取/失去触发 | 支持:
                                                                                                &lt;<span
                                                                                                        class="func">input</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">select</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">textarea</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">a</span>&gt;(带<span
                                                                                                        class="lev2">href</span>)
                                                                                                <br>
                                                                                                <span class="code">
                                                                                                        <span
                                                                                                                class="lev2">autofocus</span>:
                                                                                                        当页面加载时元素应该自动获得焦点.
                                                                                                </span>
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-2">onchange
                                                                                </h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                值变更触发 | 支持:
                                                                                                &lt;<span
                                                                                                        class="func">input</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">select</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">textarea</span>&gt;.
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-3">oninput
                                                                                </h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                输入时实时触发 | 支持:
                                                                                                &lt;<span
                                                                                                        class="func">input</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">textarea</span>&gt;.
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-4">onsubmit
                                                                                </h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                表单提交触发 | 仅支持:
                                                                                                &lt;<span
                                                                                                        class="func">form</span>&gt;.
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-5">
                                                                                        onmouseover /
                                                                                        onmouseout</h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                鼠标移入/移出触发 | 支持: 所有可见元素.
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-6">
                                                                                        onkeydown /
                                                                                        onkeyup / onkeypress</h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                键盘事件 | 支持:
                                                                                                &lt;<span
                                                                                                        class="func">input</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">textarea</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">document</span>&gt;.
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-7">onerror
                                                                                </h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                加载失败触发 | 支持:
                                                                                                &lt;<span
                                                                                                        class="func">img</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">script</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">link</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">video</span>&gt;等资源标签.
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-8">onload
                                                                                </h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                加载完成触发 | 支持:
                                                                                                &lt;<span
                                                                                                        class="func">img</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">script</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">iframe</span>&gt;,
                                                                                                &lt;<span
                                                                                                        class="func">body</span>&gt;.
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-9">onscroll
                                                                                </h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                滚动触发 | 支持:
                                                                                                所有可滚动元素(带<span
                                                                                                        class="lev2">overflow</span>)和<span
                                                                                                        class="lev2">window</span>对象.
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-10">
                                                                                        ontoggle</h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                当<span
                                                                                                        class="func">details</span>元素展开/收起状态变化时触发
                                                                                                | 仅支持:
                                                                                                &lt;<span
                                                                                                        class="func">details</span>&gt;.
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                </ul>
                                                        </div>
                                                        <div id="p4" style="height: 1200px;">
                                                                <h1 class="title">XSS中常用伪协议</h1>
                                                                <el-divider />
                                                                <br>
                                                                <ul>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p4-1">
                                                                                        &nbsp;javascript:
                                                                                </h2>
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 1900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                特点:能直接执行<span
                                                                                                        class="key">JavaScript</span>代码<br>
                                                                                                常用于:
                                                                                                &lt;<span
                                                                                                        class="func">a</span>&gt;
                                                                                                &lt;<span
                                                                                                        class="func">img</span>&gt;
                                                                                                &lt;<span
                                                                                                        class="func">iframe</span>&gt;
                                                                                                的<span
                                                                                                        class="key">href/src</span>属性<br>
                                                                                                基本形式:
                                                                                                &lt;<span
                                                                                                        class="func">a</span>
                                                                                                <span
                                                                                                        class="num"> href</span>="<span
                                                                                                        class="key">javascript:</span><span
                                                                                                        class="func">alert</span>(<span
                                                                                                        class="num">1</span>)"&gt;&lt;/<span
                                                                                                        class="func">a</span>&gt;

                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p4-2">
                                                                                        &nbsp;data:</h2>
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 1900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                特点:<br>
                                                                                                1.通过<span
                                                                                                        class="key">base64</span>编码嵌入<span
                                                                                                        class="key">HTML/JavaScript</span>代码
                                                                                                <br>
                                                                                                2.支持多种<span
                                                                                                        class="key">MIME</span>场景(如image/svg+xml,text/html)
                                                                                                <br>
                                                                                                基本形式:<br>
                                                                                                1.直接嵌入<span
                                                                                                        class="key">html</span>:<br>
                                                                                                &lt;<span
                                                                                                        class="func">iframe</span>
                                                                                                <span
                                                                                                        class="num"> src</span>="<span
                                                                                                        class="key">data:text/html</span>,&lt;<span
                                                                                                        class="func">script</span>&gt;alert(1)&lt;/<span
                                                                                                        class="func">script</span>&gt;"&gt;&lt;/<span
                                                                                                        class="func">iframe</span>&gt;
                                                                                                <br>
                                                                                                <br>
                                                                                                2.<span
                                                                                                        class="key">base64</span>加密后:<br>

                                                                                                &lt;<span
                                                                                                        class="func">iframe</span>
                                                                                                <span
                                                                                                        class="num"> src</span>="<span
                                                                                                        class="key">data:</span>image/svg;base64,<br>PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94aHRtbCI+PHNjcmlwdD5hbGVydCgxKTwvc2NyaXB0Pjwvc3ZnPg=="&gt;&lt;/<span
                                                                                                        class="func">iframe</span>&gt;
                                                                                                <br>
                                                                                                <span class="cmt">
                                                                                                        &lt;!--
                                                                                                        加密的内容为:<br>
                                                                                                        &lt;svg
                                                                                                        xmlns="http://www.w3.org/1999/xhtml"&gt;&lt;script&gt;alert(1)&lt;/script&gt;&lt;/svg&gt;<br>
                                                                                                        svg标签中允许嵌入script标签<br>
                                                                                                        --&gt;
                                                                                                </span>

                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                </ul>
                                                        </div>
                                                        <div id="p5" style="height: 3800px;">
                                                                <h1 class="title">JavaScript中一些常用全局对象的属性</h1>
                                                                <el-divider />
                                                                <br>
                                                                <ul>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p4-1">
                                                                                        &nbsp;window:</h2>
                                                                                作为浏览器窗口的代表,它包含了与浏览器窗口相关的属性和方法<br>
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 1900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                <span class="cmt">
                                                                                                        /**<br>
                                                                                                        *通过设置
                                                                                                        window.location.href
                                                                                                        的值,可以实现页面跳转
                                                                                                        <br>
                                                                                                        */
                                                                                                </span>
                                                                                                <br>
                                                                                                <span
                                                                                                        class="v">window</span>.<span
                                                                                                        class="v">location</span>.<span
                                                                                                        class="num">href</span>
                                                                                                = "<span
                                                                                                        class="lev2">https://www.bilibili.com</span>";<br>
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p4-2">
                                                                                        &nbsp;document:</h2>
                                                                                它代表了当前加载的<el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="large">HTML</el-tag>文档.通过<el-tag
                                                                                        style="color:#4cbcec"
                                                                                        type="info"
                                                                                        size="large">document</el-tag>对象,可以操作文档中的元素
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 1900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                <span class="v">document</span>.<span class="num">cookie</span>; <span
                                                                                                        class="cmt">//
                                                                                                        获取或设置当前页面的Cookie(字符串形式,可读写)</span><br>
                                                                                                <span class="v">document</span>.<span class="num">getElementById</span><span class="lev2">(<span class="code">""</span>)</span>.<span class="v">innerText</span>;
                                                                                                <span class="cmt">//
                                                                                                        获取或设置指定元素的纯文本内容(自动忽略HTML标签)</span><br>
                                                                                                <span class="v">document</span>.<span class="num">getElementById</span><span class="lev2">(<span class="code">""</span>)</span>.<span class="v">innerHTML</span>;
                                                                                                <span class="cmt">//
                                                                                                        获取或设置指定元素的HTML内容(可解析标签,易引发XSS风险)</span><br>
                                                                                                <span class="v">document</span>.<span class="num">getElementsByName</span><span class="lev2">(<span class=code>""</span>)</span>.<span class="v">innerText</span>;
                                                                                                <span class="cmt">//
                                                                                                        返回节点列表,需索引访问</span><br>
                                                                                                <span class="v">document</span>.<span class="num">getElementsByName</span><span class="lev2">(<span class="code">""</span>)</span>.<span class="v">innerHTML</span>;
                                                                                                <span class="cmt">//返回节点列表,需索引访问
                                                                                                        </span><br>
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
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
            <el-menu-item index="p2">XSS-跨站脚本攻击</el-menu-item>
            <el-submenu index="p3">
              <template slot="title">常见on事件属性</template>
              <el-menu-item index="p3-0">onclick</el-menu-item>
              <el-menu-item index="p3-1">onfocus / onblur</el-menu-item>
              <el-menu-item index="p3-2">onchange</el-menu-item>
              <el-menu-item index="p3-3">oninput</el-menu-item>
              <el-menu-item index="p3-4">onsubmit</el-menu-item>
              <el-menu-item index="p3-5">onmouseover / onmouseout</el-menu-item>
              <el-menu-item index="p3-6">onkeydown / onkeyup /onkeypress</el-menu-item>
              <el-menu-item index="p3-7">onerror</el-menu-item>
              <el-menu-item index="p3-8">onload</el-menu-item>
              <el-menu-item index="p3-9">onscroll</el-menu-item>
            </el-submenu>
            <el-submenu index="p4">
              <template slot="title">XSS中常用伪协议</template>
              <el-menu-item index="p4-1">javascript:</el-menu-item>
              <el-menu-item index="p4-2">data:</el-menu-item>
            </el-submenu>
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
      this.activeAnchor = index;
      const element = document.getElementById(index);
      if (element) {
        element.scrollIntoView({ behavior: 'smooth' });
      }
    },
    handleScroll() {
      // Implement your scroll position detection logic here
      // to update activeAnchor based on scroll position
    },
    submit() {
      if (this.input) {
        document.getElementById('xss-test').innerHTML = this.input;
        console.log(document.getElementById('xss-test').innerHTML);
        this.XssTest(this.input);
      }
    }
  }
}
</script>

<style lang="less">
// Base styles
@tech-blue: #007bff;
@tech-blue-light: #00a1ff;
@tech-blue-dark: #0033cc;
@text-color: #fff;
@menu-bg: rgba(10, 17, 40, 0.7);
@menu-border: rgba(0, 123, 255, 0.3);

.tech-anchor {
  background: @menu-bg;
  border-radius: 4px;
  padding: 15px;
  box-shadow: 0 0 15px rgba(0, 123, 255, 0.1);
  border-left: 1px solid @menu-border;
  height: 100%;
  overflow-y: auto;
  
  .el-menu {
    background: transparent;
    border-right: none;
    
    .el-menu-item-group__title {
      color: #d1e7ff;
      font-family: 'Cascadia Code', '微软雅黑';
      padding: 0 0 10px 0;
      border-bottom: 1px solid @menu-border;
      text-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    }
    
    .el-menu-item, .el-submenu__title {
      color: #a0c4ff;
      font-family: 'Cascadia Code', '微软雅黑';
      font-size: 0.9em;
      height: 40px;
      line-height: 40px;
      transition: all 0.3s ease;
      
      &:hover {
        color: @text-color !important;
        background: rgba(0, 123, 255, 0.2);
        text-shadow: 0 0 5px @tech-blue;
      }
    }
    
    .el-menu-item.is-active {
      color: @text-color !important;
      background: rgba(0, 123, 255, 0.3);
      text-shadow: 0 0 10px @tech-blue-light;
      border-left: 2px solid @tech-blue;
      padding-left: 15px !important;
    }
    
    .el-submenu {
      .el-menu-item {
        font-size: 0.85em;
        color: #8ab4f8 !important;
        height: 36px;
        line-height: 36px;
        padding-left: 40px !important;
        
        &:hover {
          color: @text-color !important;
        }
      }
    }
  }
}

// Title styles
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

// Code highlighting styles
.code {
  color: @text-color;
  font-family: 'Cascadia Code';
  
  .v { color: #4cbcec; } // variables
  .key { color: #e1567b; } // keywords
  .lev0 { color: #f0de18; } // level 0 brackets
  .lev1 { color: rgb(238, 65, 195); } // level 1 brackets
  .lev2 { color: #239af5; } // level 2 brackets
  .func { color: #84D49C; } // functions
  .txt { font-family: 'Cascadia Code'; } // text
  .cmt { 
    color: rgb(99, 97, 97);
    font-size: 15px;
  } // comments
  .num { color: #b891d8; } // numbers
}

// Element UI component overrides
.el-tag {
  background-color: #3d3b3b;
  color: @text-color;
  border-color: #282C34;
}

.el-card {
  background-color: #282C34;
  color: @text-color;
  border-color: #282C34;
}

.el-text {
  color: @text-color;
}

// Responsive adjustments
@media (max-width: 768px) {
  .tech-anchor {
    padding: 10px;
  }
}
</style>