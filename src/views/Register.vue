<template>
    <div id="background"></div>
    <div id="login-box">
        <div class="autologo">
            <img src="https://newids.seu.edu.cn/authserver/custom/images/logodz.png" alt="logo">
            <p class="title1" style="font-size: 30px;font-family: Arial, Helvetica, sans-serif;color: slategray;text-align: center;">
                二手书交易平台
            </p>
        </div>
        <resForm/>
    </div>
</template>

<script>
import resForm from "./resForm.vue"

export default {
    name: 'Register',
    components: {
        resForm
    }
}

</script>

<style scoped>
    @media screen and (min-width: 840px) {
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
    }

    div {
        padding: 20px;
    }
</style>

