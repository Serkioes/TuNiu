<template>
  <div class="train-tickets">
    <div class="info">
      <div class="towhere item">
        <div class="from">
          <h1>出发城市</h1>
          <From :is="route.query.from" @getFrom="getFrom"/>
        </div>
        <div class="to">
          <h1>到达城市</h1>
          <To :is="route.query.towhere" @getTo="getTo"/>
        </div>
      </div>
      <div class="date item">
        <h1>出发日期</h1>
        <WhichDay :is="value1" @getDay="getDay"/>
      </div>
      <div class="city item" style="width: 200px;" ref="mask">

        <span v-if="flag && route.query.returnDay === ''" style="line-height: 40px; cursor: pointer;" @click="to">
          <el-icon><CirclePlusFilled/></el-icon>添加返程
        </span>

        <h1 v-if="flag === false || route.query.returnDay !== ''">返回日期</h1>
        <ReturnDay v-if="flag === false || route.query.returnDay !== ''" :is="route.query.returnDay" @getReturn="getReturn"/>

      </div>
      <div class="button item" @click="router.push('#')">
        搜索
      </div>
    </div>
    <div class="tickets">
      <div class="up">
        <div class="left">
          <h1>{{from}}-{{towhere}}</h1>
          <h2>
            单程 {{formattedDate(day)}}
          </h2>
        </div>
        <div class="right">
          <span style="color: #409eff;cursor: pointer;font-weight: 700;" @click="router.push('/train')">火车票</span> &nbsp; > &nbsp;
          <span style="color: rgb(102,102,102);font-weight: 700;">{{from}}到{{towhere}}火车票</span>
        </div>
      </div>
      <div class="down">
        <div class="content">
          <div class="condition" style="width: 750px;">
            <el-segmented v-model="value1" :options="options1" block style="margin-bottom: 7px;height: 40px;border-radius: 10px" class="condition1"/>
            <el-segmented v-model="value2" :options="options2" block style="height: 40px;border-radius: 10px" class="condition2"/>
          </div>
          <div class="real-ticket">
            <TicketItem v-for="(item,index) in tickets" :key="index" :ticket="item"/>
          </div>
        </div>
        <div class="limit">
          <div class="tip per">
            <span>筛选</span>
            <span>全部重置</span>
          </div>
          <hr>
          <div class="onlyAvailable per">
            <el-checkbox v-model="only" label="仅显示有票车次" size="large" disabled/>
          </div>
          <hr>
          <div class="trainType per">
            <div class="msg">
              <span>车型</span>
              <span>重置</span>
            </div>
            <div style="margin-left: 20px">
              <el-checkbox v-model="only" label="高铁(G/C)" size="large" disabled/> &nbsp;&nbsp;&nbsp;
              <el-checkbox v-model="only" label="动车(D)" size="large" disabled/>
              <el-checkbox v-model="only" label="普通(Z/T/K)" size="large" disabled/> &nbsp;
              <el-checkbox v-model="only" label="其他(L/Y)" size="large" disabled/>
            </div>
          </div>
          <hr>
          <div class="startTime per">
            <div class="msg">
              <span>出发时间</span>
              <span>重置</span>
            </div>
            <div class="timeList">
              <div class="timeList-item" >
                <p>00:00</p>
                <p>-</p>
                <p>06:00</p>
              </div>
              <div class="timeList-item" >
                <p>06:00</p>
                <p>-</p>
                <p>12:00</p>
              </div>
              <div class="timeList-item" >
                <p>12:00</p>
                <p>-</p>
                <p>18:00</p>
              </div>
              <div class="timeList-item" >
                <p>18:00</p>
                <p>-</p>
                <p>24:00</p>
              </div>
            </div>
          </div>
          <hr>
          <div class="endTime per">
            <div class="msg">
              <span>到达时间</span>
              <span>重置</span>
            </div>
            <div class="timeList">
              <div class="timeList-item" >
                <p>00:00</p>
                <p>-</p>
                <p>06:00</p>
              </div>
              <div class="timeList-item" >
                <p>06:00</p>
                <p>-</p>
                <p>12:00</p>
              </div>
              <div class="timeList-item" >
                <p>12:00</p>
                <p>-</p>
                <p>18:00</p>
              </div>
              <div class="timeList-item" >
                <p>18:00</p>
                <p>-</p>
                <p>24:00</p>
              </div>
            </div>
          </div>
          <hr>
          <div class="seatType per">
            <div class="msg">
              <span>坐席</span>
              <span>重置</span>
            </div>
            <div class="every" style="margin-left: 20px">
              <el-checkbox style="width: 100px;" v-model="only" label="二等座" size="large" disabled/>
              <el-checkbox style="width: 100px;" v-model="only" label="一等座" size="large" disabled/>
              <el-checkbox style="width: 100px;" v-model="only" label="商务座" size="large" disabled/>
              <el-checkbox style="width: 100px;" v-model="only" label="优选一等座" size="large" disabled/>
              <el-checkbox style="width: 100px;" v-model="only" label="硬座" size="large" disabled />
              <el-checkbox style="width: 100px;" v-model="only" label="硬卧" size="large" disabled/>
              <el-checkbox style="width: 100px;" v-model="only" label="软卧" size="large" disabled/>
              <el-checkbox style="width: 100px;" v-model="only" label="无座" size="large" disabled/>
              <el-checkbox style="width: 100px;" v-model="only" label="二等卧" size="large" disabled/>
              <el-checkbox style="width: 100px;" v-model="only" label="一等卧" size="large" disabled/>
            </div>
          </div>
          <hr>
          <div class="startStation per">
            <div class="msg">
              <span>出发车站</span>
              <span>重置</span>
            </div>
            <div style="margin-left: 20px">
              <el-checkbox v-model="only" label="上海虹桥" size="large" disabled/> &nbsp;&nbsp;&nbsp;
              <el-checkbox v-model="only" label="上海" size="large" disabled/>
            </div>
          </div>
          <hr>
          <div class="endStation per">
            <div class="msg">
              <span>到达车站</span>
              <span>重置</span>
            </div>
            <div style="margin-left: 20px">
              <el-checkbox v-model="only" label="北京南" size="large" disabled/> &nbsp;&nbsp;&nbsp;
              <el-checkbox v-model="only" label="北京" size="large" disabled/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

