import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router'
import store from './store'
import axios from 'axios'
import VueAxios from 'vue-axios'

axios.defaults.baseURL = '/api'
axios.defaults.withCredentials = true

const app = createApp(App)
app.use(store).use(router).use(VueAxios, axios);
app.use(ElementPlus);
app.provide('axios', app.config.globalProperties.axios);
app.mount('#app')

