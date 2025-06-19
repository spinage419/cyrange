<template>
        <!-- POP链 -->
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 800px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;">
                                        </div>

                                        <div id="part2" style="height: 2350px;">
                                                <h1 class="title" style="color: #fff;">属性逃逸</h1>
                                                <el-divider />
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        通常情况下,数据会先经过<el-tag style="color:#0b915d" type="info"
                                                                size="default">serialize()</el-tag>再经过<el-tag
                                                                style="color:#0b915d" type="info"
                                                                size="default">unserialize()</el-tag><br>
                                                        在这期间,如果反序列化的字符增多或减少,就有可能存在属性逃逸<br>
                                                        反序列化时,属性长度不对,成员数量不对都无法进行序列化,会显示<el-tag style="color:#e1567b" type="info"
                                                                size="default">bool(false)</el-tag><br>
                                                        <el-tag style="color:#e1567b;font-size: 15px;" type="info"
                                                                size="default">注意</el-tag>:反序列化中,长度一定不能出错;长度是判断某些字符作为字符还是格式符号的依据
                                                        <br>
                                                        <br>
                                                        <br>
                                                        <div id="p2-1" style="height: 300px;">
                                                                <h2 class="sub-title">反序列化结束分隔符</h2>
                                                                实例对象的<el-tag style="color:#e1567b" type="info"
                                                                        size="default">反序列化</el-tag>以<el-tag style="color:#fff"
                                                                        type="info" size="default">;}</el-tag>作为结束符,<el-tag
                                                                        style="color:#fff" type="info"
                                                                        size="default">;}</el-tag>之后的内容不影响<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">反序列化</el-tag>的结果
                                                        </div>
                                                        <div id="p2-2" style="height: 1050px;">
                                                                <h2 class="sub-title">凭空冒出的属性</h2>
                                                                如果反序列化时,忽略了某个存在的变量,而在序列化字符串中加上新的变量<br>
                                                                最终反序列化结果会包含被忽略的变量和序列化字符串中的变量<br>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 1900px;" body-style="line-height: 2">
                                                                        <span class="key">&lt;?php</span><br>
                                                                        <span class="code">
                                                                                <span class="key">class</span> Test<span
                                                                                        class="lev0">{</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">public</span> $a;<br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">public</span> $b;<br>
                                                                                <span class="lev0">}</span><br>
                                                                                <span class="key">echo</span> <span
                                                                                        class="func">serialize</span><span
                                                                                        class="lev0">(</span><span
                                                                                        class="key">new</span> <span
                                                                                        class="func">Test</span><span
                                                                                        class="lev1">()</span><span
                                                                                        class="lev0">)</span>;<br>
                                                                                <span class="cmt">原本的序列化字符串 : <span
                                                                                                class="key">O:4:"Test":2:{s:1:"a";N;s:1:"b";N;}</span></span><br>
                                                                                <span class="cmt">加上新变量的新的序列化字符串</span><br>
                                                                                $ser_str <span class="key">=</span> '<span
                                                                                        class="lev0">O:4:"Test":2:{s:1:"a";N;s:1:"c";N;}</span>';<br>
                                                                                <span class="func">var_dump</span><span
                                                                                        class="lev0">(</span><span
                                                                                        class="func">unserialize</span><span
                                                                                        class="lev1">(</span>$ser_str<span
                                                                                        class="lev1">)</span><span
                                                                                        class="lev0">)</span>;<br>
                                                                                <span class="cmt">/*最终的实例对象为:<br>
                                                                                        object(Test)#2 (3) {<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;["a"]=><br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;NULL<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;["b"]=><br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;NULL<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;["c"]=><br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;NULL<br>
                                                                                        }<br>
                                                                                        */
                                                                                </span>
                                                                        </span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p2-3" style="height: 500px;">
                                                                <h2 class="sub-title">str_replace()</h2>
                                                                <el-tag style="color:#0b915d" type="info"
                                                                        size="default">str_replace()</el-tag>是 <el-tag
                                                                        style="color:#fff" type="info"
                                                                        size="default">PHP</el-tag> 中用于字符串替换的内置函数
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 1900px;" body-style="line-height: 2">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /**<br>
                                                                                        * <span class="key">@param</span>
                                                                                        array|string $search: 要被查找替换的内容<br>
                                                                                        * <span class="key">@param</span>
                                                                                        array|string $replace: 要替换上去的新内容<br>
                                                                                        * <span class="key">@param</span>
                                                                                        array|string $subject: 替换的对象<br>
                                                                                        * <span class="key">@return</span>
                                                                                        void<br>
                                                                                        */<br>
                                                                                </span>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">str_replace</span><span
                                                                                        class="lev0">(</span>$search, $replace,
                                                                                $subject<span class="lev0">)</span>;
                                                                        </span>
                                                                </el-card>
                                                                <el-tag style="color:#0b915d" type="info"
                                                                        size="default">str_replace()</el-tag>可能会使字符串中属性的长度发生变化,从而引发字符串逃逸
                                                        </div>
                                                        <div style="height: 300px;"></div>
                                                </el-text>
                                        </div>
                                </div>
                        </el-col>
                        <el-col :span="5">
                                <el-menu :default-active="activeAnchor" class="tech-anchor" mode="vertical"
                                        @select="handleAnchorSelect">
                                        <el-menu-item-group title="本页目录">
                                                <el-menu-item index="part2">属性逃逸</el-menu-item>
                                                <el-submenu index="sub_part2">
                                                        <template #title>属性逃逸子菜单</template>
                                                        <el-menu-item index="p2-1">结束分隔符</el-menu-item>
                                                        <el-menu-item index="p2-2">凭空冒出的属性</el-menu-item>
                                                        <el-menu-item index="p2-3">str_replace()</el-menu-item>
                                                </el-submenu>
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
      activeAnchor: '',
      containerRef: null
    };
  },
  mounted() {
    this.containerRef = this.$refs.containerRef;
    this.containerRef.addEventListener('scroll', this.handleScroll);
    this.handleScroll();
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
    handleScroll() {
      const sections = ['part2', 'p2-1', 'p2-2', 'p2-3'];
      for (let i = sections.length - 1; i >= 0; i--) {
        const section = document.getElementById(sections[i]);
        if (section.offsetTop <= this.containerRef.scrollTop + 60) {
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