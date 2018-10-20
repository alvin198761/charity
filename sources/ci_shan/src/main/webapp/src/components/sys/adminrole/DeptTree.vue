/*角色与部门关联 功能权限,作者:wlp,日期:2018-08-14 15:21:38*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false" width="500px">
    <p>当前角色：{{this.role_name}}</p>
    <div style="height: 500px ; overflow: auto">
      <el-tree :data="dataList" show-checkbox node-key="dept_id" ref="tree" default-expand-all :expand-on-click-node="false" @check="changeTree">
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
        dept_ids: [],//部门IDS
        role_name: null,//角色名称
        role_id: null,//角色ID
        dataList: [],
        title: '',
        show: false,
      }
    },
    created: function () {
    },
    methods: {
      refresh(role_id) {
        const that = this;
        that.$http.post("/api/adminDept/getTree").then(res => {
          that.dataList = res.data;
          that.$http.post("api/adminRoleDept/queryList", {role_id: role_id}).then(res => {
            //根据当期role_id 查出对应的部门列表
            this.$refs.tree.setCheckedKeys(res.data.map(item => item.dept_id));
          });
        }).catch(res => {
          that.$message.error("获取菜单列表失败：" + res);
        });
      },
      save() {//新增及修改记录
        const that = this;
        const list = [];
        this.dept_ids.map(item => list.push({role_id: that.role_id, dept_id: item}));
        that.$http.post("/api/adminRoleDept/insertBatch?role_id=" + that.role_id, list).then(res => {
          this.show = false;
          this.$message.success("授权成功");
        });
      },
      addDialog(row) {//新增
        this.title = "数据权限";
        this.role_id = row.id;
        this.role_name = row.name;
        this.show = true;
        this.refresh(row.id)
      },
      changeTree(data, node) {
        this.dept_ids = node.checkedNodes.map(item => item.dept_id)
      },
    },
    components: {}
  }
</script>
<style></style>
