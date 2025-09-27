<template>
  <div class="ticketItem">
    <div class="from item">
      <p style="color: blue;font-weight: 700;font-size: 24px;">{{hourAndMinute(ticket.startTime)}}</p>
      <p style="font-size: 13px; font-weight: 700;">{{ticket.startStation}}</p>
    </div>
    <div class="process item">
      <h6 style="font-size: 11px;color: rgb(153,166,196)">{{last(ticket.lasts)}}</h6>
      <hr/>
      <h6 style="font-size: 11px;color: rgb(153,166,196)">
        {{ticket.trainNumber}}
        <svg t="1725607126094" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4294" width="16" height="16"><path d="M832 176H494.4c-14.4 0-28.8 4.8-41.6 14.4l-30.4 25.6c-27.2 22.4-67.2 22.4-96 0l-30.4-25.6c-9.6-9.6-24-14.4-38.4-14.4H192c-70.4 0-128 57.6-128 128v416c0 70.4 57.6 128 128 128h52.8c20.8 0 40-9.6 51.2-25.6 52.8-72 104-72 156.8 0 12.8 16 32 25.6 51.2 25.6H832c70.4 0 128-57.6 128-128V304c0-70.4-57.6-128-128-128z m64 544c0 35.2-28.8 64-64 64H505.6c-38.4-52.8-83.2-80-129.6-80s-91.2 27.2-129.6 80H192c-35.2 0-64-28.8-64-64V304c0-35.2 28.8-64 64-64h65.6l30.4 25.6c51.2 43.2 126.4 43.2 177.6 0L496 240h336c35.2 0 64 28.8 64 64v416z" fill="#4A576A" p-id="4295"></path><path d="M384 368c-17.6 0-32 14.4-32 32v64c0 17.6 14.4 32 32 32s32-14.4 32-32v-64c0-17.6-14.4-32-32-32zM736 400H544c-17.6 0-32 14.4-32 32s14.4 32 32 32h192c17.6 0 32-14.4 32-32s-14.4-32-32-32zM736 560H544c-17.6 0-32 14.4-32 32s14.4 32 32 32h192c17.6 0 32-14.4 32-32s-14.4-32-32-32zM384 528c-17.6 0-32 14.4-32 32v64c0 17.6 14.4 32 32 32s32-14.4 32-32v-64c0-17.6-14.4-32-32-32z" fill="#4A576A" p-id="4296"></path></svg>
      </h6>
    </div>
    <div class="to item">
      <p style="font-weight: 700;font-size: 24px;">{{hourAndMinute(ticket.endTime)}}</p>
      <p style="font-size: 13px; font-weight: 700;">{{ticket.endStation}}</p>
    </div>
    <div class="secondExist item">
      <span style="color: rgb(153,166,196);font-size: 11px;">二等座有票</span>
    </div>
    <div class="firstExist item">
      <span style="color: rgb(153,166,196);font-size: 11px;">一等座有票</span>
    </div>
    <div class="goodFirst item">
      <span style="color: rgb(153,166,196);font-size: 11px;">优选一等座<span style="color: orange">(抢)</span></span>
    </div>
    <div class="economyExist item">
      <p style="font-weight: 700;font-size: 20px;color: #409eff">￥{{ticket.price}}</p>
      <span style="color: rgb(153,166,196);font-size: 11px;">商务座{{ticket.availableSeats}}张</span>
    </div>
    <div class="button item">
      <el-button type="warning" size="large" style="width: 50px;height: 50px" @click="booking">订</el-button>
    </div>
  </div>
</template>

<script setup>
import {useRouter} from "vue-router";
import {hourAndMinute,last} from '@/hook/timeFormat.js'
const router = useRouter();
const haha = defineProps(['ticket'])


const booking = () => {
  router.push({path: '/booking', query: {ticket: JSON.stringify(haha.ticket)}})
}
</script>

<style scoped lang="scss">
.ticketItem{
  height: 100px;
  background-color: rgb(248,250,253);
  //border: 1px solid #ccc;
  margin-top: 20px;
  border-radius: 10px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  .item{
    margin: 0 auto;
    width: 83px;
    //background-color: pink;
    text-align: center;
    //padding-top: 20px;
    .icon{
      vertical-align: -0.3em;
    }
  }
}
</style>