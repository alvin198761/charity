/*捐助物资管理,作者:唐植超,日期:2018-10-20 11:58:00*/
<template>
    <div>
        <el-form  :inline="true">
            <el-form-item label="类型">
                <el-select size="small" v-model="form.type" placeholder="类型"  style="width: 100%">
                    <el-option label="全部" :value="-1"></el-option>
                    <el-option
                            v-for="item in typeList"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="名称">
                <el-input placeholder="名称" size="small" v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="捐赠人">
                <el-input placeholder="捐赠人" size="small" v-model="form.c_id"></el-input>
            </el-form-item>
            <el-form-item label="状态">
                <el-input placeholder="状态" size="small" v-model="form.status"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>
                <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%">
            <el-table-column type="expand">
                <template slot-scope="props">
                    <el-form>
                        <el-row :gutter="10">
                            <el-col :span="6">
                                <el-form-item label="create_date">{{props.row.create_date}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="author">{{props.row.author}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="remark">{{props.row.remark}}</el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column prop="id" label="编号"></el-table-column>
            <el-table-column prop="type" label="类型"></el-table-column>
            <el-table-column prop="name" label="名称"></el-table-column>
            <el-table-column prop="count" label="数量"></el-table-column>
            <el-table-column prop="create_date" label="创建时间"></el-table-column>
            <el-table-column prop="author" label="创建人"></el-table-column>
            <el-table-column prop="remark" label="备注"></el-table-column>
            <el-table-column prop="c_id" label="捐赠人"></el-table-column>
            <el-table-column prop="status" label="状态"></el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="props">
                    <div>
                        <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
                        <el-button type="text" @click="doDelete(props.row)">删除</el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <br/>
        <div style="text-align: right" v-if="total > 0">
            <el-pagination small layout="sizes,prev, pager, next" :current-page="page" :total="total"
                           @current-change="(curr) => {this.page = curr ; this.refresh();}"
                           :page-sizes="[10, 15, 20, 100]" @size-change="(s) => {this.size = s ; this.refresh();}"
                           :page-size="size"></el-pagination>
        </div>
        <CategoryDialog ref="dialog" :refresh="refresh"></CategoryDialog>
    </div>
</template>
<script>
    import CategoryDialog from './CategoryDialog.vue';

    export default {
        data: function () {
            return {
                total: 0,
                page: 1,
                size: 20,
                dataList: [],
                form: {
                    id: null,// 主键
                    type: null,// type
                    name: null,// name
                    count: null,// count
                    create_date: null,// create_date
                    author: null,// author
                    remark: null,// remark
                    c_id: null,// c_id
                    status: null,// status
                },
                typeList: [],
                loading: false
            }
        },
        computed: {},
        created: function () {
            this.refresh();
            this.loadTypes(2);
        },
        methods: {
            loadTypes(type){
                const that = this;
                that.$http.post("/api/dict/queryList",JSON.stringify({
                    type: type
                })).then(res => {
                    that.typeList = res.data;
                }).catch(re => {
                    that.$message.error("获取受善方类型：" + res);
                });
            },
            refresh() {
                const that = this;
                that.loading = true;
                const requestData = {...that.form, page: that.page - 1, size: that.size};
                that.$http.post("/api/category/queryPage", JSON.stringify(requestData)).then(res => {
                    that.loading = false;
                    that.dataList = res.data.content;
                    that.total = res.data.totalElements;
                }).catch(res => {
                    that.$message.error("获取捐助物资列表失败：" + res);
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
                    that.$http.delete("/api/category/delete", {
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
        components: {CategoryDialog}
    }
</script>
<style></style>