import Vue from "vue";
import Router from "vue-router";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import Login from './Login.vue';
import Home from '../views/Home.vue';
import installPlugins from '../components/common/vue-plugins';

Vue.use(ElementUI);
Vue.use(Router)
Vue.config.productionTip = true;
installPlugins(Vue);

const router = new Router({
    routes: [
        {
            path: '/',
            name: '登录',
            component: Login,
        }
    ]
})

new Vue({
    router,
    render: h => h(Home)
}).$mount('#app')
