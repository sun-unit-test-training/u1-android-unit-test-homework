package com.sun.training.ut.exercise_ten

import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness
import com.sun.training.ut.exercise_ten.domain.business.PaymentAmountPointBusiness
import com.sun.training.ut.exercise_ten.ui.ExerciseTenViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ExerciseTenViewModelTestNguyenVanHieuC {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockResources: Resources


    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTenViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(mockContext.resources).thenReturn(mockResources)
        `when`(mockResources.getString(R.string.ex_10_user_name_default)).thenReturn("Nguyễn Văn Hiếu")
        `when`(mockResources.getString(R.string.ex_10_class_type_black)).thenReturn("Hạng Đen")
        `when`(mockResources.getString(R.string.ex_10_class_type_gold)).thenReturn("Hạng Vàng")
        `when`(mockResources.getString(R.string.ex_10_class_type_silver)).thenReturn("Hạng Bạc")
        `when`(mockResources.getString(R.string.ex_10_class_type_unknown)).thenReturn("Không phải hội viên")
        viewModel = ExerciseTenViewModel(mockResources)
    }

    /**
    No.	             1      2       3       4       5       6       7       8       9       10      11      12
    Silver	         T      T       T       F       F       F       F       F       F       F       F       F
    Gold	         F      F       F       T       T       T       F       F       F       F       F       F
    Black	         F      F       F       F       F       F       T       T       T       F       F       F
    NoneMember	     F      F       F       F       F       F       F       F       F       T       T       T
    3k	             T                      T                       T                       T
    5k                      T                       T                       T                       T
    10k                             T                       T                       T                       T
    Discount 1%      T
    Discount 2%             T
    Discount 4%                     T
    Discount 3%                             T
    Discount 5%                                     T
    Discount 10%                                             T
    Discount 5%                                                     T
    Discount 7%                                                             T
    Discount 15%                                                                    T
    Discount 0%                                                                             T       T       T
     **/
    @Test
    fun silverMemberPayment_3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 3000.toString()
        viewModel.printInvoice()
        assertEquals(3000 - PaymentAmountPointBusiness.PAYMENT_3K*DiscountBusiness.SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun silverMemberPayment_5K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 5000.toString()
        viewModel.printInvoice()
        assertEquals(5000 - PaymentAmountPointBusiness.PAYMENT_5K*DiscountBusiness.SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun silverMemberPayment_10K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 10000.toString()
        viewModel.printInvoice()
        assertEquals(10000 - PaymentAmountPointBusiness.PAYMENT_10K*DiscountBusiness.SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun goldMemberPayment_3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        viewModel.subTotal.value = 3000.toString()
        viewModel.printInvoice()
        assertEquals(3000 - PaymentAmountPointBusiness.PAYMENT_3K*DiscountBusiness.GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun goldMemberPayment_5K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        viewModel.subTotal.value = 5000.toString()
        viewModel.printInvoice()
        assertEquals(5000 - PaymentAmountPointBusiness.PAYMENT_5K*DiscountBusiness.GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun goldMemberPayment_10K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        viewModel.subTotal.value = 10000.toString()
        viewModel.printInvoice()
        assertEquals(10000 - PaymentAmountPointBusiness.PAYMENT_10K*DiscountBusiness.GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun blackMemberPayment_3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        viewModel.subTotal.value = 3000.toString()
        viewModel.printInvoice()
        assertEquals(3000 - PaymentAmountPointBusiness.PAYMENT_3K*DiscountBusiness.BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun blackMemberPayment_5K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        viewModel.subTotal.value = 5000.toString()
        viewModel.printInvoice()
        assertEquals(5000 - PaymentAmountPointBusiness.PAYMENT_5K*DiscountBusiness.BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun blackMemberPayment_10K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        viewModel.subTotal.value = 10000.toString()
        viewModel.printInvoice()
        assertEquals(10000 - PaymentAmountPointBusiness.PAYMENT_10K*DiscountBusiness.BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun noneMemberPayment_3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        viewModel.subTotal.value = 3000.toString()
        viewModel.printInvoice()
        assertEquals(3000 - PaymentAmountPointBusiness.PAYMENT_3K*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun noneMemberPayment_5K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        viewModel.subTotal.value = 3000.toString()
        viewModel.printInvoice()
        assertEquals(3000 - PaymentAmountPointBusiness.PAYMENT_5K*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun noneMemberPayment_10K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        viewModel.subTotal.value = 10000.toString()
        viewModel.printInvoice()
        assertEquals(10000 - PaymentAmountPointBusiness.PAYMENT_10K*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }
}