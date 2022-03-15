package com.shoppi.app.ui.common

import android.graphics.Paint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat
import com.shoppi.app.R
import kotlin.math.roundToInt

@BindingAdapter("setText")
fun setText(view: TextView, text: String) {
    view.text = text
}

@BindingAdapter("priceAmount")
fun applyPriceFormat(view: TextView, price: Int) {
    val decimalFormat = DecimalFormat("#,###")
    view.text = view.context.getString(R.string.unit_discount_price, decimalFormat.format(price))
}

@BindingAdapter("priceAmount", "discountRate")
fun applyPriceDiscountRate(view: TextView, price: Int, discountRate: Int) {
    val discountPrice = (((100 - discountRate) / 100.0) * price).roundToInt()
    applyPriceFormat(view, discountPrice)
}

@BindingAdapter("priceAmount", "strikeThrough")
fun applyPriceAndStrikeStyle(view: TextView, price: Int, strikeThrough: Boolean) {
    applyPriceFormat(view, price)
    if(strikeThrough) {
        view.paintFlags = view.paintFlags.or(
            Paint.STRIKE_THRU_TEXT_FLAG)
    }


}