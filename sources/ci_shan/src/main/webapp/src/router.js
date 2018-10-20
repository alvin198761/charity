import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

import AdminSysUser from './components/sys/adminsysuser/AdminSysUserList.vue';
import AdminDept from './components/sys/admindept/AdminDeptList.vue';
import AdminRole from './components/sys/adminrole/AdminRoleList.vue';
import AdminFunc from './components/sys/adminfunc/AdminFuncList.vue';

import CharityEventList from './views/charityevent/CharityEventList.vue';
import CategoryList from './views/category/CategoryList.vue';
import CharityList from './views/charity/CharityList.vue';
import DictList from './views/dict/DictList.vue';

Vue.use(Router)


export default new Router({
    routes: [
        {
            path: '/',
            component: Home,
            name: '首页',
            redirect: '/event',
            children: [
                {path: 'adminSysUser', component: AdminSysUser, name: '用户管理',},
                {path: 'adminDept', component: AdminDept, name: '部门管理',},
                {path: 'adminRole', component: AdminRole, name: '角色管理',},
                {path: 'adminMenu', component: AdminFunc, name: '菜单管理',},
                //
                {path: 'event', component: CharityEventList, name: '活动记录',},
                {path: 'categorylist', component: CategoryList, name: '物资管理',},
                {path: 'charitylist', component: CharityList, name: '行善方管理',},
                {path: 'dictlist', component: DictList, name: '类型管理',}

            ]
        }
    ]
})
