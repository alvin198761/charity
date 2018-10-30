/*类型字典管理,作者:唐植超,日期:2018-10-20 11:58:00*/
<template>
    <div>
        <el-form :inline="true">
            <el-form-item label="名称">
                <el-input placeholder="活动类型名称" size="small" v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>
                <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%">
            <el-table-column prop="id" label="主键"></el-table-column>
            <el-table-column prop="name" label="活动类型名称"></el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="props">
                    <div>
                        <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
                        <!--<el-button type="text" @click="doDelete(props.row)">删除</el-button>-->
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <DictDialog ref="dialog" :refresh="refresh" :type="form.type" titleName="活动类型"></DictDialog>
    </div>
</template>
<script>
    import DictDialog from './DictDialog.vue';

    export default {
        data: function () {
            return {
                dataList: [],
                form: {
                    id: null,// 主键
                    type: 1,// type
                    name: null,// name
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
                const requestData = {...that.form};
                that.$http.post("/api/dict/queryList", JSON.stringify(requestData)).then(res => {
                    that.loading = false;
                    that.dataList = res.data ;
                }).catch(res => {
//                    that.$message.error("获取活动类型失败：" + res);
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
                    that.$http.delete("/api/dict/delete", {
                        params: {ids: [row.id]}
                    }).then(res => {
                        this.$message.success("删除成功");
                        that.refresh();
                    }).catch(res => {
                        that.$message.error("删除失败：" + res);
                    });
                }).catch(() => {
                });
            }
        },
        components: {DictDialog}
    }
</script>
<style></style>