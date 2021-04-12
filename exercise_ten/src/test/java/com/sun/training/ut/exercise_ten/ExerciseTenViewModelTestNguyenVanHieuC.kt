package com.sun.training.ut.exercise_ten

import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.data.model.MemberClassType
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
    No.	             1      2       3       4       5       6       7       8       9       10      11      12         13          14           15          16
    Silver	         T      T       T       F       F       F       F       F       F       F       F       F           T           F           F           F
    Gold	         F      F       F       T       T       T       F       F       F       F       F       F           F           T           F           F
    Black	         F      F       F       F       F       F       T       T       T       F       F       F           F           F           T           F
    NoneMember	     F      F       F       F       F       F       F       F       F       T       T       T           F           F           F           T
    <3k                                                                                                                 T           T           T           T
    3<<5k	             T                      T                       T                       T
    5<<10k                      T                       T                       T                       T
    >10k                             T                       T                       T                       T
    Discount 1%      T
    Discount 2%             T
    Discount 4%                     T
    Discount 3%                             T
    Discount 5%                                     T
    Discount 10%                                             T
    Discount 5%                                                     T
    Discount 7%                                                             T
    Discount 15%                                                                    T
    Discount 0%                                                                             T       T       T           T            T          T           T
     **/

    //region Validate printInvoice
    @Test
    fun validatePrintInvoiceSilverMemberPayment_lower3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 2000.0.toString()
        viewModel.printInvoice()
        assertEquals(2000.0 - PaymentAmountPointBusiness.PAYMENT_LOWER_3K , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceSilverMemberPaymentMoreThan_3K_LowerThan_5k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 3500.toString()
        viewModel.printInvoice()
        assertEquals(3500 - 3500*DiscountBusiness.SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceSilverMemberPaymentMoreThan_5K_LowerThan_10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 6000.toString()
        viewModel.printInvoice()
        assertEquals(6000 - 6000*DiscountBusiness.SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceSilverMemberPaymentMoreThan_10K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 14000.toString()
        viewModel.printInvoice()
        assertEquals(14000 - 14000*DiscountBusiness.SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceGoldMemberPayment_lower3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 1500.0.toString()
        viewModel.printInvoice()
        assertEquals(1500.0 - PaymentAmountPointBusiness.PAYMENT_LOWER_3K , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceGoldMemberPaymentMoreThan_3K_LowerThan_5k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        viewModel.subTotal.value = 3600.toString()
        viewModel.printInvoice()
        assertEquals(3600 - 3600*DiscountBusiness.GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceGoldMemberPaymentMoreThan_5K_LowerThan_10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        viewModel.subTotal.value = 7000.toString()
        viewModel.printInvoice()
        assertEquals(7000 - 7000*DiscountBusiness.GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceGoldMemberPaymentMoreThan_10K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        viewModel.subTotal.value = 17000.toString()
        viewModel.printInvoice()
        assertEquals(17000 - 17000*DiscountBusiness.GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceBlackMemberPayment_lower3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 1000.0.toString()
        viewModel.printInvoice()
        assertEquals(1000.0 - PaymentAmountPointBusiness.PAYMENT_LOWER_3K , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceBlackMemberPaymentMoreThan_3K_LowerThan_5k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        viewModel.subTotal.value = 4500.toString()
        viewModel.printInvoice()
        assertEquals(4500 - 4500*DiscountBusiness.BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceBlackMemberPaymentMoreThan_5K_LowerThan_10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        viewModel.subTotal.value = 6800.toString()
        viewModel.printInvoice()
        assertEquals(6800 - 6800*DiscountBusiness.BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceBlackMemberPaymentMoreThan_10K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        viewModel.subTotal.value = 20000.toString()
        viewModel.printInvoice()
        assertEquals(20000 - 20000*DiscountBusiness.BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceNoneMemberPayment_lower3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 2200.0.toString()
        viewModel.printInvoice()
        assertEquals(2200.0 - PaymentAmountPointBusiness.PAYMENT_LOWER_3K , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceNoneMemberPaymentMoreThan_3K_LowerThan_5k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        viewModel.subTotal.value = 3600.toString()
        viewModel.printInvoice()
        assertEquals(3600 - 3600*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceNoneMemberPaymentMoreThan_5K_LowerThan_10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        viewModel.subTotal.value = 8800.toString()
        viewModel.printInvoice()
        assertEquals(8800 - 8800*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }

    @Test
    fun validatePrintInvoiceNoneMemberPaymentMoreThan_10K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        viewModel.subTotal.value = 30000.toString()
        viewModel.printInvoice()
        assertEquals(30000 - 30000*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.total)
    }
    //endregion

    //region Validate discountCalculation
    @Test
    fun validateDiscountCalculationSilverMemberLower3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 2000.toString()
        viewModel.printInvoice()
        assertEquals(2000*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationSilverMemberMoreThan_3K_LowerThan_5k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 4000.toString()
        viewModel.printInvoice()
        assertEquals(4000*DiscountBusiness.SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationSilverMemberMoreThan_5K_LowerThan_10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 7000.toString()
        viewModel.printInvoice()
        assertEquals(7000*DiscountBusiness.SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationSilverMemberMoreThan10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        viewModel.subTotal.value = 11000.toString()
        viewModel.printInvoice()
        assertEquals(11000*DiscountBusiness.SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationGoldMemberLower3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        viewModel.subTotal.value = 2000.toString()
        viewModel.printInvoice()
        assertEquals(2000*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationGoldMemberMoreThan_3K_LowerThan_5k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        viewModel.subTotal.value = 4000.toString()
        viewModel.printInvoice()
        assertEquals(4000*DiscountBusiness.GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationGoldMemberMoreThan_5K_LowerThan_10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        viewModel.subTotal.value = 7000.toString()
        viewModel.printInvoice()
        assertEquals(7000*DiscountBusiness.GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationGoldMemberMoreThan10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        viewModel.subTotal.value = 11000.toString()
        viewModel.printInvoice()
        assertEquals(11000*DiscountBusiness.GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationBlackMemberLower3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        viewModel.subTotal.value = 2000.toString()
        viewModel.printInvoice()
        assertEquals(2000*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationBlackMemberMoreThan_3K_LowerThan_5k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        viewModel.subTotal.value = 4000.toString()
        viewModel.printInvoice()
        assertEquals(4000*DiscountBusiness.BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationBlackMemberMoreThan_5K_LowerThan_10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        viewModel.subTotal.value = 7000.toString()
        viewModel.printInvoice()
        assertEquals(7000*DiscountBusiness.BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationBlackMemberMoreThan10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        viewModel.subTotal.value = 11000.toString()
        viewModel.printInvoice()
        assertEquals(11000*DiscountBusiness.BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationNoneMemberLower3K() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        viewModel.subTotal.value = 2000.toString()
        viewModel.printInvoice()
        assertEquals(2000*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationNoneMemberMoreThan_3K_LowerThan_5k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        viewModel.subTotal.value = 4000.toString()
        viewModel.printInvoice()
        assertEquals(4000*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationNoneMemberMoreThan_5K_LowerThan_10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        viewModel.subTotal.value = 7000.toString()
        viewModel.printInvoice()
        assertEquals(7000*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }

    @Test
    fun validateDiscountCalculationNoneMemberMoreThan10k() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        viewModel.subTotal.value = 11000.toString()
        viewModel.printInvoice()
        assertEquals(11000*DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT , viewModel.invoice.value?.discount)
    }
//endregion

    //region Validate giftAccepted

    @Test
    fun validateGiftAcceptedAllMemberLower5K() {
        viewModel.subTotal.value = 2000.toString()
        viewModel.printInvoice()
        assertEquals(false , viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateGiftAcceptedAllMemberMoreThan_5K_LowerThan_10k() {
        viewModel.subTotal.value = 7000.toString()
        viewModel.printInvoice()
        assertEquals(false , viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateGiftAcceptedAllMemberMoreThan_10k() {
        viewModel.subTotal.value = 15000.toString()
        viewModel.printInvoice()
        assertEquals(false , viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateGiftAcceptedAllMemberSubtotal5k() {
        viewModel.subTotal.value = 5000.toString()
        viewModel.printInvoice()
        assertEquals(true , viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateGiftAcceptedAllMemberSubtotal10k() {
        viewModel.subTotal.value = 10000.toString()
        viewModel.printInvoice()
        assertEquals(true , viewModel.invoice.value?.giftAccepted)
    }
//endregion

    //region Validate updateMemberClassType

    @Test
    fun validateUpdateMemberClassTypeSilver() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
        assertEquals(MemberClassType.SILVER_CLASS, viewModel.user.value?.classType)
    }

    @Test
    fun validateUpdateMemberClassTypeGold() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
        assertEquals(MemberClassType.GOLD_CLASS, viewModel.user.value?.classType)
    }

    @Test
    fun validateUpdateMemberClassTypeBlack() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        assertEquals(MemberClassType.BLACK_CLASS, viewModel.user.value?.classType)
    }

    @Test
    fun validateUpdateMemberClassTypeNoneMember() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))
        assertEquals(MemberClassType.UNKNOWN_CLASS, viewModel.user.value?.classType)
    }
//endregion
}