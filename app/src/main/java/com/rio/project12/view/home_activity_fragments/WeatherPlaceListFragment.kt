package com.rio.project12.view.home_activity_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.rio.project12.R
import com.rio.project12.databinding.FragmentWheatherPlaceListBinding

class WeatherPlaceListFragment : Fragment() {
    // creating a binding container to establish binding with the view
    private lateinit var binding:FragmentWheatherPlaceListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wheather_place_list, container, false)

        /*
         * opening binding scope block to minimize boilerPlatting
         */
        binding.apply{

            // setting onClickListener for back button
            // functionality on click ==> redirect back to home fragment
            weatherToHomeBackButton.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_weatherPlaceListFragment_to_homeFragment)
            }
        }

        return binding.root
    }

}