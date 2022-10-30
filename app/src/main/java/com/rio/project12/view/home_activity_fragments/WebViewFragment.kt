package com.rio.project12.view.home_activity_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import com.rio.project12.R
import com.rio.project12.databinding.FragmentWebViewBinding
import com.rio.project12.model.Rec

class WebViewFragment : Fragment() {
    private lateinit var binding: FragmentWebViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false)

        binding.apply {
            webView.webViewClient = WebViewClient()
            webView.loadUrl(if(Rec.isArticle)
                Rec.dRocketHistory.article.toString()
            else
                Rec.dRocketHistory.wiki.toString()
                )
        }

        return binding.root
    }
}