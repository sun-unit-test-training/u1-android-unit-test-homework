package com.sun.training.ut.exercise_ten

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.data.model.MemberClassType
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness
import com.sun.training.ut.exercise_ten.domain.business.PaymentAmountPointBusiness
import com.sun.training.ut.exercise_ten.ui.ExerciseTenViewModel
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
import org.robolectric.shadows.ShadowPackageManager.resources

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
        Mockito.`when`(mockResources.getString(R.string.ex_10_user_name_default))
                .thenReturn("Ho Van Ten")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_gold))
                .thenReturn("Hạng Vàng")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_black))
                .thenReturn("Hạng Đen")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_silver))
                .thenReturn("Hạng Bạc")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_unknown))
                .thenReturn("Không phải hội viên")

        viewModel = ExerciseTenViewModel(mockResources)
    }

    /*giftAccepted --------------start*/
    @Test
    fun giftAccepted_subTotal10000() {
        Assert.assertEquals(true, viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_10K))
    }

    @Test
    fun giftAccepted_subTotal5000() {
        Assert.assertEquals(true, viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_5K))
    }

    @Test
    fun giftAccepted_subTotal3000() {
        Assert.assertEquals(false, viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_3K))
    }
    /*giftAccepted --------------end*/

    /*updateMemberClassType --------------start*/
    @Test
    fun updateMemberClassType_black() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        Assert.assertEquals(MemberClassType.BLACK_CLASS, viewModel.user.value?.classType)
    }

    @Test
    fun updateMemberClassType_gold() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        Assert.assertEquals(MemberClassType.GOLD_CLASS, viewModel.user.value?.classType)
    }

    @Test
    fun updateMemberClassType_silver() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        Assert.assertEquals(MemberClassType.SILVER_CLASS, viewModel.user.value?.classType)
    }

    @Test
    fun updateMemberClassType_unknown() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        Assert.assertEquals(MemberClassType.UNKNOWN_CLASS, viewModel.user.value?.classType)
    }
    /*updateMemberClassType --------------end*/

    /*discountCalculation --------------start*/
    // BLACK ------
    @Test
    fun discountCalculation_black_subTotal10_bigger() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_10K + 1
        Assert.assertEquals(subTotal * DiscountBusiness.BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_black_subTotal10() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_10K
        Assert.assertEquals(subTotal * DiscountBusiness.BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_black_subTotal5_bigger() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_5K + 1
        Assert.assertEquals(subTotal * DiscountBusiness.BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_black_subTotal5() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_5K
        Assert.assertEquals(subTotal * DiscountBusiness.BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_black_subTotal3_bigger() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_3K + 1
        Assert.assertEquals(subTotal * DiscountBusiness.BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_black_subTotal3() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_3K
        Assert.assertEquals(subTotal * DiscountBusiness.BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_black_subTotal3_less() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_3K - 1
        Assert.assertEquals(subTotal * DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }
    // BLACK ------

    // GOLD------
    @Test
    fun discountCalculation_gold_subTotal10_bigger() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_10K + 1
        Assert.assertEquals(subTotal * DiscountBusiness.GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_gold_subTotal10() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_10K
        Assert.assertEquals(subTotal * DiscountBusiness.GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_gold_subTotal5_bigger() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_5K + 1
        Assert.assertEquals(subTotal * DiscountBusiness.GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_gold_subTotal5() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_5K
        Assert.assertEquals(subTotal * DiscountBusiness.GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_gold_subTotal3_bigger() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_3K + 1
        Assert.assertEquals(subTotal * DiscountBusiness.GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_gold_subTotal3() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_3K
        Assert.assertEquals(subTotal * DiscountBusiness.GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_gold_subTotal3_less() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_3K - 1
        Assert.assertEquals(subTotal * DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }
    // GOLD ------

    // SILVER------
    @Test
    fun discountCalculation_silver_subTotal10_bigger() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_10K + 1
        Assert.assertEquals(subTotal * DiscountBusiness.SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_silver_subTotal10() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_10K
        Assert.assertEquals(subTotal * DiscountBusiness.SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_silver_subTotal5_bigger() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_5K + 1
        Assert.assertEquals(subTotal * DiscountBusiness.SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_silver_subTotal5() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_5K
        Assert.assertEquals(subTotal * DiscountBusiness.SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_silver_subTotal3_bigger() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_3K + 1
        Assert.assertEquals(subTotal * DiscountBusiness.SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_silver_subTotal3() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_3K
        Assert.assertEquals(subTotal * DiscountBusiness.SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }

    @Test
    fun discountCalculation_silver_subTotal3_less() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_3K - 1
        Assert.assertEquals(subTotal * DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }
    // SILVER ------

    // UNKNOWN -----
    @Test
    fun discountCalculation_unknown() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        val subTotal = PaymentAmountPointBusiness.PAYMENT_3K
        Assert.assertEquals(subTotal * DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT, viewModel.discountCalculation(subTotal), 0.0)
    }
    // UNKNOWN -----
    /*discountCalculation --------------end*/


    /*printInvoice --------------start*/
    @Test
    fun printInvoice() {
        with(viewModel) {
            val total = PaymentAmountPointBusiness.PAYMENT_3K
            subTotal.value = total.toString()
            printInvoice()

            val invoice = invoice.value

            // check invoice.subTotal
            Assert.assertEquals(total, invoice?.subTotal)
            // check invoice.discount
            Assert.assertEquals(discountCalculation(total), invoice?.discount)
            // check invoice.giftAccepted
            Assert.assertEquals(giftAccepted(total), invoice?.giftAccepted)
            // check invoice.total
            Assert.assertEquals(total - discountCalculation(total), invoice?.total)
        }
    }
    /*printInvoice --------------end*/
}