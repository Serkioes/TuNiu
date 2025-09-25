<template>
  <div class="passengerInfo">
    <div class="condition">
      <span>旅客姓名</span>
      <el-input v-model="name" placeholder="中文名/英文名" style="width: 200px;margin-left: -10px;height: 25px;margin-top: 4px"/>
      <el-button style="margin-left: 10px;height: 25px;margin-top: 4px" @click="paging(1)">搜索</el-button>
      <el-button style="margin-left: 10px;height: 25px;margin-top: 4px" @click="add">新增</el-button>
    </div>
    <el-table
        ref="multipleTableRef"
        :data="page.list"
        style="width: 100%"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection"/>
      <el-table-column property="name" label="姓名" width="100px"></el-table-column>
      <el-table-column property="phone" label="手机/电话" width="130px" />
      <el-table-column property="cardType" label="证件类型" width="100px" />
      <el-table-column property="idCard" label="证件号码" width="140px"/>
      <el-table-column property="country" label="国籍" width="100px"/>
      <el-table-column property="gender" label="性别" width="90px"/>
      <el-table-column property="often" label="常旅客卡"width="90px" />
      <el-table-column label="操作">
        <template v-slot="scope">
          {{ scope.row.status }}
          <span class="button" @click="update(scope.row)">修改</span>
          <span class="button" @click="del(scope.row.id)">删除</span>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="paging"
        :current-page="page.pageNum"
        :page-size="pageSize"
        layout="prev, pager, next"
        :total="page.total"
    />


    <el-dialog title="用户" v-model="dialogFormVisible" :before-close="clear" >
      <el-form :model="form" :rules="rules" ref="sb">
        <el-form-item style="margin-left: 43px" label="" :label-width="formLabelWidth">
          <el-input v-model="form.userId" auto-complete="off" type="hidden"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 33px" label="姓名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机/电话" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="form.phone" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="证件类型" style="margin-left: 5px" :label-width="formLabelWidth" prop="cardType">
          <el-select v-model="form.cardType" placeholder="请选择证件类型">
            <el-option label="身份证" value="身份证"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="证件号码" style="margin-left: 5px" :label-width="formLabelWidth" prop="idCard">
          <el-input v-model="form.idCard" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="票务类型" style="margin-left: 5px" :label-width="formLabelWidth" prop="type">
          <el-select v-model="form.type" placeholder="请选择证件类型">
            <el-option label="成人票" value="成人票"></el-option>
            <el-option label="学生票" value="学生票"></el-option>
            <el-option label="儿童" value="儿童"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="国籍" style="margin-left: 33px" :label-width="formLabelWidth" prop="country">
          <el-select v-model="form.country" placeholder="请选择国籍">
            <el-option label="中国" value="中国"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="性别" style="margin-left: 33px" :label-width="formLabelWidth" prop="gender">
          <el-radio v-model="form.gender" label="男">男</el-radio>
          <el-radio v-model="form.gender" label="女">女</el-radio>
        </el-form-item>
        <el-form-item label="常旅客卡" style="margin-left: 5px" :label-width="formLabelWidth" prop="often">
          <el-radio v-model="form.often" label="有">有</el-radio>
          <el-radio v-model="form.often" label="无">无</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="clear">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script setup>
import {onMounted, ref} from "vue"
import axios from "axios";
import router from "@/router/index.js";

const name = ref('')
const pageSize = ref(10)
const page = ref({})
const form = ref({
  userId: JSON.parse(sessionStorage.getItem('userId'))
})
const sb = ref()
const dialogFormVisible = ref(false)
const paging = (curPage) => {
  axios({
    method:'get',
    url:'passenger/list',
    params:{
      userId: JSON.parse(sessionStorage.getItem('userId')),
      name:name.value,
      pageNum:curPage,
      pageSize:pageSize.value
    }
  }).then(resp=>{
    page.value=resp.data
  })
}
const handleSizeChange = (ps) => {
  pageSize.value=ps
  paging(1)
}
onMounted(()=>{
  paging(1)
})

const add = () => {
  if (sessionStorage.getItem('userId') === null){
    alert('登录已过期，请重新登录')
    router.push('/login')
  }
  dialogFormVisible.value=true
}
const clear = () => {
  dialogFormVisible.value=false
  form.value={}
  paging(1)
}
const save = () => {
  sb.value.validate(valid=>{
    if (valid){//所有分项的验证都通过
      axios.post('passenger/save',form.value).then(res=>{
        if (res.data.code === 200) {
          alert(res.data.msg)
          clear()
          paging(1)
        }else {
          alert(res.data.msg)
        }
      })
    }
  })
}
const update = (row) => {
  form.value=row
  dialogFormVisible.value=true
}
const del = (id) => {
  if (confirm('你确认删除吗?')){
    axios.post('passenger/del?id='+id).then(resp=>{
      alert(resp.data)
      clear()
    })
  }
}

const rules = ref({
  phone: [
    {required: true, message: '请输入手机号码', trigger: 'blur'},
    {min: 11, max: 11, message: '手机号码长度不正确', trigger: 'blur'},
    {pattern: /^1[3456789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur'},
    {validator:(rule,value,callback)=>{
        if (value.indexOf(' ')>-1){
          callback(new Error('手机号不能包含空格'))
        }else{
              callback()  //验证通过
        }
      }
    }
  ],
  name: [
    {required: true, message: '请输入姓名', trigger: 'blur'},
    {validator:(rule,value,callback)=>{
        if (value.indexOf(' ')>-1){
          callback(new Error('姓名不能包含空格'))
        }else{
          callback()  //验证通过
        }
      }
    }],
  idCard: [
    {required: true, message: '请输入身份证号', trigger: 'blur'},
    {validator:(rule,value,callback)=>{
        if (value.indexOf(' ')>-1){
          callback(new Error('身份证号不能包含空格'))
        }else{
          callback()  //验证通过
        }
      }
    }],
  type: [
    {required: true, message: '请选择票务类型', trigger: 'blur'},],
  gender: [
    {required: true, message: '请选择性别', trigger: 'blur'},],
  often: [
    {required: true, message: '请选择常旅客卡', trigger: 'blur'},],
  country: [
    {required: true, message: '请选择国籍', trigger: 'blur'},],
  cardType: [
    {required: true, message: '请选择证件类型', trigger: 'blur'},],
})

</script>

<style scoped lang="scss">
.passengerInfo{
  width: 100%;
  .condition{
    display: flex;
    margin: 1px 0;
    span{
      line-height: 31px;
      margin-right: 20px;
    }
  }
  .button{
    font-size: 12px;
    margin-right: 12px;
    cursor: pointer;
    &:hover{
      color: #409EFF;
    }
  }
}
</style>