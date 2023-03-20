import request from '@/utils/request'

// 获得题目标题内容
export function getContent(query) {
return request({
    url: '/onlineexam/content',
    method: 'get',
    params: query
})
}
  
// 获得题目全部内容
export function getQuestionsData(data) {
    return request({
      url: '/onlineexam/data',
      method: 'post',
      data: data
    })
}