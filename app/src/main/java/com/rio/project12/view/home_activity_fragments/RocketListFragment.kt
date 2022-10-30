package com.rio.project12.view.home_activity_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rio.project12.R
import com.rio.project12.databinding.FragmentRocketListBinding
import com.rio.project12.view.adapter.RocketListAdapter
import com.rio.project12.view_model.RocketListViewModel
import com.rio.project12.view_model.RocketListViewModelFactory

class RocketListFragment : Fragment() {
    private lateinit var binding:FragmentRocketListBinding
    private val rocketListViewModel: RocketListViewModel by lazy{
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this,
            RocketListViewModelFactory(activity.application)
        )[RocketListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_list, container, false)
        val adapter = activity?.applicationContext?.let {
            RocketListAdapter(
                it
            )
        }
        binding.rocketListRecyclerView.adapter = adapter
        binding.rocketListRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.lifecycleOwner = this

        rocketListViewModel.history.observe(viewLifecycleOwner, Observer {
            adapter?.items = it
        })

        return binding.root
    }
}