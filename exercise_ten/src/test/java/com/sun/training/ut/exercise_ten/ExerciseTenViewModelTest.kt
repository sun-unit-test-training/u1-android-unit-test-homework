package com.sun.training.ut.exercise_ten

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.ui.ExerciseTenActivity
import com.sun.training.ut.exercise_ten.ui.ExerciseTenViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

//@RunWith(MockitoJUnitRunner::class)
@RunWith(RobolectricTestRunner::class)
class ExerciseTenViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTenViewModel

    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val activity = Robolectric.buildActivity(ExerciseTenActivity::class.java)
        context = activity.get().applicationContext
        viewModel = ExerciseTenViewModel(context.resources)
    }

    @Test
    fun validate_silverClass_below3000_discount0_noCoupon() {
        val subTotal = 1000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_silver))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(false, viewModel.giftAccepted(subTotal))
        assertEquals(0, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_silverClass_3000_discount1_noCoupon() {
        val subTotal = 3000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_silver))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(false, viewModel.giftAccepted(subTotal))
        assertEquals(1, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_silverClass_above3000_below5000_discount1_noCoupon() {
        val subTotal = 4000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_silver))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(false, viewModel.giftAccepted(subTotal))
        assertEquals(1, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_silverClass_5000_discount2_hasCoupon() {
        val subTotal = 5000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_silver))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(2, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_silverClass_above5000_below10000_discount2_hasCoupon() {
        val subTotal = 6000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_silver))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(2, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_silverClass_10000_discount4_hasCoupon() {
        val subTotal = 10000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_silver))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(4, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_silverClass_above10000_discount4_hasCoupon() {
        val subTotal = 11000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_silver))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(4, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_goldClass_below3000_discount0_noCoupon() {
        val subTotal = 1000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_gold))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(false, viewModel.giftAccepted(subTotal))
        assertEquals(0, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_goldClass_3000_discount3_noCoupon() {
        val subTotal = 3000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_gold))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(false, viewModel.giftAccepted(subTotal))
        assertEquals(3, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_goldClass_above3000_below5000_discount3_noCoupon() {
        val subTotal = 4000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_gold))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(false, viewModel.giftAccepted(subTotal))
        assertEquals(3, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_goldClass_5000_discount5_hasCoupon() {
        val subTotal = 5000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_gold))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(5, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_goldClass_above5000_below10000_discount5_hasCoupon() {
        val subTotal = 6000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_gold))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(5, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_goldClass_10000_discount10_hasCoupon() {
        val subTotal = 10000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_gold))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(10, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_goldClass_above10000_discount10_hasCoupon() {
        val subTotal = 11000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_gold))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(10, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_blackClass_below3000_discount0_noCoupon() {
        val subTotal = 1000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_black))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(false, viewModel.giftAccepted(subTotal))
        assertEquals(0, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_blackClass_3000_discount5_noCoupon() {
        val subTotal = 3000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_black))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(false, viewModel.giftAccepted(subTotal))
        assertEquals(5, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_blackClass_above3000_below5000_discount5_noCoupon() {
        val subTotal = 4000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_black))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(false, viewModel.giftAccepted(subTotal))
        assertEquals(5, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_blackClass_5000_discount7_hasCoupon() {
        val subTotal = 5000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_black))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(7, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_blackClass_above5000_below10000_discount7_hasCoupon() {
        val subTotal = 6000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_black))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(7, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_blackClass_10000_discount15_hasCoupon() {
        val subTotal = 10000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_black))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(15, viewModel.invoice.value?.discount)
    }

    @Test
    fun validate_blackClass_above10000_discount15_hasCoupon() {
        val subTotal = 11000.0
        viewModel.updateMemberClassType(context.getString(R.string.ex_10_class_type_black))
        viewModel.discountCalculation(subTotal)
        viewModel.printInvoice()
        assertEquals(true, viewModel.giftAccepted(subTotal))
        assertEquals(15, viewModel.invoice.value?.discount)
    }
}