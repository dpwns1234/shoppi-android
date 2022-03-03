package com.shoppi.app.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
        val categoryAdapter = CategoryAdapter(viewModel)

        // 문법상 RecyclerView는 adapter를 설정해줘야 한다.
        binding.rvCategoryList.adapter = categoryAdapter

        // item이 변경될때마다(= observe)
        viewModel.items.observe(viewLifecycleOwner) {
            // adapter에 데이터를 넣어준다.
            categoryAdapter.submitList(it)
        }

        // openCategoryEvent의 상태가 변경될 때 실행된다.(= 특정 카테고리가 클릭(onClick)되었을 때)
        viewModel.openCategoryEvent.observe(viewLifecycleOwner) {
            openCategoryDetail(it.categoryId, it.label)
        }
    }

    // 특정 카테고리 디테일이 선택되었을 때, 실행된다.
    private fun openCategoryDetail(categoryId: String, categoryLabel: String) {

        findNavController().navigate(R.id.action_category_to_category_detail, bundleOf(
            // "key" to "value"
            "category_id" to categoryId,
            "category_label" to categoryLabel
        )
        )
    }
}