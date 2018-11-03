/*慈善方管理,作者:唐植超,日期:2018-10-20 11:58:00*/
<template>
    <div>
        <el-form :inline="true">
            <el-form-item label="类型">
                <el-select size="small" v-model="form.type" placeholder="受善方类型"  style="width: 100%">
                    <el-option label="全部" :value="-1"></el-option>
                    <el-option
                            v-for="item in typeList"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="姓名">
                <el-input placeholder="姓名" size="small" v-model="form.charity_name"></el-input>
            </el-form-item>
            <el-form-item label="手机号">
                <el-input placeholder="手机号" size="small" v-model="form.phone_no"></el-input>
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
                                <el-form-item label="地址">{{props.row.address}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="创建时间">{{props.row.create_date |date_filter}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="创建人">{{props.row.author}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="备注">{{props.row.remark}}</el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column prop="id" label="编号"></el-table-column>
            <el-table-column prop="type_name" label="类型"></el-table-column>
            <el-table-column prop="charity_name" label="姓名"></el-table-column>
            <el-table-column prop="phone_no" label="手机号"></el-table-column>
            <el-table-column prop="gender" label="性别"></el-table-column>
            <el-table-column prop="status" label="状态">
                <template slot-scope="props">
                    {{props.row.status == 0 ? '正常' : '禁用'}}
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="props">
                    <div>
                        <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
                        <!--<el-button type="text" @click="doDelete(props.row)">删除</el-button>-->
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
        <CharityDialog ref="dialog" :refresh="refresh"  :type="4" titleName="受善方"  :category="2"></CharityDialog>
    </div>
</template>
<script>
    import CharityDialog from './CharityDialog.vue';

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
                    charity_name: null,// charity_name
                    phone_no: null,// phone_no
                    gender: null,// gender
                    address: null,// address
                    create_date: null,// create_date
                    author: null,// author
                    remark: null,// remark
                    p_id: null,// p_id
                    category: 2,// category
                    status: null,// status
                },
                typeList: [],
                loading: false
            }
        },
        computed: {},
        created: function () {
            this.refresh();
            this.loadTypes(4);
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
                if(requestData.type == -1){
                    requestData.type = null;
                }
                requestData.type_type = 4;
                that.$http.post("/api/charity/queryPage", JSON.stringify(requestData)).then(res => {
                    that.loading = false;
                    that.dataList = res.data.content;
                    that.total = res.data.totalElements;
                }).catch(res => {
                    that.$message.error("获取受善方列表失败：" + res);
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
                    that.$http.delete("/api/charity/delete", {
                        params: {id: row.id}
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
        components: {CharityDialog}
    }
</script>
<style></style>