/*慈善方新增与修改,作者:唐植超,日期:2018-10-20 11:58:00*/
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
                                                            <el-form-item label='charity_name' prop='charity_name'>
                                    <el-input placeholder='请输入charity_name' v-model='form.charity_name'></el-input>
                                </el-form-item>
                                                            <el-form-item label='phone_no' prop='phone_no'>
                                    <el-input placeholder='请输入phone_no' v-model='form.phone_no'></el-input>
                                </el-form-item>
                                                            <el-form-item label='gender' prop='gender'>
                                    <el-input placeholder='请输入gender' v-model='form.gender'></el-input>
                                </el-form-item>
                                                            <el-form-item label='address' prop='address'>
                                    <el-input placeholder='请输入address' v-model='form.address'></el-input>
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
                                                            <el-form-item label='p_id' prop='p_id'>
                                    <el-input placeholder='请输入p_id' v-model='form.p_id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='category' prop='category'>
                                    <el-input placeholder='请输入category' v-model='form.category'></el-input>
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
                    charity_name :[
            {required: true, message: '请输入charity_name', trigger: 'blur'},
            {min: 1, max: 10, message: 'charity_name长度不正确}', trigger: 'blur'},
        ],
                    phone_no :[
            {required: true, message: '请输入phone_no', trigger: 'blur'},
            {min: 1, max: 10, message: 'phone_no长度不正确}', trigger: 'blur'},
        ],
                    gender :[
            {required: true, message: '请输入gender', trigger: 'blur'},
            {min: 1, max: 10, message: 'gender长度不正确}', trigger: 'blur'},
        ],
                    address :[
            {required: true, message: '请输入address', trigger: 'blur'},
            {min: 1, max: 10, message: 'address长度不正确}', trigger: 'blur'},
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
                    p_id :[
            {required: true, message: '请输入p_id', trigger: 'blur'},
            {min: 1, max: 10, message: 'p_id长度不正确}', trigger: 'blur'},
        ],
                    category :[
            {required: true, message: '请输入category', trigger: 'blur'},
            {min: 1, max: 10, message: 'category长度不正确}', trigger: 'blur'},
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
                        id : null,// 主键
                        type : null,// type
                        charity_name : null,// charity_name
                        phone_no : null,// phone_no
                        gender : null,// gender
                        address : null,// address
                        create_date : null,// create_date
                        author : null,// author
                        remark : null,// remark
                        p_id : null,// p_id
                        category : null,// category
                        status : null,// status
                  }
      },
      addDialog() {//新增
        this.title = "新增慈善方";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改慈善方";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>