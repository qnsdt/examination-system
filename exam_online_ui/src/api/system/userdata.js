import request from '@/utils/request'

// 查询用户选择列表
export function listUser(query) {
  return request({
    url: '/system/userdata/list',
    method: 'get',
    params: query
  })
}

export function listChoiceUser(query) {
    return request({
      url: '/system/userdata/choicelist',
      method: 'get',
      params: query
    })
  }