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
import DiffList from './views/charity/DiffList.vue';
import EventTypeList from './views/dict/EventTypeList.vue';
import CharityTypeList from './views/dict/CharityTypeList.vue';
import DiffTypeList from './views/dict/DiffTypeList.vue';
import ResTypeList from './views/dict/ResTypeList.vue';

Vue.use(Router)


export default new Router({
    routes: [
        {
            path: '/',
            component: Home,
            name: '首页',
            redirect: '/event',
            children: [
                {
                    path: 'settings', component: Home, name: '设置',
                    children: [
                        {path: 'adminSysUser', component: AdminSysUser, name: '用户管理',},
                        {path: 'adminDept', component: AdminDept, name: '部门管理',},
                        {path: 'adminRole', component: AdminRole, name: '角色管理',},
                        {path: 'adminMenu', component: AdminFunc, name: '菜单管理',},

                        {path: 'event_type', component: EventTypeList, name: '活动类型',},
                        {path: 'res_type', component: ResTypeList, name: '资源类型',},
                        {path: 'charity_type', component: CharityTypeList, name: '行善方类型',},
                        {path: 'diff_type', component: DiffTypeList, name: '受善方类型',},
                    ]
                },
                //
                {path: 'event', component: CharityEventList, name: '活动记录',},
                {path: 'categorylist', component: CategoryList, name: '物资管理',},
                {path: 'charitylist', component: CharityList, name: '行善方管理',},
                {path: 'difflist', component: DiffList, name: '受善方管理',},


            ]
        }
    ]
})
