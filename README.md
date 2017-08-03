# DrunkIrishman
Deploy instructions:
  - Run MySQL command mysql -uroot -proot < drunkIrishman.sql for import drunkIrishman.sql script;
  - Unpack DrunkIrishman.7z archive;
  - Set your path to unpacked data to src\main\resources\project.properties file, property resources.path
  - Set your MySQL server password to src\main\resources\project.properties file, property db.password
  - Build WAR file under Maven using command mvn package;
  - Copy ROOT.war file to Tomcat webapps path;
  - Restart tomcat server.
