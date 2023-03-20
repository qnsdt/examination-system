import request from '@/utils/request'

// 查询用户选择列表
export function listUserchoice(query) {
  return request({
    url: '/system/userchoice/list',
    method: 'get',
    params: query
  })
}

// 查询用户选择详细
export function getUserchoice(choiceCode) {
  return request({
    url: '/system/userchoice/' + choiceCode,
    method: 'get'
  })
}

// 新增用户选择
export function addUserchoice(data) {
  return request({
    url: '/system/userchoice',
    method: 'post',
    data: data
  })
}

// 批量新增用户
export function batchAddUser(data) {
  return request({
    url: '/system/userchoice/batch',
    method: 'post',
    data: data
  })
}

// 修改用户选择
export function updateUserchoice(data) {
  return request({
    url: '/system/userchoice/update',
    method: 'post',
    data: data
  })
}

// 删除用户选择
export function delUserchoice(data) {
  return request({
    url: '/system/userchoice/remove',
    method: 'post',
    data: data
  })
}

// 导出用户选择
export function exportUserchoice(query) {
  return request({
    url: '/system/userchoice/export',
    method: 'get',
    params: query
  })
}