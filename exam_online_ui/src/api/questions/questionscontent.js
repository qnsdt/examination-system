import request from '@/utils/request'

// 查询题目内容表列表
export function listQuestionscontent(query) {
  return request({
    url: '/questions/questionscontent/list',
    method: 'get',
    params: query
  })
}

// 查询题目内容表详细
export function getQuestionscontent(questionsCode) {
  return request({
    url: '/questions/questionscontent/' + questionsCode,
    method: 'get'
  })
}

// 新增题目内容表
export function addQuestionscontent(data) {
  return request({
    url: '/questions/questionscontent',
    method: 'post',
    data: data
  })
}

// 修改题目内容表
export function updateQuestionscontent(data) {
  return request({
    url: '/questions/questionscontent',
    method: 'put',
    data: data
  })
}

// 删除题目内容表
export function delQuestionscontent(questionsCode) {
  return request({
    url: '/questions/questionscontent/' + questionsCode,
    method: 'delete'
  })
}

// 导出题目内容表
export function exportQuestionscontent(query) {
  return request({
    url: '/questions/questionscontent/export',
    method: 'get',
    params: query
  })
}