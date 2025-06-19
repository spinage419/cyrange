<template>
        <div class="common-layout">
                <el-main>
                        <div class="title-section">
                                <h1 class="cyber-title">
                                        <span class="title-text">CYBER</span>
                                        <span class="title-accent">RANGE</span>
                                </h1>
                                <!-- 修改为 Kali 终端风格的标语 -->
                                <div class="terminal-slogan">
                                        <span class="prompt-line">
                                                <span class="prompt-dir">$</span>
                                        </span>
                                        <!-- <span class="command">Dare to <span class="username">Hack</span>, Dare to <span
                                                        class="prompt-host">Win</span>.</span>
                                        <span class="cursor">_</span> -->
                                        <span class="command">Hack<span class="username"> Ethically</span>, Secure <span
                                                        class="prompt-host">Responsibly</span>.</span>
                                        <span class="cursor">_</span>
                                </div>
                                <!-- <p class="subtitle">快来学习漏洞知识吧~</p> -->
                        </div>
                </el-main>
        </div>
</template>
<style lang="less" scoped>
.title-section {
        text-align: center;
        margin: 10rem 0;

        .cyber-title {
                font-family: 'Orbitron', sans-serif;
                font-size: 4.5rem;
                margin-bottom: 1.5rem;
                letter-spacing: 2px;
                position: relative;
                display: inline-block;
                padding: 0 2rem;

                &::after {
                        content: '';
                        position: absolute;
                        bottom: -10px;
                        left: 50%;
                        transform: translateX(-50%);
                        width: 60%;
                        height: 2px;
                        background: linear-gradient(90deg, transparent, #007bff, transparent);
                }

                .title-text {
                        color: rgba(106, 183, 255, 0.5);
                        text-shadow: 0 0 20px rgba(106, 183, 255, 0.5);
                        position: relative;
                        animation: text-flicker 3s infinite alternate;
                }

                .title-accent {
                        color: rgba(52, 254, 151, 0.5);
                        margin-left: 1rem;
                        text-shadow: 0 0 20px rgba(52, 254, 151, 0.5);
                        position: relative;
                        animation: text-flicker 3s infinite alternate-reverse;
                }

                //     .title-cursor {
                //         color: #34fe97;
                //         animation: blink-caret 0.8s step-end infinite;
                //         margin-left: 5px;
                //         font-weight: normal;
                //     }
        }

        .subtitle {
                color: rgba(209, 231, 255, 0.5);
                font-size: 1.4rem;
                letter-spacing: 4px;
                margin-bottom: 3rem;
                text-shadow: 0 0 10px rgba(209, 231, 255, 0.3);
                font-family: 'Orbitron', sans-serif;
                position: relative;

                &::before,
                &::after {
                        content: '//';
                        color: rgba(0, 123, 255, 0.3);
                        margin: 0 15px;
                        font-family: 'Courier New', monospace;
                }
        }

        .title-button1 {
                margin-bottom: 3rem;
        }
}

/* 修改终端标语样式为 Kali 风格 */
.terminal-slogan {
        font-family: 'Cascadia Code','Courier New', monospace;
        color: #e0e0e0;
        font-size: 1.3rem;
        font-weight: 500;
        margin: 1.5rem 0;
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 2px;

        .prompt-line {
                display: flex;
                align-items: center;
        }

        .prompt-user {
                color: #a9a6a6;
                /* 普通文本颜色 */
        }

        .username {
                // color: crimson;
                color: rgba(52, 254, 151, 0.5);
                // margin-left: 1rem;
                text-shadow: 0 0 20px rgba(52, 254, 151, 0.5);
                /* Kali 普通用户名的绿色 */
        }

        .prompt-at {
                color: #e0e0e0;
                /* ㉿符号颜色 */
        }

        .prompt-host {
                // color: #4E9A06;
                color: rgba(106, 183, 255, 0.5);
                text-shadow: 0 0 20px rgba(106, 183, 255, 0.5);
                /* 主机名绿色 */
        }

        .prompt-dir {
                color: #3465A4;
                /* 目录蓝色 */
        }

        .command {
                color: #bcb9b9;
                /* 命令文本颜色 */
                margin-left: 4px;
        }

        /* 方块光标 */
        .cursor {
                color: #e0e0e0;
                animation: blink 1s step-end infinite;
        }
}

/* 光标闪烁动画 */
@keyframes blink {

        from,
        to {
                opacity: 0;
        }

        50% {
                opacity: 1;
        }
}
</style>