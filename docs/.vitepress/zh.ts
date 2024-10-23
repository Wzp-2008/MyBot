import {defineConfig} from "vitepress";

const zh = defineConfig({
    title: "MyBot",
    description: "MyBot文档",
    themeConfig: {
        // https://vitepress.dev/reference/default-theme-config
        nav: [
            { text: '主页', link: '/' },
            { text: '文档', link: '/deployment' }
        ],

        sidebar: [
            {
                text: "对管理员",
                items: [
                    { text: "部署MyBot", link: '/deployment' },
                    { text: "配置", link: "/config" }
                ]
            },
            {
                text: '对开发者',
                items: [
                    { text: '快速开始', link: '/quick-start' },
                ]
            },
            {
                text: "API参考",
                items: [
                    { text: "API总览", link: '/api-list' }
                ]
            }
        ],

        socialLinks: [
            { icon: 'github', link: 'https://github.com/Wzp-2008/MyBot/', ariaLabel: "GitHub" },
            { icon: 'github', link: 'https://wzpmc.cn:3000/wzp/MyBot', ariaLabel: "Gitea" }
        ],
    },
});
export default zh;