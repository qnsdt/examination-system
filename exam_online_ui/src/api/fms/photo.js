import request from '@/utils/request'

// 查询图片管理列表
export function listPhoto(query) {
  return request({
    url: '/fms/photo/list',
    method: 'get',
    params: query
  })
}

// 查询图片管理详细
export function getPhoto(photoId) {
  return request({
    url: '/fms/photo/' + photoId,
    method: 'get'
  })
}

// 新增图片管理
export function addPhoto(data) {
  return request({
    url: '/fms/photo',
    method: 'post',
    data: data
  })
}

// 修改图片管理
export function updatePhoto(data) {
  return request({
    url: '/fms/photo',
    method: 'put',
    data: data
  })
}

// 删除图片管理
export function delPhoto(photoId) {
  return request({
    url: '/fms/photo/' + photoId,
    method: 'delete'
  })
}

// 导出图片管理
export function exportPhoto(query) {
  return request({
    url: '/fms/photo/export',
    method: 'get',
    params: query
  })
}