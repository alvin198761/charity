/*用户列表,作者:高振中,日期:2018-08-10 09:21:48*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="姓名">
        <el-input placeholder="请输入姓名" size="small" v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="编号">
        <el-input placeholder="请输入编号" size="small" v-model="form.user_id_eq"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input placeholder="请输入手机号" size="small" v-model="form.phone" maxlength="11"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="form.gender" size="small" placeholder="选择性别" transfer>
          <el-option label="全部" :value="null"></el-option>
          <el-option v-for="item in genders" :key="item.id" :label="item.name" :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>
        <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%" border>
      <el-table-column prop="user_id" label="编号"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="phone" label="手机号"></el-table-column>
      <el-table-column prop="gender" label="性别">
        <template slot-scope="props"><span>{{props.row.gender | gender-filter}}</span></template>
      </el-table-column>
      <el-table-column prop="status" label="状态">
        <template slot-scope="props"><span>{{props.row.status | question_enable}}</span></template>
      </el-table-column>
      <el-table-column prop="dept_name" label="部门名称"></el-table-column>
      <el-table-column prop="wechat" label="微信号"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>
      <el-table-column label="操作" width="300">
        <template slot-scope="props">
          <div>
            <el-button :disabled="props.row.user_id ==1"type="text" @click="doEdit(props.row)">编辑</el-button>
            <el-button :disabled="props.row.user_id ==1"type="text" @click="doRole(props.row)">关联角色</el-button>
            <el-button :disabled="props.row.user_id ==1"type="text" @click="doDelete(props.row)">删除</el-button>
            <el-button :disabled="props.row.user_id ==1"type="text" @click="openDialog(props.row)">重置密码</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <br/>
    <div style="text-align: right" v-if="total > 0">
        <el-pagination
          @size-change="(size) => { this.size = size; this.refresh();}"
          @current-change="(curr) => {this.page = curr ; this.refresh();}"
          :current-page.sync="page"
          :page-sizes="[10,15,20,25,30]"
          :page-size="size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
    </div>
    <AdminSysUserDialog ref="dialog" :refresh="refresh"></AdminSysUserDialog>
    <RoleList ref="RoleList"></RoleList>
    <el-dialog ref="passDialog" title="重置密码" :visible.sync="isShow" :close-on-click-modal="false" :close-on-press-escape="false" width="500px">
      <el-form :model="form" ref="form" :rules="rules" label-width="100px">
        <el-form-item label="密码" prop="password">
          <el-input placeholder="请输入密码" v-model="form.password" maxlength="20" style="width:75%"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" style="text-align: right">
        <el-button @click="isShow = false">取消</el-button>
        <el-button type="primary" @click="changePassword()">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import AdminSysUserDialog from './AdminSysUserDialog.vue';
  import RoleList from './RoleList.vue';
  import {GENDER} from '../../common/util.js';

  export default {
    data: function () {
      return {
        total: 0,
        page: 1,
        size: 20,
        dataList: [],
        isShow:false,
        user_id:null,//用于密码重置
        form: {
          user_id_eq: null,//主键
          name: null,//姓名
          phone: null,//手机号
          gender: null,//性别
          status: null,//状态
          department_id: null,//部门ID
          wechat: null,//微信号
          remark: null,//备注
          dept_name: null,//部门名称
          password:null,//密码
          user_id:null//用于密码重置
        },
        rules: {
          password: [
            {required: true, message: '请输入重置密码', trigger: 'blur'},
            {min: 6, max: 20, message: '姓名长度6到20个字符', trigger: 'blur'},
          ]
        },
        loading: false
      }
    },
    computed: {
      genders() {
        return GENDER;
      }
    },
    created: function () {
      this.refresh();
    },
    methods: {
      refresh() {
        const that = this;
        that.loading = true;
        const requestData = {...that.form, page: that.page - 1, size: that.size};
        that.$http.post("/api/adminSysUser/queryPage", JSON.stringify(requestData)).then(res => {
          that.loading = false;
          that.dataList = res.data.content;
          that.total = res.data.totalElements;
        }).catch(res => {
          that.$message.error("获取用户列表失败：" + res);
          that.loading = false;
        });
      },
      doAdd() {
        this.$refs["dialog"].addDialog();
      },
      doEdit(row) {
        this.$refs["dialog"].editDialog(row);
      },
      doRole(row) {
        this.$refs["RoleList"].showList(row);
      },
      doDelete(row) {
        const that = this;
        this.$confirm('你确定要删除吗?', '提示', {
          confirmbuttontext: '确定',
          cancelbuttontext: '取消',
          type: 'warning'
        }).then(() => {
          that.$http.delete("/api/adminsysuser/delete", {
            params: {ids: [row.user_id]}
          }).then(res => {
            this.$message.success("删除成功");
            that.refresh();
          }).catch(res => {
            that.$message.error("删除失败：" + res);
          });
        }).catch(() => {
        });
      },
      openDialog(row){
        this.isShow = true;
        this.user_id = row.user_id;
      },
      changePassword(){
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          that.$http.post("/api/adminSysUser/updatePassword", JSON.stringify({...that.form,user_id:this.user_id})).then(res => {
            that.form.password = null;
            that.isShow = false;
            this.$message.success("重置密码成功!");
            that.refresh();
          });
        });
      }
    },
    components: {AdminSysUserDialog, RoleList}
  }
</script>
<style></style>
