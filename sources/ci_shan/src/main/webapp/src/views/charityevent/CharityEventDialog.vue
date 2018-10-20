/*善行活动新增与修改,作者:唐植超,日期:2018-10-20 11:58:00*/
<template>
      <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
            <el-form :common="form" ref="form" :rules="rules" label-width="100px">
                  <el-row>
                        <el-col>
                                                            <el-form-item label='主键' prop='id'>
                                    <el-input placeholder='请输入主键' v-model='form.id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='type' prop='type'>
                                    <el-input placeholder='请输入type' v-model='form.type'></el-input>
                                </el-form-item>
                                                            <el-form-item label='name' prop='name'>
                                    <el-input placeholder='请输入name' v-model='form.name'></el-input>
                                </el-form-item>
                                                            <el-form-item label='content' prop='content'>
                                    <el-input placeholder='请输入content' v-model='form.content'></el-input>
                                </el-form-item>
                                                            <el-form-item label='chairman_id' prop='chairman_id'>
                                    <el-input placeholder='请输入chairman_id' v-model='form.chairman_id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='target_id' prop='target_id'>
                                    <el-input placeholder='请输入target_id' v-model='form.target_id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='event_time' prop='event_time'>
                                    <el-input placeholder='请输入event_time' v-model='form.event_time'></el-input>
                                </el-form-item>
                                                            <el-form-item label='author' prop='author'>
                                    <el-input placeholder='请输入author' v-model='form.author'></el-input>
                                </el-form-item>
                                                            <el-form-item label='remark' prop='remark'>
                                    <el-input placeholder='请输入remark' v-model='form.remark'></el-input>
                                </el-form-item>
                                                            <el-form-item label='chairmain_pnone' prop='chairmain_pnone'>
                                    <el-input placeholder='请输入chairmain_pnone' v-model='form.chairmain_pnone'></el-input>
                                </el-form-item>
                                                            <el-form-item label='target_phone' prop='target_phone'>
                                    <el-input placeholder='请输入target_phone' v-model='form.target_phone'></el-input>
                                </el-form-item>
                                                            <el-form-item label='status' prop='status'>
                                    <el-input placeholder='请输入status' v-model='form.status'></el-input>
                                </el-form-item>
                                                            <el-form-item label='create_time' prop='create_time'>
                                    <el-input placeholder='请输入create_time' v-model='form.create_time'></el-input>
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
                    id :[
            {required: true, message: '请输入主键', trigger: 'blur'},
            {min: 1, max: 10, message: '主键长度不正确}', trigger: 'blur'},
        ],
                    type :[
            {required: true, message: '请输入type', trigger: 'blur'},
            {min: 1, max: 10, message: 'type长度不正确}', trigger: 'blur'},
        ],
                    name :[
            {required: true, message: '请输入name', trigger: 'blur'},
            {min: 1, max: 10, message: 'name长度不正确}', trigger: 'blur'},
        ],
                    content :[
            {required: true, message: '请输入content', trigger: 'blur'},
            {min: 1, max: 10, message: 'content长度不正确}', trigger: 'blur'},
        ],
                    chairman_id :[
            {required: true, message: '请输入chairman_id', trigger: 'blur'},
            {min: 1, max: 10, message: 'chairman_id长度不正确}', trigger: 'blur'},
        ],
                    target_id :[
            {required: true, message: '请输入target_id', trigger: 'blur'},
            {min: 1, max: 10, message: 'target_id长度不正确}', trigger: 'blur'},
        ],
                    event_time :[
            {required: true, message: '请输入event_time', trigger: 'blur'},
            {min: 1, max: 10, message: 'event_time长度不正确}', trigger: 'blur'},
        ],
                    author :[
            {required: true, message: '请输入author', trigger: 'blur'},
            {min: 1, max: 10, message: 'author长度不正确}', trigger: 'blur'},
        ],
                    remark :[
            {required: true, message: '请输入remark', trigger: 'blur'},
            {min: 1, max: 10, message: 'remark长度不正确}', trigger: 'blur'},
        ],
                    chairmain_pnone :[
            {required: true, message: '请输入chairmain_pnone', trigger: 'blur'},
            {min: 1, max: 10, message: 'chairmain_pnone长度不正确}', trigger: 'blur'},
        ],
                    target_phone :[
            {required: true, message: '请输入target_phone', trigger: 'blur'},
            {min: 1, max: 10, message: 'target_phone长度不正确}', trigger: 'blur'},
        ],
                    status :[
            {required: true, message: '请输入status', trigger: 'blur'},
            {min: 1, max: 10, message: 'status长度不正确}', trigger: 'blur'},
        ],
                    create_time :[
            {required: true, message: '请输入create_time', trigger: 'blur'},
            {min: 1, max: 10, message: 'create_time长度不正确}', trigger: 'blur'},
        ],
                }
      }
    },
    methods: {
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
              if (!valid) {
                return;
              }
              that.$http.post("/api/charityEvent/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                        id : null,// 主键
                        type : null,// type
                        name : null,// name
                        content : null,// content
                        chairman_id : null,// chairman_id
                        target_id : null,// target_id
                        event_time : null,// event_time
                        author : null,// author
                        remark : null,// remark
                        chairmain_pnone : null,// chairmain_pnone
                        target_phone : null,// target_phone
                        status : null,// status
                        create_time : null,// create_time
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
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>