
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Index 1</title>
</head>
<body>

<h1>
    Fuck Deserialization And MemShell!
</h1>
<a href="${pageContext.request.contextPath}/turnoff">Turn Off Security Mechanism</a>
<pre>
    Here's URL Mappings:
    - /api/fastjson is for FastJSON 1.2.24 (POST method, application/x-www-form-urlencoded, Param: data)
    - /api/log4j is for Log4J 2.12.1 (GET method, Param: data)
    - /api/upload4j is for uploading evil files (POST method, multipart/form-data, Param: data, JSP Only, Uploaded Path: /uploads/UUID.jsp)
        - Upload Max Allowed Size: 10MiB
    - /api/xstream is for XStream 1.4.6 (POST method, application/x-www-form-urlencoded, Param: data)
    - /api/snakeyaml is for SnakeYAML 1.23 (POST method, application/x-www-form-urlencoded, Param: data)
    - /shiro/anonLogin is for Shiro 1.2.4, rememberMe deserialization as Shiro-550.
        - /shiro/anon* is for anonymous users, /shiro/auth* is for authenticated users.
        - Other related paths are: /shiro/anonShow /shiro/authSucc /shiro/anonFail
        - /shiro/authCleanUp for deleting all files uploaded (including other guys).
    - / is for index.jsp, which means this page, file located at webapp/views/index.jsp
    - /turnoff is for turning off security mechanism (GET method, only need to call once.)
    Something might be useful:
    - Commons Collections 3.1
    - XBean Naming 4.5
    - Commons Codecs 1.15
    - Commons FileUpload 1.4
    - Commons IO 2.5
    Web Container: Tomcat 8+ / Resin 4+ / JDK 8u261+ if Windows / OpenJDK 11 if Linux
    Reset at any time and Runs in an isolated environment.
</pre>
</body>
</html>