package ru.darkvader.services.impl;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.darkvader.dao.MovieDAO;
import ru.darkvader.exception.NotFoundException;
import ru.darkvader.model.Movie;
import ru.darkvader.services.MovieServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khairullin on 12/03/16.
 * This implementation of the MovieServices interface communicates with
 * the database by using a Spring Data JPA repository.
 *
 * @author Khairullin
 */

@Service
@Transactional
public class MovieServicesImpl implements MovieServices {

    @Autowired
    private MovieDAO movieDAO;

    //------------------
    //------CRUD queries

    @Override
    public Movie create(Movie object) {
        return movieDAO.save(object);
    }

    @Override
    public Movie delete(int objectId) throws NotFoundException {
        Movie movie = movieDAO.findOne(objectId);
        if (movie == null) {
            throw new NotFoundException(objectId, Movie.class);
        }
        movieDAO.delete(movie);
        return movie;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return (List<Movie>) movieDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Movie findById(int objectId) throws NotFoundException {
        return movieDAO.findOne(objectId);
    }

    @Override
    public Movie update(Movie object) throws NotFoundException {
        Movie oldObject = movieDAO.findOne(object.getMovieId());
        if (oldObject == null) {
            throw new NotFoundException(object.getMovieId(), Movie.class);
        }
        return movieDAO.save(object);
    }

    //---------------------
    //------Special queries
    public Movie createByLink(String movieLink) {
        String youtubeLik = "https://www.googleapis.com/youtube/v3/videos?id=" + movieLink + "&key=AIzaSyBjdxNzZcXyuNHbqCoLl6jQDdUnDDRgMkw&fields=items(id,snippet(title),statistics)&part=snippet,statistics";
        URL youtubeUrl = null;
        try {
            youtubeUrl = new URL(youtubeLik);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return movieDAO.save(youtubeJsonMapper(getMovieInformation(youtubeUrl)));
    }

    // Parse YouTube response
    private String getMovieInformation(URL youtubeMovieUrl) {
        String json = "";
        URLConnection connection = null;
        try {
            connection = youtubeMovieUrl.openConnection();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                json += inputLine;
            }

            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.toString();
                e.printStackTrace();
                return json;
            }
        } catch (Exception e) {
            e.toString();
            e.printStackTrace();
        }

        return json;
    }

    // Map YouTube object
    private Movie youtubeJsonMapper(String json) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

        String id = ((ArrayList<String>) JsonPath.read(document, "$.items..id")).get(0);
        String title = ((ArrayList<String>) JsonPath.read(document, "$.items..title")).get(0);
        String viewCount = ((ArrayList<String>) JsonPath.read(document, "$.items..viewCount")).get(0);
        String likeCount = ((ArrayList<String>) JsonPath.read(document, "$.items..likeCount")).get(0);
        String dislikeCount = ((ArrayList<String>) JsonPath.read(document, "$.items..dislikeCount")).get(0);

        Movie movie = new Movie();
        movie.setMovieLink(id);
        movie.setMovieTitle(title);
        movie.setMovieLikeCount(likeCount);
        movie.setMovieDislikeCount(dislikeCount);
        movie.setMovieViewCount(viewCount);
        movie.setMovieCTR((Float.parseFloat(likeCount) / Float.parseFloat(viewCount)) * 100);

        return movie;
    }
}
