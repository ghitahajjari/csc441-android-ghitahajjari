package edu.lemoyne.campusapp

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.ConnectivityManagerCompat
import edu.lemoyne.campusapp.ui.theme.CampusAppTheme

// --- Class 7: Step 1: a counter that remembers ---
@Composable
fun CounterDemo() {
    var count by remember { mutableStateOf(0) }

    Button(
        onClick = { count ++ }
    ) {
        Text(text = "Tapped $count times")
    }
}

// --- Class 9: Step 2: one owner for the data ---
@Composable
fun PlaylistLogScreen(modifier: Modifier = Modifier) {
    // --- Class 7: Step 2: the list lives in state---
    val music = remember {
        mutableStateListOf(
            "Orbiter",
            "Purple",
            "4th of July",
            "Flemme",
            "In another life"
        )
    }
    // --- Class 9: Step 4: which screen is showing is just state ---
    var currentScreen by rememberSaveable { mutableStateOf("home") }

    when(currentScreen) {
        "home" -> HomeScreen(
            music = music,
            onAddSong = { music.add(it) },
            onSeeAll = { currentScreen = "list" }
        )
        "list" -> ListScreen(
            music = music,
            onBack = { currentScreen = "home" },
            modifier = modifier
        )
    }

}

// --- Class 6: Step 1: my own screen ---
@Composable
fun HomeScreen(
    music: MutableList<String>,
    onAddSong: (String) -> Unit,
    onSeeAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    // --- Class 7: Step 3: what typed lives in state ---
    var newMusic by remember { mutableStateOf("") }
    // Class 8: Step 2: the error message lives in state too ---
    var error by remember { mutableStateOf<String?> (null) }

    // --- Class 6: Step 3: a column so things stack ---
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        //CounterDemo()
        // --- Lab 6: Task 3: a picture of my own ---
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = "An ocean with waves",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Class 6: Step 4: real styling ---
        Text(
            text = "Playlist Log",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Songs I have listened to",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- Class 7: Step 3: the text field ---
        OutlinedTextField(
            value = newMusic,
            // --- Class 8: Step 5: the field itself pushes back ---
            onValueChange = {
                newMusic = it.take(MAX_NAME_LENGTH)
                error = null
            },
            label = { Text("Music name") },
            singleLine = true,
            isError = error != null,
            modifier = modifier.fillMaxWidth()
        )

        // --- Class 8: Step 4: show the problem ---
        error?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // --- Lab 7: Task 4: a live character counter ---
        Text(
            text = "${newMusic.length} / $MAX_NAME_LENGTH",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // --- Class 7: Step 4: the button changes the state ---
        Button(
            onClick = {
                // --- Class 8: Step 3: check before you add ---
                val problem = validateMusicName(newMusic, existingMusic = music)
                if (problem == null) {
                    // --- Class 9: Step 3: ---
                   onAddSong(newMusic.trim())
                    music.add(newMusic.trim())
                    newMusic = ""
                } else {
                    error = problem
                }
            },
            // --- Class 8: Step 6: the sign on the door, not the lock ---
            enabled = newMusic.isNotBlank()
        ) {
            Text("Add song")
        }


        Spacer(modifier = Modifier.height(8.dp))

        // --- Class 7: Step 2: draw whatever is in the list ---
        Text(
            // --- Lab 7: Task 2: 1 trail, not 1 trails ---
            text = if (music.size == 1) "1 song" else "${music.size} songs",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ---Class 9: Step5: a way to teh second screen ---
        Button(
            onClick = onSeeAll
        ) {
            Text(text = "See all songs")
        }


        // --- Lab 6: Task 2: footer line ---
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Last updated September 2026",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

    }

}

// --- Class 9: Step 3: the second screen ---
@Composable
fun ListScreen(
    music: List<String>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    // --- Class 9: Step 6: the phone's back button goes home to ---
    BackHandler { onBack() }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("Back")
        }

        Text(
            text = "All songs",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        for (song in music){
            Text(text = song, fontSize = 18.sp)
        }
    }
}

const val MAX_NAME_LENGTH = 30

// --- Class 8: Step 1: one rule book for music names ---
fun validateMusicName(input: String, existingMusic: List<String>): String? {
    val name = input.trim()
    return when {
        name.isEmpty() -> "Enter a Music name"
        // --- Lab 8: Task 1: minimum length ---
        name.length < 3 -> "Too short - at least 3 characters"
        name.length > MAX_NAME_LENGTH -> "Keep it to $MAX_NAME_LENGTH characters or fewer"
        // --- Lab 8: Task 2: music is not just numbers ---
        name.all { it.isDigit() } -> "my own rule"
        existingMusic.any { it.equals(name, ignoreCase = true) } -> "$name is already on the list"
        else -> null
    }
}

// --- Class 6: Step 2: preview, no build required ---
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CampusAppTheme {
        HomeScreen(
            music = remember {
                mutableStateListOf(
                "Orbiter",
                "Purple",
                "4th of July",
                "Flemme",
                "In another life"
            )
        },
            onAddSong = {},
            onSeeAll = {}
        )
    }
}

// --- Lab 6: Task 4: dark mode preview ---
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDarkPreview() {
    CampusAppTheme {
        HomeScreen(
            music = remember {
                mutableStateListOf(
                    "Orbiter",
                    "Purple",
                    "4th of July",
                    "Flemme",
                    "In another life"
                )
            },
            onAddSong = {},
            onSeeAll = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    CampusAppTheme {
        ListScreen(
            music = remember {
                mutableStateListOf(
                    "Orbiter",
                    "Purple",
                    "4th of July",
                    "Flemme",
                    "In another life"
                )
            },
            onBack = {}
        )
    }

}