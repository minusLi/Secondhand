import { createRouter, createWebHistory } from 'vue-router'
import { useCookies } from "vue3-cookies";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/theMain.vue'),
    meta: {
      auth: true,
      roles: ['admin', 'user']
    },
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue'),
    meta: {
      auth: false,
      roles: ['admin', 'user']
    },
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue'),
    meta: {
      auth: false,
      roles: ['admin', 'user']
    },
  },
  {
    path: '/main',
    name: 'theMain',
    component: () => import('../views/theMain.vue'),
    meta: {
      auth: true,
      roles: ['admin', 'user']
    },
  },
  {
    path: '/search',
    name: 'theSearch',
    component: () => import('../views/Search.vue'),
    meta: {
      auth: true,
      roles: ['admin', 'user']
    },
  },
  {
    path: '/person',
    name: 'Person',
    component: () => import('../views/Person.vue'),
    redirect: '/1',
    children: [
      { path: '/1', name: 'PersonInfor', component: () => import('../components/personal/PersonInfor.vue') },
      { path: '/2', name: 'ChangePsw', component: () => import('../components/personal/ChangePsw.vue') },
      { path: '/3', name: 'MyBook', component: () => import('../components/personal/MyBook.vue') },
      { path: '/4', name: 'MyOrder', component: () => import('../components/personal/MyOrder.vue') },
    ],
    meta: {
      auth: true,
      roles: ['user']
    },
  },
  {
    path: '/bookinfo',
    name: 'bookinfo',
    component: () => import('../views/bookinfo.vue'),
    meta: {
      auth: true,
      roles: ['admin', 'user']
    },
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('../views/admin.vue'),
    redirect: '/5',
    children: [
      {
        path: '/5',
        name: 'UserInfo',
        component: () => import('../components/side-bar/userInfo.vue'),
      },
      {
        path: '/6',
        name: 'BookManage',
        component: () => import('../components/side-bar/bookManage.vue'),
      },
    ],
    meta: {
      auth: true,
      roles: ['admin']
    },
  },
  {
    path: '/chat',
    name: 'Chat',
    component: () => import('../views/Chat.vue'),
    meta: {
      auth: true,
    },
    roles: ['admin', 'user']
  },
]

const router = createRouter({
  history: createWebHistory("/"),
  routes
})

router.beforeEach((to, from, next) => {
  // 1. 每个条件执行后都要跟上 next() 或 使用路由跳转 api 否则页面就会停留一动不动
  // 2. 要合理的搭配条件语句，避免出现路由死循环。
  const { cookies } = useCookies();
  let token = cookies.get("token");
  let jwt = require("jsonwebtoken");
  if (to.meta.auth) {
    if (!token) {
      next({
        path: '/login',
        // query: {redirect: to.fullPath}   //登录成功后重定向到当前页面
      });
      return;
    }
    else {
      let role = jwt.decode(token).aud[3];
      if (to.meta.roles.includes(role)) {
        next();
      } else {
        next({ path: '/' });
      }
    }
  } else {
    next()
  }
})

export default router
