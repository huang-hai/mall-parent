本项目采用分布式开发，项目之间用dubbo通讯，zookeeper作为注册中心
mall-commons: 本模块为工具包，封装工具类
mall-api: 本模块为service接口、和一些公用的依赖，依赖mall-commons，全部模块通用
mall-ums: 本模块为后台管理员功能接口服务(dubbo提供者)
mall-web: 本模块为web唯一入口
2019-12-19：
添加校验AOP：环绕通知，在执行方法前如果参数校验出错，则不往下执行，返回提示
添加快速校验配置：接收参数校验如果遇到出错则不在继续校验
添加后台用户注册、退出、获取用户信息、用户分页列表、删除用户功能
==>明天从获取用户权限开始
2019-12-20:
获取用户权限功能完成、添加或更新权限完成、获取用户角色列表完成
刷新token完成
==>明天从更新用户密码开始