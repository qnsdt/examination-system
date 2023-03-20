import request from '@/utils/request'

// 查询考试实时数据列表
export function listTaskanswer(query) {
  return request({
    url: '/onlineexam/taskanswer/list',
    method: 'get',
    params: query
  })
}

// 查询考试实时数据详细
export function getTaskanswer(examCode) {
  return request({
    url: '/onlineexam/taskanswer/' + examCode,
    method: 'get'
  })
}

// 新增考试实时数据
export function addTaskanswer(data) {
  return request({
    url: '/onlineexam/taskanswer',
    method: 'post',
    data: data
  })
}

// 修改考试实时数据
export function updateTaskanswer(data) {
  return request({
    url: '/onlineexam/taskanswer',
    method: 'put',
    data: data
  })
}

// 删除考试实时数据
export function delTaskanswer(examCode) {
  return request({
    url: '/onlineexam/taskanswer/' + examCode,
    method: 'delete'
  })
}

// 导出考试实时数据
export function exportTaskanswer(query) {
  return request({
    url: '/onlineexam/taskanswer/export',
    method: 'get',
    params: query
  })
}