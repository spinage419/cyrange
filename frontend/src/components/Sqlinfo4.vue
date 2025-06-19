<template>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;"></div>
                                        <div id="part2" style="height: 650px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <h1 class="title">原理</h1>
                                                        <el-divider />
                                                        报错注入的存在基础是两个 <el-tag style="color:#e1567b" type="info"
                                                                size="default"><span
                                                                        style="font-size: 13px;">"无加区别"</span></el-tag>，即:
                                                        <br>
                                                        <ul>
                                                                <li>
                                                                        后端将前端的输入<el-tag style="color:#e1567b"
                                                                                type="info" size="default"><span
                                                                                        style="font-size: 13px;">"无加区别"</span></el-tag>地拼接在<el-tag
                                                                                style="color:#fff" type="info"
                                                                                size="default">SQL</el-tag>中
                                                                </li>
                                                                <li>
                                                                        数据库将执行的结果返回给后端，后端将数据库执行结果 <el-tag
                                                                                style="color:#e1567b"
                                                                                type="info" size="default"><span
                                                                                        style="font-size: 13px;">"无加区别"</span></el-tag>地回显到前端
                                                                </li>
                                                        </ul>
                                                        我们需要做的便是，构造语句，让错误信息中夹杂着可以显示数据库内容的查询语句
                                                </el-text>
                                        </div>
                                </div>
                        </el-col>
                        <el-col :span="5">
                                <el-menu :default-active="activeAnchor" class="tech-anchor" mode="vertical"
                                        @select="handleAnchorSelect">
                                        <el-menu-item-group title="本页目录">
                                                <el-menu-item index="part0"></el-menu-item>
                                                <el-menu-item index="part2">原理</el-menu-item>
                                        </el-menu-item-group>
                                </el-menu>
                        </el-col>
                </el-row>
        </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
data() {
        return {
                tableData: [
                        {
                                table_name: "数据表名",
                                table_schema: "数据表所属的数据库名"
                        },
                ],
                size: '',
                activeAnchor: 'part0'
        };
},
mounted() {
        this.$nextTick(() => {
                this.containerRef = this.$refs.containerRef;
        });
},
methods: {
        handleAnchorSelect(key) {
                const target = document.getElementById(key);
                if (target) {
                        target.scrollIntoView({ behavior: 'smooth' });
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
font-size: 15px;
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
background: rgba(10, 17, 40, 0.7);
border-radius: 4px;
padding: 15px;
box-shadow: 0 0 15px rgba(0, 123, 255, 0.1);
border-left: 1px solid rgba(0, 123, 255, 0.3);

.el-menu-item {
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
}

/* 响应式调整 */
@media (max-width: 768px) {
.tech-anchor {
        padding: 10px;

        .el-menu-item:hover::before {
                display: none;
        }
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
        box-shadow: 0 0 5px #00a2ff;
}

to {
        box-shadow: 0 0 15px #00a2ff;
}
}
</style> 