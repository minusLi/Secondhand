<template>
  <div id="background"></div>
  <div id="main-box">
    <div class="autologo">
      <el-row justify="space-between">
        <el-col :span="8">
          <img src="https://newids.seu.edu.cn/authserver/custom/images/logodz.png" alt="logo" />
        </el-col>
        <el-col :span="3">
          <el-link :underline="false" @click="toPerson()">我的账号</el-link> |
          <el-link :underline="false" @click="exitSys()">退出登录</el-link>
        </el-col>
      </el-row>
      <p class="title1" style="
          font-size: 50px;
          font-family: Arial, Helvetica, sans-serif;
          color: slategray;
          text-align: center;
        ">
        二手书交易平台
      </p>
    </div>
    <el-row class="space"></el-row>
    <div class="mt-4">
      <el-row justify="center">
        <el-col :span="15">
          <el-input v-model="content" placeholder="按作者/书名/ISBN/出版社搜索" class="input-with-select" size="large" clearable>
            <template #prepend>
              <el-select v-model="select" placeholder="筛选条件" style="width: 100px">
                <el-option label="作者" value="1" />
                <el-option label="书名" value="2" />
                <el-option label="ISBN" value="3" />
                <el-option label="出版社" value="4" />
              </el-select>
            </template>
            <template #append>
              <el-button :icon="Search" @click="toSearch"></el-button>
            </template>
          </el-input>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { inject, ref } from "vue";
import { Search } from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import { useCookies } from "vue3-cookies";

export default {
  name: "theMain",
  setup() {
    const { cookies } = useCookies();
    const content = ref("");
    const select = ref("");
    // const axios = inject("axios");
    const router = useRouter();
    // let url = "http://localhost:8081/search";
    let category = "";
    let keyword = "";
    const toSearch = async () => {
      switch (select.value) {
        case "1":
          category = "author";
          break;
        case "2":
          category = "bookname";
          break;
        case "3":
          category = "ISBN";
          break;
        case "4":
          category = "publisher";
          break;
        default:
          alert("筛选条件必选");
          return;
      }
      keyword = content.value;

      // console.log(category);
      // console.log(keyword);
      router.push({
        path: "search",
        query: { category: category, keyword: keyword },
      });
      // axios
      //   .get(url, {
      //     params: {
      //       category: category,
      //       keyword: keyword,
      //     },
      //   })
      //   .then((response) => {
      //     console.log(response);
      //   })
      //   .catch(function (error) {
      //     console.log(error);
      //   });
    };

    return {
      content,
      select,
      Search,
      toSearch,
      cookies
    };
  },
  methods: {
    toPerson() {
      this.$router.push({ path: "person" });
    },
    exitSys() {
      this.cookies.remove("token");
      this.$router.push({ path: "login" });
    },
  },
};
</script>

<style scoped>
@media screen and (min-width: 840px) {
  #background {
    position: fixed;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-image: url(https://img0.baidu.com/it/u=2893112281,2814986277&fm=253&fmt=auto&app=120&f=JPEG?w=1080&h=720);
    background-size: cover;
    opacity: 0.95;
    z-index: -1;
  }

  #main-box {
    background-color: rgba(255, 255, 255, 0.8);
    position: absolute;
    top: 50%;
    left: 50%;
    width: 80%;
    height: 90%;
    transform: translate(-50%, -50%);
    padding: 30px;
    border-radius: 20px;
    box-sizing: border-box;
  }
}

.space {
  margin-bottom: 20px;
}

.el-input :deep() .el-input__inner {
  width: 100%;
  height: 50px;
  display: inline-block;
  box-sizing: border-box;
}
</style>