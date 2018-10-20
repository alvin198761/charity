/*部门树选择页,作者:wlp,日期:2018-08-13 15:21:40*/
<template>
  <div style="height: 400px ;overflow: auto">
    <hr/>
      <el-tree :data="menus" node-key="id" default-expand-all :expand-on-click-node="false" @node-click="selectDept">
        <span class="custom-tree-node" slot-scope="{ node,data}">
          <el-radio-group v-model="department_id"><el-radio :label="data.dept_id" :v-model="data.dept_id"> {{ data.name}}</el-radio></el-radio-group>
        </span>
      </el-tree>
  </div>
</template>
<script>
  export default {
    props: ['selectDept'],
    data: function () {
      return {
        menus: [],
        department_id: null,
      }
    },
    created: function () {
      this.refresh();
    },
    methods: {
      refresh() {
        const that = this;
        that.$http.post("/api/adminDept/getTree").then(res => {
          that.menus = res.data;
        });
      },
      setDeptId(id) {
        this.department_id = id;
      }
    },
  }
</script>
<style>

  .tran_box .el-checkbox__inner {
    border-radius: 9px;
  }

</style>

