<template>
  <div>
    <el-button type="success" @click="callDialog()">新增</el-button>
    <!-- <el-card class="box-card" v-for="item in introduction" :key="item.item_name">
      <template #header>
        <div class="card-header">
          <span>{{ item.item_name }}</span>
          <el-button class="button" text>{{ item.item_description }}</el-button>
          <el-button type="primary" @click="handleEdit()" plain>编辑</el-button>
          <el-button type="danger" plain>删除</el-button>
        </div>
      </template>
      <div class="text item">
        {{ item.item_detail }}
      </div>
    </el-card> -->
    <el-table :data="introduction" style="width: 100%">
      <el-table-column label="名称" prop="item_name" />
      <el-table-column label="描述" prop="item_description" />
      <el-table-column label="详细内容" prop="item_detail" />
      <el-table-column align="right">
        <template #default="scope">
          <el-button type="primary" @click="handleEdit(scope.row)" plain>编辑</el-button>

          <el-button type="danger" plain @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <el-dialog v-model="dialogFormVisible" :title="action == 'add' ? '新增信息' : '修改信息'">
    <el-form :model="form" label-width="80px">
      <el-form-item label="名称">
        <el-input v-model="form.item_name" />
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="form.item_description" />
      </el-form-item>
      <el-form-item label="详细内容">
        <el-input v-model="form.item_detail" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import axios from 'axios'
import { reactive, ref } from 'vue'

export default {
  name: 'StarWishTreasure',

  setup() {
    const dialogFormVisible = ref(false)
    const form = reactive({
      item_name: '',
      item_description: '',
      item_detail: '',
    })
    // 新增信息
    const callDialog = () => {
      action.value = 'add'
      dialogFormVisible.value = true
    }
    const action = ref('add')
    // 修改信息
    const handleEdit = (row) => {
      console.log(row)

      action.value = 'edit'
      dialogFormVisible.value = true
      // 浅拷贝
      Object.assign(form, row)
    }

    return {
      dialogFormVisible,
      callDialog,
      form,
      handleEdit,
      action,
    }
  },
  data() {
    return {
      introduction: [],
    }
  },

  created() {
    this.getIntroduction()
  },

  methods: {
    getIntroduction() {
      axios
        .get('http://localhost:8085/msg/getIntroduction', {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token'),
          },
        })
        .then((response) => {
          console.log(response)
          let resData = Object.values(response.data)
          this.introduction = resData
        })
        .catch((error) => {
          console.log(error)
        })
    },
  },
}
</script>

<style lang="less" scoped>
.box-card {
  margin-top: 20px;
}
.card-header {
  span {
    font-size: 24px;
  }
}
</style>