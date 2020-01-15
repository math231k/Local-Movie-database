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
import localmoviedatabase.be.Movie;
import localmoviedatabase.dal.dbaccess.DalException;
import localmoviedatabase.dal.dbmanagers.dbdao.CategoryDBDAO;

/**
 *
 * @author Rizvan
 */
public class CategoryManager
{

    
    private CategoryDBDAO categoryDBDAO;
    

    public CategoryManager()
    {
        categoryDBDAO = new CategoryDBDAO();
    }
    
    
    public List<Genre> getAllCategories(){
        return categoryDBDAO.getAllCategories();
        
    }
    
    public void createGenre(Genre g){
        
            categoryDBDAO.createGenre(g);
        
    }

    public void removeGenre(Genre g) {
        categoryDBDAO.removeGenre(g);
    
    }
    
    public void updateGenre(Genre g)
    {
        categoryDBDAO.updateCategory(g);
    }

    public void addMovieToCategory(Movie movie, Genre genre) {
        categoryDBDAO.addMovieToCategory(movie, genre);
    }

    public List<Movie> getAllMoviesInGenre(Genre g) {
        return categoryDBDAO.getMoviesFromGenre(g);     
    }

    public void removeMovieFromGenre(Movie selectedMovie, Genre selectedGenre) 
    {
        categoryDBDAO.removeMovieFromGenre(selectedMovie, selectedGenre);

        List<Movie> remainingMovies = categoryDBDAO.getMoviesFromGenre(selectedGenre);
        categoryDBDAO.removeAllCategoryMovies(selectedGenre);

        for (Movie m : remainingMovies)
        {
            categoryDBDAO.addMovieToCategory(m, selectedGenre);
        }
    }
    
    
    
    
}
