/*善行活动新增与修改,作者:唐植超,日期:2018-10-20 11:58:00*/
<template>
    <el-dialog title="参与人列表" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <div>
            <el-form :inline="true">
                <el-form-item label="类型">
                    <el-select style="width: 100%"
                               v-model='form.user_id'
                               size="small"
                               filterable
                               remote
                               reserve-keyword
                               placeholder="参与人"
                               :remote-method="queryCharityList"
                               :loading="loadingUser">
                        <el-option
                                v-for="item in charityList"
                                :key="item.id"
                                :label="item.charity_name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <!--<el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>-->
                    <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
                </el-form-item>
            </el-form>
            <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%">
                <el-table-column prop="event_name" label="活动"></el-table-column>
                <el-table-column prop="join_user_name" label="参与人"></el-table-column>
                <el-table-column label="操作" width="150">
                    <template slot-scope="props">
                        <div>
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
        </div>
    </el-dialog>
</template>
<script>
    export default {
        data() {
            return {
                show: false,
                total: 0,
                page: 1,
                size: 20,
                dataList: [],
                loadingUser: false,
                loading: false,
                form: {
                    id: null,// 主键
                    event_id: null,// 活动ID
                    user_id: null,// 参与人ID
                },
                charityList: []
            }
        },
        methods: {
            queryCharityList(query){
                const that = this;
                that.loadingUser = true;
                that.$http.post("/api/charity/queryList", JSON.stringify({
                    charity_name: query,
                    category: 1
                })).then(res => {
                    that.charityList = res.data;
                    that.loadingUser = false;
                }).catch(res => {
                    that.$message.error("获取行善方类型：" + res);
                    that.loadingUser = false;
                });
            },
            showDialog(event_id){
                this.refresh();
                this.form = {...this.form, event_id: event_id}
                this.show = true;
            },
            refresh(){
                const that = this;
                that.loading = true;
                const requestData = {...that.form, page: that.page - 1, size: that.size};
                that.$http.post("/api/eventUser/queryPage", JSON.stringify(requestData)).then(res => {
                    that.loading = false;
                    that.dataList = res.data.content;
                    that.total = res.data.totalElements;
                }).catch(res => {
                    that.$message.error("获取活动参与方列表失败：" + res);
                    that.loading = false;
                });
            },
            doAdd(){
                const that = this;
                that.$http.post("/api/eventUser/save", JSON.stringify(that.form)).then(res => {
//                    that.show = false;
                    that.$message.success("添加成功!");
                    that.refresh();
                }).catch(res => {
                    that.$message.error("添加失败!" + res);
                });
            },
            doDelete(row) {
                const that = this;
                this.$confirm('你确定要删除吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    that.$http.delete("/api/eventUser/delete", {
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
        components: {}
    }
</script>
<style></style>