/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ShopController;

import DAO.BookDAO;
import DAO.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Book;
import model.Category;

/**
 *
 * @author User
 */
public class ShopServlet extends HttpServlet {

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
            out.println("<title>Servlet ShopServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopServlet at " + request.getContextPath() + "</h1>");
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
        //Step 1: Procesing Data
        //1. List Category of Book
        CategoryDAO catedao = new CategoryDAO();
        List<Category> listcate = catedao.GetAllCategory();
        request.setAttribute("listcate", listcate);
        //2. List Book
        BookDAO bookdao = new BookDAO();
        List<Book> listbook_raw = bookdao.GetAllBook();
        int numPs, numperPage, numpage, start, end, page;
        numPs = listbook_raw .size();
        numperPage = 6;
        numpage = numPs / numperPage + (numPs % numperPage == 0 ? 0 : 1);
        String tpage = request.getParameter("page");
        try {
            page = Integer.parseInt(tpage);
        } catch (NumberFormatException e) {
            page = 1;
        }
        start = (page - 1) * numperPage;
        if (page * numperPage > numPs) {
            end = numPs;
        } else {
            end = page * numperPage;
        }
        List<Book> listbook = bookdao.getListByPage(listbook_raw , start, end);
        request.setAttribute("num", numpage);
        request.setAttribute("page", page);
        request.setAttribute("listbook", listbook);
        
        //3.Search
        String search = request.getParameter("search");
        if(search!=null){
            List<Book> listsearch = bookdao.SearchBookByName(search);
            numPs = listsearch.size();
        numperPage = 6;
        numpage = numPs / numperPage + (numPs % numperPage == 0 ? 0 : 1);
         tpage = request.getParameter("page");
        try {
            page = Integer.parseInt(tpage);
        } catch (NumberFormatException e) {
            page = 1;
        }
        start = (page - 1) * numperPage;
        if (page * numperPage > numPs) {
            end = numPs;
        } else {
            end = page * numperPage;
        }
        listbook = bookdao.getListByPage(listsearch , start, end);
        request.setAttribute("num", numpage);
        request.setAttribute("page", page);
        request.setAttribute("listbook", listbook);
        }
       
        //4. Category Tab
        String cid_raw = request.getParameter("cid");
        if(cid_raw!=null){
        int cid = Integer.parseInt(cid_raw);
        List<Book> listsearcht = bookdao.SearchBookByCategory(cid);
        numPs = listsearcht.size();
        numperPage = 6;
        numpage = numPs / numperPage + (numPs % numperPage == 0 ? 0 : 1);
         tpage = request.getParameter("page");
        try {
            page = Integer.parseInt(tpage);
        } catch (NumberFormatException e) {
            page = 1;
        }
        start = (page - 1) * numperPage;
        if (page * numperPage > numPs) {
            end = numPs;
        } else {
            end = page * numperPage;
        }
        listbook = bookdao.getListByPage(listsearcht , start, end);
        request.setAttribute("num", numpage);
        request.setAttribute("page", page);
        request.setAttribute("listbook", listbook);
        }
        //Step 2: Send Redirect User
        request.getRequestDispatcher("shop.jsp").forward(request, response);
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
