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
import model.Category;

/**
 *
 * @author User
 */
public class CategoryDAO extends DBContext {

    public List<Category> GetAllCategory() {
        String sql = "select * from [Category]";
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt("category_id"), rs.getString("category_name")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public String GetNameCategoryByID(int id) {
        String sql = "select category_name from [Category] where category_id = ?";
        String n = "";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                n = rs.getString("category_name");
            }
        } catch (SQLException e) {
        }
        return n;
    }

    public int QuantityCategory(int id) {
        String sql = "select COUNT(*) as 'TotalC' from Book where category_id = ?";
        int n = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                n = rs.getInt("TotalC");
                return n;
            }
        } catch (Exception e) {
        }
        return n;
    }
    

    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        List<Category> list = dao.GetAllCategory();
        System.out.println(list.get(0).getName());
    }

    public void AddCategory(String name) {
        try {
            String sql = "INSERT INTO [Category]\n"
                    + "           ([category_name])\n"
                    + "     VALUES\n"
                    + "           (?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    public void DeleteCategory(int id) {
        String sql = "DELETE FROM [Category]\n" +
"      WHERE category_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception ex) {
        }
    }
}
