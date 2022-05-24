<template>
  <div class="common-layout">
    <el-row>
      <el-col :span="2"></el-col>
      <el-col :span="3">
        <img src="../assets/photo/logo2.png" alt="logo" width="180" />
      </el-col>
      <el-col class="title1" :span="6"> 二手书交易平台 </el-col>
      <el-col :span="7"></el-col>
      <el-col :span="5" class="title2">
        <el-link :underline="false" class="link">消息|</el-link>
        <el-link :underline="false" class="link" @click="toPerson()">我的账号|</el-link>
        <el-link :underline="false" class="link" @click="toMain()">返回首页|</el-link>
        <el-link :underline="false" class="link" @click="exitSys()">退出登录</el-link>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { useCookies } from "vue3-cookies";

export default {
  name: "Top",
  setup() {
    const { cookies } = useCookies();
    return { cookies };
  },
  methods: {
    toPerson() {
      this.$router.push({ path: "person" });
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