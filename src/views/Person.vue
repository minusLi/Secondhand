<template>
  <div id="background" class="common-layout">
    <el-container class="header">
      <el-header style="height: 200px">
        <Top />
      </el-header>

      <el-container class="main-box">
        <el-aside width="260px">
          <el-menu
            class="el-menu-vertical-demo"
            :router="true"
            :default-active="activePath"
          >
            <el-menu-item index="/1" @click="saveState('/1')">
              <el-icon><avatar /></el-icon>
              <span>个人信息</span>
            </el-menu-item>

            <el-menu-item index="/2" @click="saveState('/2')">
              <el-icon><edit-pen /></el-icon>
              <span>修改密码</span>
            </el-menu-item>

            <el-menu-item index="/3" @click="saveState('/3')">
              <el-icon><reading /></el-icon>
              <span>我的图书</span>
            </el-menu-item>

            <el-menu-item index="/4" @click="saveState('/4')">
              <el-icon><shopping-cart /></el-icon>
              <span>我的订单</span>
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
import Top from "../components/theTop.vue";
import {
  EditPen,
  ShoppingCart,
  Avatar,
  Reading,
} from "@element-plus/icons-vue";

export default {
  name: "Person",
  components: {
    Top,
    EditPen,
    ShoppingCart,
    Avatar,
    Reading,
  },
  data() {
    return {
      activePath: "/",
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
</style>