x:
set classpath=%classpath%;d:\classes111.jar;
set path=%path%;c:\j2sdk1.4.1_01\bin;c:jdk1.3.1_02\bin;
set classpath=%classpath%;d:\apache tomcat 4.0\common\lib\servlet.jar;
cd rmiserver
javac *.java
rmic atmserver
start rmiregistry
java atmserver