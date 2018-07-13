<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			body{
				width: 500px;
				height: 400px;
			}
			h2{
				text-align: center;
			}
			form{
				width: 300px;
				margin-left: auto;
				margin-right: auto;
			}
			#input1{
				margin-left: 7px;
			}
			#input1,#input2{
				width: 180px;
				height: 25px;
				border-radius: 4px;
			}
			.sub{
				width: 98px;
				background-color: #4CAF50; /* Green */
			    border: none;
			    color: white;
			    padding: 5px 32px;
			    text-align: center;
			    text-decoration: none;
			    display: inline-block;
			    font-size: 16px;
			    cursor:pointer;
			    margin-left: 150px;
			}
		</style>
	</head>
	<body>
		<h2>修改属性</h2>
		<form action="" method="post">
			<label>文  件 名</label>
			<input type="text" name="" id="input1" value="" />
			<br />
			<label>文件路径</label>
			<input type="text" name="" id="input2" value="" />
			<br />
			<br />
			<input type="submit" value="修改" class="sub"/>
		</form>
	</body>
</html>