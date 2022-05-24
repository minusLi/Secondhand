<template>
  <div id="background"></div>
  <div id="login-box">
    <div class="autologo">
      <img
        src="https://newids.seu.edu.cn/authserver/custom/images/logodz.png"
        alt="logo"
      />
      <p
        class="title1"
        style="
          font-size: 30px;
          font-family: Arial, Helvetica, sans-serif;
          color: slategray;
          text-align: center;
        "
      >
        二手书交易平台
      </p>
    </div>
    <el-form
      ref="ruleform"
      :model="ruleForm"
      :rules="rules"
      label-width="90px"
      size="large"
    >
      <el-form-item label="一卡通号：" prop="username">
        <el-input v-model="ruleForm.username" class="register-form"></el-input>
      </el-form-item>
      <el-form-item label="密码：" prop="password">
        <el-input
          v-model="ruleForm.password"
          type="password"
          class="register-form"
        ></el-input>
      </el-form-item>

      <div>
        <el-button
          type="primary"
          class="sub-btn"
          @click="submitLogin"
          color="#309c1acc"
          :dark="isDark"
          >登录</el-button
        >
      </div>
    </el-form>

    <div class="forget">
      <el-row>
        <el-col :span="3">
          <el-button @click="open" color="#81bea3" :dark="isDark" plain
            >忘记密码</el-button
          >
        </el-col>
        <el-col :span="5"> </el-col>
        <el-col :span="3">
          <el-button @click="register" color="#81bea3" :dark="isDark" plain
            >注册账号</el-button
          >
        </el-col>
        <el-col :span="5"> </el-col>
        <el-col :span="3">
          <el-button
            @click="dialogFormVisible = true"
            color="#81bea3"
            :dark="isDark"
            plain
            >管理员登录</el-button
          >
        </el-col>
      </el-row>
    </div>
  </div>

  <el-dialog
    v-model="dialogFormVisible"
    title="管理员登录"
    @close="handleClose"
  >
    <el-form
      ref="adminRef"
      :model="adminForm"
      :rules="adminRule"
      label-width="70px"
    >
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="adminForm.password"
          type="password"
          class="add-form"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="admin">登录</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { reactive, ref } from "vue";
import { inject } from "vue"; //Caution
import { useRouter } from "vue-router";
import { ElMessage, ElMessageBox } from "element-plus";
import App from "../App.vue";

export default {
  components: { App },
  name: "Login",
  setup() {
    const ruleForm = reactive({
      username: "",
      password: "",
    });

    const adminForm = reactive({
      password: "",
    });

    const dialogFormVisible = ref(false);
    const router = useRouter();
    const ruleform = ref();
    const adminRef = ref();
    const axios = inject("axios"); //Caution

    const open = () => {
      ElMessageBox.alert("请与管理员联系", "忘记密码", {
        confirmButtonText: "确定",
      });
    };

    const register = () => {
      router.push("/register");
    };

    const admin = async () => {
      await adminRef.value.validate(async (valid) => {
        if (!valid) {
          return;
        } else {
          let url = "/adminlogin";
          axios
            .post(url, adminForm)
            .then((response) => {
              console.log(response);
              if (response.data) {
                alert("登录成功!");
                router.replace("/main");
              } else alert("密码错误！");
            })
            .catch(function (error) {
              console.log(error);
            });
        }
      });
    };

    const submitLogin = async () => {
      //提交表单
      await ruleform.value.validate(async (valid) => {
        if (!valid) {
          return;
        } else {
          //提交登录信息
          let url = "/login";
          axios
            .post(url, ruleForm)
            .then((response) => {
              console.log(response);
              if (response.data) {
                alert("登录成功!");
                router.replace("/main");
              } else alert("登录失败，请检查密码或先注册！");
            })
            .catch(function (error) {
              console.log(error);
            });
        }
      });
    };

    const validateName = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入正确的一卡通号"));
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
        callback(new Error("密码不可为空"));
      } else {
        callback();
      }
    };

    const rules = reactive({
      username: [{ validator: validateName, trigger: "blur" }],
      password: [{ validator: validatePassword, trigger: "blur" }],
    });

    const adminRule = reactive({
      password: [{ validator: validatePassword, trigger: "blur" }],
    });

    return {
      dialogFormVisible,
      ruleForm,
      rules,
      ruleform,
      submitLogin,
      open,
      register,
      admin,
      adminRef,
      adminForm,
      adminRule,
    };
  },
};
</script>

<style scoped>
@media screen {
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

  #login-box {
    background-color: rgba(255, 255, 255, 0.8);
    position: absolute;
    top: 50%;
    left: 50%;
    width: 450px;
    transform: translate(-50%, -50%);
    padding: 30px;
    border-radius: 20px;
    box-sizing: border-box;
  }

  #register {
    text-align: center;
  }

  #register a {
    font-weight: bold;
    color: #81bea3;
  }

  #main {
    text-align: center;
  }

  #main a {
    font-weight: bold;
    color: #81bea3;
  }
}

input,
select {
  width: 100%;
  padding: 14px 14px;
  margin: 2px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.el-input :deep() .el-input__inner {
  width: 100%;
  padding: 14px 14px;
  margin: 2px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.user-name {
  font-size: 15px;
}

.sub-btn {
  width: 100%;
  padding: 14px 20px;
  margin: 2px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.forget {
  text-align: center;
}

div {
  padding: 7px;
}
</style>
}