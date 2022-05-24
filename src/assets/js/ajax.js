import axios from "axios";

const MyHttpServer = {}

MyHttpServer.install = (Vue) => {
  axios.defaults.baseURL = ''
  Vue.config.globalProperties.$axios = axios
}

export default MyHttpServer