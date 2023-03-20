import request from '@/utils/request'

// 查询考试成绩列表
export function listFinishanswer(query) {
  return request({
    url: '/onlineexam/finishanswer/list',
    method: 'get',
    params: query
  })
}

// 查询考试成绩详细
export function getFinishanswer(examCode) {
  return request({
    url: '/onlineexam/finishanswer/' + examCode,
    method: 'get'
  })
}

// 新增考试成绩
export function addFinishanswer(data) {
  return request({
    url: '/onlineexam/finishanswer',
    method: 'post',
    data: data
  })
}

// 维护考试成绩
export function saveFinishanswer(data) {
  return request({
    url: '/onlineexam/finishanswer/save',
    method: 'post',
    data: data
  })
}

// 修改考试成绩
export function updateFinishanswer(data) {
  return request({
    url: '/onlineexam/finishanswer',
    method: 'put',
    data: data
  })
}

// 删除考试成绩
export function delFinishanswer(examCode) {
  return request({
    url: '/onlineexam/finishanswer/' + examCode,
    method: 'delete'
  })
}

// 导出考试成绩
export function exportFinishanswer(query) {
  return request({
    url: '/onlineexam/finishanswer/export',
    method: 'get',
    params: query
  })
}