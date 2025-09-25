<template>
  <div class="train">
    <div class="detail">
      <div class="tip">
        <svg t="1725519466767" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4391" width="32" height="32"><path d="M512 512m-307.2 0a307.2 307.2 0 1 0 614.4 0 307.2 307.2 0 1 0-614.4 0Z" fill="#CCDAFF" p-id="4392"></path><path d="M512 627.2m-23.04 0a23.04 23.04 0 1 0 46.08 0 23.04 23.04 0 1 0-46.08 0Z" fill="#7A7AF9" p-id="4393"></path><path d="M535.04 535.04c0 12.8-10.24 23.04-23.04 23.04s-23.04-10.24-23.04-23.04v-138.24c0-12.8 10.24-23.04 23.04-23.04s23.04 10.24 23.04 23.04v138.24z" fill="#7A7AF9" p-id="4394"></path></svg>
        <span style="font-size: 12px;">您可以在线查看近30天的订单</span>
      </div>
      <div class="tab">
        <el-tabs type="border-card" v-model="activeName">
          <el-tab-pane name="" label="全部订单">
            <div class="nothing" v-if="orders.length===0">
              暂时没有相关订单
            </div>
            <div class="nothing" v-else>
              内容如下
            </div>
          </el-tab-pane>
          <el-tab-pane name="未出行" label="未出行">
            <div class="nothing" v-if="orders.length===0">暂时没有相关订单</div>
            <div class="nothing" v-else>
              内容如下
            </div>
          </el-tab-pane>
          <el-tab-pane name="待支付" label="待支付">
            <div class="nothing" v-if="orders.length===0">暂时没有相关订单</div>
            <div class="nothing" v-else>
              内容如下
            </div>
          </el-tab-pane>
          <el-tab-pane name="待点评" label="待点评">
            <div class="nothing" v-if="orders.length===0">暂时没有相关订单</div>
            <div class="nothing" v-else>
              内容如下
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
      <OrderItem v-for="(item,index) in orders" :is="item" :key="index"/>
    </div>
  </div>
</template>

<script setup>

import OrderItem from "@/components/Order/OrderItem.vue";
import {onMounted, ref, watch} from "vue";
import axios from "axios";

const activeName = ref('')

const orders = ref([])

const getOrders =  ()=>{
  return axios.get("/orders/list?userId=" + JSON.parse(sessionStorage.getItem("userId")) + "&status=" + activeName.value)
}

watch(activeName, async ()=>{
  orders.value = (await getOrders()).data
})

onMounted(async ()=>{
  const res = await getOrders()
  orders.value = res.data
})

</script>

<style scoped lang="scss">
.train{
  width: 100%;
  height: 100vh;
  margin-left: 20px;
  //background-color: yellow;
  .detail{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    .tip{
      width: 100%;
      height: 50px;
      background-color: #f5f5f5;
      display: flex;
      align-items: center;
      border: 1px solid #e6e6e6;
    }
    .tab{
      margin-top: 20px;
      .nothing{
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 14px;
        color: #999;
      }
    }
  }
}
</style>