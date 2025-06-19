<template>
        <div>
                <el-row>
                        <!-- 主要内容区域 -->
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff; height: 650px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;"></div>
                                        <div id="part2" style="height: auto;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2" style="height: auto;">
                                                                <h1 class="title">命令执行中绕过字符串过滤限制</h1>
                                                                <el-divider />
                                                                <br>

                                                                <!-- 过滤限制原理 -->
                                                                <h2 class="sub-title" id="p2-1">过滤限制原理</h2>
                                                                <p>在<el-tag style="color:#e1567b" type="info">WEB</el-tag>应用程序开发中,为了防止命令注入攻击,开发者通常会采用<el-tag
                                                                        type="danger">黑名单过滤</el-tag>的方式.<br>
                                                                        这种方式通过禁止特定的命令或者字符,来限制用户输入的内容,从而达到防御命令注入的目的.<br>
                                                                        然而,这种防御机制存在一些明显的缺陷,很容易被攻击者绕过.<br>
                                                                        <br>
                                                                        <h2 class="sub-title" id="p2-2">漏洞成因</h2>
                                                                <ul>
                                                                        <li>未处理命令分隔符:<br>
                                                                                在许多情况下,开发者只关注了特定的危险命令,而忽略了命令分隔符的存在;例如:<br>
                                                                                分号<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">;</el-tag>:在<el-tag style="color:#a5d6ff;font-size: 15px;" type="info">Windows,Linux</el-tag>系统中都可以用来分隔多个命令,使得攻击者可以在一个输入中执行多个命令.<br>
                                                                                另外,URL编码中的换行符<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">%0a</el-tag>也有类似的作用.<br>
                                                                                如果应用程序没有对这些命令分隔符进行处理,攻击者就可以利用它们在允许的命令后面添加额外的恶意命令.</li>
                                                                        <li>未验证参数内容:有些应用程序在接收用户输入的参数时,没有对参数的内容进行严格的验证.<br>
                                                                                例如,允许用户输入类似 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">cat <span style="color:#ff7b72">/etc$&#123;IFS&#125;passwd</span></el-tag> 这样的内容.<br>
                                                                                这里的 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large"><span style="color:#ff7b72">$&#123;IFS&#125;</span></el-tag> 是 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">Unix/Linux</el-tag> 系统中的内部字段分隔符,它用于定义命令行工具(如 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">bash</el-tag>)在解析输入时使用的分隔符.<br>
                                                                                攻击者可以利用这种方式绕过对空格的过滤,从而执行恶意命令.</li>
                                                                </ul>
                                                                </p>
                                                                <br>

                                                                <!-- 绕过方法 -->
                                                                <h2 class="sub-title" id="p2-2">绕过方法</h2>
                                                                <ul>
                                                                        <!-- 路径/编码绕过 -->
                                                                        <li>
                                                                                <h3 class="sub-sub-title">1. 路径/编码绕过</h3>
                                                                                <p>在<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">Linux</el-tag>系统中,命令是<el-tag
                                                                                                type="danger">严格区分大小写</el-tag>的,但攻击者可以通过以下几种方式绕过过滤:</p>
                                                                                <el-card style="max-width:750px; background-color:#161b22;"
                                                                                        body-style="line-height: 2">
                                                                                        <pre class="code-block">
