/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;

import DAO.BookDAO;
import DAO.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Book;
import model.Category;

/**
 *
 * @author User
 */
public class UpdateBookServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateBookServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateBookServlet at " + request.getContextPath() + "</h1>");
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
//        HttpSession session = request.getSession(true);
//        Account a = (Account) session.getAttribute("admin");
//        if (a == null){
//            PrintWriter out = response.getWriter();
//            out.println("Access denied");
//        }else{
        String id_raw = request.getParameter("id");
        int id = Integer.parseInt(id_raw);
        BookDAO bookdao = new BookDAO();
        Book p = bookdao.GetBookByID(id);
        List<Book> list = new ArrayList<>();
        list.add(p);
        request.setAttribute("list", list);
        request.getRequestDispatcher("UpdateBook.jsp").forward(request, response);
//        }
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
        String id_raw = request.getParameter("id");
        int id = Integer.parseInt(id_raw);
        String name = request.getParameter("name");
        String price_raw = request.getParameter("price");
        float price = Float.parseFloat(price_raw);
        String quanlity_raw = request.getParameter("quanlity");
        int quanlity = Integer.parseInt(quanlity_raw);
        String image = request.getParameter("image");
        String category_raw = request.getParameter("category");
        int categoryid = Integer.parseInt(category_raw);
        
        CategoryDAO catedao = new CategoryDAO();
        Category c = new Category(categoryid, catedao.GetNameCategoryByID(categoryid));
       Book b = new Book(id, name, price, quanlity, image, c);
        
         BookDAO bookdao = new BookDAO();
        bookdao.UpdateBook(b);
        response.sendRedirect("list-book");
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
