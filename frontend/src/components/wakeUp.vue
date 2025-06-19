<template>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;">
                                        </div>

                                        <div id="part2" style="height: 650px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2-1" style="height: 50px;">
                                                                <h1 class="title">CVE2016-7124</h1>
                                                                <el-divider />
                                                                反序列化漏洞<el-tag style="color:#e1567b" type="info"
                                                                        size="default">CVE2016-7124</el-tag><br>
                                                                影响版本:<el-tag style="color:#fff" type="info"
                                                                        size="default">PHP5>5.6.25</el-tag> <el-tag
                                                                        style="color:#fff" type="info"
                                                                        size="default">PHP7&lt;7.0.10</el-tag>
                                                                <br>
                                                                <br>
                                                                <div id="p2-2" style="height:200px">
                                                                        <h2 class="sub-title">
                                                                                漏洞产生原因
                                                                        </h2>
                                                                        如果存在<el-tag style="color:#0b915d" type="info"
                                                                                size="default">__wakeUp()</el-tag>,则<el-tag
                                                                                style="color:#0b915d" type="info"
                                                                                size="default">unserialize()</el-tag>前先调用<el-tag
                                                                                style="color:#0b915d" type="info"
                                                                                size="default">__wakeUp()</el-tag>,<br>
                                                                        但是序列化字符串中表示对象属性个数的值大于真实的属性个数时,会跳过<el-tag
                                                                                style="color:#0b915d" type="info"
                                                                                size="default">__wakeUp()</el-tag>的执行.<br>
                                                                        比如下面的序列化字符串,实际属性个数为<el-tag style="color:#8e5fa0"
                                                                                type="info"
                                                                                size="default">2</el-tag>,但是表示对象属性个数的值却是<el-tag
                                                                                style="color:#8e5fa0" type="info"
                                                                                size="default">3</el-tag>
                                                                        <el-card style="max-width:750px;
                                                                        background-color:#282C34;
                                                                        max-height: 900px;"
                                                                                body-style="line-height: 2">
                                                                                <span class="code">
                                                                                        O:1:"A":<span
                                                                                                class="key">3</span>:{s:1:"<span
                                                                                                class="func">a</span>";N;s:1:"<span
                                                                                                class="func">b</span>";N;}
                                                                                </span>
                                                                        </el-card>
                                                                        此时,这段序列化字符串经过<el-tag style="color:#0b915d" type="info"
                                                                                size="default">unserialize()</el-tag>进行反序列化时,就会跳过<el-tag
                                                                                style="color:#0b915d" type="info"
                                                                                size="default">__wakeUp()</el-tag>
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
                                                <el-menu-item index="p2-1">CVE2016-7124</el-menu-item>
                                                <el-submenu index="sub-p2-2">
                                                        <template #title>
                                                                <span>漏洞产生原因</span>
                                                        </template>
                                                        <el-menu-item index="p2-2"></el-menu-item>
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
                error2() {
                        ElMessage({
                                message: 'You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near """ at line 1',
                                type: 'error',
                        });
                },
                handleAnchorSelect(index) {
                        const target = document.getElementById(index);
                        if (target) {
                                this.containerRef.scrollTo({
                                        top: target.offsetTop - this.containerRef.offsetTop,
                                        behavior: 'smooth'
                                });
                        }
                },
                handleScroll() {
                        const anchors = ['p2-1', 'p2-2'];
                        for (let i = anchors.length - 1; i >= 0; i--) {
                                const anchor = anchors[i];
                                const target = document.getElementById(anchor);
                                if (target && target.offsetTop - this.containerRef.offsetTop <= this.containerRef.scrollTop) {
                                        this.activeAnchor = anchor;
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