```shell
docker build --file Dockerfile.jenkins -t employees-jenkins .
docker run -d -p 8082:8080 --name employees-jenkins employees-jenkins
```
