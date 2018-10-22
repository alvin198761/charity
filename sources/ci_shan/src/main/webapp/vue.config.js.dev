var path = require('path');

const Config = require('webpack-chain');
const config = new Config();
function resolve (dir) {
    return path.join(__dirname, dir)
}
module.exports = {
    baseUrl:'/',
    indexPath:path.resolve(__dirname, '../resources/templates/index.ftl'),
    outputDir: path.resolve(__dirname, '../resources/static'),
    configureWebpack: {
        //引入外部依赖，排除包
         // externals: {
              //     vue: 'Vue',
              //     vuex:"Vuex",
              //     "vue-router":"VueRouter",
              //     /*'element-ui': 'ELEMENT',*/
              //     'iview': 'iview',
              //     jquery: 'jQuery',
              //     "echarts": "echarts",
              //     "moment": "moment",
              //     "vue-echarts": "VueECharts",
              //     "axios":"axios",
              //     "less":"less"
              //     // "querystring":"querystring"
              // }
    },

    //去掉打包js里边的source map文件
    productionSourceMap: false,
    chainWebpack:()=>{
    },
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8000',//开发
        // ws: true,
        changeOrigin: true,
        secure: false
      }
    }
  },
   pages: {
          index: {
              // page 的入口
              entry: "src/main.js",
              // 模板来源
              template: "public/index.html",
              // 在 dist/index.html 的输出
              filename: "index.html",
              // 当使用 title 选项时，
              // template 中的 title 标签需要是 <%= htmlWebpackPlugin.options.title %>
              title: "慈善管理系统",
              // 在这个页面中包含的块，默认情况下会包含
              // 提取出来的通用 chunk 和 vendor chunk。
              chunks: ["chunk-vendors", "chunk-common", "index"]
          },
          login: {
              // page 的入口
              entry: "src/login/main.js",
              // 模板来源
              template: "public/login.html",
              // 在 dist/index.html 的输出
              filename: "login.html",
              // 当使用 title 选项时，
              // template 中的 title 标签需要是 <%= htmlWebpackPlugin.options.title %>
              title: "慈善管理系统登录",
              // 在这个页面中包含的块，默认情况下会包含
              // 提取出来的通用 chunk 和 vendor chunk。
              chunks: ["chunk-vendors", "chunk-common", "login"]
          }
      },
    pluginOptions:{
/*        new webpack.optimize.CommonsChunkPlugin('common.js'),
        new webpack.ProvidePlugin({
            jQuery: "jquery",
            $: "jquery"
        })*/
    }
/*    configureWebpack:{
        plugins:[
            new MyAwesomeWebpackPlugin()
            /!*            new webpack.optimize.CommonsChunkPlugin('common.js'),
                        new webpack.ProvidePlugin({
                            jQuery: "jquery",
                            $: "jquery"
                        })*!/
        ]
    },*/
}
