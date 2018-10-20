/*类型字典新增与修改,作者:唐植超,日期:2018-10-20 11:58:00*/
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
              that.$http.post("/api/dict/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                  }
      },
      addDialog() {//新增
        this.title = "新增类型字典";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改类型字典";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>