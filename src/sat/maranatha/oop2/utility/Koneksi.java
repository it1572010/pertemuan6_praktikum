package sat.maranatha.oop2.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Anthony-1572010
 */
public class Koneksi {
    public static Connection createConnection() throws 
            ClassNotFoundException,SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/oopl20171", "root", "");
        return con;
    }
    
    public static boolean fieldNotEmpty(TextField... textFields) {
        for (TextField t : textFields) {
            if (t.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean areaNotEmpty(TextArea... textAreas) {
        for (TextArea t : textAreas) {
            if (t.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
