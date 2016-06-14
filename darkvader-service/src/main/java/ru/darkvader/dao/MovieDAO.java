package ru.darkvader.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darkvader.model.Movie;

/**
 * Created by Khairullin on 12/03/16.
 * DAO repository on Movie table.
 * Specifies methods used to obtain and modify group related information
 * which is stored in the database.
 *
 * @author Khairullin
 */

@Repository
public interface MovieDAO extends CrudRepository<Movie, Integer> {
}
