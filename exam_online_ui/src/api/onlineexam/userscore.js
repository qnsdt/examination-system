import request from '@/utils/request'

// 查询考试成绩列表
export function listUserscore(query) {
  return request({
    url: '/onlineexam/userscore/list',
    method: 'get',
    params: query
  })
}

// 查询考试成绩详细
export function getUserscore(examCode) {
  return request({
    url: '/onlineexam/userscore/' + examCode,
    method: 'get'
  })
}

// 新增考试成绩
export function addUserscore(data) {
  return request({
    url: '/onlineexam/userscore',
    method: 'post',
    data: data
  })
}

// 维护考试成绩
export function intoUserscore(data) {
  return request({
    url: '/onlineexam/userscore/into',
    method: 'post',
    data: data
  })
}

// 修改考试成绩
export function updateUserscore(data) {
  return request({
    url: '/onlineexam/userscore',
    method: 'put',
    data: data
  })
}

// 删除考试成绩
export function delUserscore(examCode) {
  return request({
    url: '/onlineexam/userscore/' + examCode,
    method: 'delete'
  })
}

// 导出考试成绩
export function exportUserscore(query) {
  return request({
    url: '/onlineexam/userscore/export',
    method: 'get',
    params: query
  })
}