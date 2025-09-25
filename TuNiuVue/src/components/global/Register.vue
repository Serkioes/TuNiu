<template>
  <div class="login">
    <el-dialog v-model="dialogFormVisible" title="注册" width="500" :show-close="false" :align-center="true" :close-on-click-modal="false">
      <el-form :model="form" :rules="rules" ref="sb">
        <el-form-item label="请输入昵称" prop="name">
          <el-input v-model="form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="请输入密码" prop="password">
          <el-input type="password" v-model="form.password" autocomplete="off" />
        </el-form-item>
        <el-form-item label="请确认密码" prop="judgePassword">
          <el-input type="password" v-model="form.judgePassword" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="flag = !flag">取消</el-button>
          <el-button type="primary" @click="register">
            注册
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {ref, watch} from "vue";
import axios from "axios";
import router from "@/router/index.js";


const form = ref({})
const flag = ref(false)
const dialogFormVisible = ref(false)
const eyes = defineProps(['is'])
const emit = defineEmits(['registerVisible'])
const sb = ref()


watch(eyes, (newVal) => {
  dialogFormVisible.value = true
})

watch(flag, (newVal, oldVal) => {
  dialogFormVisible.value = false
  emit('registerVisible', false)
})


const rules = ref({
  name: [
    { required: true, message: '请输入昵称', trigger: 'blur' },
    { min: 1, max: 3, message: '请输入1-3位有效长度', trigger: 'blur' },
    {validator:(rule,value,callback)=>{
        if (value.indexOf(' ')>-1){
          callback(new Error('昵称不能包含空格'))
        }else{
          //验证昵称是否存在
          axios.get('user/checkName?name='+value).then((res)=>{
            if (res.data){
              callback()  //昵称没有被占用，就可以放行
            }else{
              callback(new Error('昵称已存在'))  //昵称被占用，不能放行
            }
          })
        }
      }
    }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' },
    {validator:(rule,value,callback)=>{
        if (value.indexOf(' ')>-1){
          callback(new Error('密码不能包含空格'))
        }else{
          callback()  //符合要求，放行
        }
      }
    }
  ],
  judgePassword: [
    { required: true, message: '请再次输入密码', trigger: 'blur' },
    { min: 3, max: 10, message: '请输入正确的密码', trigger: 'blur' },
    {validator:(rule,value,callback)=>{
        if (value.indexOf(' ')>-1){
          callback(new Error('密码不能包含空格'))
        }else if (value !== form.value.password){
          callback(new Error('两次输入的密码不一致'))
        } else{
          callback()  //验证通过
        }
      }
    }
  ]
})

const register = () => {
  sb.value.validate((valid) => {
    if (valid) {  //表单验证通过
      axios.post('/user/register', form.value).then(res => {
        if (res.data.code === 200) {
          alert(res.data.msg)
          flag.value = !flag.value
          router.go(0)
        } else{
          alert(res.data.msg)
        }
      })
    }
  });
  router.push('/')
}
</script>

<style scoped lang="scss">

</style>