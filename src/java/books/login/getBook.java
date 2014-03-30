/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package books.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp14
 */
public class getBook extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet getBook</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getBook at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        doPost(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        boolean state=false;
        String uname,title;
        Connection conn=null;
        ResultSet rs=null;
        String url;
        
        String br="<br><br/>";
        uname = request.getParameter("uname");
        title= request.getParameter("title");
        Statement st = null;
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control","no-cache");
        PrintWriter out = response.getWriter();
        //out.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        String msg="";
        //out.append("<result>");
        msg+="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        msg+="<result>";
        url = "jdbc:mysql://localhost:3306/mydb?zeroDateTimeBehavior=convertToNull";
        try{
            Class.forName("com.mysql.jdbc.Driver");
               conn = DriverManager.getConnection(url, "root", "root");
        }
        catch(SQLException e){
                e.printStackTrace();
        }
              
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String qry2 ="SELECT * FROM books where title LIKE '"+title+"'";
        try{
            st = (Statement)conn.createStatement();
            rs = st.executeQuery(qry2);
        
         while(rs.next()){
             if(rs.getString("uname").equals(uname)){
                     continue;
             }
             else{
                 state = true;
                
                 /*out.append("<user>") ;
                 out.append("<uname>").append(rs.getString("uname")).append("</uname>");
                 out.append("<price>").append(rs.getString("price")).append("</price>");
                 out.append("</user>");*/
                 msg += "<user>";
                 msg += "<uname>" + rs.getString("uname")+"</uname>";
                 msg += "<price>" + rs.getString("price")+"</price>";
                 msg += "</user>";
             }
        }
         if(!state){
             msg+="<user><uname>-</uname>";
             msg+="<price>-</price></user>";
         }
         //out.append("</result>");
         msg += "</result>";
         out.write(msg);
         out.flush();
         
         }
        
        catch(Exception e){
            
        }
        finally{
            if(rs != null){
                try{
                    rs.close();                    
                }
                catch(Exception e){
                    
                }
            }
            if(st != null){
                try{
                    st.close();                    
                }
                catch(Exception e){
                    
                }
            }
            if( conn != null){
                try{
                    conn.close();
                }
                catch(Exception e){
                    
                }
            }
        }
                   
            
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
