/*善行活动新增与修改,作者:唐植超,日期:2018-10-20 11:58:00*/
<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col>
                    <el-form-item label='活动名称' prop='name'>
                        <el-input placeholder='活动名称' size="small" v-model='form.name'></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='活动类型' prop='type'>
                        <el-select size="small" v-model='form.type' placeholder="活动类型" style="width: 100%">
                            <el-option
                                    label="捐款"
                                    :value="1">
                            </el-option>
                            <el-option
                                    label="物资"
                                    :value="2">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='活动时间' prop='event_time'>
                        <el-date-picker style="width: 100%"
                                size="small" v-model='form.event_time'>
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='主导人/机构' prop='chairman_id'>
                        <el-input placeholder='主导人/机构'  size="small" v-model='form.chairman_id'></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='主导人电话' prop='chairmain_pnone'>
                        <el-input placeholder='主导人电话'  size="small" v-model='form.chairmain_pnone'></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='目标人/机构' prop='target_id'>
                        <el-input placeholder='目标人/机构' size="small" v-model='form.target_id'></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='目标电话' prop='target_phone'>
                        <el-input placeholder='目标电话' size="small" v-model='form.target_phone'></el-input>
                    </el-form-item>
                </el-col>
                <el-col >
                <el-form-item label='活动内容' prop='content'>
                    <el-input placeholder='活动内容' type="textarea"  size="small" v-model='form.content'></el-input>
                </el-form-item>
                </el-col>
                <el-col >
                <el-form-item label='活动备注' prop='remark'>
                    <el-input placeholder='活动备注'   type="textarea"  size="small" v-model='form.remark'></el-input>
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
                    name: [
                        {required: true, message: '请输入活动名称', trigger: 'blur'},
                    ],
                    type: [
                        {required: true, message: '请选择活动类型', trigger: 'blur'},
                    ],
                    event_time: [
                        {required: true,  message: '请选择活动时间', trigger: 'blur'},
                    ],
                    content: [
                        {required: true, message: '请输入活动内容', trigger: 'blur'},
                    ],
                    chairman_id: [
                        {required: true,  message: '请选择主导人', trigger: 'blur'},
                    ],
                    chairmain_pnone: [
                        {required: true, message: '请输入主导人手机', trigger: 'blur'},
                    ],
                    target_id: [
                        {required: true,  message: '请选择目标人', trigger: 'blur'},
                    ],
                    target_phone: [
                        {required: true, message: '请输入目标手机', trigger: 'blur'},
                    ],

                }
            }
        },
        methods: {
            save() {//新增及修改记录
                const that = this;
                this.$refs['form'].validate((valid) => {
                    if ( !valid ){
                    return;
                        }
                        that.$http.post("/api/charityEvent/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
                            that.show = false;
                        that.$message.success(that.title + "成功!");
                        that.refresh();
                    }). catch(res => {
                        that.$message.error(that.title + "出错!" + res);
                    })  ;
                  }) ;
            },
            initForm() {//初始数据
                return {
                    id: null,// 主键
                    type: null,// type
                    name: null,// name
                    content: null,// content
                    chairman_id: null,// chairman_id
                    target_id: null,// target_id
                    event_time: null,// event_time
                    author: null,// author
                    remark: null,// remark
                    chairmain_pnone: null,// chairmain_pnone
                    target_phone: null,// target_phone
                    status: null,// status
                    create_time: null,// create_time
                }
            },
            addDialog() {//新增
                this.title = "新增善行活动";
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改善行活动";
                this.dialogMode = "update";
                this.form = {...                row            }                ;
                this.show = true;
            },
        },
        components: {}
    }
</script>
<style></style>