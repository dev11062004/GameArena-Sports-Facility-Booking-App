package com.example.gamearena

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.launch
import com.example.gamearena.data.AppDatabase
import com.example.gamearena.data.repository.BookingRepository
import com.example.gamearena.data.repository.FacilityRepository
import com.example.gamearena.ui.navigation.Screen
import com.example.gamearena.ui.screens.BookingHistoryScreen
import com.example.gamearena.ui.screens.FacilityDetailScreen
import com.example.gamearena.ui.screens.HomeScreen
import com.example.gamearena.ui.screens.LoginScreen
import com.example.gamearena.ui.screens.BookingListScreen
import com.example.gamearena.ui.screens.AddBookingScreen
import com.example.gamearena.ui.screens.EditBookingScreen
import com.example.gamearena.data.FirebaseBooking
import com.google.gson.Gson
import com.example.gamearena.ui.theme.GameArenaTheme
import com.example.gamearena.ui.viewmodel.*
import com.example.gamearena.utils.UserPreferences

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        try {
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
        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback UI in case of initialization failure
            setContent {
                GameArenaTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(24.dp)
                            ) {
                                Text(
                                    text = "Failed to initialize app",
                                    style = MaterialTheme.typography.headlineMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Please restart the app",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
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

    // Shared AuthViewModel for the entire app
    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val coroutineScope = rememberCoroutineScope()

    // Check login state on app start
    val isLoggedIn by userPreferences.isLoggedIn.collectAsState(initial = false)

    // Determine start destination based on login state
    val startDestination = if (isLoggedIn) Screen.Home.route else Screen.Login.route

    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.BookingHistory.route,
        Screen.Profile.route,
        Screen.BookingList.route
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
                                Screen.Profile.route -> "Profile"
                                Screen.BookingList.route -> "Firebase Bookings"
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
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onSignUpClick = {
                        navController.navigate(Screen.Registration.route)
                    },
                    authViewModel = authViewModel
                )
            }

            composable(Screen.Registration.route) {
                com.example.gamearena.ui.screens.RegistrationScreen(
                    onRegistrationSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                    authViewModel = authViewModel
                )
            }

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
                    },
                    onLogoutClick = {
                        // Handle logout
                        authViewModel.logoutUser()
                        coroutineScope.launch {
                            userPreferences.clearUserSession()
                            navController.navigate(Screen.Login.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    },
                    onNavigateToBookingList = {
                        navController.navigate(Screen.BookingList.route)
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
                        val userId = userPreferences.getUserId()
                        viewModel.createBooking(userId)
                    }
                )
            }

            composable(Screen.BookingHistory.route) {
                val viewModel: BookingHistoryViewModel = viewModel(
                    factory = BookingHistoryViewModelFactory(bookingRepository, facilityRepository)
                )
                val uiState by viewModel.uiState.collectAsState()

                LaunchedEffect(Unit) {
                    val userId = userPreferences.getUserId()
                    viewModel.loadBookings(userId)
                }

                BookingHistoryScreen(
                    uiState = uiState,
                    onCancelBooking = { bookingId ->
                        viewModel.cancelBooking(bookingId)
                    }
                )
            }

            // Firebase CRUD Booking Routes
            composable(Screen.BookingList.route) {
                val viewModel: FirebaseBookingViewModel = viewModel()
                val userId = userPreferences.getUserId()

                BookingListScreen(
                    userId = userId,
                    onNavigateToAddBooking = {
                        navController.navigate(Screen.AddBooking.route)
                    },
                    onNavigateToEditBooking = { booking ->
                        val gson = Gson()
                        val bookingJson = gson.toJson(booking)
                        val encodedJson = java.net.URLEncoder.encode(bookingJson, "UTF-8")
                        navController.navigate(Screen.EditBooking.createRoute(encodedJson))
                    },
                    viewModel = viewModel
                )
            }

            composable(Screen.AddBooking.route) {
                val viewModel: FirebaseBookingViewModel = viewModel()
                val userId = userPreferences.getUserId()

                AddBookingScreen(
                    userId = userId,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    viewModel = viewModel
                )
            }

            composable(
                route = Screen.EditBooking.route,
                arguments = listOf(navArgument("bookingData") { type = NavType.StringType })
            ) { backStackEntry ->
                val bookingData = backStackEntry.arguments?.getString("bookingData") ?: return@composable
                val decodedJson = java.net.URLDecoder.decode(bookingData, "UTF-8")
                val gson = Gson()
                val booking = gson.fromJson(decodedJson, FirebaseBooking::class.java)
                val viewModel: FirebaseBookingViewModel = viewModel()

                EditBookingScreen(
                    booking = booking,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    viewModel = viewModel
                )
            }
        }
    }
}