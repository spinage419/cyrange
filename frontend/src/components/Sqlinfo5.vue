<template>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;"></div>
                                        <div id="part2" style="height: 600px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2-1" style="height: 350px;">
                                                                <h1 class="title">原理</h1>
                                                                <el-divider />
                                                                盲注的场景下,攻击者无法直接从页面上看到数据库的返回结果(例如查询结果或错误信息).<br>
                                                                攻击者需要通过观察应用程序的行为(如页面响应时间、布尔值结果等)来推断数据库中的信息
                                                        </div>
                                                        <div id="p2-2" style="height: 500px;">
                                                                <h1 class="title">攻击方式</h1>
                                                                <el-divider />
                                                                盲注往往涉及大量的字符猜解,手工注入繁琐且效率低下<br>
                                                                可以使用自动化工具<el-tag style="color:#e1567b"
                                                                        type="info"
                                                                        size="default">sqlmap</el-tag>进行检测和利用盲注漏洞
                                                                <el-link type="success"
                                                                        href="https://sqlmap.org/"
                                                                        icon="Link">sqlmap官网</el-link><br>
                                                                <el-tag style="color:#1bac74;font-size: 15px;"
                                                                        type="info"
                                                                        size="default">ps:一开始就使用sqlmap素莫得灵魂的,还是要先体会一下手工注入噢~</el-tag>
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
                                                <el-menu-item index="#p1"></el-menu-item>
                                                <el-menu-item index="#p2-1">原理</el-menu-item>
                                                <el-menu-item index="#p2-2">攻击方式</el-menu-item>
                                        </el-menu-item-group>
                                </el-menu>
                        </el-col>
                </el-row>
        </div>
</template>

<script>
import { ElMessage } from 'element-plus';

export default {
data() {
        return {
                input: '',
                containerRef: null,
                activeAnchor: '',
        };
},
methods: {
        handleAnchorSelect(index) {
                // 处理锚点选择事件
                this.activeAnchor = index;
        },
        error1() {
                ElMessage({
                        message: "You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''' at line 1",
                        type: 'error',
                });
        },
        error2() {
                ElMessage({
                        message: 'You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near """ at line 1',
                        type: 'error',
                });
        },
},
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