/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;

import DAO.AccountDAO;
import DAO.BookDAO;
import DAO.CategoryDAO;
import DAO.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.Category;

/**
 *
 * @author User
 */
public class AdminServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
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
        BookDAO bookdao = new BookDAO();
        OrderDAO orderdao = new OrderDAO();
        AccountDAO accdao = new AccountDAO();
        //Top book
        List<Book> listbook = bookdao.GetTopBook();
        request.setAttribute("listbook", listbook);
        //NumofBook
        int z = bookdao.NumOfBook();
        request.setAttribute("totalbook", z);
        //NumofOrder
        int x = orderdao.NumOfOrder();
        
        request.setAttribute("totalorder", x);
        //NumofAccount
        int y = accdao.NumOfAccount();
        
        request.setAttribute("totalacc", y);
        //Profit
        int c = orderdao.Profit();
        
        request.setAttribute("profit", c);
        //Category
        CategoryDAO catedao = new CategoryDAO();
        List<Category> listcate = catedao.GetAllCategory();
        request.setAttribute("listcate", listcate);
        //Total of each category
        List<Integer> quatitycate = new ArrayList<>();
        for (int i = 1; i < listcate.size(); i++) {
            int n = catedao.QuantityCategory(i);
            quatitycate.add(n);
        }
        request.setAttribute("quatitycate", quatitycate);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
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

}
