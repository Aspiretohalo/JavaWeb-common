<template>
  <div>
    <van-cell-group inset>
      <van-cell is-link to="settings">
        <!-- 使用 title 插槽来自定义标题 -->
        <template #title>
          <van-icon name="setting-o" size="20" class="personal-icon" />
          <span class="custom-title">设置</span>
        </template>
      </van-cell>
    </van-cell-group>
  </div>
</template>

<script>
export default {
  name: 'StarWishPersonal',

  data() {
    return {}
  },

  mounted() {},

  methods: {},
}
</script>

<style  scoped>
.custom-title {
  margin-left: 10px;
  vertical-align: middle;
}
.personal-icon {
  vertical-align: middle;
}
</style>