> by Yuanwl

# zuul 网关

## 基础理论知识

![8-1-为什么需要网关服务](../attachments/8-1-为什么需要网关服务.png)

![8-1-网关要素](../attachments/8-1-网关要素.png)

![8-1-常用网关方案](../attachments/8-1-常用网关方案.png)

有空了解一下 nginx+lua,kong,tyk.

![8-1-zuul的特点](../attachments/8-1-zuul的特点.png)

![8-1-zull的四种过滤器](../attachments/8-1-zull的四种过滤器.png)

![8-1-zuul架构图](../attachments/8-1-zuul架构图.png)

![8-1-zuul请求生命周期](../attachments/8-1-zuul请求生命周期.png)

![8-4-典型应用场景-pre](../attachments/8-4-典型应用场景-pre.png)

![8-4-典型应用场景-post](../attachments/8-4-典型应用场景-post.png)

![8-4-zuul高可用](../attachments/8-4-zuul高可用.png)

![9-1-项目架构图](../attachments/9-1-项目架构图.png)

## 简单开发步骤

1. 引入依赖:
```yml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
```
2. 配置(可选);
3. 启动类上面加注解 @EnableZuulProxy //启用 zuul 网关代理;
4. 启动项目即可使用网关;

## 自定义过滤器

1. 继承 ZuulFilter, 实现其方法;
2. 过滤器上面加 @Component 注解;

## 注意事项

- zuul 在实际项目中, 不能直接访问 db, 也不要访问 user 服务;
- 建议 zuul 通过 mq+redis 实现鉴权;
- zuul 实现跨域, 首选在 zuul 加 CorsFilter, 或者在 nginx 解决跨域问题;
