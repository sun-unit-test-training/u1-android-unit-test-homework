package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.ui.exercise_two.ExerciseTwoViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseTwoViewModelTest_MaiQuocDat {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var excerciseTwoViewModel: ExerciseTwoViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        excerciseTwoViewModel = ExerciseTwoViewModel()
    }

    // - Not Vip:
    //  + normalDay, holiday
    //  + before, in, after
    // - Vip:
    //  + normalDay, holiday
    //  + before, in, after

    //1
    @Test
    fun validateFee_notVip_normalDay_beforeWorkTime_110() {
        excerciseTwoViewModel.onVipChecked(false)
        excerciseTwoViewModel.onDateChanged(23, 3)
        excerciseTwoViewModel.onTimeChanged(4, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, excerciseTwoViewModel.feeLiveData.value)
    }

    //2
    @Test
    fun validateFee_notVip_normalDay_workTime_0() {
        excerciseTwoViewModel.onVipChecked(false)
        excerciseTwoViewModel.onDateChanged(23, 3)
        excerciseTwoViewModel.onTimeChanged(17, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(0, excerciseTwoViewModel.feeLiveData.value)
    }

    //3
    @Test
    fun validateFee_notVip_normalDay_afterWorkTime_110() {
        excerciseTwoViewModel.onVipChecked(false)
        excerciseTwoViewModel.onDateChanged(23, 3)
        excerciseTwoViewModel.onTimeChanged(23, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, excerciseTwoViewModel.feeLiveData.value)
    }

    //4
    @Test
    fun validateFee_notVip_holiday_beforeWorkTime_110() {
        excerciseTwoViewModel.onVipChecked(false)
        excerciseTwoViewModel.onDateChanged(28, 3)
        excerciseTwoViewModel.onTimeChanged(4, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, excerciseTwoViewModel.feeLiveData.value)
    }

    //5
    @Test
    fun validateFee_notVip_holiday_workTime_110() {
        excerciseTwoViewModel.onVipChecked(false)
        excerciseTwoViewModel.onDateChanged(28, 3)
        excerciseTwoViewModel.onTimeChanged(17, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, excerciseTwoViewModel.feeLiveData.value)
    }

    //6
    @Test
    fun validateFee_notVip_holiday_afterWorkTime_110() {
        excerciseTwoViewModel.onVipChecked(false)
        excerciseTwoViewModel.onDateChanged(28, 3)
        excerciseTwoViewModel.onTimeChanged(23, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, excerciseTwoViewModel.feeLiveData.value)
    }

    //7
    @Test
    fun validateFee_Vip_normalDay_beforeWorkTime_0() {
        excerciseTwoViewModel.onVipChecked(true)
        excerciseTwoViewModel.onDateChanged(23, 3)
        excerciseTwoViewModel.onTimeChanged(4, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(0, excerciseTwoViewModel.feeLiveData.value)
    }

    //8
    @Test
    fun validateFee_Vip_normalDay_workTime_0() {
        excerciseTwoViewModel.onVipChecked(true)
        excerciseTwoViewModel.onDateChanged(23, 3)
        excerciseTwoViewModel.onTimeChanged(17, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(0, excerciseTwoViewModel.feeLiveData.value)
    }

    //9
    @Test
    fun validateFee_Vip_normalDay_afterWorkTime_0() {
        excerciseTwoViewModel.onVipChecked(true)
        excerciseTwoViewModel.onDateChanged(23, 3)
        excerciseTwoViewModel.onTimeChanged(23, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(0, excerciseTwoViewModel.feeLiveData.value)
    }

    //10
    @Test
    fun validateFee_Vip_holiday_beforeWorkTime_0() {
        excerciseTwoViewModel.onVipChecked(true)
        excerciseTwoViewModel.onDateChanged(28, 3)
        excerciseTwoViewModel.onTimeChanged(4, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(0, excerciseTwoViewModel.feeLiveData.value)
    }

    //11
    @Test
    fun validateFee_Vip_holiday_workTime_0() {
        excerciseTwoViewModel.onVipChecked(true)
        excerciseTwoViewModel.onDateChanged(28, 3)
        excerciseTwoViewModel.onTimeChanged(17, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(0, excerciseTwoViewModel.feeLiveData.value)
    }

    //12
    @Test
    fun validateFee_Vip_holiday_afterWorkTime_0() {
        excerciseTwoViewModel.onVipChecked(true)
        excerciseTwoViewModel.onDateChanged(28, 3)
        excerciseTwoViewModel.onTimeChanged(23, 0)
        excerciseTwoViewModel.calculateFee()
        Assert.assertEquals(0, excerciseTwoViewModel.feeLiveData.value)
    }
}