import To from "@/views/Train/To.vue";
import From from "@/views/Train/From.vue";
import WhichDay from "@/views/Train/WhichDay.vue";
import City from "@/views/Train/City.vue";
import ReturnDay from "@/views/Train/ReturnDay.vue";
import {computed, onMounted, ref, watch} from "vue";
import router from "@/router/index.js";
import TicketItem from "@/views/Train-Tickets/TicketItem.vue";
import {useRoute} from "vue-router";
import {dateAndweek} from "@/hook/timeFormat.js";
import axios from "axios";
import {formattedDate,yearAndMonthAndDay,back} from "@/hook/timeFormat.js";

const route = useRoute()
const flag = ref(true)
const mask = ref()
const from = ref('')
const towhere = ref('')
const cities = ref([])
const day = ref('')
const returnDay = ref('')
const tickets = ref(JSON.parse(route.query.tickets))   //拿到传递过来的车票信息


// const from1 = computed(() => {
//   return cities.value.find(item => from.value === item.id).name
// })

const getFrom = (val) => {
  if (typeof val === 'string') {
    from.value = val
  } else {
    from.value = cities.value.find(item => item.id === val).name
  }
}
const getTo = (val) =>{
  if (typeof val === 'string') {
    towhere.value = val
  } else {
    towhere.value = cities.value.find(item => item.id === val).name
  }
}

const getReturn = (val) => {
  returnDay.value = val
}
const getDay = (val) => {
  day.value = val
  value1.value = dateAndweek(val)
}
const to = () => {
  flag.value = false
}
const value1 = ref(route.query.day)
const value2 = ref('出发 早-晚')

watch(value2, async (val) => {
  const startStation = typeof from.value === 'string' ? cities.value.find(item => item.name === from.value).id : from.value
  const endStation = typeof towhere.value === 'string' ? cities.value.find(item => item.name === towhere.value).id : towhere.value
  if (val === '价格排序') {
    const res = await axios.get('/trainTickets/list?startStation=' + startStation + '&endStation=' + endStation +  '&startTime=' + yearAndMonthAndDay(day.value) + '&condition=price')
    tickets.value = res.data
  } else if (val === '运行时长') {
    const res = await axios.get('/trainTickets/list?startStation=' + startStation + '&endStation=' + endStation +  '&startTime=' + yearAndMonthAndDay(day.value) + '&condition=lasts')
    tickets.value = res.data
  } else {
    const res = await axios.get('/trainTickets/list?startStation=' + startStation + '&endStation=' + endStation +  '&startTime=' + yearAndMonthAndDay(day.value) + '&condition=time')
    tickets.value = res.data
  }
})

