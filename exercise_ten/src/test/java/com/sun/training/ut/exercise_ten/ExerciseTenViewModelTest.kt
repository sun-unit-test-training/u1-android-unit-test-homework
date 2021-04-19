package com.sun.training.ut.exercise_ten

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.quanghoa.apps.lequanghoaunittestexam.model.DiscountPercent
import com.quanghoa.apps.lequanghoaunittestexam.model.PaymentAmount
import com.quanghoa.apps.lequanghoaunittestexam.model.UserClassType
import com.sun.training.ut.exercise_ten.ui.ExerciseTenViewModel
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ExerciseTenViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTenViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseTenViewModel()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun calculateDiscount_withUserIsUnknownClassType_andAnyTotalPayment() {
        val payment = 4999.0
        val expectDiscount = 0.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.UNKNOWN.name)

        val discount = viewModel.discountCalculation()

        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsBlackClassType_andTolTalPaymentBellow3K() {
        val payment = 2999.0
        val expectDiscount = 0.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.BLACK.name)

        val discount = viewModel.discountCalculation()

        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsBlackClassType_andTolTalPaymentEqual3K() {
        val payment = 3000.0
        val expectDiscount = 150.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.BLACK.name)

        val discount = viewModel.discountCalculation()

        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsBlackClassType_andTolTalPaymentFrom3K() {
        val payment = 3001.0
        val expectDiscount = payment * DiscountPercent.BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.BLACK.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsBlackClassType_andTolTalPaymentEqual5K() {
        val payment = 5000.0
        val expectDiscount = 350.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.BLACK.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0001)
    }

    @Test
    fun calculateDiscount_withUserIsBlackClassType_andTolTalPaymentFrom5K() {
        val payment = 5001.0
        val expectDiscount = payment * DiscountPercent.BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.BLACK.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsBlackClassType_andTolTalPaymentEqual10K() {
        val payment = 10000.0
        val expectDiscount = 1500.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.BLACK.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsBlackClassType_andTolTalPaymentFrom10K() {
        val payment = 10001.0
        val expectDiscount = payment * DiscountPercent.BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.BLACK.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }


    @Test
    fun calculateDiscount_withUserIsGoldClassType_andTolTalPaymentBellow3K() {
        val payment = 2999.0
        val expectDiscount = 0.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.GOLD.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsGoldClassType_andTolTalPaymentEqual3K() {
        val payment = 3000.0
        val expectDiscount = 90.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.GOLD.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsGoldClassType_andTolTalPaymentFrom3K() {
        val payment = 3001.0
        val expectDiscount = payment * DiscountPercent.GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.GOLD.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsGoldClassType_andTolTalPaymentEqual5K() {
        val payment = 5000.0
        val expectDiscount = 250.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.GOLD.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsGoldClassType_andTolTalPaymentFrom5K() {
        val payment = 5005.0
        val expectDiscount = payment * DiscountPercent.GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.GOLD.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsGoldClassType_andTolTalPaymentEqual10K() {
        val payment = 10000.0
        val expectDiscount = 1000.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.GOLD.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsGoldClassType_andTolTalPaymentFrom10K() {
        val payment = 10001.0
        val expectDiscount = payment * DiscountPercent.GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.GOLD.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsSilverClassType_andTolTalPaymentBellow3K() {
        val subTotal = 2999.0
        val expectDiscount = 0.0
        viewModel.setTotalPayment(subTotal)
        viewModel.updateUserClassType(UserClassType.SILVER.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsSilverClassType_andTolTalPaymentEqual3K() {
        val payment = 3000.0
        val expectDiscount = 30.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.SILVER.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsSilverClassType_andTolTalPaymentFrom3K() {
        val payment = 3001.0
        val expectDiscount = payment * DiscountPercent.SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.SILVER.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsSilverClassType_andTolTalPaymentEqual5K() {
        val payment = 5000.0
        val expectDiscount = 100.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.SILVER.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }


    @Test
    fun calculateDiscount_withUserIsSilverClassType_andTolTalPaymentFrom5K() {
        val payment = 5001.0
        val expectDiscount = payment * DiscountPercent.SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.SILVER.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsSilverClassType_andTolTalPaymentEqual10K() {
        val payment = 10000.0
        val expectDiscount = 400.0
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.SILVER.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun calculateDiscount_withUserIsSilverClassType_andTolTalPayment10K() {
        val payment = 10001.0
        val expectDiscount = payment * DiscountPercent.SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT
        viewModel.setTotalPayment(payment)
        viewModel.updateUserClassType(UserClassType.SILVER.name)
        val discount = viewModel.discountCalculation()
        Assert.assertEquals(expectDiscount, discount, 0.0)
    }

    @Test
    fun giftAccepted_withTotalPayment_is3000k_return_false(){
        Assert.assertFalse(viewModel.giftAccepted(PaymentAmount.PAYMENT_3K))
    }

    @Test
    fun giftAccepted_withTotalPayment_is5000k_return_true(){
        Assert.assertTrue(viewModel.giftAccepted(PaymentAmount.PAYMENT_5K))
    }

    @Test
    fun giftAccepted_withTotalPayment_is10000k_return_true(){
        Assert.assertTrue(viewModel.giftAccepted(PaymentAmount.PAYMENT_10K))
    }

    @Test
    fun updateUserClassType_isBlackClassType_equalUserClassType_isBlack(){
        viewModel.updateUserClassType(UserClassType.BLACK.name)
        Assert.assertEquals(UserClassType.BLACK, viewModel.user.value?.classType)
    }

    @Test
    fun updateUserClassType_isGoldClassType_equalUserClassType_isGold(){
        viewModel.updateUserClassType(UserClassType.GOLD.name)
        Assert.assertEquals(UserClassType.GOLD, viewModel.user.value?.classType)
    }

    @Test
    fun updateUserClassType_isSilverClassType_equalUserClassType_isSilver(){
        viewModel.updateUserClassType(UserClassType.SILVER.name)
        Assert.assertEquals(UserClassType.SILVER, viewModel.user.value?.classType)
    }
}