/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sat.maranatha.oop2.entity;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Anthony-1572010
 */
public class Menu {

    private final IntegerProperty id = new SimpleIntegerProperty();

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    private final StringProperty name = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }
    private final DoubleProperty price = new SimpleDoubleProperty();

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double value) {
        price.set(value);
    }

    public DoubleProperty priceProperty() {
        return price;
    }
    private final StringProperty description = new SimpleStringProperty();

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public StringProperty descriptionProperty() {
        return description;
    }
    private final BooleanProperty recommended = new SimpleBooleanProperty();

    public boolean isRecommended() {
        return recommended.get();
    }

    public void setRecommended(boolean value) {
        recommended.set(value);
    }

    public BooleanProperty recommendedProperty() {
        return recommended;
    }
    private final StringProperty photo = new SimpleStringProperty();

    public String getPhoto() {
        return photo.get();
    }

    public void setPhoto(String value) {
        photo.set(value);
    }

    public StringProperty photoProperty() {
        return photo;
    }
    private final ObjectProperty<Category> category = new 
        SimpleObjectProperty<>();

    public Category getCategory() {
        return category.get();
    }

    public void setCategory(Category value) {
        category.set(value);
    }

    public ObjectProperty categoryProperty() {
        return category;
    }
    
}
