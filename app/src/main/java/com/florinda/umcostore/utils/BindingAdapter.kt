package com.florinda.umcostore.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.imageLoader
import coil.request.ImageRequest
import com.florinda.umcostore.screens.on_boarding.Board


@BindingAdapter("android:boardImage")
fun setOnBoardingItemImage(view: ImageView, board: Board){
    val imageLoader = view.context.imageLoader  // execute image request & caching memory management
    val request = ImageRequest.Builder(view.context)
        .data(board.image)
        .target(imageView = view)
        .build()
    imageLoader.enqueue(request)
}



@BindingAdapter("android:board_title")
fun setOnBoardingTitle(view: TextView,board: Board){
    board.title.let {
        view.text = view.context.getText(board.title)
    }
}
