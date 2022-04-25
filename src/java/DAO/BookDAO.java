/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.Category;

/**
 *
 * @author User
 */
public class BookDAO extends DBContext{
    public List<Book> GetAllBook() {
        String sql = "select * from Book b join Category c on (b.Book_id = c.category_id)";
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category g = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                Book b = new Book(rs.getInt("Book_id"), rs.getString("Book_name"), rs.getFloat("Book_price"), rs.getInt("Book_quantity"), rs.getString("Book_image"), g);
                list.add(b);
            }
        } catch (SQLException e) {
        }
        return list;
    }
}
