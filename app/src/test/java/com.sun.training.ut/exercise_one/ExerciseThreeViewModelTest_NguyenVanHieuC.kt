package com.sun.training.ut.exercise_one

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.ui.exercise_three.ExerciseThreeViewModel
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseThreeViewModelTest_NguyenVanHieuC {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseThreeViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseThreeViewModel()
    }

    /**
    No.	                   1	    2   	3         4         5       6       7       8
    Áo sơ mi trắng          F       T       F         T         T       F       T       F
    Cà vat                  F       F       T         T         F       T       T       F
    Mua từ 7 mặt hàng	    T       T       T         T         F       F       F       F
    Sale 0%	                                                    T       T               T
    Sale 5%                                                                     T
    Sale 7%                 T       T        T
    Sale 12%                                           T
     **/

    @Test
    fun validateDiscount_with_sevenItem() {
        viewModel.numberOfItems = 7
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_with_sevenItem_haveShirt() {
        viewModel.numberOfItems = 7
        viewModel.onChangedShirt(true)
        viewModel.calculate()
        Assert.assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_with_sevenItem_haveTie() {
        viewModel.numberOfItems = 7
        viewModel.onChangedTie(true)
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_with_sevenItem_haveTie_haveShirt() {
        viewModel.numberOfItems = 7
        viewModel.onChangedShirt(true)
        viewModel.onChangedTie(true)
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_12, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_with_onlyShirt() {
        viewModel.onChangedShirt(true)
        viewModel.calculate()
        assertEquals(0, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_with_onlyTie() {
        viewModel.onChangedTie(true)
        viewModel.calculate()
        assertEquals(0, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_with_haveShirt_haveTie() {
        viewModel.onChangedTie(true)
        viewModel.onChangedShirt(true)
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_5, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_with_buyNothing() {
        viewModel.calculate()
        assertEquals(0, viewModel.discountLiveData.value)
    }
}