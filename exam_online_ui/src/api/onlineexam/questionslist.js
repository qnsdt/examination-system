import request from '@/utils/request'

// 获得题目标题内容
export function getQuestionsList(query) {
return request({
    url: '/onlineexam/questionslist',
    method: 'get',
    params: query
})
}

