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
import java.util.Locale.Category;
import java.util.logging.Level;
import java.util.logging.Logger;
import localmoviedatabase.be.Genre;
import localmoviedatabase.be.Movie;
import localmoviedatabase.dal.dbaccess.DBSettings;
import localmoviedatabase.dal.dbaccess.DalException;

/**
 *
 * @author Rizvan
 */
public class CategoryDBDAO
{
    
    private DBSettings dbConnection;

    public CategoryDBDAO()
    {
        try {
            dbConnection = new DBSettings();
        } catch (IOException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Genre> getAllCategories()
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
                //gen.setId(genreId);
                allCategories.add(gen);
            }
            
            return allCategories; 
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;        
    }
    
    public Boolean createGenre(Genre g){
        try(Connection con = dbConnection.getConnection()){
            
            String sql = "INSERT INTO Genre (genreName) VALUES (?);";
            
            
            PreparedStatement pstm = con.prepareStatement(sql);
            
            pstm.setString(1, g.getGenreName());
            
            int updatedRows = pstm.executeUpdate();

            return updatedRows > 0;
             
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return false;
    }

    public boolean removeGenre(Genre g) {
    try (Connection con = dbConnection.getConnection()) {
            String sql = "DELETE FROM Genre WHERE genreId = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, g.getId());
            int updatedRows = stmt.executeUpdate();

            return updatedRows > 0;
            
        } catch (SQLServerException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean addMovieToCategory(Movie m, Genre g) {
        try (Connection con = dbConnection.getConnection()) {
           String sql = "INSERT INTO GenreMovies(genreId, MovieId) VALUES (?,?);";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, g.getId());
            pstm.setInt(2, m.getId());
            

            int updatedRows = pstm.executeUpdate();

            return updatedRows > 0;

        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   
    public List<Movie> getMoviesFromGenre(Genre g){
        try(Connection con = dbConnection.getConnection()){
            String sql = "SELECT * FROM genreMovies WHERE genreId = ? VALUES (?);";
            
            PreparedStatement pstm = con.prepareStatement(sql);
            
            pstm.setInt(1, g.getId());
            
            
            
        
        } catch (SQLServerException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
        
    
    
    
    

