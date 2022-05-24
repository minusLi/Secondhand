<template>
  <el-form ref="formRef" :model="ruleForm" :rules="rules" label-width="90px" size="large">
    <el-form-item label="一卡通号：" prop="username">
      <el-input v-model="ruleForm.username" class="register-form"></el-input>
    </el-form-item>
    <el-form-item label="密码：" prop="password">
      <el-input v-model="ruleForm.password" type="password" class="register-form"></el-input>
    </el-form-item>
    <el-form-item label="确认密码：" prop="confirm">
      <el-input v-model="ruleForm.confirm" type="password" class="register-form"></el-input>
    </el-form-item>
    <div>
      <el-button type="primary" class="sub-btn" @click="addUser" color="#309c1acc" :dark="isDark">注册并登录</el-button>
    </div>
  </el-form>
</template>

<script>
import { reactive, ref } from "vue";
import { inject } from "vue"; //Caution

export default {
  name: "resForm",
  setup() {
    const ruleForm = reactive({
      username: "",
      password: "",
      confirm: "",
    });

    const formRef = ref();
    const axios = inject("axios"); //Caution

    const addUser = async () => {
      await formRef.value.validate(async (valid) => {
        if (!valid) {
          return;
        } else {
          console.log(ruleForm);
          let url = "/register";
          axios
            .post(url, ruleForm)
            .then((response) => {
              console.log(response);
              if (response.data) {
                alert("注册成功!");
                this.$router.push({
                  path: "login",
                });
              }
              else alert("您已注册，请勿重复注册！");
            })
            .catch(function (error) {
              console.log(error);
            });
        }
      });
    };

    const validateName = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入一卡通号"));
      } else {
        const regName = /^[0-9]{9}$/;
        if (regName.test(value)) {
          callback();
        }
        callback(new Error("请输入正确的一卡通号"));
      }
    };

    const validatePassword = (rule, value, callback) => {
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

    const validateConfirm = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value === ruleForm.password) {
        callback();
      } else {
        callback(new Error("两次输入的密码不一致"));
      }
    };

    const rules = reactive({
      username: [{ validator: validateName, trigger: "blur" }],
      password: [{ validator: validatePassword, trigger: "blur" }],
      confirm: [{ validator: validateConfirm, trigger: "blur" }],
    });

    return {
      ruleForm,
      rules,
      addUser,
      formRef,
    };
  },
};
</script>

<style scoped>
.el-input :deep() .el-input__inner {
  width: 100%;
  padding: 14px 14px;
  margin: 2px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.sub-btn {
  width: 100%;
  padding: 14px 20px;
  margin: 2px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

div {
  padding: 7px;
}
</style>