/*善行活动管理,作者:唐植超,日期:2018-10-20 11:58:00*/
<template>
    <div>
        <el-form :inline="true" label-width="60px">
            <el-form-item label="类型">
                <el-select size="small" v-model="form.type" placeholder="类型" style="width: 100%">
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
                <el-input placeholder="活动名称" size="small" v-model="form.name"></el-input>
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
                                <el-form-item label="类型">{{ props.row.type_name }}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="名称">{{props.row.name}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="内容">{{props.row.content}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="目标机构/电话">{{props.row.target_name}}/{{props.row.target_phone}}
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="活动时间">{{props.row.event_time |date_filter}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="备注">{{props.row.remark}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="主导人/手机">{{props.row.charity_name}}/{{props.row.chairmain_pnone}}
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column prop="id" label="编号" width="60"></el-table-column>
            <el-table-column prop="type_name" label="类型" width="60">
            </el-table-column>
            <el-table-column prop="name" label="名称"></el-table-column>
            <el-table-column prop="status" label="状态" width="80">
                <template slot-scope="props">
                    {{props.row.status | event_status_filter}}
                </template>
            </el-table-column>
            <el-table-column prop="charity_name" label="主导人"  ></el-table-column>
            <el-table-column prop="target_name" label="目标机构"  ></el-table-column>
            <el-table-column label="操作" width="200">
                <template slot-scope="props">
                    <div>
                        <el-button type="text" @click="doShowJoin(props.row)">参与人列表</el-button>
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
        <CharityEventDialog ref="dialog" :refresh="refresh"></CharityEventDialog>
        <JoinListDialog ref="joinDialog"></JoinListDialog>
    </div>
</template>
<script>
    import CharityEventDialog from './CharityEventDialog.vue';
    import JoinListDialog from  './JoinListDialog.vue';

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
                    content: null,// content
                    chairman_id: null,// chairman_id
                    target_id: null,// target_id
                    event_time: null,// event_time
                    author: null,// author
                    remark: null,// remark
                    chairmain_pnone: null,// chairmain_pnone
                    target_phone: null,// target_phone
                    status: null,// status
                    create_time: null,// create_time
                },
                typeList: [],
                loading: false
            }
        },
        computed: {},
        created: function () {
            this.refresh();
            this.loadTypes(1);
        },
        methods: {
            loadTypes(type){
                const that = this;
                that.$http.post("/api/dict/queryList", JSON.stringify({
                    type: type
                })).then(res => {
                    that.typeList = res.data;
                }).catch(re => {
                    that.$message.error("获取类型：" + res);
                });
            },
            refresh() {
                const that = this;
                that.loading = true;
                const requestData = {...that.form, page: that.page - 1, size: that.size};
                if (requestData.type == -1) {
                    requestData.type = null;
                }
                that.$http.post("/api/charityEvent/queryPage", JSON.stringify(requestData)).then(res => {
                    that.loading = false;
                    that.dataList = res.data.content;
                    that.total = res.data.totalElements;
                }).catch(res => {
                    that.$message.error("获取善行活动列表失败：" + res);
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
                    that.$http.delete("/api/charityEvent/delete", {
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
            doShowJoin(row){
                this.$refs["joinDialog"].showDialog(row.id);
            }
        },
        components: {CharityEventDialog, JoinListDialog}
    }
</script>
<style scoped>

</style>