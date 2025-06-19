<template>
        <div>
                <el-row>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;">
                                        </div>

                                        <div id="part2" style="height: 350px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <div id="p2-1" style="height: 50px;">
                                                                <h1 class="title">原理</h1>
                                                                <el-divider />
                                                                反序列化<el-tag style="color:#0b915d" type="info"
                                                                        size="default">unserialize()</el-tag>中接收的字符串可控,通过更改字符串得到想要的对象
                                                        </div>
                                                </el-text>
                                        </div>
                                        <div id="part3" style="height: 1000px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <h1 class="title">php基础知识</h1>
                                                        <el-divider />
                                                        <div id="p3-1" style="height: 600px;">
                                                                <h2 class="sub-title">php中类和对象的写法</h2>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;" body-style="line-height: 2">
                                                                        <span class="v">class</span> <span
                                                                                class="code">Test</span><span
                                                                                class="lev0">{<br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="cmt">//不推荐使用var修饰(过时了,且不提供访问控制)</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">var</span> <span
                                                                                        class="code">$id;</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">public</span> <span
                                                                                        class="code">$name;</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">private</span> <span
                                                                                        class="code">$email;</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">protected</span>
                                                                                <span class="code"> $tel;</span><br>
                                                                                }<br></span>
                                                                        <span class="code">$a = <span
                                                                                        class="key">new</span> Test<span
                                                                                        class="lev0">()</span>;</span><br>
                                                                        <span class="code">$a<span
                                                                                        class="key">-></span>name <span
                                                                                        class="key"> = </span>'<span
                                                                                        class="lev0">123</span>';</span><br>
                                                                </el-card>
                                                        </div>
                                                        <div id="p3-1-1" style="height: 600px;">
                                                                <h2 class="sub-title">引用</h2>
                                                                引用变量前用<el-tag style="color:#e1567b" type="info"
                                                                        size="default">&</el-tag>修饰<br>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;" body-style="line-height: 2">
                                                                        <span class="v">class</span> <span
                                                                                class="code">Test</span><span
                                                                                class="lev0">{<br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">public</span> <span
                                                                                        class="code">$a;</span><br>
                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                        class="key">public</span> <span
                                                                                        class="code">$b;</span><br>
                                                                                }<br></span>
                                                                        <span class="code">$test<span class="key"> =
                                                                                        new</span> Test<span
                                                                                        class="lev0">()</span>;</span><br>
                                                                        <span class="cmt">//a是b的引用</span><br>
                                                                        <span class="code">$test<span
                                                                                        class="key">-></span>a <span
                                                                                        class="key"> = &</span>test<span
                                                                                        class="key">-></span>b;</span><br>
                                                                </el-card>
                                                        </div>
                                                        <div id="p3-2" style="height: 550px;">
                                                                <h2 class="sub-title">php中打印对象实例信息</h2>
                                                                <el-card style="max-width:750px;
                                                        background-color:#282C34;
                                                        max-height: 900px;" body-style="line-height: 2">
                                                                        <span class="code"><span
                                                                                        class="func">var_dump</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">arg</span>)</span>;</span><br>
                                                                        <span class="cmt">/**<br>
                                                                                * <span class="key">@param</span>
                                                                                arg:某个类的实例化对象<br>
                                                                                */</span><br>
                                                                        <span class="code"><span
                                                                                        class="func">print_r</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">arg</span>)</span>;</span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p3-3" style="height: 650px;">
                                                                <h1 class="title">序列化</h1>
                                                                <el-divider />
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="default">序列化</el-tag>是将对象的状态信息(属性)转换为可以存储或传输的形式的过程<br>
                                                                <img src="../assets/serialize.png"
                                                                        style="width:750px;height: 200px;">
                                                                <br>
                                                                在<el-tag style="color:#fff" type="info"
                                                                        size="default">php</el-tag>中使用函数<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">serialize()</el-tag>来将对象或者数组进行序列化,并返回一个包含字节流的字符串来表示<br>
                                                                序列化对象的要求:
                                                                <ul>
                                                                        <li>
                                                                                可以序列化的对象:
                                                                                <el-tag style="color:#fff" type="info"
                                                                                        size="default">
                                                                                        实例对象,数组,标量类型(整形,浮点,布尔,字符串),包含资源的数组和对象,null
                                                                                </el-tag><br>
                                                                        </li>
                                                                        <li>
                                                                                不可以序列化的对象:
                                                                                <el-tag style="color:#fff" type="info"
                                                                                        size="default">
                                                                                        类,资源(比如数据库连接),未定义类型
                                                                                </el-tag>
                                                                        </li>
                                                                </ul>
                                                        </div>
                                                        <br>
                                                        <br>
                                                        <div id="p3-3-1" style="height:1500px">
                                                                <h3 class="sub-sub-title">表达方式</h3>
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="default">序列化</el-tag>中,不同类型的数据都会有不同的表达方式
                                                                <ul>
                                                                        <li>
                                                                                <el-tag style="color:#8e5fa0"
                                                                                        type="info"
                                                                                        size="default">null</el-tag> ->
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">N;</el-tag>
                                                                        </li>

                                                                        <li>
                                                                                整形:
                                                                                <el-tag style="color:#8e5fa0"
                                                                                        type="info"
                                                                                        size="default">123</el-tag> ->
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">i:123;</el-tag>
                                                                        </li>
                                                                        <li>
                                                                                字符串:
                                                                                <el-tag style="color:#fff" type="info"
                                                                                        size="default">'hello
                                                                                        world'</el-tag> -> <el-tag
                                                                                        style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">s:长度:"内容";</el-tag>
                                                                                -> <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">s:11:"hello
                                                                                        world";</el-tag>
                                                                        </li>
                                                                        <li>
                                                                                浮点型:
                                                                                <el-tag style="color:#8e5fa0"
                                                                                        type="info"
                                                                                        size="default">3.14</el-tag> ->
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">d:3.14;</el-tag>
                                                                        </li>
                                                                        <li>
                                                                                Boolean:
                                                                                <el-tag style="color:#8e5fa0"
                                                                                        type="info"
                                                                                        size="default">true</el-tag> ->
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">b:1;</el-tag>
                                                                                &nbsp;&nbsp;<el-tag
                                                                                        style="color:#8e5fa0"
                                                                                        type="info"
                                                                                        size="default">false</el-tag> ->
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">b:0;</el-tag>
                                                                        </li>
                                                                        <li>
                                                                                数组:
                                                                                <el-card style="max-width:650px;
                                                                                background-color:#282C34;
                                                                                max-height: 900px;
                                                                                font-size: 13px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="code">
                                                                                                $a = <span
                                                                                                        class="key">array</span><span
                                                                                                        class="lev0">(</span>'<span
                                                                                                        class="lev0">123</span>','<span
                                                                                                        class="lev0">abc</span>'<span
                                                                                                        class="lev0">)</span>;
                                                                                        </span><br>
                                                                                        <span
                                                                                                class="cmt">a:参数个数:{i:索引;元素序列化字符串;...;}</span><br>
                                                                                        <span
                                                                                                class="key">a:2:{i:0;s:3:"123";i:1;s:3:"abc";}</span>
                                                                                </el-card>
                                                                        </li>
                                                                        <li>
                                                                                对象:
                                                                                <el-card style="max-width:650px;
                                                                                background-color:#282C34;
                                                                                max-height: 900px;
                                                                                font-size: 13px;"
                                                                                        body-style="line-height: 2">
                                                                                        <span class="v">class</span>
                                                                                        <span class="code">
                                                                                                Test</span><span
                                                                                                class="lev0">{<br>

                                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                        class="key">public</span>
                                                                                                <span class="code">
                                                                                                        $name;</span><br>
                                                                                                &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                                        class="key">private</span>
                                                                                                <span class="code">
                                                                                                        $email <span
                                                                                                                class="key">=</span>
                                                                                                        '<span
                                                                                                                class="lev0">114514@qq.com</span>';</span><br>

                                                                                                }<br></span>
                                                                                        <span class="code">$a = <span
                                                                                                        class="key">new</span>
                                                                                                Test<span
                                                                                                        class="lev0">()</span>;</span><br>
                                                                                        <span class="code">$a<span
                                                                                                        class="key">-></span>name
                                                                                                <span class="key"> =
                                                                                                </span>123;</span><br>
                                                                                        <span class="cmt">
                                                                                                O:类名长度:"类名":变量数:{s:变量名长度:"变量名";变量的序列化字符串;...;}
                                                                                        </span><br>
                                                                                        <span class="key">
                                                                                                O:4:"Test":2:{s:4:"name";i:123;s:11:"<span
                                                                                                        style="color:rgb(99, 97, 97)">0x00</span>Test<span
                                                                                                        style="color:rgb(99, 97, 97)">0x00</span>email";s:13:"114514@qq.com";}
                                                                                        </span>
                                                                                </el-card>
                                                                                注意:<br>
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">private</el-tag>属性的成员变量序列化后,变量名前会加上<el-tag
                                                                                        style="color:#8e5fa0"
                                                                                        type="info"
                                                                                        size="default">0x00</el-tag>类名<el-tag
                                                                                        style="color:#8e5fa0"
                                                                                        type="info"
                                                                                        size="default">0x00</el-tag><br>
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">protected</el-tag>属性的成员变量序列化后,变量名前会加上<el-tag
                                                                                        style="color:#8e5fa0"
                                                                                        type="info"
                                                                                        size="default">0x00</el-tag>*<el-tag
                                                                                        style="color:#8e5fa0"
                                                                                        type="info"
                                                                                        size="default">0x00</el-tag><br>
                                                                                (<el-tag style="color:#8e5fa0"
                                                                                        type="info"
                                                                                        size="default">0x00</el-tag>是不可见字符,url编码后就是<el-tag
                                                                                        style="color:#8e5fa0"
                                                                                        type="info"
                                                                                        size="default">%00</el-tag>)<br>
                                                                        </li>
                                                                </ul>
                                                        </div>
                                                        <div id="p3-4" style="height: 500px;">
                                                                <h1 class="title">反序列化</h1>
                                                                <el-divider />
                                                                作用:将<el-tag style="color:#e1567b" type="info"
                                                                        size="default">序列化</el-tag>后的参数还原成<el-tag
                                                                        style="color:#fff" type="info"
                                                                        size="default">实例化</el-tag>的对象<br>
                                                                有以下几个特点:<br>
                                                                <ol>
                                                                        <li>
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">反序列化</el-tag>之后的内容为一个<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">实例化</el-tag>对象
                                                                        </li>
                                                                        <li>
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">反序列化</el-tag>生成的实例对象中成员变量的值,由<el-tag
                                                                                        style="color:#fff" type="info"
                                                                                        size="default">序列化字符串</el-tag>中的值提供;
                                                                                与原有类预定义的值无关
                                                                        </li>
                                                                        <li>
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">反序列化</el-tag>不触发类的成员方法,需要调用方法后才能触发
                                                                        </li>
                                                                </ol>
                                                        </div>
                                                        <div id="p3-5" style="height: 300px;">
                                                                <h1 class="title">魔术方法</h1>
                                                                <el-divider />
                                                                <el-tag style="color:#e1567b" type="info"
                                                                        size="default">魔术方法</el-tag>指的是在特定条件下自动触发的方法;由4部分组成:<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">触发时机</el-tag>,<el-tag
                                                                        style="color:black" type="info"
                                                                        size="default">功能</el-tag>,<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">参数</el-tag>,<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">返回值</el-tag><br>
                                                        </div>
                                                        <div id="p3-5-1" style="height: 700px;">
                                                                <h2 class="sub-title">构造__construct()</h2>
                                                                与其他高级语言中 <el-tag style="color:#fff" type="info"
                                                                        size="default">构造器 | 构造函数</el-tag>
                                                                的作用类似,用于创建及初始化实例化对象
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        * 触发时机:当new对象时自动调用(构造器)<br>
                                                                                        * 功能:初始化对象<br>
                                                                                        * 参数:非必要<br>
                                                                                        * 返回值:无<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__construct</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">$arg...</span>)</span>;
                                                                        </span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p3-5-2" style="height: 550px;">
                                                                <h2 class="sub-title">析构__destruct()</h2>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;" body-style="line-height: 2">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        *
                                                                                        触发时机:对象的所有引用被删除或对象被显式销毁时执行,反序列化之后执行,代码运行结束执行<br>
                                                                                        * 参数:无<br>
                                                                                        * 返回值:无<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__destruct</span><span
                                                                                        class="lev0">()</span>;
                                                                        </span>
                                                                </el-card>

                                                        </div>
                                                        <div id="p3-5-3" style="height: 730px;">
                                                                <h2 class="sub-title">__sleep()</h2>
                                                                序列化时,函数<el-tag style="color:#0b915d" type="info"
                                                                        size="default">serialize()</el-tag>会先检查类中存不存在魔术方法<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">__sleep()</el-tag><br>
                                                                如果存在,该方法会先被调用,然后再执行序列化操作
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;" body-style="line-height: 2">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        * 触发时机:执行serialize()之前<br>
                                                                                        * 功能:返回需要被序列化的成员<br>
                                                                                        * 参数:无<br>
                                                                                        * 返回值:包含需要被序列化的成员属性名的数组<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__sleep</span><span
                                                                                        class="lev0">()</span>;
                                                                        </span>
                                                                </el-card>

                                                        </div>
                                                        <div id="p3-5-4" style="height: 650px;">
                                                                <h2 class="sub-title">__wakeup()</h2>
                                                                反序列化时,函数<el-tag style="color:#0b915d" type="info"
                                                                        size="default">unserialize()</el-tag>会先检查类中存不存在魔术方法<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">__wakeup()</el-tag><br>
                                                                如果存在,该方法会先被调用,预先准备对象需要的资源<br>
                                                                例如,可以为序列化字符串中没有值的成员赋值
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;" body-style="line-height: 2">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        * 触发时机:执行unserialize()之前<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__wakeup</span><span
                                                                                        class="lev0">()</span>;
                                                                        </span>
                                                                </el-card>

                                                        </div>
                                                        <div id="p3-5-5" style="height: 600px;">
                                                                <h2 class="sub-title">__toString()</h2>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        *
                                                                                        触发时机:把对象当作字符串调用;调用对象要使用var_dump()或print_r()<br>
                                                                                        *&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;使用echo或print调用对象就会触发__toString()<br>
                                                                                        * 返回值:字符串<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__toString</span><span
                                                                                        class="lev0">()</span>;
                                                                        </span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p3-5-6" style="height: 550px;">
                                                                <h2 class="sub-title">__invoke()</h2>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;" body-style="line-height: 2">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        * 触发时机:把对象当作方法(函数)调用<br>
                                                                                        * 返回值:字符串<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__invoke</span><span
                                                                                        class="lev0">()</span>;
                                                                        </span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p3-5-7" style="height: 2050px;">
                                                                <h2 class="sub-title">错误调用</h2>
                                                                <br><br>
                                                                <h3 class="sub-sub-title">__call()</h3>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;" body-style="line-height: 2">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        * 触发时机:调用一个不存在的方法<br>
                                                                                        * <span
                                                                                                class="key">@param</span>
                                                                                        $arg1:调用的不存在方法的名称<br>
                                                                                        * <span
                                                                                                class="key">@param</span>
                                                                                        $arg2:调用的不存在方法的参数列表数组<br>
                                                                                        * 返回值:调用的不存在方法名称和参数<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__call</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">$arg1,$arg2</span>)</span>;
                                                                        </span>
                                                                </el-card>
                                                                <br><br>
                                                                <h3 class="sub-sub-title">__callStatic()</h3>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;" body-style="line-height: 2">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        *
                                                                                        触发时机:静态调用(::)或调用成员常量时使用的方法不存在<br>
                                                                                        * <span
                                                                                                class="key">@param</span>
                                                                                        $arg1:调用的不存在方法的名称<br>
                                                                                        * <span
                                                                                                class="key">@param</span>
                                                                                        $arg2:调用的不存在方法的参数列表数组<br>
                                                                                        * 返回值:调用的不存在方法名称和参数<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__callStatic</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">$arg1,$arg2</span>)</span>;
                                                                        </span>
                                                                </el-card>
                                                                <br><br>
                                                                <h3 class="sub-sub-title">__get()</h3>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;" body-style="line-height: 2">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        * 触发时机:调用的成员属性不存在<br>
                                                                                        * <span
                                                                                                class="key">@param</span>
                                                                                        $arg1:调用的不存在的成员属性的名称<br>
                                                                                        <!-- * <span class="key">@param</span> $arg2:调用的不存在方法的参数列表数组<br> -->
                                                                                        * 返回值:调用的不存在成员属性名称<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__get</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">$arg1</span>)</span>;
                                                                        </span>
                                                                </el-card>
                                                                <br><br>
                                                                <h3 class="sub-sub-title">__set()</h3>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        * 触发时机:给不存在的成员属性赋值<br>
                                                                                        * <span
                                                                                                class="key">@param</span>
                                                                                        $arg1:不存在的成员属性的名称<br>
                                                                                        * <span
                                                                                                class="key">@param</span>
                                                                                        $arg2:不存在的成员属性的值<br> *
                                                                                        返回值:调用的不存在成员属性名称<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__set</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">$arg1,$arg2</span>)</span>;
                                                                        </span>
                                                                </el-card>
                                                                <br><br>
                                                                <h3 class="sub-sub-title">__isset()</h3>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        *
                                                                                        触发时机:对不可访问或不存在属性使用isset()或empty()<br>
                                                                                        * <span
                                                                                                class="key">@param</span>
                                                                                        $arg1:不可访问或不存在的成员属性的名称<br>
                                                                                        <!-- * <span class="key">@param</span> $arg2:不存在的成员属性的值<br>                                                                                         -->
                                                                                        * 返回值:调用的不可访问或不存在成员属性名称<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__isset</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">$arg1</span>)</span>;
                                                                        </span>
                                                                </el-card>
                                                                <br><br>
                                                                <h3 class="sub-sub-title">__unset()</h3>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        * 触发时机:对不可访问或不存在属性使用unset()<br>
                                                                                        * <span
                                                                                                class="key">@param</span>
                                                                                        $arg1:不可访问或不存在的成员属性的名称<br>
                                                                                        <!-- * <span class="key">@param</span> $arg2:不存在的成员属性的值<br>                                                                                         -->
                                                                                        * 返回值:调用的不可访问或不存在成员属性名称<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__unset</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">$arg1</span>)</span>;
                                                                        </span>
                                                                </el-card>
                                                                <br><br>
                                                                <h3 class="sub-sub-title">__clone()</h3>
                                                                <el-card style="max-width:750px;
                                                                background-color:#282C34;
                                                                max-height: 900px;">
                                                                        <span class="code">
                                                                                <span class="cmt">
                                                                                        /*<br>
                                                                                        *
                                                                                        触发时机:当使用clone关键字拷贝完成一个对象后,新对象会自动调用定义的魔术方法__clone()<br>
                                                                                        */
                                                                                </span><br>
                                                                                <span class="key">function</span> <span
                                                                                        class="func">__clone</span><span
                                                                                        class="lev0">()</span>;
                                                                        </span>
                                                                </el-card>
                                                        </div>
                                                        <div id="p3-5-10" style="height: 500px;">

                                                        </div>
                                                </el-text>
                                        </div>
                                        <div id="part4" style="height: 300px;">

                                        </div>
                                </div>
                        </el-col>
                        <el-col :span="5">
        <el-menu 
          :default-active="activeAnchor" 
          class="tech-anchor" 
          mode="vertical"
          @select="handleAnchorSelect"
          :unique-opened="true"
        >
          <el-menu-item-group title="本页目录">
            <el-menu-item index="p2-1">原理</el-menu-item>
            
            <el-submenu index="part3">
              <template slot="title">php基础知识</template>
              <el-menu-item index="p3-1">php中类和对象</el-menu-item>
              <el-menu-item index="p3-1-1">引用</el-menu-item>
              <el-menu-item index="p3-2">php中打印对象实例信息</el-menu-item>
            </el-submenu>
            
            <el-submenu index="p3-3">
              <template slot="title">序列化</template>
              <el-menu-item index="p3-3">序列化</el-menu-item>
        </el-submenu>
        <el-menu-item index="p3-3-1">表达方式</el-menu-item>
            
            <el-menu-item index="p3-4">反序列化</el-menu-item>
            
            <el-submenu index="p3-5">
              <template slot="title">魔术方法</template>
              <el-menu-item index="p3-5-1">构造__construct()</el-menu-item>
              <el-menu-item index="p3-5-2">析构__destruct()</el-menu-item>
              <el-menu-item index="p3-5-3">__sleep()</el-menu-item>
              <el-menu-item index="p3-5-4">__wakeup()</el-menu-item>
              <el-menu-item index="p3-5-5">__toString()</el-menu-item>
              <el-menu-item index="p3-5-6">__invoke()</el-menu-item>
              <el-menu-item index="p3-5-7">错误调用</el-menu-item>
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
      observer: null,
      scrollLock: false
    }
  },
  mounted() {
    this.setupScrollSpy()
    window.addEventListener('scroll', this.handleScroll)
  },
  beforeDestroy() {
    if (this.observer) {
      this.observer.disconnect()
    }
    window.removeEventListener('scroll', this.handleScroll)
  },
  methods: {
    setupScrollSpy() {
      const options = {
        root: null,
        rootMargin: '0px',
        threshold: 0.5
      }

      this.observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting && !this.scrollLock) {
            this.activeAnchor = entry.target.id
          }
        })
      }, options)

      // 观察所有锚点元素
      const anchors = [
        'p2-1', 'p3-1', 'p3-1-1', 'p3-2', 
        'p3-3', 'p3-3-1', 'p3-4', 'p3-5',
        'p3-5-1', 'p3-5-2', 'p3-5-3', 'p3-5-4',
        'p3-5-5', 'p3-5-6', 'p3-5-7'
      ]
      
      anchors.forEach(id => {
        const el = document.getElementById(id)
        if (el) this.observer.observe(el)
      })
    },
    
    handleAnchorSelect(index) {
      this.scrollLock = true
      const el = document.getElementById(index)
      if (el) {
        // 使用平滑滚动
        el.scrollIntoView({ behavior: 'smooth' })
        // 0.5秒后释放滚动锁，防止滚动事件干扰
        setTimeout(() => {
          this.scrollLock = false
        }, 500)
      }
    },
    
    handleScroll() {
      if (this.scrollLock) return
      
      const anchors = [
        'p2-1', 'p3-1', 'p3-1-1', 'p3-2', 
        'p3-3', 'p3-3-1', 'p3-4', 'p3-5',
        'p3-5-1', 'p3-5-2', 'p3-5-3', 'p3-5-4',
        'p3-5-5', 'p3-5-6', 'p3-5-7'
      ]
      
      for (let i = anchors.length - 1; i >= 0; i--) {
        const el = document.getElementById(anchors[i])
        if (el) {
          const rect = el.getBoundingClientRect()
          if (rect.top <= 100) {
            this.activeAnchor = anchors[i]
            break
          }
        }
      }
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