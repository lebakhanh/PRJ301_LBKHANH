/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CartController;

import DAO.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import model.Cart;
import model.Order;

/**
 *
 * @author User
 */
public class CheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
        //Step 1: Get infomation of CHECKOUT
        HttpSession session = request.getSession(true);
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String note = request.getParameter("note");

        Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("carts");
        if (carts != null) {
            float totalPrice = 0;
            for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                Integer productId = entry.getKey();
                Cart cart = entry.getValue();
                totalPrice += cart.getBook().getPrice() * cart.getQuatity();
            }
            OrderDAO orderdao = new OrderDAO();
  
            Order order = new Order(name, 0, email, phone, address, note, "0", totalPrice);
            
//            System.out.println(order.getFullname());
//            System.out.println(order.getEmail());
//            System.out.println(order.getPhone_number());
//            System.out.println(order.getAddress());
//            System.out.println(order.getNote());
//            System.out.println(order.getStatus());
//            System.out.println(order.getTotal_money());

            //Get ID of order    
            int n = orderdao.CreateOrderAndReturnId(order);
//            System.out.println(n + "=============");
//            //Save Order to Order Detail
//            for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
//                Integer productId = entry.getKey();
//                Cart cart = entry.getValue();
//                System.out.println(cart.getShoes().getName());
//                System.out.println(cart.getShoes().getId());
//                System.out.println(cart.getShoes().getPrice());
//                System.out.println(cart.getQuantity());
//                System.out.println(cart.getQuantity()*cart.getShoes().getPrice());
//            }
//            
            
            
            orderdao.SaveOrderDetail(n, carts);
            
            //Remove Carts
            session.removeAttribute("carts");
            response.sendRedirect("shop");
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
