package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.ui.exercise_five.ExerciseFiveViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseFiveViewModelTest_MaiQuocDat {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var exerciseFiveViewModel: ExerciseFiveViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        exerciseFiveViewModel = ExerciseFiveViewModel()
    }

    //1
    @Test
    fun calculateDiscount_Price1500_NotDelivery_NotVoucher_returnPOTATO_PROMOTION_PIZZA_SECOND_FREE() {
        exerciseFiveViewModel.totalPrice = Constant.DEFAULT_PRICE + 1
        exerciseFiveViewModel.onChangedDelivery(false)
        exerciseFiveViewModel.onChangedVoucher(false)
        exerciseFiveViewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.PIZZA_SECOND_FREE.coupon,
            exerciseFiveViewModel.discountLiveData.value
        )
    }

    //2
    @Test
    fun calculateDiscount_Price1500_Delivery_NotVoucher_returnPOTATO_PROMOTION() {
        exerciseFiveViewModel.totalPrice = Constant.DEFAULT_PRICE + 1
        exerciseFiveViewModel.onChangedDelivery(true)
        exerciseFiveViewModel.onChangedVoucher(false)
        exerciseFiveViewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.REGULAR_FEE.coupon,
            exerciseFiveViewModel.discountLiveData.value
        )
    }

    //3
    @Test
    fun calculateDiscount_Price1500_Delivery_Voucher_returnPOTATO_PROMOTION_OFF_20() {
        exerciseFiveViewModel.totalPrice = Constant.DEFAULT_PRICE + 1
        exerciseFiveViewModel.onChangedDelivery(true)
        exerciseFiveViewModel.onChangedVoucher(true)
        exerciseFiveViewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.OFF_20.coupon,
            exerciseFiveViewModel.discountLiveData.value
        )
    }

    //4
    @Test
    fun calculateDiscount_NotPrice1500_NotDelivery_NotVoucher_returnPIZZA_SECOND_FREE() {
        exerciseFiveViewModel.totalPrice = Constant.DEFAULT_PRICE
        exerciseFiveViewModel.onChangedDelivery(false)
        exerciseFiveViewModel.onChangedVoucher(false)
        exerciseFiveViewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.PIZZA_SECOND_FREE.coupon,
            exerciseFiveViewModel.discountLiveData.value
        )
    }

    //5
    @Test
    fun calculateDiscount_NotPrice1500_Delivery_NotVoucher_returnREGULAR_FEE() {
        exerciseFiveViewModel.totalPrice = Constant.DEFAULT_PRICE
        exerciseFiveViewModel.onChangedDelivery(true)
        exerciseFiveViewModel.onChangedVoucher(false)
        exerciseFiveViewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.REGULAR_FEE.coupon,
            exerciseFiveViewModel.discountLiveData.value
        )
    }

    //6
    @Test
    fun calculateDiscount_NotPrice1500_Delivery_Voucher_returnOFF_20() {
        exerciseFiveViewModel.totalPrice = Constant.DEFAULT_PRICE
        exerciseFiveViewModel.onChangedDelivery(true)
        exerciseFiveViewModel.onChangedVoucher(true)
        exerciseFiveViewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.OFF_20.coupon,
            exerciseFiveViewModel.discountLiveData.value
        )
    }
}