<template>
  <div id="background" class="common-layout">
    <el-container class="header">
      <el-header>
        <Top />
      </el-header>
      <el-container>
        <el-aside class="aside">
          <div class="picp">
            <el-carousel trigger="click" height="300px">
              <el-carousel-item v-for="item in imagebox" :key="item.id">
                <img :src="item.idView" class="image" />
              </el-carousel-item>
            </el-carousel>
            <p>实物展示</p>
          </div>
        </el-aside>
        <el-main class="main">
          <el-container>
            <el-aside class="inner-aside">
              <div class="context">
                <h2 v-text="msg"></h2>
                <div class="boder1">
                  <el-row>
                    <el-col :span="3">作者：</el-col>
                    <el-col :span="5" v-text="author"></el-col>
                    <el-col :span="3">出版社:</el-col>
                    <el-col :span="10" v-text="address"></el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="2">ISBN：</el-col>
                    <el-col :span="10" v-text="isbn"></el-col>
                  </el-row>
                </div>
                <div class="boder2">
                  <el-row>
                    <el-col class="text1" :span="8">售价￥</el-col>
                    <el-col class="text2" :span="10" v-text="price"></el-col>
                  </el-row>
                </div>
                <el-divider />
                <el-container>
                  <el-aside class="pricecss"> </el-aside>
                  <el-main>
                    <button class="bubtn" @click="show">立刻购买</button>
                    <model
                      :showModel="showModel"
                      @cancel="showModel = false"
                    ></model>
                  </el-main>
                </el-container>
              </div>
            </el-aside>
            <el-main class="inner-main">
              <p>卖家信息</p>
              <el-row>
                <el-col :span="9">一卡通：</el-col>
                <el-col :span="15" v-text="num"></el-col>
              </el-row>
              <el-row>
                <el-col :span="9">电话号:</el-col>
                <el-col :span="15" v-text="tele"></el-col>
              </el-row>
              <div class="chat">
                <p>想了解更多，或者有意者请点击下方按钮联系我</p>
                <div class="pic">
                  <img width="100" src="../assets/photo/down.png" />
                </div>

                <button class="btn" @click="chat">立刻联系</button>
              </div>
            </el-main>
          </el-container>
        </el-main>
      </el-container>
      <el-footer class="foot">
        <h3 style="color: white" class="ques">
          如果有问题请致电客服:1398274089
        </h3>
      </el-footer>
    </el-container>
  </div>
</template>

<script >
import model from "./model.vue";
import Top from "../components/theTop.vue";

export default {
  name: "Infor",
  components: {
    model,
    Top,
  },
  data() {
    return {
      msg: "毛主席文集",
      price: 100,
      showModel: false,
      tele: 15161410888,
      num: 213288844,
      imagebox: [
        { id: 0, idView: require("../assets/photo/p1.jpg") },
        { id: 1, idView: require("../assets/photo/p2.jpg") },
        { id: 2, idView: require("../assets/photo/p3.jpg") },

        // imagebox是assets下一个放图片的文件夹
      ],

      author: "tom",
      isbn: "12345678",
      address: "东南大学物理学院出版社",
    };
  },
  methods: {
    show() {
      this.showModel = true;
      console.log(this.showModel);
    },
    chat() {
      alert("conneting...");
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
    background-size: cover;
    background-image: url("../assets/photo/bg.png");
    min-width: 1200px;
    background-color: rgba(83, 104, 71);

    opacity: 0.95;
    z-index: -1;
  }
}
.inner-main {
  background-color: rgb(255, 255, 255);

  margin-left: 40px;
  border-radius: 10px;
  font-family: "Courier New", Courier, monospace;
}
.chat {
  border: solid antiquewhite 1px;
  border-radius: 10px;
  background-color: antiquewhite;
}
.ques {
  margin-top: 45px;
  margin-right: 40px;
  float: right;
}
.foot {
  height: 98px;
}
.boder1 {
  padding: 10px;
  border-radius: 10px;
  border: 2px solid rgba(125, 115, 115, 0.878);
  margin: 15px;
}

.pic {
  margin-left: 48px;
  margin-top: 30px;
}
.boder2 {
  padding: 20px;
  margin: 15px;
  border-radius: 10px;
  background-color: antiquewhite;
  font-size: 30px;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
}
.text2 {
  color: rgb(131, 44, 44);
}
.btn {
  margin-left: 36px;
  margin-right: 36px;
  margin-top: 20px;
  margin-bottom: 10px;
}
.bubtn {
  background-color: rgb(185, 17, 17);
  color: rgb(255, 255, 255);
  padding: 14px 20px;

  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.bubtn:hover {
  background-color: red;
  color: rgb(255, 255, 255);
}
.inner-aside {
  width: 60%;
  background-color: white;
  border-radius: 10px;
  height: 400px;
}
.header {
  text-align: center;
  height: 150px;
}
.aside {
  width: 26%;
  float: left;
  margin-top: 120px;
  height: 400px;
  margin-left: 60px;
  background-color: rgba(235, 195, 76, 0.7);
  border-radius: 10px;
}
.main {
  margin-top: 100px;
  margin-right: 40px;
}
.picp {
  width: 80%;
  margin-top: 50px;
  margin-left: 40px;
}

.el-carousel__item {
  color: #475669;
  font-size: 14px;
  line-height: 150px;
  margin: 0;
  width: 300px;

  overflow: hidden;
}
.image {
  /*设置图片宽度和浏览器宽度一致*/
  width: 100%;
}

.el-row {
  margin-bottom: 20px;
  text-align: left;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
