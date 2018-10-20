/*角色列表,作者:高振中,日期:2018-08-10 09:21:48*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="名称">
        <el-input placeholder="请输入名称" size="small" v-model="form.name"/>
      </el-form-item>
      <el-form-item label="备注">
        <el-input placeholder="请输入备注" size="small" v-model="form.remark"/>
      </el-form-item>
      <el-form-item>
        <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>
        <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%" border>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>
      <el-table-column label="操作" width="300">
        <template slot-scope="props">
          <div>
            <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" @click="doDelete(props.row)">删除</el-button>
            <el-button type="text" @click="doChangeFunc(props.row)">功能权限</el-button>
            <el-button type="text" @click="doOpenDeptTree(props.row)">数据授权</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <br/>
    <div style="text-align: right" v-if="total > 0">
      <el-pagination :total="total" @size-change="(size) => { this.size = size; this.refresh();}" @current-change="(curr) => {this.page = curr ; this.refresh();}"
                     :current-page.sync="page" :page-sizes="[10,15,20,25,30]" :page-size="size" layout="total, sizes, prev, pager, next, jumper"></el-pagination>
    </div>
    <AdminRoleDialog ref="dialog" :refresh="refresh"/>
    <FunList ref="funcList"></FunList>
    <DeptTree ref="deptTree"></DeptTree>
  </div>
</template>
<script>
  import AdminRoleDialog from './AdminRoleDialog.vue';
  import FunList from './FunList.vue';
  import DeptTree from './DeptTree.vue';

  export default {
    data: function () {
      return {
        total: 0,
        page: 1,
        size: 20,
        dataList: [],
        form: {
          id: null,//主键
          name: null,//名称
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
        const requestData = {...that.form, page: that.page - 1, size: that.size};
        that.$http.post("/api/adminRole/queryPage", JSON.stringify(requestData)).then(res => {
          that.loading = false;
          that.dataList = res.data.content;
          that.total = res.data.totalElements;
        }).catch(res => {
          that.$message.error("获取角色列表失败：" + res);
          that.loading = false;
        });
      },
      doAdd() {
        this.$refs["dialog"].addDialog();
      },
      doEdit(row) {
        this.$refs["dialog"].editDialog(row);
      },
      doDelete(row) {
        const that = this;
        this.$confirm('你确定要删除吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          that.$http.delete("/api/adminRole/delete", {
            params: {ids: [row.id]}
          }).then(res => {
            this.$message.success("删除成功");
            that.refresh();
          }).catch(res => {
            that.$message.error("删除失败：" + res);
          });
        }).catch(() => {
        });
      },
      doChangeFunc(row) {
        this.$refs["funcList"].addDialog(row);
      },
      doOpenDeptTree(row) {
        this.$refs["deptTree"].addDialog(row);
      }
    },
    components: {AdminRoleDialog, FunList, DeptTree}
  }
</script>
<style></style>
