package com.my.sapiaassignment.uilayer.petdetail

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.my.sapiaassignment.R
import com.my.sapiaassignment.utility.Constants
import com.my.sapiaassignment.utility.LiveDataInternetConnections


class PetDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pet_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val petContentUrl = arguments?.getString(Constants.SELECTED_PET_CONTENT_URL) ?: ""
        checkInternetConnection(petContentUrl, view)
    }

    private fun checkInternetConnection(petContentUrl: String, view: View) {

        val application: Application = activity?.applicationContext as Application
        val liveDataInternetConnections = LiveDataInternetConnections(application)
        liveDataInternetConnections.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                initViews(petContentUrl, view)
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.internet_not_available_msg),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews(petContentUrl: String, view: View) {
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val webView: WebView = view.findViewById(R.id.webview)
        webView.loadUrl(petContentUrl)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object :WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                webView.visibility=View.VISIBLE
                progressBar.visibility=View.GONE
            }
        }

    }

}