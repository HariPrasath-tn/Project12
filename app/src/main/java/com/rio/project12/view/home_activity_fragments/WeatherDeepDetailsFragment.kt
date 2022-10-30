package com.rio.project12.view.home_activity_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.rio.project12.R
import com.rio.project12.databinding.FragmentWeatherDeepDetailsBinding
import com.rio.project12.view_model.FWeatherDeepDetailsViewModelFactory
import com.rio.project12.view_model.WeatherDeepDetailsViewModel

class WeatherDeepDetailsFragment : Fragment() {
    private lateinit var binding:FragmentWeatherDeepDetailsBinding
    private val weatherDeepDetailsViewModel:WeatherDeepDetailsViewModel by lazy{
        var activity = requireNotNull(activity)
        ViewModelProvider(this, FWeatherDeepDetailsViewModelFactory(activity.application))[WeatherDeepDetailsViewModel::class.java]
    }

    /*
     * ***** weather bit ******
     * 532e61560fc8468bb9fda40e97b4856c
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_deep_details, container, false)
        binding.apply {
            weatherDeepInfoBottom.deepInfo = weatherDeepDetailsViewModel
            weatherDeepInfoTop.deepInfo = weatherDeepDetailsViewModel

            weatherDeepDetailsViewModel.image.observe(viewLifecycleOwner){
                weatherDeepInfoTop.weatherImage.load(weatherDeepDetailsViewModel.image.value.toString())
            }
        }
        binding.lifecycleOwner = this
        return binding.root
    }
}