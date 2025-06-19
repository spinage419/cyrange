<template>
        <div>
          <el-row>
                  <el-col :span="19">
                        <div ref="containerRef" style="color: #fff;height: 800px; overflow-y: auto" @scroll="handleScroll">
                <div id="part0" style="height: 100px;">
                </div>
      
                <div id="part2" style="height: 1250px;">
                        <h1 class="title" style="color: #fff;">原理</h1>
                        <el-divider />
                        <el-text size="large" style="color:#fff; line-height: 3.6;">
                    <div id="p2-1" style="height: 750px;">
                        <h2 class="sub-title">字符串逃逸减少</h2>
                      <!-- <el-divider /> -->
                      <el-tag style="color:#0b915d" type="info"
                              size="default">serialize()</el-tag>后的字符串,经过<el-tag
                        style="color:#0b915d" type="info"
                        size="default">str_replace()</el-tag>的替换后,属性值的实际长度变小<br>
                        导致原本属于其他成员属性的标识被吃进该属性中<br>
                        <el-card style="max-width:750px; background-color:#24292e; max-height: 1900px;" body-style="line-height: 2">
                                <span class="key">&lt;?php</span><br>
                                <span class="code">
                                        <span class="key">class</span> Test<span class="lev0">{<br>
                            &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">public</span> <span class="code">$a</span> <span class="key">=</span> <span class="code">'</span>evalabc<span class="code">';</span><br>
                            &nbsp;&nbsp;&nbsp;&nbsp;<span class="key">public</span> <span class="code">$b;</span><br>
                        }</span><br>
                          $data <span class="key">=</span> <span class="func">serialize</span><span class="lev0">(</span><span class="key">new</span> <span class="func">Test</span><span class="lev1">()</span><span class="lev0">)</span>;<br>
                          <span class="cmt">//最初的序列化字符串,蓝色的部分将会被替换<br>
                            O:4:"Test":2:{s:1:"a";s:7:"<span class="lev2">eval</span>abc";s:1:"b";N;}<br>
                        </span>
                        $data <span class="key">=</span> <span class="func">str_replace</span><span class="lev0">(</span>'<span class="lev0">eval</span>','',$data<span class="lev0">)</span>;<br>
                        <span class="cmt">//替换后的字符串,红色部分会被认为是$a的值<br>
                            O:4:"Test":2:{s:1:"a";s:7:"<span class="key">abc";s:</span>1:"b";N;}<br>
                        </span>
                        echo $data;<br>
                </span>
        </el-card>
        经过<el-tag style="color:#0b915d" type="info"
        size="default">str_replace()</el-tag>后,字符串格式被破坏,识别<el-tag
        style="color:#8e5fa0" type="info"
        size="default">7</el-tag>位后不再是<el-tag style="color:#fff"
        type="info" size="default">"</el-tag>
</div>
<div id="p2-2" style="height: 250px;">
                      <h2 class="sub-title">漏洞成因</h2>
                      经过<el-tag style="color:#0b915d" type="info"
                              size="default">str_replace()</el-tag>后,后面的属性会被"吃掉"一部分;那我们能不能索性让残缺的变量吃后面的字符,<br>
                              吃到一个恰当的程度,使剩下的部分刚好是一个变量的完整序列化表述?<br>
                      这就是关键所在<br>
                </div>
        </el-text>
</div>
<div id="part3" style="height: 1700px;">
        <el-text size="large" style="color:#fff; line-height: 3.6;">
                    <h1 class="title">绕过方法</h1>
                    <el-divider />
                    前面的例子中,<el-tag style="color:#0b915d" type="info"
                    size="default">str_replace()</el-tag>将"eval"替换为"",一次减少了<el-tag
                    style="color:#8e5fa0" type="info"
                    size="default">4</el-tag>个字符<br>
                    我们的思路是:构造成员变量的值在<el-tag style="color:#0b915d" type="info"
                    size="default">str_replace()</el-tag>的时候,能刚好吃掉不想要的,能够构造我们的命令<br>
                    应该吃到什么位置呢?<br>
                    我们构造的参数是<el-tag style="color:#fff" type="info"
                            size="default">";s:1:"b";N;}</el-tag><br>
                            序列化后该部分为<el-tag style="color:#fff;font-size: 13px;" type="info"
                            size="default">s:13:<span class="key">"</span>";s:1:"b";N;}"</el-tag><br>
                            应该吃到红色引号<el-tag style="color:#e1567b;font-size: 13px;" type="info"
                            size="default">"</el-tag>的位置,这样后面剩余部分刚好是格式正确的表达式<br>
                            先把我们要执行的命令写好,这样便于构造长度
                            <div id="p3-1" style="height: 500px;">
                      <br><br>
                      <h2 class="sub-title">步骤</h2>
                      <ul>
                        <li>
                          首先判断哪部分会被吃掉
                          <el-card style="max-width:750px; background-color:#24292e; max-height: 1900px;"
                          body-style="line-height: 2">
                            <span style="color:rgb(204, 201, 201)">经过<span
                                class="func">str_replace</span><span class="lev0">()</span>后的字符串:<br>
                              ?是因为并不确定原始长度,红色为应该被吃掉的部分,绿色为被吃掉后剩下的刚好构成表达式的部分<br>
                              用;}截断<br>
                              O:4:"Test":2:{s:1:"a";s:?:"<span class="key">abc";s:1:"b";s:13:</span>"<span
                              class="func">";s:1:"b";N;}</span>";}<br>
                              这里的吃掉是指长度? 能够包含几个红色部分的字符<br>
                              我们需要使?的值尽可能接近红色部分的长度<br>
                        </span>
                          </el-card>
                        </li>
                        <br>
                        <li>
                          随后计算所需长度,构造?的长度;通过增减构造参数达到长度匹配
                          <el-card style="max-width:750px; background-color:#24292e; max-height: 1900px;"
                                   body-style="line-height: 2">
                            <span style="color:rgb(204, 201, 201)">
                                应该被吃掉的部分有<span class="key">18</span>个字符,<span class="key">18/4=4</span>,因此需要<span
                                class="key">4</span>个<span class="lev2">eval</span><br>
                                再算上原本的<span class="key">abc</span>,原始的长度就为<span class="key">4*4+3=19</span><br>
                                与要吃掉的部分相比,多了个1个字符,怎么解决呢?<br>
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
              <el-menu
                :default-active="activeAnchor"
                class="tech-anchor"
                mode="vertical"
                @select="handleAnchorSelect"
              >
                <el-menu-item-group title="本页目录">
                  <!-- <el-menu-item index="part0">概述</el-menu-item> -->
                  <el-menu-item index="part2">原理</el-menu-item>
                  <el-menu-item index="p2-1">字符串逃逸减少</el-menu-item>
                  <el-menu-item index="p2-2">漏洞成因</el-menu-item>
                  <el-menu-item index="part3">绕过方法</el-menu-item>
                  <el-menu-item index="p3-1">步骤</el-menu-item>
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
            this.activeAnchor = key;
            const target = document.getElementById(key);
            if (target) {
              this.$refs.containerRef.scrollTop = target.offsetTop - this.$refs.containerRef.offsetTop;
            }
          },
          handleScroll() {
            const sections = ['part0', 'part2', 'p2-1', 'p2-2', 'part3', 'p3-1'];
            for (let i = sections.length - 1; i >= 0; i--) {
              const section = document.getElementById(sections[i]);
              if (section.offsetTop - this.$refs.containerRef.scrollTop <= 0) {
                this.activeAnchor = sections[i];
                break;
              }
            }
          }
        }
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