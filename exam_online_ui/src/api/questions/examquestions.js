import request from '@/utils/request'

// 查询考试题目列表
export function listExamquestions(query) {
  return request({
    url: '/questions/examquestions/list',
    method: 'get',
    params: query
  })
}

// 查询考试题目详细
export function getExamquestions(questionsCode) {
  return request({
    url: '/questions/examquestions/' + questionsCode,
    method: 'get'
  })
}

// 新增考试题目
export function addExamquestions(data) {
  return request({
    url: '/questions/examquestions/add',
    method: 'post',
    data: data
  })
}

// 修改考试题目
export function updateExamquestions(data) {
  return request({
    url: '/questions/examquestions/update',
    method: 'post',
    data: data
  })
}

// 删除考试题目
export function delExamquestions(questionsCode) {
  return request({
    url: '/questions/examquestions/delete/' + questionsCode,
    method: 'get'
  })
}

// 导出考试题目
export function exportExamquestions(query) {
  return request({
    url: '/questions/examquestions/export',
    method: 'get',
    params: query
  })
}