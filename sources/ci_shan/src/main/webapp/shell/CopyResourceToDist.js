var shell = require('shelljs');
console.log("build 完成，开始拷贝资源文件");
shell.mv('../resources/static/login.html',  '../resources/templates/login.ftl');
console.log("拷贝完成");

//cp mv rm