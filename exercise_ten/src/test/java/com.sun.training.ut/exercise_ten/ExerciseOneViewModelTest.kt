package com.sun.training.ut

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.data.model.Invoice
import com.sun.training.ut.exercise_ten.data.model.MemberClassType.Companion.BLACK_CLASS
import com.sun.training.ut.exercise_ten.data.model.MemberClassType.Companion.GOLD_CLASS
import com.sun.training.ut.exercise_ten.data.model.MemberClassType.Companion.SILVER_CLASS
import com.sun.training.ut.exercise_ten.data.model.MemberClassType.Companion.UNKNOWN_CLASS
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
import org.junit.Assert.assertEquals
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
class ExerciseOneViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockResources: Resources

    private lateinit var viewModel: ExerciseTenViewModel

    companion object {
        const val SILVER_CLASS_STRING = "Hạng Bạc"
        const val GOLD_CLASS_STRING = "Hạng Vàng"
        const val BLACK_CLASS_STRING = "Hạng Đen"
        const val UNKNOWN_CLASS_STRING = "Không phải hội viên"
    }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(mockResources.getString(R.string.ex_10_user_name_default)).thenReturn("Mai Quốc Đạt")
        `when`(mockResources.getString(R.string.ex_10_class_type_black)).thenReturn(
            BLACK_CLASS_STRING
        )
        `when`(mockResources.getString(R.string.ex_10_class_type_gold)).thenReturn(GOLD_CLASS_STRING)
        `when`(mockResources.getString(R.string.ex_10_class_type_silver)).thenReturn(
            SILVER_CLASS_STRING
        )
        viewModel = ExerciseTenViewModel(mockResources)
    }

    //region Test for printInvoice - 16 case
    //1
    @Test
    fun validateInvoice_LessSilver3k_return0Discount() {
        val payment = 2000.0
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        viewModel.subTotal.value = payment.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = payment,
                discount = payment * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = payment - payment * UNKNOWN_CLASS_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //2
    @Test
    fun validateInvoice_Silver3k_return1Discount() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_3K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_3K,
                discount = PAYMENT_3K * SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = PAYMENT_3K - PAYMENT_3K * SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //3
    @Test
    fun validateInvoice_Silver5k_HaveGift_return2Discount_Gift() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_5K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_5K,
                discount = PAYMENT_5K * SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_5K - PAYMENT_5K * SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //4
    @Test
    fun validateInvoice_Silver10k_HaveGift_return4Discount_Gift() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_10K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_10K,
                discount = PAYMENT_10K * SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_10K - PAYMENT_10K * SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //5
    @Test
    fun validateInvoice_LessGold3k_return0Discount() {
        val payment = 2000.0
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        viewModel.subTotal.value = payment.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = payment,
                discount = payment * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = payment - payment * UNKNOWN_CLASS_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //6
    @Test
    fun validateInvoice_Gold3k_return3Discount() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_3K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_3K,
                discount = PAYMENT_3K * GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = PAYMENT_3K - PAYMENT_3K * GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //7
    @Test
    fun validateInvoice_Gold5k_HaveGift_return5Discount_Gift() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_5K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_5K,
                discount = PAYMENT_5K * GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_5K - PAYMENT_5K * GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //8
    @Test
    fun validateInvoice_Gold10k_HaveGift_return10Discount_Gift() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_10K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_10K,
                discount = PAYMENT_10K * GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_10K - PAYMENT_10K * GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //9
    @Test
    fun validateInvoice_LessBlack3k_return0Discount() {
        val payment = 2000.0
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        viewModel.subTotal.value = payment.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = payment,
                discount = payment * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = payment - payment * UNKNOWN_CLASS_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //10
    @Test
    fun validateInvoice_Black3k_return3Discount() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_3K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_3K,
                discount = PAYMENT_3K * BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = PAYMENT_3K - PAYMENT_3K * BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //11
    @Test
    fun validateInvoice_Black5k_HaveGift_return7Discount_Gift() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_5K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_5K,
                discount = PAYMENT_5K * BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_5K - PAYMENT_5K * BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //12
    @Test
    fun validateInvoice_Black10k_HaveGift_return15Discount_Gift() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_10K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_10K,
                discount = PAYMENT_10K * BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_10K - PAYMENT_10K * BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //13
    @Test
    fun validateInvoice_LessUnknown3k_returnNothing() {
        val payment = 2000.0
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        viewModel.subTotal.value = payment.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = payment,
                discount = payment * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = payment - payment * UNKNOWN_CLASS_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //14
    @Test
    fun validateInvoice_Unknown3k_returnNothing() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_3K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_3K,
                discount = PAYMENT_3K * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                giftAccepted = false,
                total = PAYMENT_3K - PAYMENT_3K * UNKNOWN_CLASS_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //15
    @Test
    fun validateInvoice_Unknown5k_HaveGift_returnGift() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_5K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_5K,
                discount = PAYMENT_5K * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_5K - PAYMENT_5K * UNKNOWN_CLASS_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }

    //16
    @Test
    fun validateInvoice_Unknown10k_HaveGift_returnGift() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        viewModel.subTotal.value = PAYMENT_10K.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = PAYMENT_10K,
                discount = PAYMENT_10K * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                giftAccepted = true,
                total = PAYMENT_10K - PAYMENT_10K * UNKNOWN_CLASS_DISCOUNT_PERCENT
            ), viewModel.invoice.value
        )
    }
    //endregion

    //region Test for updateMemberClassType - 4 case
    //1
    @Test
    fun validateMemberClass_updateSilver_returnSilverType() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        assertEquals(SILVER_CLASS, viewModel.user.value?.classType)
    }

    //2
    @Test
    fun validateMemberClass_updateGold_returnGoldType() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        assertEquals(GOLD_CLASS, viewModel.user.value?.classType)
    }

    //3
    @Test
    fun validateMemberClass_updateBlack_returnBlackType() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        assertEquals(BLACK_CLASS, viewModel.user.value?.classType)
    }

    //4
    @Test
    fun validateMemberClass_updateUnknown_returnUnknownType() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        assertEquals(UNKNOWN_CLASS, viewModel.user.value?.classType)
    }
    //endregion

    //region Test for giftAccepted - 3 case
    //1
    @Test
    fun validateGiftAccepted_Payment3k_returnFalse() {
        val isGift = viewModel.giftAccepted(PAYMENT_3K)
        assertEquals(false, isGift)
    }

    //2
    @Test
    fun validateGiftAccepted_Payment5k_returnTrue() {
        val isGift = viewModel.giftAccepted(PAYMENT_5K)
        assertEquals(true, isGift)
    }

    //3
    @Test
    fun validateGiftAccepted_Payment10k_returnTrue() {
        val isGift = viewModel.giftAccepted(PAYMENT_10K)
        assertEquals(true, isGift)
    }
    //endregion

    //region Test for discountCalculation - 28 case
    //1
    @Test
    fun validateDiscount_LessSilver3k_return0Discount() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        val subTotal = 2000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }

    //2
    @Test
    fun validateDiscount_Silver3k_return1Discount() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_3K)
        assertEquals(discount, PAYMENT_3K * SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT, 0.0)
    }

    //3
    @Test
    fun validateDiscount_Silver3kTo5k_return1Discount() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        val subTotal = 4000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT, 0.0)
    }

    //4
    @Test
    fun validateDiscount_Silver5k_return2Discount() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_5K)
        assertEquals(discount, PAYMENT_5K * SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT, 0.0)
    }

    //5
    @Test
    fun validateDiscount_Silver5kTo10k_return2Discount() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        val subTotal = 7000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT, 0.0)
    }

    //6
    @Test
    fun validateDiscount_Silver10k_return4Discount() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_10K)
        assertEquals(discount, PAYMENT_10K * SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT, 0.0)
    }

    //7
    @Test
    fun validateDiscount_MoreSilver10k_return4Discount() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        val subTotal = 11000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT, 0.0)
    }

    //8
    @Test
    fun validateDiscount_LessGold3k_return0Discount() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        val subTotal = 2000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }

    //9
    @Test
    fun validateDiscount_Gold3k_return3Discount() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_3K)
        assertEquals(discount, PAYMENT_3K * GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT, 0.0)
    }

    //10
    @Test
    fun validateDiscount_Gold3kTo5k_return3Discount() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        val subTotal = 4000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT, 0.0)
    }

    //11
    @Test
    fun validateDiscount_Gold5k_return5Discount() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_5K)
        assertEquals(discount, PAYMENT_5K * GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT, 0.0)
    }

    //12
    @Test
    fun validateDiscount_Gold5kTo10k_return5Discount() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        val subTotal = 7000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT, 0.0)
    }

    //13
    @Test
    fun validateDiscount_Gold10k_return10Discount() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_10K)
        assertEquals(discount, PAYMENT_10K * GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT, 0.0)
    }

    //14
    @Test
    fun validateDiscount_MoreGold10k_return10Discount() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        val subTotal = 11000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT, 0.0)
    }

    //15
    @Test
    fun validateDiscount_LessBlack3k_return0Discount() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        val subTotal = 2000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }

    //16
    @Test
    fun validateDiscount_Black3k_return5Discount() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_3K)
        assertEquals(discount, PAYMENT_3K * BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT, 0.0)
    }

    //17
    @Test
    fun validateDiscount_Black3kTo5k_return5Discount() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        val subTotal = 4000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT, 0.0)
    }

    //18
    @Test
    fun validateDiscount_Black5k_return7Discount() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_5K)
        assertEquals(discount, PAYMENT_5K * BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT, 0.0)
    }

    //19
    @Test
    fun validateDiscount_Black5kTo10k_return7Discount() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        val subTotal = 7000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT, 0.0)
    }

    //20
    @Test
    fun validateDiscount_Black10k_return15Discount() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_10K)
        assertEquals(discount, PAYMENT_10K * BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT, 0.0)
    }

    //21
    @Test
    fun validateDiscount_MoreBlack10k_return15Discount() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        val subTotal = 11000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT, 0.0)
    }

    //22
    @Test
    fun validateDiscount_LessUnknown3k_return0Discount() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        val subTotal = 2000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }

    //23
    @Test
    fun validateDiscount_Unknown3k_return0Discount() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_3K)
        assertEquals(discount, PAYMENT_3K * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }

    //24
    @Test
    fun validateDiscount_Unknown3kTo5k_return0Discount() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        val subTotal = 4000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }

    //25
    @Test
    fun validateDiscount_Unknown5k_return0Discount() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_5K)
        assertEquals(discount, PAYMENT_5K * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }

    //26
    @Test
    fun validateDiscount_Unknown5kTo10k_return0Discount() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        val subTotal = 7000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }

    //27
    @Test
    fun validateDiscount_Unknown10k_return0Discount() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_10K)
        assertEquals(discount, PAYMENT_10K * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }

    //28
    @Test
    fun validateDiscount_MoreUnknown10k_return0Discount() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        val subTotal = 11000.0
        val discount = viewModel.discountCalculation(subTotal)
        assertEquals(discount, subTotal * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }
    //endregion
}
