<template>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;">
                                        </div>
                                        <div id="part2" style="height: 600px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2-1" style="height: 250px;">
                                                                <h1 class="title">原理</h1>
                                                                <el-divider />
                                                                web页面只返回一个正常页面,利用页面的<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">响应时间</el-tag>不同,逐个猜解数据
                                                        </div>
                                                        <div id="p2-1-1" style="height: 650px;">
                                                                <h2 class="sub-title">if()</h2>
                                                                在<el-tag style="color:#fff" type="info"
                                                                        size="default">MySQL</el-tag>中,<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">if()</el-tag>是一个函数,类似于<el-tag
                                                                        style="color:#fff" type="info"
                                                                        size="default">C</el-tag>,<el-tag
                                                                        style="color:#fff" type="info"
                                                                        size="default">PHP</el-tag>等语言中的三目操作符<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">? :</el-tag>
                                                                <el-card
                                                                        style="max-width:750px;background-color:#282C34;max-height: 900px;">
                                                                        <span class="cmt">
                                                                                /**<br>
                                                                                * <span
                                                                                        class="key">@param</span>
                                                                                Condition: 布尔类型的条件<br>
                                                                                * <span
                                                                                        class="key">@param</span>
                                                                                exp1: Condition为true返回的值<br>
                                                                                * <span
                                                                                        class="key">@param</span>
                                                                                exp2: Condition为false返回的值<br>
                                                                                */
                                                                        </span><br>
                                                                        <span class="code"><span
                                                                                        class="func">if</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">Condition,exp1,exp2</span>)</span></span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p2-1-2" style="height:560px">
                                                                <h2 class="sub-title">sleep()</h2>
                                                                在<el-tag style="color:#fff" type="info"
                                                                        size="default">MySQL</el-tag>中,<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">sleep()</el-tag>是一个函数,休眠指定时间后再执行
                                                                <el-card
                                                                        style="max-width:750px;background-color:#282C34;max-height: 900px;">
                                                                        <span class="cmt">
                                                                                /**<br>
                                                                                * <span
                                                                                        class="key">@param</span>
                                                                                second:
                                                                                暂停的时间长度,以秒为单位,可以是整数或小数<br>
                                                                                */
                                                                        </span><br>
                                                                        <span class="code"><span
                                                                                        class="func">sleep</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">second</span>)</span></span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p2-1-3" style="height:750px">
                                                                <h2 class="sub-title">ascii()</h2>
                                                                在<el-tag style="color:#fff" type="info"
                                                                        size="default">MySQL</el-tag>中,<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">ascii()</el-tag>是一个函数,用于获取指定字符的<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">ascii</el-tag>值<br>
                                                                在盲注中,可以结合<el-tag style="color:#0b915d"
                                                                        type="info"
                                                                        size="default">substring()</el-tag>对关键信息的值进行猜解<br>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="default">'0' ~ '9'</el-tag> :
                                                                <el-tag style="color:#8e5fa0" type="info"
                                                                        size="default">48 ~ 57</el-tag><br>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="default">'A' ~ 'B'</el-tag> :
                                                                <el-tag style="color:#8e5fa0" type="info"
                                                                        size="default">65 ~ 90</el-tag><br>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="default">'a' ~ 'b'</el-tag> :
                                                                <el-tag style="color:#8e5fa0" type="info"
                                                                        size="default">97 ~ 122</el-tag><br>
                                                                <el-card
                                                                        style="max-width:750px;background-color:#282C34;max-height: 900px;">
                                                                        <span class="cmt">
                                                                                /**<br>
                                                                                * <span
                                                                                        class="key">@param</span>
                                                                                char: 要被转换为ASCII的字符<br>
                                                                                */
                                                                        </span><br>
                                                                        <span class="code"><span
                                                                                        class="func">ascii</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">char</span>)</span></span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p2-2" style="height: 500px;">
                                                                <h1 class="title">攻击方式</h1>
                                                                <el-divider />
                                                                利用<el-tag style="color:#0b915d" type="info"
                                                                        size="default">substring()</el-tag>逐字符获取想查询的信息;将分割下的字符放入<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">ascii()</el-tag>中获取<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">ascii</el-tag>值<br>
                                                                将<el-tag style="color:#e1567b" type="info"
                                                                        size="default">ascii</el-tag>放入<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">if()</el-tag>中猜解大小,在<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">if()</el-tag>中的两个返回值处放上能明显看出响应时间差异的<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">sleep()</el-tag><br>
                                                                根据响应时间差异判断猜解是否正确<br>
                                                                比如:<br>
                                                                <el-tag style="color:#fff;font-size: 15px;"
                                                                        type="info" size="default">?id=1 and
                                                                        <span class="key">if</span><span
                                                                                class="lev0">(</span> <span
                                                                                class="key">ascii</span><span
                                                                                class="lev1">(</span> <span
                                                                                class="key">substring</span><span
                                                                                class="lev2">(</span> 'abcd',1,1
                                                                        <span class="lev2">)</span> <span
                                                                                class="lev1">)</span> > 97,
                                                                        <span class="key">sleep</span><span
                                                                                class="lev1">(</span>0<span
                                                                                class="lev1">)</span>, <span
                                                                                class="key">sleep</span><span
                                                                                class="lev1">(</span>5<span
                                                                                class="lev1">)</span> <span
                                                                                class="lev0">)</span></el-tag><br>
                                                                将'abcd'换成要查询的命令语句<br>
                                                                <el-tag style="color:#1bac74;font-size: 15px;"
                                                                        type="info"
                                                                        size="default">ps:可以f12进入开发者模式,在网络中查看页面响应时间长短</el-tag>
                                                        </div>
                                                </el-text>
                                                <div id="part2-3" style="height: 300px;">
                                                </div>
                                        </div>
                                </div>
                        </el-col>
                        <el-col :span="5">
                                <el-menu :default-active="activeAnchor" class="tech-anchor" mode="vertical"
                                        @select="handleAnchorSelect">
                                        <el-menu-item-group title="本页目录">
                                                <el-menu-item index="p2-1">原理</el-menu-item>
                                                <el-menu-item index="p2-1-1">if()</el-menu-item>
                                                <el-menu-item index="p2-1-2">sleep()</el-menu-item>
                                                <el-menu-item index="p2-1-3">ascii()</el-menu-item>
                                                <el-menu-item index="p2-2">攻击方式</el-menu-item>
                                        </el-menu-item-group>
                                </el-menu>
                        </el-col>
                </el-row>
        </div>
