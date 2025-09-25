<template>
  <div class="train">
    <img src="@/assets/background.png">
    <div class="reserve">
      <div class="real">
        <div class="up">
          <el-radio-group v-model="radio">
            <el-radio :value="1">单程</el-radio>
            <el-radio :value="2">往返</el-radio>
            <el-radio :value="3">中转</el-radio>
          </el-radio-group>
          <router-link to="/order/train">
            <svg t="1725451186164" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4431" width="20" height="20"><path d="M825.702 942.723l-96.098-115.811c54.516-3.771 97.718-49.321 97.718-104.779V301.867c0-57.918-47.12-105.039-105.039-105.039h-121.24V175.14c0-16.282-14.44-29.036-32.875-29.036H455.833c-18.435 0-32.876 12.754-32.876 29.036v21.688h-121.24c-57.919 0-105.039 47.121-105.039 105.039v420.266c0 55.458 43.202 101.008 97.719 104.78l-96.098 115.81c-7.054 8.5-5.881 21.109 2.62 28.163a19.92 19.92 0 0 0 12.761 4.609c5.745 0 11.447-2.463 15.402-7.229L341.99 832.198a20.01 20.01 0 0 0 3.047-5.025h333.929a19.988 19.988 0 0 0 3.046 5.025L794.92 968.266c3.956 4.767 9.657 7.229 15.402 7.229 4.5 0 9.027-1.512 12.761-4.609 8.499-7.054 9.672-19.663 2.619-28.163zM462.957 186.105h98.086v10.723h-98.086v-10.723zM236.678 722.133V301.867c0-35.863 29.176-65.039 65.039-65.039h149.521c1.5 0.178 3.032 0.277 4.596 0.277h112.335c1.563 0 3.096-0.099 4.596-0.277h149.52c35.862 0 65.039 29.176 65.039 65.039v420.266c0 35.862-29.177 65.039-65.039 65.039H301.717c-35.863 0-65.039-29.177-65.039-65.039z" fill="" p-id="4432"></path><path d="M669.21 883H354.79c-11.046 0-20 8.954-20 20s8.954 20 20 20h314.42c11.046 0 20-8.954 20-20s-8.954-20-20-20zM683.099 585c43.95 0 79.707-36.865 79.707-82.18V389.179c0-45.314-35.757-82.179-79.707-82.179H340.902c-43.951 0-79.708 36.865-79.708 82.179V502.82c0 45.314 35.757 82.18 79.708 82.18h342.197z m-381.905-82.18V389.179c0-23.258 17.813-42.179 39.708-42.179h342.196c21.895 0 39.707 18.921 39.707 42.179V502.82c0 23.258-17.813 42.18-39.707 42.18H340.902c-21.895 0-39.708-18.922-39.708-42.18zM334.319 733.125c23.779 0 43.125-19.346 43.125-43.125s-19.346-43.125-43.125-43.125-43.125 19.346-43.125 43.125 19.346 43.125 43.125 43.125zM688.527 646.875c-23.779 0-43.125 19.346-43.125 43.125s19.346 43.125 43.125 43.125 43.125-19.346 43.125-43.125-19.345-43.125-43.125-43.125z" fill="" p-id="4433"></path><path d="M393 380c5.523 0 10-4.477 10-10s-4.477-10-10-10h-43.725c-8.729 0-16.935 3.399-23.106 9.57a32.785 32.785 0 0 0-9.339 19.214c-0.655 5.484 3.259 10.46 8.743 11.115 0.403 0.048 0.803 0.072 1.199 0.072 4.993 0 9.31-3.734 9.917-8.815a12.536 12.536 0 0 1 3.623-7.444 12.591 12.591 0 0 1 8.964-3.713H393zM326.598 403.706c-5.523 0-10 4.477-10 10v0.667c0 5.523 4.477 10 10 10s10-4.477 10-10v-0.667c0-5.523-4.477-10-10-10z" fill="" p-id="4434"></path></svg>
            火车票订单>
          </router-link>
        </div>




        <div class="middle">
          <div class="towhere item">
            <div class="from">
              <h1>出发城市</h1>
              <From @getFrom="getFrom"/>
            </div>
            <div class="to">
              <h1>到达城市</h1>
              <To @getTo="getTo"/>
            </div>
          </div>
          <div class="date item">
            <h1>出发日期</h1>
            <WhichDay @getDay="getDay"/>
          </div>
          <div class="city item" style="width: 200px;">
            <h1 v-if="radio === 3">指定中转城市</h1>
            <City v-if="radio === 3" @getMid="getMid"/>

            <h1 v-if="radio === 2">返回日期</h1>
            <ReturnDay v-if="radio === 2" @getReturn="getReturn"/>

            <span v-if="radio === 1" style="line-height: 40px; cursor: pointer;" @click="to">
              <el-icon><CirclePlusFilled/></el-icon>添加返程
            </span>

          </div>
          <div class="button item" @click="search">
            搜索
          </div>
        </div>
        <div class="down">
          <el-checkbox v-model="only" label="只搜高铁动车" size="large" />
        </div>
      </div>






      <div class="advertisement">
        <div class="left">
          <div class="info">
            <h1>火车团体票预订</h1>
            <span>支持9人以上团体火车票预订，专人服务</span>
          </div>
          <div class="triggler">
            <el-button type="warning" size="large" style="width: 100px;height: 50px" @click="stupid">查看</el-button>
          </div>
        </div>
        <div class="right">
          <div class="info">
            <h1>
              <span style="font-size: 21px;font-weight: 700;">微信扫码预订，</span><span style="font-size: 21px;font-weight: 700;color: orange">新人免费领</span>
              <img src="@/assets/discount.png" style="width: 90px;height: 26px;">
            </h1>
            <div class="more">
              <div class="per">
                <svg t="1725448081868" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5280" width="16" height="16"><path d="M704 318.805333v-58.197333C704 152.149333 618.389333 64 512.469333 64s-191.530667 88.149333-191.530666 196.608v58.197333h42.666666v-58.197333c0-85.162667 66.773333-153.941333 148.864-153.941333C594.538667 106.666667 661.333333 175.445333 661.333333 260.608v58.197333h42.666667z" fill="#3D3D3D" p-id="5281"></path><path d="M146.24 363.989333A25.045333 25.045333 0 0 1 170.666667 341.333333h682.666666c12.309333 0 23.552 10.474667 24.426667 22.656L914.346667 876.373333c0.810667 11.136-7.104 19.626667-18.282667 19.626667H127.936a17.856 17.856 0 0 1-18.282667-19.626667l36.586667-512.384zM103.68 360.96L67.093333 873.344A60.522667 60.522667 0 0 0 127.936 938.666667h768.128a60.373333 60.373333 0 0 0 60.842667-65.322667L920.32 360.96C917.845333 326.464 888.042667 298.666667 853.333333 298.666667H170.666667c-34.837333 0-64.512 27.626667-66.986667 62.293333z" fill="#3D3D3D" p-id="5282"></path><path d="M661.333333 469.333333m-42.666666 0a42.666667 42.666667 0 1 0 85.333333 0 42.666667 42.666667 0 1 0-85.333333 0Z" fill="#3D3D3D" p-id="5283"></path><path d="M362.666667 469.333333m-42.666667 0a42.666667 42.666667 0 1 0 85.333333 0 42.666667 42.666667 0 1 0-85.333333 0Z" fill="#3D3D3D" p-id="5284"></path></svg>
                多通道选择 <br>
                <span>抢票成功率更高</span>
              </div>
              <div class="per">
                <svg t="1725448104655" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6414" width="16" height="16"><path d="M978.466642 709.023935a498.765695 498.765695 0 0 1-818.798292 132.166712c-41.308114-44.256115-75.805744-98.126324-98.042095-153.921757a498.633336 498.633336 0 0 1-35.797157-188.359224c0.276751-66.336042 13.89772-133.646729 40.008587-194.110835a501.677598 501.677598 0 0 1 108.546605-158.831081c45.387185-44.785552 100.352365-81.605485 158.831082-105.730964q22.609364-9.32531 46.048981-16.448643l-14.800169-14.800169c-1.54018 7.376019-2.743446 14.824234-3.910614 22.260417q-4.897292 31.28491-7.712933 62.762343-3.958744 43.46196-4.21143 87.164572a893.412803 893.412803 0 0 0 5.414695 104.563796c4.367855 38.733125 11.2746 77.213565 21.887405 114.743424 10.937686 38.672962 25.773953 76.46754 45.18263 111.699161a415.956945 415.956945 0 0 0 74.602477 98.294781 399.147322 399.147322 0 0 0 110.303374 75.228176c49.261701 22.693592 101.844415 35.893418 155.546166 42.571543 65.938964 8.110011 132.92477 6.136655 198.827636-1.516115 6.738288-0.782123 12.670389-1.54018 18.975501-3.898581l13.344218-4.98152c6.088525-2.274172 10.239792-8.134077 8.42286-14.800169-1.612376-5.847872-8.687579-10.685-14.800169-8.422861l-14.186503 5.29437c-1.756768 0.649764-3.549634 1.203266-5.270304 1.973356 0.457241-0.192523 3.742157-0.457241 0.385045-0.072196l-3.429308 0.421143-7.508378 0.878384q-26.965186 3.044262-54.062731 4.704769a822.444187 822.444187 0 0 1-98.330879 0.252686q-22.296515-1.347658-44.520834-4.139235l-2.093682-0.264718c-3.489471-0.445208 2.502793 0.36098-0.962613-0.132359l-5.16201-0.733992q-5.101847-0.746025-10.191661-1.588311-10.45638-1.72067-20.840563-3.826385a497.586494 497.586494 0 0 1-74.229466-21.009021c-5.52299-2.045552-10.985816-4.247528-16.43661-6.47357-2.791577-1.143102 2.406532 1.058874-0.336915-0.144391l-1.600343-0.685862-3.958744-1.732703q-4.319724-1.913193-8.579285-3.922646-15.317573-7.219595-30.009449-15.642455-14.318863-8.254403-27.903733-17.712072-6.629994-4.608508-13.055434-9.5058l-1.203265-0.95058c-2.201976-1.672539 2.12978 1.696605 0 0l-3.056295-2.406531q-3.020197-2.406532-6.016329-4.885259a380.48467 380.48467 0 0 1-44.207985-43.317568q-5.089814-5.871937-9.938975-11.948429l-2.286205-2.887838-0.902449-1.203266c-1.576278-1.997421 1.335625 1.744735 0.300816 0.397078-1.552213-2.045552-3.116458-4.091104-4.644606-6.160721q-8.892134-12.032658-16.84572-24.715079a452.692649 452.692649 0 0 1-28.312844-52.053277c-2.009454-4.343789-3.934679-8.723677-5.823806-13.115597a9.794583 9.794583 0 0 1-0.457241-1.082939l-1.359691-3.308981q-1.49205-3.609797-2.923936-7.303823-5.282337-13.693164-9.890844-27.542754a653.601934 653.601934 0 0 1-25.677692-111.133627c-0.312849-2.12978-0.601633-4.259561-0.902449-6.401373-0.348947-2.526858 0.397078 3.044262 0.072196 0.505371l-0.18049-1.347657q-0.264718-2.009454-0.517404-4.018908-0.842286-6.666092-1.564246-13.344218-1.467984-13.476577-2.49076-26.989251-1.961323-25.930377-2.406531-51.957016-0.830253-45.724099 2.574988-91.448198 1.35969-18.542326 3.39321-37.024488 0.866351-7.905456 1.877094-15.79888l0.433176-3.381177 0.204555-1.564245c0.397078-2.984099-0.168457 1.203266-0.108294 0.818221 0.312849-2.201976 0.601633-4.415985 0.914482-6.617962 1.516115-10.612804 3.284916-21.165445 5.246239-31.706053 0.084229-0.457241 0.18049-0.914482 0.276751-1.371723 1.792866-8.60335-5.535023-17.627844-14.800169-14.800169a523.288251 523.288251 0 0 0-116.993531 949.244334 518.330796 518.330796 0 0 0 182.655744 67.178328 530.339389 530.339389 0 0 0 211.47396-7.965619 519.112919 519.112919 0 0 0 188.010276-89.174027 529.364743 529.364743 0 0 0 138.231172-154.547455q12.598193-21.478294 23.04254-44.099691c2.731413-5.896002 1.660507-12.971205-4.319724-16.460675-5.198108-3.044262-13.71723-1.612376-16.460676 4.319724z" fill="#2CA6E0" p-id="6415"></path><path d="M986.456327 230.701725a108.293919 108.293919 0 1 1-192.943667 69.512664 121.048537 121.048537 0 0 1 0.505372-12.995271l-3.03223 5.162011 1.90116-0.974646-9.048559-5.198108c2.502793 27.675113-5.101847 55.494617-22.284482 77.430153a108.293919 108.293919 0 0 1-176.747709-124.622236 105.213559 105.213559 0 0 1 6.593897-9.277179c2.021486-2.538891 2.478727-6.016329 0-8.507089-2.105715-2.105715-6.47357-2.550923-8.507089 0a121.890823 121.890823 0 0 0-25.027928 97.813474 120.242349 120.242349 0 0 0 238.005969-32.788992c-0.421143-4.620541-4.813063-7.388052-9.048559-5.198108a14.439189 14.439189 0 0 0-2.514825 1.443919c-2.611087 2.069617-2.478727 5.450794-2.67125 8.507089a114.129758 114.129758 0 0 0 0 13.428446 123.166284 123.166284 0 0 0 3.369144 22.777821 118.642005 118.642005 0 0 0 18.530293 40.333468 120.254381 120.254381 0 0 0 199.381138-134.428852 134.922191 134.922191 0 0 0-7.953586-10.91362c-2.033519-2.538891-6.389341-2.117748-8.507089 0-2.514825 2.514825-2.033519 6.016329 0 8.507089z" fill="#2CA6E0" p-id="6416"></path></svg>
                24h专业服务<br>
                <span>订单提醒，专业客服</span>
              </div>
              <div class="per">
                <svg t="1725448150671" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="7697" width="16" height="16"><path d="M416 192v224H192V192h224m48-64H144c-8.8 0-16 7.2-16 16v320c0 8.8 7.2 16 16 16h320c8.8 0 16-7.2 16-16V144c0-8.8-7.2-16-16-16z m-48 480v224H192V608h224m48-64H144c-8.8 0-16 7.2-16 16v320c0 8.8 7.2 16 16 16h320c8.8 0 16-7.2 16-16V560c0-8.8-7.2-16-16-16z m368-352v224H608V192h224m48-64H560c-8.8 0-16 7.2-16 16v320c0 8.8 7.2 16 16 16h320c8.8 0 16-7.2 16-16V144c0-8.8-7.2-16-16-16z m-48 480v224H608V608h224m48-64H560c-8.8 0-16 7.2-16 16v320c0 8.8 7.2 16 16 16h320c8.8 0 16-7.2 16-16V560c0-8.8-7.2-16-16-16z" p-id="7698"></path></svg>
                出行推荐<br>
                <span>多出行方案，多保障</span>
              </div>
            </div>
          </div>
          <div class="program">
            <img src="@/assets/program.png" style="width: 90px;height: 90px;">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

