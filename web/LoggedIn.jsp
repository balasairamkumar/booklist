<%-- 
    Document   : LoggedIn
    Created on : Mar 10, 2014, 9:39:21 AM
    Author     : hp14
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LoggedIn</title>
        <script>
            var req = false;
            function ckBooks()
            {
                var title = document.getElementById("title");
                var name = document.getElementById("uname");
                var res = document.getElementById("result");
                if(window.ActiveXObject){
                    req = new ActiveXObject("Microsoft.XMLHTTP");
                }
                else if (window.XMLHttpRequest){
                    req = new XMLHttpRequest();
                }
                if(req != null){
                    req.open("GET","getBook?title="+title.value+"&uname="+name.value,true);
                    req.onreadystatechange = function(){
                        
                       /* if(req.readyState == 1){
                            res.innerHTML = "<b>Loading....</b>";
                        }
                        if(req.readyState == 2){
                            res.innerHTML = "Loaded..!!";
                        }*/
                        if(req.readyState == 4){
                            //res.innerHTML = req.responseText;
                            var response = req.responseXML;
                            
                            var docRoot = response.getElementsByTagName("result");
                            var str = "";
                            str += "<table border=1><tr><td>Name</td><td>Price Opted</td></tr>";
                            var i = 0;
                            
                            while(i < docRoot[0].childNodes.length){
                                str += "<tr><td>"+docRoot[0].childNodes[i].childNodes[0].firstChild.nodeValue+"</td>";
                                str += "<td>"+docRoot[0].childNodes[i].childNodes[1].firstChild.nodeValue+"</td></tr>";
                                i++;
                            }
                            res.innerHTML = str+ "</table>";
                            
                        }
                    }
                    req.send(null);
                    
                }
            }
        </script>
    </head>
    <body>
        <h1>Logged In</h1>
        <jsp:scriptlet>
            HttpSession mySess = request.getSession(false);
            String msg = mySess.getAttribute("msg").toString();                  
            String uname = mySess.getAttribute("user").toString();
        </jsp:scriptlet>
        Your Login Name : <%= uname%><br/>
        <form id="bookform" method="POST" action=<%=response.encodeURL("checkBooks")%>>
         <h2>Wish-List of Books </h2>
         User Name :<input id="uname" type="label" value=<%=uname%> /><br/>
         Book Title:<input id="title" type="text" name="title" onchange="ckBooks();"  /><br/>
        Author    :<input id="author" type="text" name="author"/><br/>
        Price     :<input id="price" type="text" name="price" /><br/>
        <input type="submit" value="submit"/>
                                
        </form>
        <div id="result">
            
        <%if (msg != null){
            %><%=msg%><%}%><br/>
           </div>
    </body>
</html>
