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
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp14
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession sess;
        boolean state=false;
        String uname,pwd;
        Connection conn=null;
        ResultSet rs=null;
        String url;
        String msg="<br><br/>";
        uname = request.getParameter("username");
        pwd= request.getParameter("password");
        Statement st = null;
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
        String qry = "SELECT * FROM users WHERE username='"+uname+"' AND password='"+pwd+"'";
        try{
            st = (Statement)conn.createStatement();
            rs = st.executeQuery(qry);
            
            if(!rs.next()){
                state = false;
            }
            else{
                state = true;
            }
           
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
                   
            if(state){
                sess = request.getSession(true);
                sess.setMaxInactiveInterval(60);
                sess.setAttribute("user", uname);
                sess.setAttribute("msg","Logged In");
                response.sendRedirect(response.encodeRedirectURL("LoggedIn.jsp"));
            }
            else{
                response.sendRedirect(response.encodeRedirectURL("Login.jsp"));
            }
        }
        
        

//processRequest(request, response);
    

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
        processRequest(request, response);
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
