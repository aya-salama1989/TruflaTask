package com.trufla.task.app.presentation

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trufla.task.app.domain.LibraryEntity
import com.trufla.task.core.BaseViewModelProvider
import com.trufla.task.databinding.MainFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    //add new items to the list?!
    private val libs = ArrayList<LibraryEntity>()

    @Inject
    lateinit var baseViewModelProvider: BaseViewModelProvider

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, baseViewModelProvider).get(MainViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pbLibraries.visibility = View.VISIBLE
        getData()
    }

    private fun getData() {
        viewModel.getLibrariesList()
        viewModel.libraries.observe(viewLifecycleOwner, {
            binding.tvError.visibility = View.GONE
            binding.pbLibraries.visibility = View.GONE
            setLayout(it)
        })

        viewModel.errorData.observe(viewLifecycleOwner, {
            binding.pbLibraries.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = it
        })
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = binding.rvLibraries.layoutManager as LinearLayoutManager
            if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 3) {
                //Post delayed is used tp avoid 403 API response out
                // of fast scrolling after the 4th page
                Handler().postDelayed({
                    viewModel.getLibrariesList()
                }, 4000)

            }

            super.onScrolled(recyclerView, dx, dy)
        }
    }

    private fun setLayout(libraries: List<LibraryEntity>) {
        if (!libraries.isNullOrEmpty()) {
            libs.addAll(libraries)

            binding.rvLibraries.visibility = View.VISIBLE
            val adapter = LibrariesAdapter()
            binding.rvLibraries.adapter = adapter

            //submit the whole list to adapter again?!
            adapter.submitList(libs)

            binding.rvLibraries.addOnScrollListener(scrollListener)
            //Show loading item in the bottom of the list?
        }else{
            binding.rvLibraries.removeOnScrollListener(scrollListener)
            //Add a notification that there's no more data to show
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}