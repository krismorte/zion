# ZION

## Oracle

https://mkyong.com/maven/how-to-add-oracle-jdbc-driver-in-your-maven-local-repository/

teste Oracle

git clone https://github.com/oracle/docker-images.git

cd docker-images/OracleDatabase/SingleInstance/dockerfiles

./buildDockerImage.sh -v 18.4.0 -x

docker run -e ORACLE_PWD=oracle -p 1521:1521 oracle/database:18.4.0-xe
