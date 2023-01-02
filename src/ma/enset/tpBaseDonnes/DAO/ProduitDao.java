package ma.enset.tpBaseDonnes.DAO;

import ma.enset.tpBaseDonnes.DAO.entities.Produit;

import java.sql.SQLException;
import java.util.List;

public interface ProduitDao extends dao <Produit>{
    List<Produit> findProduitsByMc(String mc) throws SQLException;
}