watch(value1, async (val) => {
  const startStation = typeof from.value === 'string' ? cities.value.find(item => item.name === from.value).id : from.value
  const endStation = typeof towhere.value === 'string' ? cities.value.find(item => item.name === towhere.value).id : towhere.value

  day.value = back(val)

  const res = await axios.get('/trainTickets/list?startStation=' + startStation + '&endStation=' + endStation +  '&startTime=' + yearAndMonthAndDay(day.value) + '&condition=' + value2.value)
  tickets.value = res.data

})


const options1 = computed(() => {
  let arr = []
  for (let i = 0; i < 7; i++) {
    arr.push(dateAndweek(new Date(new Date().getTime() + i * 24 * 60 * 60 * 1000)))
  }
  return arr
})


const options2 = ref([
  '出发 早-晚',
  '运行时长',
  '价格排序',
])

const getCity = async () =>{
  return await axios.get('/city/list')
}

onMounted(async () => {
  const res =  await getCity()
  cities.value = res.data
})
</script>


<style scoped lang="scss">
.train-tickets{
  width: 1120px;
  height: 100%;
  margin: 0 auto;
  background-color: white;
  .info{
    margin-top: 20px;
    display: flex;
    justify-content: space-between;
    .towhere{
      display: flex;
      .from{
        width: 49%;
      }
      .to{
        width: 49%;
      }
    }
    .item{
      width: 350px;
      height: 80px;
      background-color: rgb(248,248,248);
      text-align: center;
      padding-top: 15px;
      font-size: 14px;
      border-radius: 5px;
    }
    .button{
      width: 150px;
      background-color: deepskyblue;
      color: white;
      padding-top: 25px;
      font-size: 22px;
      font-weight: 700;
      cursor: pointer;
      .icon{
        vertical-align: -0.1em;
        fill: currentColor;
        overflow: hidden;
      }
    }
  }
  .tickets{
    margin-top: 20px;
    .up{
      //background-color: pink;
      height: 80px;
      display: flex;
      justify-content: space-between;
      .left{
        display: flex;
        h1{
          font-size: 20px;
          font-weight: 700;
          line-height: 80px;
        }
        h2{
          line-height: 80px;
          font-size: 14px;
          color: rgb(150,150,150);
          margin-left: 10px;
          margin-top: 1.5px;
        }
      }
      .right{
        line-height: 80px;
        font-size: 14px;
        color: rgb(150,150,150);
      }
    }
    .down{
      width: 100%;
      display: flex;
      .limit{
        margin-left: 30px;
        width: 100%;
        background-color: rgb(248,250,253);
        border-radius: 10px;
        .per{
          margin-bottom: 20px;
        }
        .tip{
          display: flex;
          justify-content: space-between;
          align-items: center;
          height: 60px;
          margin: 0 20px;
          font-weight: 700;
        }
        .onlyAvailable{
          margin-left: 20px;
          padding-top: 20px;
          &:hover{
            color: #409eff;
          }
        }
        .trainType{
          .msg{
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin:10px 20px;
            font-weight: 700;
          }
        }
        .startTime{
          .msg{
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin:10px 20px;
            font-weight: 700;
          }
          .timeList{
            display: flex;
            justify-content: space-between;
            margin: 0 20px;
            .timeList-item{
              border: 1px solid rgb(230,230,230);
              border-radius: 10px;
              cursor: pointer;
              text-align: center;
              width: 60px;
              font-size: 14px;
              height: 80px;
              padding-top: 10px;
              &:hover{
                background-color: rgb(222,239,254);
              }
            }
          }
        }
        .endTime{
          .msg{
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin:10px 20px;
            font-weight: 700;
          }
          .timeList{
            display: flex;
            justify-content: space-between;
            margin: 0 20px;
            .timeList-item{
              border: 1px solid rgb(230,230,230);
              border-radius: 10px;
              cursor: pointer;
              text-align: center;
              width: 60px;
              font-size: 14px;
              height: 80px;
              padding-top: 10px;
              &:hover{
                background-color: rgb(222,239,254);
              }
            }
          }
        }
        .seatType{
          .msg{
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin:10px 20px;
            font-weight: 700;
          }
          .every{
            display: flex;
            justify-content: flex-start;
            flex-wrap: wrap;
          }
        }
        .startStation{
          .msg{
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin:10px 20px;
            font-weight: 700;
          }
        }
        .endStation{
          .msg{
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin:10px 20px;
            font-weight: 700;
          }
        }
      }
    }
  }
}
</style>