import City from "@/views/Train/City.vue";
import WhichDay from "@/views/Train/WhichDay.vue";
import From from "@/views/Train/From.vue";
import To from "@/views/Train/To.vue";
import {computed, onMounted, ref, watch} from "vue";
import ReturnDay from "@/views/Train/ReturnDay.vue";
import {useRouter} from "vue-router";
import {formattedDate} from "@/hook/timeFormat.js";
import axios from "axios";

const router = useRouter()
const radio = ref(1)
const only = ref(false)
const from = ref('')
const towhere = ref('')
const mid = ref('')
const day = ref(formattedDate(new Date()))
const returnDay = ref('')
const tickets = ref([])


const to = () =>{
  radio.value = 2
}

const getFrom = (val) =>{
  from.value = val   //更新出发地，父子件拿到出发查询条件
}

const getTo = (val) =>{
  towhere.value = val  //更新出发地，父子件拿到到达查询条件
}

const getMid = (val) =>{
  mid.value = val  //更新出发地，父子件拿到中转查询条件
}

const getDay = (val) =>{
  day.value = formattedDate(val)  //更新出发地，父子件拿到出发日期查询条件
}

const getReturn = (val) =>{
  returnDay.value = formattedDate(val)
}

const stupid = () =>{
  alert("这个作者很懒，什么都没有实现")
}

