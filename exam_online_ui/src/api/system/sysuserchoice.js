import request from '@/utils/request'

// 查询选择用户列表
export function listSelectuser(query) {
  return request({
    url: '/system/sysuserchoice/list',
    method: 'get',
    params: query
  })
}

// 查询选择用户列表
export function listChoiceuser(query) {
  return request({
    url: '/system/sysuserchoice/choiceuserlist',
    method: 'get',
    params: query
  })
}

// 查询选择用户详细
export function getSelectuser(choiceCode) {
  return request({
    url: '/system/sysuserchoice/' + choiceCode,
    method: 'get'
  })
}

// 批量拷贝用户
export function copySysUser(data) {
  return request({
    url: '/system/copysysuser',
    method: 'post',
    data: data
  })
}

// 从临时表拷贝数据
export function copyTempUser(data) {
  return request({
    url: '/system/copytempuser',
    method: 'post',
    data: data
  })
}

// 新增选择用户
export function addSelectuser(data) {
  return request({
    url: '/system/sysuserchoice',
    method: 'post',
    data: data
  })
}

// 修改选择用户
export function updateSelectuser(data) {
  return request({
    url: '/system/sysuserchoice/update',
    method: 'post',
    data: data
  })
}

// 删除选择用户
export function delSelectuser(choiceCode) {
  return request({
    url: '/system/sysuserchoice/delete/' + choiceCode,
    method: 'get'
  })
}

// 导出选择用户
export function exportSelectuser(query) {
  return request({
    url: '/system/sysuserchoice/export',
    method: 'get',
    params: query
  })
}