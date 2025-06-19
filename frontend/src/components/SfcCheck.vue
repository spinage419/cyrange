<template>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto"
                                        @scroll="handleScroll">
                                        <div id="part0" style="height: 100px;"></div>
                                        <div id="part2" style="height: 650px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2" style="height: 2350px;">
                                                                <h1 class="title">文件后缀黑名单检验绕过</h1>
                                                                <el-divider />
                                                                黑名单中存储着危险文件的后缀,如<el-tag style="color:#fff" type="info"
                                                                        size="default">php</el-tag>,<el-tag style="color:#fff"
                                                                        type="info" size="default">asp</el-tag>,<el-tag
                                                                        style="color:#fff" type="info"
                                                                        size="default">jsp</el-tag>,当上传的文件后缀名在黑名单中时,便会上传失败
                                                                <br>
                                                                这是一个典型的黑名单:<br>
                                                                <el-card style="max-width:750px; background-color:#282C34; max-height: 1900px;"
                                                                        body-style="line-height: 2">
                                                                        <span class="code">
                                                                                $blacklist <span class="key">=</span> <span
                                                                                        class="lev0">[</span>'<span
                                                                                        class="lev0">php</span>','<span
                                                                                        class="lev0">asp</span>','<span
                                                                                        class="lev0">jsp</span>','<span
                                                                                        class="lev0">html</span>'<span
                                                                                        class="lev0">]</span>;
                                                                        </span>
                                                                </el-card>
                                                                <br>
                                                                <h2 id="p2-0" class="sub-title">漏洞成因与绕过方法</h2>
                                                                <ul>
                                                                        <li>
                                                                                <h3 class="sub-sub-title">
                                                                                        将上传文件的后缀名不做处理直接与黑名单元素比较</h3>
                                                                                以去空<el-tag style="color:#0b915d" type="info"
                                                                                        size="default">trim()</el-tag>举例,如果不进行去空,在直接比较的情况下,<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">php</el-tag>与<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">php&nbsp;&nbsp;</el-tag>就会不一样<br>
                                                                                也就是说,在恶意文件后缀中添加空格,就能绕过检查<br>
                                                                                另一种情况是,通过大小写绕过<br>
                                                                                由于只是简单地比较字符串之间是否相等,改变文件后缀大小写,就能绕过黑名单的过滤
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h3 class="sub-sub-title">
                                                                                        只是简单地对恶意文件的后缀名进行处理就放行</h3>
                                                                                比如,只是简单地将匹配到黑名单的文件后缀部分替换为空格,就像这样:<br>
                                                                                <el-tag style="color:#fff" type="info"
                                                                                        size="default">1.<span
                                                                                                class="key">php</span></el-tag>处理后变为<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">1.</el-tag><br>
                                                                                看似已经安全了,但是危险的代码仍然在文件中未被处理<br>
                                                                                如果在危险后缀中间添加字符,使文件后缀被处理后被重新拼接成新的危险文件后缀<br>
                                                                                就相当于上传了危险的文件到对方的服务器上<br>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h3 class="sub-sub-title">对恶意文件后缀列举不完整</h3>
                                                                                以<el-tag style="color:#fff" type="info"
                                                                                        size="default">php</el-tag>文件为例,可被当<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">php</el-tag>文件的有<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">php3</el-tag>,<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">php5</el-tag>,<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">php7</el-tag>,<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">phtml</el-tag><br>
                                                                                如果黑名单中列举不完整,那么攻击者就可以使用其他具有同样效果的文件后缀进行绕过
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h3 class="sub-sub-title">没有禁止上传一些敏感配置文件</h3>
                                                                                对于<el-tag style="color:#0b915d" type="info"
                                                                                        size="default">Apache</el-tag>服务器来说,<el-tag
                                                                                        style="color:#0b915d" type="info"
                                                                                        size="default">.htaccess</el-tag>是<el-tag
                                                                                        style="color:#0b915d" type="info"
                                                                                        size="default">Apache</el-tag>的分布式配置文件<br>
                                                                                具有优先级最高,即时生效的特点<br>
                                                                                如果没有禁止上传<el-tag style="color:#0b915d"
                                                                                        type="info"
                                                                                        size="default">.htaccess</el-tag>文件,那么攻击者就可以自己构建解析文件的规则<br>
                                                                                比如上传一个有以下内容的<el-tag style="color:#0b915d"
                                                                                        type="info"
                                                                                        size="default">.htaccess</el-tag>文件
                                                                                <el-card style="max-width:750px; background-color:#282C34; max-height: 1900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">SetHandler
                                                                                                application/x-httpd-php</span>
                                                                                </el-card>
                                                                                这会使上传到服务器的任何文件都被当成<el-tag style="color:#fff"
                                                                                        type="info"
                                                                                        size="default">PHP</el-tag>文件执行<br>
                                                                                此时如果上传一个伪装成<el-tag style="color:#fff"
                                                                                        type="info"
                                                                                        size="default">.jpg</el-tag>的恶意代码文件,那么服务器就危险了
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h3 class="sub-sub-title">
                                                                                        利用了Apache服务器本身解析文件后缀的特性</h3>
                                                                                <el-tag style="color:#0b915d" type="info"
                                                                                        size="default">Apache</el-tag>面对多拓展名时,会从右向左解析,直到遇到能识别解析的后缀名<br>
                                                                                比如<el-tag style="color:#fff" type="info"
                                                                                        size="default">flag.php.rar.zip</el-tag>,<el-tag
                                                                                        style="color:#0b915d" type="info"
                                                                                        size="default">Apache</el-tag>并不能解析<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">.rar</el-tag>和<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">.zip</el-tag>,因此该文件会被当作<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">flag.php</el-tag>
                                                                        </li>
                                                                </ul>
                                                                <br>
                                                                <br>
                                                        </div>
                                                        <div id="p3" style="height: 900px;">
                                                                <h1 class="title">绕过手段</h1>
                                                                <el-divider />
                                                                <div id="p3-1" style="height: 550px;">
                                                                        <h2 class="sub-title">抓包测试</h2>
                                                                        <img src="../assets/mime_demo1.png"
                                                                                style="width: 900px;height: auto;align-content: center;"><br>
                                                                        在<el-tag style="color:#0b915d" type="info"
                                                                                size="default">BurpSuite</el-tag>中打开代理(Proxy),将抓到的上传文件的数据发送到Repeater(右键选择Repeater)<br>
                                                                        <img src="../assets/mime_demo2.png"
                                                                                style="width: 1000px;height: auto;align-content: center;"><br>
                                                                        1.找到<el-tag style="color:#0b915d" type="info"
                                                                                size="default">content-type</el-tag>字段,尝试只修改该字段为允许上传的类型(如<el-tag
                                                                                style="color:#e1567b" type="info"
                                                                                size="default">image/jpeg</el-tag>)<br>
                                                                        <img src="../assets/mime_demo3.png"
                                                                                style="width: 1000px;height: auto;align-content: center;"><br>
                                                                        2.点击send<br>
                                                                        3.查看软件中显示的页面响应是否与上传正常文件的响应一致
                                                                </div>
                                                                <div id="part4" style="height: 200px;"></div>
                                                        </div>
                                                </el-text>
                                        </div>
                                </div>
                        </el-col>
                        <el-col :span="5">
                                <el-menu :default-active="activeAnchor" class="tech-anchor" mode="vertical"
                                        @select="handleAnchorSelect">
                                        <el-menu-item-group title="本页目录">
                                                <el-menu-item index="p2">文件后缀黑名单检验绕过</el-menu-item>
                                                <el-submenu index="p2-0">
                                                        <template #title>漏洞成因与绕过方法</template>
                                                </el-submenu>
                                                <el-menu-item index="p2-0">漏洞成因与绕过方法</el-menu-item>
                                                <el-menu-item index="p3">绕过手段</el-menu-item>
                                                <el-menu-item index="p3-1">抓包测试</el-menu-item>
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
                        input: '',
                        activeAnchor: 'p2',
                        containerRef: null
                };
        },
        mounted() {
                this.containerRef = this.$refs.containerRef;
                this.containerRef.addEventListener('scroll', this.handleScroll);
        },
        beforeDestroy() {
                this.containerRef.removeEventListener('scroll', this.handleScroll);
        },
        methods: {
                handleClick(e) {
                        e.preventDefault();
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
                handleAnchorSelect(index) {
                        const target = document.getElementById(index);
                        if (target) {
                                this.containerRef.scrollTop = target.offsetTop - this.containerRef.offsetTop;
                        }
                },
                handleScroll() {
                        // 更新 sections 数组，包含 p3 和 p3-1
                        const sections = ['p2', 'p2-0', 'p3', 'p3-1'];
                        for (let i = sections.length - 1; i >= 0; i--) {
                                const section = document.getElementById(sections[i]);
                                if (section.offsetTop - this.containerRef.scrollTop <= 30) {
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
}</style>  