package com.example.gamearena.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamearena.data.TimeSlot
import com.example.gamearena.ui.theme.NeonYellow
import com.example.gamearena.ui.theme.SurfaceVariantDark
import com.example.gamearena.ui.viewmodel.BookingUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacilityDetailScreen(
    uiState: BookingUiState,
    onBackClick: () -> Unit,
    onDateSelect: (Long) -> Unit,
    onTimeSlotSelect: (TimeSlot) -> Unit,
    onBookingConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    val facility = uiState.facility

    if (facility == null) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = NeonYellow)
        }
        return
    }

    Box(modifier = modifier.fillMaxSize().background(Color.Black)) {
        // Hero Image Placeholder (Top Half)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(SurfaceVariantDark)
        ) {
            // Back Button
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(top = 40.dp, start = 16.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            
            // Like/Heart Button
            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 40.dp, end = 16.dp)
            ) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Like", tint = NeonYellow)
            }
            
            // Title over image
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, bottom = 60.dp) // Padding to be above the overlapping card
            ) {
                Text(
                    text = facility.name,
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, null, tint = NeonYellow, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sports Complex, Arena Central", // Generic or add location to Facility model
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }

        // Draggable/Overlapping Sheet
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 320.dp) // Overlap amount
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)) // Dark theme card
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Rating Row - Made Generic
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Badge - Generic or based on Type
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = SurfaceVariantDark,
                            modifier = Modifier.height(60.dp)
                        ) {
                             Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 16.dp)) {
                                 Text(
                                    text = "Premium\nFacility",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White,
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                )
                             }
                        }
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Column {
                            Row {
                                repeat(5) { Icon(Icons.Rounded.Star, null, tint = NeonYellow, modifier = Modifier.size(20.dp)) }
                            }
                            Text(
                                text = "Highly Rated",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                        
                        Spacer(modifier = Modifier.weight(1f))
                        
                        Row {
                            IconButton(onClick = { }) { Icon(Icons.Default.Phone, null, tint = Color.Gray) }
                            IconButton(onClick = { }) { Icon(Icons.Default.Share, null, tint = Color.Gray) }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // About Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "About",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        
                        Surface(
                            color = NeonYellow.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.border(1.dp, NeonYellow, RoundedCornerShape(8.dp))
                        ) {
                             Text(
                                text = "${facility.hourlyRate}$ / hour",
                                style = MaterialTheme.typography.labelLarge,
                                color = NeonYellow,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = facility.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.LightGray,
                        lineHeight = 24.sp
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Text(
                        text = "Amenities",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        SuggestionChip(onClick = {}, label = { Text("Parking") }, colors = SuggestionChipDefaults.suggestionChipColors(labelColor = Color.White))
                        SuggestionChip(onClick = {}, label = { Text("Showers") }, colors = SuggestionChipDefaults.suggestionChipColors(labelColor = Color.White))
                        SuggestionChip(onClick = {}, label = { Text("Equipment") }, colors = SuggestionChipDefaults.suggestionChipColors(labelColor = Color.White))
                    }


                    Spacer(modifier = Modifier.height(100.dp)) // Space for the bottom button
                }
            }
        }
        
         // Sticky Bottom Button "Book Now"
         Button(
            onClick = { 
                onBookingConfirm() 
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = NeonYellow,
                contentColor = Color.Black
            ),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(24.dp)
                .height(56.dp)
                .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.large)
        ) {
            Text(
                text = "Book Now",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
