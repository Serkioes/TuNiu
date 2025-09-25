import './assets/reset.scss'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8080'

//导入Element-Plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
//导入Element-Plus的图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import BaiduMap from 'vue-baidu-map-3x'

//导入全局组件
import Navi from '@/components/global/Navi.vue'
import Title from '@/components/global/Title.vue'
import Login from "@/components/global/Login.vue";
const app = createApp(App)
app.use(ElementPlus, {
    locale: zhCn,
})
//注册Element-Plus的图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

//注册全局组件
app.component('Navi', Navi)
app.component('Title', Title)
app.component('Login',Login)

app.use(createPinia())
app.use(router)
app.use(BaiduMap, {
    // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
    ak: 'DsFFl7sO8nLwoxDlLuNvX9GJC9T2s0WY',
    // v:'2.0',  // 默认使用3.0
    // type: 'WebGL' // ||API 默认API  (使用此模式 BMap=BMapGL)
});

app.mount('#app')
