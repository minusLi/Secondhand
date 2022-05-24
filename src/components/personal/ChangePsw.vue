<template>
  <el-form
    ref="formRef"
    :model="ruleForm"
    :rules="rules"
    label-width="90px"
    size="large"
  >
    <el-form-item label="原密码：" prop="password">
      <el-input
        v-model="ruleForm.password"
        type="password"
        class="change-form"
      ></el-input>
    </el-form-item>
    <el-form-item label="新密码：" prop="newpsw">
      <el-input
        v-model="ruleForm.newpsw"
        type="password"
        class="change-form"
      ></el-input>
    </el-form-item>
    <el-form-item label="确认密码：" prop="confirm">
      <el-input
        v-model="ruleForm.confirm"
        type="password"
        class="change-form"
      ></el-input>
    </el-form-item>
    <div>
      <el-button type="primary" class="sub-btn" @click="change"
        >确认更改</el-button
      >
    </div>
  </el-form>
</template>

<script>
import { reactive, ref } from "vue";
import { inject } from "vue"; //Caution

export default {
  name: "ChangePsw",
  setup() {
    const ruleForm = reactive({
      password: "",
      newpsw: "",
      confirm: "",
    });

    const formRef = ref();
    const axios = inject("axios"); //Caution

    const change = async () => {
      await formRef.value.validate(async (valid) => {
        if (!valid) {
          return;
        } else {
          console.log(ruleForm);
          let url = "/methods/password";
          axios
            .post(url, ruleForm)
            .then((response) => {
              console.log(response.data);
              if (response.data) {
                alert("修改密码成功!");
              } else alert("修改密码失败，请检查原密码!");
            })
            .catch(function (error) {
              console.log(error);
            });
        }
      });
    };

    const validatePassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入原密码"));
      } else {
        const regName = /^[a-zA-Z]\w{5,15}$/;
        if (regName.test(value)) {
          callback();
        }
        callback(new Error("密码以字母开头且长度在6-16个字符之间"));
      }
    };

    const validateNewpsw = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入新密码"));
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
        callback(new Error("请再次输入新密码"));
      } else if (value === ruleForm.newpsw) {
        callback();
      } else {
        callback(new Error("两次输入的密码不一致"));
      }
    };

    const rules = reactive({
      password: [{ validator: validatePassword, trigger: "blur" }],
      newpsw: [{ validator: validateNewpsw, trigger: "blur" }],
      confirm: [{ validator: validateConfirm, trigger: "blur" }],
    });

    return {
      ruleForm,
      rules,
      change,
      formRef,
    };
  },
};
</script>

<style scoped>
.change-form {
  width: 80%;
  padding: 4px 4px;
  margin: 2px 0;
  display: inline-block;
  box-sizing: border-box;
}
</style>