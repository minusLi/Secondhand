<template>
  <div class="model" v-show="showModel">
    <div class="mask">123</div>
    <div class="model-dialog">
      <div class="model-header">
        <span>提示</span>
        <a href="javascript:;" class="icon-close"  @click="$emit('cancel')"></a>
      </div>
      <div class="model-body">
        <div class="body">确定要购买吗？</div>
      </div>
      <div class="model-footer">
        <button class="btn" @click="buy">确认</button>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "model",
  props: {
    //是否展示弹框
    showModel: Boolean,
  },
  methods:{
      buy(){
         this.$emit('cancel');
      }
  }
};
</script>


<style>
.mask {
  position: fixed;
  top: 0;
  height: 100%;
  width: 100%;
  
  opacity: 0.5;
  z-index: 9;
}
.model-dialog {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  border-radius: 4px;
  width: 400px;
  height: 270px;
  border: 1px solid #f5f5f5;
  overflow: hidden;
  z-index: 10;
}
.model-header {
  position: relative;
  height: 45px;
  padding-left: 10px;
  padding-top: 10px;
  font-size: 20px;
  line-height: 50px;
  background-color: #f5f5f5;
  border-bottom: 1px solid rgb(177, 176, 176);
}
.model-body {
  height: 130px;
  line-height: 130px;
  font-size: 28px;
  text-align: center;
  background-color: #fff;
}
.model-footer {
  background-color: #f5f5f5;
  height: 80px;
  text-align: center;
  line-height: 100px;
   border-top: 1px solid rgb(177, 176, 176);
}
.btn {
  width: 100px;
  height: 40px;
  border-radius: 4px;
  background-color: rgb(229, 70, 70);
  color: #fff;
  font-size: 15px;
  border: none;
}
.icon-close {
  position: absolute;
  background-color: pink;
  right: 15px;
  top: 16px;
  width: 30px;
  height: 30px;
  z-index: 10;
 background:url("https://img2.baidu.com/it/u=895574383,4180866369&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500") no-repeat;
  background-size: contain;
}
</style>