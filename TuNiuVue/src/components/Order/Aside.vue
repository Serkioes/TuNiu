<template>
  <div class="aside">
    <ul>
      <router-link to="/" style="text-decoration: none;color: black"><li class="item">我的途牛首页</li></router-link>
      <router-link to="/order/train" style="text-decoration: none;color: black"><li class="item">订单</li></router-link>
      <li class="item" @click="notice">我的消息</li>
      <li class="item" @click="notice">钱包</li>
      <li class="item" @click="notice">优惠券</li>
      <li class="item" @click="notice">积分</li>
      <li class="item" @click="notice">我的收藏</li>
      <li class="item" @click="notice">我的发票</li>
      <router-link to="/passengerInfo" style="text-decoration: none;color: black"><li class="item">常用旅客信息</li></router-link>
      <router-link to="/myself" style="text-decoration: none;color: black"><li class="item">个人中心</li></router-link>
    </ul>
  </div>
</template>

<script setup>
const notice = () => {
  alert('这个作者很懒，还什么都没有实现');
}
</script>

<style scoped lang="scss">
.aside{
  ul{
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
    justify-content: space-around;
    .item{
      height: 40px;
      line-height: 40px;
      padding-left: 20px;
      font-size: 14px;
      color: #333;
      cursor: pointer;
      &:hover{
        color: deepskyblue;
        background-color: white;
      }
    }
  }
}
</style>