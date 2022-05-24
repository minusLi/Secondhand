<template>
  <div style="height: 5vh; font-size: 18px">未完成订单</div>
  <el-card class="box-card" shadow="never">
    <el-table :data="myorders" height="250" style="width: 100%" :fit="true">
      <el-table-column prop="name" label="书名" />
      <!-- <el-table-column prop="ISBN" label="ISBN" />
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="publish" label="出版社" />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="used" label="成色" /> -->
      <el-table-column prop="seller" label="卖家" />
      <el-table-column prop="tel" label="联系方式" /> -->
      <el-table-column label="操作">
        <template v-slot="scope">
          <!-- 确认按钮 -->
          <el-button
            type="primary"
            v-model="scope.row.id"
            size="small"
            circle
            @click="confirmOrder(scope.row.id)"
            ><el-icon><circle-check /></el-icon>
          </el-button>
          <!-- 删除按钮 -->
          <el-button
            type="danger"
            size="small"
            circle
            @click="removeOrder(scope.row.id)"
            ><el-icon><delete /></el-icon
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
import { CircleCheck, Delete } from "@element-plus/icons-vue";
import { useCookies } from "vue3-cookies";
import { inject, reactive } from "@vue/runtime-core";

export default {
  name: "MyOrder",
  components: {
    CircleCheck,
    Delete,
  },
  setup() {
    let myorders = reactive([]);
    const { cookies } = useCookies();
    const axios = inject("axios");
    let token = cookies.get("token");
    let jwt = require("jsonwebtoken");
    let userid = jwt.decode(token).aud[0];
    let url = "/methods/myorder";

    axios
      .get(url, {
        params: {
          userid: userid,
        },
      })
      .then((response) => {
        for (let i = 0; i < response.data.length; i++) {
          var temp = {
            id: "",
            name: "",
            seller: "",
            tel: ""
          };
          temp.id = response.data[i].oid;
          temp.name = response.data[i].bookname;
          temp.seller = response.data[i].seller;
          temp.tel = response.data[i].tel;
          myorders.push(temp);
        }
      })
      .catch(function (error) {
        console.log(error);
      });

    return {
      myorders,
    };
  },
  data() {},
  methods: {
    async confirmOrder(id) {
      let url = "/methods/completeorder";
      const confirmResult = await this.$confirm("是否确认交易完成?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).catch((err) => err);
      if (confirmResult !== "confirm") {
        return this.$message.info("已取消确认");
      } else {
        this.axios
          .get(url, {
            params: {
              oid: id,
            },
          })
          .then((response) => {
            if (response.data == true) {
              this.$message.success("成功确认");
              this.$router.go(0);
            } else {
              this.$message.warning("确认失败");
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },

    async removeOrder(id) {
      let url = "/methods/deleteorder";
      const confirmResult = await this.$confirm("是否撤销预约订单?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).catch((err) => err);
      if (confirmResult !== "confirm") {
        return this.$message.info("已取消撤销");
      } else {
        this.axios
          .get(url, {
            params: {
              oid: id,
            },
          })
          .then((response) => {
            if (response.data == true) {
              this.$message.success("成功撤销预约订单");
              this.$router.go(0);
            } else {
              this.$message.warning("撤销预约订单失败");
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
  },
};
</script>