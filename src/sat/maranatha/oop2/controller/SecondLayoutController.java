package sat.maranatha.oop2.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sat.maranatha.oop2.entity.Category;
import sat.maranatha.oop2.utility.Koneksi;

/**
 * FXML Controller class
 *
 * @author Anthony-1572010
 */
public class SecondLayoutController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private Button btnSave;
    @FXML
    private TableView<Category> tblCategory;
    @FXML
    private TableColumn<Category, Integer> colId;
    @FXML
    private TableColumn<Category, String> colName;
    private MainLayoutController mainController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setMainController(MainLayoutController mainController) {
    //   categoryDao = new CategoryDaoImpl();
       this.mainController = mainController;
       tblCategory.setItems(mainController.getCategorys());
       colId.setCellValueFactory(p -> p.getValue().idProperty().asObject());
       colName.setCellValueFactory(p -> p.getValue().nameProperty());
    }
    
    @FXML
    private void btnSaveAction(ActionEvent event) {
        if(Koneksi.fieldNotEmpty(txtName))
        {
            Category d= new Category();
            
            d.setName(txtName.getText().trim());
            if(mainController.getCategoryDao().addData(d)==1)
            {
                mainController.getCategorys().clear();
                mainController.getCategorys().addAll(mainController.
                        getCategoryDao().showAllData());
            }
           
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nama masih kosong");
            alert.showAndWait();
        }
    }
    
}
