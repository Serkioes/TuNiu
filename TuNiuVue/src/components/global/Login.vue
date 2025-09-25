<template>
  <div class="login">
    <el-dialog v-model="dialogFormVisible" title="登录" width="500" :show-close="false" :align-center="true" :close-on-click-modal="false">
      <el-form :model="form" :rules="rules" ref="sb">
        <el-form-item label="账号" prop="account">
          <el-input v-model="form.account" autocomplete="off" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <span class="noAccount" @click="register">还没有账号？立即注册</span>
          <el-button @click="flag = !flag">取消</el-button>
          <el-button type="primary" @click="login">
            登录
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {computed, onMounted, ref, watch} from "vue";
import {useRouter} from "vue-router"
import axios from "axios";

const router = useRouter()

const emit = defineEmits(['visible','register'])

const flag = ref(false)

const open =  defineProps(['is'])

const form = ref({})

const sb = ref()

const dialogFormVisible = ref(false)

// const dialogFormVisible = computed(() => {
//   return open.is
// })

watch(open, (newVal, oldVal) => {
  dialogFormVisible.value = newVal.is
})

watch(flag, (newVal, oldVal) => {
  dialogFormVisible.value = false
  emit('visible', false)
})

const rules = ref({
  account: [
    { required: true, message: '请输入账号', trigger: 'blur' },
    {validator:(rule,value,callback)=>{
        if (value.indexOf(' ')>-1){
          callback(new Error('账号不能包含空格'))
        }else{
          //验证用户名是否存在
          axios.get('user/checkAccount?account='+value).then((res)=>{
            if (res.data){
              callback()  //验证通过
            }else{
              callback(new Error('账号不存在'))  //验证不通过
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
          //验证用户名是否存在
          axios.get('user/checkPassword?password='+value+'&account='+form.value.account).then((res)=>{
            if (res.data){
              callback()  //验证通过
            }else{
              callback(new Error('密码错误'))  //验证不通过
            }
          })
        }
      }}
  ],
})

const login = () => {
  sb.value.validate(valid=>{
    if (valid){//所有分项的验证都通过
      axios.post('user/login',form.value).then(res=>{
        if (res.data.code === 200) {
          flag.value = false
          sessionStorage.setItem("token", res.data.data.token)
          sessionStorage.setItem("userId", res.data.data.userId)
          sessionStorage.setItem("userName", res.data.data.name)
          alert(res.data.msg)
          router.go(0)
        }else {
          alert(res.data.msg)
        }
      })
    }
  })
  router.push('/')
}

const register = () => {
  flag.value = !flag.value   //自动关闭登录框
  emit('register', true)   //触发父组件中的注册框弹出
}

</script>

<style scoped lang="scss">
.login{
  .noAccount{
    position: relative;
    left: -200px;
    font-size: 13px;
    cursor: pointer;
    &:hover{
      color: #409eff;
    }
  }
}
</style>