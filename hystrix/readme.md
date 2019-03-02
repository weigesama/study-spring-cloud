> by Yuanwl

# hystrix 学习

![10-1-hystrix简介1](attachments/10-1-hystrix简介1.png)

![10-1-hystrix简介2](attachments/10-1-hystrix简介2.png)

## 降级

可以理解为: 调用某服务没有响应时, 转而调用一个设置好的降级方法, 给用户返回提示信息, 而不是一直等待响应--这个过程就叫降级.

可使用 @HystrixCommand,@DefaultProperties 和降级方法实现降级. 触发降级的条件一般有:

- 调用的服务不可用;
- 调用的服务超时没有响应. 默认只等待1秒, 可通过在 @HystrixCommand 内的 @HystrixProperty 指定等待响应时间;
- 主调用方法内部抛异常;

代码请看: [HystrixController1.java](src/main/java/xyz/yuanwl/demo/spring/cloud/hystrix/controller/HystrixController1.java)

## 熔断

### 依赖隔离

类似于 docker 的舱壁模式把进程隔离开来, hystrix 则把线程池隔离: 某个线程池失去响应, 不会拖慢其他线程池. hystrix 自动为加了 @HystrixCommand 的方法创建这样的线程池.

### 容错

为提高微服务的高可用, 必须容错. 一般有两种方式:

1. 重试: 适用于预期只是短暂的故障, 重试几次就可能成功;
2. 熔断: 如果故障是无法评估的, 可能是长时间, 就应该用这种模式(又叫断路器模式), 把调用服务过程封装在受监控的断路器对象里.

### 熔断(断路器模式)

首先要了解断路器状态机:

![10-4-断路器模式状态机_1](attachments/10-4-断路器模式状态机_1.png)

1. 断路器默认关闭;
2. 监控: 每 n 次调用计算一次调用失败的比率, 如果在设置的百分比内, 依然关闭断路器, 否则打开断路器;
3. 断路器打开后, 进入休眠时间窗口, 在这时间内, 所有调用都返回降级方法的结果, 即降级方法临时称为主调逻辑;
4. 休眠时间结束后, 断路器进入半开状态, 如果有请求过来, 断路器就让请求到达主调用逻辑, 如果能调用, 就关闭断路器, 进入新一轮的监控计数; 否则, 断路器继续打开, 重新进入休眠时间窗口, 重新计时;

代码请看: [HystrixController2.java](src/main/java/xyz/yuanwl/demo/spring/cloud/hystrix/controller/HystrixController2.java)