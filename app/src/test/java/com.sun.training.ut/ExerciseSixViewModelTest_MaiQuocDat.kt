package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.ui.excercise_six.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseSixViewModelTest_MaiQuocDat {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var exerciseSixViewModel: ExerciseSixViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        exerciseSixViewModel = ExerciseSixViewModel()
    }

    //1
    @Test
    fun calculateMinute_WatchMovie_5000_2000_returnWATCH_MOVIE_FREE_TIME_SECOND_FREE_TIME() {
        exerciseSixViewModel.onWatchMovieChecked(true)
        exerciseSixViewModel.totalPurchased = SECOND_MONEY_POINT + 1
        exerciseSixViewModel.calculateMinute()
        Assert.assertEquals(
            WATCH_MOVIE_FREE_TIME + SECOND_FREE_TIME,
            exerciseSixViewModel.freeParkingInMinute.value
        )
    }

    //2
    @Test
    fun calculateMinute_WatchMovie_Not5000_2000_returnWATCH_MOVIE_FREE_TIME_FIRST_FREE_TIME() {
        exerciseSixViewModel.onWatchMovieChecked(true)
        exerciseSixViewModel.totalPurchased = FIRST_MONEY_POINT + 1
        exerciseSixViewModel.calculateMinute()
        Assert.assertEquals(
            WATCH_MOVIE_FREE_TIME + FIRST_FREE_TIME,
            exerciseSixViewModel.freeParkingInMinute.value
        )
    }

    //3
    @Test
    fun calculateMinute_WatchMovie_Not5000_Not2000_returnWATCH_MOVIE_FREE_TIME() {
        exerciseSixViewModel.onWatchMovieChecked(true)
        exerciseSixViewModel.totalPurchased = 0
        exerciseSixViewModel.calculateMinute()
        Assert.assertEquals(WATCH_MOVIE_FREE_TIME, exerciseSixViewModel.freeParkingInMinute.value)
    }

    //4
    @Test
    fun calculateMinute_NotWatchMovie_5000_2000_returnSECOND_FREE_TIME() {
        exerciseSixViewModel.onWatchMovieChecked(false)
        exerciseSixViewModel.totalPurchased = SECOND_MONEY_POINT + 1
        exerciseSixViewModel.calculateMinute()
        Assert.assertEquals(SECOND_FREE_TIME, exerciseSixViewModel.freeParkingInMinute.value)
    }

    //5
    @Test
    fun calculateMinute_NotWatchMovie_Not5000_2000_returnFIRST_FREE_TIME() {
        exerciseSixViewModel.onWatchMovieChecked(false)
        exerciseSixViewModel.totalPurchased = FIRST_MONEY_POINT + 1
        exerciseSixViewModel.calculateMinute()
        Assert.assertEquals(FIRST_FREE_TIME, exerciseSixViewModel.freeParkingInMinute.value)
    }

    //6
    @Test
    fun calculateMinute_NotWatchMovie_Not5000_Not2000_return0() {
        exerciseSixViewModel.onWatchMovieChecked(false)
        exerciseSixViewModel.totalPurchased = 0
        exerciseSixViewModel.calculateMinute()
        Assert.assertEquals(0, exerciseSixViewModel.freeParkingInMinute.value)
    }
}