package vedu_enterprises.application.activityies.activity

import androidx.activity.ComponentActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import vedu_enterprises.application.R
import vedu_enterprises.application.ui.theme.Green
import vedu_enterprises.application.ui.theme.LightBlue80

@Composable
fun BillDetails() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Bill details",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.List, contentDescription = "Items"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Items total")
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFFDCEEFF), shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Saved ₹257",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF004AAD)
                    )
                }
            }
            Text(
                text = "₹1,504",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.DirectionsBike, contentDescription = "Delivery"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Delivery charge")
            }
            Text(
                text = "₹25 FREE",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF004AAD)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ShoppingBag, contentDescription = "Handling"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Handling charge")
            }
            Text(
                text = "₹2", style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(thickness = 2.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Grand total",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "₹1,249",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightBlue80.copy(alpha = 0.8f), shape = RoundedCornerShape(8.dp))
                .padding(
                    10.dp
                )
        ) {
            Column {
                Text(
                    text = "Your total savings",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF004AAD)
                )
                Text(
                    text = "Includes ₹25 savings through free delivery",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Black
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowHeader(heading: String, activity: ComponentActivity) {
    TopAppBar(title = {
        Text(
            heading, fontSize = 18.sp, modifier = Modifier.padding(start = 15.dp)
        )
    }, navigationIcon = {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
            modifier = Modifier.size(35.dp)
        ) {
            IconButton(onClick = { activity.finish() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    }, modifier = Modifier.padding(start = 10.dp)
    )
}

@Composable
fun ShowTextView(
    text: String,
    color: Color,
    fontSize: TextUnit,
    isBold: Boolean,
    topPadding: Dp,
    bottomPadding: Dp,
    rightPadding: Dp,
    leftPadding: Dp,
    font: Int
) {
    val robotoBoldFont = when (font) {
        1 -> FontFamily(Font(R.font.open_sans_semibold))
        2 -> FontFamily(Font(R.font.poppin_ssemi_bold))
        3 -> FontFamily(Font(R.font.poppins_bold))
        4 -> FontFamily(Font(R.font.poppins_extra_bold))
        5 -> FontFamily(Font(R.font.poppins_light))
        6 -> FontFamily(Font(R.font.poppins_regular))
        7 -> FontFamily(Font(R.font.raleway_bold))
        8 -> FontFamily(Font(R.font.roboto_bold))
        9 -> FontFamily(Font(R.font.roboto_regular))
        else -> FontFamily(Font(R.font.roboto_regular))
    }
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(
            start = leftPadding, top = topPadding, end = rightPadding, bottom = bottomPadding
        ),
        fontFamily = robotoBoldFont
    )
}

@Composable
fun ScaleText(text: String) {
    var big by remember { mutableStateOf(true) }
    val scale by animateFloatAsState(targetValue = if (big) 1.5f else 1f)
    val robotoBoldFont = FontFamily(Font(R.font.poppins_bold))

    LaunchedEffect(Unit) {
        while (true) {
            big = !big
            delay(1000)
        }
    }

    Text(
        text = text, modifier = Modifier.scale(scale), fontSize = 20.sp, fontFamily = robotoBoldFont
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheetScreen(showBottomSheet: MutableState<Boolean>, onClick: () -> Unit) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(showBottomSheet.value) {
        if (showBottomSheet.value) {
            bottomSheetState.show()
        } else {
            bottomSheetState.hide()
        }
    }

    ModalBottomSheet(
        onDismissRequest = { showBottomSheet.value = false },
        sheetState = bottomSheetState,
        containerColor = Color.Transparent
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp), color = Color.Transparent
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Surface(
                        shape = CircleShape,
                        modifier = Modifier.padding(top = 5.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    ) {
                        IconButton(onClick = { showBottomSheet.value = false }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cancel",
                                tint = Color.Black
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 15.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(
                            topStart = 20.dp, topEnd = 20.dp, bottomStart = 0.dp, bottomEnd = 0.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Delete Account",
                                fontSize = 24.sp,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                            Text(
                                text = "Are you sure?",
                                fontSize = 18.sp,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Button(
                                    onClick = { showBottomSheet.value = false },
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text(
                                        text = "Cancel", fontSize = 16.sp
                                    )
                                }
                                Button(
                                    onClick = { onClick },
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text(
                                        text = "Delete", fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Green)
    ) {
        Text(text = text, fontSize = 18.sp, color = Color.Black)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableSheet() {
    val sheetState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        containerColor = Color.Transparent,
        scaffoldState = sheetState,
        sheetContent = { },
        sheetPeekHeight = 0.dp,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp), color = Color.Transparent
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Surface(
                            shape = CircleShape,
                            modifier = Modifier.padding(top = 5.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        ) {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Cancel",
                                    tint = Color.Black
                                )
                            }
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(top = 15.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Delete Account",
                                    fontSize = 24.sp,
                                    modifier = Modifier.padding(top = 16.dp)
                                )
                                Text(
                                    text = "Are you sure?",
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Button(
                                        onClick = { /* Handle Cancel */ },
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(8.dp),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Text(
                                            text = "Cancel", fontSize = 16.sp
                                        )
                                    }
                                    Button(
                                        onClick = { },
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(8.dp),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Text(
                                            text = "Delete", fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteAccountDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(onDismissRequest = onDismiss, title = {
        Text(
            stringResource(R.string.delete_account),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }, text = {
        Text(
            stringResource(R.string.are_you_sure_you_want_to_delete_your_account_this_action_cannot_be_undone),
            fontSize = 16.sp
        )
    }, confirmButton = {
        TextButton(onClick = onConfirm) {
            Text("Delete", color = MaterialTheme.colorScheme.error)
        }
    }, dismissButton = {
        TextButton(onClick = onDismiss) {
            Text("Cancel", color = MaterialTheme.colorScheme.onSurface)
        }
    }, icon = {
        Icon(
            Icons.Default.Warning,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(40.dp)
        )
    })
}

@Composable
fun ShowSnackBar() {
    val snackbarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }

    Scaffold(snackbarHost = {
        SnackbarHost(
            hostState = snackbarHostState, modifier = Modifier.padding(8.dp)
        )
    }, content = { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Button(
                onClick = { showSnackbar = true }, modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Show Snackbar")
            }
        }
    })
}