package com.example.flixsterplus_part2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "MovieDetails"


class MovieDetails : AppCompatActivity() {
    private lateinit var movieImageView: ImageView
    private lateinit var titleView: TextView
    private lateinit var genreTextView: TextView
    private lateinit var descriptionView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /** Find the views for the screen */
        movieImageView = findViewById(R.id.detMovieImage)
        titleView = findViewById(R.id.detMovieTitle)
        genreTextView = findViewById(R.id.detMovieGenres)
        descriptionView = findViewById(R.id.detMovieDescription)

        /** Get the extra from the Intent */
        val movie = intent.getParcelableArrayExtra(MOVIE_EXTRA) as Movie // getSerializableExtra("MOVIE_EXTRA") as Movie


        /** Set the title, byline, and abstract information from the article */
        titleView.text = movie.title
        //genreTextView.text = movie.genre?[0]
        descriptionView.text = movie.description

        /** Load the media image */
        val imageUrl : String = "https://image.tmdb.org/t/p/w500" + movie.movieImageUrl
        Glide.with(this)
            .load(imageUrl)
            .into(movieImageView)
    }
}