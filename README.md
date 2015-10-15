#Maliang

Maliang(马良), the name of this project comes from a character of folk tail in China who has a magical brush by which he can draw any thing and turn it to real thing.

##Motivation
In most of J2EE projet, there are a lot of codes are about CRUD which refer to the basic functions of a database or persistence layer in a software system. By using Maliang, you can generate these template codes based on your DDL
```
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
```

## Under the hood
Maliang use sql parser to parser DDL, then convert the result to a VelocityContext.   
The *SourceCodeGenerator.generate(VelocityContext context)* will generate the code with the template and output directory, so acctually you could generate code with your own context builder and your own tempalte.

##Some rules
1. **Directroy and File Name**
The *SourceCodeGenerator* will replace automatically replace the directory path and the file name with the value in VelocityContext. Suppose you have a template file called  ```/${basepackage}/${name}Dao.java```, and in the context the ```${basepackage}``` equals ```com.github.maliang``` and the ```${name}``` equals ```jadetang```, you will get a new file ```/com/github/maliang/jadetangDao.java``` 

2. **Database table naming convention**
Usually we name the database objects in a different way comparing we name the java objects, with the custom config file, you can control how the database object name are converted to the java object name.Here is a config examle
```
name {
  separator = "T_APP"
  table.prefix = "F"
  column.prefix = "_"
}
```
