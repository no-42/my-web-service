<template>
  <el-form ref="genInfoForm" :model="info" :rules="rules" label-width="150px">
    <el-row>
      <el-col :span="12">
        <el-form-item prop="tplCategory">
          <template #label>生成模板</template>
          <el-select v-model="info.tplCategory" @change="tplSelectChange">
            <el-option label="单表（增删改查）" value="crud"/>
          </el-select>
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="packageName">
          <template #label>
            生成包路径
            <el-tooltip content="生成在哪个java包下，例如 com.ruoyi.system" placement="top">
              <el-icon>
                <question-filled/>
              </el-icon>
            </el-tooltip>
          </template>
          <el-input v-model="info.packageName"/>
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="moduleName">
          <template #label>
            生成模块名
            <el-tooltip content="可理解为子系统名，例如 system" placement="top">
              <el-icon>
                <question-filled/>
              </el-icon>
            </el-tooltip>
          </template>
          <el-input v-model="info.moduleName"/>
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="businessName">
          <template #label>
            生成业务名
            <el-tooltip content="可理解为功能英文名，例如 user" placement="top">
              <el-icon>
                <question-filled/>
              </el-icon>
            </el-tooltip>
          </template>
          <el-input v-model="info.businessName"/>
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="functionName">
          <template #label>
            生成功能名
            <el-tooltip content="用作类描述，例如 用户" placement="top">
              <el-icon>
                <question-filled/>
              </el-icon>
            </el-tooltip>
          </template>
          <el-input v-model="info.functionName"/>
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item>
          <template #label>
            上级菜单
            <el-tooltip content="分配到指定菜单下，例如 系统管理" placement="top">
              <el-icon>
                <question-filled/>
              </el-icon>
            </el-tooltip>
          </template>
          <tree-select
              v-model:value="info.parentMenuId"
              :options="menuOptions"
              :objMap="{ value: 'id', label: 'name', children: 'children' }"
              placeholder="请选择系统菜单"
          />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="genType">
          <template #label>
            生成代码方式
            <el-tooltip content="默认为zip压缩包下载，也可以自定义生成路径" placement="top">
              <el-icon>
                <question-filled/>
              </el-icon>
            </el-tooltip>
          </template>
          <el-radio v-model="info.genType" label="0">zip压缩包</el-radio>
          <el-radio v-model="info.genType" label="1">自定义路径</el-radio>
        </el-form-item>
      </el-col>

      <el-col :span="24" v-if="info.genType == '1'">
        <el-form-item prop="genPath">
          <template #label>
            自定义路径
            <el-tooltip content="填写磁盘绝对路径，若不填写，则生成到当前Web项目下" placement="top">
              <el-icon>
                <question-filled/>
              </el-icon>
            </el-tooltip>
          </template>
          <el-input v-model="info.genPath">
            <template #append>
              <el-dropdown>
                <el-button type="primary">
                  最近路径快速选择
                  <i class="el-icon-arrow-down el-icon--right"></i>
                </el-button>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item @click="info.genPath = '/'">恢复默认的生成基础路径</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </template>
          </el-input>
        </el-form-item>
      </el-col>
    </el-row>

  </el-form>
</template>

<script setup>
import {getCurrentInstance, ref, watch} from 'vue';
import {listMenu} from "@/api/system/menu";

const subColumns = ref([]);
const menuOptions = ref({});
const {proxy} = getCurrentInstance();

const props = defineProps({
  info: {
    type: Object,
    default: null
  },
  tables: {
    type: Array,
    default: null
  }
});

// 表单校验
const rules = ref({
  tplCategory: [{required: true, message: "请选择生成模板", trigger: "blur"}],
  packageName: [{required: true, message: "请输入生成包路径", trigger: "blur"}],
  moduleName: [{required: true, message: "请输入生成模块名", trigger: "blur"}],
  businessName: [{required: true, message: "请输入生成业务名", trigger: "blur"}],
  functionName: [{required: true, message: "请输入生成功能名", trigger: "blur"}]
});

function tplSelectChange(value) {
  if (value !== "sub") {
    props.info.subTableName = "";
    props.info.subTableFkName = "";
  }
}

function setSubTableColumns(value) {
  for (var item in props.tables) {
    const name = props.tables[item].tableName;
    if (value === name) {
      subColumns.value = props.tables[item].columns;
      break;
    }
  }
}

/** 查询菜单下拉树结构 */
function getMenuTreeSelect() {
  listMenu().then(response => {
    menuOptions.value = proxy.handleTree(response.data, "id");
  });
}

watch(() => props.info.subTableName, val => {
  setSubTableColumns(val);
});

getMenuTreeSelect();
</script>
