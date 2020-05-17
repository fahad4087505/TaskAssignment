package net.app.serviceprovider.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import net.app.serviceprovider.base.BaseViewModel
import net.app.serviceprovider.model.Movie
import net.app.serviceprovider.network.PostApi
import javax.inject.Inject

class PostListViewModel(application: Application): BaseViewModel(application ){
    @Inject
    lateinit var postApi: PostApi
    var items: MutableList<Movie> = mutableListOf()
    private var movieList: MutableLiveData<List<Movie>>? = null
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var mDatabaseReference: DatabaseReference = database.reference
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    private fun setData():List<Movie> {
        val databaseRef = mDatabaseReference.child("users").child("53").child("movies").ref
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.w(TAG, "Failed to read value.", p0.toException())
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                try {
                    for (child in dataSnapshot.children) {
                        items.add(child.getValue(Movie::class.java)!!)
                    }
                    movieList!!.value = items
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
         return items
    }
    fun getMovies(): MutableLiveData<List<Movie>>? {
        if (movieList == null) {
            movieList= MutableLiveData<List<Movie>>()
            setData()
        }
        return movieList
    }
}