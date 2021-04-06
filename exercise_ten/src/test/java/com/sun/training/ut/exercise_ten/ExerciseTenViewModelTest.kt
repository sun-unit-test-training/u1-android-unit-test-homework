package com.sun.training.ut.exercise_ten

import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.domain.business.PaymentAmountPointBusiness
import com.sun.training.ut.exercise_ten.ui.ExerciseTenViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@RunWith(MockitoJUnitRunner.Silent::class)
class ExerciseTenViewModelTest {

    private lateinit var viewModel: ExerciseTenViewModel

    @Mock
    private lateinit var resource: Resources

    @Mock
    private lateinit var mockContext: Context

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    companion object {
        var NONE_MEMBER = "none member"
        var GOLD = "Hạng Vàng"
        var SILVER = "Hạng Bạc"
        var BLACK = "Hạng Đen"
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(mockContext.resources).thenReturn(resource)
        Mockito.`when`(mockContext.getString(R.string.ex_10_class_type_black)).thenReturn(BLACK)
        Mockito.`when`(mockContext.getString(R.string.ex_10_class_type_gold)).thenReturn(GOLD)
        Mockito.`when`(mockContext.getString(R.string.ex_10_class_type_silver)).thenReturn(SILVER)
        viewModel = ExerciseTenViewModel(resource)
    }

    @Test
    fun discount_SilverType_paymentBellow3K_have_0() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 2900.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discount_SilverType_paymentEquals3K_have_1() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = PaymentAmountPointBusiness.PAYMENT_5K
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_5K.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.03)
    }

    @Test
    fun discount_SilverType_paymentFrom3KTo5K_have_1() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 4000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.01)

    }

    @Test
    fun discount_SilverType_paymentEquals5K_have_2() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.02)
    }

    @Test
    fun discount_SilverType_paymentFrom5KTo10K_have_2() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 6000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.02)
    }

    @Test
    fun discount_SilverType_paymentEquals10K_have_4() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 10000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.04)
    }

    @Test
    fun discount_SilverType_paymentAbove10K_have_4() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 11000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.04)
    }

    @Test
    fun discount_GoldType_paymentBellow3K_have_0() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 2900.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discount_GoldType_paymentEquals3K_have_3() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 3000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.03)
    }

    @Test
    fun discount_GoldType_paymentFrom3KTo5K_have_3() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 4000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.03)
    }

    @Test
    fun discount_GoldType_paymentEquals5K_have_5() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }

    @Test
    fun discount_GoldType_paymentFrom5KTo10K_have_5() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 5001.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }

    @Test
    fun discount_GoldType_paymentEquals10K_have_10() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 10000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.10)
    }

    @Test
    fun discount_GoldType_paymentAbove10K_have_10() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 10001.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.10)
    }

    @Test
    fun discount_BlackType_paymentBellow3K_have_0() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 2900.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discount_BlackType_paymentEquals3K_have_5() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 3000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }

    @Test
    fun discount_BlackType_paymentFrom3KTo5K_have_5() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = Random.nextDouble(3001.0, 5000.0)
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }

    @Test
    fun discount_BlackType_paymentEquals5K_have_7() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.07)
    }

    @Test
    fun discount_BlackType_paymentFrom5KTo10K_have_7() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 5001.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.07)
    }

    @Test
    fun discount_BlackType_paymentEquals10K_have_15() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 10000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.15)
    }

    @Test
    fun discount_BlackType_paymentAbove10K_have_15() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 10001.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.15)
    }

    @Test
    fun discount_NoneType_paymentEquals3K_have_0() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = 3000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discount_NoneType_paymentEquals5K_have_0() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discount_NoneType_paymentEquals10K_have_0() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = 10000.0
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discount_NoneType_paymentAny_have_0() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = Random.nextDouble()
        viewModel.subTotal.value = paymentAmount.toString()
        TestCase.assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun gift_paymentsNotEquals5KOr10K_returnFalse() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = 3000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), false)
    }

    @Test
    fun gift_paymentEquals5K_returnTrue() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = 5000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun gift_paymentEquals10K_returnTrue() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = 10000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun gift_SilverType_payments_NotEquals5KOr10K_returnFalse() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 3000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), false)
    }

    @Test
    fun gift_SilverType_paymentEquals5K_returnTrue() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 5000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun gift_SilverType_paymentEquals10K_returnTrue() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 10000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun gift_GoldType_payments_NotEquals5KOr10K_returnFalse() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 3000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), false)
    }

    @Test
    fun gift_GoldType_paymentEquals5K_returnTrue() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 5000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun gift_GoldType_paymentEquals10K_returnTrue() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 10000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun gift_BlackType_paymentsBellow5K_returnTrue() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 3000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), false)
    }

    @Test
    fun gift_BlackType_paymentEquals5K_returnTrue() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 5000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun gift_BlackType_paymentEquals10K_returnTrue() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 10000.0
        TestCase.assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun checkInvoice_SilverType_paymentAny() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = Random.nextDouble(Double.MAX_VALUE)
        viewModel.subTotal.value = paymentAmount.toString()

        val currentDiscount = viewModel.discountCalculation(paymentAmount)
        val currentGiftAccepted = viewModel.giftAccepted(paymentAmount)

        viewModel.printInvoice()
        viewModel.invoice.value?.run {
            TestCase.assertEquals(subTotal, paymentAmount)
            TestCase.assertEquals(discount, currentDiscount)
            TestCase.assertEquals(total, paymentAmount - currentDiscount)
            TestCase.assertEquals(giftAccepted, currentGiftAccepted)
        }
    }

    @Test
    fun checkInvoice_GoldType_paymentAny() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = Random.nextDouble(Double.MAX_VALUE)
        viewModel.subTotal.value = paymentAmount.toString()
        val currentDiscount = viewModel.discountCalculation(paymentAmount)
        val currentGiftAccepted = viewModel.giftAccepted(paymentAmount)

        viewModel.printInvoice()
        viewModel.invoice.value?.run {
            TestCase.assertEquals(subTotal, paymentAmount)
            TestCase.assertEquals(discount, currentDiscount)
            TestCase.assertEquals(total, paymentAmount - currentDiscount)
            TestCase.assertEquals(giftAccepted, currentGiftAccepted)
        }
    }

    @Test
    fun checkInvoice_BlackType_paymentAny() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = Random.nextDouble(Double.MAX_VALUE)
        viewModel.subTotal.value = paymentAmount.toString()
        val currentDiscount = viewModel.discountCalculation(paymentAmount)
        val currentGiftAccepted = viewModel.giftAccepted(paymentAmount)

        viewModel.printInvoice()
        viewModel.invoice.value?.run {
            TestCase.assertEquals(subTotal, paymentAmount)
            TestCase.assertEquals(discount, currentDiscount)
            TestCase.assertEquals(total, paymentAmount - currentDiscount)
            TestCase.assertEquals(giftAccepted, currentGiftAccepted)
        }
    }

    @Test
    fun checkInvoice_NoneType_paymentAny() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = Random.nextDouble(Double.MAX_VALUE)
        viewModel.subTotal.value = paymentAmount.toString()
        val currentDiscount = viewModel.discountCalculation(paymentAmount)
        val currentGiftAccepted = viewModel.giftAccepted(paymentAmount)
        viewModel.printInvoice()
        viewModel.invoice.value?.run {
            TestCase.assertEquals(subTotal, paymentAmount)
            TestCase.assertEquals(discount, currentDiscount)
            TestCase.assertEquals(total, paymentAmount - currentDiscount)
            TestCase.assertEquals(giftAccepted, currentGiftAccepted)
        }
    }

}
