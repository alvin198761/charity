<template>
    <div id="app">
        <el-container>
            <el-header style="text-align: center">
                <el-row>
                    <el-col :span="2"><a href="/#/">系统图标</a></el-col>
                    <el-col :span="20">
                        <el-menu default-active="/event" mode="horizontal" router
                                 background-color="#545c64"
                                 text-color="#fff"
                                 active-text-color="#ffd04b">
                            <el-menu-item index="/event">活动记录</el-menu-item>
                            <el-menu-item index="/categorylist">物资管理</el-menu-item>
                            <el-menu-item index="/charitylist">行善方管理</el-menu-item>
                            <el-menu-item index="/difflist">受善方管理</el-menu-item>
                            <el-submenu index="fields">
                                <template slot="title">字段设置</template>
                                <el-menu-item index="/settings/event_type">活动类型</el-menu-item>
                                <el-menu-item index="/settings/res_type">物资类型</el-menu-item>
                                <el-menu-item index="/settings/charity_type">行善方类型</el-menu-item>
                                <el-menu-item index="/settings/diff_type">受善方类型</el-menu-item>
                            </el-submenu>
                            <el-submenu index="permission">
                                <template slot="title">系统权限</template>
                                <el-menu-item index="/settings/adminDept">部门管理</el-menu-item>
                                <el-menu-item index="/settings/adminMenu">菜单管理</el-menu-item>
                                <el-menu-item index="/settings/adminRole">角色管理</el-menu-item>
                                <el-menu-item index="/settings/adminSysUser">用户管理</el-menu-item>
                            </el-submenu>
                            <el-menu-item index="/center">个人中心</el-menu-item>
                        </el-menu>
                    </el-col>
                    <el-col :span="2">
                        <a href="/j_spring_security_logout">退出登录</a>
                    </el-col>
                </el-row>


            </el-header>
            <el-main style="min-height: 800px">
                <router-view></router-view>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        data(){
            return {
                timer: null
            };
        },
        created(){
            const that = this;
            this.timer = setInterval(() => that.$http.get("/api/heartbeat"), 2 * 60 * 1000);
        },
        destroyed(){
            clearInterval(this.timer);
        }
    }
</script>


<style lang="less">
    html, body, #app {
        width: 100%;
        height: 100%;
        margin: 0px;
    }

    .el-header, .el-footer, .el-header a, .el-footer a {
        background-color: #545c64;
        color: #fff;
        text-align: center;
        line-height: 60px;
        text-decoration: none;
    }

    /*.el-aside {*/
    /*!*background-color: #D3DCE6;*!*/
    /*color: #333;*/
    /*text-align: center;*/
    /*line-height: 200px;*/
    /*}*/

    /*.el-main {*/
    /*background-color: #E9EEF3;*/
    /*color: #333;*/
    /*text-align: center;*/
    /*line-height: 160px;*/
    /*}*/

    /*body > .el-container {*/
    /*margin-bottom: 40px;*/
    /*}*/

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .el-table td {
        padding: 2px 0 !important;
    }

    input, button, .el-card, .el-message, .el-alert, textarea, .el-dialog, .el-select-dropdown, .el-tag, .el-radio-button:first-child .el-radio-button__inner, .el-radio-button:last-child .el-radio-button__inner, .el-popover, .el-message-box {
        border-radius: 0px !important;
    }

    .el-form-item__content, .el-form-item__label {
        /*line-height: 30px;*/
    }

    .el-input__inner, .el-button, .el-cascader {
        /*height: 30px;*/
        line-height: 30px;
        font-size: 12px !important;

    }

    .el-button {
        padding: 0 20px;
    }

    .el-button--text {
        padding-right: 0px;
        padding-left: 0px;
    }

    .el-tree-node__content {
        height: 30px;
    }

    /*下拉展示样式*/
    .el-table__expanded-cell .el-form {
        margin: 20px 0px 10px 58px;

    }

    /*    调整间距*/
    .el-form .el-row .el-col .el-form-item {
        margin-bottom: 15px;
    }

    /*调整验证距离*/
    .el-form-item__error {
        padding-top: 1px !important
    }
</style>
