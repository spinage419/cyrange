<template>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;">
                                        </div>

                                        <div id="part2" style="height: 1550px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2-1" style="height: 50px;">
                                                                <h1 class="title">session</h1>
                                                                <el-divider />
                                                                当<el-tag style="color:#0b915d" type="info"
                                                                        size="default">session_start()</el-tag>被调用或php.ini中的session.auto_start为1时<br>
                                                                PHP内部调用会话管理器,访问用户session序列化后,存储到指定目录(默认为/tmp)
                                                                <br>
                                                                <br>
                                                                <br>
                                                                <div id="p2-2" style="height:2200px">
                                                                        <h2 class="sub-title">
                                                                                处理器
                                                                        </h2>
                                                                        <!-- <el-table :data="tableData" border
                                                                                style="width: 100%">
                                                                                <el-table-column prop="Sol"
                                                                                        label="处理器" width="180" />
                                                                                <el-table-column prop="Fom"
                                                                                        label="对应存储格式" width="180" />
                                                                        </el-table> -->
                                                                        <div id="p2-2-1" style="height: 700px;">
                                                                                <h3 class="sub-sub-title">php</h3>
                                                                                默认情况下以php格式存储<br>
                                                                                对应的存储格式:<br>
                                                                                <el-tag style="color:#fff" type="info"
                                                                                        size="default">键名 + <span
                                                                                                class="key">|</span> + <span
                                                                                                style="font-family: 'Cascadia';color:#0b915d">serialize()</span>
                                                                                        + 处理后的值</el-tag><br>
                                                                                以下面代码为例:<br>
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                <span class="key">
                                                                                                        &lt;?php<br>
                                                                                                </span>
                                                                                                <span
                                                                                                        class="func">session_start</span><span
                                                                                                        class="lev0">()</span>;<br>
                                                                                                $_SESSION<span
                                                                                                        class="lev0">[<span
                                                                                                                class="code">'</span>name<span
                                                                                                                class="code">'</span>]</span>
                                                                                                <span class="key">=</span>
                                                                                                $_GET<span class="lev0">[<span
                                                                                                                class="code">'</span>arg<span
                                                                                                                class="code">'</span>]</span>;<br>
                                                                                        </span>
                                                                                </el-card>
                                                                                传入参数:<el-tag style="color:#fff" type="info"
                                                                                        size="default">?arg=nihao</el-tag><br>
                                                                                存储格式为:<br>
                                                                                <el-tag style="color:#fff" type="info"
                                                                                        size="default">name<span
                                                                                                class="key">|</span><span
                                                                                                style="font-family: 'Cascadia';color:#0b915d">s:5:"nihao"</span></el-tag><br>
                                                                        </div>
                                                                        <div id="p2-2-2" style="height: 700px;">
                                                                                <h3 class="sub-sub-title">php_serialize(php >=
                                                                                        5.5.4)</h3>

                                                                                对应的存储格式:<br>
                                                                                <el-tag style="color:#fff" type="info"
                                                                                        size="default"><span
                                                                                                style="font-family: 'Cascadia';color:#0b915d">serialize()</span>处理后的数组</el-tag><br>
                                                                                以下面代码为例:<br>
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                <span class="key">
                                                                                                        &lt;?php<br>
                                                                                                </span>
                                                                                                <span
                                                                                                        class="cmt">//声明存储格式为php_serialize</span><br>
                                                                                                <span
                                                                                                        class="key">ini_set</span><span
                                                                                                        class="lev0">(<span
                                                                                                                class="code">'<span
                                                                                                                        class="v">session.serialize_handler</span>','<span
                                                                                                                        class="lev0">php_serialize</span>'</span>)</span>;<br>
                                                                                                <span
                                                                                                        class="func">session_start</span><span
                                                                                                        class="lev0">()</span>;<br>
                                                                                                $_SESSION<span
                                                                                                        class="lev0">[<span
                                                                                                                class="code">'</span>name<span
                                                                                                                class="code">'</span>]</span>
                                                                                                <span class="key">=</span>
                                                                                                $_GET<span class="lev0">[<span
                                                                                                                class="code">'</span>a<span
                                                                                                                class="code">'</span>]</span>;<br>
                                                                                                $_SESSION<span
                                                                                                        class="lev0">[<span
                                                                                                                class="code">'</span>id<span
                                                                                                                class="code">'</span>]</span>
                                                                                                <span class="key">=</span>
                                                                                                $_GET<span class="lev0">[<span
                                                                                                                class="code">'</span>b<span
                                                                                                                class="code">'</span>]</span>;<br>
                                                                                        </span>
                                                                                </el-card>
                                                                                传入参数:<el-tag style="color:#fff" type="info"
                                                                                        size="default">?a=hello&b=world</el-tag><br>
                                                                                存储格式为:<br>
                                                                                <el-tag style="color:#fff" type="info"
                                                                                        size="default"><span
                                                                                                class="key">a</span>:2:{s:4:"name";s:5:"hello";s:2:"id";s:5:"world";}</el-tag><br>
                                                                        </div>
                                                                        <div id="p2-2-3" style="height: 600px;">
                                                                                <h3 class="sub-sub-title">php_binary</h3>
                                                                                对应的存储格式:<br>
                                                                                <el-tag style="color:#fff" type="info"
                                                                                        size="default">键名长度对应的<span
                                                                                                class="key">ASCII</span>字符+键名+<span
                                                                                                style="color:#0b915d">serialize()</span>处理后的值</el-tag><br>
                                                                                以下面代码为例:<br>
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                <span class="key">
                                                                                                        &lt;?php<br>
                                                                                                </span>
                                                                                                <span
                                                                                                        class="cmt">//声明存储格式为php_binary</span><br>
                                                                                                <span
                                                                                                        class="key">ini_set</span><span
                                                                                                        class="lev0">(<span
                                                                                                                class="code">'<span
                                                                                                                        class="v">session.serialize_handler</span>','<span
                                                                                                                        class="lev0">php_binary</span>'</span>)</span>;<br>
                                                                                                <span
                                                                                                        class="func">session_start</span><span
                                                                                                        class="lev0">()</span>;<br>
                                                                                                $_SESSION<span
                                                                                                        class="lev0">[<span
                                                                                                                class="code">'</span>name<span
                                                                                                                class="code">'</span>]</span>
                                                                                                <span class="key">=</span>
                                                                                                $_GET<span class="lev0">[<span
                                                                                                                class="code">'</span>a<span
                                                                                                                class="code">'</span>]</span>;<br>
                                                                                                $_SESSION<span
                                                                                                        class="lev0">[<span
                                                                                                                class="code">'</span>id<span
                                                                                                                class="code">'</span>]</span>
                                                                                                <span class="key">=</span>
                                                                                                $_GET<span class="lev0">[<span
                                                                                                                class="code">'</span>b<span
                                                                                                                class="code">'</span>]</span>;<br>
                                                                                        </span>
                                                                                </el-card>
                                                                                传入参数:<el-tag style="color:#fff" type="info"
                                                                                        size="default">?a=hello&b=world</el-tag><br>
                                                                                存储格式为:<br>
                                                                                <el-tag style="color:#fff" type="info"
                                                                                        size="default"><span
                                                                                                class="key">\x04</span>names:5:"hello";<span
                                                                                                class="key">\x02</span>ids:5:"world";</el-tag><br>
                                                                        </div>
                                                                </div>
                                                                <div id="p2-3" style="height:200px">
                                                                        <h2 class="sub-title">
                                                                                漏洞产生原因
                                                                        </h2>
                                                                        一个页面写入session,而另一个页面读出session<br>
                                                                        当写入和读出的格式不同时,就可能产生漏洞<br>
                                                                        以下面的代码为例:<br>
                                                                        <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 900px;"
                                                                                body-style="line-height: 2">
                                                                                <span class="code">
                                                                                        <span class="key">
                                                                                                &lt;?php<br>
                                                                                        </span>
                                                                                        <span class="cmt">//写入session</span><br>
                                                                                        <span class="key">ini_set</span><span
                                                                                                class="lev0">(<span
                                                                                                        class="code">'<span
                                                                                                                class="v">session.serialize_handler</span>','<span
                                                                                                                class="lev0">php_serialize</span>'</span>)</span>;<br>
                                                                                        <span
                                                                                                class="func">session_start</span><span
                                                                                                class="lev0">()</span>;<br>
                                                                                        $_SESSION<span class="lev0">[<span
                                                                                                        class="code">'</span>name<span
                                                                                                        class="code">'</span>]</span>
                                                                                        <span class="key">=</span> $_GET<span
                                                                                                class="lev0">[<span
                                                                                                        class="code">'</span>a<span
                                                                                                        class="code">'</span>]</span>;<br>
                                                                                </span>
                                                                                <span class="key">
                                                                                        ?&gt;<br>
                                                                                </span>
                                                                                <br>
                                                                                <span class="code">
                                                                                        <span class="key">
                                                                                                &lt;?php<br>
                                                                                        </span>
                                                                                        <span class="cmt">//读出session</span><br>
                                                                                        <span class="key">ini_set</span><span
                                                                                                class="lev0">(<span
                                                                                                        class="code">'<span
                                                                                                                class="v">session.serialize_handler</span>','<span
                                                                                                                class="lev0">php</span>'</span>)</span>;<br>
                                                                                        <span
                                                                                                class="func">session_start</span><span
                                                                                                class="lev0">()</span>;
                                                                                        <br>
                                                                                        <br>
                                                                                        <span class="key">class</span> D<span
                                                                                                class="lev0">{</span><br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                class="key">public</span>
                                                                                        $a;<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                class="key">function</span><span
                                                                                                class="func">__construct</span><span
                                                                                                class="lev0">(</span>$a<span
                                                                                                class="lev0">)</span><span
                                                                                                class="lev1">{</span><br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                class="func">eval</span><span
                                                                                                class="lev0">(</span>$a<span
                                                                                                class="lev0">)</span>;<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                class="lev1">}</span><br>
                                                                                        <span class="lev0">}</span>
                                                                                </span><br>
                                                                                <span class="key">
                                                                                        ?&gt;<br>
                                                                                </span>
                                                                        </el-card>
                                                                        以php_serialize写入,以php读出,构造的参数需要加上竖线<el-tag
                                                                                style="color:#e1567b" type="info"
                                                                                size="default">|</el-tag><br>
                                                                        传入参数:<el-tag style="color:#fff" type="info"
                                                                                size="default">?a=<span
                                                                                        style="color:#e1567b">|</span>O:1:"D":1:{s:1:"a";s:11:"echo
                                                                                '123';";}</el-tag>
                                                                        <br>
                                                                        存储的格式:<br>
                                                                        <el-tag style="color:#fff" type="info"
                                                                                size="default">a:1:{s:4:"name":s:40:"|O:1:"D":1:{s:1:"a";s:11:"echo
                                                                                '123';";}"}</el-tag>
                                                                        <br>
                                                                        而读出时,只有竖线后的内容会被解释为序列化字符串<br>
                                                                        导致<el-tag style="color:#0b915d" type="info"
                                                                                size="default">eval()</el-tag>中的变量被赋值成恶意代码

                                                                </div>
                                                                <div id="p2-4" style="height:200px">
                                                                </div>
                                                        </div>
                                                </el-text>
                                        </div>
                                        <div id="part4" style="height: 300px;">

                                        </div>
                                </div>
                        </el-col>
                        <el-col :span="5">
                                <el-menu :default-active="activeAnchor" class="tech-anchor" mode="vertical"
                                        @select="handleAnchorSelect">
                                        <el-menu-item-group title="本页目录">
                                                <el-menu-item index="p2-1">session</el-menu-item>
                                                <el-menu-item index="p2-2">处理器</el-menu-item>
                                                <el-menu-item index="p2-2-1" style="padding-left: 20px;">php</el-menu-item>
                                                <el-menu-item index="p2-2-2" style="padding-left: 20px;">php_serialize(php >=
                                                        5.5.4)</el-menu-item>
                                                <el-menu-item index="p2-2-3"
                                                        style="padding-left: 20px;">php_binary</el-menu-item>
                                                <el-menu-item index="p2-3">漏洞产生原因</el-menu-item>
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
                };
        },
        mounted() {
                this.$refs.containerRef.addEventListener('scroll', this.handleScroll);
                this.handleScroll();
        },
        beforeDestroy() {
                this.$refs.containerRef.removeEventListener('scroll', this.handleScroll);
        },
        methods: {
                handleAnchorSelect(index) {
                        const target = document.getElementById(index);
                        if (target) {
                                this.$refs.containerRef.scrollTop = target.offsetTop - this.$refs.containerRef.offsetTop;
                        }
                },
                handleScroll() {
                        const scrollTop = this.$refs.containerRef.scrollTop;
                        const anchors = ['p2-1', 'p2-2', 'p2-2-1', 'p2-2-2', 'p2-2-3', 'p2-3'];
                        for (let i = anchors.length - 1; i >= 0; i--) {
                                const target = document.getElementById(anchors[i]);
                                if (target && target.offsetTop - this.$refs.containerRef.offsetTop <= scrollTop) {
                                        this.activeAnchor = anchors[i];
                                        break;
                                }
                        }
                },
        },
};
</script>


<style lang="less" scoped>
::-webkit-scrollbar {
  display: none;
}
/* 标题样式 */
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

/* 代码块样式 */
.code {
  color: #fff;
  font-family: 'Cascadia Code', monospace;
}

.key {
  color: #e1567b;
  font-family: 'Cascadia Code', monospace;
}

.cmt {
  color: #6a6a6a;
  font-family: 'Cascadia Code', monospace;
  font-size: 15px;
}

.num {
  color: #8e5fa0;
  font-family: 'Cascadia Code', monospace;
}

/* 组件样式 */
.el-tag {
  background-color: #3d3b3b;
  color: #fff;
  border-color: #282C34;
}

.el-card {
  background-color: #282C34;
  color: #fff;
  border-color: #282C34;
}

.el-text {
  color: #fff;
}

/* 目录菜单样式 */
.tech-anchor {
  position: fixed;
  right: 20px;
  background: rgba(10, 17, 40, 0.7);
  border-radius: 4px;
  padding: 15px;
  box-shadow: 0 0 15px rgba(0, 123, 255, 0.1);
  border-left: 1px solid rgba(0, 123, 255, 0.3);
  width: 220px;
  
  /deep/ .el-menu {
    background-color: transparent;
    border-right: none;
  }
  
  /deep/ .el-menu-item-group__title {
    color: #d1e7ff;
    font-family: 'Cascadia Code', '微软雅黑';
    padding-left: 0;
    font-size: 16px;
    border-bottom: 1px solid rgba(0, 123, 255, 0.3);
    margin-bottom: 10px;
  }
  
  /deep/ .el-menu-item {
    color: #a0c4ff;
    height: 36px;
    line-height: 36px;
    font-size: 14px;
    padding-left: 20px !important;
    
    &:hover {
      color: #ffffff !important;
      background: rgba(0, 123, 255, 0.2);
    }
    
    &.is-active {
      color: #ffffff !important;
      background: rgba(0, 123, 255, 0.3);
      border-left: 2px solid #007bff;
    }
  }
  
  /deep/ .el-submenu__title {
    color: #a0c4ff;
    height: 36px;
    line-height: 36px;
    font-size: 14px;
    
    &:hover {
      color: #ffffff !important;
      background: rgba(0, 123, 255, 0.2);
    }
  }
}

/* 响应式调整 */
@media (max-width: 1200px) {
  .tech-anchor {
    width: 180px;
    right: 10px;
  }
}

@media (max-width: 992px) {
  .tech-anchor {
    position: static;
    width: 100%;
    margin-bottom: 20px;
  }
  
  /deep/ .el-col-5 {
    width: 100%;
  }
  
  /deep/ .el-col-19 {
    width: 100%;
  }
}
</style>