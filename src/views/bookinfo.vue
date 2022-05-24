<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="6">
            <div class="title">
              <el-image :src="require('../assets/photo/secondhand.svg')" />
            </div>
          </el-col>
          <el-col :span="18">
            <div class="link">
              <el-link :underline="false" @click="toPerson()">我的账号</el-link> | 
              <el-link :underline="false" @click="toMain()">返回首页</el-link> | 
              <el-link :underline="false" @click="exitSys()">退出登录</el-link>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-row class="book_inf" :gutter="40">
          <el-col :span="6">
            <div class="block">
              <el-carousel class="photo" trigger="click" height="50vh">
                <el-carousel-item
                  v-for="(img, index) in data.imgList"
                  :key="index"
                >
                  <img v-bind:src="img.url" />
                </el-carousel-item>
              </el-carousel>
            </div>
          </el-col>
          <el-col :span="12">
            <el-row>
              <div class="bookname">{{ data.bookname }}</div>
            </el-row>
            <el-row>
              <div class="shortInfo">
                {{ data.author }} 著/{{ data.publisher }}/{{ data.date }}
              </div>
            </el-row>
            <el-row>
              <el-card class="priceCard">
                <div class="priceItem">
                  <el-row>
                    <el-col :span="2">售价</el-col>
                    <el-col class="priceInfo" :span="4"
                      >¥{{ data.price }}</el-col
                    >
                  </el-row>
                  <el-row class="used">
                    <el-col :span="2">品相</el-col>
                    <el-col :span="3">
                      <el-tag size="large" class="usedInfo" v-bind="pattern">
                        {{ data.used }}
                      </el-tag>
                    </el-col>
                  </el-row>
                </div>
              </el-card>
            </el-row>
            <el-row class="place">
              <el-col :span="2.5"> 联系方式： </el-col>
              <el-col class="placeInfo" :span="2"> {{ data.tel }} </el-col>
            </el-row>
            <el-row>
              <el-col class="button" :span="6">
                <el-button
                  class="purchase"
                  color="#4d7c2c"
                  size="large"
                  plain
                  @click="addOrder(data.id, data.myid)"
                  >立即购买</el-button
                >
              </el-col>
              <el-col class="button" :span="8">
                <el-button class="chat" color="#4d7c2c" size="large"
                  >联系卖家</el-button
                >
              </el-col>
            </el-row>
          </el-col>
          <el-row>
            <el-card class="userCard">
              <div class="item">
                <el-row class="sellerId">{{ data.userId }}</el-row>
                <el-row class="sellerName">{{ data.username }}</el-row>
                <el-row class="breakTime">逾期{{ data.credit }}次</el-row>
                <el-row class="credit">信用{{ data.xinyong }}</el-row>
              </div>
            </el-card>
          </el-row>
        </el-row>
      </el-main>
      <el-tabs type="border-card" class="tabs">
        <el-tab-pane label="详细信息" class="pane">
          <div class="description">
            <p>ISBN: {{ data.iSBN }}</p>
            <p>{{ data.comment }}</p>
          </div>
          <div class="image" v-for="(img, index) in data.imgList" :key="index">
            <!-- <el-image :src="path1" lazy /> -->
            <el-image v-bind:src="img.url" />
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-container>
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import { inject } from "vue"; //Caution
import { useRoute } from "vue-router";
import { useRouter } from "vue-router";
import { useCookies } from "vue3-cookies";

