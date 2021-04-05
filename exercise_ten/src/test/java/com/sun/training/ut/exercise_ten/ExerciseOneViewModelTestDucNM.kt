package com.sun.training.ut.exercise_ten

import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.data.model.Invoice
import com.sun.training.ut.exercise_ten.data.model.MemberClassType
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.PaymentAmountPointBusiness.PAYMENT_10K
import com.sun.training.ut.exercise_ten.domain.business.PaymentAmountPointBusiness.PAYMENT_3K
import com.sun.training.ut.exercise_ten.domain.business.PaymentAmountPointBusiness.PAYMENT_5K
import com.sun.training.ut.exercise_ten.ui.ExerciseTenViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ExerciseOneViewModelTestDucNM {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockResources: Resources

    private lateinit var viewModel: ExerciseTenViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(mockContext.resources).thenReturn(mockResources)
        `when`(mockResources.getString(R.string.ex_10_user_name_default)).thenReturn("DucNM")
        viewModel = ExerciseTenViewModel(mockResources)
    }

    @Test
    fun validateInvoice_Silver_pay3k_returnTotal_with_1PercentDiscount_NoGift() {
        viewModel.updateMemberType(MemberClassType.SILVER_CLASS)
        viewModel.subTotal.value = PAYMENT_3K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_3K,
                discount = PAYMENT_3K * SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = PAYMENT_3K - PAYMENT_3K * SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    @Test
    fun validateInvoice_Silver_pay5k_returnTotal_with2Percent_Discount_HaveGift() {
        viewModel.updateMemberType(MemberClassType.SILVER_CLASS)
        viewModel.subTotal.value = PAYMENT_5K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_5K,
                discount = PAYMENT_5K * SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_5K - PAYMENT_5K * SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    @Test
    fun validateInvoice_Silver_pay10k_returnTotal_with_4Percent_Discount_HaveGift() {
        viewModel.updateMemberType(MemberClassType.SILVER_CLASS)
        viewModel.subTotal.value = PAYMENT_10K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_10K,
                discount = PAYMENT_10K * SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_10K - PAYMENT_10K * SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    @Test
    fun validateInvoice_Gold_pay3k_returnTotal_with_3Percent_Discount_NoGift() {
        viewModel.updateMemberType(MemberClassType.GOLD_CLASS)
        viewModel.subTotal.value = PAYMENT_3K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_3K,
                discount = PAYMENT_3K * GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = PAYMENT_3K - PAYMENT_3K * GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    @Test
    fun validateInvoice_Gold_pay5k_returnTotal_with_5Percent_Discount_HaveGift() {
        viewModel.updateMemberType(MemberClassType.GOLD_CLASS)
        viewModel.subTotal.value = PAYMENT_5K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_5K,
                discount = PAYMENT_5K * GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_5K - PAYMENT_5K * GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    @Test
    fun validateInvoice_Gold_pay10k_returnTotal_with_10Percent_Discount_HaveGift() {
        viewModel.updateMemberType(MemberClassType.GOLD_CLASS)
        viewModel.subTotal.value = PAYMENT_10K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_10K,
                discount = PAYMENT_10K * GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_10K - PAYMENT_10K * GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    @Test
    fun validateInvoice_Black_pay3k_returnTotal_with_3Percent_Discount_NoGift() {
        viewModel.updateMemberType(MemberClassType.BLACK_CLASS)
        viewModel.subTotal.value = PAYMENT_3K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_3K,
                discount = PAYMENT_3K * BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = PAYMENT_3K - PAYMENT_3K * BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    @Test
    fun validateInvoice_Black_pay5k_returnTotal_with_7Percent_Discount_HaveGift() {
        viewModel.updateMemberType(MemberClassType.BLACK_CLASS)
        viewModel.subTotal.value = PAYMENT_5K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_5K,
                discount = PAYMENT_5K * BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_5K - PAYMENT_5K * BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    @Test
    fun validateInvoice_Black_pay10k_returnTotal_with_15Percent_Discount_HaveGift() {
        viewModel.updateMemberType(MemberClassType.BLACK_CLASS)
        viewModel.subTotal.value = PAYMENT_10K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_10K,
                discount = PAYMENT_10K * BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_10K - PAYMENT_10K * BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    @Test
    fun validateInvoice_Un_Know_pay3k_returnTotal_with_0Percent_Discount_NoGift() {
        viewModel.updateMemberType(MemberClassType.UNKNOWN_CLASS)
        viewModel.subTotal.value = PAYMENT_3K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_3K,
                discount = PAYMENT_3K * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = PAYMENT_3K - PAYMENT_3K * UNKNOWN_CLASS_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    @Test
    fun validateInvoice_Un_Know_pay5k_returnTotal_with_0Percent_Discount_HaveGift() {
        viewModel.updateMemberType(MemberClassType.UNKNOWN_CLASS)
        viewModel.subTotal.value = PAYMENT_5K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_5K,
                discount = PAYMENT_5K * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_5K - PAYMENT_5K * UNKNOWN_CLASS_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    @Test
    fun validateInvoice_Un_Know_pay10k_returnTotal_with_0Percent_Discount_HaveGift() {
        viewModel.updateMemberType(MemberClassType.UNKNOWN_CLASS)
        viewModel.subTotal.value = PAYMENT_10K.toString()
        viewModel.printInvoice()
        Assert.assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_10K,
                discount = PAYMENT_10K * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_10K - PAYMENT_10K * UNKNOWN_CLASS_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }
}