import request from '@/utils/request'

// 查询考试人员列表
export function listTaskperson(query) {
  return request({
    url: '/exam/taskperson/list',
    method: 'get',
    params: query
  })
}

// 查询考试人员详细
export function getTaskperson(examCode) {
  return request({
    url: '/exam/taskperson/' + examCode,
    method: 'get'
  })
}

// 新增考试人员
export function addTaskperson(data) {
  return request({
    url: '/exam/taskperson/add',
    method: 'post',
    data: data
  })
}

// 修改考试人员
export function updateTaskperson(data) {
  return request({
    url: '/exam/taskperson/update',
    method: 'post',
    data: data
  })
}

// 修改考试人员开始时间
export function updateStartTime(data) {
  return request({
    url: '/exam/taskperson/starttime',
    method: 'post',
    data: data
  })
}
// 删除考试人员
export function delTaskperson(examCode) {
  return request({
    url: '/exam/taskperson/delete/' + examCode,
    method: 'get'
  })
}

// 导出考试人员
export function exportTaskperson(query) {
  return request({
    url: '/exam/taskperson/export',
    method: 'get',
    params: query
  })
}