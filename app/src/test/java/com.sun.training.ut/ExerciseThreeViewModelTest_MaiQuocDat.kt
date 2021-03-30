package com.sun.training.ut

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
class ExerciseThreeViewModelTest_MaiQuocDat {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var exerciseThreeViewModel: ExerciseThreeViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        exerciseThreeViewModel = ExerciseThreeViewModel()
    }

    //1
    @Test
    fun calculateDiscount_SevenItems_NotShirt_NotTie_returnDiscount7() {
        exerciseThreeViewModel.numberOfItems = Constant.DEFAULT_ITEM_HAVE_DISCOUNT
        exerciseThreeViewModel.onChangedShirt(false)
        exerciseThreeViewModel.onChangedTie(false)
        exerciseThreeViewModel.calculate()
        Assert.assertEquals(Constant.DISCOUNT_7, exerciseThreeViewModel.discountLiveData.value)
    }

    //2
    @Test
    fun calculateDiscount_SevenItems_HaveShirt_NotTie_returnDiscount7() {
        exerciseThreeViewModel.numberOfItems = Constant.DEFAULT_ITEM_HAVE_DISCOUNT
        exerciseThreeViewModel.onChangedShirt(true)
        exerciseThreeViewModel.onChangedTie(false)
        exerciseThreeViewModel.calculate()
        Assert.assertEquals(Constant.DISCOUNT_7, exerciseThreeViewModel.discountLiveData.value)
    }

    //3
    @Test
    fun calculateDiscount_SevenItems_HaveShirt_HaveTie_returnDiscount12() {
        exerciseThreeViewModel.numberOfItems = Constant.DEFAULT_ITEM_HAVE_DISCOUNT
        exerciseThreeViewModel.onChangedShirt(true)
        exerciseThreeViewModel.onChangedTie(true)
        exerciseThreeViewModel.calculate()
        Assert.assertEquals(Constant.DISCOUNT_12, exerciseThreeViewModel.discountLiveData.value)
    }

    //4
    @Test
    fun calculateDiscount_NotSevenItems_NotShirt_NotTie_returnDiscount0() {
        exerciseThreeViewModel.numberOfItems = 6
        exerciseThreeViewModel.onChangedShirt(false)
        exerciseThreeViewModel.onChangedTie(false)
        exerciseThreeViewModel.calculate()
        Assert.assertEquals(0, exerciseThreeViewModel.discountLiveData.value)
    }

    //5
    @Test
    fun calculateDiscount_NotSevenItems_HaveShirt_NotTie_returnDiscount0() {
        exerciseThreeViewModel.numberOfItems = 6
        exerciseThreeViewModel.onChangedShirt(true)
        exerciseThreeViewModel.onChangedTie(false)
        exerciseThreeViewModel.calculate()
        Assert.assertEquals(0, exerciseThreeViewModel.discountLiveData.value)
    }

    //6
    @Test
    fun calculateDiscount_NotSevenItems_HaveShirt_HaveTie_returnDiscount5() {
        exerciseThreeViewModel.numberOfItems = 6
        exerciseThreeViewModel.onChangedShirt(true)
        exerciseThreeViewModel.onChangedTie(true)
        exerciseThreeViewModel.calculate()
        Assert.assertEquals(Constant.DISCOUNT_5, exerciseThreeViewModel.discountLiveData.value)
    }
}