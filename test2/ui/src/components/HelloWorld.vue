<template>
  <div>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input
          v-model="search"
          class="search_name"
          size="mini"
          placeholder="输入课程名查询">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="text"
          @click="onSearch()"
          class="el-icon-search">查询
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          class="el-icon-refresh"
          type="text"
          @click="refreshData">刷新
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          class="el-icon-circle-plus-outline"
          type="text"
          @click="dialogVisible = true">添加
        </el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      highlight-current-row
      border
      style="width: 100%">
      <el-table-column
        label="课程号">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="课程名">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="right">
            <p>课程名: {{ scope.row.name }}</p>
            <div slot="reference" class="name-wrapper">
              <el-button type="text">{{ scope.row.name }}</el-button>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="课时">
        <template slot-scope="scope">
          <span>{{ scope.row.hours }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="开课学院">
        <template slot-scope="scope">
          <span>{{ scope.row.sid }}</span>
        </template>
      </el-table-column>
  <!--    <el-table-column
        label="地址">
        <template slot-scope="scope">
          <span>{{ scope.row.address }}</span>
        </template>
      </el-table-column>
      -->
      <el-table-column
        label="操作"
        fixed="right"
        width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            icon="el-icon-edit"
            @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm" size="medium">
      <el-dialog
        title="编辑"
        :append-to-body='true'
        :visible.sync="dialogVisible"
        width="80%"
        :before-close="handleClose">
        <el-input type="hidden" v-model="ruleForm.id"/>

        <el-form-item label="课程名" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>

        <el-form-item label="课时" prop="hours">
          <el-input v-model="ruleForm.hours"></el-input>
        </el-form-item>

        <el-form-item label="开课学院" prop="sid">
        <el-input v-model="ruleForm.sid"></el-input>
        </el-form-item>

        <!--
        <el-form-item label="地址" prop="address">
          <el-input v-model="ruleForm.address"></el-input>
        </el-form-item>
        -->
        <span slot="footer" class="dialog-footer">
            <el-button @click="cancel()" size="medium">取 消</el-button>
            <el-button @click="addUser()" type="primary" size="medium">确 定</el-button>
          </span>
      </el-dialog>
    </el-form>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm" size="medium">
      <el-dialog
        title="编辑"
        :append-to-body='true'
        :visible.sync="dialogUpdate"
        width="80%"
        :before-close="handleClose">
        <el-input type="hidden" v-model="ruleForm.id"/>

        <el-form-item label="课程名" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>

        <el-form-item label="课时" prop="hours">
          <el-input v-model="ruleForm.hours"></el-input>
        </el-form-item>

        <el-form-item label="开课学院" prop="sid">
          <el-input v-model="ruleForm.sid"></el-input>
        </el-form-item>

        <!--
        <el-form-item label="住址" prop="address">
          <el-input v-model="ruleForm.address"></el-input>
        </el-form-item>
        -->

        <span slot="footer" class="dialog-footer">
            <el-button @click="cancel()" size="medium">取 消</el-button>
            <el-button @click="updateUser()" type="primary" size="medium">确 定</el-button>
          </span>
      </el-dialog>
    </el-form>
    <br>
    <div class="pages">
      <el-pagination
        background
        :disabled = "disablePage"
        :current-page.sync="currentPage"
        small
        layout="prev, pager, next"
        :page-size="pageSize"
        :total="total"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        ruleForm: {
          id: '',
          name: '',
          hours: '',
          sid: ''
        },
        rules: {
          name: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            { min: 2, max: 7, message: '长度在 2 到 7 个字符', trigger: 'blur' }
          ],
        },
        tableData: [],
        search: '',
        dialogVisible: false,
        dialogUpdate: false,
        pageSize: 5,
        currentPage: 1,
        total: 0,
        disablePage: false
      }
    },
    methods: {
      handleEdit(index, row) {
        this.dialogUpdate = true;
        this.ruleForm = Object.assign({}, row); //这句是关键！！！
      },
      handleDelete(index, row) {
        console.log(index, row);
        this.$confirm('删除操作, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let postData = this.qs.stringify({
            id: row.id,
          });
          this.axios({
            method: 'post',
            url:'/delete',
            data:postData
          }).then(response =>
          {
            this.getPages();
            this.currentPage = 1;
            this.axios.post('/page').then(response =>
            {
              this.tableData = response.data;
            }).catch(error =>
            {
              console.log(error);
            });
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            console.log(response);
          }).catch(error =>
          {
            console.log(error);
          });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      handleCurrentChange() {
        console.log(`当前页: ${this.currentPage}`);
        let postData = this.qs.stringify({
          page: this.currentPage
        });
        this.axios({
          method: 'post',
          url:'/page',
          data:postData
        }).then(response =>
        {
          this.tableData = response.data;
        }).catch(error =>
        {
          console.log(error);
        });
      },
      cancel() {
        this.dialogUpdate = false;
        this.dialogVisible = false;
        this.emptyUserData();
      },
      emptyUserData(){
        this.ruleForm = {
          id: '',
          name: '',
          hours: '',
          sid: ''
        }
      },
      addUser() {
        let postData = this.qs.stringify({
          name: this.ruleForm.name,
          hours: this.ruleForm.hours,
          sid: this.ruleForm.sid
        });
        this.axios({
          method: 'post',
          url:'/add',
          data:postData
        }).then(response =>
        {
          this.axios.post('/page').then(response =>
          {
            this.tableData = response.data;
            this.currentPage = 1;
            this.$message({
              type: 'success',
              message: '已添加!'
            });
          }).catch(error =>
          {
            console.log(error);
          });
          this.getPages();
          this.dialogVisible = false
          console.log(response);
        }).catch(error =>
        {
          console.log(error);
        });
      },
      updateUser() {
        let postData = this.qs.stringify({
          id: this.ruleForm.id,
          name: this.ruleForm.name,
          hours: this.ruleForm.hours,
          sid: this.ruleForm.sid
        });
        this.axios({
          method: 'post',
          url:'/update',
          data:postData
        }).then(response =>
        {
          this.handleCurrentChange();
          this.cancel();
          this.$message({
            type: 'success',
            message: '更新成功!'
          });
          console.log(response);
        }).catch(error =>
        {
          this.$message({
            type: 'success',
            message: '更新失败!'
          });
          console.log(error);
        });
      },
      onSearch() {
        let postData = this.qs.stringify({
          name: this.search
        });
        this.axios({
          method: 'post',
          url: '/findByName',
          data: postData
        }).then(response =>
        {
          this.tableData = response.data;
          this.disablePage = true;
        }).catch(error =>
        {
          console.log(error);
        });
      },
      getPages() {
        this.axios.post('/rows').then(response =>
        {
          this.total = response.data;
        }).catch(error =>
        {
          console.log(error);
        });
      },
      refreshData() {
        location.reload();
      }
    },
    created() {
      this.axios.post('/page').then(response =>
      {
        this.tableData = response.data;
      }).catch(error =>
      {
        console.log(error);
      });

      this.axios.post('/rows').then(response =>
      {
        this.total = response.data;
      }).catch(error =>
      {
        console.log(error);
      });

    },
  }
</script>
<style scoped>
  .search_name{
    width: 200px;
  }
  .pages{
    margin: 0px;
    padding: 0px;
    text-align: right;
  }
</style>
