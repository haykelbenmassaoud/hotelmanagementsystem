
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv='Expires' content='0'>
<meta http-equiv='Pragma' content='no-cache'>
<meta http-equiv='Cache-Control' content='no-cache'>
<link type="text/css" rel="stylesheet" href=<%=request.getContextPath() +"/resources/css/template.css"%>>
</head>
<body>
	<div id="div-1">
		<form id="login" method="POST"
			action=<%=request.getContextPath() + "/jsp/createreservation.jsp"%>>
			<h1>Log In</h1>
			<fieldset id="inputs">
				<input id="username" name="username" type="text"
					placeholder="Username" autofocus required> <input
					id="password" name="password" type="password"
					placeholder="Password" required>
			</fieldset>
			<fieldset id="actions">
				<input type="submit" id="submit" value="Log in"> <a href="">Forgot
					your password?</a><a href="">Register</a>
			</fieldset>
		</form>
	</div>
</body>
</html>
