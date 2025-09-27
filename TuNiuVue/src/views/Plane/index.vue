<template>
  <div class="plane">
    <div class="reserve">
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="国内、国际/中国港澳台" name="first">
          <div class="real">
            <div class="up">
              <el-radio-group v-model="radio">
                <el-radio :value="1">单程</el-radio>
                <el-radio :value="2">往返</el-radio>
                <el-radio :value="3">多程(含缺口程)</el-radio>
              </el-radio-group>
              <router-link to="/">
                不限仓等∨
              </router-link>
            </div>
            <div class="middle">
              <div class="towhere item">
                <div class="from">
                  <h1>出发地</h1>
                  <From/>
                </div>
                <div class="to">
                  <h1>目的地</h1>
                  <To/>
                </div>
              </div>
              <div class="date item">
                <h1>出发日期</h1>
                <WhichDay/>
              </div>
              <div class="city item" style="width: 200px;">
                <h1>乘客类型</h1>
                <el-checkbox>带儿童</el-checkbox>
                <el-checkbox>带婴儿</el-checkbox>
              </div>
            </div>
            <div class="button item">
              <svg t="1725452065372" class="icon" viewBox="0 0 1024 1024" version="1.1"
                   xmlns="http://www.w3.org/2000/svg" p-id="5596" width="25" height="25">
                <path d="M809.246403 771.926938 777.737782 803.458071 924.965961 950.667831 956.476629 919.157163Z"
                      fill="#5D5D5D" p-id="5597"></path>
                <path
                    d="M454.878536 70.285786C239.51556 70.285786 64.93236 244.847497 64.93236 460.231963c0 215.341486 174.583201 389.945153 389.945153 389.945153 215.362976 0 389.9472-174.603667 389.9472-389.945153C844.825736 244.847497 670.241512 70.285786 454.878536 70.285786zM454.878536 805.611108c-190.750415 0-345.381192-154.626683-345.381192-345.379145 0-190.751439 154.629753-345.380168 345.381192-345.380168 190.752462 0 345.382215 154.62873 345.382215 345.380168C800.259728 650.983401 645.630998 805.611108 454.878536 805.611108z"
                    fill="#5D5D5D" p-id="5598"></path>
              </svg>
              搜索
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="特价机票" name="second"></el-tab-pane>
        <el-tab-pane label="航班动态" name="third">
          <div class="up">
            <el-radio-group v-model="radio">
              <el-radio :value="1">搜航班号</el-radio>
              <el-radio :value="2">搜起降地</el-radio>
            </el-radio-group>
          </div>
          <div class="hangban">
            <el-row :gutter="20">
              <el-col>
              <el-input placeholder="请填写航班号,如MU1234"></el-input>
              </el-col>
                <div class="date item">
                <h1>出发日期</h1>
              </div>
            </el-row>

          </div>
          <div class="sousuo">
            <svg t="1725452065372" class="icon" viewBox="0 0 1024 1024" version="1.1"
                 xmlns="http://www.w3.org/2000/svg" p-id="5596" width="25" height="25">
              <path d="M809.246403 771.926938 777.737782 803.458071 924.965961 950.667831 956.476629 919.157163Z"
                    fill="#5D5D5D" p-id="5597"></path>
              <path
                  d="M454.878536 70.285786C239.51556 70.285786 64.93236 244.847497 64.93236 460.231963c0 215.341486 174.583201 389.945153 389.945153 389.945153 215.362976 0 389.9472-174.603667 389.9472-389.945153C844.825736 244.847497 670.241512 70.285786 454.878536 70.285786zM454.878536 805.611108c-190.750415 0-345.381192-154.626683-345.381192-345.379145 0-190.751439 154.629753-345.380168 345.381192-345.380168 190.752462 0 345.382215 154.62873 345.382215 345.380168C800.259728 650.983401 645.630998 805.611108 454.878536 805.611108z"
                  fill="#5D5D5D" p-id="5598"></path>
            </svg>
            搜索
          </div>
        </el-tab-pane>
        <el-tab-pane label="退票改签" name="fourth"></el-tab-pane>
        <el-tab-pane label="更多服务" name="fifth">
          <div class="gengduo">
            <div class="gdfl">
              <img src="../../assets/baoxiaoPZ.png" height="152" width="152">
              <h1>报销凭证</h1>
            </div>
            <div class="gdfl">
              <img src="../../assets/jichangGL.png" height="152" width="152"/>
              <h1>机场攻略</h1>
            </div>
            <div class="gdfl">
              <img src="../../assets/guoneiJCDQ.png" height="152" width="152"/>
              <h1>国内机场大全</h1>
            </div>
            <div class="gdfl">
              <img src="../../assets/guojiJCDQ.png" height="152" width="152"/>
              <h1>国际机场大全</h1>
            </div>
            <div class="gdfl">
              <img src="../../assets/dingzhiBJ.png" height="152" width="152"/>
              <h1>定制包机</h1>
            </div>
            <div class="gdfl">
              <img src="../../assets/tuantiP.png" height="152" width="152"/>
              <h1>团体票</h1>
            </div>
            <div class="xiazai">
              <img src="../../assets/xiechengCode.jpg" height="196" width="196"/>
              <h2>扫码下载携程APP</h2>
            </div>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>


</template>

<script setup>


import WhichDay from "@/views/Train/WhichDay.vue";
import From from "@/views/Train/From.vue";
import To from "@/views/Train/To.vue";
import {computed, onMounted, ref, watch} from "vue";

const radio = ref(1)
const only = ref(false)
const activeName = ref('first')

</script>

<style scoped lang="scss">
.plane {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;

  .reserve {
    width: 1120px;
    height: 250px;
    margin: 0 auto;
    transform: translate(20px, 120px);
    background-color: white;
    box-shadow: -1px 2px 4px #666666;
    border-radius: 5px;
    position: relative;
    top: -75px;
    z-index: 20;

    .real {
      .up {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
        padding: 0 20px;

        a {
          text-decoration: none;
          color: black;

          &:hover {
            color: deepskyblue;
          }
        }

        .icon {
          vertical-align: -0.2em;
          fill: currentColor;
          overflow: hidden;
        }
      }

      .middle {
        margin-top: 20px;
        display: flex;
        justify-content: space-between;
        padding: 0 20px;

        .towhere {
          display: flex;

          .from {
            width: 49%;
          }

          .to {
            width: 49%;
          }
        }

        .item {
          width: 350px;
          height: 80px;
          background-color: rgb(248,248,248);
          text-align: center;
          padding-top: 15px;
          font-size: 14px;
          border-radius: 5px;
        }
      }

      .button {
        width: 150px;
        background-color: #ff8f21;
        color: white;
        align-content: center;
        text-align: center;
        position: relative;
        left: 500px;
        font-size: 22px;
        font-weight: 700;
        cursor: pointer;

        .icon {
          vertical-align: -0.1em;
          fill: currentColor;
          overflow: hidden;
        }
      }
    }

    .hangban {
      display: flex;
      justify-content: space-between;
      margin-top: 20px;
      padding: 0 20px;
      .sousuo{
          width: 150px;
          background-color: #ff8f21;
          color: white;
          align-content: center;
          text-align: center;
          position: relative;
          left: 500px;
          font-size: 22px;
          font-weight: 700;
          cursor: pointer;
      }
    }

    .gengduo {
      width: 1120px;
      height: 200px;
      display: flex;

      .gdfl {
        width: 120px;
        height: 140px;
        border-radius: 20px;

      }

      .xiazai {
        width: 120px;
        height: 140px;
      }
    }
  }
}
</style>