/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.dal.dbmanagers.dbdao;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import localmoviedatabase.be.Movie;
import localmoviedatabase.dal.dbaccess.DBSettings;
import localmoviedatabase.dal.dbaccess.DalException;
import localmoviedatabase.dal.dbmanagers.facades.MovieDalFacade;

/**
 *
 * @author math2
 */
public class MovieDBDAO implements MovieDalFacade{

    private DBSettings dbConnection;

    public MovieDBDAO()
    {
        try {
            dbConnection = new DBSettings();
        } catch (IOException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public boolean deleteMovie(Movie movie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
/*
    @Override
    public boolean updateMovie(Movie movie) {
        try (Connection con = dbConnection.getConnection()) {
           String sql = "UPDATE Songs SET id = ?, category = ?, title = ?, length = ?, relDate = ?, path = ?  WHERE id = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, movie.getId());
            stmt.setString(2, movie.getCategory());
            stmt.setString(3, movie.getTitle());
            stmt.setString(4, movie.getLength());
            stmt.setInt(5, movie.getRelDate());
            stmt.setString(6, movie.getPath());
            
            int updatedRows = stmt.executeUpdate();
            return updatedRows > 0;
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
*/
    @Override
    public List<Movie> readMovie()
    {
    try
        {
            dbConnection = new DBSettings();
        } catch (IOException ex)
        {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        try ( Connection con = dbConnection.getConnection())
        {
            String sql = "SELECT * FROM Movie;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Movie> allMovies = new ArrayList<>();
            while (rs.next())
            {
                String title = rs.getString("title");
                String path = rs.getString("path");
                int id = rs.getInt("movieId");
                String category = rs.getString("genre");
                int rating = rs.getInt("rating");
                String length = rs.getString("length");
                int relDate = rs.getInt("date");
                
                Movie mov = new Movie(id, rating, title);
                mov.setId(id);
                allMovies.add(mov);
            }
            
            return allMovies;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            
        }
        return null;
    }
    
    

    @Override
    public boolean updateMovie(Movie movie) {
        try (Connection con = dbConnection.getConnection()) {
            
            String title = movie.getTitle();
            int rating = movie.getRating();
            int movieId = movie.getId();
            
            String sql = "UPDATE Movie SET title=?, rating=? WHERE movieId=?";
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setString(1, title);
            prst.setInt(2, rating);
            prst.setInt(3, movieId);
            int affectedRows = prst.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    

    @Override
    public boolean createMovie(Movie movie) {

        try (Connection con = dbConnection.getConnection()) {
            String sql = "INSERT INTO Movie (title, length, path) VALUES (?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getLength());
            stmt.setString(3, movie.getPath());
            
            int updatedRows = stmt.executeUpdate();

            return updatedRows > 0;

        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   

}
