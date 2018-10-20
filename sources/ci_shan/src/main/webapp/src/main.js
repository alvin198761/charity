import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import NProgress from 'nprogress'//页面顶部进度条
import installFilter from './components/common/vue-filters';
import installPlugins from './components/common/vue-plugins';

Vue.use(ElementUI);
installFilter(Vue);
installPlugins(Vue);

Vue.config.productionTip = true;

router.beforeEach((to, from, next) => {
    NProgress.start();
    next()
})

router.afterEach(transition => {
    NProgress.done();
});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
