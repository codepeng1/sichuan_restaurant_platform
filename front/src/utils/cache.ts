// 缓存工具

// 支持的缓存方案枚举
enum Schema {
  LOCALSTORAGE = 'LOCALSTORAGE',
  SESSIONSTORAGE = 'SESSIONSTORAGE'
}

class Cache {
  private schema: Schema

  constructor(schema: Schema) {
    this.schema = schema
  }

  /**
   * 设置缓存
   * @param key 键
   * @param value 值
   * @returns 返回实例对象
   */
  setCache(key: string, value: any) {
    switch (this.schema) {
      case Schema.LOCALSTORAGE:
        window.localStorage.setItem(key, JSON.stringify(value))
        break
      case Schema.SESSIONSTORAGE:
        window.sessionStorage.setItem(key, JSON.stringify(value))
        break
      default: {
        const err: never = this.schema
        console.log(err)
        break
      }
    }
    return this
  }

  /**
   * 获取缓存数据
   * @param key 键
   * @param schema 选择哪种缓存方案
   * @returns 返回缓存数据
   */
  getCache(key: string) {
    switch (this.schema) {
      case Schema.LOCALSTORAGE: {
        const value = window.localStorage.getItem(key)
        return value ? JSON.parse(value) : ''
      }
      case Schema.SESSIONSTORAGE: {
        const value = window.sessionStorage.getItem(key)
        return value ? JSON.parse(value) : ''
      }
      default: {
        const err: never = this.schema
        console.log(err)
        break
      }
    }
  }

  /**
   * 删除缓存
   * @param key 键
   * @returns 返回实例对象
   */
  removeCache(key: string) {
    switch (this.schema) {
      case Schema.LOCALSTORAGE:
        window.localStorage.removeItem(key)
        break
      case Schema.SESSIONSTORAGE:
        window.sessionStorage.removeItem(key)
        break
      default: {
        const err: never = this.schema
        console.log(err)
        break
      }
    }
    return this
  }

  /**
   * 清空缓存
   */
  clearCache() {
    switch (this.schema) {
      case Schema.LOCALSTORAGE:
        window.localStorage.clear()
        break
      case Schema.SESSIONSTORAGE:
        window.sessionStorage.clear()
        break
      default: {
        const err: never = this.schema
        console.log(err)
        break
      }
    }
  }
}

const localCache = new Cache(Schema.LOCALSTORAGE)
const sessionCache = new Cache(Schema.SESSIONSTORAGE)

export { localCache, sessionCache }
