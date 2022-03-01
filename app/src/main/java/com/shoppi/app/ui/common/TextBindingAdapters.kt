package com.shoppi.app.ui.common

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