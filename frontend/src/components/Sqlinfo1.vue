<template>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff; height: 650px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;"></div>
                                        <div id="part2" style="height: 600px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <h1 class="title">判断方法</h1>
                                                        <el-divider />
                                                        <div id="part2-1" style="height:300px">
                                                                <h2 class="sub-title">名字由来</h2>
                                                                <span style="font-weight: 500;">
                                                                        数字型顾名思义,向<el-tag style="color:#fff"
                                                                                type="info"
                                                                                size="default">SQL</el-tag>中传递的参数是数字而非字符串,不需要使用引号闭合<br>
                                                                </span>
                                                                <el-card
                                                                        style="max-width:600px;background-color:#282C34;max-height: 90px;">
                                                                        <span
                                                                                class="key">select</span>&nbsp;<span
                                                                                class="code">username</span>&nbsp;<span
                                                                                class="key">from</span>&nbsp;<span
                                                                                class="code">users</span>&nbsp;<span
                                                                                class="key">where</span>&nbsp;<span
                                                                                class="code">id&nbsp;<span
                                                                                        class="key">=</span>&nbsp;参数</span>
                                                                </el-card>
                                                        </div>
                                                        <div id="part2-2" style="height: 300px;">
                                                                <h2 class="sub-title">测试判断</h2>
                                                                <span style="font-weight: 500;">
                                                                        如果我们在注入点传入<el-tag style="color:#fff"
                                                                                type="info"
                                                                                size="default">'</el-tag>或<el-tag
                                                                                style="color:#fff" type="info"
                                                                                size="default">"</el-tag>这类闭合符时不引起关于闭合符不匹配的报错;同时构造参数:<br>
                                                                        <el-tag style="color:#fff" type="info"
                                                                                size="default">0 <span
                                                                                        class="key">or</span>&nbsp;1<span
                                                                                        class="key">=</span>1</el-tag>与
                                                                        <el-tag style="color:#fff" type="info"
                                                                                size="default">0 <span
                                                                                        class="key">or</span>&nbsp;1<span
                                                                                        class="key">=</span>2</el-tag>,<br>
                                                                        在数字型中,<el-tag style="color:#fff"
                                                                                type="info"
                                                                                size="default">1<span
                                                                                        class="key">=</span>2</el-tag>会被当作表达式,但是这个表达式的值显然是<el-tag
                                                                                style="color:#7C74C4"
                                                                                type="info"
                                                                                size="default">false</el-tag><br>
                                                                        而在字符型中,则会被当作一个普通的字符串参与查询
                                                                        <br>如果前者能让页面正常回显,而后者不能让页面正常回显,则说明是数字型
                                                                </span>
                                                        </div>
                                                </el-text>
                                                <div id="part2-3" style="height: 300px;"></div>
                                        </div>
                                </div>
                        </el-col>
                        <el-col :span="5">
                                <el-menu :default-active="activeAnchor" class="tech-anchor" mode="vertical"
                                        @select="handleAnchorSelect">
                                        <el-menu-item-group title="本页目录">
                                                <el-menu-item index="part2" title="判断方法">
                                                        <el-menu-item index="part2-1"
                                                                title="名字由来"></el-menu-item>
                                                        <el-menu-item index="part2-2"
                                                                title="测试判断"></el-menu-item>
                                                        <el-menu-item index="part2-3" title=""></el-menu-item>
                                                </el-menu-item>
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
                activeAnchor: 'part2'
        };
},
mounted() {
        this.containerRef = this.$refs.containerRef;
},
methods: {
        handleAnchorSelect(key) {
                const targetElement = document.getElementById(key);
                if (targetElement) {
                        this.containerRef.scrollTop = targetElement.offsetTop;
                }
        }
}
};
</script>

<style lang="less" scoped>
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
color: rgb(99, 97, 97);
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
--el-menu-padding: 0 0 0 20px;
--el-menu-border-width: 0;
background: rgba(10, 17, 40, 0.7);
border-radius: 4px;
padding: 15px;
box-shadow: 0 0 15px rgba(0, 123, 255, 0.1);
border-left: 1px solid rgba(0, 123, 255, 0.3);

.el-menu-item,
.el-submenu__title {
        color: #a0c4ff;
        font-family: 'Cascadia Code', '微软雅黑';
        font-size: 0.9em;
        padding: 5px 10px;
        border-radius: 3px;
        transition: all 0.3s ease;
        position: relative;

        &:hover {
                color: #ffffff;
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
}

.el-menu-item.is-active {
        color: #ffffff;
        background: rgba(0, 123, 255, 0.3);
        text-shadow: 0 0 10px #00a2ff;
        border-left: 2px solid #007bff;
        padding-left: 15px;
        animation: pulse-glow 2s infinite alternate;
}

.el-submenu .el-menu-item {
        margin-left: 20px;
        font-size: 0.85em;
        color: #8ab4f8;
}
}

@keyframes blink-caret {

from,
to {
        opacity: 0;
}

50% {
        opacity: 1;
}
}

@keyframes pulse-glow {
from {
        box-shadow: 0 0 5px #007bff;
}

to {
        box-shadow: 0 0 15px #007bff;
}
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