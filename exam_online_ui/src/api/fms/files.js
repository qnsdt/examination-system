import request from '@/utils/request'

// 查询文件管理列表
export function listFiles(query) {
  return request({
    url: '/fms/files/list',
    method: 'get',
    params: query
  })
}

// 查询文件管理详细
export function getFiles(fileId) {
  return request({
    url: '/fms/files/' + fileId,
    method: 'get'
  })
}

// 新增文件管理
export function addFiles(data) {
  return request({
    url: '/fms/files',
    method: 'post',
    data: data
  })
}

// 修改文件管理
export function updateFiles(data) {
  return request({
    url: '/fms/files',
    method: 'put',
    data: data
  })
}

// 删除文件管理
export function delFiles(fileId) {
  return request({
    url: '/fms/files/' + fileId,
    method: 'delete'
  })
}

// 导出文件管理
export function exportFiles(query) {
  return request({
    url: '/fms/files/export',
    method: 'get',
    params: query
  })
}