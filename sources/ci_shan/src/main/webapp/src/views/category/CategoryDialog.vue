/*捐助物资新增与修改,作者:唐植超,日期:2018-10-20 11:58:00*/
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
                                                            <el-form-item label='count' prop='count'>
                                    <el-input placeholder='请输入count' v-model='form.count'></el-input>
                                </el-form-item>
                                                            <el-form-item label='create_date' prop='create_date'>
                                    <el-input placeholder='请输入create_date' v-model='form.create_date'></el-input>
                                </el-form-item>
                                                            <el-form-item label='author' prop='author'>
                                    <el-input placeholder='请输入author' v-model='form.author'></el-input>
                                </el-form-item>
                                                            <el-form-item label='remark' prop='remark'>
                                    <el-input placeholder='请输入remark' v-model='form.remark'></el-input>
                                </el-form-item>
                                                            <el-form-item label='c_id' prop='c_id'>
                                    <el-input placeholder='请输入c_id' v-model='form.c_id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='status' prop='status'>
                                    <el-input placeholder='请输入status' v-model='form.status'></el-input>
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
                    count :[
            {required: true, message: '请输入count', trigger: 'blur'},
            {min: 1, max: 10, message: 'count长度不正确}', trigger: 'blur'},
        ],
                    create_date :[
            {required: true, message: '请输入create_date', trigger: 'blur'},
            {min: 1, max: 10, message: 'create_date长度不正确}', trigger: 'blur'},
        ],
                    author :[
            {required: true, message: '请输入author', trigger: 'blur'},
            {min: 1, max: 10, message: 'author长度不正确}', trigger: 'blur'},
        ],
                    remark :[
            {required: true, message: '请输入remark', trigger: 'blur'},
            {min: 1, max: 10, message: 'remark长度不正确}', trigger: 'blur'},
        ],
                    c_id :[
            {required: true, message: '请输入c_id', trigger: 'blur'},
            {min: 1, max: 10, message: 'c_id长度不正确}', trigger: 'blur'},
        ],
                    status :[
            {required: true, message: '请输入status', trigger: 'blur'},
            {min: 1, max: 10, message: 'status长度不正确}', trigger: 'blur'},
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
                        id : null,// 主键
                        type : null,// type
                        name : null,// name
                        count : null,// count
                        create_date : null,// create_date
                        author : null,// author
                        remark : null,// remark
                        c_id : null,// c_id
                        status : null,// status
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