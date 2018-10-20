/*角色新增与修改,作者:高振中,日期:2018-08-10 09:21:48*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false" width="500px">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="名称" prop="name">
            <el-input placeholder="请输入名称" v-model="form.name"/>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input placeholder="请输入备注" v-model="form.remark" type="textarea"/>
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
            {required: true, message: '请输入名称', trigger: 'blur'},
            {min: 2, max: 10, message: '名称长度为2到10个字符', trigger: 'blur'},
          ]
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
          that.$http.post("/api/adminRole/validateName", JSON.stringify({id: that.form.id, name: that.form.name})).then(res => {
            if (res.data.success === true) {
              that.$http.post("/api/adminRole/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
                that.show = false;
                that.$message.success(that.title + "成功!");
                that.refresh();
              });
            } else {
              if (res.data.code === 1) {
                that.$message.error(res.data.errorMsg);
              }
            }
          });
        });
      },
      initForm() {//初始数据
        return {
          id: null,//主键
          name: null,//名称
          remark: null,//备注
        }
      },
      addDialog() {//新增
        this.title = "新增角色";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改角色";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>
