在Tomcat 的content.xml的content標籤裡面放下面這三個Resource，原本的專案跟老師的專案都可以運行
要注意，自己的SQL裡面也要建老師的上課的資料庫跟資料表才能測試！！！

	<Resource name="connectSQLServerConn/OrderSystem"
		type="javax.sql.DataSource" auth="Container" username="ProFit"
		password="ProFit"
		driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver"
		url="jdbc:sqlserver://localhost:1433;databaseName=ProFitDB;encrypt=true;trustServerCertificate=true" />

	<Resource name="connectMySQLConn/OrderSystem"
		type="javax.sql.DataSource" auth="Container" username="ProFit"
		password="ProFit" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/ProFitDB?serverTimezone=UTC" />