<span class="token comment">// 使用绝对路径(绕过 ls 过滤)</span>
<span class="token delimiter">&lt;?php</span>
        <span class="token variable">$cmd</span> <span class="token operator">=</span> <span class="token string">'/bin/ls -al'</span>;
        <span class="token function">system</span><span class="token punctuation">(</span><span class="token variable">$cmd</span><span class="token punctuation">)</span><span class="token punctuation">;</span>

        <span class="token comment">// 十六进制编码(ls = 0x6c73)</span>
        <span class="token variable">$cmd</span> <span class="token operator">=</span> <span class="token string">'echo 6c73 | xxd -r -p | sh'</span>; <span class="token comment">// 解码后执行 ls</span></pre>
                                                                                        <span
                                                                                                class="token delimiter">?&gt;</span>
                                                                                </el-card>
                                                                                <ul>
                                                                                        <br>
                                                                                        <li>
                                                                                                <h3 class="sub-sub-title">使用绝对路径:</h3>
                                                                                                如果应用程序只过滤了命令名(如 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">ls</el-tag>),而没有对命令的绝对路径进行过滤,攻击者可以使用命令的绝对路径来绕过过滤.<br>
                                                                                                例如,<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large"><span style="color:#ff7b72">/bin/ls</span> -al /</el-tag> 可以列出根目录下的所有文件和文件夹信息.
                                                                                        </li>
                                                                                        <br>
                                                                                        <li>
                                                                                                <h3 class="sub-sub-title">十六进制编码:</h3>
                                                                                                攻击者可以将命令进行十六进制编码,然后在执行时进行解码.<br>
                                                                                                例如,<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">ls</el-tag> 的十六进制编码是 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">6c73</el-tag>,通过 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">echo <span class="code">6c73</span> <span class="func">|</span> xxd <span style="color: #ff7b72;">-r -p</span> <span class="func">|</span> sh</el-tag> 可以将十六进制编码解码为 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">ls</el-tag> 并执行.
                                                                                                
                                                                                        </li>
                                                                                </ul>
                                                                        </li>
                                                                        
                                                                        <br>
                                                                        <!-- 命令分隔技巧 -->
                                                                        <li>
                                                                                <h3 class="sub-sub-title">2. 命令分隔技巧</h3>
                                                                                <el-card style="max-width:750px; background-color:#161b22;"
                                                                                        body-style="line-height: 2">
                                                                                        <pre class="code-block">
<span class="token comment">// 分号绕过(原始形式)</span>
<span class="token string">id; uname <span style="color: #ff7b72;">-a</span></span>

<span class="token comment">// URL编码形式(%0a 为换行符)</span>
<span class="token string">id%0auname%20<span style="color: #ff7b72;">-a</span></span>

<span class="token comment">// 逻辑运算符(需要前命令返回0)</span>
<span class="token string">ping 127.0.0.1 <span style="color: #ff7b72;">&&</span> cat <span style="color:#ff7b72">/etc/passwd</span></span></pre>
                                                                                </el-card>
                                                                                <ul>
                                                                                        <br>
                                                                                        <li>
                                                                                                <h3 class="sub-sub-title">分号绕过:</h3>
                                                                                                在许多系统中,分号<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">;</el-tag>可以用来分隔多个命令.<br>
                                                                                                例如,<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">id; uname <span style="color: #ff7b72;">-a</span></el-tag> 命令,先执行 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">id</el-tag> ,然后执行 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">uname <span style="color: #ff7b72;">-a</span></el-tag> 命令.<br>
                                                                                                如果应用程序没有对分号进行过滤,攻击者可以利用这种方式在允许的命令后面添加额外的恶意命令.

                                                                                        </li>
                                                                                        <br>
                                                                                        <li>
                                                                                                <h3 class="sub-sub-title">URL编码形式:</h3>
                                                                                                攻击者可以使用URL编码来绕过对特殊字符的过滤.<br>
                                                                                                例如,<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">%0a</el-tag> 代表换行符,<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">%20</el-tag> 代表空格.通过将命令进行<el-tag style="color:#e1567b;font-size: 14px;" type="info" size="large">URL</el-tag>编码,攻击者可以在不触发过滤机制的情况下执行多个命令.

                                                                                        </li>
                                                                                        <br>
                                                                                        <li>
                                                                                                <h3 class="sub-sub-title">逻辑运算符:</h3>
                                                                                                逻辑运算符(如 <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">&&</el-tag> )可以用来连接多个命令,只有当前一个命令返回状态码为0(表示成功)时,才会执行后面的命令.<br>例如,<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">ping 127.0.0.1<span style="color: #ff7b72;"> &&</span> cat <span style="color: #ff7b72;">/etc/passwd</span></el-tag> 会先尝试ping本地地址,如果ping成功,则会执行 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">cat <span style="color: #ff7b72;">/etc/passwd</span></el-tag> 命令.
                                                                                        </li>
                                                                                </ul>
                                                                        </li>
                                                                        
                                                                        <br>
                                                                        <!-- 高级替换技巧 -->
                                                                        <li>
                                                                                <h3 class="sub-sub-title">3. 高级替换技巧</h3>
                                                                                <el-card style="max-width:750px; background-color:#161b22;"
                                                                                        body-style="line-height: 2">
                                                                                        <pre class="code-block">
<span class="token comment">// 环境变量拼接</span>
<span class="token string">
${parameter:-word}<span class="cmt">
        parameter:要检查的变量名.
        word:如果变量 parameter 未定义或为空时,将返回word
</span>
'l$&#123;undefined:-s&#125;'</span>  <span class="token comment">// 等效 ls
</span>

<span class="token comment">// 通配符利用</span>
<span class="token string">'/???/c?t /etc/pass*'</span> <span class="token comment">// 匹配 /bin/cat</span>

<span class="token comment">// PHP变量动态构造</span>
<span class="token delimiter">&lt;?php</span>
        <span class="token variable">$a</span> <span class="token operator">=</span> <span class="token string">'l'</span><span class="token punctuation">;</span>
        <span class="token variable">$b</span> <span class="token operator">=</span> <span class="token string">'s -l /etc'</span><span class="token punctuation">;</span>
        <span class="token function">system</span><span class="token punctuation">(</span><span class="token variable">$a</span> <span class="token operator">.</span> <span class="token string">"s -l"</span><span class="token punctuation">)</span><span class="token punctuation">;</span> <span class="token comment">// 最终执行 ls -l /etc</span></pre>
<span class="token delimiter">?&gt;</span>
                                                                                </el-card>
                                                                                <ul>
                                                                                        <br>
                                                                                        <li>
                                                                                                <p><h3 class="sub-sub-class">环境变量拼接</h3>
                                                                                                        攻击者可以利用环境变量来拼接命令.<br>
                                                                                                        例如<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">l${undefined:-s}</el-tag>:<br>
                                                                                                        会尝试获取 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">undefined</el-tag> 环境变量的值,如果该变量未定义,则使用 <code>s</code> 作为默认值,最终等效于 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">ls</el-tag> 命令.</p>

                                                                                        </li>
                                                                                        <br>
                                                                                        <li>
                                                                                                <p><h3 class="sub-sub-class">通配符利用</h3>
                                                                                                        通配符(如 <code>?</code> 和 <code>*</code>)可以用来匹配任意字符.<br>
                                                                                                        例如<el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large"><span style="color: #ff7b72;">/<span style="color: #fff;">???</span>/c<span style="color: #fff;">?</span>t</span> <span style="color: #ff7b72;">/cat/pass<span style="color: #fff;">*</span></span></el-tag> :<br>
                                                                                                        会匹配所有以 <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">/</el-tag> 开头,中间有三个任意字符,然后是 <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">/c</el-tag> 后面跟一个任意字符,最后是 <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">t</el-tag><br>
                                                                                                        通常会匹配到 <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">/bin/cat</el-tag> ,并尝试读取 <el-tag style="color:#ff7b72;font-size: 14px;" type="info" size="large">/etc/passwd</el-tag> 文件.</p>
                                                                                                
                                                                                        </li>
                                                                                        <br>
                                                                                        <li>
                                                                                                <p><h3 class="sub-sub-class">PHP变量动态构造</h3>
                                                                                                        在PHP中,攻击者可以通过动态构造变量来执行命令.<br>
                                                                                                        例如,通过将 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">l</el-tag> 和 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">s -l /etc</el-tag>分别赋值给变量 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">$a</el-tag> 和 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">$b</el-tag> ,然后将它们拼接起来执行 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">ls -l <span style="color: #ff7b72;">/etc</span></el-tag> 命令.</p>

                                                                                        </li>
                                                                                </ul>
                                                                        </li>
                                                                </ul>
                                                                <br>
                                                                <br>
                                                                <br>

                                                                <div style="height: 1000px;">
                                                                        
                                                                        <!-- 防范措施 -->
                                                                        <h1 class="title" id="p3">防范措施</h1>
                                                                        <el-divider />
                                                                        <el-card style="max-width:750px; background-color:#161b22;"
                                                                                body-style="line-height: 2">
                                                                                <pre class="code-block">
        <span class="token comment">// 1. 白名单验证(PHP示例)</span>
        <span class="token variable">$allowed</span> <span class="token operator">=</span> <span class="token punctuation">[</span><span class="token string">'ping'</span> <span class="token operator">=></span> <span class="token string">'/^[0-9\.]+$/'</span><span class="token punctuation">]</span><span class="token punctuation">;</span>
        <span class="token keyword">if</span> <span class="token punctuation">(</span><span class="token operator">!</span><span class="token function">preg_match</span><span class="token punctuation">(</span><span class="token variable">$allowed</span><span class="token punctuation">[</span><span class="token variable">$_GET</span><span class="token punctuation">[</span><span class="token string">'cmd'</span><span class="token punctuation">]</span><span class="token punctuation">]</span><span class="token punctuation">,</span> <span class="token variable">$_GET</span><span class="token punctuation">[</span><span class="token string">'args'</span><span class="token punctuation">]</span><span class="token punctuation">)</span><span class="token punctuation">)</span> <span class="token punctuation">{</span>
        <span class="token keyword">die</span><span class="token punctuation">(</span><span class="token string">'非法操作'</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
        <span class="token punctuation">}</span>
        
        <span class="token comment">// 2. 安全命令执行(使用 escapeshellcmd)</span>
        <span class="token function">system</span><span class="token punctuation">(</span><span class="token function">escapeshellcmd</span><span class="token punctuation">(</span><span class="token string">'/usr/sbin/ping -c 4 '</span> <span class="token operator">.</span> <span class="token variable">$_GET</span><span class="token punctuation">[</span><span class="token string">'ip'</span><span class="token punctuation">]</span><span class="token punctuation">)</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
        
        <span class="token comment">// 3. 使用受限权限运行Web服务</span>
        <span class="token comment">// 创建专用用户并设置权限:</span>
        <span class="token comment">// $ sudo useradd -r -s /bin/false webuser</span>
        <span class="token comment">// $ sudo chown -R webuser:webuser /var/www</span></pre>
                                                                        </el-card>
                                                                        <ul>
                                                                                <br>
                                                                                <li>
                                                                                        <p><h2 class="sub-title" id="p3-1">白名单验证</h2>
                                                                                                与黑名单过滤不同,白名单验证只允许执行预先定义好的命令和参数.<br>
                                                                                                通过使用正则表达式对用户输入的命令和参数进行验证,只有符合规则的输入才会被执行.<br>
                                                                                                例如,在PHP中,可以使用 <el-tag style="color:#d2a8ff;font-size: 14px;" type="info" size="large">preg_match</el-tag> 函数来验证用户输入的IP地址是否合法.</p>
        
                                                                                </li>
                                                                                <br>
                                                                                <li>
                                                                                        <p><h2 class="sub-title" id="p3-2">安全命令执行</h2>
                                                                                                在执行用户输入的命令时,应该使用安全的函数来处理输入.<br>
                                                                                                例如,在PHP中,可以使用 <el-tag style="color:#d2a8ff;font-size: 14px;" type="info" size="large">escapeshellcmd</el-tag> 函数来对用户输入的命令进行转义,防止命令注入攻击.<br>
                                                                                                这样可以确保用户输入的特殊字符被正确处理,不会被解释为命令分隔符或其他危险字符.</p>
        
                                                                                </li>
                                                                                <br>
                                                                                <li>
                                                                                        <p><h2 class="sub-title" id="p3-3">使用受限权限运行Web服务</h2>
                                                                                                为了减少攻击造成的损失,可以使用受限权限的用户来运行Web服务.<br>
                                                                                                通过创建专用的用户,并将Web服务的文件和目录的所有权设置为该用户,可以限制Web服务的访问权限.<br>
                                                                                                例如,使用 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">sudo useradd -r -s <span style="color: #ff7b72;">/bin/false</span> webuser</el-tag> 创建一个没有登录权限的用户<br>
                                                                                                然后使用 <el-tag style="color:#a5d6ff;font-size: 14px;" type="info" size="large">sudo chown -R webuser:webuser <span style="color: #ff7b72;">/var/www</span></el-tag> 将Web服务的文件和目录的所有权设置为该用户.</p>
                                                                                </li>
                                                                        </ul>
                                                                        <div style="height: 300px;"></div>
                                                                </div>
                                                        </div>
                                                </el-text>
                                        </div>
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
              <el-menu-item index="p2">绕过字符串过滤限制</el-menu-item>
              <el-submenu index="p2-sub">
                <template slot="title">子目录</template>
                <el-menu-item index="p2-1">过滤原理</el-menu-item>
                <el-menu-item index="p2-2">漏洞成因</el-menu-item>
              </el-submenu>
              <el-menu-item index="p3">防御措施</el-menu-item>
              <el-submenu index="p3-sub">
                <template slot="title">子目录</template>
                <el-menu-item index="p3-1">白名单验证</el-menu-item>
                <el-menu-item index="p3-2">安全命令执行</el-menu-item>
                <el-menu-item index="p3-3">使用受限权限运行Web服务</el-menu-item>
              </el-submenu>
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
      activeAnchor: 'p2',
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
        const sections = ['p2', 'p2-1', 'p2-2', 'p3', 'p3-1', 'p3-2', 'p3-3']
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

<style scoped>
::-webkit-scrollbar {
  display: none;
}
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
        color: #ff7b72;
}

.token.delimiter {
        color: #ff7b72;
}

/* Card styling */
.el-card {
        border: 1px solid #30363d;
        margin: 10px 0;
}

/* Anchor styling */
.tech-anchor {
        position: fixed;
        right: 20px;
        background-color: #0d1117;
        padding: 15px;
        border-radius: 6px;
        border: 1px solid #30363d;
}

.tech-submit-btn {
        /* 基础样式 */
        border: none;
        padding: 12px 24px;
        font-weight: bold;
        letter-spacing: 1px;
        text-transform: uppercase;
        position: relative;
        overflow: hidden;
        transition: all 0.3s ease;

        /* 确保按钮内容居中 */
        display: inline-flex;
        align-items: center;
        justify-content: center;

        /* 渐变背景 - 科技蓝 */
        background: linear-gradient(135deg, #00a1ff 0%, #0066ff 50%, #0033cc 100%);

        /* 文字和图标颜色 */
        color: white !important;

        /* 圆角 - 使用round属性已设置 */

        /* 阴影 - 外发光效果 */
        box-shadow: 0 0 10px rgba(0, 150, 255, 0.5),
                0 0 20px rgba(0, 100, 255, 0.3),
                inset 0 0 5px rgba(255, 255, 255, 0.2);
}

.tech-submit-btn:hover {
        /* 悬停时增强发光效果 */
        box-shadow: 0 0 15px rgba(0, 150, 255, 0.8),
                0 0 30px rgba(0, 100, 255, 0.5),
                inset 0 0 10px rgba(255, 255, 255, 0.3);

        /* 悬停时轻微放大 */
        transform: scale(1.05);

        /* 悬停时改变渐变角度 */
        background: linear-gradient(135deg, #00a1ff 0%, #0066ff 50%, #0033cc 100%);
}

.tech-submit-btn:active {
        /* 点击时效果 */
        transform: scale(0.98);
        box-shadow: 0 0 5px rgba(0, 150, 255, 0.8),
                inset 0 0 10px rgba(0, 0, 0, 0.3);
}

.tech-submit-btn::before {
        /* 创建光效层 */
        content: '';
        position: absolute;
        top: -50%;
        left: -50%;
        width: 200%;
        height: 200%;
        background: linear-gradient(to bottom right,
                        rgba(255, 255, 255, 0) 0%,
                        rgba(255, 255, 255, 0.1) 50%,
                        rgba(255, 255, 255, 0) 100%);
        transform: rotate(45deg);
        animation: shine 3s infinite;
        opacity: 0;
}

.tech-submit-btn:hover::before {
        opacity: 1;
}

.btn-content {
        position: relative;
        z-index: 1;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
        /* 确保内容区域占满整个按钮 */
}

.btn-icon {
        font-size: 16px;
        transition: all 0.3s ease;
        margin-right: 0px;
        /* 用margin替代gap确保更好的兼容性 */
}

.btn-text {
        transition: all 0.3s ease;
}

.tech-submit-btn:hover .btn-icon {
        transform: translateY(-2px);
}

@keyframes shine {
        0% {
                left: -50%;
                top: -50%;
        }

        20% {
                left: 100%;
                top: 100%;
        }

        100% {
                left: 100%;
                top: 100%;
        }
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

/* 特殊定义的变量,比如PHP.EOL */
.v {
        color: #4cbcec;
        font-family: 'Cascadia Code';
}

/* 关键词与运算符,比如function,+-* */
.key {
        color: #e1567b;
        font-family: 'Cascadia Code';
}

/* 第1层{[()]} */
.lev0 {
        color: #f0de18;
        font-family: 'Cascadia Code';
}

/* 第2层{[()]} */
.lev1 {
        color: rgb(238, 65, 195);
        font-family: 'Cascadia Code';
}

/* 第3层{[()]} */
.lev2 {
        color: #239af5;
        font-family: 'Cascadia Code';
}

/* 函数名 */
.func {
        color: #84D49C;
        /* color:#434040 */
        font-family: 'Cascadia Code';
}

.txt {
        font-family: 'Cascadia Code';
}

/* 所有代码的基底,普通变量,超全局变量,标点符号'",; */
.code {
        color: #fff;
        font-family: 'Cascadia Code';
}

/* 表示注释 */
.cmt {
        color: rgb(99, 97, 97);
        font-family: 'Cascadia Code';
        font-size: 15px
}

/* 表示数字 */
.num {
        color: #b891d8;
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

.tech-anchor {
  position: fixed;
  right: 20px;
  background-color: #0d1117;
  padding: 15px;
  border-radius: 6px;
  border: 1px solid #30363d;
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