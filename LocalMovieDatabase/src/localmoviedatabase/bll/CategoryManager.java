/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.bll;

import java.util.List;
import localmoviedatabase.be.Genre;
import localmoviedatabase.be.Movie;
import localmoviedatabase.dal.dbmanagers.dbdao.CategoryDBDAO;

/**
 *
 * @author Rizvan
 */
public class CategoryManager
{

    
    private final CategoryDBDAO categoryDBDAO;
    

    /**
     * Constructor, initializes object
     */
    public CategoryManager()
    {
        categoryDBDAO = new CategoryDBDAO();
    }
    
    /**
     * Gets all categories from database
     * @return 
     */
    public List<Genre> getAllCategories(){
        return categoryDBDAO.getAllCategories();
        
    }
    
    /**
     * Creates a new category in the database
     * @param name
     * @return 
     */
    public Genre createGenre(String name){
        
            Genre genre = categoryDBDAO.createGenre(name);
            return genre;
        
    }

    /**
     * Removes a category from the database
     * @param g 
     */
    public void removeGenre(Genre g) {
        categoryDBDAO.removeGenre(g);
    
    }
    
    /**
     * Updates category from the database
     * @param g 
     */
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
    }
    
    
    
    
}
