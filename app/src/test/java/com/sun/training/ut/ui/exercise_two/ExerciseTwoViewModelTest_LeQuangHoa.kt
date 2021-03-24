package com.sun.training.ut.ui.exercise_two

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.model.Customer
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertEquals
import org.junit.Test

@RunWith(MockitoJUnitRunner::class)
class ExerciseTwoViewModelTestLeQuangHoa {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTwoViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseTwoViewModel()
    }

    /**
    Conditions: Vip and days of week
     */
    @Test
    fun validatePrice_vip_dayOfWeek_inTime_return0() {
        val input = Customer(hour = 8, minute = 45, isVip = true, dayOfMonth = 1, monthOfYear = 3)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_dayOfWeek_timeOutLower_return0() {
        val input = Customer(hour = 8, minute = 15, isVip = true, dayOfMonth = 1, monthOfYear = 3)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_dayOfWeek_timeOutHigher_return0() {
        val input = Customer(hour = 18, minute = 0, isVip = true, dayOfMonth = 1, monthOfYear = 3)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    /**
    Conditions: Vip and holiday
     */
    @Test
    fun validatePrice_vip_holiday_inTime_return0() {
        val input = Customer(hour = 8, minute = 45, isVip = true, dayOfMonth = 1, monthOfYear = 5)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_holiday_timeOutLower_return0() {
        val input = Customer(hour = 8, minute = 15, isVip = true, dayOfMonth = 1, monthOfYear = 5)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_holiday_timeOutHigher_return0() {
        val input = Customer(hour = 18, minute = 0, isVip = true, dayOfMonth = 1, monthOfYear = 5)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    /**
    Conditions: no Vip and days of week
     */
    @Test
    fun validatePrice_noVip_dayOfWeek_inTime_return0() {
        val input = Customer(hour = 8, minute = 45, isVip = false, dayOfMonth = 1, monthOfYear = 3)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_noVip_dayOfWeek_timeOutLower_return110() {
        val input = Customer(hour = 8, minute = 44, isVip = false, dayOfMonth = 1, monthOfYear = 3)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(110, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_noVip_dayOfWeek_timeOutHigher_return110() {
        val input = Customer(hour = 18, minute = 0, isVip = false, dayOfMonth = 1, monthOfYear = 3)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(110, viewModel.feeLiveData.value)
    }

    /**
    Conditions: no Vip and holiday
     */
    @Test
    fun validatePrice_noVip_holiday_inTime_return110() {
        val input = Customer(hour = 8, minute = 45, isVip = false, dayOfMonth = 1, monthOfYear = 5)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(110, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_noVip_holiday_timeOutLower_return110() {
        val input = Customer(hour = 8, minute = 44, isVip = false, dayOfMonth = 1, monthOfYear = 5)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(110, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_noVip_holiday_timeOutHigher_return110() {
        val input = Customer(hour = 18, minute = 0, isVip = false, dayOfMonth = 1, monthOfYear = 5)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(110, viewModel.feeLiveData.value)
    }

}
