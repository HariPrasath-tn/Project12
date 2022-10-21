package com.rio.project12.view.home_activity_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.rio.project12.R
import com.rio.project12.databinding.FragmentHomeBinding

/**
 * @HomeFragment -> home ui of the application
 *
 * include two cardViews
 *      Rocket history View of spaceX
 *      weather list view
 *
 */
class HomeFragment : Fragment() {
    // creating a binding container to establish binding with the view
    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        /*
         * opening binding scope block to minimize boilerPlatting
         */
        binding.apply{

            //setting onclick listener for cardView spaceX
            rocketInfoCardView.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_rocketListFragment)
            }

            //setting onClick listener for cardView weather
            weatherInfoCardView.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_weatherPlaceListFragment)
            }
        }

        return binding.root
    }
}