package com.example.helloworldapk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.helloworldapk.data.AppDatabase
import com.example.helloworldapk.data.repository.BookingRepository
import com.example.helloworldapk.data.repository.FacilityRepository
import com.example.helloworldapk.ui.navigation.Screen
import com.example.helloworldapk.ui.screens.BookingHistoryScreen
import com.example.helloworldapk.ui.screens.FacilityDetailScreen
import com.example.helloworldapk.ui.screens.HomeScreen
import com.example.helloworldapk.ui.theme.GameArenaTheme
import com.example.helloworldapk.ui.viewmodel.*
import com.example.helloworldapk.utils.UserPreferences

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize database and repositories
        val database = AppDatabase.getDatabase(applicationContext)
        val facilityRepository = FacilityRepository(database.facilityDao())
        val bookingRepository = BookingRepository(database.bookingDao())

        setContent {
            GameArenaTheme {
                GameArenaApp(
                    facilityRepository = facilityRepository,
                    bookingRepository = bookingRepository
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameArenaApp(
    facilityRepository: FacilityRepository,
    bookingRepository: BookingRepository
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.BookingHistory.route
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (showBottomBar) {
                TopAppBar(
                    title = {
                        Text(
                            text = when (currentRoute) {
                                Screen.Home.route -> "GameArena"
                                Screen.BookingHistory.route -> "My Bookings"
                                else -> "GameArena"
                            }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = currentRoute == Screen.Home.route,
                        onClick = {
                            if (currentRoute != Screen.Home.route) {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Home.route) { inclusive = true }
                                }
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.History, contentDescription = "History") },
                        label = { Text("Bookings") },
                        selected = currentRoute == Screen.BookingHistory.route,
                        onClick = {
                            if (currentRoute != Screen.BookingHistory.route) {
                                navController.navigate(Screen.BookingHistory.route) {
                                    popUpTo(Screen.Home.route)
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                val viewModel: FacilitiesViewModel = viewModel(
                    factory = FacilitiesViewModelFactory(facilityRepository)
                )
                val uiState by viewModel.uiState.collectAsState()

                HomeScreen(
                    uiState = uiState,
                    onFacilityClick = { facilityId ->
                        navController.navigate(Screen.FacilityDetail.createRoute(facilityId))
                    },
                    onFilterChange = { type ->
                        viewModel.filterByType(type)
                    }
                )
            }

            composable(
                route = Screen.FacilityDetail.route,
                arguments = listOf(navArgument("facilityId") { type = NavType.LongType })
            ) { backStackEntry ->
                val facilityId = backStackEntry.arguments?.getLong("facilityId") ?: return@composable
                val viewModel: BookingViewModel = viewModel(
                    factory = BookingViewModelFactory(facilityRepository, bookingRepository)
                )
                val uiState by viewModel.uiState.collectAsState()
                val context = androidx.compose.ui.platform.LocalContext.current

                LaunchedEffect(facilityId) {
                    viewModel.loadFacility(facilityId)
                }

                LaunchedEffect(uiState.bookingSuccess) {
                    if (uiState.bookingSuccess) {
                        navController.popBackStack()
                        viewModel.resetBookingState()
                    }
                }

                FacilityDetailScreen(
                    uiState = uiState,
                    onBackClick = { navController.popBackStack() },
                    onDateSelect = { date -> viewModel.selectDate(date) },
                    onTimeSlotSelect = { slot -> viewModel.selectTimeSlot(slot) },
                    onBookingConfirm = {
                        val userId = UserPreferences.getUserId(context)
                        viewModel.createBooking(userId)
                    }
                )
            }

            composable(Screen.BookingHistory.route) {
                val viewModel: BookingHistoryViewModel = viewModel(
                    factory = BookingHistoryViewModelFactory(bookingRepository, facilityRepository)
                )
                val uiState by viewModel.uiState.collectAsState()
                val context = androidx.compose.ui.platform.LocalContext.current

                LaunchedEffect(Unit) {
                    val userId = UserPreferences.getUserId(context)
                    viewModel.loadBookings(userId)
                }

                BookingHistoryScreen(
                    uiState = uiState,
                    onCancelBooking = { bookingId ->
                        viewModel.cancelBooking(bookingId)
                    }
                )
            }
        }
    }
}