mvn clean install
java -jar target\silver-bars-marketplace-1.0-SNAPSHOT-jar-with-dependencies.jar > target\testResults.out
SLEEP 3600
WAIT 3600
TIMEOUT  /t 360000 /nobreak