</template>

<script>
import { ElMessage } from 'element-ui';

export default {
data() {
        return {
                input: '',
                activeAnchor: 'p2-1',
                containerRef: null
        };
},
methods: {
        handleAnchorSelect(key) {
                const element = document.getElementById(key);
                if (element) {
                        element.scrollIntoView({ behavior: 'smooth' });
                }
        },
        error1() {
                ElMessage({
                        message: "You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''' at line 1",
                        type: 'error'
                });
        },
        error2() {
                ElMessage({
                        message: 'You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near """ at line 1',
                        type: 'error'
                });
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

/* 锚点容器样式 */
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

/* 锚点标题样式 */
.anchor-title {
color: #d1e7ff !important;
font-family: 'Cascadia Code', '微软雅黑';
margin: 0 0 15px 0;
padding-bottom: 10px;
border-bottom: 1px solid rgba(0, 123, 255, 0.3);
text-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

/* 锚点链接基础样式 */
.tech-anchor :deep(.el-anchor-link) {
margin: 8px 0;
}

.tech-anchor :deep(.el-anchor-link__title) {
color: #a0c4ff !important;
font-family: 'Cascadia Code', '微软雅黑';
font-size: 0.9em;
padding: 5px 10px;
border-radius: 3px;
transition: all 0.3s ease;
position: relative;
}

/* 锚点悬停效果 */
.tech-anchor :deep(.el-anchor-link__title:hover) {
color: #ffffff !important;
background: rgba(0, 123, 255, 0.2);
text-shadow: 0 0 5px #007bff;
transform: translateX(5px);
}

.tech-anchor :deep(.el-anchor-link__title:hover)::before {
content: '>';
position: absolute;
left: -10px;
color: #00a2ff;
opacity: 0;
animation: blink-caret 0.8s step-end infinite alternate;
}

/* 激活锚点样式 */
.tech-anchor :deep(.el-anchor-link.active .el-anchor-link__title) {
color: #ffffff !important;
background: rgba(0, 123, 255, 0.3);
text-shadow: 0 0 10px #00a2ff;
border-left: 2px solid #007bff;
padding-left: 15px;
animation: pulse-glow 2s infinite alternate;
}

/* 子链接样式 */
.tech-anchor :deep(.el-anchor-link .el-anchor-link) {
margin-left: 20px;
}

.tech-anchor :deep(.el-anchor-link .el-anchor-link__title) {
font-size: 0.85em;
color: #8ab4f8 !important;
}

/* 标记线样式 */
.tech-anchor :deep(.el-anchor__marker) {
background: linear-gradient(to bottom, transparent, #007bff, transparent);
opacity: 0.7;
width: 2px !important;
left: 0 !important;
}

/* 响应式调整 */
@media (max-width: 768px) {
.tech-anchor {
        padding: 10px;
}

.tech-anchor :deep(.el-anchor-link__title:hover)::before {
        display: none;
}
}
</style>