/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import JDBC.DatabaseConnectionPoolManager;
import Models.Customer;
import Services.CustomerDbUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author PC
 */
public class CustomerControllerServlet extends HttpServlet {
    private CustomerDbUtil customerDbUtil;
    //(@Resource is for dataSource)
//    @Resource(name="jdbc/jsp")
    private DatabaseConnectionPoolManager dbcpm;
//    private DataSource dataSource;
    

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        try{
            customerDbUtil=new CustomerDbUtil(dbcpm);
        }catch(Exception e){
            throw new ServletException(e);
        }
    }
    
    

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
        try{
            String command=request.getParameter("command");
            if(command==null){
                command="";
            }
            switch(command){
                case "":
                    String url="http://localhost:8080/CustomerExam/Views/customer_form.jsp";
                    response.sendRedirect(url);
                    break;
                case "SHOW":
                    showCustomer(request, response);
                    break;
            }
        }catch(Exception e){
            throw new ServletException(e);
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
        processRequest(request, response);
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

    private void showCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputName=request.getParameter("name");
        List<Customer> customers=customerDbUtil.getCustomersByName(inputName);
        request.setAttribute("Customers", customers);
        RequestDispatcher dispatcher=request.getRequestDispatcher("Views/customer_form_response.jsp");
        dispatcher.forward(request, response);
    }

}
