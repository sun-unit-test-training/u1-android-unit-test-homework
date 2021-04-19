package com.sun.training.ut

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.exercise_ten.ui.ExerciseTenViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mock
import com.example.exercise_ten.R
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.jvm.Throws
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class ExerciseTenViewModelTest {
    companion object {
        const val NONE_MEMBER = "Không phải hội viên"
        const val GOLD_MEMBER = "Hạng Vàng"
        const val SLIVER_MEMBER = "Hạng Bạc"
        const val BLACK_MEMBER = "Hạng Đen"
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTenViewModel


    @Mock
    private lateinit var mockResources: Resources

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(mockResources.getString(R.string.ex_10_user_name_default)).thenReturn("Nguyen Hoa Phuong")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_gold)).thenReturn("Hạng Vàng")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_black)).thenReturn("Hạng Đen")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_silver)).thenReturn("Hạng Bạc")
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_unknown)).thenReturn("Không phải hội viên")
        viewModel = ExerciseTenViewModel(mockResources)
    }


    /** test discount  **/
    @Test
    fun discountCalculate_NoneMemberPayment_3K() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = 3000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_NoneMemberPayment_5K() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_NoneMemberPayment_10K() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = 10000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_GoldMemberPaymentLessThan_3K() {
        viewModel.updateMemberClassType(GOLD_MEMBER)
        val paymentAmount = 2900.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_GoldMemberPayment_3K() {
        viewModel.updateMemberClassType(GOLD_MEMBER)
        val paymentAmount = 3000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.03)
    }

    @Test
    fun discountCalculate_GoldMemberPayment_Between3K_5K() {
        viewModel.updateMemberClassType(GOLD_MEMBER)
        val paymentAmount = 3005.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.03)
    }

    @Test
    fun discountCalculate_GoldMemberPayment_5K() {
        viewModel.updateMemberClassType(GOLD_MEMBER)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }


    @Test
    fun discountCalculate_GoldMemberPayment_Between5K_10K() {
        viewModel.updateMemberClassType(GOLD_MEMBER)
        val paymentAmount = 5005.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }

    @Test
    fun discountCalculate_GoldMemberPayment_10K() {
        viewModel.updateMemberClassType(GOLD_MEMBER)
        val paymentAmount = 5005.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.10)
    }

    @Test
    fun discountCalculate_GoldMemberPayment_GreatThan10K() {
        viewModel.updateMemberClassType(GOLD_MEMBER)
        val paymentAmount = 10010.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.10)
    }

    @Test
    fun discountCalculate_SliverMemberPayment_LessThan3K() {
        viewModel.updateMemberClassType(SLIVER_MEMBER)
        val paymentAmount = 2900.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_SliverMemberPayment_3K() {
        viewModel.updateMemberClassType(SLIVER_MEMBER)
        val paymentAmount = 3000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.01)
    }

        @Test
    fun discountCalculate_SliverMemberPayment_Between3K_5K() {
        viewModel.updateMemberClassType(SLIVER_MEMBER)
        val paymentAmount = 3003.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.01)
    }

    @Test
    fun discountCalculate_SliverMemberPayment_5K() {
        viewModel.updateMemberClassType(SLIVER_MEMBER)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.02)
    }

    @Test
    fun discountCalculate_SliverMemberPayment_Between5K_10K() {
        viewModel.updateMemberClassType(SLIVER_MEMBER)
        val paymentAmount = 5005.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.02)
    }


    @Test
    fun discountCalculate_SliverMemberPayment_10K() {
        viewModel.updateMemberClassType(SLIVER_MEMBER)
        val paymentAmount = 10000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.04)
    }

    @Test
    fun discountCalculate_SliverMemberPayment_GrateThan10K() {
        viewModel.updateMemberClassType(SLIVER_MEMBER)
        val paymentAmount = 10010.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.04)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_LessThan_3K() {
        viewModel.updateMemberClassType(BLACK_MEMBER)
        val paymentAmount = 2900.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.0)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_3K() {
        viewModel.updateMemberClassType(BLACK_MEMBER)
        val paymentAmount = 3000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_Between3K_5K() {
        viewModel.updateMemberClassType(BLACK_MEMBER)
        val paymentAmount = 3003.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.05)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_5K() {
        viewModel.updateMemberClassType(BLACK_MEMBER)
        val paymentAmount = 5000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.07)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_Between5K_10K() {
        viewModel.updateMemberClassType(BLACK_MEMBER)
        val paymentAmount = 5005.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.07)
    }



    @Test
    fun discountCalculate_BlackMemberPayment_10K() {
        viewModel.updateMemberClassType(BLACK_MEMBER)
        val paymentAmount = 10000.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.15)
    }

    @Test
    fun discountCalculate_BlackMemberPayment_GreatThan_10K() {
        viewModel.updateMemberClassType(BLACK_MEMBER)
        val paymentAmount = 10010.0
        viewModel.subTotal.value = paymentAmount.toString()

        assertEquals(viewModel.discountCalculation(paymentAmount), paymentAmount * 0.15)
    }

    /** Test gift checking **/
    @Test
    fun giftChecking_withNoneMember_andPaymentEquals5K_willReturnTrue() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = 5000.0
        assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun giftChecking_withNoneMember_andPaymentEquals10K_willReturnTrue() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = 10000.0

        assertEquals(viewModel.giftAccepted(paymentAmount), true)
    }

    @Test
    fun giftChecking_withNoneMember_andPaymentNotEquals5KOr10K_willReturnFalse() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val paymentAmount = Random.nextDouble(Double.MAX_VALUE)
        val accept = paymentAmount == 5000.0 || paymentAmount == 10000.0

        assertEquals(viewModel.giftAccepted(paymentAmount), accept)
    }

    /*** Test check Invoice **/
    @Test
    fun printInvoice_SliverMemberPayment_LessThan3K() {
        with(viewModel) {
            viewModel.updateMemberClassType(SLIVER_MEMBER)
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
            viewModel.updateMemberClassType(SLIVER_MEMBER)
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
            viewModel.updateMemberClassType(SLIVER_MEMBER)
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
            viewModel.updateMemberClassType(SLIVER_MEMBER)
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
            viewModel.updateMemberClassType(SLIVER_MEMBER)
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
            viewModel.updateMemberClassType(SLIVER_MEMBER)
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
            viewModel.updateMemberClassType(SLIVER_MEMBER)
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
            viewModel.updateMemberClassType(GOLD_MEMBER)
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
            viewModel.updateMemberClassType(GOLD_MEMBER)
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
            viewModel.updateMemberClassType(GOLD_MEMBER)
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
            viewModel.updateMemberClassType(GOLD_MEMBER)
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
            viewModel.updateMemberClassType(GOLD_MEMBER)
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
            viewModel.updateMemberClassType(GOLD_MEMBER)
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
            viewModel.updateMemberClassType(GOLD_MEMBER)
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
            viewModel.updateMemberClassType(BLACK_MEMBER)
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
            viewModel.updateMemberClassType(BLACK_MEMBER)
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
            viewModel.updateMemberClassType(BLACK_MEMBER)
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
            viewModel.updateMemberClassType(BLACK_MEMBER)
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
            viewModel.updateMemberClassType(BLACK_MEMBER)
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
            viewModel.updateMemberClassType(BLACK_MEMBER)
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
            viewModel.updateMemberClassType(BLACK_MEMBER)
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
