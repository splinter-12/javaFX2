package ma.enset.tpBaseDonnes.services;

import ma.enset.tpBaseDonnes.DAO.entities.Categorie;
import ma.enset.tpBaseDonnes.DAO.entities.Produit;

import java.sql.SQLException;
import java.util.List;

public interface CatalogueService {
    List<Produit> getAllProduit() throws SQLException;
    void addProduit(Produit p) throws SQLException;
    void deleteProduit(Produit p) throws SQLException;
    List<Produit> getProduitsParMC(String mc) throws SQLException;
    List<Categorie> getAllCategories() throws SQLException;
    Categorie getCategorieById(int id) throws SQLException;
    void addCategorie(Categorie c) throws SQLException;
    void deleteCtaegorie(Categorie c) throws SQLException;

}
