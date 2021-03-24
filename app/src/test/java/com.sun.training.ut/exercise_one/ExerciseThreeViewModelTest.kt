package com.sun.training.ut.exercise_one

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.ui.exercise_three.ExerciseThreeViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseThreeViewModelTest {
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
    No.	                        1	2	3   4   5
    7 mat hang	                T	T	T   F   F
    somi                    	F	T	T   F   T
    cavat	                    F	T	F   -   T
     **/

    @Test
    fun validate_discount_moreThan7Items_noShirt_noTie_returnDiscount7() {
        viewModel.numberOfItems = 8
        viewModel.onChangedShirt(false)
        viewModel.onChangedTie(false)
        viewModel.calculate()
        Assert.assertEquals(
            7,
            viewModel.discountLiveData.value
        )
    }

    @Test
    fun validate_discount_moreThan7Items_haveShirt_HaveTie_returnDiscount12() {
        viewModel.numberOfItems = 8
        viewModel.onChangedShirt(true)
        viewModel.onChangedTie(true)
        viewModel.calculate()
        Assert.assertEquals(
            12,
            viewModel.discountLiveData.value
        )
    }

    @Test
    fun validate_discount_moreThan7Items_haveShirt_noTie_returnDiscount7() {
        viewModel.numberOfItems = 8
        viewModel.onChangedShirt(true)
        viewModel.onChangedTie(false)
        viewModel.calculate()
        Assert.assertEquals(
            7,
            viewModel.discountLiveData.value
        )
    }

    @Test
    fun validate_discount_lower7Items_noShirt_tieAny_returnDiscount12() {
        viewModel.numberOfItems = 6
        viewModel.onChangedShirt(false)
        viewModel.calculate()
        Assert.assertEquals(
            0,
            viewModel.discountLiveData.value
        )
    }

    @Test
    fun validate_discount_lower7Items_haveShirt_haveTie_returnDiscount12() {
        viewModel.numberOfItems = 6
        viewModel.onChangedShirt(true)
        viewModel.onChangedTie(true)
        viewModel.calculate()
        Assert.assertEquals(
            5,
            viewModel.discountLiveData.value
        )
    }
}