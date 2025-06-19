<template>
  <div>
    <div id="xss-test"></div>
    <el-row>
      <!-- 主要内容区域 -->
      <el-col :span="19">
        <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
          <div id="part0" style="height: 100px;"></div>

          <div id="part2" style="height: 650px;">
            <el-text size="large" style="color:#fff; line-height: 3.6;">
              <div id="p2" style="height: 900px;">
                <h1 class="title">DOM型XSS</h1>
                <el-divider />
                <br>
                <h2 class="sub-title" id="p2-1">漏洞原理</h2>
                <img src="../assets/dom_xss.png"
                  style="width: 800px;height: auto;">
                <br>
                <el-tag style="color:#239af5" type="info"
                size="large">DOM</el-tag>型<el-tag style="color:#e1567b" type="info"
                size="large">XSS</el-tag>攻击是指攻击者通过构造包含恶意脚本的<el-tag style="color:#e1567b" type="info"
                size="large">URL</el-tag>,诱导用户点击.当用户点击该<el-tag style="color:#e1567b" type="info"
                size="large">URL</el-tag>
                时,浏览器会直接执行这些恶意脚本.<br>
                与反射型<el-tag style="color:#e1567b" type="info"
                size="large">XSS</el-tag>和存储型<el-tag style="color:#e1567b" type="info"
                size="large">XSS</el-tag>不同,<el-tag style="color:#239af5" type="info"
                size="large">DOM</el-tag>
                型<el-tag style="color:#e1567b" type="info"
                size="large">XSS</el-tag>的攻击代码不需要经过服务器处理,而是直接在浏览器的<el-tag style="color:#239af5" type="info"
                size="large">DOM</el-tag>环境中执行.
                <br>
                <el-tag style="color:#239af5" type="info"
                size="large">DOM</el-tag>型<el-tag style="color:#e1567b" type="info"
                size="large">XSS</el-tag>漏洞通常是由于前端代码对用户输入的数据没有进行充分的验证和过滤,直接将用户输入的内容插入到
                <el-tag style="color:#239af5" type="info"
                size="large">DOM</el-tag>中导致的.<br>
                攻击者可以通过构造恶意的<el-tag style="color:#e1567b" type="info"
                size="large">URL</el-tag>参数,将恶意脚本注入到页面中.
                <br>
              </div>
              <div id="p3" style="height: 1550px;">
                <h1 class="title">产生DOM型XSS的地方</h1>
                <el-divider />
                <br>
                <ul>
                  <li>
                    <h2 class="sub-title" id="p3-0">URL参数</h2>
                    <el-card style="max-width:750px; background-color:#282C34;"
                      body-style="line-height: 2">
                      <span class="code">
                        很多网站会根据<span class="key"> URL</span>
                        参数来显示不同的内容.如果前端代码没有对<span class="key"> URL</span>
                        参数进行过滤,攻击者可以通过构造恶意的<span class="key"> URL</span>
                        参数,将恶意脚本注入到页面中.<br>
                        例如:<br>
                        正常的<span class="key"> URL</span>: <span
                          class="txt">http://example.com/page?param=value</span><br>
                        恶意的<span class="key"> URL</span>: <span
                          class="txt">http://example.com/page?param=&lt;<span class="func">script</span>&gt;<span class="num">alert</span><span class="v">('XSS')</span>&lt;/<span class="func">script</span>&gt;</span>
                      </span>
                    </el-card>
                  </li>
                  <br>
                  <li>
                    <h2 class="sub-title" id="p3-1">表单提交</h2>
                    <el-card style="max-width:750px; background-color:#282C34;"
                      body-style="line-height: 2">
                      <span class="code">
                        当用户提交表单时,前端代码会将表单数据作为响应内容返回给浏览器.如果前端代码没有对表单数据进行过滤,攻击者可以在表单中输入恶意脚本,浏览器会将该脚本作为响应内容返回给浏览器,从而触发
                        <span class="key">DOM </span>型<span class="key"> XSS </span>攻击.<br>
                        例如:<br>
                        攻击者可以在表单中输入
                        &lt;<span class="func">script</span>&gt;<span class="num">alert</span><span class="v">('XSS')</span>&lt;/<span class="func">script</span>&gt;,当用户提交表单时,浏览器会执行该脚本.
                      </span>
                    </el-card>
                  </li>
                  <br>
                  <li>
                    <h2 class="sub-title" id="p3-2">本地存储</h2>
                    <el-tag style="color:#239af5" type="info"
                    size="large">localStorage</el-tag>和<el-tag style="color:#239af5" type="info"
                    size="large">sessionStorage</el-tag>是Web浏览器提供的两种用于在客户端存储数据的存储机制.<br>
                    它们属于<el-tag style="color:#e1567b" type="info"
                    size="large">Web Storage API</el-tag>,允许网页在用户的浏览器中存储键值对数据
                    <el-card style="max-width:750px; background-color:#282C34;"
                      body-style="line-height: 2">
                      <span class="code">
                        前端代码可以使用本地存储(如
                        <span class="v">localStorage</span>、<span class="v">sessionStorage</span>)来存储用户数据.如果前端代码没有对存储的数据进行过滤,攻击者可以通过构造恶意的数据,将恶意脚本注入到页面中.<br>
                        例如:<br>
                        攻击者可以在本地存储中设置一个键值对:<br>
                        <span class="txt"><span class="v">localStorage</span>.<span class="num">setItem</span><span class="lev2">(</span>'<span class="v">malicious</span>',
                          '&lt;<span class="func">script</span>&gt;<span class="num">alert</span><span class="lev2">(</span>'<span class="v">XSS</span>'<span class="lev2">)</span>&lt;/<span class="func">script</span>&gt;'<span class="lev2">)</span></span><br>
                          当页面读取该数据时,浏览器会执行该脚本.
                      </span>
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
            <el-menu-item index="p2">DOM型XSS</el-menu-item>
            <el-menu-item index="p2-1">↳ 漏洞原理</el-menu-item>
            <el-menu-item index="p3">产生DOM型XSS的地方</el-menu-item>
            <el-menu-item index="p3-0">↳ URL参数</el-menu-item>
            <el-menu-item index="p3-1">↳ 表单提交</el-menu-item>
            <el-menu-item index="p3-2">↳ 本地存储</el-menu-item>
          </el-menu-item-group>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>
      
      <script>
      import { ElMessage } from 'element-ui';
      import { XssTest } from '@/script/xss';
      
      export default {
        data() {
          return {
            containerRef: null,
            activeAnchor: 'p2',
            input: ''
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
            const sections = ['p2', 'p2-1', 'p3', 'p3-0', 'p3-1', 'p3-2'];
            const scrollPosition = window.scrollY || document.documentElement.scrollTop;
            
            for (let i = sections.length - 1; i >= 0; i--) {
              const section = document.getElementById(sections[i]);
              if (section && scrollPosition >= section.offsetTop - 100) {
                this.activeAnchor = sections[i];
                break;
              }
            }
          },
          submit() {
            if (this.input) {
              document.getElementById('xss-test').innerHTML = this.input;
              console.log(document.getElementById('xss-test').innerHTML);
              XssTest(this.input);
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