package sat.maranatha.oop2.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import sat.maranatha.oop2.dao.CategoryDaoImpl;
import sat.maranatha.oop2.dao.MenuDaoImpl;
import sat.maranatha.oop2.entity.Category;
import sat.maranatha.oop2.entity.Menu;
import sat.maranatha.oop2.mainApp;
import sat.maranatha.oop2.utility.Koneksi;

/**
 * FXML Controller class
 *
 * @author Anthony-1572010
 */
public class MainLayoutController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextArea txtDescription;
    @FXML
    private CheckBox checkRecomended;
    @FXML
    private ComboBox<Category> cmbCategory;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Menu> tblMenu;
    @FXML
    private TableColumn<Menu, Integer> colId;
    @FXML
    private TableColumn<Menu, String> colName;
    @FXML
    private TableColumn<Menu, Double> colPrice;
    @FXML
    private TableColumn<Menu, Boolean> colRecomended;
    private ObservableList<Category> categories;
    private Menu selectedMenu;
    private ObservableList<Menu> menus;
    private MenuDaoImpl menuDao;
    private CategoryDaoImpl categoryDao;
    private Stage categoryStage;
    @FXML
    private BorderPane borderPane;
    
    public ObservableList<Category> getCategorys() {
        if(categories==null)
        {
            categories=FXCollections.observableArrayList();
            categories.addAll(getCategoryDao().showAllData());
        }
        return categories;
    }
    
    public ObservableList<Menu> getMenus() {
        if(menus==null)
        {
            menus=FXCollections.observableArrayList();
            menus.addAll(getMenuDao().showAllData());
        }
        return menus;
    }
    
    

    public MenuDaoImpl getMenuDao() {
        if(menuDao==null)
        {
            menuDao=new MenuDaoImpl();
        }
        return menuDao;
    }
    
    public CategoryDaoImpl getCategoryDao() {
        if(categoryDao==null)
        {
            categoryDao=new CategoryDaoImpl();
        }
        return categoryDao;
    }

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cmbCategory.setItems(getCategorys());
       tblMenu.setItems(getMenus());
       
       colId.setCellValueFactory(p -> p.getValue().idProperty().asObject());
       colName.setCellValueFactory(p -> p.getValue().nameProperty());
       colPrice.setCellValueFactory(p -> 
               p.getValue().priceProperty().asObject());
       colRecomended.setCellValueFactory(p -> 
               p.getValue().recommendedProperty().asObject());
    }    


    @FXML
    private void tblClickedAction(MouseEvent event) {
        selectedMenu=tblMenu.getSelectionModel().getSelectedItem();
        if(selectedMenu!=null)
        {
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            btnSave.setDisable(true);
            txtId.setText(String.valueOf(selectedMenu.getId()));
            txtName.setText(selectedMenu.getName());
            txtPrice.setText(String.valueOf(selectedMenu.getPrice()));
            txtDescription.setText(selectedMenu.getDescription());
            checkRecomended.setSelected(selectedMenu.isRecommended());
            cmbCategory.setValue(selectedMenu.getCategory());
        }
    }

    @FXML
    private void menuShowCategoryManagementAction(ActionEvent event) {
        try {
            if (categoryStage == null) {
                categoryStage = new Stage();
                categoryStage.setTitle("Category Management");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(mainApp.class.getResource
                    ("view/secondLayoutView.fxml"));
                BorderPane root = loader.load();
                Scene scene = new Scene(root);
                SecondLayoutController secondLayoutController = 
                          loader.getController();
                secondLayoutController.setMainController(this);
                categoryStage.setScene(scene);
                categoryStage.initOwner(borderPane.getScene().getWindow());
                categoryStage.initModality(Modality.WINDOW_MODAL);
            }
            if (!categoryStage.isShowing()) {
                categoryStage.show();
            } else {
                categoryStage.toFront();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    private void menuCloseAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void menuAboutAction(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PBO2 #6 Praktikum");
        alert.setContentText("Anthony Sutandi - 1572010");
        alert.showAndWait();
    }

    @FXML
    private void btnSaveAction(ActionEvent event) {
        if(Koneksi.fieldNotEmpty(txtName,txtPrice)&& cmbCategory.getValue()
                !=null && Koneksi.areaNotEmpty(txtDescription))
        {
            Menu d= new Menu();
            d.setName(txtName.getText().trim());
            d.setPrice(Double.parseDouble(txtPrice.getText().trim()));
            d.setDescription(txtDescription.getText().trim());
            d.setRecommended(checkRecomended.isSelected());
            d.setCategory(cmbCategory.getValue());
            if(menuDao.addData(d)==1)
            {  
                System.out.println(d.getDescription());
                menus.clear();
                menus.addAll(menuDao.showAllData());
                System.out.println(menus.lastIndexOf(d));
                btnResetAction(event);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Masih Ada yang Kosong");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnResetAction(ActionEvent event) {
        txtId.clear();
        txtDescription.clear();
        txtName.clear();
        txtPrice.clear();
        cmbCategory.setValue(null);
        checkRecomended.setSelected(false);
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
        if(Koneksi.fieldNotEmpty(txtName,txtPrice,txtId)&&cmbCategory.
                getValue()!=null && Koneksi.areaNotEmpty(txtDescription))
        {
            selectedMenu.setId(Integer.valueOf(txtId.getText().trim()));
            selectedMenu.setName(txtName.getText().trim());
            selectedMenu.setPrice(Double.parseDouble(txtPrice.getText()
                   .trim()));
            selectedMenu.setDescription(txtDescription.getText().trim());
            selectedMenu.setRecommended(checkRecomended.isSelected());
            selectedMenu.setCategory(cmbCategory.getValue());
            if(menuDao.updateData(selectedMenu)==1)
            {
                menus.clear();
                menus.addAll(menuDao.showAllData());
                btnResetAction(event);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Masih Ada yang Kosong");
            alert.showAndWait();
        }
        btnResetAction(event);
    }

    @FXML
    private void btnDeleteAction(ActionEvent event) {
        if(selectedMenu!=null){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete confirmation");
            alert.setContentText("Are you sure want to delete?");
            //cara java 8
            alert.showAndWait().ifPresent(jawaban ->{
                if(jawaban  == ButtonType.OK)
                {
                   getMenuDao().deleteData(selectedMenu);
                   getMenus().clear();
                   getMenus().addAll(getMenuDao().showAllData());
                }
            });
            btnResetAction(event);
        }
    }

    @FXML
    private void menuReportMenuAction(ActionEvent event) {
        try {
            Map param=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport
        ("src\\sat\\maranatha\\oop2\\report\\reportMenu.jasper",param,
                Koneksi.createConnection());
            JasperViewer view=new JasperViewer(jp,false);
            view.setTitle("Report Menu");
            view.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void menuReportCategoryAction(ActionEvent event) {
        try {
            Map param=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport
        ("src\\sat\\maranatha\\oop2\\report\\reportCategory.jasper",param
                ,Koneksi.createConnection());
            JasperViewer view=new JasperViewer(jp,false);
            view.setTitle("Report Category");
            view.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
