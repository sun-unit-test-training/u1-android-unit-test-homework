package com.sun.training.ut.exercise_one

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
import kotlin.jvm.Throws

@RunWith(MockitoJUnitRunner::class)
class ExerciseFiveViewModelTest_NguyenKhacTung {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseFiveViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseFiveViewModel()
    }

    @Test
    fun validateDiscount_withTotal__withReceiveAtStore_returnPOTATO_PROMOTION_PIZZA_SECOND_FREE() {
        viewModel.onChangedDelivery(false)
        viewModel.onChangedVoucher(false)
        viewModel.totalPrice = 1501
        viewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.PIZZA_SECOND_FREE.coupon,
            viewModel.discountLiveData.value
        )
    }

    @Test
    fun validateDiscount_withTotal_withDelivery_withVoucher_returnPOTATO_PROMOTION_OFF20() {
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(true)
        viewModel.totalPrice = 1501
        viewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.OFF_20.coupon,
            viewModel.discountLiveData.value
        )
    }

    @Test
    fun validateDiscount_withTotal_withDelivery_returnPOTATO_PROMOTION_REGULAR_FEE() {
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(false)
        viewModel.totalPrice = 1501
        viewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.REGULAR_FEE.coupon,
            viewModel.discountLiveData.value
        )
    }

    @Test
    fun validateDiscount_withTotal_withReceiveAtStore_returnPOTATO_PROMOTION_OFF20() {
        viewModel.onChangedDelivery(false)
        viewModel.onChangedVoucher(true)
        viewModel.totalPrice = 1501
        viewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.PIZZA_SECOND_FREE.coupon,
            viewModel.discountLiveData.value
        )
    }

    @Test
    fun validateDiscount_noTotal_noDelivery_noVoucher_returnPIZZA_SECOND_FREE() {
        viewModel.onChangedDelivery(false)
        viewModel.onChangedVoucher(false)
        viewModel.totalPrice = 1400
        viewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.PIZZA_SECOND_FREE.coupon,
            viewModel.discountLiveData.value
        )
    }

    @Test
    fun validateDiscount_noTotal_withDelivery_noVoucher_returnREGULAR_FEE() {
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(false)
        viewModel.totalPrice = 1400
        viewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.REGULAR_FEE.coupon,
            viewModel.discountLiveData.value
        )
    }

    @Test
    fun validateDiscount_noTotal_withDelivery_withVoucher_returnOFF_20() {
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(true)
        viewModel.totalPrice = 1400
        viewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.OFF_20.coupon,
            viewModel.discountLiveData.value
        )
    }

    @Test
    fun validateDiscount_noTotal_noDelivery_withVoucher_returnPIZZA_SECOND_FREE() {
        viewModel.onChangedDelivery(false)
        viewModel.onChangedVoucher(true)
        viewModel.totalPrice = 1400
        viewModel.calculateCouponWithPizza()
        Assert.assertEquals(
            Constant.Coupon.PIZZA_SECOND_FREE.coupon,
            viewModel.discountLiveData.value
        )
    }
}
