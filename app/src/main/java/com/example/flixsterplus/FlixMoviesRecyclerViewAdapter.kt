package com.example.flixsterplus

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class FlixMoviesRecyclerViewAdapter(
    private val books: List<FlixMoviesFragment>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<FlixMoviesRecyclerViewAdapter>()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FlixMoviesRecyclerViewAdapter> {
        override fun createFromParcel(parcel: Parcel): FlixMoviesRecyclerViewAdapter {
            return FlixMoviesRecyclerViewAdapter(parcel)
        }

        override fun newArray(size: Int): Array<FlixMoviesRecyclerViewAdapter?> {
            return arrayOfNulls(size)
        }
    }
}
)


class Glide {

}

private fun Nothing?.onItemClick(book: FlixMoviesFragment) {

}

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movies, parent, false)
        return MovieViewHolder(view)
    }


    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {


        private val id: Any
        var mItem: FlixMoviesFragment? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
        val mMovieDescription: TextView = mView.findViewById<View>(id.movie_overview) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(id.movie_image) as ImageView


        override fun toString(): String {
            return mMovieTitle.toString() + " '"
        }
    }


    override fun onMovieViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.description


        Glide.with(holder.mView)
            .load(movie.movieImageUrl)
            .centerInside()
            .into(holder.mMovieImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                var mListener = null
                mListener?.onItemClick(book)

            }
        }
    }

