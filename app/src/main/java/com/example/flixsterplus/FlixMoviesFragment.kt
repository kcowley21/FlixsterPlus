package com.example.flixsterplus

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

public class FlixMoviesFragment : Fragment(), OnListFragmentInteractionListener  {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView)
        return view
    }


    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()


        val client = AsyncHttpClient()
        val params = RequestParams()
        params["a07e22bc18f5cb106bfe4cc1f83ad8ed"] = API_KEY

        client[
            "https://api.themoviedb.org/3/movie/now_playing",
            params,
            object : JsonHttpResponseHandler()

            {

                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {

                    progressBar.hide()

                    //TODO - Parse JSON into Models

                    val resultsJSON : JSONObject = json.jsonObject.get("results") as JSONObject
                    val booksRawJSON : String = resultsJSON.get("books").toString()
                    val gson = Gson<Any>()
                    val arrayBookType = object : TypeToken<List<FlixMoviesFragment>>() {}.type
                    val models : List<FlixMovies> = gson.fromJson(booksRawJSON, arrayBookType) // Fix me!
                    recyclerView.adapter = FlixMoviesRecyclerViewAdapter(models, this@FlixMoviesFragment)



                    Log.d("FlixMoviessFragment", "response successful")
                }

                /*
                 * The onFailure function gets called when
                 * HTTP response status is "4XX" (eg. 401, 403, 404)
                 */
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {

                    progressBar.hide()

                    // If the error is not null, log it!
                    t?.message?.let {
                        Log.e("FlixMoviessFragment", errorResponse)
                    }
                }
            }]


    }


    override fun onItemClick(item: FlixMovies) {
        Toast.makeText(context, "test: " + item.movie_title, Toast.LENGTH_LONG).show()
    }

}

class Gson<Type> {
    fun fromJson(booksRawJSON: String, arrayBookType: Type?): List<FlixMovies> {

    }

}



