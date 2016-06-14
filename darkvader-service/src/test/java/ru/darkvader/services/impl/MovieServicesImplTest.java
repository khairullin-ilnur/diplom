package ru.darkvader.services.impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.darkvader.services.MovieServices;

/**
 * Created by Khairullin on 12/03/16.
 *
 * @author Khairullin
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ru.darkvader.config.TestConfig.class})
@Transactional
public class MovieServicesImplTest {
    @Autowired
    private MovieServices movieServices;

//    @Test
//    public void testFindAll() throws Exception {
//        List<Movie> movies = movieServices.findAll();
//        assertNotNull(movies);
//    }
//
//    @Test
//    public void testCRUD() throws NotFoundException {
//        //Create movie object
//        Movie movie = TestData.standardMovie();
//
//        //Check that empty database
//        assertEquals(0, movieServices.findAll().size());
//
//        //Check insert movie to database
//        movieServices.create(movie);
//        assertEquals(1, movieServices.findAll().size());
//
//        //Check get by id from database
//        assertEquals(TestConstants.MovieData.MOVIE_TITLE, movieServices.findById(movie.getMovieId()).getMovieTitle());
//
//        //Check update post in database
//        movie.setMovieTitle(TestConstants.MovieData.UPD_MOVIE_TITLE);
//        movieServices.update(movie);
//        assertEquals(TestConstants.MovieData.UPD_MOVIE_TITLE, movieServices.findById(movie.getMovieId()).getMovieTitle());
//
//        //Check delete post from database
//        movieServices.delete(movie.getMovieId());
//        assertEquals(0, movieServices.findAll().size());
//    }
//
//    @Test
//    public void testCreateByLink() throws NotFoundException {
//        //Check that empty database
//        assertEquals(0, movieServices.findAll().size());
//
//        //Check insert movie to database
//        movieServices.createByLink(TestConstants.MovieData.MOVIE_LINK);
//        assertEquals(1, movieServices.findAll().size());
//
//        //Check get by id from database
//        assertEquals(TestConstants.MovieData.MOVIE_LINK_TITLE, movieServices.findById(TestConstants.MovieData.MOVIE_LINK).getMovieTitle());
//
//        //Check delete post from database
//        movieServices.delete(TestConstants.MovieData.MOVIE_LINK);
//        assertEquals(0, movieServices.findAll().size());
//    }

}
