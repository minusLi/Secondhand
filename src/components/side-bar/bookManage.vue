<template>
  <div style="height: 5vh; font-size: 18px">图书审核</div>
  <el-card class="box-card" shadow="never">
    <el-table :data="tableData" height="250" style="width: 100%" :fit="true">
      <el-table-column prop="name" label="书名" />
      <el-table-column prop="ISBN" label="ISBN" />
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="publisher" label="出版社" />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="used" label="成色" />
      <el-table-column label="操作">
        <template v-slot="scope">
          <!-- 删除按钮 -->
          <el-button
            type="danger"
            size="small"
            circle
            @click="deleteBook(scope.row.id)"
            ><el-icon><delete /></el-icon
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
import { Delete } from "@element-plus/icons-vue";
import { ref, reactive, inject } from "vue";
import { useCookies } from "vue3-cookies";

export default {
  name: "BookManage",
  components: {
    Delete,
  },
  setup() {
    let tableData = reactive([]);
    const axios = inject("axios");
    let url = "/methods/allbook";
    axios
      .get(url)
      .then((response) => {
        for (let i = 0; i < response.data.length; i++) {
          var temp = {
            id: "",
            name: "",
            ISBN: "",
            author: "",
            publisher: "",
            price: "",
            used: "",
          };
          temp.id = response.data[i].id;
          temp.name = response.data[i].bookname;
          temp.ISBN = response.data[i].iSBN;
          if (response.data[i].author == "") {
            temp.author = "未知作者";
          } else {
            temp.author = response.data[i].author;
          }
          if (response.data[i].publisher == "") {
            temp.publisher = "未知出版社";
          } else {
            temp.publisher = response.data[i].publisher;
          }
          temp.price = String(response.data[i].price);
          if (response.data[i].used == "0") {
            temp.used = "全新";
          } else if (response.data[i].used == "1") {
            temp.used = "较新";
          } else if (response.data[i].used == "2") {
            temp.used = "普通";
          } else if (response.data[i].used == "3") {
            temp.used = "可以接受";
          }
          tableData.push(temp);
        }
      })
      .catch(function (error) {
        console.log(error);
      });

    return {
      tableData,
    };
  },
  methods: {
    async deleteBook(id) {
      let url = "/methods/deletebook";
      const confirmResult = await this.$confirm("是否删除违规书籍?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).catch((err) => err);
      if (confirmResult !== "confirm") {
        return this.$message.info("已取消删除");
      } else {
        this.axios
          .get(url, {
            params: {
              bookid: id,
            },
          })
          .then((response) => {
            if (response.data == true) {
              this.$message.success("删除成功");
              this.$router.go(0);
            } else {
              this.$message.warning("删除失败");
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