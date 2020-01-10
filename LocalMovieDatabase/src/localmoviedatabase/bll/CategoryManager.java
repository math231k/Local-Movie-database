/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.bll;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import localmoviedatabase.be.Genre;
import localmoviedatabase.dal.dbaccess.DalException;
import localmoviedatabase.dal.dbmanagers.dbdao.CategoryDBDAO;

/**
 *
 * @author Rizvan
 */
public class CategoryManager
{
    private CategoryDBDAO categoryDBDAO;

    public CategoryManager() throws IOException
    {
        categoryDBDAO = new CategoryDBDAO();
    }
    
    
    public List<Genre> getAllCategories() throws DalException{
        return categoryDBDAO.getAllCategories();
        
    }
    
    public void createGenre(Genre g) throws DalException, SQLException{
        
        if (g.getId() == 0)
        {
            categoryDBDAO.createGenre(g);
        } /*else
        {
            categoryDBDAO.(g);
        }*/
    }

    public void removeGenre(Genre g) {
        categoryDBDAO.removeGenre(g);
    
    }
    
}
