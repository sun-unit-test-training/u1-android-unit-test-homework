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
    fun silverType_paymentBellow3K_have_discount_0() {
        viewModel.updateMemberClassType(SILVER)
        val payment = 2900.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.0)
    }

    @Test
    fun silverType_paymentEquals3K_have_discount_1() {
        viewModel.updateMemberClassType(SILVER)
        val payment = PaymentAmountPointBusiness.PAYMENT_3K
        viewModel.subTotal.value = PaymentAmountPointBusiness.PAYMENT_3K.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.01)
    }

    @Test
    fun silverType_paymentFrom3KTo5K_have_discount_1() {
        viewModel.updateMemberClassType(SILVER)
        val payment = 4000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.01)

    }

    @Test
    fun silverType_paymentEquals5K_have_discount_discount_2() {
        viewModel.updateMemberClassType(SILVER)
        val payment = 5000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.02)
    }

    @Test
    fun silverType_paymentFrom5KTo10K_have_discount_2() {
        viewModel.updateMemberClassType(SILVER)
        val payment = 6000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.02)
    }

    @Test
    fun silverType_paymentEquals10K_have_discount_4() {
        viewModel.updateMemberClassType(SILVER)
        val payment = 10000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.04)
    }

    @Test
    fun silverType_paymentAbove10K_have_discount_4() {
        viewModel.updateMemberClassType(SILVER)
        val payment = 11000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.04)
    }

    @Test
    fun goldType_paymentBellow3K_have_discount_0() {
        viewModel.updateMemberClassType(GOLD)
        val payment = 2000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.0)
    }

    @Test
    fun goldType_paymentEquals3K_have_discount_3() {
        viewModel.updateMemberClassType(GOLD)
        val payment = 3000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.03)
    }

    @Test
    fun goldType_paymentFrom3KTo5K_have_discount_3() {
        viewModel.updateMemberClassType(GOLD)
        val payment = 4000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.03)
    }

    @Test
    fun goldType_paymentEquals5K_have_discount_5() {
        viewModel.updateMemberClassType(GOLD)
        val payment = 5000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.05)
    }

    @Test
    fun goldType_paymentFrom5KTo10K_have_discount_5() {
        viewModel.updateMemberClassType(GOLD)
        val payment = 6000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.05)
    }

    @Test
    fun goldType_paymentEquals10K_have_discount_10() {
        viewModel.updateMemberClassType(GOLD)
        val payment = 10000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.10)
    }

    @Test
    fun goldType_paymentAbove10K_have_discount_10() {
        viewModel.updateMemberClassType(GOLD)
        val payment = 11000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.10)
    }

    @Test
    fun blackType_paymentBellow3K_have_discount_0() {
        viewModel.updateMemberClassType(BLACK)
        val payment = 2900.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.0)
    }

    @Test
    fun blackType_paymentEquals3K_have_discount_5() {
        viewModel.updateMemberClassType(BLACK)
        val payment = 3000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.05)
    }

    @Test
    fun blackType_paymentFrom3KTo5K_have_discount_5() {
        viewModel.updateMemberClassType(BLACK)
        val payment = Random.nextDouble(3001.0, 5000.0)
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.05)
    }

    @Test
    fun blackType_paymentEquals5K_have_discount_7() {
        viewModel.updateMemberClassType(BLACK)
        val payment = 5000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.07)
    }

    @Test
    fun blackType_paymentFrom5KTo10K_have_discount_7() {
        viewModel.updateMemberClassType(BLACK)
        val payment = 5001.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.07)
    }

    @Test
    fun blackType_paymentEquals10K_have_discount_15() {
        viewModel.updateMemberClassType(BLACK)
        val payment = 10000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.15)
    }

    @Test
    fun blackType_paymentAbove10K_have_discount_15() {
        viewModel.updateMemberClassType(BLACK)
        val payment = 10001.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.15)
    }

    @Test
    fun noneType_paymentEquals3K_have_discount_0() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val payment = 3000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.0)
    }

    @Test
    fun noneType_paymentEquals5K_have_discount_0() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val payment = 5000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.0)
    }

    @Test
    fun noneType_paymentEquals10K_have_discount_0() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val payment = 10000.0
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.0)
    }

    @Test
    fun noneType_paymentAny_have_discount_0() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val payment = Random.nextDouble()
        viewModel.subTotal.value = payment.toString()
        TestCase.assertEquals(viewModel.discountCalculation(payment), payment * 0.0)
    }

    @Test
    fun gift_paymentsNotEquals5KOr10K_returnFalse() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val payment = 3000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), false)
    }

    @Test
    fun gift_paymentEquals5K_returnTrue() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val payment = 5000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), true)
    }

    @Test
    fun gift_paymentEquals10K_returnTrue() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val payment = 10000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), true)
    }

    @Test
    fun gift_SilverType_payments_NotEquals5KOr10K_returnFalse() {
        viewModel.updateMemberClassType(SILVER)
        val payment = 3000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), false)
    }

    @Test
    fun gift_SilverType_paymentEquals5K_returnTrue() {
        viewModel.updateMemberClassType(SILVER)
        val payment = 5000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), true)
    }

    @Test
    fun gift_SilverType_paymentEquals10K_returnTrue() {
        viewModel.updateMemberClassType(SILVER)
        val payment = 10000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), true)
    }

    @Test
    fun gift_GoldType_payments_NotEquals5KOr10K_returnFalse() {
        viewModel.updateMemberClassType(GOLD)
        val payment = 3000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), false)
    }

    @Test
    fun gift_GoldType_paymentEquals5K_returnTrue() {
        viewModel.updateMemberClassType(GOLD)
        val payment = 5000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), true)
    }

    @Test
    fun gift_GoldType_paymentEquals10K_returnTrue() {
        viewModel.updateMemberClassType(GOLD)
        val payment = 10000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), true)
    }

    @Test
    fun gift_BlackType_paymentsBellow5K_returnTrue() {
        viewModel.updateMemberClassType(BLACK)
        val payment = 3000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), true)
    }

    @Test
    fun gift_BlackType_paymentEquals5K_returnTrue() {
        viewModel.updateMemberClassType(BLACK)
        val payment = 5000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), true)
    }

    @Test
    fun gift_BlackType_paymentEquals10K_returnTrue() {
        viewModel.updateMemberClassType(BLACK)
        val payment = 10000.0
        TestCase.assertEquals(viewModel.giftAccepted(payment), true)
    }

    @Test
    fun checkInvoice_SilverType_paymentAny() {
        viewModel.updateMemberClassType(SILVER)
        val payment = Random.nextDouble(Double.MAX_VALUE)
        viewModel.subTotal.value = payment.toString()

        val currentDiscount = viewModel.discountCalculation(payment)
        val currentGiftAccepted = viewModel.giftAccepted(payment)

        viewModel.printInvoice()
        viewModel.invoice.value?.run {
            TestCase.assertEquals(subTotal, payment)
            TestCase.assertEquals(discount, currentDiscount)
            TestCase.assertEquals(total, payment - currentDiscount)
            TestCase.assertEquals(giftAccepted, currentGiftAccepted)
        }
    }

    @Test
    fun checkInvoice_GoldType_paymentAny() {
        viewModel.updateMemberClassType(GOLD)
        val payment = Random.nextDouble(Double.MAX_VALUE)
        viewModel.subTotal.value = payment.toString()
        val currentDiscount = viewModel.discountCalculation(payment)
        val currentGiftAccepted = viewModel.giftAccepted(payment)

        viewModel.printInvoice()
        viewModel.invoice.value?.run {
            TestCase.assertEquals(subTotal, payment)
            TestCase.assertEquals(discount, currentDiscount)
            TestCase.assertEquals(total, payment - currentDiscount)
            TestCase.assertEquals(giftAccepted, currentGiftAccepted)
        }
    }

    @Test
    fun checkInvoice_BlackType_paymentAny() {
        viewModel.updateMemberClassType(BLACK)
        val payment = Random.nextDouble(Double.MAX_VALUE)
        viewModel.subTotal.value = payment.toString()
        val currentDiscount = viewModel.discountCalculation(payment)
        val currentGiftAccepted = viewModel.giftAccepted(payment)

        viewModel.printInvoice()
        viewModel.invoice.value?.run {
            TestCase.assertEquals(subTotal, payment)
            TestCase.assertEquals(discount, currentDiscount)
            TestCase.assertEquals(total, payment - currentDiscount)
            TestCase.assertEquals(giftAccepted, currentGiftAccepted)
        }
    }

    @Test
    fun checkInvoice_NoneType_paymentAny() {
        viewModel.updateMemberClassType(NONE_MEMBER)
        val payment = Random.nextDouble(Double.MAX_VALUE)
        viewModel.subTotal.value = payment.toString()
        val currentDiscount = viewModel.discountCalculation(payment)
        val currentGiftAccepted = viewModel.giftAccepted(payment)
        viewModel.printInvoice()
        viewModel.invoice.value?.run {
            TestCase.assertEquals(subTotal, payment)
            TestCase.assertEquals(discount, currentDiscount)
            TestCase.assertEquals(total, payment - currentDiscount)
            TestCase.assertEquals(giftAccepted, currentGiftAccepted)
        }
    }

}