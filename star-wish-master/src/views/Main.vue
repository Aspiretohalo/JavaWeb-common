<template>
  <div>
    <el-container>
      <el-header class="header">
        <div class="logo">
          <img src="../assets/logo.png" alt="" />
        </div>
        <div class="web">
          <img src="../assets/web.png" alt="" />
        </div>
        <div class="avatar">
          <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
        </div>
      </el-header>
      <el-container class="bottom">
        <el-aside width="300px">
          <el-menu default-active="0" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
            <el-menu-item @click="clickTabbar(item)" v-for="item in noChildren" :index="item.name" :key="item.name">
              <span>{{ item.label }}</span>
            </el-menu-item>
            <!-- <el-menu-item index="1"> <span>家乡特产</span> </el-menu-item>
            <el-menu-item index="2"> <span>家乡景点</span> </el-menu-item>
            <el-menu-item index="3"> <span>我的信息</span> </el-menu-item> -->
          </el-menu>
        </el-aside>
        <el-container>
          <el-main>
            <router-view></router-view>
          </el-main>
          <el-footer>
            <div class="bottomFrame">
              <div class="bottomCenter">
                <ul>
                  <li>中国计量大学信息工程学院计算机科学与技术专业</li>
                  <li>2100303107-曹扬-21计算机1</li>
                  <li>JavaWeb期末大作业</li>
                </ul>
              </div>
            </div></el-footer
          >
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { useRoute } from 'vue-router'
import { ref } from 'vue'
export default {
  setup() {
    // 这段代码用于控制下方标签栏的选中，
    // 在刷新和重新出现这个标签栏时与当前路由保持匹配
    const route = useRoute()
    const routeName = route.path.slice(1)
    let routeIndex = 0
    if (routeName == 'treasure') routeIndex = 0
    else if (routeName == 'vocabulary') routeIndex = 1
    else if (routeName == 'plan') routeIndex = 2
    else if (routeName == 'personal') routeIndex = 3
    const active = ref(routeIndex)
    return { active }
  },
  name: 'StarWishMain',

  data() {
    return {
      menuData: [
        {
          path: '/treasure',
          name: 'Treasure',
          label: '家乡介绍',
          icon: 'apps-o',
          index: 0,
        },
        {
          path: '/vocabulary',
          name: 'Vocabulary',
          label: '家乡特产',
          icon: 'bookmark-o',
          index: 1,
        },
        {
          path: '/plan',
          name: 'Plan',
          label: '家乡景点',
          icon: 'notes-o',
          index: 2,
        },
        {
          path: '/personal',
          name: 'Personal',
          label: '我的资料',
          icon: 'manager-o',
          index: 3,
        },
      ],
    }
  },

  components: {},
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
.header {
  height: 80px;
  background-color: #ffffff;
  .logo {
    display: inline-block;
  }
  .web {
    display: inline-block;
    vertical-align: top;
    margin-top: 20px;
    margin-left: 20px;
  }
  .avatar {
    display: inline-block;
    float: right;
    margin-top: 20px;
    margin-right: 50px;
  }
}

.bottom {
  background-color: #fff;
  height: 1000px;
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 300px;
    min-height: 400px;
  }
  .el-menu-vertical-demo {
    height: 100vh;
    vertical-align: center;

    span {
      margin: 0 auto;
      font-size: large;
    }
  }
}
.bottomCenter {
  margin: 30px auto;
  text-align: center;
}
</style>