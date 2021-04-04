package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.ui.home.HomeViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest_datbt {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var homeViewModel: HomeViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        homeViewModel = HomeViewModel()
    }

    //    Hạng bạc	Bonus
//    Số tiền thanh toán 3,000円/ yên	Giảm 1%
//    Số tiền thanh toán 5,000円/yên	Giảm 2%
//    Số tiền thanh toán 10,000円/yên	Giảm 4%
    @Test
    fun buyBonusSilver10001() {
        val test = homeViewModel.buy(price = 10001.0, level = HomeViewModel.Level.silver)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_4)
    }
    @Test
    fun buyBonusSilver10000() {
        val test =
            homeViewModel.buy(price = 10000.0, level = HomeViewModel.Level.silver)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_4)
    }
    @Test
    fun buyBonusSilver9999() {
        val test = homeViewModel.buy(price = 9999.0, level = HomeViewModel.Level.silver)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_2)
    }
    @Test
    fun buyBonusSilver5000() {
        val test = homeViewModel.buy(price = 5000.0, level = HomeViewModel.Level.silver)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_2)
    }
    @Test
    fun buyBonusSilver4999() {
        val test = homeViewModel.buy(price = 4999.0, level = HomeViewModel.Level.silver)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_1)
    }
    @Test
    fun buyBonusSilver3000() {
        val test = homeViewModel.buy(price = 3000.0, level = HomeViewModel.Level.silver)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_1)
    }
    @Test
    fun buyBonusSilver2999() {
        val test = homeViewModel.buy(price = 2999.0, level = HomeViewModel.Level.silver)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_0)
    }


    //    Hạng vàng 	Bonus
//    Số tiền thanh toán 3,000円/ yên	Giảm 3%
//    Số tiền thanh toán 5,000円/yên	Giảm 5%
//    Số tiền thanh toán 10,000円/yên	Giảm 10%
    @Test
    fun buyBonusGold10001() {
        val test = homeViewModel.buy(price = 10001.0, level = HomeViewModel.Level.gold)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_10)
    }
    @Test
    fun buyBonusGold10000() {
        val test =
            homeViewModel.buy(price = 10000.0, level = HomeViewModel.Level.gold)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_10)
    }
    @Test
    fun buyBonusGold9999() {
        val test = homeViewModel.buy(price = 9999.0, level = HomeViewModel.Level.gold)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_5)
    }
    @Test
    fun buyBonusGold5000() {
        val test = homeViewModel.buy(price = 5000.0, level = HomeViewModel.Level.gold)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_5)
    }
    @Test
    fun buyBonusGold4999() {
        val test = homeViewModel.buy(price = 4999.0, level = HomeViewModel.Level.gold)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_3)
    }
    @Test
    fun buyBonusGold3000() {
        val test = homeViewModel.buy(price = 3000.0, level = HomeViewModel.Level.gold)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_3)
    }
    @Test
    fun buyBonusGold2999() {
        val test = homeViewModel.buy(price = 2999.0, level = HomeViewModel.Level.gold)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_0)
    }

    //    Hạng đen	Bonus
//    Số tiền thanh toán 3,000円/ yên	Giảm 5%
//    Số tiền thanh toán 5,000円/yên	Giảm 7%
//    Số tiền thanh toán 10,000円/yên	Giảm 15%
    @Test
    fun buyBonusBlack10001() {
        val test = homeViewModel.buy(price = 10001.0, level = HomeViewModel.Level.black)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_15)
    }
    @Test
    fun buyBonusBlack10000() {
        val test =
            homeViewModel.buy(price = 10000.0, level = HomeViewModel.Level.black)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_15)
    }
    @Test
    fun buyBonusBlack9999() {
        val test = homeViewModel.buy(price = 9999.0, level = HomeViewModel.Level.black)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_7)
    }
    @Test
    fun buyBonusBlack5000() {
        val test = homeViewModel.buy(price = 5000.0, level = HomeViewModel.Level.black)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_7)
    }
    @Test
    fun buyBonusBlack4999() {
        val test = homeViewModel.buy(price = 4999.0, level = HomeViewModel.Level.black)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_5)
    }
    @Test
    fun buyBonusBlack3000() {
        val test = homeViewModel.buy(price = 3000.0, level = HomeViewModel.Level.black)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_5)
    }
    @Test
    fun buyBonusBlack2999() {
        val test = homeViewModel.buy(price = 2999.0, level = HomeViewModel.Level.black)
        Assert.assertEquals(test, HomeViewModel.Bonus.bonus_0)
    }


}