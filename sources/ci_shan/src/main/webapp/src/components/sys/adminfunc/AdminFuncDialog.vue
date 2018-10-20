/*菜单新增与修改,作者:高振中,日期:2018-08-10 09:21:48*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false" width="800px">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-col :span="12">
        <el-form-item label="名称" prop="name">
          <el-input placeholder="请输入名称" v-model="form.name"/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="地址">
          <el-input placeholder="请输入地址" v-model="form.url"/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="状态" prop="status">
          <el-switch v-model="form.status" active-color="#13ce66" inactive-color="#ff4949" :active-value="1" :inactive-value="0" inactive-text="禁用" active-text="启用">
          </el-switch>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="图标" prop="icon">
          <el-input placeholder="请输入图标" v-model="form.icon"/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="提示信息" prop="tip">
          <el-input placeholder="请输入提示信息" v-model="form.tip"/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="排序编号" prop="order_num">
          <el-input placeholder="请输入排序编号" v-model="form.order_num"/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option :value="1" label="菜单"></el-option>
            <el-option :value="2" label="按钮"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="颜色">
          <el-input placeholder="请输入颜色" v-model="form.color"/>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item label="备注">
          <el-input placeholder="请输入备注" v-model="form.remark" type="textarea"/>
        </el-form-item>
      </el-col>
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
          // color: [
          //   {required: true, message: '请输入颜色', trigger: 'blur'},
          // ],
          icon: [
            {required: true, message: '请输入图标', trigger: 'blur'},
          ],
          name: [
            {required: true, message: '请输入名称', trigger: 'blur'},
            {min: 2, max: 10, message: '名称长度为2到10个字符', trigger: 'blur'},
          ],
          tip: [
            {required: true, message: '请输入提示信息', trigger: 'blur'},
            {min: 2, max: 10, message: '提示信息为2到10个字符', trigger: 'blur'},
          ],
          status: [
            {required: true, message: '请输入状态', trigger: 'blur'},
          ],
          // url: [
          //   {required: true, message: '请输入地址', trigger: 'blur'},
          // ],
          order_num: [
            {required: true, message: '请输入排序编号', trigger: 'blur'},
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
          that.$http.post("/api/adminFunc/validate", JSON.stringify({func_id: that.form.func_id, name: that.form.name})).then(res => {
            if (res.data.success === true) {
              that.$http.post("/api/adminFunc/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
          func_id: null,//主键
          type: 1,//类型
          color: null,//颜色
          icon: null,//图标
          name: null,//名称
          parent_id: null,//父结点ID
          tip: null,//提示信息
          remark: null,//备注
          status: 1,//状态
          url: null,//地址
          order_num: null,//排序编号
        }
      },
      addDialog(data) {//新增
        this.title = "新增菜单";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.form.parent_id = data;
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改菜单";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>
