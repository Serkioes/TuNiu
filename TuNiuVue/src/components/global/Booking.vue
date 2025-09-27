<template>
  <div class="booking">
    <div class="title">
      <img src="@/assets/tuniu.png" style="width: 280px; height: 100px;margin-left: -10px; cursor: pointer;" @click="router.push('/')">
      <el-steps
          style="max-width: 600px"
          :space="200"
          :active="0"
          finish-status="success"
          class="step"
      >
        <el-step title="填写" />
        <el-step title="支付" />
        <el-step title="完成" />
      </el-steps>
      <div class="other">
        <span>
          <svg @click="stupid" t="1725440610455" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6219" width="19" height="19"><path d="M512 215.253333a240.341333 240.341333 0 0 1 165.866667-65.792V149.333333c61.290667 0 122.56 23.082667 169.322666 69.248 46.762667 46.165333 70.144 106.666667 70.144 167.146667s-23.381333 120.981333-70.144 167.146667l-174.848 172.629333-138.794666 137.024a30.656 30.656 0 0 1-43.093334 0L176.832 552.874667A234.069333 234.069333 0 0 1 106.666667 385.706667c0-60.501333 23.381333-121.002667 70.144-167.146667a240.256 240.256 0 0 1 169.301333-69.12V149.333333C405.909333 149.333333 465.706667 171.306667 512 215.253333z m35.050667 121.002667a30.506667 30.506667 0 0 1 21.568-8.96 30.784 30.784 0 0 1 21.653333 8.832 30.08 30.08 0 0 1 8.917333 21.354667 29.738667 29.738667 0 0 1-9.066666 21.312l-138.794667 137.002666a30.677333 30.677333 0 0 1-42.794667-0.277333 29.866667 29.866667 0 0 1-0.277333-42.24l138.794667-137.024z m69.418666 68.522667a30.506667 30.506667 0 0 1 21.653334-9.216 30.805333 30.805333 0 0 1 21.824 8.810666 30.037333 30.037333 0 0 1 8.917333 21.546667 29.717333 29.717333 0 0 1-9.344 21.397333l-184.469333 182.101334a30.656 30.656 0 0 1-42.858667-0.213334 29.866667 29.866667 0 0 1-0.213333-42.304l184.469333-182.122666z m69.397334 68.522666a30.656 30.656 0 0 1 42.858666 0.213334 29.866667 29.866667 0 0 1 0.213334 42.304l-138.794667 137.024a30.656 30.656 0 0 1-42.837333-0.213334 29.866667 29.866667 0 0 1-0.213334-42.304l138.773334-137.024z m-152.32-194.282666l-90.282667 89.066666a30.506667 30.506667 0 0 1-21.610667 9.024 30.826667 30.826667 0 0 1-21.696-8.789333 30.08 30.08 0 0 1-8.917333-21.418667 29.738667 29.738667 0 0 1 9.152-21.333333l68.693333-67.818667a179.2 179.2 0 0 0-122.752-48.256v-0.128c-45.632 0-91.306667 17.237333-126.250666 51.754667a174.592 174.592 0 0 0 0 249.237333L512 798.741333l117.269333-115.754666 174.848-172.629334a174.570667 174.570667 0 0 0 52.288-124.629333 174.506667 174.506667 0 0 0-52.266666-124.608 179.178667 179.178667 0 0 0-126.272-51.626667v-0.128c-45.610667 0-91.306667 17.237333-126.229334 51.754667l-18.112 17.856v0.021333z" p-id="6220"></path></svg>
          网站无障碍
        </span>
        <span>客服电话:95010</span>
        <span>客服</span>
        <span>预订须知</span>
      </div>

    </div>
    <div class="content">
      <div class="left">
        <div class="userName item">
          {{name}}
        </div>
        <div class="passengerInfo item">
          <h1 style="font-weight: 700;font-size: 22px;margin-top: 20px">乘客信息</h1>
          <div class="topic" style="margin-top: 6px">
            <span>请选择乘客</span>
            <span>
              所有乘客
              <el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
            </span>
          </div>
          <div class="passenger">
            <el-checkbox-group
                v-model="checkedPassengers"
                @change="handleCheckedCitiesChange"
            >
              <el-checkbox v-for="item in passengers" :key="item.id" :label="item.id" :value="item.id">
                {{ item.name }}
              </el-checkbox>
            </el-checkbox-group>
          </div>
        </div>
        <div class="tips item">
          <p>立即预订产品说明: 30元/人,包含国内租车券、线上极速退改签、7*24小时预订+客服服务。</p>
          <p>请您在提交订单前仔细阅读 火车票服务协议，火车票预订须知</p>
          <p>由上海顺途科技有限公司提供服务，查看 营业执照</p>
        </div>
        <div class="button item" @click="reserve">
          预定
        </div>
      </div>
      <div class="right">
        <baidu-map class="map" :center="{lng: 116.404, lat: 39.915}" :zoom="5" @ready="handler">
          <bm-marker :position="start" :dragging="true"></bm-marker>
          <bm-marker :position="end" :dragging="true"></bm-marker>
        </baidu-map>
      </div>
    </div>
  </div>