watch(radio,() => {
  if (radio.value === 3){
    returnDay.value = ''
  }
})


const search = async () =>{
  if (from.value === '' || towhere.value === ''){
    alert('出发地或目的地不能为空')
    return
  }
  tickets.value = await axios.get('/trainTickets/list?startStation=' + from.value + '&endStation=' + towhere.value +  '&startTime=' + day.value + '&condition=time')
  await router.push({
    path: '/trainTicket',
    query: {
      from: from.value,
      towhere: towhere.value,
      mid: mid.value,
      day: day.value,
      returnDay: returnDay.value,
      tickets: JSON.stringify(tickets.value.data)   //路由传递数组参数，需要转换为字符串，利用JSON。stringify转换  不然的话会显示  “[Object,Object]”   接收的时候要用parse方法解析为对象，不然就只是字符串
    }
  })
  router.go(0)
}

</script>

<style scoped lang="scss">
.train {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  .reserve{
    width: 1120px;
    height: 200px;
    margin: 0 auto;
    background-color: white;
    box-shadow: -1px 2px 4px #666666;
    border-radius: 5px;
    position: relative;
    top: -75px;
    z-index: 20;

    .real{
      .up{
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
        padding: 0 20px;
        a{
          text-decoration: none;
          color: black;
          &:hover{
            color: deepskyblue;
          }
        }
        .icon{
          vertical-align: -0.2em;
          fill: currentColor;
          overflow: hidden;
        }
      }
      .middle{
        margin-top: 20px;
        display: flex;
        justify-content: space-between;
        padding: 0 20px;
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
      .down{
        padding: 0 20px;
        margin-top: 10px;
      }
    }

  }
  .advertisement{
    width: 1120px;
    height: 130px;
    margin: 0 auto;
    //background-color: red;
    position: relative;
    top: 40px;
    display: flex;
    justify-content: space-between;
    .left{
      width: 49.5%;
      height: 100%;
      //background-color: red;
      box-shadow: -1px 2px 4px #666666;
      border-radius: 5px;
      display: flex;
      .info{
        width: 70%;
        height: 100%;
        //background-color: yellow;
        display: flex;
        flex-direction: column;
        justify-content: center;
        padding-left: 30px;
        h1{
          font-size: 24px;
          font-weight: 700;
          margin-bottom: 5px;
        }
        span{
          font-size: 15px;
        }
      }
      .triggler{
        width: 30%;
        height: 100%;
        //background-color: blue;
        display: flex;
        justify-content: center;
        align-items: center;
      }
    }
    .right{
      width: 49.5%;
      height: 100%;
      //background-color: red;
      box-shadow: -1px 2px 4px #666666;
      border-radius: 5px;
      display: flex;
      .info{
        width: 70%;
        height: 100%;
        //background-color: yellow;
        display: flex;
        flex-direction: column;
        justify-content: center;
        padding-left: 30px;
        h1{
          font-size: 24px;
          font-weight: 700;
          margin-bottom: 10px;
        }
        .more{
          display: flex;
          justify-content: space-between;
          .per{
            font-size: 14px;
            .icon{
              vertical-align: -0.15em;
              fill: currentColor;
              overflow: hidden;
            }
            span{
              font-size: 12px;
              color: #999999;
            }
          }
        }
      }
      .program{
        width: 30%;
        height: 100%;
        //background-color: blue;
        display: flex;
        justify-content: center;
        align-items: center;
      }
    }
  }
}
</style>