> by Yuanwl

## RestTemplate 调用微服务的三种方式

看：xyz.yuanwl.demo.spring.cloud.order.controller.ClientController


## 客户端负载均衡器 ribbon

eureka 是客户端发现，其负载均衡是软负载，是由 ribbon 客户端负载均衡器实现的，而后者则是 spring-cloud 基于 Netflix-Ribbon 的二次封装。RestTemplate、Feign、Zuul 都用到了 ribbon。

### ribbon 的核心

1. 服务发现：根据服务名拿到实例列表；
1. 服务选择规则：依据规则策略从实例列表中选出合适的有效实例；
1. 服务监听：剔除失效的服务；

### ribbon 的主要流程

1. ServerList：获取所有可用实例列表；
1. ServerListFilter：过滤掉一部分地址；
1. IRule：选择一个合适的实例；

### ribbon 的主要源码解读

1. org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient.choose
1. org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient.getServer(java.lang.String)
1. org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient.getLoadBalancer
1. com.netflix.loadbalancer.BaseLoadBalancer.getAllServers
1. com.netflix.loadbalancer.BaseLoadBalancer.chooseServer：
    ```java
    public Server chooseServer(Object key) {
        if (this.counter == null) {
            this.counter = this.createCounter();
        }

        this.counter.increment();
        if (this.rule == null) {
            return null;
        } else {
            try {
                return this.rule.choose(key); //这里的rule，默认是RoundRobinRule，也就是轮询负载均衡规则
            } catch (Exception var3) {
                logger.warn("LoadBalancer [{}]:  Error choosing server for key {}", new Object[]{this.name, key, var3});
                return null;
            }
        }
    }
    ```

我们可以在 application.yml 设置用哪种负载均衡规则（一般不用设置）。