<template>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 750px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;"></div>
                                        <el-text size="large" style="color:#fff; line-height: 3.6;">
                                        <div id="p2" style="height: 750px;">
                                                <h1 class="title">二次渲染绕过</h1>
                                                <el-divider />
                                                <br>
                                                <br>
                                                <h2 id="p2-0" class="sub-title">二次渲染</h2>
                                                <el-tag style="color:#e1567b" type="info"
                                                        size="default">二次渲染</el-tag>指的是我们上传图片文件后,服务器端会对图片进行处理,将其中的元素内容替换更新<br>
                                                并根据上传的图片生成一张新的图片回显到前端<br>
                                                那么如何判断存在二次渲染呢?<br>
                                                我们将上传后回显到前端的图片下载下来,放到十六进制编辑器中(比如<el-link type="danger" target="_blank"
                                                        href="https://www.sweetscape.com/010editor/"
                                                        icon="Link">010Editor</el-link>),与原图片进行比较<br>
                                                如果存在文件哈希值变化,文件大小变化等现象,则证明存在<el-tag style="color:#e1567b" type="info"
                                                        size="default">二次渲染</el-tag>
                                        </div>
                                        <div id="p3" style="height: 900px;">
                                                <h1 class="title">绕过手段</h1>
                                                <el-divider />
                                                <br>
                                                <br>
                                                <div id="p3-1" style="height: 550px;">
                                                        <h2 class="sub-title">GIF二次渲染绕过</h2>
                                                        我们将上传后回显到前端的<el-tag style="color:#fff" type="info"
                                                                size="default">GIF</el-tag>文件下载下来,放到十六进制编辑器中(比如<el-link
                                                                type="danger" target="_blank"
                                                                href="https://www.sweetscape.com/010editor/"
                                                                icon="Link">010Editor</el-link>),与原<el-tag style="color:#fff"
                                                                type="info" size="default">GIF</el-tag>进行比较<br>
                                                        比如我们上传一个chiikawa.gif<br>
                                                        <img src="../assets/chiikawa.gif" style="width:200px;height: auto;">
                                                        <br>
                                                        这是经服务器端处理回显的chiikawa.gif<br>
                                                        <img src="../assets/chiikawa2.gif" style="width:200px;height: auto;">
                                                        <br>
                                                        我们可以发现它不会动了,比较一下二者的大小<br>
                                                        <img src="../assets/chiikawa.png" style="width:400px;height: auto;"><br>
                                                        <img src="../assets/chiikawa2.png"
                                                                style="width:400px;height: auto;"><br>
                                                        发现大小发生明显变化<br>
                                                        把它们放进010Editor<br>
                                                        <el-tag style="color:#fff" type="info" size="default">Tools</el-tag> ->
                                                        <el-tag style="color:#fff" type="info" size="default">Compare
                                                                Files</el-tag>
                                                        <img src="../assets/010Editor0.png"
                                                                style="width:800px;height: auto;"><br>
                                                        选择需要比较的两个文件<br>
                                                        <img src="../assets/010Editor1.png"
                                                                style="width:400px;height: auto;"><br>
                                                        以下就是两个文件的二进制数据对比<br>
                                                        <img src="../assets/010Editor.png"
                                                                style="width:1200px;height: 400px;"><br>
                                                        <ol>
                                                                <li>
                                                                        红色部分为二次渲染中被改变的地方
                                                                </li>
                                                                <li>
                                                                        蓝色部分为二次渲染中未改变的地方
                                                                </li>
                                                        </ol>
                                                        这样一来,我们把恶意的代码嵌入蓝色的部分,经二次渲染后就不会消失
                                                </div>
                                        </div>
                                        </el-text>
                                </div>
                        </el-col>
                        <el-col :span="5">
                                <el-menu :default-active="activeAnchor" class="tech-anchor" mode="vertical"
                                        @select="handleAnchorSelect">
                                        <el-menu-item-group title="本页目录">
                                                <el-menu-item index="p2">二次渲染绕过</el-menu-item>
                                                <el-submenu index="p2-sub">
                                                        <template #title>
                                                                <span>二次渲染</span>
                                                        </template>
                                                        <el-menu-item index="p2-0">二次渲染</el-menu-item>
                                                </el-submenu>
                                                <el-menu-item index="p3">绕过手段</el-menu-item>
                                                <el-submenu index="p3-sub">
                                                        <template #title>
                                                                <span>绕过手段子项</span>
                                                        </template>
                                                        <el-menu-item index="p3-1">GIF二次渲染绕过</el-menu-item>
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