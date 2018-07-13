create database cloudDisk;

use cloudDisk;

Create table user( userId INT PRIMARY KEY AUTO_INCREMENT, Username varchar(32), Password varchar(64), Email varchar(64) );

Create table directory(
dirId INT  PRIMARY KEY AUTO_INCREMENT,
dirName varchar(32),
createTime DATE,
updateTime DATE
);

Create table file(
fileId INT PRIMARY KEY AUTO_INCREMENT,
fileName varchar(32),
filePath varchar(128),
fileType varchar(32),
fileSize varchar(32),
uploadTime DATE,
updateTime DATE,
downloadNum INT,
userId INT,
dirId INT 
);
