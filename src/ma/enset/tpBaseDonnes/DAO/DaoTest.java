package ma.enset.tpBaseDonnes.DAO;

import ma.enset.tpBaseDonnes.DAO.entities.Produit;

import java.sql.SQLException;
import java.util.List;

public class DaoTest {
    public static void main(String[] args) throws SQLException {
        ProduitDao produitDao = new ProduitDaoImpl() ;
        //Produit p1=new Produit("ASUS","SESC3",12000,1);
        //produitDao.save(p1);
        Produit produit = produitDao.findOne(1);


            produit.setPrix(17000);
            produit.setQuantite(0);
            produitDao.update(produit);

        List<Produit> produits= produitDao.finAll();
        for (Produit p:produits) {
            System.out.println(p.getId()+" "+p.getNom()+" "+p.getDescription()+" "+p.getPrix()+" "+p.getQuantite());
            
        }

    }
}
