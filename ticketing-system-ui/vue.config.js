// vue.config.js 
module.exports = {
  devServer: {
    port: 3000,
    proxy: {
      '/ticketing-system': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true
      }
    }
  }
}