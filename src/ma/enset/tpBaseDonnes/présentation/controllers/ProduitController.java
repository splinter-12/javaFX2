package ma.enset.tpBaseDonnes.présentation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.tpBaseDonnes.DAO.CategorieDaoImpl;
import ma.enset.tpBaseDonnes.DAO.ProduitDaoImpl;
import ma.enset.tpBaseDonnes.DAO.entities.Categorie;
import ma.enset.tpBaseDonnes.DAO.entities.Produit;
import ma.enset.tpBaseDonnes.services.CatalogueService;
import ma.enset.tpBaseDonnes.services.CatalogueServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {
    @FXML
    private TextField textNom;
    @FXML
    private TextArea textDescription;
    @FXML
    private TextField textPrix;
    @FXML
    private TextField textQuantite;
    @FXML
    private ComboBox<Categorie> categorieComboBox;
    @FXML
   private TableView<Produit> produitTableView;
    @FXML
    private TableColumn<Produit,Integer> colID;
    @FXML
    private TableColumn<Produit,String> colNom;
    @FXML
    private TableColumn<Produit, Float> colPrix;
    @FXML
    private TableColumn<Produit,Categorie> colCategorie;
    @FXML
    private TableColumn<Produit,Integer>  colQuantite;
    @FXML
    private ObservableList<Produit> observableList= FXCollections.observableArrayList();
    private CatalogueService catalogueService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        catalogueService = new CatalogueServiceImpl(new ProduitDaoImpl(),new CategorieDaoImpl());
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        try {
            categorieComboBox.getItems().addAll(catalogueService.getAllCategories());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        produitTableView.setItems(observableList);
        loadProduct();

    }
    @FXML
    private void addProduit() throws SQLException {

        String nom=textNom.getText();
        String description= textDescription.getText();
        float prix=Float.parseFloat(textPrix.getText());
        int quantite=Integer.parseInt(textQuantite.getText());
        Categorie categorie = categorieComboBox.getSelectionModel().getSelectedItem();

        if (nom.isEmpty())
        {
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setContentText("veulleiz saisr un nom");
            alert.show();
        }
        else
        {
            Produit p=new Produit();
            p.setNom(nom);
            p.setPrix(prix);
            p.setDescription(description);
            p.setQuantite(quantite);
            catalogueService.addProduit(p);
            p.setCategorie(categorie);

            loadProduct();
            textNom.clear();

        }

    }
    @FXML
    private void deleteProduit() throws SQLException {
        Produit c =produitTableView.getSelectionModel().getSelectedItem();
        catalogueService.deleteProduit(c);
        loadProduct();
    }
    private void loadProduct()
    {
        observableList.clear();
        try {
            observableList.addAll(catalogueService.getAllProduit());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
