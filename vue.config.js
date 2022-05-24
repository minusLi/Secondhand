module.exports = {
    devServer: {
        proxy: {
            "/api": {
                target: 'http://localhost:8081',      //后端接口的根目录
                changeOrigin: true,                    //是否跨域
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
    publicPath: process.env.NODE_ENV === "production" ? "/" : "/",
    outputDir: "dist", //是打包后所生成的目录名，可以自己定义
    indexPath: "index.html",
}