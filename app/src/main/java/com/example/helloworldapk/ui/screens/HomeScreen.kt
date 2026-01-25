package com.example.helloworldapk.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.helloworldapk.data.Facility
import com.example.helloworldapk.data.FacilityType
import com.example.helloworldapk.ui.viewmodel.FacilitiesUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: FacilitiesUiState,
    onFacilityClick: (Long) -> Unit,
    onFilterChange: (FacilityType?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Filter chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = uiState.selectedType == null,
                onClick = { onFilterChange(null) },
                label = { Text("All") }
            )
            FilterChip(
                selected = uiState.selectedType == FacilityType.CRICKET_GROUND,
                onClick = { onFilterChange(FacilityType.CRICKET_GROUND) },
                label = { Text("Cricket") },
                leadingIcon = { Icon(Icons.Default.SportsCricket, null, modifier = Modifier.size(18.dp)) }
            )
            FilterChip(
                selected = uiState.selectedType == FacilityType.POOL_TABLE,
                onClick = { onFilterChange(FacilityType.POOL_TABLE) },
                label = { Text("Pool") },
                leadingIcon = { Icon(Icons.Default.Pool, null, modifier = Modifier.size(18.dp)) }
            )
            FilterChip(
                selected = uiState.selectedType == FacilityType.PICKLEBALL_COURT,
                onClick = { onFilterChange(FacilityType.PICKLEBALL_COURT) },
                label = { Text("Pickleball") },
                leadingIcon = { Icon(Icons.Default.SportsTennis, null, modifier = Modifier.size(18.dp)) }
            )
        }

        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (uiState.facilities.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No facilities available")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.facilities) { facility ->
                    FacilityCard(
                        facility = facility,
                        onClick = { onFacilityClick(facility.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun FacilityCard(
    facility: Facility,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = when (facility.type) {
                    FacilityType.CRICKET_GROUND -> Icons.Default.SportsCricket
                    FacilityType.POOL_TABLE -> Icons.Default.Pool
                    FacilityType.PICKLEBALL_COURT -> Icons.Default.SportsTennis
                },
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = facility.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = facility.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${facility.hourlyRate}/hour",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "View details",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
