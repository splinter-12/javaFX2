package ma.enset.tpBaseDonnes.présentation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.tpBaseDonnes.DAO.CategorieDaoImpl;
import ma.enset.tpBaseDonnes.DAO.ProduitDaoImpl;
import ma.enset.tpBaseDonnes.DAO.entities.Categorie;
import ma.enset.tpBaseDonnes.services.CatalogueService;
import ma.enset.tpBaseDonnes.services.CatalogueServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class categorieController implements Initializable {
    @FXML
    private TextField textNom;
    @FXML
    private TableView<Categorie> tableCategorie;
    @FXML
    private TableColumn<Categorie,Integer>colId;
    @FXML
    private TableColumn<Categorie,String> colNom;
    ObservableList<Categorie> categories = FXCollections.observableArrayList();
    private CatalogueService catalogueService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        catalogueService=new CatalogueServiceImpl(new ProduitDaoImpl(),new CategorieDaoImpl());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableCategorie.setItems(categories);
        try {
            loadCategories();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void addCategorie() throws SQLException {
        String nom= textNom.getText();
        Categorie c= new Categorie();
        c.setNom(nom);
        catalogueService.addCategorie(c);
        loadCategories();
    }

    public void deleteCtegorie() throws SQLException {
        Categorie c=tableCategorie.getSelectionModel().getSelectedItem();
        catalogueService.deleteCtaegorie(c);
        loadCategories();
    }
private void loadCategories() throws SQLException {
        categories.clear();
    categories.addAll(catalogueService.getAllCategories());
}
}
