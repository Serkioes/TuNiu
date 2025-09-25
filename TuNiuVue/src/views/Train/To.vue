<template>
  <div class="to">
    <el-select v-model="value" placeholder="出发城市" style="width: 120px">
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="热门选择" name="first">
          <el-option v-for="item in cities" :key="item.value" :label="item.name" :value="item.id"></el-option>
        </el-tab-pane>
        <el-tab-pane label="ABDC" name="second">
          <el-option>
            暂未实现
          </el-option>
        </el-tab-pane>
        <el-tab-pane label="EFGH" name="third">
          <el-option>
            暂未实现
          </el-option>
        </el-tab-pane>
        <el-tab-pane label="JKLM" name="fourth">
          <el-option>
            暂未实现
          </el-option>
        </el-tab-pane>
        <el-tab-pane label="NPQRS" name="fifth">
          <el-option>
            暂未实现
          </el-option>
        </el-tab-pane>
        <el-tab-pane label="TWX" name="sixth">
          <el-option>
            暂未实现
          </el-option>
        </el-tab-pane>
        <el-tab-pane label="YZ" name="seventh">
          <el-option>
            暂未实现
          </el-option>
        </el-tab-pane>
      </el-tabs>
    </el-select>
  </div>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue'
import axios from "axios";
const activeName = ref('first')

const value = ref('')
const cities = ref([])
const emit = defineEmits(['getTo'])
const is = defineProps(['is'])

watch(value, (newVal) => {
  emit('getTo', newVal)
})

const getCity = async () =>{
  return await axios.get('/city/list')
}

onMounted(async ()=>{
  const res = await getCity()
  cities.value = res.data
  value.value = cities.value.find(item => is.is == item.id).name
})

</script>

<style scoped lang="scss">
.to{
  margin: 0 auto;
}
</style>