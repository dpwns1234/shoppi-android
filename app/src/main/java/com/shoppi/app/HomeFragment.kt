package com.shoppi.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import org.json.JSONObject

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleText = view.findViewById<TextView>(R.id.title_text)
        val titleIcon = view.findViewById<ImageView>(R.id.title_icon)
        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewpagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)


        val assetLoader = AssetLoader()
        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")
        Log.d("homeData", homeJsonString ?:"")

        if(!homeJsonString.isNullOrEmpty()){
            val gson = Gson()
            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)



            titleText.text = homeData.title.text
            GlideApp.with(this)
                .load(homeData.title.iconUrl)
                .into(titleIcon)


            viewpager.adapter = HomeBannerAdapter().apply {
                submitList(homeData.topBanners)
            }
        }


    }
}