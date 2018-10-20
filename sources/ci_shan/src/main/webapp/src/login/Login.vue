<template>
    <el-card style="width: 500px;margin-right: auto ; margin-left: auto;margin-top: 300px">
        <div slot="header">
            慈善管理系统登录
        </div>
        <el-form :model="form" status-icon :rules="rules" ref="loginForm" label-width="100px">
            <el-form-item label="用户名" prop="j_username">
                <el-input v-model="form.j_username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="j_password">
                <el-input type="password" v-model="form.j_password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('loginForm')" style="width: 100%">登录</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>
<script>
    import querystring  from 'querystring';
    import crypto from 'crypto';
    export default{
        data() {
            return {
                form: {
                    j_password: '',
                    j_username: ''
                },
                rules: {
                    j_password: [
                        {required: true, message: '请输密码！', trigger: 'blur'}
                    ],
                    j_username: [
                        {required: true, message: '请输用户名！', trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const that = this;
                this.$refs[formName].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                    that.$http.post("/j_spring_security_check", {
                        params: {...that.form ,j_password: $.md5(that.form.j_password)}
                    }).then(res => {
                        console.log(res.data);
                    }).catch(err => {
                        console.log(err)
                    });
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>
<style>

</style>
