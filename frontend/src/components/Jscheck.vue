<template>
        <div>
          <el-row>
            <el-col :span="19">
              <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto" @scroll="handleScroll">
                <div id="part0" style="height: 100px;"></div>
                <div id="part2" style="height: 650px;">
                  <el-text size="large" style="color:#fff; line-height: 3.6;">
                    <div id="p2" style="height: 1350px;">
                      <h1 class="title">JavaScript检验绕过</h1>
                      <el-divider />
                      JavaScript检验绕过指的是:
                      <br>
                      <el-tag style="color:#0b915d" type="info" size="default">web</el-tag>应用对于非法文件的过滤,采用前端<el-tag style="color:#0b915d" type="info" size="default">javascript</el-tag>函数过滤的方式,同时后端并没有对前端传入的文件加以严格过滤
                      <br>
                      通过某种手段禁用<el-tag style="color:#0b915d" type="info" size="default">javascript</el-tag>,则非法文件就能绕过前端的过滤从而上传到服务器
                      <br>
                      下面是一个<el-tag style="color:#0b915d" type="info" size="default">javascript</el-tag>过滤文件类型的函数:
                      <br>
                      <el-card style="max-width:750px; background-color:#282C34; max-height: 1900px;" body-style="line-height: 2; padding: 16px;">
                        <pre class="code-block">
                          <code>
<span class="token keyword">function</span> <span class="token function">checkFile</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">{</span><br>
&nbsp;&nbsp;&nbsp;&nbsp;<span class="token keyword">var</span> <span class="token variable">file</span> <span class="token operator">=</span> document.<span class="token function">getElementById</span><span class="token punctuation">(</span><span class="token string">'file-input'</span><span class="token punctuation">)</span>.<span class="token property">files</span><span class="token punctuation">[</span><span class="token number">0</span><span class="token punctuation">]</span>.<span class="token property">name</span><span class="token comment">//文件名</span><br>
&nbsp;&nbsp;&nbsp;&nbsp;<span class="token keyword">if</span> <span class="token punctuation">(</span><span class="token operator">!</span><span class="token variable">file</span> <span class="token operator">||</span> <span class="token variable">file</span> <span class="token operator">==</span> <span class="token string">''</span><span class="token punctuation">)</span> <span class="token punctuation">{</span><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="token keyword">return</span> <span class="token boolean">false</span><span class="token punctuation">;</span><br>
&nbsp;&nbsp;&nbsp;&nbsp;<span class="token punctuation">}</span><br>
&nbsp;&nbsp;&nbsp;&nbsp;<span class="token keyword">var</span> <span class="token variable">sfx</span> <span class="token operator">=</span> <span class="token variable">file</span>.<span class="token function">substring</span><span class="token punctuation">(</span><span class="token variable">file</span>.<span class="token function">lastIndexOf</span><span class="token punctuation">(</span><span class="token string">'.'</span><span class="token punctuation">)</span><span class="token punctuation">)</span><span class="token punctuation">;</span><span class="token comment">//文件后缀</span><br>
&nbsp;&nbsp;&nbsp;&nbsp;<span class="token keyword">var</span> <span class="token variable">allow_list</span> <span class="token operator">=</span> <span class="token punctuation">[</span><span class="token string">".jpg"</span><span class="token punctuation">,</span> <span class="token string">".jpeg"</span><span class="token punctuation">,</span> <span class="token string">".png"</span><span class="token punctuation">]</span><span class="token punctuation">;</span><br>
&nbsp;&nbsp;&nbsp;&nbsp;<span class="token keyword">if</span> <span class="token punctuation">(</span><span class="token variable">allow_list</span>.<span class="token function">includes</span><span class="token punctuation">(</span><span class="token variable">sfx</span><span class="token punctuation">)</span><span class="token punctuation">)</span> <span class="token punctuation">{</span><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="token keyword">return</span> <span class="token boolean">true</span><span class="token punctuation">;</span><br>
&nbsp;&nbsp;&nbsp;&nbsp;<span class="token punctuation">}</span> <span class="token keyword">else</span> <span class="token punctuation">{</span><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="token keyword">return</span> <span class="token boolean">false</span><span class="token punctuation">;</span><br>
&nbsp;&nbsp;&nbsp;&nbsp;<span class="token punctuation">}</span><br>
<span class="token punctuation">}</span>
                          </code>
                        </pre>
                      </el-card>
                      <br>
                      <br>
                    </div>
                    <div id="p3" style="height: 900px;">
                      <h1 class="title">绕过手段</h1>
                      <el-divider />
                      <div id="p3-1" style="height: 550px;">
                        <h2 class="sub-title">简单的代码审计</h2>
                        <el-tag style="color:#fff" type="info" size="default">f12</el-tag>查看网页源代码,观察<el-tag style="color:#e1567b" type="info" size="default">&lt;script&gt;</el-tag>标签内有无类似过滤文件后缀功能的代码
                        <br>
                        如果存在,试一试让浏览器阻止<el-tag style="color:#0b915d" type="info" size="default">javascript</el-tag>
                        <br>
                        比如:
                        <br>
                        <el-tag style="color:#e1567b" type="info" size="default">Edge浏览器</el-tag>: 设置中搜索<el-tag style="color:#0b915d" type="info" size="default">javascript</el-tag>,选择阻止
                        <br>
                        <el-tag style="color:#e1567b" type="info" size="default">Firefox浏览器</el-tag>: 地址栏输入<el-tag style="color:#fff" type="info" size="default">about:config</el-tag>,搜索<el-tag style="color:#0b915d" type="info" size="default">javascript.enable</el-tag>,改为<el-tag style="color:#e1567b" type="info" size="default">false</el-tag>
                        <br>
                        然后就可以尝试上传啦
                      </div>
                      <div id="part4" style="height: 200px;"></div>
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
      <el-menu-item index="p2">JavaScript检验绕过</el-menu-item>
      <el-menu-item index="p3">绕过手段</el-menu-item>
      <el-menu-item index="p3-1" class="sub-menu-item">— 代码审计</el-menu-item>
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
      const sections = ['p2', 'p3', 'p3-1'];
      const scrollPosition = window.scrollY || window.pageYOffset;
      
      for (const section of sections) {
        const element = document.getElementById(section);
        if (element) {
          const offsetTop = element.offsetTop;
          const offsetHeight = element.offsetHeight;
          
          if (scrollPosition >= offsetTop - 60 && scrollPosition < offsetTop + offsetHeight - 60) {
            this.activeAnchor = section;
            break;
          }
        }
      }
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