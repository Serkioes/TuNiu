<template>
  <div class="returnDay">
    <el-date-picker
        v-model="value1"
        type="date"
        :disabled-date="disabledDate"
        placeholder="选择返程日期"
        size="default"
        style="width: 160px"
    />
  </div>
</template>

<script setup>
import {onMounted, ref, watch} from "vue";

const value1 = ref('')
const emit = defineEmits(['getReturn'])
const is = defineProps(['is'])

watch(value1, (newVal) => {
  emit('getReturn', newVal)
})

const disabledDate = (time) => {
  return time.getTime() > Date.now() + 3600 * 1000 * 24 * 14 || time.getTime() < Date.now() - 3600 * 1000 * 24
}

onMounted(() => {
  value1.value = new Date(is.is)
})
</script>

<style scoped lang="scss">
.returnDay{
  margin: 0 auto;
}
</style>