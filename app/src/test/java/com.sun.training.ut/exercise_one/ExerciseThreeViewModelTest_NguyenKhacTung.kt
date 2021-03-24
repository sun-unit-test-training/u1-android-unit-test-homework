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
import kotlin.jvm.Throws

@RunWith(MockitoJUnitRunner::class)
class ExerciseThreeViewModelTest_NguyenKhacTung {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseThreeViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseThreeViewModel()
    }

    @Test
    fun validateDiscount_withNumber_return7() {
        viewModel.numberOfItems = 8
        viewModel.onChangedShirt(false)
        viewModel.onChangedTie(false)
        viewModel.calculate()
        Assert.assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_withNumber_withShirt_withTie_return12() {
        viewModel.numberOfItems = 8
        viewModel.onChangedShirt(true)
        viewModel.onChangedTie(true)
        viewModel.calculate()
        Assert.assertEquals(Constant.DISCOUNT_12, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_withNumber_withShirt_return7() {
        viewModel.numberOfItems = 8
        viewModel.onChangedShirt(true)
        viewModel.onChangedTie(false)
        viewModel.calculate()
        Assert.assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_withNumber_withTie_return7() {
        viewModel.numberOfItems = 8
        viewModel.onChangedShirt(false)
        viewModel.onChangedTie(true)
        viewModel.calculate()
        Assert.assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_withTie_return0() {
        viewModel.numberOfItems = 5
        viewModel.onChangedShirt(false)
        viewModel.onChangedTie(true)
        viewModel.calculate()
        Assert.assertEquals(0, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_withShirt_return0() {
        viewModel.numberOfItems = 5
        viewModel.onChangedShirt(true)
        viewModel.onChangedTie(false)
        viewModel.calculate()
        Assert.assertEquals(0, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_withShirt_withTie_return5() {
        viewModel.numberOfItems = 5
        viewModel.onChangedShirt(true)
        viewModel.onChangedTie(true)
        viewModel.calculate()
        Assert.assertEquals(Constant.DISCOUNT_5, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_return0() {
        viewModel.numberOfItems = 5
        viewModel.onChangedShirt(false)
        viewModel.onChangedTie(false)
        viewModel.calculate()
        Assert.assertEquals(0, viewModel.discountLiveData.value)
    }
}
