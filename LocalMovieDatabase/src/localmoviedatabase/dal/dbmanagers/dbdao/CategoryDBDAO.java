/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.dal.dbmanagers.dbdao;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import localmoviedatabase.be.Genre;
import localmoviedatabase.dal.dbaccess.DBSettings;
import localmoviedatabase.dal.dbaccess.DalException;

/**
 *
 * @author Rizvan
 */
public class CategoryDBDAO
{
    
    private DBSettings dbConnection;

    public CategoryDBDAO() throws IOException
    {
        dbConnection = new DBSettings();
    }
    
    public List<Genre> getAllCategories() throws DalException
    {
        
        try
        {
            dbConnection = new DBSettings();
        } catch (IOException ex)
        {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try ( Connection con = dbConnection.getConnection())
        {
            String sql = "SELECT * FROM Genre;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Genre> allCategories = new ArrayList<>();
            while (rs.next())
            {
                String genreName = rs.getString("genreName");
                int genreId = rs.getInt("genreId");
                
                Genre gen = new Genre(genreName);
                allCategories.add(gen);
            }
            
            return allCategories; 
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }        
    }
    
    
    
}