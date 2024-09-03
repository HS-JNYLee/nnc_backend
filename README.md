EC2 코드 적용 방법
============

1. Git Repo Update

  * 프로젝트 폴더로 들어간다.
  * Git pull을 해 코드를 업데이트 한다.

  ~~~command
  git pull origin
  ~~~
  
2. Docker Container 내리기

 * Docker Container를 내리고 삭제한다.
   
~~~command
docker compose down
~~~

3. 프로젝트 빌드

 * 프로젝트 jar 파일을 만들어준다.
   
 ~~~command
 sh gradlew clean build
 ~~~

4. Docker Container 올리기

* Docker Container를 올린다.

~~~command
docker compose up --build -d
~~~

5. Docker Container 및 Swagger 정상 작동 여부 확인

* 아래 Command를 쳤을 때 같은 화면이 나와야 한다.

~~~command
docker ps -a
~~~

 ![image](https://github.com/user-attachments/assets/9d84f6a4-7c2c-418b-9290-01d72b55f5c6)

*  ip:8080/swagger-ui/index.html로 접속했을 때 다음과 같이 접속되어야 한다.
  
 ![image](https://github.com/user-attachments/assets/2c5779ae-7fe0-416f-93c2-5835b7c310dc)



