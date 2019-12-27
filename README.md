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
2019-12-22:
更新密码完成、获取指定用户信息、创建角色完成、批量删除角色完成
==>明天从获取角色列表开始
2019-12-23:
获取所有角色完成、更新角色信息完成、获取角色对应权限完成、
更新角色权限完成、创建权限完成、批量删除权限完成、获取树形权限列表完成、
更新权限信息完成、获取所有会员等级完成
==========
创建mall-pms: 本模块为商品模块 接口服务(dubbo提供者)
创建品牌完成
===>明天从批量删除品牌开始
2019-12-24:
批量删除品牌完成、按名称获取分页品牌完成、获取所有品牌完成、
更新品牌完成、获取指定品牌完成、批量更新显示状态完成、
批量更新厂家制造商状态完成、
(商品分类)-----
创建商品分类完成、删除商品分类完成、获取指定商品分类完成、
更新商品分类完成
=====>明天从修改显示状态开始
2019-12-25:
商品分类--批量修改显示状态完成、批量修改状态栏显示完成、
分页获取商品分类信息完成、获取一级分类及子分类完成、
商品属性--商品属性创建完成、商品属性更新完成、获取指定商品属性信息完成、
批量删除商品属性完成、分页查询商品属性完成、按分类获取商品属性和属性分类id完成、
商品属性分类--创建商品属性分类完成、更新商品属性分类完成、获取指定商品属性分类完成、
获取分页商品属性分类完成、删除商品属性分类完成
======>明天从获取所有商品属性分类及旗下属性(解决问题)开始
2019-12-26：
解决昨天的获取商品属性分类及旗下属性的问题：
记录一下这个问题的另一种办法
问题: 如果多表查询，表的一些字段相同，如：id,name 多表里面都有这两个字段，然而查出来后的结果封装如果映射
都是属于符合驼峰命名的属性,那么则会出现封装数据结果错误,原来的解决办法是: 给其中一张表的列表使用别名,
然后修改映射结果集resultMap的结果映射,但现在我的想法是,xml都是mybatis自动生成的,不会去改这些文件,
而是自己扩展功能新建一个xml和dao去继承自动生成的,而在扩展的xml中,(除了加一对一或一对多的属性另外加的字段外,
原先的字段都不加)也不想重新写结果集resultMap,而是
直接使用它父类的resultMap就行,解决办法是定义一个sql列片段,在这个片段中使用变量来控制列名(比如给表列名加个前缀),
在查询使用这个片段时,给这个变量赋值即可,在结果集中association 或者collection 标签中配置columnPrefix前缀,
即可解决多表字段名相同,类属性也相同而又不要重新写一大堆的映射
--更多详情参阅mybatis官方文档：https://mybatis.org/mybatis-3/zh/sqlmap-xml.html
批量更新sku库存完成(批量更新使用replace into和insert into差不多,前者id存在则更新,不存在则新建)
按商品id或sku编码模糊查询完成
=======>明天从保存商品开始
2019-12-27:
添加mall-cms: 本模块为商品专题优选模块
商品保存(大操作)未完
=======>明天开始从保存商品的回滚和添加方法不存在问题解决