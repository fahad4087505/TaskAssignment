package net.gahfy.serviceprovider.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import net.gahfy.serviceprovider.R
import net.gahfy.serviceprovider.adapters.PostsAdapter
import net.gahfy.serviceprovider.base.BaseFragment
import net.gahfy.serviceprovider.databinding.FragmentPostListBinding
import net.gahfy.serviceprovider.model.Movie
import net.gahfy.serviceprovider.viewmodel.PostListViewModel


class PostListFragment: BaseFragment() {

    var manager: LinearLayoutManager? = null
    var latestManager: LinearLayoutManager? = null
    var popularManager: LinearLayoutManager? = null
    private lateinit var binding: FragmentPostListBinding
    private var viewModel: PostListViewModel? = null
    override fun onCreateChildView(inflater: LayoutInflater?, parent: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(requireActivity()).get(PostListViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_post_list, parent, false)
        binding.viewModel = viewModel
        progressBar.show(activity!!)
        viewModel!!.getMovies()!!.observe(this as LifecycleOwner, object : Observer<List<Movie?>?> {
           override fun onChanged(@Nullable items: List<Movie?>?) {
               setAdapter(items as List<Movie>)
               progressBar.dialog.dismiss()
//               binding.progressCircular.visibility=View.GONE
           }
        })

        return binding.root
    }
    private fun setAdapter(items:List<Movie>){
        manager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        latestManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        popularManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.postList.layoutManager = manager
        binding.postList.adapter = PostsAdapter(items, activity!!, this)
        binding.latestRecyclerview.layoutManager = latestManager
        binding.latestRecyclerview.adapter = PostsAdapter(items, activity!!, this)
        binding.popularRecyclerview.layoutManager = popularManager
        binding.popularRecyclerview.adapter = PostsAdapter(items, activity!!, this)
    }
}