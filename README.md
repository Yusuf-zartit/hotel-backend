# hotel-backend

Project download, import and run 
1- Download project on gitlab
git clone https://github.com/Yusuf-zartit/hotel-backend.git

2- Open the project with intellij idea

3- Import pom.xml process
    Click project structure(File > Project Structure)
    Choose Sdk version in Project (should be upper java 21)
    import pom.xml in Modules. Modules --> Add --> Import Module --> path of project's pom.xml.
    Choose Sdk version again in SDKs.
    
4- Maven install.
5- Run /startup/application.java class.


# Start Dependent Containers

```
docker-compose up -d
```


- Postgresql should be running and bound to port 5432 on localhost


