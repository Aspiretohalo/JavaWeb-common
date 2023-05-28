<template>
  <div id="box">
    <h3>Welcome</h3>
    <h4>欢迎来到javaweb</h4>
    <el-card class="box-card" :body-style="{ padding: '0px' }">
      <van-form @submit="handleLogin" class="MyForm">
        <van-cell-group inset>
          <van-field v-model="phoneNum" name="phoneNum" label="手机号码" placeholder="手机号码" :rules="[{ required: true }]" />
          <van-field
            v-model="password"
            type="password"
            name="password"
            label="密码"
            placeholder="密码"
            show-error-message="false"
            :rules="[{ required: true }]"
          />
        </van-cell-group>
        <div style="margin: 45px">
          <van-button class="add" round block color="#333" plain native-type="submit" @click="handleLogin"> 登录 </van-button>
        </div>
      </van-form>
    </el-card>
  </div>
</template>

<script>
import { ref } from 'vue'
import axios from 'axios'
import router from '../router'
// import { showLoadingToast } from 'vant'

export default {
  name: 'StarWishLogin',
  methods: {},

  setup() {
    const phoneNum = ref('')
    const password = ref('')
    const handleLogin = (values) => {
      axios
        .post('http://localhost:8085/login', values, {
          headers: {
            // Authorization: 'Bearer ' + localStorage.getItem('token'),
          },
        })
        .then((response) => {
          if (response.data.code == 1) {
            console.log(response)
            localStorage.setItem('token', response.data.data.jwt)
            router.push('/')
          }
        })
        .catch((error) => {
          console.log(error)
        })
    }

    return {
      phoneNum,
      password,
      handleLogin,
    }
  },
}
</script>

<style scoped>
.box-card {
  margin: 100px auto;
  width: 480px;
}
.add {
  border: none;
  background: transparent;
  background-color: rgb(246, 246, 246);
}
.MyForm {
  margin-top: 50px;
}
h3 {
  text-align: center;
  margin-top: 42px;
}
h4 {
  text-align: center;
  margin-top: 24px;
}
</style>