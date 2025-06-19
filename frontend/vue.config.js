
module.exports = {

	productionSourceMap: true, // 确保为 true
	configureWebpack: {
	  devtool: 'source-map' // 显式指定 Source Map 类型
	},
    devServer: {
		proxy: {
			'/api':{
				target:'http://localhost:8080',//起同源作用的url 简单来说就是你要访问的服务器
				changeOrigin: true,
				pathRewrite: {
					'^/api': '' 
				}
			}
		},
		allowedHosts: "all",
		client: {
			overlay: false // 编译错误时，取消全屏覆盖（建议关掉）
		  }
	}
	
}