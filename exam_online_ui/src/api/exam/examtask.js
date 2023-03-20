import request from '@/utils/request'

// 查询创建考试列表
export function listExamtask(query) {
  return request({
    url: '/exam/examtask/list',
    method: 'get',
    params: query
  })
}

// 查询创建考试详细
export function getExamtask(examCode) {
  return request({
    url: '/exam/examtask/' + examCode,
    method: 'get'
  })
}

// 新增创建考试
export function addExamtask(data) {
  return request({
    url: '/exam/examtask/add',
    method: 'post',
    data: data
  })
}

// 新增创建考试
export function deleteExamtask(data) {
  return request({
    url: '/exam/examtask/delete',
    method: 'post',
    data: data
  })
}

// 修改创建考试
export function updateExamtask(data) {
  return request({
    url: '/exam/examtask/update',
    method: 'post',
    data: data
  })
}

// 删除创建考试
export function delExamtask(examCode) {
  return request({
    url: '/exam/examtask/delete/' + examCode,
    method: 'get'
  })
}

// 导出创建考试
export function exportExamtask(query) {
  return request({
    url: '/exam/examtask/export',
    method: 'get',
    params: query
  })
}

// 上传图片
export function uploadPhoto(examCode, data) {
  return request({
    url: '/exam/examtask/uploadphoto/' + examCode,
    method: 'post',
    data: data
  })
}