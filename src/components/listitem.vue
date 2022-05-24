<template>
  <div class="list-item" style="display: flex">
    <div class="img-box">
      <img
        :src="item.titlepic"
        alt="img"
        width="130"
        class="w-100"
        style="display: inline-block"
      />
    </div>
    <div class="inform-box">
      <div class="title-box">{{ item.title }}</div>
      <div class="inform-box2">
        <span class="other"
          >{{ item.author }}著/{{ item.desc }}/{{ item.time }}</span
        >
        <span class="other" style="float: right"
          >品相：<el-tag size="large" class="usedInfo" v-bind="pattern">
            {{ item.chense }}
          </el-tag></span
        >
      </div>
      <div class="price-box">
        <span>￥</span><span>{{ item.pprice }}</span>
      </div>

      <span class="inform-box3" style="display: inline-block"
        >ISBN:
        {{ item.isbn }}
      </span>
      <div style="float: right">
        <el-button
          class="btn"
          size="small"
          type="danger"
          plain
          @click="addOrder(item.id,myid)"
          >立刻购买</el-button
        >
        <el-button class="btn" size="small" type="danger" plain @click="detail"
          >查看详情</el-button
        >
      </div>
    </div>
  </div>
</template>
<script>
import { reactive } from "@vue/reactivity";
import { useRouter } from "vue-router";
import { useCookies } from "vue3-cookies";

export default {
  name: "listitem",
  props: {
    item: Object,
    index: Number,
  },
  setup(props) {
    let pattern = reactive({
      type: "info",
    });

    if (props.item.chense == "全新") {
      pattern.type = "success";
    } else if (props.item.chense == "较新") {
      pattern.type = "";
    } else if (props.item.chense == "普通") {
      pattern.type = "info";
    } else if (props.item.chense == "可以接受") {
      pattern.type = "danger";
    }

    const { cookies } = useCookies();
    let token = cookies.get("token");
    let jwt = require("jsonwebtoken");
    let myid = jwt.decode(token).aud[0];

    const router = useRouter();
    const detail = () => {
      router.push({
        path: "bookinfo",
        query: {
          bookid: props.item.id,
          userid: props.item.userid,
        },
      });
    };
    return {
      pattern,
      detail,
      myid
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
  },
};
</script>
<style>
.usedInfo {
  font-size: 2vh;
  font-weight: bolder;
}
.list-item {
  border-bottom-style: solid;
  border-width: thin;
  border-bottom-color: rgb(205, 202, 198);
}
.img-box {
  margin: 5px;
}
.inform-box {
  flex-direction: column;
  width: 85%;
}
.title-box {
  font-family: "微软雅黑";
  font-size: 20px;
  font-weight: 500;
  margin: 10px;
}
.inform-box2 {
  font-family: "微软雅黑";

  color: rgb(85, 81, 81);
  margin: 10px;
}
.inform-box3 {
  font-family: "微软雅黑";
  font-size: 15px;
  color: rgb(85, 81, 81);
  margin-left: 10px;
  margin-bottom: 10px;
}
.price-box {
  font-family: "微软雅黑";
  font-size: 20px;
  margin: 10px;
  color: #a63030;
}
.other {
  margin-right: 40px;
}
.btn {
  margin-bottom: 5px;
}
</style>