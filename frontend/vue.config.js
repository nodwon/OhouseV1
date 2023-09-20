const { defineConfig } = require('@vue/cli-service')
module.exports = {
  outputDir: "../src/main/resources/static",
  devServer: {
    port: 8083,
    proxy: {
      '/example': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
};