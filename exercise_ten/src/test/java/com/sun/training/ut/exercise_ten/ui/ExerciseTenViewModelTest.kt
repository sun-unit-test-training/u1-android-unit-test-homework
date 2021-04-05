package com.sun.training.ut.exercise_ten.ui

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.domain.business.PaymentAmountPointBusiness
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseTenViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockResources: Resources
    private lateinit var viewModel: ExerciseTenViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(mockResources.getString(R.string.ex_10_user_name_default)).thenReturn("Ho Van Ten")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_gold)).thenReturn("Hạng Vàng")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_black)).thenReturn("Hạng Đen")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_silver)).thenReturn("Hạng Bạc")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_unknown)).thenReturn("Không phải hội viên")

        viewModel = ExerciseTenViewModel(mockResources)
    }

    @Test
    fun testDiscountCalculation_blackClass_subtotal3000_equal150() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))

        Assert.assertEquals(150.0, viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K), 0.0)
    }

    @Test
    fun testDiscountCalculation_blackClass_subtotal5000_equal350() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))

        Assert.assertEquals(350.0, viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K), 0.0001)
    }

    @Test
    fun testDiscountCalculation_blackClass_subtotal10000_equal1500() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))

        Assert.assertEquals(1500.0, viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_10K), 0.0)
    }

    @Test
    fun testDiscountCalculation_goldClass_subtotal3000_equal90() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))

        Assert.assertEquals(90.0, viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K), 0.0)
    }

    @Test
    fun testDiscountCalculation_goldClass_subtotal5000_equal250() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))

        Assert.assertEquals(250.0, viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K), 0.0)
    }

    @Test
    fun testDiscountCalculation_goldClass_subtotal10000_equal1000() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))

        Assert.assertEquals(1000.0, viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_10K), 0.0)
    }


    @Test
    fun testDiscountCalculation_silverClass_subtotal3000_equal30() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))

        Assert.assertEquals(30.0, viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K), 0.0)
    }

    @Test
    fun testDiscountCalculation_silverClass_subtotal5000_equal100() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))

        Assert.assertEquals(100.0, viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K), 0.0)
    }

    @Test
    fun testDiscountCalculation_silverClass_subtotal10000_equal400() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))

        Assert.assertEquals(400.0, viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_10K), 0.0)
    }

    @Test
    fun testDiscountCalculation_unknownClass_equal0() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))

        Assert.assertEquals(0.0, viewModel.discountCalculation(2342324.0), 0.0)
    }

    @Test
    fun giftAccepted_subTotal3000_ReturnFalse() {
        Assert.assertEquals(false, viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_3K))
    }

    @Test
    fun giftAccepted_subTotal5000_ReturnTrue() {
        Assert.assertEquals(true, viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_5K))
    }

    @Test
    fun giftAccepted_subTotal10000_ReturnTrue() {
        Assert.assertEquals(true, viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_10K))
    }
}