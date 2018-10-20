<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false" width="500px">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-form-item label="上级部门">{{form.parent_name}}</el-form-item>
      <el-form-item label="部门名称" prop="name">
        <el-input placeholder="请输入部门名称" v-model="form.name"/>
      </el-form-item>
      <el-form-item label="可用标志">
        <el-switch v-model="form.enable" active-color="#13ce66" inactive-color="#ff4949" :active-value="1" :inactive-value="0" inactive-text="禁用" active-text="启用">
        </el-switch>
      </el-form-item>
      <el-form-item label="备注">
        <el-input placeholder="请输入备注" v-model="form.remark" type="textarea"/>
      </el-form-item>
    </el-form>
    <div slot="footer" style="text-align: right">
      <el-button type="primary" @click="save()">确定</el-button>
      <el-button @click="show = false">取消</el-button>
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
            {required: true, message: '请输入部门名称', trigger: 'blur'},
            {min: 2, max: 10, message: '部门名称2到10个字符', trigger: 'blur'},
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
          that.$http.post("/api/adminDept/validate", JSON.stringify({dept_id: that.form.dept_id, name: that.form.name})).then(res => {
            if (res.data.success === true) {
              that.$http.post("/api/adminDept/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
          dept_id: null,//主键
          parent_id: null,//上级ID
          name: null,//部门名称
          enable: 1,//可用标志
          remark: null,//备注
          parent_name: null,
        }
      },
      addDialog(data) {//新增
        this.title = "新增部门";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.form.parent_id = data.dept_id;//将点击的这条数据的ID作为子菜单的父ID
        this.show = true;
        this.form.parent_name = data.name;
      },
      editDialog(row) {//修改
        this.title = "修改部门";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
        this.form.parent_name = row.parent_name;
      },
    },
    components: {}
  }
</script>
<style></style>