</template>

<script setup>

import router from "@/router/index.js";
import {computed, onMounted, ref, watch} from "vue";
import {useRoute} from "vue-router";
import axios from "axios";


const route = useRoute();
const ticketInfo = ref(JSON.parse(route.query.ticket))
const passengers = ref([])



const getPassengers = async () => {
  return await axios.get('/passenger/list?userId=' + sessionStorage.getItem("userId") + '&pageSize=1000')
}

onMounted(async () => {
  const res = await getPassengers()
  passengers.value = res.data.list
})

const checkedPassengers = ref([])


const name = computed(()=>{
  return sessionStorage.getItem("userName")
})

// const a = ref({name:'上海',lng: "116.404", lat: "39.915"})

const a = computed(async ()=>{
  const res = await axios.get("/city/which?name=" + ticketInfo.value.startStation)
  const position1 = {lng: res.data.lng, lat: res.data.lat}

  return position1
})
const start = ref({})

// const b = ref({name:'上海',lng: 120.52, lat: 30.40})

const b = computed(async ()=>{
  const res = await axios.get("/city/which?name=" + ticketInfo.value.endStation)
  const position2 = {lng: res.data.lng, lat: res.data.lat}

  return position2
})

const end = ref({})

const handler = ({BMap, map}) => {
  a.value.then(res=>{start.value = res})
  b.value.then(res=>{end.value = res})
}

const reserve = async () => {
  let params = new URLSearchParams();
  const tickets = JSON.parse(route.query.ticket)
  params.append("userId", sessionStorage.getItem("userId"))
  params.append("passengers", JSON.stringify(checkedPassengers.value))
  params.append("id", ticketInfo.value.id)
  params.append("price", JSON.stringify(ticketInfo.value.price * checkedPassengers.value.length))
  const res = await axios.post("/orders/save", params)
  alert(res.data)
  router.push("/order/train")
}

</script>

<style scoped lang="scss">
.booking{
  width: 100%;
  height: 100%;
  .title{
    width: 1120px;
    height: 100px;
    margin: 0 auto;
    //background-color: red;
    display: flex;
    align-items: center;
    justify-content: space-between;
    .step{
      width: 400px;
      margin-top: 15px;
    }
  }
  .other{
    width: 350px;
    color: rgb(153,153,153);
    font-size: 14px;
    font-weight: 700;
    display: flex;
    justify-content: space-between;
    .icon{
      vertical-align: middle;
      margin-top: -2px;
    }
  }
  .content{
    width: 1120px;
    height: 100%;
    margin: 0 auto;
    //background-color: red;
    display: flex;
    justify-content: space-between;
    .left{
      width: 740px;
      height: 100%;
      //background-color: rgb(242,242,242);
      display: flex;
      flex-direction: column;
      .item{
        margin-bottom: 10px;
      }
      .userName{
        width: 100%;
        height: 60px;
        background-color: rgb(242,242,242);
        border-radius: 10px;
        display: flex;
        align-items: center;
        font-weight: 700;
        font-size: 14px;
        padding-left: 20px;
      }
      .passengerInfo{
        width: 100%;
        height: 140px;
        background-color: rgb(242,242,242);
        padding-left: 20px;
        border-radius: 10px;
        .topic{
          display: flex;
          justify-content: space-between;
          font-weight: 700;
        }
      }
      .tips{
        width: 100%;
        height: 70px;
        background-color: rgb(242,242,242);
        border-radius: 10px;
        //padding-left: 20px;
        font-size: 12px;
        color: rgb(153,153,153);
        p{
          margin-top: 5px;
        }
      }
      .button {
        width: 100%;
        height: 50px;
        background-color: orange;
        border-radius: 10px;
        padding-left: 20px;
        text-align: center;
        line-height: 50px;
        font-size: 30px;
        color: rgb(255,255,255);
        font-weight: 700;
        cursor: pointer;
        &:hover{
          background-color: green;
        }
      }
    }
    .right{
      width: 350px;
      height: 350px;
      //background-color: red;
      border-radius: 10px;
      .map {
        width: 100%;
        height: 350px;
      }
    }
  }
}
</style>