# Hibernate lesson test code
## MySql database
Hibernate configuration is provided for MySql datanase running on port 3306, database `company`, user `test`, pasword `mypass`
For quick start use docker container:
```bash
docker run -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_USER=test -e MYSQL_PASSWORD=mypass -e MYSQL_DATABASE=company -d -p 3306:3306 mysql/mysql-server:latest
```
