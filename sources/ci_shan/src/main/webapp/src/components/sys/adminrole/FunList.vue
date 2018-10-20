/*角色与菜单关联 功能权限,作者:wlp,日期:2018-08-14 09:21:38*/
<template>
  <el-dialog title="功能权限" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false" width="500px">
    <p>当前角色：{{this.role_name}}</p>
    <div style="height: 500px ; overflow: auto">
      <el-tree :data="dataList" show-checkbox node-key="func_id" ref="tree" default-expand-all :expand-on-click-node="false" @check="changeSelect">
        <span class="custom-tree-node" slot-scope="{ node,data}">{{ data.name}}</span>
      </el-tree>
    </div>
    <div slot="footer" style="text-align: right">
      <el-button @click="show = false">取消</el-button>
      <el-button type="primary" @click="save()">确定</el-button>
    </div>
  </el-dialog>
</template>
<script>
  export default {
    data() {
      return {
        func_ids: [],//菜单IDS
        role_name: null,//角色名称
        role_id: null,//角色ID
        dataList: [],
        show: false,
        rules: {}
      }
    },
    methods: {
      refresh(role_id) {
        const that = this;
        const requestData = {...that.form, page: that.page - 1, size: that.size};
        that.$http.post("/api/adminFunc/getTree").then(res => {
          that.dataList = res.data;
          that.$http.post("/api/adminRoleFunc/queryList", {role_id: role_id}).then(res => {
            //根据当期role_id 查出对应的菜单列表
            this.$refs.tree.setCheckedKeys(res.data.map(item => item.user_id));
          });
        });
      },
      save() {//新增及修改记录
        const that = this;
        let funs = [];
        funs =  funs.concat(this.$refs["tree"].getHalfCheckedKeys());
        funs =  funs.concat(this.$refs["tree"].getCheckedKeys());
        console.log(funs)
        const list = funs.map(item => {
          return {role_id: that.role_id, user_id: item}
        });
        that.$http.post("/api/adminRoleFunc/insertBatch?role_id=" + that.role_id, list).then(res => {
          this.show = false;
          this.$message.success("授权成功");
        });
      },
      addDialog(row) {//新增
        this.role_id = row.id;
        this.role_name = row.name;
        this.show = true;
        this.refresh(row.id)
      },
      changeSelect(data, node) {
//        this.func_ids = node.checkedNodes.map(item => item.func_id)
      },
    },
  }
</script>
<style></style>
