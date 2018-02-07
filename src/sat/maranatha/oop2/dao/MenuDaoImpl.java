package sat.maranatha.oop2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sat.maranatha.oop2.entity.Category;
import sat.maranatha.oop2.entity.Menu;
import sat.maranatha.oop2.utility.Koneksi;

/**
 *
 * @author Anthony=15720100
 */
public class MenuDaoImpl implements DaoService<Menu>{

    @Override
    public int addData(Menu object) {
        Timestamp t=new Timestamp(System.currentTimeMillis());
        int result=0;
        try {
            try(Connection connection = Koneksi.createConnection()){
                connection.setAutoCommit(false);
                String query ="INSERT INTO menu(name,price,description,"
                        + "recomended,created,category_id)"
                        + "VALUES (?,?,?,?,?,?)";
                PreparedStatement ps=connection.prepareStatement(query);
                ps.setString(1, object.getName());
                ps.setDouble(2, object.getPrice());
                ps.setString(3, object.getDescription());
                if(object.isRecommended()){
                    ps.setInt(4, 1);
                }
                else{
                    ps.setInt(4, 0);
                }
                ps.setTimestamp(5, t);
                ps.setInt(6, object.getCategory().getId());
                if(ps.executeUpdate()!=0)
                {
                    connection.commit();
                    result=1;
                }
                else
                {
                    connection.rollback();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public int deleteData(Menu object) {
        int result=0;
        try {
            try(Connection connection = Koneksi.createConnection()){
                connection.setAutoCommit(false);
                String query ="DELETE FROM menu WHERE id=?";
                PreparedStatement ps=connection.prepareStatement(query);
                ps.setInt(1, object.getId());
                if(ps.executeUpdate()!=0)
                {
                    connection.commit();
                    result=1;
                }
                else
                {
                    connection.rollback();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public int updateData(Menu object) {
        Timestamp t=new Timestamp(System.currentTimeMillis());
        int result=0;
        try {
            try(Connection connection = Koneksi.createConnection()){
                connection.setAutoCommit(false);
                String query ="UPDATE menu SET name=?,price=?,description=?,"
                        + "recomended=?,created=?,category_id=? WHERE id=?";
                PreparedStatement ps=connection.prepareStatement(query);
                ps.setInt(7, object.getId());
                ps.setString(1, object.getName());
                ps.setDouble(2, object.getPrice());
                ps.setString(3, object.getDescription());
                if(object.isRecommended()){
                    ps.setInt(4, 1);
                }
                else{
                    ps.setInt(4, 0);
                }
                ps.setTimestamp(5, t);
                ps.setInt(6, object.getCategory().getId());
                if(ps.executeUpdate()!=0)
                {
                    connection.commit();
                    result=1;
                }
                else
                {
                    connection.rollback();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public List<Menu> showAllData() {
        ObservableList<Menu> menus = FXCollections.observableArrayList();
        try {
            try(Connection connection = Koneksi.createConnection()){
                String query ="SELECT m.*, c.id AS cat_id, c.name AS "
                        + "cat_name FROM menu m JOIN category c on c.id "
                        + "= m.category_id ORDER BY c.id ASC, m.id";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs= ps.executeQuery();
                while(rs.next()){
                    Menu menu=new Menu();
                    Category cat=new Category();
                    cat.setId(rs.getInt("cat_id"));
                    cat.setName(rs.getString("cat_name"));
                    menu.setId(rs.getInt("id"));
                    menu.setName(rs.getString("name"));
                    menu.setPrice(rs.getDouble("price"));
                    menu.setDescription(rs.getString("description"));
                    menu.setRecommended(rs.getBoolean("recomended"));
                    menu.setCategory(cat);
                    menus.add(menu);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return menus;
    }
    
}
