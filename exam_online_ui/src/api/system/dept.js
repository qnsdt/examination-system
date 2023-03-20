import request from '@/utils/request'
import { agentId } from "@/config/sysinfo.js"

// 查询部门列表
export function listDept(query) {
  return request({
    url: '/system/dept/list',
    method: 'get',
    params: query
  })
}

// 查询部门列表（排除节点）
export function listDeptExcludeChild(deptId) {
  return request({
    url: '/system/dept/list/exclude/' + deptId,
    method: 'get'
  })
}

// 查询部门详细
export function getDept(deptId) {
  return request({
    url: '/system/dept/' + deptId,
    method: 'get'
  })
}

// 查询部门下拉树结构
export function treeselect() {
  return request({
    url: '/system/dept/treeselect',
    method: 'get'
  })
}

// 根据角色ID查询部门树结构
export function roleDeptTreeselect(roleId) {
  return request({
    url: '/system/dept/roleDeptTreeselect/' + roleId,
    method: 'get'
  })
}

// 新增部门
export function addDept(data) {
  return request({
    url: '/system/dept',
    method: 'post',
    data: data
  })
}

// 修改部门
export function updateDept(data) {
  return request({
    url: '/system/dept/update',
    method: 'post',
    data: data
  })
}

// 删除部门
export function delDept(deptId) {
  return request({
    url: '/system/dept/delete/' + deptId,
    method: 'get'
  })
}

// 新增导入临时部门
export function inDeptBuff() {
  return request({
    url: '/wxcp/dept/indeptbuff/' + agentId,
    method: 'get'
  })
}

// 新增导入部门
export function inDept() {
  return request({
    url: '/wxcp/dept/indept',
    method: 'get'
  })
}

// 取得部门列表
export function choiceOrg() {
  return request({
    url: '/wxcp/dept/choiceorg',
    method: 'get'
  })
}