<a name="readme-top"></a>
<h3 align="center">Trang web Quản lý thiết bị </h3>
<!-- TABLE OF CONTENTS -->
<details>
  <summary>Mục lục</summary>
  <ol>
    <li><a href="#usage">Chuẩn bị tệp tin application.properties</a></li>
    <li><a href="#roadmap">Compile project</a></li>
    <li><a href="#contributing">Chạy project (nếu không dùng Docker)</a></li>
    <li><a href="#license">Tạo docker file</a></li>
    <li><a href="#contact">Tạo docker image</a></li>
    <li><a href="#acknowledgments">Chạy docker</a></li>
    <li><a href="#acknowledgments">Liên hệ</a></li>
  </ol>
</details>
<!-- GETTING STARTED -->
## Cài đặt trang web

Clone dự án về bằng lệnh:
```git
git clone https://gitlab.siu.edu.vn/benfcquang/device_mange_website.git
```
### Chuẩn bị tệp tin application.properties

Chúng ta cần chuẩn bị một tệp tin config ``src/main/resources/application.properties`` như sau, đảm bảo điền đầy đủ các thông tin yêu cầu:

```
# DATABASE
# ===============================
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://<MYSQL-HOST>:<MYSQL-PORT>/<MYSQL-DATABASE>
spring.datasource.username=<MYSQL-USERNAME>
spring.datasource.password=<MYSQL-PASSWORD>

# ===============================
# JPA / HIBERNATE
# ===============================
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.current_session_context_class=org.springframework.orm.hibernate5.SpringSessionContext

server.servlet.context-path=
server.error.whitelabel.enabled=false

#tạo foler chứa ảnh và bill : imageDevice, imageBill
upload.imageDevice=<E:/Trung/imageDevice/> (ví dụ)
upload.imageBill=<E:/Trung/imageBill/> (ví dụ)

#tạo đường dẫn
imageDevice.location = <E:\\Trung\\imageDevice\\> (ví dụ)
imageBill.location=  <E:\\Trung\\imageBill\\> (ví dụ)
                   
```
### Compile project
```
mvn clean install
```
### Chạy project (nếu không dùng Docker)
```
cd/target
java -jar DeviceManager-0.0.1-SNAPSHOT.jar
```
### Tạo docker file
Chúng ta cần chuẩn bị một tệp tin docker ``src/main/resources/Dockerfile`` như sau, đảm bảo điền đầy đủ các thông tin yêu cầu:
```
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} DeviceManager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/DeviceManager-0.0.1-SNAPSHOT.jar"]
```
### Tạo docker image
```
docker build -t devicemanager .
```
### Chạy docker
```
docker run -p <PORT-TO-EXPOSE>:<PORT-PROJECT-USED> devicemanager
```

<!-- CONTRIBUTING -->
## Contributing and contact
Trần Hàm Dương - tranhamduong@siu.edu.vn
Đặng Nhật Trung - benfcquang@gmail.com

<p align="right">(<a href="#readme-top">back to top</a>)</p>


