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
import localmoviedatabase.be.Movie;
import localmoviedatabase.dal.dbaccess.DBSettings;
import localmoviedatabase.dal.dbmanagers.facades.CategoryDalFacade;

/**
 *
 * @author Rizvan
 */
public class CategoryDBDAO implements CategoryDalFacade
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
    
    @Override
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
            List<Genre> allCategories = new ArrayList<>();
            while (rs.next())
            {
                String genreName = rs.getString("genreName");
                int genreId = rs.getInt("genreId");
                //List<Movie> movieList = rs.getInt("genreMovies");
                
                Genre gen = new Genre(genreName);
                gen.setId(genreId);
                allCategories.add(gen);
            }
            
            return allCategories; 
        } catch (SQLException ex)
        {
        }
        return null;        
    }
    
    @Override
    public Genre createGenre(String name){
        try(Connection con = dbConnection.getConnection()){
            
            String sql = "INSERT INTO Genre VALUES (?);";
            PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, name);
            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = pstm.getGeneratedKeys();
                if (rs.next())
                {
                    int genreId = rs.getInt(1);
                    Genre g1 = new Genre(genreId, name);
                    return g1;
                }
            }
            
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
        
        
    }

    @Override
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
           String sql = "INSERT INTO GenreMovies(genId, movId) VALUES (?,?);";
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
   
    public List<Movie> getMoviesFromGenre(Genre genre){
        try (Connection con = dbConnection.getConnection()) {
            String sql = "SELECT * FROM genreMovies FULL OUTER JOIN Movie ON "
                    + "genreMovies.movId = Movie.movieId WHERE genId = ? "
                    + "ORDER BY movieId;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, genre.getId());
            ResultSet rs = stmt.executeQuery();
            List<Movie> movies = new ArrayList<>();
            
            while (rs.next()) {
                int Id = rs.getInt("Id");
                int rating = rs.getInt("rating");
                String title = rs.getString("title");
                String path = rs.getString("path");
                

                Movie m = new Movie(Id, rating , title);
                m.setPath(path);

                if (m.getTitle()!=null){
                movies.add(m);
                }
            }
            return movies;
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
  
    @Override
    public boolean updateCategory(Genre g)
    {
        try (Connection con = dbConnection.getConnection())
        {
            String genreName = g.getGenreName();
            int genreId = g.getId();
            
            String sql = "UPDATE Genre SET genreName=? WHERE genreId=?";
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setString(1, genreName);
            prst.setInt(2, genreId);
            int affectedRows = prst.executeUpdate();
            
            return affectedRows > 0;
              
        } catch (SQLServerException ex)
        {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void removeMovieFromGenre(Movie selectedMovie, Genre selectedGenre) {
      
      try (Connection con = dbConnection.getConnection()) {
      String sql = "DELETE FROM genreMovies WHERE id = ? and genId = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            System.out.println(selectedMovie.getId());
            System.out.println(selectedGenre.getId());
            stmt.setInt(1, selectedMovie.getId());
            stmt.setInt(2, selectedGenre.getId());
            
            
            
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /*public void removeAllCategoryMovies(Genre selectedGenre) {
        try (Connection con = dbConnection.getConnection()) {
            String sql = "DELETE FROM GenreMovies WHERE movId = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, selectedGenre.getId());

            int updatedRows = stmt.executeUpdate();


        } catch (SQLServerException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
}
        
    
    
    
    

