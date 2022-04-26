/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
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
        String sql = "select * from Book b join Category c on (b.category_id = c.category_id)";
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
    public Book GetBookByID(int id) {
        String sql = "select * from Book b join Category c on (b.Book_id = c.category_id) where b.Book_id = ?";
      
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category g = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                Book b = new Book(rs.getInt("Book_id"), rs.getString("Book_name"), rs.getFloat("Book_price"), rs.getInt("Book_quantity"), rs.getString("Book_image"), g);
               return b;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public void add(Book p) {
        String sql = "INSERT INTO [Book]\n" +
"           ([Book_name]\n" +
"           ,[Book_price]\n" +
"           ,[Book_quantity]\n" +
"           ,[Book_image]\n" +
"           ,[category_id])\n" +
"     VALUES\n" +
"           (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getName());
            st.setFloat(2, p.getPrice());
            st.setInt(3, p.getQuantity());
            st.setString(4, p.getImage());
            st.setInt(5, p.getCategoryid().getId());
            st.executeUpdate();
        } catch (Exception ex) {
        }
    }
    public static void main(String[] args) {
        BookDAO bookdao = new BookDAO();
        List<Book> listb = bookdao.GetAllBook();
        System.out.println(listb.get(0).getName());
    }

    public void UpdateBook(Book b) {
         String sql = "UPDATE [Book] SET [Book_name] = ?\n" +
"      ,[Book_price] = ?\n" +
"      ,[Book_quantity] = ?\n" +
"      ,[Book_image] = ?\n" +
"      ,[category_id] = ?\n" +
" WHERE Book_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, b.getName());
            st.setFloat(2, b.getPrice());
            st.setInt(3, b.getQuantity());
            st.setString(4, b.getImage());
            st.setInt(5, b.getCategoryid().getId());
            st.setInt(6, b.getId());
            st.executeUpdate();
        } catch (Exception ex) {
        }
    }

    public void DeleteBook(int id) {
        String sql = "DELETE FROM [Book]"
                + "      WHERE Book_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception ex) {
        }
    }
    public List<Book> getListByPage(List<Book> list,
            int start, int end) {
        ArrayList<Book> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Book> SearchBookByName(String search) {
        String sql = "select * from Book b join Category c on (b.Book_id = c.category_id) where b.Book_name like ?";
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%"+search+"%");
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
    public List<Book> SearchBookByCategory(int cid) {
        String sql = "select * from Book b join Category c on (b.Book_id = c.category_id) where c.category_id = ?";
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
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
    public List<Book> GetTopBook() {
        String sql = "select TOP 4 * from Book b join Category c on (b.Book_id = c.category_id)";
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
