package net.gahfy.serviceprovider.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import net.gahfy.serviceprovider.base.BaseViewModel
import net.gahfy.serviceprovider.model.Post

class PostViewModel(application: Application):BaseViewModel(application) {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post){
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody():MutableLiveData<String>{
        return postBody
    }
}