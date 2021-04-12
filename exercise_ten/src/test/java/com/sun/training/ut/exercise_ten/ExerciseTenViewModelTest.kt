package com.sun.training.ut.exercise_ten


import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.data.model.MemberClassType
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

@RunWith(MockitoJUnitRunner::class)
class ExerciseTenViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockResources: Resources
    private lateinit var viewModel: ExerciseTenViewModel

    @Before
    private fun setUp() {
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

    @Test
    fun updateMemberClassType_black() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
        Assert.assertEquals(0.0, viewModel.discountCalculation(1000.0), 0.0)
    }

    @Test
    fun testDiscountCalculation_blackClass_subtotal3000_equal150() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))

        Assert.assertEquals(
            150.0,
            viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K),
            0.0
        )
    }

    @Test
    fun testDiscountCalculation_blackClass_subtotal4000_equal200() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))

        Assert.assertEquals(200.0, viewModel.discountCalculation(4000.0), 0.0)
    }

    @Test
    fun testDiscountCalculation_blackClass_subtotal5000_equal350() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))

        Assert.assertEquals(
            350.0,
            viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K),
            0.0001
        )
    }

    @Test
    fun testDiscountCalculation_blackClass_subtotal8000_equal560() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))

        Assert.assertEquals(560.0, viewModel.discountCalculation(8000.0), 0.0)
    }


    @Test
    fun testDiscountCalculation_blackClass_subtotal10000_equal1500() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))

        Assert.assertEquals(
            1500.0,
            viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_10K),
            0.0
        )
    }

    @Test
    fun testDiscountCalculation_blackClass_subtotal11000_equal1500() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))

        Assert.assertEquals(1650.0, viewModel.discountCalculation(11000.0), 0.0)
    }

    @Test
    fun testDiscountCalculation_goldClass_subtotal1000_equal0() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))

        Assert.assertEquals(0.0, viewModel.discountCalculation(1000.0), 0.0)
    }

    @Test
    fun testDiscountCalculation_goldClass_subtotal3000_equal90() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))

        Assert.assertEquals(
            90.0,
            viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K),
            0.0
        )
    }

    @Test
    fun testDiscountCalculation_goldClass_subtotal4000_equal120() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))

        Assert.assertEquals(120.0, viewModel.discountCalculation(4000.0), 0.0)
    }

    @Test
    fun testDiscountCalculation_goldClass_subtotal5000_equal250() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))

        Assert.assertEquals(
            250.0,
            viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K),
            0.0
        )
    }

    @Test
    fun testDiscountCalculation_goldClass_subtotal8000_equal250() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))

        Assert.assertEquals(400.0, viewModel.discountCalculation(8000.0), 0.0)
    }

    @Test
    fun testDiscountCalculation_goldClass_subtotal10000_equal1000() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))

        Assert.assertEquals(
            1000.0,
            viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_10K),
            0.0
        )
    }

    @Test
    fun testDiscountCalculation_goldClass_subtotal11000_equal1000() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))

        Assert.assertEquals(1100.0, viewModel.discountCalculation(11000.0), 0.0)
    }

    @Test
    fun testDiscountCalculation_silverClass_subtotal1000_equal30() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))

        Assert.assertEquals(0.0, viewModel.discountCalculation(1000.0), 0.0)
    }

    @Test
    fun testDiscountCalculation_silverClass_subtotal4000_equal30() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))

        Assert.assertEquals(40.0, viewModel.discountCalculation(4000.0), 0.0)
    }

    @Test
    fun testDiscountCalculation_silverClass_subtotal8000_equal30() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))

        Assert.assertEquals(160.0, viewModel.discountCalculation(8000.0), 0.0)
    }

    @Test
    fun testDiscountCalculation_silverClass_subtotal11000_equal30() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))

        Assert.assertEquals(440.0, viewModel.discountCalculation(11000.0), 0.0)
    }

    @Test
    fun testDiscountCalculation_silverClass_subtotal3000_equal30() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))

        Assert.assertEquals(
            30.0,
            viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_3K),
            0.0
        )
    }

    @Test
    fun testDiscountCalculation_silverClass_subtotal5000_equal100() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))

        Assert.assertEquals(
            100.0,
            viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_5K),
            0.0
        )
    }

    @Test
    fun testDiscountCalculation_silverClass_subtotal10000_equal400() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))

        Assert.assertEquals(
            400.0,
            viewModel.discountCalculation(PaymentAmountPointBusiness.PAYMENT_10K),
            0.0
        )
    }

    @Test
    fun testDiscountCalculation_unknownClass_equal0() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))

        Assert.assertEquals(0.0, viewModel.discountCalculation(2342324.0), 0.0)
    }

    @Test
    fun giftAccepted_subTotal1000_ReturnFalse() {
        Assert.assertEquals(false, viewModel.giftAccepted(1000.0))
    }

    @Test
    fun giftAccepted_subTotal3000_ReturnFalse() {
        Assert.assertEquals(false, viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_3K))
    }

    @Test
    fun giftAccepted_subTotal4000_ReturnFalse() {
        Assert.assertEquals(false, viewModel.giftAccepted(4000.0))
    }

    @Test
    fun giftAccepted_subTotal5000_ReturnTrue() {
        Assert.assertEquals(true, viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_5K))
    }

    @Test
    fun giftAccepted_subTotal8000_ReturnFalse() {
        Assert.assertEquals(false, viewModel.giftAccepted(8000.0))
    }

    @Test
    fun giftAccepted_subTotal10000_ReturnTrue() {
        Assert.assertEquals(true, viewModel.giftAccepted(PaymentAmountPointBusiness.PAYMENT_10K))
    }

    @Test
    fun giftAccepted_subTotal11000_ReturnFalse() {
        Assert.assertEquals(false, viewModel.giftAccepted(11000.0))
    }

    @Test
    fun updateMemberClassType_unknown_ReturnUnknownClass() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_unknown))

        Assert.assertEquals(MemberClassType.UNKNOWN_CLASS, viewModel.user.value?.classType)
    }

    @Test
    fun updateMemberClassType_silver_ReturnSilverClass() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))

        Assert.assertEquals(MemberClassType.SILVER_CLASS, viewModel.user.value?.classType)
    }

    @Test
    fun updateMemberClassType_gold_ReturnSilverClass() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))

        Assert.assertEquals(MemberClassType.GOLD_CLASS, viewModel.user.value?.classType)
    }

    @Test
    fun updateMemberClassType_black_ReturnSilverClass() {
        viewModel.updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))

        Assert.assertEquals(MemberClassType.BLACK_CLASS, viewModel.user.value?.classType)
    }

    @Test
    fun printInvoice_silver_subTotal1000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "1000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(0.0, invoice?.discount)
            Assert.assertEquals(1000.0, invoice?.subTotal)
            Assert.assertEquals(1000.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silver_subTotal3000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "3000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(30.0, invoice?.discount)
            Assert.assertEquals(3000.0, invoice?.subTotal)
            Assert.assertEquals(2970.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silver_subTotal4000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "4000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(40.0, invoice?.discount)
            Assert.assertEquals(4000.0, invoice?.subTotal)
            Assert.assertEquals(3960.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silver_subTotal5000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "5000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(100.0, invoice?.discount)
            Assert.assertEquals(5000.0, invoice?.subTotal)
            Assert.assertEquals(4900.0, invoice?.total)
            Assert.assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silver_subTotal8000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "8000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(160.0, invoice?.discount)
            Assert.assertEquals(8000.0, invoice?.subTotal)
            Assert.assertEquals(7840.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silver_subTotal10000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "10000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(400.0, invoice?.discount)
            Assert.assertEquals(10000.0, invoice?.subTotal)
            Assert.assertEquals(9600.0, invoice?.total)
            Assert.assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silver_subTotal11000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "11000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(440.0, invoice?.discount)
            Assert.assertEquals(11000.0, invoice?.subTotal)
            Assert.assertEquals(10560.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }


    @Test
    fun printInvoice_gold_subTotal1000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "1000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(0.0, invoice?.discount)
            Assert.assertEquals(1000.0, invoice?.subTotal)
            Assert.assertEquals(1000.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_gold_subTotal3000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "3000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(90.0, invoice?.discount)
            Assert.assertEquals(3000.0, invoice?.subTotal)
            Assert.assertEquals(2910.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_gold_subTotal4000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "4000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(120.0, invoice?.discount)
            Assert.assertEquals(4000.0, invoice?.subTotal)
            Assert.assertEquals(3880.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_gold_subTotal5000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "5000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(250.0, invoice?.discount)
            Assert.assertEquals(5000.0, invoice?.subTotal)
            Assert.assertEquals(4750.0, invoice?.total)
            Assert.assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_gold_subTotal8000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "8000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(400.0, invoice?.discount)
            Assert.assertEquals(8000.0, invoice?.subTotal)
            Assert.assertEquals(7600.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_gold_subTotal10000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "10000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(1000.0, invoice?.discount)
            Assert.assertEquals(10000.0, invoice?.subTotal)
            Assert.assertEquals(9000.0, invoice?.total)
            Assert.assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_gold_subTotal11000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "11000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(1100.0, invoice?.discount)
            Assert.assertEquals(11000.0, invoice?.subTotal)
            Assert.assertEquals(9900.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }


    @Test
    fun printInvoice_black_subTotal1000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
            subTotal.value = "1000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(0.0, invoice?.discount)
            Assert.assertEquals(1000.0, invoice?.subTotal)
            Assert.assertEquals(1000.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_black_subTotal3000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
            subTotal.value = "3000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(150.0, invoice?.discount)
            Assert.assertEquals(3000.0, invoice?.subTotal)
            Assert.assertEquals(2850.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_black_subTotal4000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
            subTotal.value = "4000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(200.0, invoice?.discount)
            Assert.assertEquals(4000.0, invoice?.subTotal)
            Assert.assertEquals(3800.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_black_subTotal5000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
            subTotal.value = "5000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(350.0, invoice?.discount ?: 0.0, 0.0001)
            Assert.assertEquals(5000.0, invoice?.subTotal)
            Assert.assertEquals(4650.0, invoice?.total)
            Assert.assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_black_subTotal8000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
            subTotal.value = "8000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(560.0, invoice?.discount)
            Assert.assertEquals(8000.0, invoice?.subTotal)
            Assert.assertEquals(7440.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_black_subTotal10000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
            subTotal.value = "10000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(1500.0, invoice?.discount)
            Assert.assertEquals(10000.0, invoice?.subTotal)
            Assert.assertEquals(8500.0, invoice?.total)
            Assert.assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_black_subTotal11000() {
        with(viewModel) {
            updateMemberClassType(mockResources.getString(R.string.ex_10_class_type_black))
            subTotal.value = "11000"
            printInvoice()

            val invoice = invoice.value

            Assert.assertEquals(1650.0, invoice?.discount)
            Assert.assertEquals(11000.0, invoice?.subTotal)
            Assert.assertEquals(9350.0, invoice?.total)
            Assert.assertEquals(false, invoice?.giftAccepted)
        }
    }

}