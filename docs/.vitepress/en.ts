import {defineConfig} from "vitepress";

const en = defineConfig({
    title: "MyBot",
    description: "MyBot docs",
    themeConfig: {
        nav: [
            { text: 'Home', link: '/en/' },
            { text: 'Docs', link: '/en/deployment' }
        ],

        sidebar: [
            {
                text: "For Administrator",
                items: [
                    { text: "Deployment", link: "/en/deployment" },
                    { text: "Configuration", link: "/en/config" }
                ]
            },
            {
                text: 'For Developer',
                items: [
                    { text: 'Quick Start', link: '/en/quick-start' },
                ]
            },
            {
                text: "API Reference",
                items: [
                    { text: "API Overview", link: '/en/api-list' }
                ]
            }
        ],

        socialLinks: [
            { icon: 'github', link: 'https://github.com/Wzp-2008/MyBot/', ariaLabel: "GitHub" },
            { icon: 'github', link: 'https://wzpmc.cn:3000/wzp/MyBot', ariaLabel: "Gitea" }
        ],
    },
});
export default en;