package com.davidbaudais.myrecipeapp

data class Category(val idCategory: String,
                    val strCategory: String,
                    val strCategoryThumb: String,
                    val strCategoryDescription: String)

// contains a list of category items from the api
data class CategoriesResponse(val categories: List<Category>)

