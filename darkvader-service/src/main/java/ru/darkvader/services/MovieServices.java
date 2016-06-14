package ru.darkvader.services;

import ru.darkvader.model.Movie;

/**
 * Created by Khairullin on 12/03/16.
 * Declares methods used to obtain and modify Movie table.
 *
 * @author Khairullin
 */

public interface MovieServices extends GenericCRUD<Movie> {

    /**
     * Creates a new Youtube Movie by link (YouTube id).
     *
     * @param movieLink The created object.
     * @return The created object.
     */
    public Movie createByLink(String movieLink);

}
