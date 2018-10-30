<template>
    <el-card style="width: 500px;margin-right: auto ; margin-left: auto;margin-top: 300px">
        <div slot="header">
            慈善管理系统登录
        </div>
        <el-form action="/j_spring_security_check" method="post" :model="form" status-icon :rules="rules"
                 ref="loginForm" id="loginForm" label-width="100px">
            <el-form-item label="用户名" prop="j_username">
                <el-input v-model="form.j_username" name="j_username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="j_password">
                <el-input type="password" v-model="form.j_password" name="j_password" id="j_password"
                          autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('loginForm')" style="width: 100%">登录</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>
<script>
    import axios from 'axios';
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
                    $.ajax({
                        type: "POST",
                        url: "/api/loginValidate",
                        dataType: "json",
                        data: {
                            j_username: that.form.j_username,
                            j_password: $.md5(that.form.j_password),

                        },
                        success: function (data) {
                            if (data.success) {
                                document.getElementById("j_password").value = $.md5(document.getElementById("j_password").value);
                                document.getElementById("loginForm").submit();
                                return;
                            }
                            for (let p in data.msgMap) {
                                that.$message.error(data.msgMap[p]);
                                break;
                            }
                        },
                        error: function (error) {
                            that.$message.error(error);
                        }
                    });

                })
            }
        }
    }
</script>
<style>

</style>
