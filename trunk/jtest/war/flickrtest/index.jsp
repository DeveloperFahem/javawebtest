<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to the Flickr API Test</title>
</head>
<body>
<h1>Welcome to the Flickr API Test</h1>
<p>This Application will:</p>
<ol><li> Ask you to click on a Link to ask Flickr for Authentication</li>
<li>Flickr will ask you for Sign in (if you are not signed in)</li>
<li>Flickr will ask you to grant read access for this Demo App (Bernd's SSO Demo)</li>
<li>Flickr will redirect your Broweser back to this App (with an short token)</li>
<li>This App will use the token to request a full token from Flickr</li>
<li>This App will display you the result of this request (incl. your username)</li>
</ol>
<p>So here is the Link to Flickr to start it: <a href="http://flickr.com/services/auth/?api_key=3d938a92a71b43d6f04841d48bedf698&perms=read&api_sig=1f72bff54d65b59e302c7ed8fc2b409d">http://flickr.com/services/auth/?api_key=...&perms=read&api_sig=...</a></p>
<hr />
  <p><a href="mailto:Bernd.Eckenfels@gmail.com">Bernd.Eckenfels</a></p>
  <p>You find the GPL code for this WAR on <a href="http://javawebtest.googlecode.com/">javawebtest.googlecode.com</a></p>
</body>
</html>