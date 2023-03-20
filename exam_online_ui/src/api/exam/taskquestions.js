import request from '@/utils/request'

// 查询任务题目列表
export function listTaskquestions(query) {
  return request({
    url: '/exam/taskquestions/list',
    method: 'get',
    params: query
  })
}

// 查询任务题目详细
export function getTaskquestions(examCode) {
  return request({
    url: '/exam/taskquestions/' + examCode,
    method: 'get'
  })
}

// 新增任务题目
export function addTaskquestions(data) {
  return request({
    url: '/exam/taskquestions',
    method: 'post',
    data: data
  })
}

export function batchUpdateTaskquestions(data) {
  return request({
    url: '/exam/taskquestions/batchupdate',
    method: 'post',
    data: data
  })
}

// 修改任务题目
export function updateTaskquestions(data) {
  return request({
    url: '/exam/taskquestions/update',
    method: 'post',
    data: data
  })
}

// 删除任务题目
export function delTaskquestions(examCode) {
  return request({
    url: '/exam/taskquestions/delete/' + examCode,
    method: 'get'
  })
}

// 导出任务题目
export function exportTaskquestions(query) {
  return request({
    url: '/exam/taskquestions/export',
    method: 'get',
    params: query
  })
}