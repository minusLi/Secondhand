<template>
  <div id="app">
    <div class="main">
      <Contact @set-contact="set" />
      <Dialog :contact="contact" :msgList="msgList" />
    </div>
  </div>
</template>

<script>
import Contact from "../components/Contact.vue";
import Dialog from "../components/Dialog.vue";

export default {
  name: "Chat",
  components: {
    Dialog,
    Contact,
  },
  data() {
    return {
      contact: null,
      msgList: [],
    };
  },

  methods: {
    // 点击指定用户后，就获取两人之间的所有信息
    // 并将当前联系人保存在 localStorage
    set(user) {
      this.contact = user;
      request({
        method: "post",
        url: "/pullMsg",
        params: {
          from: JSON.parse(localStorage.getItem("user")).id,
          to: this.contact.id,
        },
      })
        .then((res) => {
          this.msgList = res.data.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
#app {
  width: 100%;
  height: 100%;
  background-size: cover;
  background-image: url("../assets/photo/chat-bg.jpg");
}

.main {
  width: 1080px;
  height: 648px;
  margin-top: 72px;
  margin-left: auto;
  margin-right: auto;
  border-radius: 5px;
  background-color: #efeded;
  border: #d0d0d0 1px solid;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
</style>