package com.sun.training.ut

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.exercise_ten.domain.business.PaymentAmountPointBusiness
import com.sun.training.ut.exercise_ten.ui.ExerciseTenViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseTenViewModelTest {
    companion object {
        const val NONE_MEMBER = "none member"
        const val GOLD_MEMBER = "Hạng Vàng"
        const val SLIVER_MEMBER = "Hạng Bạc"
        const val BLACK_MEMBER = "Hạng Đen"
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTenViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseTenViewModel(resources = Resources.getSystem())
    }

    /** None member **/
    @Test
    fun noneMemberPayment_3K() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_3K.toString()
        viewModel.printInvoice()
        assertEquals(PaymentAmountPointBusiness.PAYMENT_3K, viewModel.invoice.value?.total)
    }

    @Test
    fun noneMemberPayment_5K() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_5K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_5K)
        viewModel.printInvoice()
        assertEquals(PaymentAmountPointBusiness.PAYMENT_5K, viewModel.invoice.value?.total)
    }

    @Test
    fun noneMemberPayment_10K() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_10K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_5K)
        viewModel.printInvoice()
        assertEquals(PaymentAmountPointBusiness.PAYMENT_10K, viewModel.invoice.value?.total)
    }

    /** None member **/
    @Test
    fun goldMemberPayment_3K() {
        viewModel.updateMemberClassType(GOLD_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_3K.toString()
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_3K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun goldMemberPayment_5K() {
        viewModel.updateMemberClassType(GOLD_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_5K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_5K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_5K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun goldMemberPayment_10K() {
        viewModel.updateMemberClassType(GOLD_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_10K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_10K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_10K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_10K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    /** Sliver member **/
    @Test
    fun sliverMemberPayment_3K() {
        viewModel.updateMemberClassType(SLIVER_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_3K.toString()
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_3K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun sliverMemberPayment_5K() {
        viewModel.updateMemberClassType(SLIVER_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_5K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_5K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_5K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun sliverMemberPayment_10K() {
        viewModel.updateMemberClassType(SLIVER_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_10K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_10K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_10K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_10K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    /** Black member **/
    @Test
    fun blackMemberPayment_3K() {
        viewModel.updateMemberClassType(BLACK_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_3K.toString()
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_3K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun blackMemberPayment_5K() {
        viewModel.updateMemberClassType(BLACK_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_5K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_5K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_5K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

    @Test
    fun blackMemberPayment_10K() {
        viewModel.updateMemberClassType(BLACK_MEMBER)
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_10K.toString()
        viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_10K)
        viewModel.printInvoice()
        val discount = viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K)
        val totalTestCost = PaymentAmountPointBusiness.PAYMENT_10K - discount
        assertEquals(totalTestCost, viewModel.invoice.value?.total)
    }

}
