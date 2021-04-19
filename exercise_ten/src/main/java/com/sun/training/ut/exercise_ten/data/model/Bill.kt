package com.quanghoa.apps.lequanghoaunittestexam.model

data class Bill(
    val billId: Int,
    val payment: Double,
    val discount: Double = 0.0,
    val giftAccepted: Boolean = false,
    val realPayment: Double
)