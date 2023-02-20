/**
 * 根据process.env.NODE_ENV 来判断环境
 *  开发环境: development
 *  生产环境: production
 *  测试环境: test
 */

let BASE_URL = ''
const TIME_OUT = 10000

if (process.env.NODE_ENV === 'development') {
  BASE_URL = '/api'
} else if (process.env.NODE_ENV === 'production') {
  BASE_URL = '/prod'
} else if (process.env.NODE_ENV === 'test') {
  BASE_URL = '/test'
}

export { BASE_URL, TIME_OUT }
