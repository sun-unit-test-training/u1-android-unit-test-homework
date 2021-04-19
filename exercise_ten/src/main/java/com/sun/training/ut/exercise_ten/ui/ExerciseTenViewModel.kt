package com.sun.training.ut.exercise_ten.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quanghoa.apps.lequanghoaunittestexam.model.*
import com.sun.training.ut.exercise_ten.util.SingleLiveData

class ExerciseTenViewModel: ViewModel() {
    val user = MutableLiveData<User>()

    val bill = SingleLiveData<Bill>()

    val totalPayment = MutableLiveData<Double>()

    init {
        user.value = User(userId = 1, userName = "Le Quang Hoa", classType = UserClassType.GOLD)
    }

    /**
     * calculation discount
     */
    fun discountCalculation(): Double {
        val total = totalPayment.value ?: 0.0
        return user.value?.let { currentUser ->
            when {
                currentUser.classType == UserClassType.BLACK && total >= PaymentAmount.PAYMENT_10K -> total * DiscountPercent.BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT
                currentUser.classType == UserClassType.BLACK && total >= PaymentAmount.PAYMENT_5K -> total * DiscountPercent.BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT
                currentUser.classType == UserClassType.BLACK && total >= PaymentAmount.PAYMENT_3K -> total * DiscountPercent.BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT

                currentUser.classType == UserClassType.GOLD && total >= PaymentAmount.PAYMENT_10K -> total * DiscountPercent.GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT
                currentUser.classType == UserClassType.GOLD && total >= PaymentAmount.PAYMENT_5K -> total * DiscountPercent.GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT
                currentUser.classType == UserClassType.GOLD && total >= PaymentAmount.PAYMENT_3K -> total * DiscountPercent.GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT

                currentUser.classType == UserClassType.SILVER && total >= PaymentAmount.PAYMENT_10K -> total * DiscountPercent.SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT
                currentUser.classType == UserClassType.SILVER && total >= PaymentAmount.PAYMENT_5K -> total * DiscountPercent.SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT
                currentUser.classType == UserClassType.SILVER && total >= PaymentAmount.PAYMENT_3K -> total * DiscountPercent.SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT

                else -> total * DiscountPercent.UNKNOWN_CLASS_DISCOUNT_PERCENT
            }
        } ?: 0.0
    }

    fun giftAccepted(totalPayment: Double): Boolean =
        totalPayment in Gift.GIFT_ACCEPTED_WITH_PAYMENT_EQUALS

    fun exportBill() {
        val total = totalPayment.value ?: 0.0

        val discount = discountCalculation()
        bill.value = Bill(
            billId = 1,
            payment = total,
            discount = discount,
            giftAccepted = giftAccepted(total),
            realPayment = total - discount
        )
    }

    fun setTotalPayment(payment: Double) {
        this.totalPayment.value = payment
    }

    fun updateUserClassType(type: String) {
        val memberType = when (type) {
            UserClassType.BLACK.name -> UserClassType.BLACK
            UserClassType.GOLD.name -> UserClassType.GOLD
            UserClassType.SILVER.name -> UserClassType.SILVER
            else -> UserClassType.UNKNOWN
        }
        user.value?.let { user ->
            user.classType = memberType
        }

    }
}