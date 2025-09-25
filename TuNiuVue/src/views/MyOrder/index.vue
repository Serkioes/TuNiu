<template>
  <div class="order">
    <div class="title"><Title/></div>
    <div class="content">
      <div class="aside">
        <Aside/>
      </div>
      <div class="main">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script setup>

import Title from "@/components/global/Title.vue";
import Aside from "@/components/Order/Aside.vue";

import {ref, watch} from "vue";
import OrderItem from "@/components/Order/OrderItem.vue";

const activeName = ref('all')
</script>

<style scoped lang="scss">
.order{
  width: 100%;
  height: 100%;
  .title{
    width: 100%;
    height: 100px;
  }
  .content{
    width: 1120px;
    margin: 0 auto;
    display: flex;
    .aside{
      width: 200px;
      height: 500px;
      background-color: rgb(247,251,255);
      border: 1px solid rgb(190,211,238);
      border-top: none;
    }
    .main{
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
  }
}
</style>