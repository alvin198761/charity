/*善行活动管理,作者:唐植超,日期:2018-10-20 11:58:00*/
<template>
      <div>
            <el-form :inline="true">
                                    <el-form-item label="主键">
                        <el-input placeholder="请输入主键" size="small" v-model="form.id"></el-input>
                    </el-form-item>
                                    <el-form-item label="type">
                        <el-input placeholder="请输入type" size="small" v-model="form.type"></el-input>
                    </el-form-item>
                                    <el-form-item label="name">
                        <el-input placeholder="请输入name" size="small" v-model="form.name"></el-input>
                    </el-form-item>
                                    <el-form-item label="content">
                        <el-input placeholder="请输入content" size="small" v-model="form.content"></el-input>
                    </el-form-item>
                                    <el-form-item label="chairman_id">
                        <el-input placeholder="请输入chairman_id" size="small" v-model="form.chairman_id"></el-input>
                    </el-form-item>
                                    <el-form-item label="target_id">
                        <el-input placeholder="请输入target_id" size="small" v-model="form.target_id"></el-input>
                    </el-form-item>
                                    <el-form-item label="event_time">
                        <el-input placeholder="请输入event_time" size="small" v-model="form.event_time"></el-input>
                    </el-form-item>
                                    <el-form-item label="author">
                        <el-input placeholder="请输入author" size="small" v-model="form.author"></el-input>
                    </el-form-item>
                                    <el-form-item label="remark">
                        <el-input placeholder="请输入remark" size="small" v-model="form.remark"></el-input>
                    </el-form-item>
                                    <el-form-item label="chairmain_pnone">
                        <el-input placeholder="请输入chairmain_pnone" size="small" v-model="form.chairmain_pnone"></el-input>
                    </el-form-item>
                                    <el-form-item label="target_phone">
                        <el-input placeholder="请输入target_phone" size="small" v-model="form.target_phone"></el-input>
                    </el-form-item>
                                    <el-form-item label="status">
                        <el-input placeholder="请输入status" size="small" v-model="form.status"></el-input>
                    </el-form-item>
                                    <el-form-item label="create_time">
                        <el-input placeholder="请输入create_time" size="small" v-model="form.create_time"></el-input>
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
                                              <el-form-item label="主键">{{props.row.id}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="type">{{props.row.type}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="name">{{props.row.name}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="content">{{props.row.content}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="chairman_id">{{props.row.chairman_id}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="target_id">{{props.row.target_id}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="event_time">{{props.row.event_time}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="author">{{props.row.author}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="remark">{{props.row.remark}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="chairmain_pnone">{{props.row.chairmain_pnone}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="target_phone">{{props.row.target_phone}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="status">{{props.row.status}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="create_time">{{props.row.create_time}}</el-form-item>
                                          </el-col>
                                                                            </el-row>
                                </el-form>
                        </template>
                  </el-table-column>
                                    <el-table-column prop="id" label="主键"></el-table-column>
                                    <el-table-column prop="type" label="type"></el-table-column>
                                    <el-table-column prop="name" label="name"></el-table-column>
                                    <el-table-column prop="content" label="content"></el-table-column>
                                    <el-table-column prop="chairman_id" label="chairman_id"></el-table-column>
                                    <el-table-column prop="target_id" label="target_id"></el-table-column>
                                    <el-table-column prop="event_time" label="event_time"></el-table-column>
                                    <el-table-column prop="author" label="author"></el-table-column>
                                    <el-table-column prop="remark" label="remark"></el-table-column>
                                    <el-table-column prop="chairmain_pnone" label="chairmain_pnone"></el-table-column>
                                    <el-table-column prop="target_phone" label="target_phone"></el-table-column>
                                    <el-table-column prop="status" label="status"></el-table-column>
                                    <el-table-column prop="create_time" label="create_time"></el-table-column>
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
              <el-pagination small layout="sizes,prev, pager, next" :current-page="page" :total="total" @current-change="(curr) => {this.page = curr ; this.refresh();}"
               :page-sizes="[10, 15, 20, 100]" @size-change="(s) => {this.size = s ; this.refresh();}" :page-size="size"></el-pagination>
            </div>
        <CharityEventDialog ref="dialog" :refresh="refresh"></CharityEventDialog>
  </div>
</template>
<script>
  import CharityEventDialog from './CharityEventDialog.vue';

  export default {
    data: function () {
      return {
        total: 0,
        page: 1,
        size: 20,
        dataList: [],
        form: {
                    id : null,// 主键
                    type : null,// type
                    name : null,// name
                    content : null,// content
                    chairman_id : null,// chairman_id
                    target_id : null,// target_id
                    event_time : null,// event_time
                    author : null,// author
                    remark : null,// remark
                    chairmain_pnone : null,// chairmain_pnone
                    target_phone : null,// target_phone
                    status : null,// status
                    create_time : null,// create_time
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
      }
    },
    components: { CharityEventDialog }
  }
</script>
<style></style>