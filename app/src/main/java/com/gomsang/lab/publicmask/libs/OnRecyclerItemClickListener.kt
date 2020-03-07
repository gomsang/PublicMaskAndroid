package com.gomsang.lab.publicmask.libs

interface OnRecyclerItemClickListener<T> {
    fun onClicked(position : Int, data : T)
}