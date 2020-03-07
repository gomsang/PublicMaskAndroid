package com.gomsang.lab.publicmask.models.naver

interface MaskRepository {
    fun find(lat : String, lng : String)
}