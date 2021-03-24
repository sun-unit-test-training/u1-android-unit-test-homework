package com.sun.training.ut.exercise_one

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.ui.exercise_two.ExerciseTwoViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.jvm.Throws

@RunWith(MockitoJUnitRunner::class)
class ExcerciseTwoViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTwoViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseTwoViewModel()
    }

    @Test
    fun validateVip_zeroFee() {
        viewModel.onVipChecked(true)
        viewModel.onDateChanged(2, 9)
        viewModel.calculateFee()
        assertEquals(viewModel.feeLiveData.value, 0)
    }

    @Test
    fun validate_weekend_noVip_zeroFee() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(1, 1)
        viewModel.calculateFee()
        assertEquals(viewModel.feeLiveData.value, 110)
    }

    @Test
    fun validate_normalDay_00AM_110Fee() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2, 1)
        viewModel.onTimeChanged(0, 0)
        viewModel.calculateFee()
        assertEquals(viewModel.feeLiveData.value, 110)
    }

    @Test
    fun validate_normalDay_between_00AM_0845AM_110Fee() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2, 1)
        viewModel.onTimeChanged(1, 3)
        viewModel.calculateFee()
        assertEquals(viewModel.feeLiveData.value, 110)
    }

    fun validate_normalDay_0844AM_110Fee() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2, 1)
        viewModel.onTimeChanged(8, 44)
        viewModel.calculateFee()
        assertEquals(viewModel.feeLiveData.value, 110)
    }

    @Test
    fun validate_normalDay_0845AM_zeroFee() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2, 1)
        viewModel.onTimeChanged(8, 45)
        viewModel.calculateFee()
        assertEquals(viewModel.feeLiveData.value, 0)
    }

    @Test
    fun validate_normalDay_between_0845AM_1759PM_zeroFee() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2, 1)
        viewModel.onTimeChanged(10, 62)
        viewModel.calculateFee()
        assertEquals(viewModel.feeLiveData.value, 0)
    }

    @Test
    fun validate_normalDay_1759PM_zeroFee() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2, 1)
        viewModel.onTimeChanged(17, 59)
        viewModel.calculateFee()
        assertEquals(viewModel.feeLiveData.value, 0)
    }

    @Test
    fun validate_normalDay_1800PM_110Fee() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2, 1)
        viewModel.onTimeChanged(18, 0)
        viewModel.calculateFee()
        assertEquals(viewModel.feeLiveData.value, 110)
    }

    @Test
    fun validate_normalDay_betwwen_1800PM_2359PM_110Fee() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2, 1)
        viewModel.onTimeChanged(20, 30)
        viewModel.calculateFee()
        assertEquals(viewModel.feeLiveData.value, 110)
    }

    @Test
    fun validate_normalDay2359PM_110Fee() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2, 1)
        viewModel.onTimeChanged(23, 59)
        viewModel.calculateFee()
        assertEquals(viewModel.feeLiveData.value, 110)
    }
}