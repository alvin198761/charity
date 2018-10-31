/*捐助物资新增与修改,作者:唐植超,日期:2018-10-20 11:58:00*/
<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="类型">
                        <el-select size="small" v-model="form.type" placeholder="类型"  style="width: 100%">
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
                    <el-form-item label='名称' prop='name'>
                        <el-input placeholder='名称'  size="small"  v-model='form.name'></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='数量' prop='count'>
                        <el-input placeholder='数量'  size="small"  v-model='form.count'></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='捐助人' prop='c_id'>
                        <el-input placeholder='捐助人'  size="small"  v-model='form.c_id'></el-input>
                    </el-form-item>
                </el-col>
                <el-col>
                    <el-form-item label='备注' prop='remark'>
                        <el-input placeholder='备注'  size="small"  type="textarea" v-model='form.remark'></el-input>
                    </el-form-item>
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
        props: ["refresh"],
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
                    name: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                    ],
                    count: [
                        {required: true, message: '请输入数量', trigger: 'blur'},
                    ],
                    c_id: [
                        {required: true, message: '请选择捐助人', trigger: 'blur'},
                    ]
                },
                typeList: [],
            }
        },
        created: function () {
            this.loadTypes(2);
        },
        methods: {
            loadTypes(type){
                const that = this;
                that.$http.post("/api/dict/queryList",JSON.stringify({
                    type: type
                })).then(res => {
                    that.typeList = res.data;
                }).catch(re => {
                    that.$message.error("获取受善方类型：" + res);
                });
            },
            save() {//新增及修改记录
                const that = this;
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                    that.$http.post("/api/category/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                    name: null,// name
                    count: null,// count
                    create_date: null,// create_date
                    author: null,// author
                    remark: null,// remark
                    c_id: null,// c_id
                    status: null,// status
                }
            },
            addDialog() {//新增
                this.title = "新增捐助物资";
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改捐助物资";
                this.dialogMode = "update";
                this.form = {...row};
                this.show = true;
            },
        },
        components: {}
    }
</script>
<style></style>