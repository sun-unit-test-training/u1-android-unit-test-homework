package com.sun.training.ut.ui.home

import com.sun.training.ut.ui.base.BaseViewModel

class HomeViewModel() : BaseViewModel() {

//    ◎Bài thi:
//    Tại nhà hàng Nam Từ Liêm đang áp dụng chế độ hội viên hình thức thẻ prepaid card.
//    ---
//    ●Điều kiện tiên quyết：
//    ①：Thứ hạng của hội viên được chia thành 3 cấp ứng với số lần sử dụng nhà hàng.
//    ②：Tuỳ theo số tiền thanh toán trên thẻ prepaid card, các bonus đặc quyền thanh toán với tỷ lệ khác nhau sẽ được cấp cho hội viên.
//    ③：Thứ hạng hội viên và điều kiện cấp bonus đặc quyền theo như bên dưới:
//    Hạng bạc	Bonus
//    Số tiền thanh toán 3,000円/ yên	Giảm 1%
//    Số tiền thanh toán 5,000円/yên	Giảm 2%
//    Số tiền thanh toán 10,000円/yên	Giảm 4%
//
//    Hạng vàng 	Bonus
//    Số tiền thanh toán 3,000円/ yên	Giảm 3%
//    Số tiền thanh toán 5,000円/yên	Giảm 5%
//    Số tiền thanh toán 10,000円/yên	Giảm 10%
//
//    Hạng đen	Bonus
//    Số tiền thanh toán 3,000円/ yên	Giảm 5%
//    Số tiền thanh toán 5,000円/yên	Giảm 7%
//    Số tiền thanh toán 10,000円/yên	Giảm 15%
//
//    ④：Mỗi lần có thanh toán 5,000 hoặc 10,000円/yên, sẽ có 1 lần bốc thăm (Bốc thăm là option có hoặc không dựa trên điều kiện Y/N)
//    Theo 1 tỷ lệ nhất định sẽ có trúng giải và phát hành coupon giảm giá. (Bỏ qua phần tỷ lệ trúng giả và % coupon giảm giá)
//    ⑤：Các trường hợp không tồn tại với các điều kiện, không cần tham khảo dưới dạng test case.
//    ---
//    Hãy tham khảo các điều kiện bên trên và viết ra các unit test case tối thiểu nhất bạn có thể nghĩ được trong trường hợp bạn đang implement chương trình đó.

    enum class Level {
        black, silver, gold
    }

    enum class Bonus(val value: Double) {
        bonus_0(1.0),
        bonus_1(1.01),
        bonus_2(1.02),
        bonus_3(1.03),
        bonus_4(1.04),
        bonus_5(1.05),
        bonus_7(1.07),
        bonus_10(1.1),
        bonus_15(1.15)
    }

    //
    enum class Coupon(val value: Double) {
        coupon_10(1.1)
    }

    enum class Price(val value: Int) {
        a3000(3000), a5000(5000), a10000(10000)
    }

    /*
    price: Gia tien mua
    level: hang hoi vien
    * */
    fun buy(price: Double, level: Level): Bonus {
        when (level) {
            Level.silver -> {
                if (price >= Price.a10000.value) {
                    return Bonus.bonus_4
                }
                if (price >= Price.a5000.value) {
                    return Bonus.bonus_2
                }
                if (price >= Price.a3000.value) {
                    return Bonus.bonus_1
                }
            }
            Level.gold -> {
                if (price >= Price.a10000.value) {
                    return Bonus.bonus_10
                }
                if (price >= Price.a5000.value) {
                    return Bonus.bonus_5
                }
                if (price >= Price.a3000.value) {
                    return Bonus.bonus_3
                }
            }
            Level.black -> {
                if (price >= Price.a10000.value) {
                    return Bonus.bonus_15
                }
                if (price >= Price.a5000.value) {
                    return Bonus.bonus_7
                }
                if (price >= Price.a3000.value) {
                    return Bonus.bonus_5
                }
            }
        }
        return Bonus.bonus_0
    }

    // gia su ty le trung giai la 100% nhan duoc Coupon giam gia 10%
    fun buyReal(price: Double, level: Level, isRandom: Boolean): Double {
        return if (isRandom && price >= Price.a5000.value) {
            buy(price, level).value * Coupon.coupon_10.value * price
        } else buy(price, level).value * price
    }

}