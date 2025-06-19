<template>
        <div id="xss-test"></div>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;">
                                        </div>

                                        <div id="part2" style="height: 650px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2" style="height: 700px;">
                                                                <h1 class="title">XXE-XML外部实体注入</h1>
                                                                <el-divider />
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>外部实体注入是:<br>
                                                                一种针对<el-tag style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>应用程序的安全漏洞,攻击者通过操纵<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>实体定义来注入恶意内容
                                                                <br>
                                                                <br>
                                                                <h2 class="sub-title" id="p2-1">原理</h2>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>实体是 <el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>文档中用于定义可重复使用的元素或数据的一种机制.<br>
                                                                在 <el-tag style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>解析过程中,解析器会根据实体定义来替换相应的实体引用.攻击者利用这一特性,通过构造恶意的
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>实体定义,<br>
                                                                让解析器在处理 <el-tag style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>文档时执行非预期的操作
                                                                <br>
                                                        </div>
                                                        <div id="p3" style="height: 1100px;">
                                                                <h1 class="title">XML结构</h1>
                                                                <el-divider />
                                                                <br>
                                                                <ul>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-0">XML声明</h2>
                                                                                <ul>
                                                                                        <li>
                                                                                                包含了<el-tag
                                                                                                        style="color:#e1567b"
                                                                                                        type="info"
                                                                                                        size="">XML</el-tag>的版本号常见的如&lt;?<span
                                                                                                        class="func">xml</span>
                                                                                                <span
                                                                                                        class="num">version</span>="<span
                                                                                                        class="v">1.0</span>"?&gt;<br>
                                                                                        </li>
                                                                                        <li>
                                                                                                指定文档的编码方式:&lt;?<span
                                                                                                        class="func">xml</span>
                                                                                                <span
                                                                                                        class="num">version</span>="<span
                                                                                                        class="v">1.0</span>"
                                                                                                <span
                                                                                                        class="num">encoding</span>="<span
                                                                                                        class="v">UTF-8</span>"?&gt;<br>
                                                                                        </li>
                                                                                        <li>
                                                                                                <el-tag style="color:#e1567b"
                                                                                                        type="info"
                                                                                                        size="">standalone</el-tag>属性:表示文档是否独立,如&lt;?<span
                                                                                                        class="func">xml</span>
                                                                                                <span
                                                                                                        class="num">version</span>="<span
                                                                                                        class="v">1.0</span>"
                                                                                                <span
                                                                                                        class="num">standalone</span>="<span
                                                                                                        class="v">yes</span>"?&gt;<br>
                                                                                        </li>

                                                                                </ul>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-1">根元素</h2>
                                                                                <ul>
                                                                                        <li>
                                                                                                <el-tag style="color:#e1567b"
                                                                                                        type="info"
                                                                                                        size="">XML</el-tag>有且仅有一个根元素,其他所有元素都是它的后代元素
                                                                                        </li>
                                                                                </ul>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h2 class="sub-title" id="p3-2">子元素</h2>
                                                                                <ul>
                                                                                        <li>
                                                                                                是根元素或其他元素的后代元素,有自己的标签,属性,内容
                                                                                        </li>
                                                                                </ul>
                                                                        </li>
                                                                        <br>
                                                                </ul>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="">注意</el-tag>:元素标签名对大小写敏感
                                                                <br>
                                                        </div>
                                                        <div id="p4" style="height: 1600px;">
                                                                <h1 class="title">DTD-文档类型定义</h1>
                                                                <el-divider />
                                                                <br>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="">DTD</el-tag>并不是<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>文档的一部分,它们总是在根元素的定义之上,即<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">DTD</el-tag>像实体一样可以从外部加载
                                                                <br>
                                                                <ul>
                                                                        <li>
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="">XML</el-tag>文档的根元素通常与<el-tag
                                                                                        style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="">DTD</el-tag>中定义的根元素名称相匹配
                                                                        </li>
                                                                </ul>
                                                                <br>
                                                                <h2 class="sub-title" id="p4-1">语法</h2>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 1900px;" body-style="line-height: 2">
                                                                        &lt;?<span class="func">xml</span>&nbsp;<span
                                                                                class="num">version</span>="<span
                                                                                class="v">1.0</span>"&nbsp;<span
                                                                                class="num">encoding</span>="<span
                                                                                class="v">UTF-8</span>"?&gt;<br>
                                                                        <span class="cmt">&lt;-- DTD根元素通常与XML根元素同名
                                                                                --&gt</span><br>
                                                                        &lt;<span class="lev0">!DOCTYPE</span> <span
                                                                                class="func">Root</span> [<br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span class="cmt">&lt;--
                                                                                &lt;!DTD标记 标签名 值&gt; --&gt</span>
                                                                        <br>
                                                                        ]&gt;
                                                                        <br>
                                                                        <br>
                                                                        &lt;<span class="func">Root</span>&gt;<br>
                                                                        &lt;/<span class="func">Root</span>&gt;
                                                                </el-card>
                                                                <br>
                                                                <br>
                                                                <h2 class="sub-title" id="p4-2">引用外部DTD</h2>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="">SYSTEM</el-tag>,<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">PUBLIC</el-tag>关键字用于引用外部资源<br>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 1900px;" body-style="line-height: 2">
                                                                        &lt;?<span class="func">xml</span>&nbsp;<span
                                                                                class="num">version</span>="<span
                                                                                class="v">1.0</span>"&nbsp;<span
                                                                                class="num">encoding</span>="<span
                                                                                class="v">UTF-8</span>"?&gt;<br>
                                                                        <span class="cmt">&lt;-- DTD根元素通常与XML根元素同名
                                                                                --&gt</span><br>
                                                                        &lt;<span class="lev0">!DOCTYPE</span> <span
                                                                                class="func">Root</span> <span
                                                                                class="v">SYSTEM</span> <span
                                                                                class="cmt">"外部DTD的URL"</span> &gt;
                                                                        <br>
                                                                        <span class="cmt">&lt;-- 或者 --&gt;</span>
                                                                        <br>
                                                                        &lt;<span class="lev0">!DOCTYPE</span> <span
                                                                                class="func">Root</span> <span
                                                                                class="v">PUBLIC</span> <span
                                                                                class="cmt">公共标识符 "备用URL"</span> &gt;
                                                                        <br>
                                                                        <br>
                                                                        &lt;<span class="func">Root</span>&gt;<br>
                                                                        &lt;/<span class="func">Root</span>&gt;
                                                                </el-card>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>解析器遇到 &lt;<span
                                                                        class="func">!DOCTYPE</span> <span
                                                                        class="cmt">... <span class="v">SYSTEM</span>
                                                                        "xxx.dtd"</span>&gt; 时,<br>
                                                                会加载并解析指定的外部<el-tag style="color:#e1567b" type="info"
                                                                        size="">DTD</el-tag>文件,然后根据<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">DTD</el-tag>规则验证<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>文档的结构和内容有效性.<br>
                                                                (如果是 <el-tag style="color:#e1567b" type="info"
                                                                        size="">PUBLIC</el-tag> 类型的<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">DTD</el-tag>,解析器会先尝试通过公共标识符查找,找不到再访问后面的<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">URL</el-tag>.)
                                                        </div>
                                                        <div id="p5" style="height: 2850px;">
                                                                <h1 class="title">ENTITY实体</h1>
                                                                <el-divider />
                                                                <br>
                                                                <ul>
                                                                        <li>
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="">ENTITY</el-tag>就像<el-tag
                                                                                        style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="">XML</el-tag>中的变量,可以对其进行赋值,在<el-tag
                                                                                        style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="">XML</el-tag>文档的其他地方进行引用<br>
                                                                        </li>
                                                                        <li>
                                                                                实体在<el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="">XML</el-tag>的文档类型定义部分(<el-tag
                                                                                        style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="">DTD</el-tag>)被单独定义描述<br>

                                                                        </li>
                                                                        <li>
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="">XML</el-tag>文档的根元素通常与<el-tag
                                                                                        style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="">XML</el-tag>中定义的根元素名称相匹配
                                                                        </li>
                                                                        <br>
                                                                </ul>
                                                                <img src="../assets/ENTITY_example.png"
                                                                        style="width: 600px;">
                                                                <br>
                                                                前面的<el-tag style="color:#84D49C" type="info"
                                                                        size="">DOCTYPE</el-tag>表示这是一个<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">DTD</el-tag>,用<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">ENTITY</el-tag>定义了一个叫做name的实体,赋值为K4n9L4n<br>
                                                                后面可以直接用<el-tag style="color:#e1567b" type="info"
                                                                        size="">&</el-tag>跟上实体名引用这个实体,可以防止重复赋值
                                                                <br>
                                                                <br>
                                                                <h2 class="sub-title" id="p5-1">分类</h2>
                                                                <ul>
                                                                        <li>
                                                                                <h3 class="sub-sub-title">通用实体</h3>
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 1900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span
                                                                                                class="cmt">如上图的实体就是通用实体</span><br>
                                                                                        &lt;?<span
                                                                                                class="func">xml</span>&nbsp;<span
                                                                                                class="num">version</span>="<span
                                                                                                class="v">1.0</span>"&nbsp;<span
                                                                                                class="num">encoding</span>="<span
                                                                                                class="v">UTF-8</span>"?&gt;<br>
                                                                                        <span class="cmt">&lt;--
                                                                                                DTD根元素通常与XML根元素同名
                                                                                                --&gt</span><br>
                                                                                        &lt;<span
                                                                                                class="lev0">!DOCTYPE</span>
                                                                                        <span class="func"> Root</span>
                                                                                        [<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&lt;!
                                                                                        <span class="num">ENTITY</span>
                                                                                        <span class="v"> name</span>
                                                                                        "<span
                                                                                                class="lev2">K4n9L4n</span>"
                                                                                        &gt;<br>
                                                                                        ]&gt;
                                                                                        <br>
                                                                                        <br>
                                                                                        &lt;<span
                                                                                                class="func">Root</span>&gt;<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&lt;<span
                                                                                                class="func">Name</span>&gt;<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                class="v">&name;</span><br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&lt;<span
                                                                                                class="func">/Name</span>&gt;<br>
                                                                                        &lt;/<span
                                                                                                class="func">Root</span>&gt;
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h3 class="sub-sub-title">外部实体</h3>
                                                                                <el-card style="max-width:750px;
                                                                                        background-color:#282C34;
                                                                                        max-height: 1900px;"
                                                                                        body-style="line-height: 2">
                                                                                        &lt;?<span
                                                                                                class="func">xml</span>&nbsp;<span
                                                                                                class="num">version</span>="<span
                                                                                                class="v">1.0</span>"&nbsp;<span
                                                                                                class="num">encoding</span>="<span
                                                                                                class="v">UTF-8</span>"?&gt;<br>
                                                                                        <span class="cmt">&lt;--
                                                                                                DTD根元素通常与XML根元素同名
                                                                                                --&gt</span><br>
                                                                                        &lt;<span
                                                                                                class="lev0">!DOCTYPE</span>
                                                                                        <span class="func"> Root</span>
                                                                                        [<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&lt;!
                                                                                        <span class="num">ENTITY</span>
                                                                                        <span class="v"> name</span>
                                                                                        <span class="num"> SYSTEM</span>
                                                                                        <span
                                                                                                class="cmt"> "xml的url"</span>
                                                                                        &gt;<br>
                                                                                        ]&gt;
                                                                                        <br>
                                                                                        <br>
                                                                                        &lt;<span
                                                                                                class="func">Root</span>&gt;<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&lt;<span
                                                                                                class="func">Name</span>&gt;<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                class="v">&name;</span><br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&lt;<span
                                                                                                class="func">/Name</span>&gt;<br>
                                                                                        &lt;/<span
                                                                                                class="func">Root</span>&gt;
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                        <li>
                                                                                <h3 class="sub-sub-title">参数实体</h3>
                                                                                <el-card style="max-width:750px;
                                                                                background-color:#282C34;
                                                                                max-height: 1900px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="cmt">
                                                                                                必须定义在单独的dtd区域内,参数实体只能在同一个DTD中调用<br>
                                                                                                比如,用一个实体给另外一个实体赋值<br>
                                                                                                以<span class="key"> %
                                                                                                </span>开头<br>
                                                                                        </span><br>
                                                                                        &lt;?<span
                                                                                                class="func">xml</span>&nbsp;<span
                                                                                                class="num">version</span>="<span
                                                                                                class="v">1.0</span>"&nbsp;<span
                                                                                                class="num">encoding</span>="<span
                                                                                                class="v">UTF-8</span>"?&gt;<br>
                                                                                        <span class="cmt">&lt;--
                                                                                                DTD根元素通常与XML根元素同名
                                                                                                --&gt</span><br>
                                                                                        &lt;<span
                                                                                                class="lev0">!DOCTYPE</span>
                                                                                        <span class="func"> Root</span>
                                                                                        [<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&lt;!
                                                                                        <span class="num">ENTITY</span>
                                                                                        <span class="key"> %</span> <span
                                                                                                class="v">outer</span>
                                                                                        " &lt;! <span
                                                                                                class="num">ENTITY</span>
                                                                                        <span class="v">inner</span>
                                                                                        <span class="num">SYSTEM</span>
                                                                                        <span class="cmt">"xxx"</span>
                                                                                        &gt; " &gt;<br>
                                                                                        ]&gt;
                                                                                        <br>
                                                                                        <br>
                                                                                        &lt;<span
                                                                                                class="func">Root</span>&gt;<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&lt;<span
                                                                                                class="func">Name</span>&gt;<br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                class="v">&name;</span><br>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&lt;<span
                                                                                                class="func">/Name</span>&gt;<br>
                                                                                        &lt;/<span
                                                                                                class="func">Root</span>&gt;
                                                                                </el-card>
                                                                        </li>
                                                                        <br>
                                                                </ul>
                                                        </div>
                                                        <div id="p6" style="height: 800px;">
                                                                <h1 class="title">CDATA</h1>
                                                                <el-divider />
                                                                <br>
                                                                在<el-tag style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>中有一些字符是不能单独出现的,比如<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">"</el-tag>,<el-tag style="color:#e1567b"
                                                                        type="info" size="">'</el-tag>,<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">&</el-tag>,<el-tag style="color:#e1567b"
                                                                        type="info" size="">&lt;</el-tag>,<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">&gt;</el-tag>;否则它们会被错误解析.<br>
                                                                那要是我们想在<el-tag style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>中包含代码等内容,怎么办?<br>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="">CDATA</el-tag>就用于解决这个问题<br>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="">CDATA</el-tag>是一种特殊的标记,用于告知解析器将包含的文本内容视为纯字符数据,而不解析其中的<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="">XML</el-tag>标记或实体引用
                                                                <br>
                                                                <br>
                                                                <h2 class="sub-title" id="p6-1">语法</h2>
                                                                以<el-tag style="color:#4cbcec" type="info"
                                                                        size="">&lt;![CDATA[</el-tag>开始,以<el-tag
                                                                        style="color:#4cbcec" type="info"
                                                                        size="">]]&gt;</el-tag>结束
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 1900px;" body-style="line-height: 2">
                                                                        <span class="code">
                                                                                <span class="v">&lt;![CDATA[ <span
                                                                                                class="cmt">这里是任意文本或特殊符号(例如
                                                                                                <span class="key">&lt;
                                                                                                        &gt; & "
                                                                                                        '</span>
                                                                                                )</span> ]]&gt;</span>
                                                                        </span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p7" style="height: 1000px;">
                                                                <h1 class="title">XML安全性问题</h1>
                                                                <el-divider />
                                                                <br>
                                                                前文我们提到了,使用<el-tag style="color:#e1567b" type="info"
                                                                size="">SYSTEM</el-tag>引用外部实体时,需要跟上外部实体的<el-tag style="color:#e1567b" type="info"
                                                                size="">URL</el-tag><br>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                size="">XML</el-tag>在此接收任意有效的<el-tag style="color:#e1567b" type="info"
                                                                size="">URL</el-tag>包括<el-tag style="color:#e1567b" type="info"
                                                                size="">file</el-tag>,<el-tag style="color:#e1567b" type="info"
                                                                size="">http</el-tag>,<el-tag style="color:#e1567b" type="info"
                                                                size="">ftp</el-tag>和其他协议形式的内容<br>
                                                                如果<el-tag style="color:#e1567b" type="info"
                                                                size="">WEB</el-tag>应用程序没有禁用外部实体的加载,那么这些协议的支持将带来广泛的攻击面<br>
                                                        </div>
                                                </el-text>
                                        </div>
                                </div>
                        </el-col>
                        <el-col :span="5">
                                <el-anchor :container="containerRef" @click.prevent direction="vertical" type="default"
                                        :offset="60" class="tech-anchor">
                                        <el-anchor-link>
                                                <h3>本页目录</h3>
                                        </el-anchor-link>
                                        <el-anchor-link href="#p2" title="XXE-XML外部实体注入">
                                                <template #sub-link>
                                                        <el-anchor-link href="#p2-1" title="原理" />
                                                </template>
                                        </el-anchor-link>
                                        <el-anchor-link href="#p3" title="XML结构">
                                                <template #sub-link>
                                                        <el-anchor-link href="#p3-0" title="XML声明" />
                                                        <el-anchor-link href="#p3-1" title="根元素" />
                                                        <el-anchor-link href="#p3-2" title="子元素" />
                                                        <!-- <el-anchor-link href="#p3-3" title="oninput" />
                                                        <el-anchor-link href="#p3-4" title="onsubmit" />
                                                        <el-anchor-link href="#p3-5" title="onmouseover / onmouseout" />
                                                        <el-anchor-link href="#p3-6" title="onkeydown / onkeyup /onkeypress" />
                                                        <el-anchor-link href="#p3-7" title="onerror" />
                                                        <el-anchor-link href="#p3-8" title="onload" />
                                                        <el-anchor-link href="#p3-9" title="onscroll" /> -->
                                                </template>
                                        </el-anchor-link>
                                        <el-anchor-link href="#p4" title="DTD-文档类型定义">
                                                <template #sub-link>
                                                        <el-anchor-link href="#p4-1" title="语法" />
                                                        <el-anchor-link href="#p4-2" title="引用外部DTD" />
                                                </template>
                                        </el-anchor-link>
                                        <el-anchor-link href="#p5" title="ENTITY实体">
                                                <template #sub-link>
                                                        <el-anchor-link href="#p5-1" title="分类" />
                                                        <!-- <el-anchor-link href="#p4-2" title="data:" /> -->
                                                </template>
                                        </el-anchor-link>
                                        <el-anchor-link href="#p6" title="CDATA">
                                                <template #sub-link>
                                                        <el-anchor-link href="#p6-1" title="语法" />
                                                </template>
                                        </el-anchor-link>
                                        <el-anchor-link href="#p7" title="XML安全性问题">
                                                <!-- <template #sub-link>
                                                </template> -->
                                        </el-anchor-link>
                                </el-anchor>
                        </el-col>
                </el-row>
        </div>
