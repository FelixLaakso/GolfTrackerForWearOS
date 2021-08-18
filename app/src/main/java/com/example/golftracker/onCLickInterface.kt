package com.example.golftracker

interface OnCLickInterface {
    fun onClickListener(holeList: MutableList<Hole>, index: Int)
}