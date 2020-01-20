/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.dal.dbmanagers.dbdao;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import localmoviedatabase.be.Movie;
import localmoviedatabase.dal.dbaccess.DBSettings;
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
    
    /**
     * deletes a movie From the database
     * @param m the movie to be deleted
     * @return true if the movie was deleted
     */
    @Override
    public boolean deleteMovie(Movie m) {
        
        try (Connection con = dbConnection.getConnection()) {
            String sql = "DELETE FROM Movie WHERE movieId = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, m.getId());
            int updatedRows = stmt.executeUpdate();

            return updatedRows > 0;
            
        } catch (SQLServerException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    
    /**
     * reads all movies in the database
     * @return a list of Movie objects 
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
            ArrayList<Movie> allMovies = new ArrayList<>();
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
                mov.setPath(path);
                mov.setTitle(title);
                mov.setRating(rating);
                mov.setRelDate(relDate);
                allMovies.add(mov);
            }
            
            return allMovies;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            
        }
        return null;
    }
    
    
    /**
     * updates the data of a movie in the database
     * @param movie the movie to be updated
     * @return true uf the movie was updated
     */
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

    
    /**
     * Creates a new movie in the database
     * @param category
     * @param title
     * @param rating
     * @param relDate
     * @param path
     * @return the movie which was vreated
     */
    @Override
    public Movie createMovie(String title, int rating, int relDate, String path) {

        try (Connection con = dbConnection.getConnection()) {
            String sql = "INSERT INTO Movie (title, path, rating, date) VALUES (?,?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, title);
            stmt.setString(2, path);
            stmt.setInt(3, rating);
            stmt.setInt(4, relDate);
            
            int updatedRows = stmt.executeUpdate();

            if (updatedRows == 1)
            {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    Movie mov = new Movie (id, title, rating, relDate, path);
                    mov.setId(id);
                    mov.setPath(path);
                    return mov;
                }
            }

        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
      
    }
 
}
