package ru.darkvader.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Khairullin on 12/03/16.
 * Youtube movie bean.
 * An entity class which contains the information of a single movie.
 *
 * @author Khairullin
 */

@Entity
@Table(name = "movie", indexes = {
        @Index(columnList = "MOVIE_TITLE", name = "INDEX_MOVIE_TITLE")}
)
public class Movie implements Serializable {

    @Id
    @Column(name = "MOVIE_ID")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int movieId;

    @Basic
    @Column(name = "MOVIE_LINK", nullable = false)
    private String movieLink;

    @Basic
    @Column(name = "MOVIE_TITLE", nullable = false)
    private String movieTitle;

    @Basic
    @Column(name = "MOVIE_VIEW_COUNT", nullable = false)
    private String movieViewCount;

    @Basic
    @Column(name = "MOVIE_LIKE_COUNT", nullable = false)
    private String movieLikeCount;

    @Basic
    @Column(name = "MOVIE_DISLIKE_COUNT", nullable = false)
    private String movieDislikeCount;

    @Basic
    @Column(name = "MOVIE_CTR", nullable = false)
    private float movieCTR;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieViewCount() {
        return movieViewCount;
    }

    public void setMovieViewCount(String movieViewCount) {
        this.movieViewCount = movieViewCount;
    }

    public String getMovieLikeCount() {
        return movieLikeCount;
    }

    public void setMovieLikeCount(String movieLikeCount) {
        this.movieLikeCount = movieLikeCount;
    }

    public String getMovieDislikeCount() {
        return movieDislikeCount;
    }

    public void setMovieDislikeCount(String movieDislikeCount) {
        this.movieDislikeCount = movieDislikeCount;
    }

    public String getMovieLink() {
        return movieLink;
    }

    public void setMovieLink(String movieLink) {
        this.movieLink = movieLink;
    }

    public float getMovieCTR() {
        return movieCTR;
    }

    public void setMovieCTR(float movieCTR) {
        this.movieCTR = movieCTR;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieLink='" + movieLink + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieViewCount='" + movieViewCount + '\'' +
                ", movieLikeCount='" + movieLikeCount + '\'' +
                ", movieDislikeCount='" + movieDislikeCount + '\'' +
                ", movieCTR='" + movieCTR + '\'' +
                '}';
    }

}
