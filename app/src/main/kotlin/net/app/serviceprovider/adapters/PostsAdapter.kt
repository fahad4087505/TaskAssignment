package net.app.serviceprovider.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_artists_album.view.*
import net.app.serviceprovider.R
import net.app.serviceprovider.fragments.PostListFragment
import net.app.serviceprovider.model.Movie


class PostsAdapter(val items: List<Movie>, val context: Context, val listener: PostListFragment) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_artists_album, parent, false))
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        try {
            holder.nameTextView.text=items[position].movieName
            holder.descriptionTextView.text=items[position].movieRating.toString()
        } catch (ignored: Exception) {
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTextView:TextView=view.category_name
    val descriptionTextView:TextView=view.category_description
}