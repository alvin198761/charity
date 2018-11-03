/*慈善方新增与修改,作者:唐植超,日期:2018-10-20 11:58:00*/
<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col :span="12">
                    <el-form-item label='类型' prop='type'>
                        <el-select size="small" v-model="form.type" placeholder="类型" style="width: 100%">
                            <el-option
                                    v-for="item in typeList"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='名称' prop='charity_name'>
                        <el-input placeholder='机构/个人名称' size="small" v-model='form.charity_name'></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='联系电话' prop='phone_no'>
                        <el-input placeholder='联系电话' size="small" v-model='form.phone_no'></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='个人性别' prop='gender'>
                        <el-select size="small" v-model="form.gender" placeholder="个人性别" style="width: 100%">
                            <el-option label="男" value="男"></el-option>
                            <el-option label="女" value="女"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="24">
                    <el-form-item label='联系地址' prop='address'>
                        <el-input placeholder='联系地址' size="small" v-model='form.address'></el-input>
                    </el-form-item>
                </el-col>
                <el-col>
                    <el-form-item label='备注' prop='remark'>
                        <el-input placeholder='备注' type="textarea" size="small" v-model='form.remark'></el-input>
                    </el-form-item>
                </el-col>
                </el-col>
            </el-row>
        </el-form>
        <div slot="footer" style="text-align: right">
            <el-button @click="show = false">取消</el-button>
            <el-button type="primary" @click="save()">确定</el-button>
        </div>
    </el-dialog>
</template>
<script>
    export default {
        props: ["refresh", "type", "titleName", "category"],
        data() {
            return {
                title: '',
                form: this.initForm(),
                dialogMode: "save",
                show: false,
                rules: {
                    type: [
                        {required: true, message: '请选择类型', trigger: 'blur'},
                    ],
                    charity_name: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                    ],
                    phone_no: [
                        {required: true, message: '请输入联系方式', trigger: 'blur'},
                    ]
                },
                deptLists: [],
                typeList: []
            }
        },
        created(){
            this.loadTypes(this.type);
        },
        methods: {
            loadTypes(type){
                const that = this;
                that.$http.post("/api/dict/queryList", JSON.stringify({
                    type: type
                })).then(res => {
                    that.typeList = res.data;
                }).catch(re => {
                    that.$message.error("获取类型：" + res);
                });
            },
            save() {//新增及修改记录
                const that = this;
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                    that.form = {... that.form, category: that.category};
                    that.$http.post("/api/charity/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
                        that.show = false;
                        that.$message.success(that.title + "成功!");
                        that.refresh();
                    }).catch(res => {
                        that.$message.error(that.title + "出错!" + res);
                    });
                });
            },
            initForm() {//初始数据
                return {
                    id: null,// 主键
                    type: null,// type
                    charity_name: null,// charity_name
                    phone_no: null,// phone_no
                    gender: null,// gender
                    address: null,// address
                    create_date: null,// create_date
                    author: null,// author
                    remark: null,// remark
                    p_id: 0,// p_id
                    category: null,// category
                    status: null,// status
                }
            },
            addDialog() {//新增
                this.title = "新增" + this.titleName;
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改" + this.titleName;
                this.dialogMode = "update";
                this.form = {...row};
                this.show = true;
            },
        },
        components: {}
    }
</script>
<style></style>