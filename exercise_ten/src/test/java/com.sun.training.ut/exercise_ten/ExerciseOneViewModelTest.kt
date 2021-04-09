package com.sun.training.ut

import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.data.model.Invoice
import com.sun.training.ut.exercise_ten.data.model.MemberClassType.Companion.BLACK_CLASS
import com.sun.training.ut.exercise_ten.data.model.MemberClassType.Companion.GOLD_CLASS
import com.sun.training.ut.exercise_ten.data.model.MemberClassType.Companion.SILVER_CLASS
import com.sun.training.ut.exercise_ten.data.model.MemberClassType.Companion.UNKNOWN_CLASS
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness
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
    private lateinit var mockContext: Context

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
        `when`(mockContext.resources).thenReturn(mockResources)
        `when`(mockResources.getString(R.string.ex_10_user_name_default)).thenReturn("Mai Quốc Đạt")
        `when`(mockResources.getString(R.string.ex_10_class_type_black)).thenReturn(
            BLACK_CLASS_STRING
        )
        `when`(mockResources.getString(R.string.ex_10_class_type_gold)).thenReturn(GOLD_CLASS_STRING)
        `when`(mockResources.getString(R.string.ex_10_class_type_silver)).thenReturn(
            SILVER_CLASS_STRING
        )
        `when`(mockResources.getString(R.string.ex_10_class_type_unknown)).thenReturn(
            UNKNOWN_CLASS_STRING
        )
        viewModel = ExerciseTenViewModel(mockResources)
    }

    //region Test for printInvoice - 20 case
    //1
    @Test
    fun validateInvoice_Silver3k_return1Discount() {
        validateCommonForInvoiceTest(
            PAYMENT_3K,
            SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_silver,
            false
        )
    }

    //2
    @Test
    fun validateInvoice_Silver5k_HaveGift_return2Discount_Gift() {
        validateCommonForInvoiceTest(
            PAYMENT_5K,
            SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_silver,
            true
        )
    }

    //3
    @Test
    fun validateInvoice_Silver5k_NotGift_return2Discount() {
        validateCommonForInvoiceTest(
            PAYMENT_5K,
            SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_silver,
            false
        )
    }

    //4
    @Test
    fun validateInvoice_Silver10k_HaveGift_return4Discount_Gift() {
        validateCommonForInvoiceTest(
            PAYMENT_10K,
            SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_silver,
            true
        )
    }

    //5
    @Test
    fun validateInvoice_Silver10k_NotGift_return4Discount() {
        validateCommonForInvoiceTest(
            PAYMENT_10K,
            SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_silver,
            false
        )
    }

    //6
    @Test
    fun validateInvoice_Gold3k_return3Discount() {
        validateCommonForInvoiceTest(
            PAYMENT_3K,
            GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_gold,
            false
        )
    }

    //7
    @Test
    fun validateInvoice_Gold5k_HaveGift_return5Discount_Gift() {
        validateCommonForInvoiceTest(
            PAYMENT_5K,
            GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_gold,
            true
        )
    }

    //8
    @Test
    fun validateInvoice_Gold5k_NotGift_return5Discount() {
        validateCommonForInvoiceTest(
            PAYMENT_5K,
            GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_gold,
            false
        )
    }

    //9
    @Test
    fun validateInvoice_Gold10k_HaveGift_return10Discount_Gift() {
        validateCommonForInvoiceTest(
            PAYMENT_10K,
            GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_gold,
            true
        )
    }

    //10
    @Test
    fun validateInvoice_Gold10k_NotGift_return10Discount() {
        validateCommonForInvoiceTest(
            PAYMENT_10K,
            GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_gold,
            false
        )
    }

    //11
    @Test
    fun validateInvoice_Black3k_return3Discount() {
        validateCommonForInvoiceTest(
            PAYMENT_3K,
            BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_black,
            false
        )
    }

    //12
    @Test
    fun validateInvoice_Black5k_HaveGift_return7Discount_Gift() {
        validateCommonForInvoiceTest(
            PAYMENT_5K,
            BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_black,
            true
        )
    }

    //13
    @Test
    fun validateInvoice_Black5k_NotGift_return7Discount() {
        validateCommonForInvoiceTest(
            PAYMENT_5K,
            BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_black,
            false
        )
    }

    //14
    @Test
    fun validateInvoice_Black10k_HaveGift_return15Discount_Gift() {
        validateCommonForInvoiceTest(
            PAYMENT_10K,
            BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_black,
            true
        )
    }

    //15
    @Test
    fun validateInvoice_Black10k_NotGift_return15Discount() {
        validateCommonForInvoiceTest(
            PAYMENT_10K,
            BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_black,
            false
        )
    }

    //16
    @Test
    fun validateInvoice_Unknown3k_returnNothing() {
        validateCommonForInvoiceTest(
            PAYMENT_3K,
            UNKNOWN_CLASS_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_unknown,
            false
        )
    }

    //17
    @Test
    fun validateInvoice_Unknown5k_HaveGift_returnGift() {
        validateCommonForInvoiceTest(
            PAYMENT_5K,
            UNKNOWN_CLASS_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_unknown,
            true
        )
    }

    //18
    @Test
    fun validateInvoice_Unknown5k_NotGift_returnNothing() {
        validateCommonForInvoiceTest(
            PAYMENT_5K,
            UNKNOWN_CLASS_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_unknown,
            false
        )
    }

    //19
    @Test
    fun validateInvoice_Unknown10k_HaveGift_returnGift() {
        validateCommonForInvoiceTest(
            PAYMENT_10K,
            UNKNOWN_CLASS_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_unknown,
            true
        )
    }

    //20
    @Test
    fun validateInvoice_Unknown10k_NotGift_returnNothing() {
        validateCommonForInvoiceTest(
            PAYMENT_10K,
            UNKNOWN_CLASS_DISCOUNT_PERCENT,
            R.string.ex_10_class_type_unknown,
            false
        )
    }

    private fun validateCommonForInvoiceTest(
        payment: Double,
        discount: Double,
        classTypeRes: Int,
        isGift: Boolean
    ) {
        viewModel.updateMemberClassType(
            mockContext.resources.getString(classTypeRes)
        )
        viewModel.subTotal.value = payment.toString()
        viewModel.printInvoice()
        assertEquals(
            Invoice(
                invoiceId = 1,
                subTotal = payment,
                discount = payment * discount,
                giftAccepted = isGift,
                total = payment - payment * discount
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

    //region Test for discountCalculation - 12 case
    //1
    @Test
    fun validateDiscount_Silver3k_return1Discount() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_3K)
        assertEquals(discount, PAYMENT_3K * SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT, 0.0)
    }

    //2
    @Test
    fun validateDiscount_Silver5k_return2Discount() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_5K)
        assertEquals(discount, PAYMENT_5K * SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT, 0.0)
    }

    //3
    @Test
    fun validateDiscount_Silver10k_return4Discount() {
        viewModel.updateMemberClassType(SILVER_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_10K)
        assertEquals(discount, PAYMENT_10K * SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT, 0.0)
    }

    //4
    @Test
    fun validateDiscount_Gold3k_return3Discount() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_3K)
        assertEquals(discount, PAYMENT_3K * GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT, 0.0)
    }

    //5
    @Test
    fun validateDiscount_Gold5k_return5Discount() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_5K)
        assertEquals(discount, PAYMENT_5K * GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT, 0.0)
    }

    //6
    @Test
    fun validateDiscount_Gold10k_return10Discount() {
        viewModel.updateMemberClassType(GOLD_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_10K)
        assertEquals(discount, PAYMENT_10K * GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT, 0.0)
    }

    //7
    @Test
    fun validateDiscount_Black3k_return5Discount() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_3K)
        assertEquals(discount, PAYMENT_3K * BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT, 0.0)
    }

    //8
    @Test
    fun validateDiscount_Black5k_return7Discount() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_5K)
        assertEquals(discount, PAYMENT_5K * BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT, 0.0)
    }

    //9
    @Test
    fun validateDiscount_Black10k_return15Discount() {
        viewModel.updateMemberClassType(BLACK_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_10K)
        assertEquals(discount, PAYMENT_10K * BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT, 0.0)
    }

    //10
    @Test
    fun validateDiscount_Unknown3k_return0Discount() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_10K)
        assertEquals(discount, PAYMENT_3K * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }

    //11
    @Test
    fun validateDiscount_Unknown5k_return0Discount() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_10K)
        assertEquals(discount, PAYMENT_10K * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }

    //12
    @Test
    fun validateDiscount_Unknown10k_return0Discount() {
        viewModel.updateMemberClassType(UNKNOWN_CLASS_STRING)
        val discount = viewModel.discountCalculation(PAYMENT_10K)
        assertEquals(discount, PAYMENT_10K * UNKNOWN_CLASS_DISCOUNT_PERCENT, 0.0)
    }
    //endregion
}
