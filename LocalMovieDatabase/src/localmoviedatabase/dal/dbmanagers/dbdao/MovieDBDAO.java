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

    public MovieDBDAO() throws IOException
    {
        dbConnection = new DBSettings();
    }
    
    public List<Movie> getAllMovies() throws DalException
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
                
                Movie mov = new Movie(id, category, title, length, rating, relDate, path);
                allMovies.add(mov);
            }
            
            return allMovies;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }
    

    @Override
    public boolean createMovie(Movie movie) {
        try (Connection con = dbConnection.getConnection()) {
            String sql = "INSERT INTO Movie (title = ?, lenght = ?, path = ?, relDate = ?, genre = ?) VALUES (?,?,?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getLength());
            stmt.setString(3, movie.getPath());
            stmt.setInt(4, movie.getRelDate());
            stmt.setString(5, movie.getCategory());

            int updatedRows = stmt.executeUpdate();

            return updatedRows > 0;

        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteMovie(Movie movie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

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

    @Override
    public List<Movie> readMovie()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
