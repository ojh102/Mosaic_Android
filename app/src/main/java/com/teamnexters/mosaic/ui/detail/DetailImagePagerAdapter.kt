package com.teamnexters.mosaic.ui.detail

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.AppCompatImageView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.teamnexters.mosaic.R


class DetailImagePagerAdapter(val context : Context) : PagerAdapter() {
    val mInflater = LayoutInflater.from(context)

    var imageList = ArrayList<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun getCount(): Int {
        return imageList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = mInflater.inflate(R.layout.image_view_pager_view,container, false)

        val contentImage = view.findViewById<AppCompatImageView>(R.id.content_image)
        Glide.with(context).load(imageList.get(position)).into(contentImage)

        container.addView(view)

        return view
    }
}