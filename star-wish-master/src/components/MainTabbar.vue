<template>
  <div>
    <van-tabbar v-model="active">
      <van-tabbar-item :icon="item.icon" @click="clickTabbar(item)" v-for="item in noChildren" :index="item.name" :key="item.name">
        {{ item.label }}
      </van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
// import { useRoute } from 'vue-router'
// import { ref } from 'vue'

export default {
  name: 'StarWishTabbar',

  // setup() {
  //   // 这段代码用于控制下方标签栏的选中，
  //   // 在刷新和重新出现这个标签栏时与当前路由保持匹配
  //   const route = useRoute()
  //   const routeName = route.path.slice(1)
  //   // console.log(routeName)
  //   let routeIndex = 0
  //   if (routeName == 'treasure') routeIndex = 0
  //   else if (routeName == 'vocabulary') routeIndex = 1
  //   else if (routeName == 'plan') routeIndex = 2
  //   else if (routeName == 'personal') routeIndex = 3
  //   const active = ref(routeIndex)
  //   return { active }
  // },

  data() {
    return {
      menuData: [
        {
          path: '/treasure',
          name: 'Treasure',
          label: '功能',
          icon: 'apps-o',
          index: 0,
        },
        {
          path: '/vocabulary',
          name: 'Vocabulary',
          label: '词汇',
          icon: 'bookmark-o',
          index: 1,
        },
        {
          path: '/plan',
          name: 'Plan',
          label: '规划',
          icon: 'notes-o',
          index: 2,
        },
        {
          path: '/personal',
          name: 'Personal',
          label: '我的',
          icon: 'manager-o',
          index: 3,
        },
      ],
    }
  },
  computed: {
    // 没有子菜单
    noChildren() {
      return this.menuData.filter((item) => !item.children)
    },
  },
  methods: {
    clickTabbar(item) {
      // console.log(item)
      //当页面要跳转的路由与当前不一样
      if (this.$route.path !== item.path && !(this.$route.path === '/main' && item.path === '/')) {
        // 跳转到item.path对应的页面
        this.$router.push(item.path)
      }
    },
  },
}
</script>

<style lang="less" scoped>
</style>