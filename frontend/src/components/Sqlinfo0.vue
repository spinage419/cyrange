<template>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff; height: 700px; overflow-y: auto">
                                        <div id="p1" style="height: 850px;">
                                                <div id="p1-0" style="height: 500px;">
                                                        <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                                <br>
                                                                <br>
                                                                <h1 class="title">产生原因</h1>
                                                                <el-divider />
                                                                页面响应的过程如下:<br>
                                                                <img src="../assets/errorij.png"
                                                                        style="width:750px;height: 115px;">
                                                                <br>
                                                                <el-text size="large"
                                                                        style="color:#fff; line-height: 3.6;">
                                                                        <span style="font-weight: 500;">后端对前端传入的内容过滤不严,导致本应作为查询参数内容被当作
                                                                                <el-tag type="info"
                                                                                        size="default">SQL</el-tag>
                                                                                执行<br>
                                                                                以<el-tag style="" type="info"
                                                                                        size="default">MySQL</el-tag>为例,下面这句简单的<el-tag
                                                                                        style="" type="info"
                                                                                        size="default">SQL</el-tag>:<br>
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
                                                                                                class="key">=</span>&nbsp;'参数'</span>
                                                                        </el-card>
                                                                        <span style="font-weight: 500;">
                                                                                这句简单的<el-tag style="" type="info"
                                                                                        size="default">SQL</el-tag>目的是根据<el-tag
                                                                                        style="" type="info"
                                                                                        size="default">id</el-tag>来获取表中的<el-tag
                                                                                        style="" type="info"
                                                                                        size="default">username</el-tag>
                                                                        </span>
                                                                </el-text>
                                                        </el-text>
                                                </div>
                                        </div>

                                        <div id="p2" style="height: 600px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <h1 class="title">传入恶意参数</h1>
                                                        <el-divider />
                                                        <span style="font-weight: 500;">
                                                                如果我们正常传入参数,它会按照预期执行
                                                        </span><br>
                                                        <span style="font-weight: 500;">
                                                                如果我们对参数做一些变化,比如传入<el-tag style=" color: #fff"
                                                                        type="info" size="default">' or 1=1#</el-tag>
                                                        </span><br>
                                                        <span style="font-weight: 500;">
                                                                此时<el-tag style=" color: #fff" type="info"
                                                                        size="default">SQL</el-tag>变为
                                                        </span>
                                                        <el-card style="max-width:600px;;max-height: 150px;">
                                                                <span class="key">select</span>&nbsp;<span
                                                                        class="code">username</span>&nbsp;<span
                                                                        class="key">from</span>&nbsp;<span
                                                                        class="code">users</span>&nbsp;<span
                                                                        class="key">where</span>&nbsp;<span
                                                                        class="code">id&nbsp;<span
                                                                                class="key">=</span>&nbsp;''&nbsp;<span
                                                                                class="key">or</span>&nbsp;1&nbsp;<span
                                                                                class="key">=</span>&nbsp;1#</span>
                                                                <br><span
                                                                        class="cmt">//#是Mysql中的注释符,用于注释掉后面的SQL(如果有)</span>
                                                        </el-card>
                                                </el-text>
                                        </div>

                                        <div id="p3" style="height: 500px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <h1 class="title">执行结果</h1>
                                                        <el-divider />
                                                        <span style="font-weight: 500;">查询条件变为<el-tag
                                                                        style=" color: #fff" type="info"
                                                                        size="default">''
                                                                        or 1=1</el-tag>,<el-tag style=" color: #fff"
                                                                        type="info"
                                                                        size="default">1=1</el-tag>结果是<el-tag
                                                                        style=" color: #fff" type="info"
                                                                        size="default">true</el-tag>;
                                                        </span>
                                                        <br>
                                                        <span style="font-weight: 500;">
                                                                由逻辑与的性质我们知道,这样一来,就相当于查询参数整体为<el-tag style=" color: #fff"
                                                                        type="info" size="default">true</el-tag>
                                                                <el-card style="max-width:600px;;max-height: 90px;">
                                                                        <span class="key">select</span>&nbsp;<span
                                                                                class="code">username</span>&nbsp;<span
                                                                                class="key">from</span>&nbsp;<span
                                                                                class="code">users</span>&nbsp;<span
                                                                                class="key">where</span>&nbsp;<span
                                                                                class="code">id&nbsp;<span
                                                                                        class="num">true</span></span>
                                                                </el-card>
                                                        </span>
                                                </el-text>
                                        </div>

                                        <div id="p4" style="height: 4850px;">
                                                <h1 class="title">MySQL基础知识</h1>
                                                <el-divider />
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p4-1" style="height: 780px;">
                                                                <h2 class="sub-title">information_schema</h2>
                                                                information_schema是<el-tag style=" color: #fff"
                                                                        type="info"
                                                                        size="default">MySQL</el-tag>中一个特殊且重要的数据库,存储了所有表,列的信息以及其他敏感信息<br>
                                                                其中这两个数据表对我们十分重要:<br><el-tag
                                                                        style=" color: #fff; font-size: 20px;"
                                                                        type="info" size="large">tables</el-tag>
                                                                <el-descriptions title="tables表中的关键字段"
                                                                        direction="vertical" :column="2" size='default'
                                                                        border>
                                                                        <el-descriptions-item
                                                                                label="table_schema">数据表所属的数据库</el-descriptions-item>
                                                                        <el-descriptions-item
                                                                                label="table_name">数据表名</el-descriptions-item>
                                                                </el-descriptions>
                                                                <br>
                                                                <el-tag style=" color: #fff; font-size: 20px;"
                                                                        type="info" size="large">columns</el-tag>
                                                                <el-descriptions title="columns表中的关键字段" :column="2"
                                                                        size='default' border>
                                                                        <el-descriptions-item
                                                                                label="table_schema">数据表所属的数据库</el-descriptions-item>
                                                                        <el-descriptions-item
                                                                                label="table_name">所属的数据表名</el-descriptions-item>
                                                                        <el-descriptions-item
                                                                                label="column_name">列名</el-descriptions-item>
                                                                        <el-descriptions-item
                                                                                label=""></el-descriptions-item>
                                                                </el-descriptions>
                                                        </div>
                                                        <div id="p4-2" style="height: 250px">
                                                                <h2 class="sub-title">database()</h2>
                                                                <el-tag style=" color: #fff" type="info"
                                                                        size="default">database()</el-tag>是一个函数,用于返回当前正在使用的数据库的名称<br>
                                                                通常与<el-tag style=" color: #fff" type="info"
                                                                        size="default">tables</el-tag>表的<el-tag
                                                                        style=" color: #fff" type="info"
                                                                        size="default">table_schema</el-tag>字段配合使用
                                                        </div>
                                                        <div id="p4-3" style="height: 650px;">
                                                                <h2 class="sub-title">LIMIT</h2>
                                                                <el-tag style=" color: #fff" type="info"
                                                                        size="default">LIMIT</el-tag>是一个非常重要的子句,用于限制查询结果返回的行数
                                                                <el-card style="max-width:600px;;max-height: 900px;">
                                                                        <span class="cmt">-- 查询前10条数据</span><br>
                                                                        <span class="key">select</span>&nbsp;<span
                                                                                class="code">username</span>&nbsp;<span
                                                                                class="key">from</span>&nbsp;<span
                                                                                class="code">users</span>&nbsp;<span
                                                                                class="key">limit</span>&nbsp;<span
                                                                                class="num">10</span>
                                                                </el-card>
                                                                <br>
                                                                <el-card style="max-width:600px;;max-height: 900px;">
                                                                        <span class="cmt">--
                                                                                跳过前20条数据,查询前10条数据</span><br>
                                                                        <span class="key">select</span>&nbsp;<span
                                                                                class="code">username</span>&nbsp;<span
                                                                                class="key">from</span>&nbsp;<span
                                                                                class="code">users</span>&nbsp;<span
                                                                                class="key">limit</span>&nbsp;<span
                                                                                class="num">10</span>&nbsp;<span
                                                                                class="key">offset</span>&nbsp;<span
                                                                                class="num">20</span><br>
                                                                        <span class="cmt">-- 简写形式</span><br>
                                                                        <span class="key">select</span>&nbsp;<span
                                                                                class="code">username</span>&nbsp;<span
                                                                                class="key">from</span>&nbsp;<span
                                                                                class="code">users</span>&nbsp;<span
                                                                                class="key">limit</span>&nbsp;<span
                                                                                class="num">20</span><span
                                                                                class="code">,</span><span
                                                                                class="num">10</span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p4-4" style="height: 700px;">
                                                                <h2 class="sub-title">order by | group by</h2>
                                                                在<el-tag style=" color: #fff" type="info"
                                                                        size="default">SQL</el-tag>中,<el-tag
                                                                        style=" color: #fff" type="info"
                                                                        size="default">GROUP
                                                                        BY</el-tag>子句用于将查询结果按指定列分组,所有非聚合列必须出现在<el-tag
                                                                        style=" color: #fff" type="info"
                                                                        size="default">GROUP BY</el-tag>子句中<br>
                                                                (<el-tag style=" color: #fff" type="info"
                                                                        size="default">MySQL</el-tag>严格模式下)<br>
                                                                以<el-tag style=" color: #fff" type="info"
                                                                        size="default">MySQL</el-tag>为例,语法如下:
                                                                <el-card
                                                                        style="max-width:750px;background-color:#282C34;max-height: 90px;">
                                                                        <span class="code"><span
                                                                                        class="key">select</span>
                                                                                col1,col2... <span
                                                                                        class="key">from</span> table1
                                                                                <span class="key">group by</span>
                                                                                col1,col2...</span>
                                                                </el-card>
                                                                <el-tag style=" color: #fff" type="info"
                                                                        size="default">ORDER
                                                                        BY</el-tag>则用于用于对查询结果进行排序,它可以通过列的序号或列名指定排序依据<br>
                                                                以<el-tag style=" color: #fff" type="info"
                                                                        size="default">MySQL</el-tag>为例,语法如下:
                                                                <el-card
                                                                        style="max-width:750px;background-color:#282C34;max-height: 90px;">
                                                                        <span class="code"><span
                                                                                        class="key">select</span>
                                                                                col1,col2... <span
                                                                                        class="key">from</span> table1
                                                                                <span class="key">order by</span>
                                                                                col1</span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p4-5" style="height: 800px;">
                                                                <h2 class="sub-title">substring()</h2>
                                                                <el-tag style=" color: #fff" type="info"
                                                                        size="default">substring()</el-tag>是一个函数;用于根据指定的起始位置和长度,返回字符串的一部分
                                                                <el-card
                                                                        style="max-width:750px;background-color:#282C34;max-height: 900px;">
                                                                        <span class="cmt">
                                                                                /**<br>
                                                                                * <span class="key">@param</span> str:
                                                                                原字符串<br>
                                                                                * <span class="key">@param</span> start:
                                                                                起始位置<br>
                                                                                * <span class="key">@param</span>
                                                                                length: 要提取的子字符串长度<br>
                                                                                */
                                                                        </span><br>
                                                                        <span class="code"><span
                                                                                        class="func">substring</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">str,start,length</span>)</span></span>
                                                                </el-card>
                                                                <el-tag style=" color: #fff" type="info"
                                                                        size="default">extractvalue()</el-tag>的返回值最多只有32个字符,有时我们查询到了想要的信息,但是不能完整显示它们<br>我们这时就需要用<el-tag
                                                                        style=" color: #fff" type="info"
                                                                        size="default">substring()</el-tag>将信息分段
                                                        </div>
                                                        <div id="p4-6" style="height: 750px;">
                                                                <h2 class="sub-title">concat()</h2>
                                                                <el-tag style=" color: #fff" type="info"
                                                                        size="default"><span
                                                                                style="font-size: 14px;">concat()</span></el-tag>用于将多个字符串连接成一个字符串,通常用于单行数据的处理<br>
                                                                <el-card
                                                                        style="max-width:750px;background-color:#282C34;max-height: 900px;">
                                                                        <span class="cmt">
                                                                                /**<br>
                                                                                *<span class="key">@param</span> str1:
                                                                                要拼接的字符串其一,在SQL注入中这个位置可以当成分隔符<br>
                                                                                */
                                                                        </span><br>
                                                                        <span class="code"><span
                                                                                        class="func">concat</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">str1,...,strn</span>)</span></span>
                                                                </el-card>
                                                                在报错注入中,目标表藏在多张表中的情况并不少见<br>
                                                                在这种情况下,即使加上了<el-tag style=" color: #fff" type="info"
                                                                        size="default">where</el-tag>限定,还是会出现回显位只能显示第一个表或列的情况<br>
                                                                此时我们需要使用<el-tag style=" color: #fff" type="info"
                                                                        size="default">concat()</el-tag>将多行查询结果中的值连接成一个字符串
                                                        </div>
                                                        <div id="p4-7" style="height: 800px;">
                                                                <h2 class="sub-title">group_concat()</h2>
                                                                <el-tag style=" color: #fff" type="info"
                                                                        size="default"><span
                                                                                style="font-size: 14px;">group_concat()</span></el-tag>是一个聚合函数,用于将多行查询结果中的某个列的值连接成一个字符串<br>
                                                                <el-card
                                                                        style="max-width:750px;background-color:#282C34;max-height: 900px;">
                                                                        <span class="cmt">
                                                                                /**<br>
                                                                                *<span class="key">@param</span>
                                                                                col_name: 字段名<br>
                                                                                */
                                                                        </span><br>
                                                                        <span class="code"><span
                                                                                        class="func">group_concat</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">col_name</span>)</span></span>
                                                                </el-card>
                                                                在注入中,一个字段下有多个值情况并不少见<br>
                                                                在这种情况下,即使加上了<el-tag style=" color: #fff" type="info"
                                                                        size="default">where</el-tag>限定,还是会出现回显位只能显示第一个字段值的情况<br>
                                                                此时我们需要使用<el-tag style=" color: #fff" type="info"
                                                                        size="default">group_concat()</el-tag>将多行查询结果中的值连接成一个字符串
                                                        </div>
                                                </el-text>
                                        </div>
                                        <div id="p5" style="height: 500px;">
                                                <h1 class="title">可能的注入点</h1>
                                                <el-divider />
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <span style="font-weight: 500;">
                                                                出现在与数据库存在交互的表单,回显的用户信息等<br>
                                                                <input v-model="input"
                                                                        style="width: 240px;height: 30px;"
                                                                        placeholder="试着构造恶意的SQL~" />
                                                        </span>
                                                        <el-card
                                                                style="max-width:600px;background-color:#282C34;max-height: 90px;">
                                                                <span class="key">select</span>&nbsp;<span
                                                                        class="code">username</span>&nbsp;<span
                                                                        class="key">from</span>&nbsp;<span
                                                                        class="code">users</span>&nbsp;<span
                                                                        class="key">where</span>&nbsp;<span
                                                                        class="code">id&nbsp;<span
                                                                                class="key">=</span>&nbsp;'{{ input
                                                                        }}'</span>
                                                        </el-card>
                                                </el-text>
                                        </div>
                                        <div style="height: 300px;"></div>
                                </div>
                        </el-col>
          <!-- 目录区域 - 修改为Vue2格式 -->
      <el-col :span="5">
        <el-affix :offset="60">
          <el-menu 
            :default-active="activeAnchor" 
            class="tech-anchor" 
            mode="vertical"
            @select="handleAnchorSelect"
          >
            <el-menu-item-group title="本页目录">
              <el-menu-item index="p1">产生原因</el-menu-item>
              <el-menu-item index="p2">传入恶意参数</el-menu-item>
              <el-menu-item index="p3">执行结果</el-menu-item>
              <el-submenu index="p4">
                <template slot="title">MySQL基础知识</template>
                <el-menu-item index="p4-1">information_schema</el-menu-item>
                <el-menu-item index="p4-2">database()</el-menu-item>
                <el-menu-item index="p4-3">LIMIT</el-menu-item>
                <el-menu-item index="p4-4">order by | group by</el-menu-item>
                <el-menu-item index="p4-5">substring()</el-menu-item>
                <el-menu-item index="p4-6">concat()</el-menu-item>
                <el-menu-item index="p4-7">group_concat()</el-menu-item>
              </el-submenu>
              <el-menu-item index="p5">可能的注入点</el-menu-item>
            </el-menu-item-group>
          </el-menu>
        </el-affix>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      containerRef: null,
      activeAnchor: 'p1',
      input: '',
      scrollTimer: null
    }
  },
  mounted() {
    this.containerRef = this.$refs.containerRef
    window.addEventListener('scroll', this.handleScroll)
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll)
    clearTimeout(this.scrollTimer)
  },
  methods: {
    handleAnchorSelect(index) {
      this.activeAnchor = index
      const target = document.getElementById(index)
      if (target) {
        target.scrollIntoView({ behavior: 'smooth' })
      }
    },
    handleScroll() {
      clearTimeout(this.scrollTimer)
      this.scrollTimer = setTimeout(() => {
        const sections = ['p1', 'p2', 'p3', 'p4-1', 'p4-2', 'p4-3', 'p4-4', 'p4-5', 'p4-6', 'p4-7', 'p5']
        const scrollPosition = window.scrollY || document.documentElement.scrollTop
        
        for (let i = sections.length - 1; i >= 0; i--) {
          const section = document.getElementById(sections[i])
          if (section && section.offsetTop <= scrollPosition + 100) {
            this.activeAnchor = sections[i]
            break
          }
        }
      }, 100)
    },
    handleClick(e) {
      e.preventDefault()
    }
  }
}
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