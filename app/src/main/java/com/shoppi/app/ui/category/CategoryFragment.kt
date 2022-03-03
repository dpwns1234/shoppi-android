package com.shoppi.app.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.shoppi.app.R
import com.shoppi.app.databinding.FragmentCategoryBinding
import com.shoppi.app.ui.common.ViewModelFactory

class CategoryFragment:Fragment() {
    private val viewModel: CategoryViewModel by viewModels() { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryAdapter = CategoryAdapter()

        // 문법상 RecyclerView는 adapter를 설정해줘야 한다.
        binding.rvCategoryList.adapter = categoryAdapter

        // item이 변경될때마다(= observe)
        viewModel.items.observe(viewLifecycleOwner) {
            // adapter에 데이터를 넣어준다.
            categoryAdapter.submitList(it)
        }
    }
}