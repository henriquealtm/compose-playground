package com.example.composeplayground.commons.navigation

import com.example.composeplayground.commons.user.UserType

interface IRoute {

    fun getRoute(userType: UserType? = null): String

    fun checkPermission(userType: UserType): Boolean

}
