package com.sun.training.ut.exercise_ten

import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.domain.business.PaymentAmountPointBusiness
import com.sun.training.ut.exercise_ten.ui.ExerciseTenViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.jvm.Throws

@RunWith(MockitoJUnitRunner::class)
class ExerciseTenViewModelTest {

    companion object {
        var NONE = "none member"
        var GOLD = "Hạng Vàng"
        var SILVER = "Hạng Bạc"
        var BLACK = "Hạng Đen"
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTenViewModel

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockResource: Resources

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(mockContext.resources).thenReturn(mockResource)
        Mockito.`when`(mockContext.getString(R.string.ex_10_class_type_black)).thenReturn(BLACK)
        Mockito.`when`(mockContext.getString(R.string.ex_10_class_type_gold)).thenReturn(GOLD)
        Mockito.`when`(mockContext.getString(R.string.ex_10_class_type_silver)).thenReturn(SILVER)
        viewModel = ExerciseTenViewModel(mockResource)
    }

    @Test
    fun silverMember_pay_3K() {
        viewModel.updateMemberClassType(SILVER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_3K.toString()
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_3K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun silverMember_pay_5K() {
        viewModel.updateMemberClassType(SILVER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_5K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_5K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_5K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun silverMember_pay_10K() {
        viewModel.updateMemberClassType(SILVER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_10K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_10K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_10K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_10K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun goldMember_pay_3K() {
        viewModel.updateMemberClassType(GOLD)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_3K.toString()
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_3K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun goldMember_pay_5K() {
        viewModel.updateMemberClassType(GOLD)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_5K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_5K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_5K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun goldMember_pay_10K() {
        viewModel.updateMemberClassType(GOLD)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_10K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_10K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_10K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_10K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun blackMember_pay_3K() {
        viewModel.updateMemberClassType(BLACK)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_3K.toString()
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_3K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun blackMember_pay_5K() {
        viewModel.updateMemberClassType(BLACK)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_5K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_5K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_5K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun blackMember_pay_10K() {
        viewModel.updateMemberClassType(BLACK)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_10K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_10K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_10K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }


}