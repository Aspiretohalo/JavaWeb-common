<template>
  <div>
    <el-button type="success" @click="callDialog()">新增</el-button>
    <el-table :data="specialty" style="width: 100%">
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
          <el-button type="primary" @click="handleSubmit()"> 确认 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'
import { reactive, ref } from 'vue'
export default {
  name: 'StarWishVocabulary',
  setup() {
    const dialogFormVisible = ref(false)
    const form = reactive({
      item_index: 0,
      item_name: '',
      item_description: '',
      item_detail: '',
      phoneNum: '',
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
    const alterSpecialty = (obj) => {
      console.log(obj)
      axios
        .put('http://localhost:8085/msg/alterSpecialty', JSON.stringify(obj), {
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
    const setSpecialty = (obj) => {
      axios
        .post('http://localhost:8085/msg/setSpecialty', JSON.stringify(obj), {
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
      if (action.value == 'add') {
        console.log(form)
        console.log(phone.value)

        let obj = {
          item_index: form.item_index,
          item_name: form.item_name,
          item_description: form.item_description,
          item_detail: form.item_detail,
          phoneNum: phone.value,
        }
        setSpecialty(obj)
      } else {
        console.log(form)
        let obj = {
          item_index: form.item_index,
          item_name: form.item_name,
          item_description: form.item_description,
          item_detail: form.item_detail,
          phoneNum: form.phoneNum,
        }
        console.log(form.item_index)
        alterSpecialty(obj)
      }
    }

    const deleteSpecialty = (item_index) => {
      axios
        .delete('http://localhost:8085/msg/deleteSpecialty/' + item_index, {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token'),
            'Content-Type': 'application/json',
          },
        })
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {
          console.log(error)
        })
    }
    const handleDelete = (row) => {
      console.log(row.item_name)
      deleteSpecialty(row.item_index)
    }
    return {
      dialogFormVisible,
      callDialog,
      form,
      handleEdit,
      action,
      handleSubmit,
      alterSpecialty,
      phone,
      deleteSpecialty,
      handleDelete,
    }
  },
  data() {
    return {
      specialty: [
        // {
        //   name: '金华火腿',
        //   detail: '金华火腿色泽鲜艳，红白分明，瘦肉香咸带甜，肥肉香而<br>不腻，美味可口；内含丰富的蛋白质和脂肪，多种维生素和矿物质',
        // },
        // {
        //   name: '金华酥饼',
        //   detail:
        //     '香松的酥饼，味道极佳；入口酥碎，遇湿消融，即使牙齿脱尽的人也有口福品尝其味；酥饼还以浓烈的陈香和鲜咸的回味显示其特有的魅力，强烈地吸引着顾客；明代已闻名于世、民间更有李白“闻香下马”的传说。',
        // },
        // {
        //   name: '金华佛手',
        //   detail:
        //     '金华种植佛手历史悠久已近千年。金华市土质含微酸性沙壤土，土质疏松、肥沃，非常适应金华佛手的种植；属亚热带季风气候。四季分明，年温适中，热量较优，雨量丰富，日照热量资源丰富。金华佛手果实似花非花，似果非果',
        // },
      ],
    }
  },

  created() {
    this.getSpecialty()
  },

  methods: {
    getSpecialty() {
      axios
        .get('http://localhost:8085/msg/getSpecialty', {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token'),
          },
        })
        .then((response) => {
          console.log(response)
          let resData = Object.values(response.data)
          this.specialty = resData
          this.phone = resData[0].phoneNum
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