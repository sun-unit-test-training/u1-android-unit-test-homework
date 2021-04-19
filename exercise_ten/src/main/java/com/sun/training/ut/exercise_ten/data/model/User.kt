package com.quanghoa.apps.lequanghoaunittestexam.model

data class User(
    val userId: Int,
    val userName: String,
    var classType: UserClassType = UserClassType.UNKNOWN,
    var point: Int = 0
)