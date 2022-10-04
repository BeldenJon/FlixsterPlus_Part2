package com.example.flixsterplus_part2

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONException


/** API KEY for "themoviedb" JSON data  */
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val TAG = "InTheaterMovieFragment"
const val MOVIE_EXTRA = "MOVIE_EXTRA"
private const val NOW_PLAYING = "https://api.themoviedb.org/3/movie/now_playing"

/** The class for the only fragment in the app, which contains the progress bar, recyclerView,
 * and performs the network calls to themoviedb API.  */
class InTheaterMovieFragment : Fragment(), OnListFragmentInteractionListener {

    /** Constructing the view */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ) : View? {

        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /** Updates the RecyclerView adapter with new data. */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        /** Create and set up an AsyncHTTPClient() */
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        /** Using the client, perform the HTTP request */
        client[NOW_PLAYING, params, object : JsonHttpResponseHandler() {
            /** The onFailure function gets called when HTTP response status is "4XX" */
            override fun onFailure(statusCode: Int, headers: Headers?,
                                   errorResponse: String, t: Throwable?) {
                // The wait for a response is over
                progressBar.hide()

                // If the error is not null, log it!
                t?.message?.let {
                    Log.i(TAG, errorResponse)
                }
            }

            /** The onSuccess function gets called when HTTP response status is "200 OK" */
            override fun onSuccess(statusCode: Int, headers: Headers,
                                   json: JsonHttpResponseHandler.JSON) {
                Log.i(TAG, "successfully fetched now playing movies")
                // The wait for a response is over
                progressBar.hide()
                try {
                    val resultsJSON: JSONArray = json.jsonObject.get("results") as JSONArray
                    val moviesRawJSON: String = resultsJSON.toString()

                    val gson = Gson()
                    val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
                    val models: List<Movie> = gson.fromJson(moviesRawJSON, arrayMovieType)

                    recyclerView.adapter =
                        InTheaterRecycleViewAdapter(models, this@InTheaterMovieFragment)
                } catch (e:JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }
        }]
    }
    override fun onItemClick(movie: Movie) {

        Toast.makeText(context, "Title:     " + movie.title + "\n" +
                "Released:  " + movie.releaseDate + "\n" +
                "Voter Ave: " + movie.voteAverage,
            Toast.LENGTH_LONG).show()


        /*
        /** Navigate to Details screen and pass selected article */
        val intent = Intent(context, MovieDetails::class.java)
        intent.putExtra(MOVIE_EXTRA, movie)//putExtra(MOVIE_EXTRA, movie)
        context.startActivity(intent)
        */
    }
}

