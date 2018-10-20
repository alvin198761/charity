/*菜单列表,作者:高振中,日期:2018-08-10 09:21:48*/
<template>
  <div>
    <el-button @click="refresh" type="primary">刷新</el-button>
    <hr/>
    <div style="overflow: auto; height: 600px ; width: 450px">
      <el-tree :data="dataList" node-key="id" default-expand-all :expand-on-click-node="false"
               draggable
               v-loading="loading" element-loading-text="正在加载......"
               @node-drop="handleDrop"
               :allow-drop="allowDrop"
               :allow-drag="allowDrag">
        <span class="custom-tree-node" slot-scope="{ node,data}">
          {{ data.name}}
          <span style="margin-left: 30px">
            <el-button type="text" size="mini" @click="() => doAdd(data)">添加子菜单</el-button>
            <el-button type="text" size="mini" @click="() => doEdit(data)">修改</el-button>
            <el-button type="text" size="mini" :disabled="data.parent_id ==0"
                       @click="() => doDelete(node, data)">删除</el-button>
          </span>
        </span>
      </el-tree>
    </div>
    <hr/>
    <AdminFuncDialog ref="dialog" :refresh="refresh"></AdminFuncDialog>
  </div>
</template>
<script>
  import AdminFuncDialog from './AdminFuncDialog.vue';

  export default {
    data: function () {
      return {
        list: [],
        total: 0,
        page: 1,
        size: 20,
        dataList: [],
        form: {
          func_id: null,//主键
          type: null,//类型
          color: null,//颜色
          icon: null,//图标
          name: null,//名称
          parent_id: null,//父结点ID
          tip: null,//提示信息
          remark: null,//备注
          status: null,//状态
          url: null,//地址
          order_num: null,//排序编号
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
        that.$http.post("/api/adminFunc/getTree").then(res => {
          that.loading = false;
          that.dataList = res.data;
          that.total = res.data.totalElements;
        }).catch(res => {
          that.$message.error("获取菜单列表失败：" + res);
          that.loading = false;
        });
      },
      doAdd(data) {
        this.$refs["dialog"].addDialog(data.func_id);
      },
      doEdit(row) {
        this.$refs["dialog"].editDialog(row);
      },
      doDelete(node, row) {
        const that = this;
        if (row.children.length > 0) {
          that.$message.error("请先删除子结点!");
          return;
        }
        this.$confirm('你确定要删除吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          that.$http.delete("/api/adminFunc/delete", {
            params: {ids: [row.func_id]}
          }).then(res => {
            this.$message.success("删除成功");
            that.refresh();
          }).catch(res => {
            that.$message.error("删除失败：" + res);
          });
        }).catch(() => {
        });
      },
      handleDrop(draggingNode, dropNode, dropType, ev) {//拖拽成功
        console.log(draggingNode);
        console.log(dropNode);
        console.log(dropType);
        console.log(ev);

        // {
        //   type: '',
        //     dragNode: null,
        //   dropNode: null
        // }
        const that = this;
        // that.$http.delete("/api/adminFunc/dragMove", {
        //   params: {cur_id: draggingNode.data.func_id,tar_id:dropNode.data.func_id,type:dropType}
        // }).then(res => {
        //   this.$message.success("拖拽成功");
        //   that.refresh();
        // }).catch(res => {
        //   that.$message.error("拖拽失败：" + res);
        // });
        that.list.push(draggingNode.data);//拖拽对象
        that.list.push(dropNode.data);//目标对象
        that.$http.post("/api/adminFunc/dragMove?type=" + dropType, that.list).then(res => {
          this.show = false;
          this.$message.success("拖拽成功");
        }).catch(res => {
          that.$message.error("拖拽失败：" + res);
        });
        // console.log('tree drop: ', draggingNode.label,draggingNode.data.id,dropNode.label,dropNode.key, dropType,draggingNode.data.parent_id);

      },
      allowDrop(draggingNode, dropNode, type) {//当前拖拽的节点不能放在 系统菜单前后 不能平级，可放到里面
        if (dropNode.data.parent_id == 0) {//系统菜单
          return type !== 'prev' && type !== 'next'
        } else {
          return true;
        }
      },
      allowDrag(draggingNode) {//节点能否被拖拽 系统菜单不能被拖拽 true可拽
        console.log(draggingNode)
        return draggingNode.data.parent_id != 0;
      }
    },
    components: {AdminFuncDialog}
  }
</script>
<style></style>
