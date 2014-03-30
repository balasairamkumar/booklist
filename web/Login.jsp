<%-- 
    Document   : Login
    Created on : Mar 10, 2014, 9:20:47 AM
    Author     : hp14
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
       <h2> Login </h2>
        <form action=<%=response.encodeURL("LoginServlet")%>>
              Username : <input type='text' name="username" /><br  />
            Password : <input type="text" name="password" /> <br    />
            <input type="submit" value="submit"/>
        </form>
    </body>
</html>
