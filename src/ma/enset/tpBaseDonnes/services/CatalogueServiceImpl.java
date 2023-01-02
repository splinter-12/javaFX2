package ma.enset.tpBaseDonnes.services;

import ma.enset.tpBaseDonnes.DAO.CategorieDao;
import ma.enset.tpBaseDonnes.DAO.ProduitDao;
import ma.enset.tpBaseDonnes.DAO.entities.Categorie;
import ma.enset.tpBaseDonnes.DAO.entities.Produit;

import java.sql.SQLException;
import java.util.List;

public class CatalogueServiceImpl implements CatalogueService {
    ProduitDao produitDao;
    private CategorieDao categorieDao;
    public CatalogueServiceImpl(ProduitDao produitDao,CategorieDao categorieDao)
    {
        this.produitDao =produitDao;
        this.categorieDao =categorieDao;

    }

    @Override
    public List<Produit> getAllProduit() throws SQLException {
        return produitDao.finAll();
    }

    @Override
    public void addProduit(Produit p) throws SQLException {
        produitDao.save(p);
    }

    @Override
    public void deleteProduit(Produit p) throws SQLException {
    produitDao.delete(p);
    }

    @Override
    public List<Produit> getProduitsParMC(String mc) throws SQLException {
        return produitDao.findProduitsByMc(mc);
    }

    @Override
    public List<Categorie> getAllCategories() throws SQLException {
        return categorieDao.finAll();
    }

    @Override
    public Categorie getCategorieById(int id) throws SQLException {
        return categorieDao.findOne(id);
    }

    @Override
    public void addCategorie(Categorie c) throws SQLException {
        categorieDao.save(c);

    }

    @Override
    public void deleteCtaegorie(Categorie c) throws SQLException {
        categorieDao.delete(c);
    }


}
