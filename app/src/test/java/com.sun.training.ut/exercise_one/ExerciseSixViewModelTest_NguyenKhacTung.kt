package com.sun.training.ut.exercise_one

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.ui.excercise_six.ExerciseSixViewModel
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
class ExerciseSixViewModelTest_NguyenKhacTung {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseSixViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseSixViewModel()
    }

    @Test
    fun validateMoney_with2000_return60() {
        viewModel.totalPurchased = 2000
        viewModel.onWatchMovieChecked(false)
        viewModel.calculateMinute()
        Assert.assertEquals(60, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun validateMoney_with5000_return120() {
        viewModel.totalPurchased = 5000
        viewModel.onWatchMovieChecked(false)
        viewModel.calculateMinute()
        Assert.assertEquals(120, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun validateMoney_with2000_withWatch_return240() {
        viewModel.totalPurchased = 2000
        viewModel.onWatchMovieChecked(true)
        viewModel.calculateMinute()
        Assert.assertEquals(240, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun validateMoney_with5000_withWatch_return300() {
        viewModel.totalPurchased = 5000
        viewModel.onWatchMovieChecked(true)
        viewModel.calculateMinute()
        Assert.assertEquals(300, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun validateMoney_return0() {
        viewModel.totalPurchased = 1000
        viewModel.onWatchMovieChecked(false)
        viewModel.calculateMinute()
        Assert.assertEquals(0, viewModel.freeParkingInMinute.value)
    }
}
