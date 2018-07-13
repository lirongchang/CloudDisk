<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>网盘登录</title>
		<style type="text/css">
			.wrapper{
				width: 1000px;
				height:402px;
				background-color: aqua;
				margin-top: 100px;
				margin-left: auto;
				margin-right: auto;
			}
			.wrapper img{
				float: left;
			}
			.login{
				width: 310px;
				height: 402px;
				background-color: #DDDDDD;
				float: left;
			}
			.login h2{
				text-align: center;
				margin-top: 60px;
				color: cornflowerblue;
			}
			.login form{
				width: 100%;
				
				height: 300px;
			}
			form input{
				width: 180px;
				height: 30px;
				border-radius: 4px;
				border: 1px;
				margin-left: 5px;
			}
			.login label{
				padding-left: 50px;
			}
			.but{
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
			}
			
			.input1{
				padding-top: 20px;
			}
		</style>
	</head>
	<body>
		<div class="wrapper">
			<img src="images/timg.jpg"/>
			<div class="login">
				<h2>网盘注册</h2>
				<form action="/CloudDisk/userAction?method=register" method="post">
					<div class="input1">
						<label>账号   </label>
						<input type="text"  name="username" placeholder="请输入账号" />
					</div>
					
					<br/>
					<div class="input2">
						<label>密码   </label>
						<input type="password" name="password" placeholder="请输入密码" />
					</div>
					<br />
					<div class="input3">
						<label>邮箱   </label>
						<input type="email" name="email" placeholder="请输入邮箱" />
					</div>
					<br />
					<br/>
					<input type="submit" class="but" value="注册" style="margin-left: 50px;"/>
					<input type="reset" class="but" value="重置" style="margin-left: 20px;"/>
				</form>
			</div>
		</div>
	</body>
</html>