import { createRouter, createWebHistory } from 'vue-router'
import axios from "axios";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      // component: () => import('@/views/A.vue')
      component: () => import('@/views/index.vue'),
      redirect: '/train',   //标志了重定向，那么在子路由书写path时，不需要写/  见下边的order 和train
      children: [
        {
          path: 'train',
          component: () => import('@/views/Train/index.vue')
        },
        {
          path: '/plane',
          component: () => import('@/views/Plane/index.vue')
        },
        {
          path: '/trainTicket',
          component: () => import('@/views/Train-Tickets/index.vue'),
        }
      ]
    },
    {
      path: '/order',
      component: () => import('@/views/MyOrder/index.vue'),
      redirect: '/train',
      children: [
        {
          path: 'train',
          component: () => import('@/views/MyOrder/Train.vue')
        },
        {
          path: '/passengerInfo',
          component: () => import('@/views/MyOrder/PassengerInfo.vue')
        },
        {
          path: '/myself',
          component: () => import('@/views/MyOrder/Myself.vue')
        }
        ],
      meta:{
        requireLogin:true
      }
    },
    {
      path: '/booking',
      component: () => import('@/components/global/Booking.vue'),
      meta:{
        requireLogin:true
      }
    }
  ]
})
router.beforeEach((to, from, next)=>{
  if (to.meta.requireLogin){  //需要登录
    // 判断是否登录
    //从本地存储中获取token
    let token = sessionStorage.getItem('token');
    if (token){
      axios({
        url:'http://localhost:8080/user/checkToken',
        method:'get',
        headers:{
          token:token
        }
      }).then(resp=>{
          if (resp.data){
            next() //放行
          }else{
            alert('token无效,请先登录')
            router.go(0)
            next('/')
          }
        })
    }else{
      alert('请先登录')
      next('/')  //转到首页，需用户自主登录
    }
  }else{ //不需要登录
    next()  //放行
  }
})
export default router
