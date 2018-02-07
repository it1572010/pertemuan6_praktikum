package sat.maranatha.oop2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sat.maranatha.oop2.entity.Category;
import sat.maranatha.oop2.utility.Koneksi;

/**
 *
 * @author Anthony-1572010
 */
public class CategoryDaoImpl implements DaoService<Category>{

    @Override
    public int addData(Category object) {
        Timestamp t=new Timestamp(System.currentTimeMillis());
        int result=0;
        try {
            try(Connection connection = Koneksi.createConnection()){
                connection.setAutoCommit(false);
                String query ="INSERT INTO category(name,created)"
                        + "VALUES (?,?)";
                PreparedStatement ps=connection.prepareStatement(query);
                ps.setString(1, object.getName());
                ps.setTimestamp(2, t);
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
    public int deleteData(Category object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int updateData(Category object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Category> showAllData() {
        ObservableList<Category> categories = FXCollections
                .observableArrayList();
        try {
            try(Connection connection = Koneksi.createConnection()){
                String query ="SELECT id,name FROM category";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs= ps.executeQuery();
                while(rs.next()){
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    categories.add(category);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return categories;
    }
    
}
