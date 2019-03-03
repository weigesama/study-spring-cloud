# 从 hub.c.163.com 拉取镜像 /library/java:8-alpine, 因为这个镜像比较小
FROM hub.c.163.com/library/java:8-alpine
# 把 target 文件夹下的 jar 文件加入到容器的根目录, 并改名为 app.jar
ADD target/*.jar app.jar
# 暴露端口 8761 给外部调用
EXPOSE 8761
# 设置容器启动时执行的命令和参数
ENTRYPOINT ["java", "-jar", "-Dserver.port=8761", "/app.jar"]