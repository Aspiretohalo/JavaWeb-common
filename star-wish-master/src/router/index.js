import { createRouter, createWebHashHistory } from "vue-router";
import Main from '../views/Main.vue'
import Login from '../views/Login.vue'
import Treasure from '../views/children/Treasure.vue'
import Vocabulary from '../views/children/Vocabulary.vue'
import Plan from '../views/children/Plan.vue'
import Personal from '../views/children/Personal.vue'
// import Settings from '../views/children/Settings.vue'
import { showFailToast } from 'vant';


// 1.创建路由组件
// 2.每个路由应该映射一个组件
// 3. 创建 router 实例，然后传 `routes` 配置
// 4. 创建和挂载根实例。
const router = createRouter({
    history: createWebHashHistory(),
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            component: Main,
            redirect: '/treasure',
            children: [
                {
                    path: 'treasure',
                    component: Treasure,
                },
                {
                    path: 'vocabulary',
                    component: Vocabulary,
                },
                {
                    path: 'plan',
                    component: Plan,
                },
                {
                    path: 'personal',
                    component: Personal,
                },

            ]
        },
        // {
        //     path: '/settings',
        //     component: Settings,
        // },


        {
            path: '/login',
            component: Login
        },



    ]

})
router.beforeEach(async (to) => {
    // 检查用户是否已登录  // ❗️ 避免无限重定向

    if (!localStorage.getItem('token') && to.path !== '/login') {
        // 将用户重定向到登录页面
        showFailToast('请先登录');
        return { path: '/login' }
    }
})

export default router