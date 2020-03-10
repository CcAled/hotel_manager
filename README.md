![Alt text](https://github.com/CcAled/hotel_manager/blob/master/demonstration/demonstration.jpg)
## 目录
* 1.项目使用技术栈
* 2.项目部署步骤
* 3.关键技术问题的学习与解决

### 技术栈
springboot为框架，thymeleaf为模板引擎，redis作为缓存数据库，mysql作为数据库
### 项目部署步骤
* 1.配置application.properties中的mysql和redis地址、账号、密码，并完成建库建表（build.sql可导入库和示例数据）
* 2.本地图片存储文件夹默认为C:/hotel_host/img，可在WebMvcConfig中修改（hotel_host文件内为示例数据）
### 关键技术问题的学习与解决
#### 1.超卖问题的解决
1.sql语句加判断<br>
2.数据库加唯一索引防止用户重复购买<br>
3.redis预减库存,通过内存标记减少redis访问<br>
#### 2.全局异常处理拦截
定义全局异常类型GlobleException和全局异常处理器GlobleExceptionHandler<br>
#### 3.减少服务器压力的一些措施
1.使用页面级缓存thymeleafViewResolver进行渲染后存入redis中，减少渲染次数<br>
2.使用redis缓存商品对象，减库存先减内存标记再减redis，最后才更新到mysql中<br>
3.rabbitmq请求先入队缓冲，异步下单，请求出队，生成订单，减少库存，客户端定时轮询检查是否秒杀成功（未实现）<br>
#### 4.解决分布式session
1.登录时使用随机的UUID作为cookie，并将其存入redis<br>
2.编写自定义的拦截器，拦截需要权限的请求，并使用cookie识别出用户，设置在ThreadLocal中<br>
3.需要权限的请求再使用UserArgumentResolver将当前用户解析到相应的请求中<br>
#### 5.安全性设计
1.秒杀接口隐藏（未实现）<br>
2.数字公式验证码<br>
3.接口防刷限流<br>
