<template>
  <div class="navi">
    <div class="aside"><Navi/></div>
    <div class="main">
      <div class="title"><Title/></div>
      <div class="eee"><router-view></router-view></div>
    </div>
  </div>
</template>

<script setup>

import Navi from "@/components/global/Navi.vue";
</script>

<style scoped lang="scss">
.navi{
  display: flex;
  width: 100%;
  height: 100vh;
  //background-color: yellowgreen;
  .aside{
    //width: 193px;
    height: 100vh;
    //background-color: red;
    position: static;
  }
  .main{
    width: 100%;
    display: flex;
    flex-direction: column;
    //background-color: rgb(248,250,253);
    .title{
      margin: 0 auto;
      width: 100%;
      height: 100px;
    }
    .eee{
      flex: 1;
      //background-color: green;
    }
  }
}
</style>