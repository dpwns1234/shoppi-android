package com.shoppi.app.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppi.app.model.Category
import com.shoppi.app.repository.category.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: CategoryRepository): ViewModel() {

    private val _items = MutableLiveData<List<Category>>()
    val items: LiveData<List<Category>> = _items

    // 선택되었는지의 여부를 관리?? 이게 맞아? 그럼 선택 되었다 or 안되었다 이것만 관리하는건가? <- 96. 10분
    // 카테고리 디테일에서 선택된 Category를 '저장', '관리'
    private val _openCategoryEvent = MutableLiveData<Category>()
    val openCategoryEvent: LiveData<Category> = _openCategoryEvent

    init {
        loadCategory()
    }

    // 선택된 카테고리에 데이터를 저장
    fun openCategoryDetail(category: Category) {
        _openCategoryEvent.value = category
    }

    private fun loadCategory() {
        // TODO repository에 데이터 요청
        viewModelScope.launch {
            val categories = categoryRepository.getCategories()
            _items.value = categories
        }
    }
}