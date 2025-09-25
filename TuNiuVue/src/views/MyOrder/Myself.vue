<template>
  <div class="myself">
    <el-descriptions
        title="我的信息"
        direction="vertical"
        border
        style="margin-top: 15px"
    >
      <el-descriptions-item
          :rowspan="2"
          :width="140"
          label="头像"
          align="center"
      >
        <el-image
            style="width: 100px; height: 100px"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        />
      </el-descriptions-item>
      <el-descriptions-item label="昵称">{{name}}</el-descriptions-item>
      <el-descriptions-item label="账号">{{account}}</el-descriptions-item>
    </el-descriptions>

  </div>
</template>

<script setup>
import {computed, onMounted, ref} from "vue";
import axios from "axios";
import {ElMessageBox} from "element-plus";

const name = computed(() => {
  return sessionStorage.getItem('userName')
})

const account = ref('')
const direction = ref('rtl')

const getAccount = async () => {
  const res = await axios.get('http://localhost:8080/user/getAccount?id=' + sessionStorage.getItem('userId'))
  return res.data
}

onMounted(async () => {
  account.value = await getAccount()
})

</script>

<style scoped lang="scss">

</style>