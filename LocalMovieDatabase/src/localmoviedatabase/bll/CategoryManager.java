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
     * @return a list of Genre objects
     */
    public List<Genre> getAllCategories(){
        return categoryDBDAO.getAllCategories();
        
    }
    
    /**
     * Creates a new category in the database
     * @param name the name to give the catagory
     * @return the created category
     */
    public Genre createGenre(String name){
        
            Genre genre = categoryDBDAO.createGenre(name);
            return genre;
        
    }

    /**
     * Removes a category from the database
     * @param g the genre to be removed
     */
    public void removeGenre(Genre g) {
        categoryDBDAO.removeGenre(g);
    
    }
    
    /**
     * Updates category from the database
     * @param g the genre to be updated
     */
    public void updateGenre(Genre g)
    {
        categoryDBDAO.updateCategory(g);
    }

    /**
     * Adds a movie to a specific Genre 
     * @param movie the movie to be added
     * @param genre the genre you want the movie added to
     */
    public void addMovieToCategory(Movie movie, Genre genre) {
        categoryDBDAO.addMovieToCategory(movie, genre);
    }

    /**
     * gets all Movies from a Genre
     * @param g the genre from where to get the movies
     * @return a list of movie objects
     */
    public List<Movie> getAllMoviesInGenre(Genre g) {
        return categoryDBDAO.getMoviesFromGenre(g);     
    }

    /**
     * removes a Movie from a genre
     * @param selectedMovie the movie to be deleted
     * @param selectedGenre the genre from where the movie is deleted
     */
    public void removeMovieFromGenre(Movie selectedMovie, Genre selectedGenre) 
    {
        categoryDBDAO.removeMovieFromGenre(selectedMovie, selectedGenre);
    }
    
    
    
    
}
