package ma.enset.tpBaseDonnes.présentation;

import ma.enset.tpBaseDonnes.DAO.CategorieDaoImpl;
import ma.enset.tpBaseDonnes.DAO.ProduitDaoImpl;
import ma.enset.tpBaseDonnes.DAO.entities.Produit;
import ma.enset.tpBaseDonnes.services.CatalogueService;
import ma.enset.tpBaseDonnes.services.CatalogueServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        CatalogueService produitService=new CatalogueServiceImpl(new ProduitDaoImpl(),new CategorieDaoImpl());
        List<Produit> produits = produitService.getProduitsParMC("A") ;
        for (Produit p :produits) {
            System.out.println(  p.getId()+" "+p.getNom()+" "+p.getDescription()+" "+p.getPrix()+" "+p.getQuantite());
        }
    }
}
