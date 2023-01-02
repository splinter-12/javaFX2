package ma.enset.tpBaseDonnes.DAO;

import ma.enset.tpBaseDonnes.DAO.entities.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImpl implements ProduitDao{
    @Override
    public List<Produit> finAll() throws SQLException {
        Connection connection = SingletonConnexionDB.getConnection();
        PreparedStatement stm = connection.prepareStatement("select  * from Produit");
        ResultSet rs = stm.executeQuery();
        List<Produit> produits=new ArrayList<>();
        while (rs.next())
        {
            Produit produit=new Produit();
            produit.setId(rs.getInt("ID"));
            produit.setNom(rs.getString("NOM"));
            produit.setPrix(rs.getFloat("PRIX"));
            produit.setQuantite(rs.getInt("QUANTITE"));
            produit.setDescription(rs.getString("DESCRIPTION"));
            produits.add(produit);
        }
        return produits;
    }

    @Override
    public Produit findOne(int id) throws SQLException {
        Connection connection = SingletonConnexionDB.getConnection();
        PreparedStatement stm = connection.prepareStatement("select  * from Produit where ID=?");
        stm.setInt(1,id);
        Produit produit =new Produit();
        ResultSet rs = stm.executeQuery();
        if (rs.next())
        {
            produit.setId(rs.getInt("ID"));
            produit.setNom(rs.getString("NOM"));
            produit.setPrix(rs.getFloat("PRIX"));
            produit.setQuantite(rs.getInt("QUANTITE"));
            produit.setDescription(rs.getString("DESCRIPTION"));

        }
        return produit;
    }

    @Override
    public Produit save(Produit o) throws SQLException {
        Connection connection = SingletonConnexionDB.getConnection();
        PreparedStatement pstm = connection.prepareStatement("insert into Produit(NOM,DESCRIPTION,PRIX,QUANTITE,ID_CATEGORIE) " +
                "values(?,?,?,?,?)");
        pstm.setString(1,o.getNom());
        pstm.setString(2,o.getDescription());
        pstm.setFloat(3,o.getPrix());
        pstm.setInt(4,o.getQuantite());
        pstm.setInt(5,o.getCategorie().getId());
        pstm.executeUpdate();
        return o;
    }

    @Override
    public boolean delete(Produit o)  {
        try{
            Connection connection = SingletonConnexionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("delete from Produit where ID=?" );
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        }catch (SQLException e)
        {
            return false;
        }
        return true;
    }

    @Override
    public Produit update(Produit o) throws SQLException {
        Connection connection = SingletonConnexionDB.getConnection();
        PreparedStatement pstm = connection.prepareStatement(" update Produit set NOM=?,DESCRIPTION=?,PRIX=?,QUANTITE=? where ID=?" );
        pstm.setString(1,o.getNom());
        pstm.setString(2,o.getDescription());
        pstm.setFloat(3,o.getPrix());
        pstm.setInt(4,o.getQuantite());
        pstm.setInt(5,o.getId());
        pstm.executeUpdate();
        return o;
    }

    @Override
    public List<Produit> findProduitsByMc(String mc) throws SQLException {
        Connection connection = SingletonConnexionDB.getConnection();
        PreparedStatement pstm = connection.prepareStatement("select  * from Produit where NOM like ?");
        pstm.setString(1, "%"+ mc + "%");
        ResultSet rs = pstm.executeQuery();
        List<Produit> produits=new ArrayList<>();
        while (rs.next())
        {
            Produit produit=new Produit();
            produit.setId(rs.getInt("ID"));
            produit.setNom(rs.getString("NOM"));
            produit.setPrix(rs.getFloat("PRIX"));
            produit.setQuantite(rs.getInt("QUANTITE"));
            produit.setDescription(rs.getString("DESCRIPTION"));
            produits.add(produit);
        }
        return produits;
    }
}
