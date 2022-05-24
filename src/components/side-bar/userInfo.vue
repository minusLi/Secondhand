<template>
  <div style="height: 5vh; font-size: 18px">用户信息</div>
  <el-card class="box-card" shadow="never">
    <el-table :data="tableData" height="250" style="width: 100%">
      <el-table-column prop="cardNumber" label="一卡通号" />
      <el-table-column prop="username" label="用户昵称" />
      <el-table-column prop="password" label="密码" />
      <el-table-column label="操作">
        <template v-slot="scope">
          <!-- 修改按钮 -->
          <el-button
            type="primary"
            v-model="scope.row.cardNumber"
            size="small"
            circle
            @click="showChangeDialog(scope.row.cardNumber)"
            ><el-icon><edit /></el-icon
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>

  <el-dialog
    v-model="dialogChangeVisible"
    title="修改密码"
    @close="handleClose"
  >
    <el-form ref="formRef" :model="ruleForm" :rules="rules" label-width="70px">
      <el-form-item label="新密码" prop="password">
        <el-input
          v-model="ruleForm.password"
          type="password"
          class="change-form"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogChangeVisible = false">取消</el-button>
        <el-button type="primary" @click="changePassword">修改</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { Edit } from "@element-plus/icons-vue";
import { inject, reactive } from "@vue/runtime-core";
import { ref } from "vue";

export default {
  name: "UserInfo",
  components: {
    Edit,
  },
  setup() {
    let tableData = reactive([]);
    const axios = inject("axios");
    let url = "/methods/alluser";
    axios
      .get(url)
      .then((response) => {
        for (let i = 0; i < response.data.length; i++) {
          var temp = {
            cardNumber: "",
            username: "",
            password: "",
          };
          temp.cardNumber = response.data[i].id;
          temp.username = response.data[i].username;
          temp.password = response.data[i].password;
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
  data() {
    var checkPassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        const regName = /^[a-zA-Z]\w{5,15}$/;
        if (regName.test(value)) {
          callback();
        }
        callback(new Error("密码需以字母开头且长度在6-16个字符之间"));
      }
    };

    return {
      dialogChangeVisible: false,
      ruleForm: {
        password: "",
        id: "",
      },
      rules: {
        password: [
          {
            required: true,
            message: "请输入新密码",
            trigger: "blur",
          },
          {
            validator: checkPassword,
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    async showChangeDialog(cardNumber) {
      this.ruleForm.id = cardNumber; //需要修改的用户
      this.dialogChangeVisible = true;
    },

    changePassword() {
      this.$refs.formRef.validate(async (valid) => {
        if (!valid) {
          return;
        } else {
          let url = "/methods/adminchange";
          this.axios
            .post(url, this.ruleForm)
            .then((response) => {
              if (response.data == true) {
                alert("修改密码成功!");
                this.$router.go(0);
              } else alert("修改密码失败!");
            })
            .catch(function (error) {
              console.log(error);
            });
          this.dialogChangeVisible = false;
        }
      });
    },

    handleClose() {
      this.$refs.formRef.resetFields();
    },
  },
};
</script>