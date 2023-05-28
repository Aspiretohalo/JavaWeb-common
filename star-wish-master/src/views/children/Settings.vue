<template>
  <div>
    <van-nav-bar title="设置" safe-area-inset-top left-text="返回" left-arrow @click-left="onClickLeft" fixed placeholder />
    <van-cell title="退出登录" is-link @click="logout" />
  </div>
</template>

<script>
import { showConfirmDialog } from 'vant'
export default {
  name: 'StarWishSettings',

  setup() {
    const onClickLeft = () => history.back()
    return {
      onClickLeft,
    }
  },

  methods: {
    logout() {
      showConfirmDialog({
        title: '退出登录',
        message: '确定退出登录？',
        width: '250',
      })
        .then(() => {
          localStorage.removeItem('token')
          this.$router.push('login')
        })
        .catch(() => {
          // on cancel
        })
    },
  },
}
</script>

<style  scoped>
</style>