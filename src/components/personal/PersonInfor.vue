<template>
  <el-descriptions
    class="margin-top"
    title="个人信息"
    :column="1"
    style="width: 60%"
    size="large"
  >
    <template #extra>
      <el-button type="primary" @click="dialogFormVisible = true"
        >修改信息</el-button
      >
    </template>
    <el-descriptions-item class="item" label="一卡通号">{{
      information.userId
    }}</el-descriptions-item>
    <el-descriptions-item class="item" label="昵称">{{
      information.username
    }}</el-descriptions-item>
    <el-descriptions-item class="item" label="信用">{{
      information.credit
    }}</el-descriptions-item>
  </el-descriptions>

  <el-dialog v-model="dialogFormVisible" title="修改昵称" @close="handleClose">
    <el-form ref="formRef" :model="ruleForm" :rules="rules" label-width="70px">
      <el-form-item label="昵称" prop="name">
        <el-input v-model="ruleForm.name" class="change-form"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="changeName">修改</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { reactive } from "@vue/reactivity";
import { inject } from "vue";



export default {
  name: "PersonInfor",
  setup() {
    let information = reactive({
      userId: "",
      username: "",
      credit: "",
    });
    let url = "/methods/personinfo";
    const axios = inject("axios");
    axios
      .get(url)
      .then((response) => {
        console.log(response.data);
        if (response.data) {
          information.userId = response.data.userId;
          information.username = response.data.username;
          if (response.data.credit < 2) {
            information.credit = "良好";
          } else information.credit = "较差";
        }
      })
      .catch(function (error) {
        console.log(error);
      });

    return {
      information,
    };
  },
  data() {
    return {
      dialogFormVisible: false,
      ruleForm: {
        name: "",
      },
      rules: {
        name: [
          {
            required: true,
            message: "请输入新昵称",
            trigger: "blur",
          },
          {
            min: 1,
            max: 50,
            message: "不超过10个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    changeName() {
      this.$refs.formRef.validate(async (valid) => {
        if (!valid) {
          console.log(this.ruleForm);
          return;
        } else {
          console.log(this.ruleForm);
          let url = "/methods/username";
          this.axios
            .post(url, this.ruleForm)
            .then((response) => {
              console.log(response.data);
              if (response.data) {
                this.$message.success("修改成功");
                this.$router.go(0);
              } else this.$message.success("修改失败");
            })
            .catch(function (error) {
              console.log(error);
            });
          this.addDialogVisible = false;
          this.information.name = this.ruleForm.name;
          this.dialogFormVisible = false;
        }
      });
    },
    handleClose() {
      this.$refs.formRef.resetFields();
    },
  },
};
</script>

<style scoped>
.margin-top {
  font-size: 40px;
}
</style>