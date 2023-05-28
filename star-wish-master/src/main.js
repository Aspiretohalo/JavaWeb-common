import { createApp } from 'vue'
import App from './App.vue'
import Vant from 'vant'
import 'vant/lib/index.css'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { axios } from './axios.js'
import vuex from 'vuex'
import store from './store'

const app = createApp(App)

app.use(ElementPlus)
app.use(Vant)
app.use(router)
app.use(axios)
app.use(vuex)
app.use(store)

app.mount('#app')