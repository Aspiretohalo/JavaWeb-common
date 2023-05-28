// import { reactive } from 'vue'
import { createStore } from 'vuex'
import state from './state'
import getters from './getter'
import mutations from './mutations'

const store = createStore({
    state: state,
    getters: getters,
    mutations: mutations,
})
export default store