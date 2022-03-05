package com.shoppi.app.repository.categorydetail

import com.shoppi.app.model.CategoryDetail
import com.shoppi.app.network.ApiClient

class CategoryDetailRemoteSource(private val api: ApiClient): CategoryDetailDataSource {
    override suspend fun getCategoryDetail(): CategoryDetail {
        return api.getCategoryDetail()
    }
}