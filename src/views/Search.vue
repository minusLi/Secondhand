<template>
  <div id="background" class="common-layout">
    <el-container class="header">
      <el-header style="height: 200px">
        <Top />
      </el-header>

      <el-container class="main-box">
        <el-main>
          <block v-for="(item, index) in list" :key="index">
            <listitem :item="item" :index="index"></listitem>
          </block>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Top from "../components/theTop.vue";
import Listitem from "../components/listitem.vue";
import { inject, reactive } from "vue"; //Caution
import { useRoute } from "vue-router";

export default {
  name: "theSearch",
  components: { Listitem, Top },
  setup() {
    history.pushState(null, null, document.URL);
    const axios = inject("axios");
    let url = "/search";

    const route = useRoute();
    let category = route.query.category;
    let keyword = route.query.keyword;

    let list = reactive([]);

    axios
      .get(url, {
        params: {
          category: category,
          keyword: keyword,
        },
      })
      .then((response) => {
        for (let i = 0; i < response.data.length; i++) {
          var temp = {
            userid:"",
            id: "",
            title: "",
            isbn: "",
            author: "",
            desc: "",
            pprice: "",
            chense: "",
            time: "",
            titlepic: "",
          };
          temp.userid=response.data[i].userId;
          temp.id = response.data[i].id;
          temp.title = response.data[i].bookname;
          temp.isbn = response.data[i].iSBN;
          if (!response.data[i].author) {
            temp.author = "未知作者";
          } else {
            temp.author = response.data[i].author;
          }
          if (!response.data[i].publisher) {
            temp.desc = "未知出版社";
          } else {
            temp.desc = response.data[i].publisher;
          }
          temp.pprice = String(response.data[i].price);
          if (response.data[i].used == "0") {
            temp.chense = "全新";
          } else if (response.data[i].used == "1") {
            temp.chense = "较新";
          } else if (response.data[i].used == "2") {
            temp.chense = "普通";
          } else if (response.data[i].used == "3") {
            temp.chense = "可以接受";
          }
          if (!response.data[i].date) {
            temp.time = "未知时间";
          } else {
            temp.time = response.data[i].date;
          }
          let root = 'http://124.70.58.159/'
          temp.titlepic = root+response.data[i].photoPath+'1.jpg';
          list.push(temp);
        }
      })
      .catch(function (error) {
        console.log(error);
      });

    return {
      list,
    };
  },
  // data() {
  //   return {
  //     list: [
  //       {
  //         title: "毛主席文集",
  //         titlepic: require("../assets/photo/p1.jpg"),
  //         desc: "xxxxxxx出版社",
  //         pprice: 100,
  //         author: "某某普.阿丽莎拉四零四零零零零",
  //         chense: "7分",
  //         time: "2012.3.6",
  //         good_num: "信用很好",
  //       },
  //       {
  //         title: "语文",
  //         titlepic: require("../assets/photo/p2.jpg"),
  //         desc: "xxxxxxx出版社",
  //         pprice: 100,
  //         author: "某某普.mmmmmmm",
  //         chense: "7分",
  //         time: "2012.3.6",
  //         good_num: "信用很好",
  //       },
  //       {
  //         title: "数学",
  //         titlepic: require("../assets/photo/p3.jpg"),
  //         desc: "七年级/初中生爱看",
  //         pprice: 100,
  //         author: "某某普.mmmmmmm",
  //         chense: "7分",
  //         time: "2012.3.6",
  //         good_num: "信用很好",
  //       },
  //       {
  //         title: "英语",
  //         titlepic: require("../assets/photo/p3.jpg"),
  //         desc: "xxxxxxxxxx出版社",
  //         pprice: 100,
  //         author: "某某普.mmmmmmm",
  //         chense: "7分",
  //         time: "2012.3.6",
  //         good_num: "信用很好",
  //       },
  //     ],
  //   };
  // },
};
</script>

<style>
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

