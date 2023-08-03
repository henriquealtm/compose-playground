package com.example.composeplayground.commons.navigation

import com.example.composeplayground.commons.user.UserType

enum class UserRoutePermission(private val prefix: String?) {

    END_USER("end_user"),
    TECHNICIAN("technician"),
    ALL(null);

    fun getPrefix(): String {
        return prefix?.let { it + "_" } ?: ""
    }

}

fun UserRoutePermission.checkRoutePermission(userType: UserType): Boolean {
    return (userType == UserType.END_USER && this != UserRoutePermission.TECHNICIAN)
            || (userType == UserType.TECHNICIAN && this != UserRoutePermission.END_USER)
}
