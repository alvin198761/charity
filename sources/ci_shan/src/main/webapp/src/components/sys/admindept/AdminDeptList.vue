<template>
  <div>
    <el-button type="primary" @click="refresh">刷新</el-button>
    <hr/>
    <div style="height: 500px ; overflow: auto ; width: 450px  ">
      <el-tree :data="menus" node-key="id" default-expand-all :expand-on-click-node="false" v-loading="loading" element-loading-text="正在加载......">
        <span class="custom-tree-node" slot-scope="{ node,data}">{{ data.name}}
          <span style="margin-left: 30px">
            <el-button type="text" size="mini" @click="() => doAdd(data)">添加子部门</el-button>
            <el-button type="text" size="mini" @click="() => doEdit(node, data)">修改</el-button>
            <el-button type="text" size="mini" :disabled="data.parent_id ==0" @click="() => doDelete(node, data)">删除</el-button>
          </span>
        </span>
      </el-tree>
    </div>
    <hr/>
    <AdminDeptDialog ref="dialog" :refresh="refresh"></AdminDeptDialog>
  </div>
</template>
<script>
  import AdminDeptDialog from './AdminDeptDialog.vue';

  export default {
    data: function () {
      return {
        menus: [],//菜单
        ids: [],
        form: {
          dept_id: null,//主键
          branch_id: null,//门店主键
          parent_id: null,//上级ID
          name: null,//部门名称
          enable: null,//可用标志
          remark: null,//备注
        },
        loading: false
      }
    },
    computed: {},
    created: function () {
      this.refresh();
    },
    methods: {
      refresh() {
        const that = this;
        that.loading = true;
        that.$http.post("/api/adminDept/getTree").then(res => {
          that.loading = false;
          that.menus = res.data;
          that.total = res.data.totalElements;
        }).catch(res => {
          that.$message.error("获取部门数据失败：" + res);
          that.loading = false;
        });
      },
      doAdd(data) {
        this.$refs["dialog"].addDialog(data);
      },
      doEdit(node, row) {
        console.log(node)
        this.$refs["dialog"].editDialog(row);
      },
      doDelete(node, data) {
        const that = this;
        if (data.children.length > 0) {
          that.$message.error("请先删除子结点!");
          return;
        }
        this.$confirm('你确定要删除吗?', '提示', {
          confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning'
        }).then(() => {
          that.$http.delete("/api/adminDept/delete", {params: {ids: [data.dept_id]}}).then(res => {
            this.$message.success("删除成功");
            that.refresh();
          }).catch(res => {
            that.$message.error("删除失败：" + res);
          });
        });
      },
    },
    components: {AdminDeptDialog}
  }
</script>
<style></style>


