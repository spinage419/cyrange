<template>
        <!-- POP链 -->
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #000;height: 800px; overflow-y: auto" @scroll="handleScroll()">
                                        <div id="part0" style="height: 100px;">
                                        </div>

                                        <div id="part2" style="height: 1250px;">
                                                <h1 class="title" style="color: #fff;">原理</h1>
                                                <el-divider />
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2-1" style="height: 750px;">
                                                                <h2 class="sub-title">字符串逃逸增加</h2>
                                                                <!-- <el-divider /> -->
                                                                <el-tag style="color:#0b915d" type="info"
                                                                        size="default">serialize()</el-tag>后的字符串,经过<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">str_replace()</el-tag>的替换后,属性值的实际长度变长<br>
                                                                导致吐出多余代码<br>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 1900px;" body-style="line-height: 2">
                                                                        <span class="key">&lt;?php</span><br>
                                                                        <span class="code">
                                                                                <span class="key">class</span> Test<span
                                                                                        class="lev0">{<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                class="key">public</span> <span
                                                                                                class="code">$a</span> <span
                                                                                                class="key">=</span> <span
                                                                                                class="code">'</span>php<span
                                                                                                class="code">';</span><br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                class="key">public</span> <span
                                                                                                class="code">$b;</span><br>
                                                                                        }</span><br>
                                                                                $data <span class="key">=</span> <span
                                                                                        class="func">serialize</span><span
                                                                                        class="lev0">(</span><span
                                                                                        class="key">new</span> <span
                                                                                        class="func">Test</span><span
                                                                                        class="lev1">()</span><span
                                                                                        class="lev0">)</span>;<br>
                                                                                <span class="cmt">//最初的序列化字符串,蓝色的部分将会被替换<br>
                                                                                        O:4:"Test":2:{s:1:"a";s:3:"<span
                                                                                                class="lev2">php</span>";s:1:"b";N;}<br>
                                                                                </span>
                                                                                $data <span class="key">=</span> <span
                                                                                        class="func">str_replace</span><span
                                                                                        class="lev0">(</span>'<span
                                                                                        class="lev0">php</span>','<span
                                                                                        class="lev0">flag</span>',$data<span
                                                                                        class="lev0">)</span>;<br>
                                                                                <span class="cmt">//替换后的字符串,红色部分会被认为是$a的值<br>
                                                                                        O:4:"Test":2:{s:1:"a";s:3:"<span
                                                                                                class="key">fla</span>g";s:1:"b";N;}<br>
                                                                                </span>
                                                                                echo $data;<br>
                                                                        </span>
                                                                </el-card>
                                                                经过<el-tag style="color:#0b915d" type="info"
                                                                        size="default">str_replace()</el-tag>后,字符串格式被破坏,识别<el-tag
                                                                        style="color:#8e5fa0" type="info"
                                                                        size="default">3</el-tag>位后不再是<el-tag style="color:#000"
                                                                        type="info" size="default">"</el-tag>
                                                        </div>
                                                        <div id="p2-2" style="height: 250px;">
                                                                <h2 class="sub-title">漏洞成因</h2>
                                                                经过<el-tag style="color:#0b915d" type="info"
                                                                        size="default">str_replace()</el-tag>后,会被吐出来一部分内容;那我们能不能让吐出来的多余内容变成逃逸成员属性,<br>
                                                                使反序列化能正常进行?<br>
                                                                这就是关键所在<br>
                                                                <!-- 不妨试验一下<br>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 1900px;"
                                                                body-style="line-height: 2"
                                                                >
                                                                <span class="key">&lt;?php</span><br>
                                                                <span class="code">
                                                                        <span class="key">class</span> Test<span class="lev0">{<br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">public</span> <span class="code">$a</span><span class="code">;</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">public</span> <span class="code">$b;</span><br>
                                                                        }</span><br>
                                                                        $test1 <span class="key">=</span> <span class="key">new</span> <span class="func">Test</span><span class="lev1">()</span>;<br>
                                                                        $test1<span class="key">-></span>a <span class="key">=</span> '<span class="lev0">evalabc</span>';<br><br>
                                                                        $test2 <span class="key">=</span> <span class="key">new</span> <span class="func">Test</span><span class="lev1">()</span>;<br>
                                                                        $test2<span class="key">-></span>a <span class="key">=</span> '<span class="lev0">evalevalabc</span>';<br><br>
                                                                        $data1 <span class="key">=</span> <span class="func">serialize</span><span class="lev0">(</span><span class="code">$test</span><span class="lev0">)</span>;<br>
                                                                        $data2 <span class="key">=</span> <span class="func">serialize</span><span class="lev0">(</span><span class="code">$test</span><span class="lev0">)</span>;<br>
                                                                        <span class="cmt">//最初的序列化字符串,蓝色的部分将会被替换<br>
                                                                        $data1:   O:4:"Test":2:{s:1:"a";s:7:"<span class="lev2">eval</span>abc";s:1:"b";N;}<br>
                                                                        $data2:   O:4:"Test":2:{s:1:"a";s:11:"<span class="lev2">evaleval</span>abc";s:1:"b";N;}<br>
                                                                        </span>
                                                                        $data1 <span class="key">=</span> <span class="func">str_replace</span><span class="lev0">(</span>'<span class="lev0">eval</span>','',$data1<span class="lev0">)</span>;<br>
                                                                        $data2 <span class="key">=</span> <span class="func">str_replace</span><span class="lev0">(</span>'<span class="lev0">eval</span>','',$data2<span class="lev0">)</span>;<br>
                                                                        <span class="cmt">//替换后的字符串,红色部分会被认为是$a的值<br>
                                                                        $data1:  O:4:"Test":2:{s:1:"a";s:7:"<span class="key">abc";s:</span>1:"b";N;}<br>
                                                                        $data2:  O:4:"Test":2:{s:1:"a";s:11:"<span class="key">abc";s:1:"b</span>";N;}<br>
                                                                        </span>
                                                                        echo $data;<br>
                                                                </span>
                                                                </el-card> -->
                                                                <!-- <el-divider /> -->
                                                        </div>
                                                </el-text>
                                        </div>
                                        <div id="part3" style="height: 1700px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <h1 class="title">绕过方法</h1>
                                                        <el-divider />
                                                        前面的例子中,<el-tag style="color:#0b915d" type="info"
                                                                size="default">str_replace()</el-tag>将"php"替换为"flag",一次增加了<el-tag
                                                                style="color:#8e5fa0" type="info"
                                                                size="default">1</el-tag>个字符<br>
                                                        我们的思路是:构造成员变量的值,在<el-tag style="color:#0b915d" type="info"
                                                                size="default">str_replace()</el-tag>的时候,能刚好吐出功能性代码<br>
                                                        <!-- 应该吃到什么位置呢?<br>
                                                        应该吃到红色引号<el-tag style="color:#e1567b;font-size: 13px;" type="info" size="default">"</el-tag>的位置,这样后面剩余部分刚好是格式正确的表达式<br> -->
                                                        先把我们的功能性代码写好,这样便于构造长度<br>
                                                        比如功能性代码是<el-tag style="color:#000" type="info"
                                                                size="default">";s:1:"b";N;}</el-tag><br>

                                                        <div id="p3-1" style="height: 500px;">
                                                                <br><br>
                                                                <h2 class="sub-title">步骤</h2>
                                                                <ul>
                                                                        <li>
                                                                                首先计算所需的长度
                                                                                <el-card style="max-width:750px;
                                                                        background-color:#282C34;
                                                                        max-height: 1900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span style="color:rgb(204, 201, 201)">
                                                                                                构造的参数";s:1:"b";N;}的长度是<span
                                                                                                        class="key">13</span><br>
                                                                                                说明我们需要<span
                                                                                                        class="key">13</span>位才能让构造参数被吐出来<br>
                                                                                                从<span
                                                                                                        class="lev2">php</span>被替换成<span
                                                                                                        class="lev2">flag</span>多出了<span
                                                                                                        class="key">1</span>个字符,<span
                                                                                                        class="key">13/1=13</span><br>
                                                                                                也就是说,我们需要在参数中构造<span
                                                                                                        class="key">13</span>个php,并在它后面加上我们的功能性代码<br>
                                                                                                这样,php都被替换成flag时,就会吐出<span
                                                                                                        class="key">13</span>个字符,刚好就是我们的功能性代码

                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                对属性赋值
                                                                                <el-card style="max-width:750px;
                                                                        background-color:#282C34;
                                                                        max-height: 1900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span style="color:rgb(204, 201, 201)">
                                                                                                <span class="code">
                                                                                                        <span
                                                                                                                class="key">class</span>
                                                                                                        Test<span
                                                                                                                class="lev0">{<br>
                                                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                                        class="key">public</span>
                                                                                                                <span
                                                                                                                        class="code">$a</span>
                                                                                                                <span
                                                                                                                        class="key">=</span>
                                                                                                                <span
                                                                                                                        class="code">'</span>phpphpphpphpphpphpphpphpphpphpphpphpphp";s:1:"b";N;}<span
                                                                                                                        class="code">';</span><br>
                                                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                                        class="key">public</span>
                                                                                                                <span
                                                                                                                        class="code">$b;</span><br>
                                                                                                                }
                                                                                                        </span><br>

                                                                                                </span>
                                                                                        </span>
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                用<el-tag style="color:#0b915d" type="info"
                                                                                        size="default">unserialize()</el-tag>的返回结果判断字符串格式是否正确
                                                                                <el-card style="max-width:750px;
                                                                        background-color:#282C34;
                                                                        max-height: 1900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span style="color:rgb(204, 201, 201)">
                                                                                                <br>此时序列化字符串变为:<br>
                                                                                                <span
                                                                                                        style="font-size: 11.4px;">
                                                                                                        O:4:"Test":2:{s:1:"a";s:52:"<span
                                                                                                                class="key">flagflagflagflagflagflagflagflagflagflagflagflagflag</span>"<span
                                                                                                                class="func">;s:1:"b";N;}</span>";s:1:"b";N;}<br>
                                                                                                </span>
                                                                                                格式正确了,能成功反序列化
                                                                                                <br><br>
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
                                                <!-- <el-menu-item index="part0">Part 0</el-menu-item> -->
                                                <el-menu-item index="part2">原理</el-menu-item>
                                                <el-menu-item index="p2-1"> 字符串逃逸增加</el-menu-item>
                                                <el-menu-item index="p2-2"> 漏洞成因</el-menu-item>
                                                <el-menu-item index="part3">绕过方法</el-menu-item>
                                                <el-menu-item index="p3-1"> 步骤</el-menu-item>
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
  methods: {
    handleAnchorClick(index) {
      const targetElement = document.getElementById(index);
      if (targetElement) {
        targetElement.scrollIntoView({ behavior: 'smooth' });
      }
    },
    handleScroll() {
      const container = this.$refs.containerRef;
      const scrollTop = container.scrollTop;
      const part0 = document.getElementById('part0');
      const part2 = document.getElementById('part2');
      const p2_1 = document.getElementById('p2-1');
      const p2_2 = document.getElementById('p2-2');
      const part3 = document.getElementById('part3');
      const p3_1 = document.getElementById('p3-1');

      if (scrollTop >= part0.offsetTop - container.offsetTop && scrollTop < part2.offsetTop - container.offsetTop) {
        this.activeAnchor = 'part0';
      } else if (scrollTop >= part2.offsetTop - container.offsetTop && scrollTop < p2_1.offsetTop - container.offsetTop) {
        this.activeAnchor = 'part2';
      } else if (scrollTop >= p2_1.offsetTop - container.offsetTop && scrollTop < p2_2.offsetTop - container.offsetTop) {
        this.activeAnchor = 'p2-1';
      } else if (scrollTop >= p2_2.offsetTop - container.offsetTop && scrollTop < part3.offsetTop - container.offsetTop) {
        this.activeAnchor = 'p2-2';
      } else if (scrollTop >= part3.offsetTop - container.offsetTop && scrollTop < p3_1.offsetTop - container.offsetTop) {
        this.activeAnchor = 'part3';
      } else if (scrollTop >= p3_1.offsetTop - container.offsetTop) {
        this.activeAnchor = 'p3-1';
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