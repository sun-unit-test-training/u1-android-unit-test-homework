package com.sun.training.ut.exercise_ten.ui

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.data.model.MemberClassType
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT
import com.sun.training.ut.exercise_ten.domain.business.DiscountBusiness.UNKNOWN_CLASS_DISCOUNT_PERCENT
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ExerciseTenViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    lateinit var exerciseTenViewModelTest: ExerciseTenViewModel

    @MockK
    lateinit var resourceTest: Resources

    @Before
    @Throws(Exception::class)
    fun setUp() {

        MockKAnnotations.init(this, relaxUnitFun = true)

        every {
            resourceTest.getString(R.string.ex_10_class_type_black)
        } returns "Hạng Đen"

        every {
            resourceTest.getString(R.string.ex_10_class_type_gold)
        } returns "Hạng Vàng"

        every {
            resourceTest.getString(R.string.ex_10_class_type_silver)
        } returns "Hạng Bạc"

        every {
            resourceTest.getString(R.string.ex_10_class_type_unknown)
        } returns "Không phải hội viên"

        every {
            resourceTest.getString(R.string.ex_10_user_name_default)
        } returns "Bach Ngoc Hoai"

        exerciseTenViewModelTest = ExerciseTenViewModel(resourceTest)
    }

//    case
//    class    payment   gift

