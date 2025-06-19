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
                                                        <div id="p2" style="height: 1100px;">
                                                                <h1 class="title">反射型XSS</h1>
                                                                <el-divider />
                                                                <br>
                                                                <h2 class="sub-title" id="p2-1">漏洞原理</h2>
                                                                <img src="../assets/reflect_xss.png"
                                                                        style="width: 800px;height: auto;">
                                                                <br>
                                                                攻击者通过构造包含恶意脚本的
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="large">URL</el-tag>,诱导用户点击,当用户点击该<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="large">URL</el-tag>时,服务器会将恶意脚本作为响应内容返回给浏览器<br>浏览器会直接执行这些恶意脚本,从而达到窃取用户信息等目的<br>
                                                                <br>
                                                                反射型<el-tag style="color:#e1567b" type="info"
                                                                        size="large">XSS</el-tag>漏洞通常是由于服务器端没有对用户输入进行充分的验证和过滤,直接将用户输入的内容反射到响应页面中导致的<br>
                                                                攻击者可以通过构造恶意的<el-tag style="color:#e1567b" type="info"
                                                                        size="large">URL</el-tag>参数,将恶意脚本注入到页面中<br>
                                                        </div>
                                                        <div id="p3" style="height: 1650px;">
                                                                <h1 class="title">产生反射型XSS的地方</h1>
                                                                <el-divider />
                                                                <br>
                                                                <ul>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-0">搜索框</h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                当用户在搜索框中输入关键词时,服务器会将搜索结果显示在页面上.如果服务器没有对用户输入进行过滤,攻击者可以在搜索框中输入恶意脚本,服务器会将该脚本作为搜索结果的一部分返回给浏览器,从而触发反射型<span
                                                                                                        class="key">XSS</span>攻击.<br>
                                                                                                例如:<br>
                                                                                                正常的搜索<span
                                                                                                        class="key">URL</span>:<span
                                                                                                        class="txt">http://test.com/search?a=abc</span><br>
                                                                                                恶意的搜索<span
                                                                                                        class="key">URL</span>:<span
                                                                                                        class="txt">http://test.com/search?a=&lt;<span
                                                                                                                class="func">script</span>&gt;<span
                                                                                                                class="num">alert</span><span
                                                                                                                class="v">('XSS')</span>&lt;/<span
                                                                                                                class="func">script</span>&gt;</span>
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-1">表单提交
                                                                                </h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                当用户提交表单时,服务器会将表单数据作为响应内容返回给浏览器.如果服务器没有对表单数据进行过滤,攻击者可以在表单中输入恶意脚本,服务器会将该脚本作为响应内容返回给浏览器,从而触发反射型<span
                                                                                                        class="key">XSS</span>攻击.<br>
                                                                                                例如:<br>
                                                                                                正常的表单提交<span
                                                                                                        class="key">URL</span>:<span
                                                                                                        class="txt">http://form.com/form?a=abc<span
                                                                                                                class="key"> &
                                                                                                        </span>b=john@example.com</span><br>
                                                                                                恶意的表单提交<span
                                                                                                        class="key">URL</span>:<span
                                                                                                        class="txt">http://form.com/form?a=&lt;<span
                                                                                                                class="func">script</span>&gt;<span
                                                                                                                class="num">alert</span><span
                                                                                                                class="v">('XSS')</span>&lt;/<span
                                                                                                                class="func">script</span>&gt;
                                                                                                        &nbsp;
                                                                                                        &nbsp;
                                                                                                        &nbsp;
                                                                                                        &nbsp;
                                                                                                        &nbsp;
                                                                                                        &nbsp;
                                                                                                        &nbsp;
                                                                                                        &nbsp;
                                                                                                        &nbsp;
                                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                                class="key">&
                                                                                                        </span>b=john@example.com</span>
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-2">URL 参数
                                                                                </h2>
                                                                                <el-card style="max-width:750px; background-color:#282C34;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                很多网站会根据<span
                                                                                                        class="key">URL</span>
                                                                                                参数来显示不同的内容.如果服务器没有对<span
                                                                                                        class="key">URL</span>
                                                                                                参数进行过滤,攻击者可以通过构造恶意的<span
                                                                                                        class="key">URL</span>
                                                                                                参数,将恶意脚本注入到页面中.<br>
                                                                                                例如:<br>
                                                                                                正常的<span
                                                                                                        class="key">URL</span>:<span
                                                                                                        class="txt">https://example.com/page?id=1</span><br>
                                                                                                恶意的<span
                                                                                                        class="key">URL</span>:<span
                                                                                                        class="txt">https://example.com/page?id=&lt;<span
                                                                                                                class="func">script</span>&gt;<span
                                                                                                                class="num">alert</span><span
                                                                                                                class="v">('XSS')</span>&lt;/<span
                                                                                                                class="func">script</span>&gt;</span>
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
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
                                                <el-menu-item index="p2">反射型XSS</el-menu-item>
                                                <el-sub-menu index="p2-sub">
                                                        <template #title>
                                                                <span>反射型XSS - 子菜单</span>
                                                        </template>
                                                        <el-menu-item index="p2-1">漏洞原理</el-menu-item>
                                                </el-sub-menu>
                                                <el-menu-item index="p3">产生反射型XSS的地方</el-menu-item>
                                                <el-sub-menu index="p3-sub">
                                                        <template #title>
                                                                <span>产生反射型XSS的地方 - 子菜单</span>
                                                        </template>
                                                        <el-menu-item index="p3-0">搜索框</el-menu-item>
                                                        <el-menu-item index="p3-1">表单提交</el-menu-item>
                                                        <el-menu-item index="p3-2">URL参数</el-menu-item>
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
                        input: '',
                        activeAnchor: '',
                        anchorItems: [
                                { id: 'p2', offsetTop: 0 },
                                { id: 'p2-1', offsetTop: 0 },
                                { id: 'p3', offsetTop: 0 },
                                { id: 'p3-0', offsetTop: 0 },
                                { id: 'p3-1', offsetTop: 0 },
                                { id: 'p3-2', offsetTop: 0 }
                        ]
                };
        },
        mounted() {
                this.$nextTick(() => {
                        this.anchorItems.forEach(item => {
                                const element = document.getElementById(item.id);
                                if (element) {
                                        item.offsetTop = element.offsetTop;
                                }
                        });
                        this.$refs.containerRef.addEventListener('scroll', this.handleScroll);
                });
        },
        beforeDestroy() {
                this.$refs.containerRef.removeEventListener('scroll', this.handleScroll);
        },
        methods: {
                submit() {
                        if (this.input) {
                                document.getElementById('xss-test').innerHTML = this.input;
                                console.log(document.getElementById('xss-test').innerHTML);
                                XssTest(this.input);
                        }
                },
                handleAnchorSelect(key) {
                        const element = document.getElementById(key);
                        if (element) {
                                this.$refs.containerRef.scrollTop = element.offsetTop;
                        }
                },
                handleScroll() {
                        const scrollTop = this.$refs.containerRef.scrollTop;
                        let activeId = '';
                        for (let i = this.anchorItems.length - 1; i >= 0; i--) {
                                if (scrollTop >= this.anchorItems[i].offsetTop) {
                                        activeId = this.anchorItems[i].id;
                                        break;
                                }
                        }
                        this.activeAnchor = activeId;
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
}</style>