export default {
  name: "bookinfo",
  setup() {
    let data = reactive({
      id: "",
      myid: "",
      path: "",
      bookname: "",
      author: "",
      publisher: "",
      date: "",
      price: "",
      used: "1",
      userId: "",
      username: "",
      credit: "",
      comment: "",
      xinyong: "",
      tel: "",
      iSBN: "",
      imgList: [],
    });
    // let imgList = reactive([]);
    let pattern = reactive({
      type: "success",
    });

    const route = useRoute();
    const router = useRouter();
    const axios = inject("axios");
    const { cookies } = useCookies();
    let bookid = route.query.bookid;
    let userid = route.query.userid;
    let token = cookies.get("token");
    let jwt = require("jsonwebtoken");
    let myid = jwt.decode(token).aud[0];

    data.id = bookid;
    data.myid = myid;

    let path = "";

    let url1 = "/methods/bookinfo";
    axios
      .get(url1, {
        params: {
          id: bookid,
        },
      })
      .then((response) => {
        data.id = response.data.id;
        data.bookname = response.data.bookname;
        data.path = response.data.photoPath;
        if (response.data.photonum == 1) {
          var temp = { url: "" };
          let root = "/";
          temp.url = root + data.path + "1.jpg";
          data.imgList.push(temp);
        } else if (response.data.photonum == 2) {
          var temp = { url: "" };
          var temp1 = { url: "" };
          let root = "/";
          temp.url = root + data.path + "1.jpg";
          data.imgList.push(temp);
          temp1.url = root + data.path + "2.jpg";
          data.imgList.push(temp1);
        } else if (response.data.photonum == 3) {
          var temp = { url: "" };
          var temp1 = { url: "" };
          var temp2 = { url: "" };
          let root = "/";
          temp.url = root + data.path + "1.jpg";
          data.imgList.push(temp);
          temp1.url = root + data.path + "2.jpg";
          data.imgList.push(temp1);
          temp2.url = root + data.path + "3.jpg";
          data.imgList.push(temp2);
        }
        if (response.data.author == "") {
          data.author = "未知作者";
        } else {
          data.author = response.data.author;
        }

        if (response.data.publisher == "") {
          data.publisher = "未知出版社";
        } else {
          data.publisher = response.data.publisher;
        }

        if (response.data.date == "") {
          data.date = "时间不详";
        } else {
          data.date = response.data.date;
        }

        data.price = response.data.price;
        data.used = response.data.used;
        if (data.used == "0") {
          data.used = "全新";
          pattern.type = "success";
        } else if (data.used == "1") {
          data.used = "较新";
          pattern.type = "";
        } else if (data.used == "2") {
          data.used = "普通";
          pattern.type = "info";
        } else if (data.used == "3") {
          data.used = "可以接受";
          pattern.type = "danger";
        }
        data.userId = response.data.userId;
        data.comment = response.data.comment;
        data.tel = response.data.tel;
        data.iSBN = response.data.iSBN;
      })
      .catch(function (error) {
        console.log(error);
      });

    data.userId = userid;
    let url2 = "methods/getuser";
    axios
      .get(url2, {
        params: {
          userid: userid,
        },
      })
      .then((response) => {
        data.username = response.data.username;
        data.credit = response.data.credit;
        if (data.credit < "1") {
          data.xinyong = "良好";
        } else {
          data.xinyong = "普通";
        }
      })
      .catch(function (error) {
        console.log(error);
      });

    return {
      data,
      pattern,
      cookies
    };
  },
  methods: {
    addOrder(bookid, myid) {
      let url = "/methods/addorder";
      let form = {
        bookid: bookid,
        buyer: myid,
      };
      this.axios
        .post(url, form)
        .then((response) => {
          if (response.data == true) {
            this.$message.success("下单成功");
            this.$router.replace({
              path: "4",
            });
          } else {
            this.$message.warning("下单失败");
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },

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
.el-header {
  background-color: #ffffff;
  color: var(--el-text-color-primary);
  text-align: right;
  height: 10vh;
}

.el-main {
  background-color: #e9eef3;
  /* background-color: #ffffff;  */
  color: var(--el-text-color-primary);
  text-align: center;
  height: 60vh;
  margin-top: 3vh;
  margin-left: 5vw;
  margin-right: 5vw;
}

.el-tabs {
  background-color: #ffffff;
  color: var(--el-text-color-primary);
  text-align: center;
  margin-left: 5vw;
  margin-right: 5vw;
  margin-top: 5vh;
}

.el-link {
  color: #4d7c2c;
}

.link {
  margin-top: 0.5vh;
}

.photo {
  margin-left: 1vw;
  margin-top: 0.5vh;
  border: 0.1vw solid var(--el-border-color);
}

.el-carousel__item img {
  height: inherit;
  width: inherit;
}

.book_inf {
  margin-top: 2vh;
}

.bookname {
  font-family: "微软雅黑";
  font-size: 3vh;
  font-weight: bolder;
}

.shortInfo {
  font-family: "微软雅黑";
  font-size: 2vh;
  margin-top: 1.5vh;
}

.priceCard {
  margin-top: 2vh;
  width: 100%;
}

.priceItem {
  font-family: "微软雅黑";
  color: #606266;
  line-height: 6vh;
}

.priceInfo {
  font-size: 3.5vh;
  color: #a63030;
}

.usedInfo {
  font-size: 2vh;
  font-weight: bolder;
}

.place {
  margin-top: 4vh;
  font-family: "微软雅黑";
  font-size: 2.5vh;
  margin-left: 0;
}

.placeInfo {
  color: #000000;
  font-weight: bolder;
}

.button {
  margin-top: 4vh;
}

.purchase {
  height: 6vh;
  width: 10vw;
}

.chat {
  height: 6vh;
  width: 10vw;
}

.userCard {
  width: 20vw;
  border: 0.2vw solid var(--el-border-color);
}

.item {
  font-family: "微软雅黑";
  font-size: 3vh;
  font-weight: bolder;
}

.breakTime {
  margin-top: 5vh;
  font-size: 2.5vh;
  font-weight: normal;
}

.credit {
  margin-top: 3vh;
  font-size: 2.5vh;
  font-weight: normal;
}

.description {
  text-align: left;
  text-indent: 2vw;
}

.image {
  margin-top: 3vh;
  text-align: center;
  border: solid 0.2vh var(--el-border-color);
  display: inline-block;
  width: 50%;
  box-sizing: border-box;
  vertical-align: top;
}

.title {
  text-align: left;
  height: 10vh;
  margin-left: 4vw;
}
</style>