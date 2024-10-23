import { defineConfig } from 'vitepress'
import zh from "./zh";
import en from "./en";

// https://vitepress.dev/reference/site-config
export default defineConfig({
  ...zh,
  locales: {
    root: {
      label: '简体中文',
      lang: 'zh'
    },
    en: {
      label: 'English',
      lang: 'en',
      link: '/en/',
      ...en
    }
  }
})
