<template>
  <div id="background">
    <el-container class="header">
      <el-header style="height: 200px">
        <div class="common-layout">
          <el-row>
            <el-col :span="2"></el-col>
            <el-col :span="3">
              <img src="../assets/photo/logo2.png" alt="logo" width="180" />
            </el-col>
            <el-col class="title1" :span="6"> 二手书交易平台 </el-col>
            <el-col :span="7"></el-col>
            <el-col :span="5" class="title2">
              <el-link :underline="false" @click="toMain()">返回首页|
              </el-link>
              <el-link :underline="false" @click="exitSys()">退出登录</el-link>
            </el-col>
          </el-row>
        </div>
      </el-header>

      <el-container class="main-box">
        <el-aside width="260px">
          <el-menu class="el-menu-vertical-demo" :router="true" :default-active="activePath">
            <el-menu-item index="/5" @click="saveState('/5')">
              <el-icon>
                <avatar />
              </el-icon>
              <span>用户信息</span>
            </el-menu-item>

            <el-menu-item index="/6" @click="saveState('/6')">
              <el-icon>
                <View />
              </el-icon>
              <span>图书审核</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { Avatar, View } from "@element-plus/icons-vue";
import { useCookies } from "vue3-cookies";

export default {
  name: "Admin",
  components: {
    Avatar,
    View,
  },
  setup() {
    const { cookies } = useCookies();
    return { cookies };
  },
  data() {
    return {
      activePath: "/5",
    };
  },
  created() {
    this.activePath = window.sessionStorage.getItem("activePath");
  },
  methods: {
    saveState(activePath) {
      window.sessionStorage.setItem("activePath", activePath);
      this.activePath = window.sessionStorage.getItem("activePath");
    },
    toMain() {
      this.$router.push({ path: "main" });
    },
    exitSys() {
      this.cookies.remove("token");
      this.$router.push({ path: "login" });
    },
  },
};
</script>

<style scoped>
@media screen {
  #background {
    position: fixed;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-size: cover;
    background-image: url("../assets/photo/bg.png");
    min-width: 1200px;
    background-color: rgba(83, 104, 71);
    opacity: 0.95;
    z-index: -1;
  }
}

.main-box {
  background-color: white;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 90%;
  height: 80%;
  transform: translate(-50%, -50%);
  padding: 30px;
  border-radius: 20px;
  box-sizing: border-box;
}

.common-layout {
  width: 100%;
}

.title1 {
  font-size: 35px;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  color: rgb(252, 254, 255);
  text-align: center;
  margin-top: 8px;
}

.link {
  font-size: 15px;
  color: white;
}
</style>