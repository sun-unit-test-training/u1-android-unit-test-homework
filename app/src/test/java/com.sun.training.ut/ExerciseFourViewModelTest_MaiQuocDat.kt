package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.ui.exercise_four.ExerciseFourViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseFourViewModelTest_MaiQuocDat {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var exerciseFourViewModel: ExerciseFourViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        exerciseFourViewModel = ExerciseFourViewModel()
    }

    //1
    @Test
    fun calculateColor_Holiday_Sunday_NotSaturday_NotNormalDay_ReturnRed() {
        exerciseFourViewModel.onDateChanged(5, 2)
        exerciseFourViewModel.calculateColor()
        Assert.assertEquals(Constant.Color.RED, exerciseFourViewModel.colorLiveData.value)
    }

    //2
    @Test
    fun calculateColor_Holiday_NotSunday_Saturday_NotNormalDay_ReturnRed() {
        exerciseFourViewModel.onDateChanged(5, 1)
        exerciseFourViewModel.calculateColor()
        Assert.assertEquals(Constant.Color.RED, exerciseFourViewModel.colorLiveData.value)
    }

    //3
    @Test
    fun calculateColor_Holiday_NotSunday_NotSaturday_NormalDay_ReturnRed() {
        exerciseFourViewModel.onDateChanged(9, 2)
        exerciseFourViewModel.calculateColor()
        Assert.assertEquals(Constant.Color.RED, exerciseFourViewModel.colorLiveData.value)
    }

    //4
    @Test
    fun calculateColor_NotHoliday_Sunday_NotSaturday_NotNormalDay_ReturnRed() {
        exerciseFourViewModel.onDateChanged(12, 28)
        exerciseFourViewModel.calculateColor()
        Assert.assertEquals(Constant.Color.RED, exerciseFourViewModel.colorLiveData.value)
    }

    //5
    @Test
    fun calculateColor_NotHoliday_NotSunday_Saturday_NotNormalDay_ReturnBLUE() {
        exerciseFourViewModel.onDateChanged(12, 27)
        exerciseFourViewModel.calculateColor()
        Assert.assertEquals(Constant.Color.BLUE, exerciseFourViewModel.colorLiveData.value)
    }

    //6
    @Test
    fun calculateColor_NotHoliday_NotSunday_NotSaturday_NormalDay_ReturnBLACK() {
        exerciseFourViewModel.onDateChanged(12, 26)
        exerciseFourViewModel.calculateColor()
        Assert.assertEquals(Constant.Color.BLACK, exerciseFourViewModel.colorLiveData.value)
    }
}