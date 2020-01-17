/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.dal.dbmanagers.facades;

import java.util.List;
import localmoviedatabase.be.Genre;

/**
 *
 * @author math2
 */
public interface CategoryDalFacade {
    List<Genre> getAllCategories();
    Genre createGenre(String name);
    boolean removeGenre(Genre g);
    boolean updateCategory(Genre g);
}
