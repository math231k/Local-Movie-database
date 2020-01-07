/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.bll;

import java.io.IOException;
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
    
    
    public List <Genre> getAllCategories() throws DalException
    {
        return categoryDBDAO.getAllCategories();
        
    }
    
    
}
