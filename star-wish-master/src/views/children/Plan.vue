<template>
  <div>
    <el-button type="success" @click="callDialog()">新增</el-button>
    <el-table :data="scenery" style="width: 100%">
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
  name: 'StarWishPlan',
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
    const alterScenery = (obj) => {
      console.log(obj)
      axios
        .put('http://localhost:8085/msg/alterScenery', JSON.stringify(obj), {
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
    const setScenery = (obj) => {
      axios
        .post('http://localhost:8085/msg/setScenery', JSON.stringify(obj), {
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
        setScenery(obj)
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
        alterScenery(obj)
      }
    }

    const deleteScenery = (item_index) => {
      axios
        .delete('http://localhost:8085/msg/deleteScenery/' + item_index, {
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
      deleteScenery(row.item_index)
    }
    return {
      dialogFormVisible,
      callDialog,
      form,
      handleEdit,
      action,
      handleSubmit,
      alterScenery,
      phone,
      deleteScenery,
      handleDelete,
    }
  },
  data() {
    return {
      scenery: [
        // {
        //   name: '双龙洞',
        //   description: '双龙洞是国家重点风景名胜区，位于浙江省金华市北郊的金华山西南山麓。',
        //   detail:
        //     '金华双龙洞距金华市区约8公里，坐落在海拔350-450多米的北山南坡，除底层的双龙洞之外，还有中层的冰壶洞和最高的朝真洞。双龙洞分内、外两洞，内洞与外洞仅相隔5米，有一块巨大石屏相隔',
        // },
        // {
        //   name: '尖峰山',
        //   description: '尖峰山又称芙蓉峰，屹立于金华城北，海拔427米，是登临俯瞰金华城市的最佳点之一。',
        //   detail:
        //     '尖峰山又称芙蓉峰，屹立于金华城北，海拔427米，是登临俯瞰金华城市的最佳点之一，登顶需爬一千八百多级台阶，登山者络绎不绝，它是金华双龙风景名胜区的主要景区之一，且免费，自古为金华人的一种心理凝聚和精神象征',
        // },
        // {
        //   name: '万佛塔',
        //   description: ' 万佛塔，老金华城地标性建筑，始建于北宋嘉佑七年（1062）至治平元年（1064）之间。',
        //   detail:
        //     '万佛塔，老金华城地标性建筑，始建于北宋嘉佑七年（1062）至治平元年（1064）之间。原址位于浙江省金华市区塔下寺的山坡上，九层八角楼阁式砖木结构建筑，由佛僧居政倡导建成，初名叫“密印寺塔”。',
        // },
      ],
    }
  },

  created() {
    this.getScenery()
  },

  methods: {
    getScenery() {
      axios
        .get('http://localhost:8085/msg/getScenery', {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token'),
          },
        })
        .then((response) => {
          console.log(response)
          let resData = Object.values(response.data)
          this.scenery = resData
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