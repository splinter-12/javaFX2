package ma.enset.tpBaseDonnes.DAO;

import ma.enset.tpBaseDonnes.DAO.entities.Categorie;
import ma.enset.tpBaseDonnes.DAO.entities.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDaoImpl implements CategorieDao{
    @Override
    public List<Categorie> finAll() throws SQLException {
        Connection connection = SingletonConnexionDB.getConnection();
        PreparedStatement stm = connection.prepareStatement("select  * from CATEGORIES");
        ResultSet rs = stm.executeQuery();
        List<Categorie> categories=new ArrayList<>();
        while (rs.next())
        {
            Categorie categorie=new Categorie();
            categorie.setId(rs.getInt("ID"));
            categorie.setNom(rs.getString("NOM"));

            categories.add(categorie);
        }
        return categories;
    }

    @Override
    public Categorie findOne(int id) throws SQLException {
        Connection connection = SingletonConnexionDB.getConnection();
        PreparedStatement stm = connection.prepareStatement("select  * from CTEGORIES where ID=?");
        stm.setInt(1,id);
        Categorie categorie =new Categorie();
        ResultSet rs = stm.executeQuery();
        if (rs.next())
        {
            categorie.setId(rs.getInt("ID"));
            categorie.setNom(rs.getString("NOM"));


        }
        return categorie;
    }

    @Override
    public Categorie save(Categorie o) throws SQLException {
        Connection connection = SingletonConnexionDB.getConnection();
        PreparedStatement pstm = connection.prepareStatement("insert into CATEGORIES(NOM) " +
                "values(?)");
        pstm.setString(1,o.getNom());

        pstm.executeUpdate();
        return o;
    }

    @Override
    public boolean delete(Categorie o)  {
        try{
            Connection connection = SingletonConnexionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("delete from CATEGORIES where ID=?" );
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        }catch (SQLException e)
        {
            return false;
        }
        return true;
    }

    @Override
    public Categorie update(Categorie o) throws SQLException {
        Connection connection = SingletonConnexionDB.getConnection();
        PreparedStatement pstm = connection.prepareStatement(" update Produit set NOM=? where ID=?" );
        pstm.setString(1,o.getNom());

        pstm.setInt(1,o.getId());
        pstm.executeUpdate();
        return o;
    }
}
