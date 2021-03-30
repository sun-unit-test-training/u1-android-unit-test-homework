package com.sun.training.ut.exercise_one

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
import kotlin.jvm.Throws

@RunWith(MockitoJUnitRunner::class)
class ExerciseFourViewModelTest_NguyenKhacTung {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseFourViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseFourViewModel()
    }

    @Test
    fun validateColor_withHoliday_returnRed() {
        viewModel.onDateChanged(0, 1)
        viewModel.calculateColor()
        Assert.assertEquals(Constant.Color.RED.name, viewModel.colorLiveData.value)
    }

    @Test
    fun validateColor_withSunday_returnRed() {
        viewModel.onDateChanged(11, 27)
        viewModel.calculateColor()
        Assert.assertEquals(Constant.Color.RED.name, viewModel.colorLiveData.value)
    }

    @Test
    fun validateColor_withSaturday_returnBlue() {
        viewModel.onDateChanged(11, 26)
        viewModel.calculateColor()
        Assert.assertEquals(Constant.Color.BLUE.name, viewModel.colorLiveData.value)
    }

    @Test
    fun validateColor_withNormalDate_returnBlack() {
        viewModel.onDateChanged(11, 25)
        viewModel.calculateColor()
        Assert.assertEquals(Constant.Color.BLACK.name, viewModel.colorLiveData.value)
    }
}