//    silver    > 10000     no
//    silver    = 10000     yes
//    silver    5000<x<10000     no
//    silver    = 5000     yes
//    silver    3000<x<5000     no
//    silver    = 3000     no
//    silver    < 3000     no
//
//    gold    > 10000     no
//    gold    = 10000     yes
//    gold    5000<x<10000     no
//    gold    = 5000     yes
//    gold    3000<x<5000     no
//    gold    = 3000     no
//    gold    < 3000     no
//
//    black    > 10000     no
//    black    = 10000     yes
//    black    5000<x<10000     no
//    black    = 5000     yes
//    black    3000<x<5000     no
//    black    = 3000     no
//    black    < 3000     no
//
//    unknown    > 10000     no
//    unknown    = 10000     yes
//    unknown    5000<x<10000     no
//    unknown    = 5000     yes
//    unknown    x<5000     no

    @Test
    fun printInvoice_silverClass_paymentMoreThan10000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "11000"
            printInvoice()
            Assert.assertEquals(
                11000 * SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silverClass_payment10000Yen_withGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "10000"
            printInvoice()
            Assert.assertEquals(
                10000 * SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(true, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silverClass_paymentAbove5000YenToUnder10000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "8000"
            printInvoice()
            Assert.assertEquals(
                8000 * SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silverClass_payment5000Yen_withGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "5000"
            printInvoice()
            Assert.assertEquals(
                5000 * SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(true, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silverClass_paymentAbove3000YenToUnder5000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "4000"
            printInvoice()
            Assert.assertEquals(
                4000 * SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silverClass_payment3000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "3000"
            printInvoice()
            Assert.assertEquals(
                3000 * SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_silverClass_paymentUnder3000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_silver))
            subTotal.value = "2000"
            printInvoice()
            Assert.assertEquals(
                2000 * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_goldClass_paymentMoreThan10000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "11000"
            printInvoice()
            Assert.assertEquals(
                11000 * GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_goldClass_payment10000Yen_withGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "10000"
            printInvoice()
            Assert.assertEquals(
                10000 * GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(true, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_goldClass_paymentAbove5000ToUnder10000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "8000"
            printInvoice()
            Assert.assertEquals(
                8000 * GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_goldClass_payment5000Yen_withGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "5000"
            printInvoice()
            Assert.assertEquals(
                5000 * GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(true, invoice.value?.giftAccepted)
        }
    }


    @Test
    fun printInvoice_goldClass_paymentAbove3000ToUnder5000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "4000"
            printInvoice()
            Assert.assertEquals(
                4000 * GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_goldClass_payment3000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "3000"
            printInvoice()
            Assert.assertEquals(
                3000 * GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_goldClass_paymentUnder3000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_gold))
            subTotal.value = "2000"
            printInvoice()
            Assert.assertEquals(
                2000 * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }


    @Test
    fun printInvoice_blackClass_paymentMoreThan10000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_black))
            subTotal.value = "12000"
            printInvoice()
            Assert.assertEquals(
                12000 * BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_blackClass_payment10000Yen_withGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_black))
            subTotal.value = "10000"
            printInvoice()
            Assert.assertEquals(
                10000 * BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(true, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_blackClass_paymentAbove5000YenToUnder10000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_black))
            subTotal.value = "8000"
            printInvoice()
            Assert.assertEquals(
                8000 * BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_blackClass_payment5000Yen_withGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_black))
            subTotal.value = "5000"
            printInvoice()
            Assert.assertEquals(
                5000 * BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(true, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_blackClass_paymentAbove3000YenToUnder5000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_black))
            subTotal.value = "4000"
            printInvoice()
            Assert.assertEquals(
                4000 * BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_blackClass_payment3000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_black))
            subTotal.value = "3000"
            printInvoice()
            Assert.assertEquals(
                3000 * BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_blackClass_paymentUnder3000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_black))
            subTotal.value = "2000"
            printInvoice()
            Assert.assertEquals(
                2000 * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                invoice.value?.discount
            )
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_unknownClass_paymentMoreThan10000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_unknown))
            subTotal.value = "12000"
            printInvoice()
            Assert.assertEquals(0.0, invoice.value?.discount)
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_unknownClass_payment10000Yen_withGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_unknown))
            subTotal.value = "10000"
            printInvoice()
            Assert.assertEquals(0.0, invoice.value?.discount)
            Assert.assertEquals(true, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_unknownClass_paymentAbove5000YenToUnder10000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_unknown))
            subTotal.value = "8000"
            printInvoice()
            Assert.assertEquals(0.0, invoice.value?.discount)
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_unknownClass_payment5000Yen_withGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_unknown))
            subTotal.value = "5000"
            printInvoice()
            Assert.assertEquals(0.0, invoice.value?.discount)
            Assert.assertEquals(true, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun printInvoice_unknownClass_paymentUnder5000Yen_noGift() {
        with(exerciseTenViewModelTest) {
            updateMemberClassType(resourceTest.getString(R.string.ex_10_class_type_unknown))
            subTotal.value = "4000"
            printInvoice()
            Assert.assertEquals(0.0, invoice.value?.discount)
            Assert.assertEquals(false, invoice.value?.giftAccepted)
        }
    }

    @Test
    fun giftAccepted_amout5000Yen_returnTrue() {
        with(exerciseTenViewModelTest) {
            val subTotal = 5000.0
            Assert.assertEquals(true, giftAccepted(subTotal = subTotal))
        }
    }

    @Test
    fun giftAccepted_amout10000Yen_returnTrue() {
        with(exerciseTenViewModelTest) {
            val subTotal = 10000.0
            Assert.assertEquals(true, giftAccepted(subTotal = subTotal))
        }
    }

    @Test
    fun giftAccepted_amout8000Yen_returnFalse() {
        with(exerciseTenViewModelTest) {
            val subTotal = 8000.0
            Assert.assertEquals(false, giftAccepted(subTotal = subTotal))
        }
    }

    @Test
    fun updateMemberClassType_typeBlack() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_black)
            updateMemberClassType(type)
            Assert.assertEquals(
                MemberClassType.BLACK_CLASS,
                user.value?.classType
            )
        }
    }

    @Test
    fun updateMemberClassType_typeSilver() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_silver)
            updateMemberClassType(type)
            Assert.assertEquals(
                MemberClassType.SILVER_CLASS,
                user.value?.classType
            )
        }
    }

    @Test
    fun updateMemberClassType_typeGold() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_gold)
            updateMemberClassType(type)
            Assert.assertEquals(
                MemberClassType.GOLD_CLASS,
                user.value?.classType
            )
        }
    }

    @Test
    fun updateMemberClassType_typeUnknow() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_unknown)
            updateMemberClassType(type)
            Assert.assertEquals(
                MemberClassType.UNKNOWN_CLASS,
                user.value?.classType
            )
        }
    }

    @Test
    fun discountCalculation_classBlack_paymentMoreThan10000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_black)
            updateMemberClassType(type)

            val subTotal = 11000.0

            Assert.assertEquals(
                subTotal * BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classBlack_payment10000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_black)
            updateMemberClassType(type)

            val subTotal = 10000.0
            Assert.assertEquals(
                subTotal * BLACK_CLASS_MIN_10K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classBlack_paymentAbove5000ToUnder10000Yen() {

        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_black)
            updateMemberClassType(type)
            val subTotal = 8000.0
            Assert.assertEquals(
                subTotal * BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }

    }

    @Test
    fun discountCalculation_classBlack_payment5000Yen() {

        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_black)
            updateMemberClassType(type)
            val subTotal = 5000.0
            Assert.assertEquals(
                subTotal * BLACK_CLASS_MIN_5K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classBlack_paymentAbove3000AndUnder5000Yen() {

        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_black)
            updateMemberClassType(type)
            val subTotal = 4000.0
            Assert.assertEquals(
                subTotal * BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classBlack_payment3000Yen() {

        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_black)
            updateMemberClassType(type)
            val subTotal = 3000.0
            Assert.assertEquals(
                subTotal * BLACK_CLASS_MIN_3K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classBlack_paymentUnder3000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_black)
            updateMemberClassType(type)
            val subTotal = 2000.0
            Assert.assertEquals(
                subTotal * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classGold_paymentMoreThan10000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_gold)
            updateMemberClassType(type)

            val subTotal = 11000.0

            Assert.assertEquals(
                subTotal * GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classGold_payment10000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_gold)
            updateMemberClassType(type)

            val subTotal = 10000.0

            Assert.assertEquals(
                subTotal * GOLD_CLASS_MIN_10K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classGold_paymentAbove5000YenToUnder10000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_gold)
            updateMemberClassType(type)

            val subTotal = 8000.0

            Assert.assertEquals(
                subTotal * GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classGold_payment5000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_gold)
            updateMemberClassType(type)

            val subTotal = 5000.0

            Assert.assertEquals(
                subTotal * GOLD_CLASS_MIN_5K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classGold_paymentAbove3000ToUnder5000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_gold)
            updateMemberClassType(type)

            val subTotal = 4000.0

            Assert.assertEquals(
                subTotal * GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classGold_payment3000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_gold)
            updateMemberClassType(type)

            val subTotal = 3000.0

            Assert.assertEquals(
                subTotal * GOLD_CLASS_MIN_3K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classGold_paymentUnder3000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_gold)
            updateMemberClassType(type)

            val subTotal = 2000.0

            Assert.assertEquals(
                subTotal * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classSilver_paymentMoreThan10000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_silver)
            updateMemberClassType(type)

            val subTotal = 11000.0

            Assert.assertEquals(
                subTotal * SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classSilver_payment10000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_silver)
            updateMemberClassType(type)

            val subTotal = 10000.0

            Assert.assertEquals(
                subTotal * SILVER_CLASS_MIN_10K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classSilver_paymentAbove5000ToUnder10000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_silver)
            updateMemberClassType(type)

            val subTotal = 8000.0

            Assert.assertEquals(
                subTotal * SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classSilver_payment5000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_silver)
            updateMemberClassType(type)

            val subTotal = 5000.0

            Assert.assertEquals(
                subTotal * SILVER_CLASS_MIN_5K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }


    @Test
    fun discountCalculation_classSilver_paymentAbove3000YenToUnder5000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_silver)
            updateMemberClassType(type)

            val subTotal = 4000.0

            Assert.assertEquals(
                subTotal * SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classSilver_payment3000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_silver)
            updateMemberClassType(type)

            val subTotal = 3000.0

            Assert.assertEquals(
                subTotal * SILVER_CLASS_MIN_3K_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }

    @Test
    fun discountCalculation_classSilver_paymentUnder3000Yen() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_unknown)
            updateMemberClassType(type)

            val subTotal = 2000.0

            Assert.assertEquals(
                subTotal * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }


    @Test
    fun discountCalculation_classUnknow_paymentAny() {
        with(exerciseTenViewModelTest) {
            val type = resourceTest.getString(R.string.ex_10_class_type_unknown)
            updateMemberClassType(type)

            val subTotal = 10000.0

            Assert.assertEquals(
                subTotal * UNKNOWN_CLASS_DISCOUNT_PERCENT,
                discountCalculation(subTotal), 0.0
            )
        }
    }


}