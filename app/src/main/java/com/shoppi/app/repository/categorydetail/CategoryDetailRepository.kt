package com.shoppi.app.repository.categorydetail

import com.shoppi.app.model.CategoryDetail

class CategoryDetailRepository(private val remoteDetailSource: CategoryDetailRemoteSource){

    suspend fun getCategoryDetail(): CategoryDetail {
        return remoteDetailSource.getCategoryDetail()
    }
}