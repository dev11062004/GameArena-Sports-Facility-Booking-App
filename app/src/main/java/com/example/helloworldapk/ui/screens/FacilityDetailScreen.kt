package com.example.helloworldapk.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworldapk.data.TimeSlot
import com.example.helloworldapk.ui.theme.NeonYellow
import com.example.helloworldapk.ui.theme.SurfaceVariantDark
import com.example.helloworldapk.ui.viewmodel.BookingUiState
import java.util.*

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
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            
            // Like/Heart Button
            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 40.dp, end = 16.dp)
            ) {
                Icon(Icons.Default.Favorite, contentDescription = "Like", tint = NeonYellow)
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
                    color = Color.White
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, null, tint = Color.White, modifier = Modifier.size(16.dp))
                    Text(
                        text = "Sokak 748, 38", // Mock address
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }

        // Draggable/Overlapping Sheet (Simulated with absolute offset/layout for now or standard scroll)
        // Design shows a white/light card starting from bottom 1/3 of screen
        // We'll use a Column with a spacer to push content down, but background logic needs care.
        // Easiest is to just have a Column that scrolls, with the top part being transparent.
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 320.dp) // Overlap amount
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White) // White card as per design (or light grey)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Rating Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Badge
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color.Black),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "New\nBasketball\nSchool",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                fontSize = 8.sp,
                                lineHeight = 10.sp
                            )
                        }
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Column {
                            Row {
                                repeat(4) { Icon(Icons.Rounded.Star, null, tint = NeonYellow, modifier = Modifier.size(20.dp)) }
                                Icon(Icons.Rounded.Star, null, tint = Color.Gray, modifier = Modifier.size(20.dp))
                            }
                            Text(
                                text = "563 reviews",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                        
                        Spacer(modifier = Modifier.weight(1f))
                        
                        Row {
                            Icon(Icons.Default.Phone, null, tint = Color.Gray)
                            Spacer(modifier = Modifier.width(16.dp))
                            Icon(Icons.Default.Language, null, tint = Color.Gray)
                            Spacer(modifier = Modifier.width(16.dp))
                            Icon(Icons.Default.Share, null, tint = Color.Gray)
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
                            color = Color.Black
                        )
                        
                        Surface(
                            color = NeonYellow,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                             Text(
                                text = "Our equipment",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.Black,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "${facility.description}\n\nA basketball school is a specialized educational institution that focuses on developing and enhancing the skills...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    
                    Spacer(modifier = Modifier.height(100.dp)) // Space for the bottom button
                }
            }
        }
        
         // Sticky Bottom Button "Schedule"
         Button(
            onClick = { /* Open Schedule Logic - keeping existing flow or simulating */ 
                // In real app this might expand the schedule sheet
                onBookingConfirm() 
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = NeonYellow,
                contentColor = Color.Black
            ),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(24.dp)
                .height(56.dp)
        ) {
            Text(
                text = "Schedule",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
