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
import java.text.ChoiceFormat.nextDouble
import java.util.*
import kotlin.jvm.Throws
import kotlin.random.Random.Default.nextDouble
import kotlin.random.Random

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

    /** test discount  **/
    @Test
    fun discountCalculate_NoneMemberPayment_3K() {
        viewModel.updateMemberClassType(NONE)
        val paymentAmount = 3000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_NoneMemberPayment_5K() {
        viewModel.updateMemberClassType(NONE)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_NoneMemberPayment_10K() {
        viewModel.updateMemberClassType(NONE)
        val paymentAmount = 10000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_GoldMemberPaymentLessThan_3K() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 2900.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_GoldMemberPayment_3K() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 3000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.03)
    }

    @Test
    fun discountCalculate_GoldMemberPayment_Between3K_5K() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 3005.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.03)
    }

    @Test
    fun discountCalculate_GoldMemberPayment_5K() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }


    @Test
    fun discountCalculate_GoldMemberPayment_Between5K_10K() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 5005.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }

    @Test
    fun discountCalculate_GoldMemberPayment_10K() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 5005.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.10)
    }

    @Test
    fun discountCalculate_GoldMemberPayment_GreatThan10K() {
        viewModel.updateMemberClassType(GOLD)
        val paymentAmount = 10010.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.10)
    }

    @Test
    fun discountCalculate_SliverMemberPayment_LessThan3K() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 2900.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_SliverMemberPayment_3K() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 3000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.01)
    }

    @Test
    fun discountCalculate_SliverMemberPayment_Between3K_5K() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 3003.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.01)
    }

    @Test
    fun discountCalculate_SliverMemberPayment_5K() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.02)
    }

    @Test
    fun discountCalculate_SliverMemberPayment_Between5K_10K() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 5005.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.02)
    }


    @Test
    fun discountCalculate_SliverMemberPayment_10K() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 10000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.04)
    }

    @Test
    fun discountCalculate_SliverMemberPayment_GrateThan10K() {
        viewModel.updateMemberClassType(SILVER)
        val paymentAmount = 10010.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.04)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_LessThan_3K() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 2900.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_3K() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 3000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_Between3K_5K() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 3003.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_5K() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.07)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_Between5K_10K() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 5005.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.07)
    }



    @Test
    fun discountCalculate_BlackMemberPayment_10K() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 10000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.15)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_GreatThan_10K() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 10010.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.15)
    }

    /** Test gift checking **/
    @Test
    fun giftChecking_withNoneMember_andPaymentEquals5K_willReturnTrue() {
        viewModel.updateMemberClassType(BLACK)
        val paymentAmount = 5000.0
        assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun giftChecking_withNoneMember_andPaymentEquals10K_willReturnTrue() {
        viewModel.updateMemberClassType(NONE)
        val paymentAmount = 10000.0

        assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun giftChecking_withNoneMember_andPaymentNotEquals5KOr10K_willReturnFalse() {
        viewModel.updateMemberClassType(NONE)
        val paymentAmount = Random.nextDouble(Double.MAX_VALUE)
        val accept = paymentAmount == 5000.0 || paymentAmount == 10000.0

        assertEquals(viewModel.giftAccepted(paymentAmount), accept)
    }

    /*** Test check Invoice **/
    @Test
    fun printInvoice_SliverMemberPayment_LessThan3K() {
        with(viewModel) {
            viewModel.updateMemberClassType(SILVER)
            val paymentAmount = 1000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(0.0, invoice?.discount)
            assertEquals(1000.0, invoice?.subTotal)
            assertEquals(1000.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_SliverMemberPayment_3K() {
        with(viewModel) {
            viewModel.updateMemberClassType(SILVER)
            val paymentAmount = 3000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(30.0, invoice?.discount)
            assertEquals(3000.0, invoice?.subTotal)
            assertEquals(2970.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_SliverMemberPayment_Between3k_5k() {
        with(viewModel) {
            viewModel.updateMemberClassType(SILVER)
            val paymentAmount = 4000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()


            val invoice = invoice.value

            assertEquals(40.0, invoice?.discount)
            assertEquals(4000.0, invoice?.subTotal)
            assertEquals(3960.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_SliverMemberPayment_Between_5k() {
        with(viewModel) {
            viewModel.updateMemberClassType(SILVER)
            val paymentAmount = 5000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(100.0, invoice?.discount)
            assertEquals(5000.0, invoice?.subTotal)
            assertEquals(4900.0, invoice?.total)
            assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_SliverMemberPayment_Between5k_10k() {
        with(viewModel) {
            viewModel.updateMemberClassType(SILVER)
            val paymentAmount = 8000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value


            assertEquals(160.0, invoice?.discount)
            assertEquals(8000.0, invoice?.subTotal)
            assertEquals(7840.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_SliverMemberPayment_10k() {
        with(viewModel) {
            viewModel.updateMemberClassType(SILVER)
            val paymentAmount = 10000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(400.0, invoice?.discount)
            assertEquals(10000.0, invoice?.subTotal)
            assertEquals(9600.0, invoice?.total)
            assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_SliverMemberPayment_GreatThan_10k() {
        with(viewModel) {
            viewModel.updateMemberClassType(SILVER)
            val paymentAmount = 11000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(440.0, invoice?.discount)
            assertEquals(11000.0, invoice?.subTotal)
            assertEquals(10560.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }


    @Test
    fun printInvoice_GoldMemberPayment_LessThan_3k() {
        with(viewModel) {
            viewModel.updateMemberClassType(GOLD)
            val paymentAmount = 1000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(0.0, invoice?.discount)
            assertEquals(1000.0, invoice?.subTotal)
            assertEquals(1000.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_GoldMemberPayment_3k() {
        with(viewModel) {
            viewModel.updateMemberClassType(GOLD)
            val paymentAmount = 3000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(90.0, invoice?.discount)
            assertEquals(3000.0, invoice?.subTotal)
            assertEquals(2910.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_GoldMemberPayment_Between3k_5k() {
        with(viewModel) {
            viewModel.updateMemberClassType(GOLD)
            val paymentAmount = 4000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(120.0, invoice?.discount)
            assertEquals(4000.0, invoice?.subTotal)
            assertEquals(3880.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_GoldMemberPayment_Between5k() {
        with(viewModel) {
            viewModel.updateMemberClassType(GOLD)
            val paymentAmount = 5000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(250.0, invoice?.discount)
            assertEquals(5000.0, invoice?.subTotal)
            assertEquals(4750.0, invoice?.total)
            assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_GoldMemberPayment_Between5k_10k() {
        with(viewModel) {
            viewModel.updateMemberClassType(GOLD)
            val paymentAmount = 8000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(400.0, invoice?.discount)
            assertEquals(8000.0, invoice?.subTotal)
            assertEquals(7600.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_GoldMemberPayment_10K() {
        with(viewModel) {
            viewModel.updateMemberClassType(GOLD)
            val paymentAmount = 10000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(1000.0, invoice?.discount)
            assertEquals(10000.0, invoice?.subTotal)
            assertEquals(9000.0, invoice?.total)
            assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_GoldMemberPayment_GreatThan_10k() {
        with(viewModel) {
            viewModel.updateMemberClassType(GOLD)
            val paymentAmount = 11000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(1100.0, invoice?.discount)
            assertEquals(11000.0, invoice?.subTotal)
            assertEquals(9900.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }


    @Test
    fun printInvoice_BlackMemberPayment_LessThan_3k() {
        with(viewModel) {
            viewModel.updateMemberClassType(BLACK)
            val paymentAmount = 1000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(0.0, invoice?.discount)
            assertEquals(1000.0, invoice?.subTotal)
            assertEquals(1000.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_BlackMemberPayment_3k() {
        with(viewModel) {
            viewModel.updateMemberClassType(BLACK)
            val paymentAmount = 3000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(150.0, invoice?.discount)
            assertEquals(3000.0, invoice?.subTotal)
            assertEquals(2850.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_BlackMemberPayment_Between3k_5k(){
        with(viewModel) {
            viewModel.updateMemberClassType(BLACK)
            val paymentAmount = 4000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(200.0, invoice?.discount)
            assertEquals(4000.0, invoice?.subTotal)
            assertEquals(3800.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_BlackMemberPayment_5k() {
        with(viewModel) {
            viewModel.updateMemberClassType(BLACK)
            val paymentAmount = 5000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(350.0, invoice?.discount ?: 0.0, 0.0001)
            assertEquals(5000.0, invoice?.subTotal)
            assertEquals(4650.0, invoice?.total)
            assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_BlackMemberPayment_Between5k_10k() {
        with(viewModel) {
            viewModel.updateMemberClassType(BLACK)
            val paymentAmount = 81000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(560.0, invoice?.discount)
            assertEquals(8000.0, invoice?.subTotal)
            assertEquals(7440.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_BlackMemberPayment_10k() {
        with(viewModel) {
            viewModel.updateMemberClassType(BLACK)
            val paymentAmount = 10000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(1500.0, invoice?.discount)
            assertEquals(10000.0, invoice?.subTotal)
            assertEquals(8500.0, invoice?.total)
            assertEquals(true, invoice?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_BlackMemberPayment_GreatThan_10k() {
        with(viewModel) {
            viewModel.updateMemberClassType(BLACK)
            val paymentAmount = 11000.0
            viewModel.subTotal.value = paymentAmount.toString()
            printInvoice()

            val invoice = invoice.value

            assertEquals(1650.0, invoice?.discount)
            assertEquals(11000.0, invoice?.subTotal)
            assertEquals(9350.0, invoice?.total)
            assertEquals(false, invoice?.giftAccepted)
        }
    }


}