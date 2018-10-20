/*角色列表,作者:高振中,日期:2018-08-10 09:21:48*/
<template>
  <el-dialog title="关联角色" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false" width="500px">
    <div>
      <el-table :data="dataList" style="width: 100%" @selection-change="onSelectChange" ref="multipleTable" border>
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="name" label="名称"></el-table-column>
        <el-table-column prop="remark" label="备注"></el-table-column>
      </el-table>
    </div>
    <div slot="footer" style="text-align: right">
      <el-button @click="show = false">取消</el-button>
      <el-button type="primary" @click="save">确定</el-button>
    </div>
  </el-dialog>
</template>
<script>
  export default {
    data: function () {
      return {
        dataList: [],
        show: false,
        roleIds: [],
        userId: null,
      }
    },
    computed: {},
    created: function () {
    },
    methods: {
      refresh(user_id) {
        const that = this;
        that.$http.post("/api/adminRole/queryList", {}).then(res => {
          that.dataList = res.data;
          that.$http.post("/api/adminUserRole/queryList", {user_id: user_id}).then(res => {
            res.data.map(item => {
              that.dataList.map(role => {
                if (role.id === item.role_id) {
                  this.$refs.multipleTable.toggleRowSelection(role, true);
                }
              })
            });
          });
        });
      },
      onSelectChange(data) {
        this.selectId = data.map(item => item.id);
      },
      save() {
        const that = this;
        const list = this.selectId.map(item => {
          return {user_id: that.userId, role_id: item}
        });
        that.$http.post("/api/adminUserRole/insertBatch?user_id=" + that.userId, list).then(res => {
          this.show = false;
          this.$message.success("授权成功");
        });
      },
      showList(row) {
        const that = this;
        this.userId = row.user_id;
        this.show = true;
        this.refresh(row.user_id);
      },
    },
    components: {}
  }
</script>
<style></style>
