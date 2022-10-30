package com.rio.project12.view.home_activity_fragments

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import coil.load
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.rio.project12.R
import com.rio.project12.databinding.FragmentRocketInfoFragmentBinding
import com.rio.project12.model.Rec
import com.rio.project12.view_model.RocketInfoViewModel
import com.rio.project12.view_model.RocketInfoViewModelFactory

class RocketInfoFragment : Fragment() {
    private lateinit var binding:FragmentRocketInfoFragmentBinding
    private val rocketInfoViewModel: RocketInfoViewModel by lazy{
        var application = requireNotNull(this.activity)
        ViewModelProvider(this,
            RocketInfoViewModelFactory(application.application)
        )[RocketInfoViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_info_fragment, container, false)
        binding.lifecycleOwner = this

        val youtube = binding.infoTop.youtubePlayerView
        lifecycle.addObserver(youtube)

        binding.apply {

            infoBottom.infoViewModel =  rocketInfoViewModel
            infoTop.infoViewModel =  rocketInfoViewModel

            infoTop.rocketTopImageView.load(rocketInfoViewModel.image.value.toString().toUri())
            youtube.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(rocketInfoViewModel.youTubeID.value.toString(), 0F)
                }
            })


            infoBottom.rocketInfoBottomWiki.underline()
            infoBottom.rocketInfoBottomWiki.background.alpha =64
            infoBottom.rocketInfoBottomWiki.setOnClickListener {
                Rec.isArticle = false
                loadWebView(it)
            }

            infoBottom.rocketInfoBottomArticle.underline()
            infoBottom.rocketInfoBottomArticle.background.alpha =64
            infoBottom.rocketInfoBottomArticle.setOnClickListener {
                Rec.isArticle = true
                loadWebView(it)
            }
        }
        return binding.root
    }

    private fun TextView.underline() {
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
    private fun loadWebView(view:View){
        Navigation.findNavController(view).navigate(R.id.action_rocketInfoFragment_to_webViewFragment)
    }
}