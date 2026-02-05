package com.example.helloworldapk.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Registration : Screen("registration")
    object Profile : Screen("profile")
    object FacilityDetail : Screen("facility/{facilityId}") {
        fun createRoute(facilityId: Long) = "facility/$facilityId"
    }
    object BookingConfirmation : Screen("booking_confirmation/{bookingId}") {
        fun createRoute(bookingId: Long) = "booking_confirmation/$bookingId"
    }
    object BookingHistory : Screen("booking_history")

    // Firebase CRUD Screens
    object BookingList : Screen("booking_list")
    object AddBooking : Screen("add_booking")
    object EditBooking : Screen("edit_booking/{bookingData}") {
        fun createRoute(bookingData: String) = "edit_booking/$bookingData"
    }
}
