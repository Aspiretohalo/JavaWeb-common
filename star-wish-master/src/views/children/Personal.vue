<template>
  <div>
    <el-button type="primary" @click="logout">退出登录</el-button>
    <el-table :data="Msg" style="width: 100%">
      <el-table-column label="姓名" prop="item_name" />
      <el-table-column label="简介" prop="item_description" />
      <el-table-column label="掌握技能" prop="item_detail" />
      <el-table-column align="right">
        <template #default="scope">
          <el-button type="primary" @click="handleEdit(scope.row)" plain>编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <img class="resume" src="../../assets/resume.jpg" alt="" />
    <el-dialog v-model="dialogFormVisible" title="修改信息">
      <el-form :model="form" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.item_name" />
        </el-form-item>
        <el-form-item label="简介">
          <el-input v-model="form.item_description" />
        </el-form-item>
        <el-form-item label="掌握技能">
          <el-input v-model="form.item_detail" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSubmit()"> 确认 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { showConfirmDialog } from 'vant'
import axios from 'axios'
import { reactive, ref } from 'vue'
export default {
  name: 'StarWishPersonal',

  setup() {
    const dialogFormVisible = ref(false)
    const form = reactive({
      item_index: 0,
      item_name: '姓名',
      item_description: '简介',
      item_detail: '掌握技能',
      phoneNum: '',
    })
    const handleEdit = (row) => {
      console.log(row)

      dialogFormVisible.value = true
      // 浅拷贝
      Object.assign(form, row)
    }
    const alterMsg = (obj) => {
      console.log(obj)
      axios
        .put('http://localhost:8085/msg/alterMsg', JSON.stringify(obj), {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token'),
            'Content-Type': 'application/json',
          },
        })
        .then((res) => {
          console.log(res)
        })
        .catch((error) => {
          console.log(error)
        })
    }
    const phone = ref('')
    const handleSubmit = () => {
      dialogFormVisible.value = false
      console.log(form)
      let obj = {
        item_index: form.item_index,
        item_name: form.item_name,
        item_description: form.item_description,
        item_detail: form.item_detail,
        phoneNum: form.phoneNum,
      }
      console.log(form.item_index)
      alterMsg(obj)
    }

    return {
      handleEdit,
      dialogFormVisible,
      form,
      handleSubmit,
      phone,
      alterMsg,
    }
  },
  data() {
    return {
      Msg: [],
    }
  },
  created() {
    this.getMsg()
  },

  methods: {
    getMsg() {
      axios
        .get('http://localhost:8085/msg/getMsg', {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token'),
          },
        })
        .then((response) => {
          console.log(response)
          let resData = Object.values(response.data)
          this.Msg = resData
          this.phone = resData[0].phoneNum
        })
        .catch((error) => {
          console.log(error)
        })
    },

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
.custom-title {
  margin-left: 10px;
  vertical-align: middle;
}
.personal-icon {
  vertical-align: middle;
}
.resume {
  margin-left: 250px;
  margin-top: 20px;
}
</style>