</template>

<script lang="ts" setup>
import { ElMessage } from 'element-plus';
import { ref } from 'vue';
import { XssTest } from '@/script/xss';

const input = ref('');
const containerRef = ref<HTMLElement | null>(null);

const submit = () => {
        // //   重写 window.alert 为 ElMessage
        // window.alert = (msg: string) => {
        //         ElMessage({
        //                 message: msg,
        //                 type: 'error',
        //         });
        // };

        if (input.value) {
                // 将用户输入的内容写入到隐藏的 div 中
                document.getElementById('xss-test')!.innerHTML = input.value;
                console.log(document.getElementById('xss-test')!.innerHTML);
                XssTest(input.value);
        }
};
</script>

<style scoped>
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

/* 锚点容器样式 */
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

/* 锚点标题样式 */
.anchor-title {
        color: #d1e7ff !important;
        font-family: 'Cascadia Code', '微软雅黑';
        margin: 0 0 15px 0;
        padding-bottom: 10px;
        border-bottom: 1px solid rgba(0, 123, 255, 0.3);
        text-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

/* 锚点链接基础样式 */
.tech-anchor :deep(.el-anchor-link) {
        margin: 8px 0;
}

.tech-anchor :deep(.el-anchor-link__title) {
        color: #a0c4ff !important;
        font-family: 'Cascadia Code', '微软雅黑';
        font-size: 0.9em;
        padding: 5px 10px;
        border-radius: 3px;
        transition: all 0.3s ease;
        position: relative;
}

/* 锚点悬停效果 */
.tech-anchor :deep(.el-anchor-link__title:hover) {
        color: #ffffff !important;
        background: rgba(0, 123, 255, 0.2);
        text-shadow: 0 0 5px #007bff;
        transform: translateX(5px);
}

.tech-anchor :deep(.el-anchor-link__title:hover)::before {
        content: '>';
        position: absolute;
        left: -10px;
        color: #00a2ff;
        opacity: 0;
        animation: blink-caret 0.8s step-end infinite alternate;
}

/* 激活锚点样式 */
.tech-anchor :deep(.el-anchor-link.active .el-anchor-link__title) {
        color: #ffffff !important;
        background: rgba(0, 123, 255, 0.3);
        text-shadow: 0 0 10px #00a2ff;
        border-left: 2px solid #007bff;
        padding-left: 15px;
        animation: pulse-glow 2s infinite alternate;
}

/* 子链接样式 */
.tech-anchor :deep(.el-anchor-link .el-anchor-link) {
        margin-left: 20px;
}

.tech-anchor :deep(.el-anchor-link .el-anchor-link__title) {
        font-size: 0.85em;
        color: #8ab4f8 !important;
}

/* 标记线样式 */
.tech-anchor :deep(.el-anchor__marker) {
        background: linear-gradient(to bottom, transparent, #007bff, transparent);
        opacity: 0.7;
        width: 2px !important;
        left: 0 !important;
}

/* 响应式调整 */
@media (max-width: 768px) {
        .tech-anchor {
                padding: 10px;
        }

        .tech-anchor :deep(.el-anchor-link__title:hover)::before {
                display: none;
        }
}
</style>