# practice-3

docker run -d -p 8080:8080 \
  -e DB_URL="jdbc:sqlserver://my-login-sql-server.database.windows.net:1433;database=loginappdb;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;" \
  -e DB_USER="sqladmin@my-login-sql-server" \
  -e DB_PASS="StrongPassw0rd!" \
  --name loginapp loginapp:v5
