package com.shoppi.app.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.model.Banner
import com.shoppi.app.GlideApp
import com.shoppi.app.R
import com.shoppi.app.databinding.ItemHomeBannerBinding
import java.text.DecimalFormat
import kotlin.math.roundToInt

class HomeBannerAdapter : ListAdapter<Banner, HomeBannerAdapter.HomeBannerViewHolder>(
    BannerDiffCallback()
) {
    private lateinit var binding: ItemHomeBannerBinding
    // ViewHolder가 생성되며 실행
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        binding = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeBannerViewHolder(binding)
    }

    // onCreateViewHolder가 실행되어 ViewHolder가 잘 생성이 되었다면 그 view holder가 인자로 전달된다.
    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // Home Banner에서 inflate 시킬 view를 인자로 받음
    class HomeBannerViewHolder(private val binding: ItemHomeBannerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(banner: Banner) {
            binding.banner = banner
            binding.executePendingBindings()
        }
    }

}

class BannerDiffCallback:DiffUtil.ItemCallback<Banner>() {
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.productDetail.productId == newItem.productDetail.productId
    }

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }

}