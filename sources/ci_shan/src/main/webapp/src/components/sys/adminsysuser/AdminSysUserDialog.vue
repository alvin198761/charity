/*用户新增与修改,作者:高振中,日期:2018-08-10 09:21:48*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false" width="800px">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="姓名" prop="name">
            <el-input placeholder="请输入姓名" v-model="form.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="手机号" prop="phone">
            <el-input placeholder="请输入手机号" v-model="form.phone" maxlength="11"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="部门" prop="dept_name">
            <el-popover ref="deptPopover" v-model="showSearchBox" placement="right" title="请选择部门" width="400" trigger="click" @show="setPopoverDeptId">
              <AdminDeptTreeDialog  ref="PopoverDialog" :selectDept="selectDept" ></AdminDeptTreeDialog>
            </el-popover>
            <el-input placeholder="请输入部门" v-model="form.dept_name" readonly auto-complete="off" v-popover:deptPopover  ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="gender">
            <el-select v-model="form.gender"  placeholder="选择性别" transfer style="width:100%">
              <el-option v-for="item in genders" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="微信号" prop="wechat">
            <el-input placeholder="请输入微信号" v-model="form.wechat"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态" prop="status">
            <el-switch v-model="form.status" active-color="#13ce66" inactive-color="#ff4949" :active-value="1" :inactive-value="0" inactive-text="禁用" active-text="启用">
            </el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" placeholder="请输入备注" v-model="form.remark"></el-input>
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
  import {GENDER} from '../../common/util.js';
  import AdminDeptTreeDialog from './AdminDeptTreeDialog';

  export default {
    props: ["refresh"],
    data() {
      return {
        title: '',
        form: this.initForm(),
        showSearchBox: false,
        dialogMode: "save",
        show: false,
        rules: {
          name: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
            {min: 2, max: 8, message: '姓名长度2到8个字符', trigger: 'blur'},
          ],
          department_id: [
            {required: true, message: '请选择部门', trigger: 'blur'},
          ],
          phone: [
            {required: true, message: '请输入手机号', trigger: 'blur'},
            {min: 11, max: 11, message: '手机号长度不正确', trigger: 'blur'},
          ],
        }
      }
    },
    computed: {
      genders() {
        return GENDER;
      }
    },
    methods: {
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          that.$http.post("/api/adminSysUser/validatePhone", JSON.stringify({user_id: that.form.user_id, phone_eq: that.form.phone})).then(res => {
            if (res.data.success === true) {
              that.$http.post("/api/adminSysUser/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
                that.show = false;
                that.$message.success(that.title + "成功!");
                that.refresh();
              });
            } else {
              if (res.data.code === 1) {
                that.$message.error("手机号码不能重复!");
              }
            }
          });
        });
      },
      initForm() {//初始数据
        return {
          user_id: null,//主键
          name: null,//姓名
          code: null,//会员编号
          phone: null,//手机号
          gender: 1,//性别
          status: 1,//状态
          department_id: null,//部门ID
          wechat: null,//微信号
          remark: null,//备注
          dept_name: null//部门名称 查询
        }
      },
      addDialog() {//新增
        this.title = "新增用户 默认密码[888888]";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改用户";
        this.dialogMode = "update";
        this.form = {...row};
        this.form.headName = row.dept_name;
        this.show = true;
      },
      selectDept(row) {//选中
        this.showSearchBox = false;
        this.form = {...this.form, department_id: row.dept_id, dept_name: row.name};
      },
      setPopoverDeptId(){//树上回显选中
        this.$refs['PopoverDialog'].setDeptId(this.form.department_id);
      }
    },
    components: {AdminDeptTreeDialog}
  }
</script>
<style></style>
