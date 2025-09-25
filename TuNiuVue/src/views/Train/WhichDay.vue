<template>
  <div class="whichDay">
    <el-date-picker
        v-model="value1"
        type="date"
        :disabled-date="disabledDate"
        placeholder="选择出发日期"
        size="default"
    />
  </div>
</template>

<script setup>
import {onMounted, ref, watch} from "vue";

const value1 = ref(new Date())
const emit = defineEmits(['getDay'])
const is = defineProps(['is'])

watch(value1, (newValue) => {
  emit('getDay', newValue)
})

const disabledDate = (time) => {
  //只允许选择当前日期到后6天，且不能选择今天之前的日期
  return time.getTime() > Date.now() + 3600 * 1000 * 24 * 6 || time.getTime() < Date.now() -  3600 * 1000 * 24
}

onMounted(() => {
  value1.value = new Date(is.is)
})
</script>

<style scoped lang="scss">
.whichDay{
  margin: 0 auto;
}
</style>