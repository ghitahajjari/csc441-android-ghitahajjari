package edu.lemoyne.campusapp

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.Alignment
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
            "In another life",
            "Maybe next time"
        )
    }

    // --- Class 10: Step 1: see the problem ---
    //val music = remember {
     //   (1..60).map { "Test music $it" }.toMutableList()
    //}

    // --- Class 9: Step 4: which screen is showing is just state ---
    var currentScreen by rememberSaveable { mutableStateOf("home") }

    when(currentScreen) {
        "home" -> HomeScreen(
            music = music,
            onAddSong = { music.add(it) },
            onSeeAll = { currentScreen = "list" },
            // --- Lab 9: Task 2: add about screen case ---
            onAbout = { currentScreen = "about"},
            modifier = modifier
        )
        "list" -> ListScreen(
            music = music,
            onBack = { currentScreen = "home" },
            // --- Class 10: Step 4: only the owner changes the list ---
            onRemove = { music.remove(it) },
            modifier = modifier
        )
        // --- Lab 9: Task 2: add about screen case ---
        "about" -> AboutScreen(
            onBack = { currentScreen = "home"},
            modifier = modifier
        )
    }

}

// --- Class 6: Step 1: my own screen ---
// --- Class 9: Step 2: Homescreen gets its data from outside ---
@Composable
fun HomeScreen(
    music: List<String>,
    onAddSong: (String) -> Unit,
    onSeeAll: () -> Unit,
    // Lab 9: Task 2: add about screen case ---
    onAbout: () -> Unit,
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

        //Spacer(modifier = Modifier.height(16.dp))

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
                    // --- Class 9: Step 3: ask the owner to add it ---
                    onAddSong(newMusic.trim())
                    //music.add(newMusic.trim())
                    newMusic = ""
                    error = null
                } else {
                    error = problem
                }
            },
            // --- Class 8: Step 6: the sign on the door, not the lock ---
            enabled = newMusic.isNotBlank()
        ) {
            Text("Add song")
        }


        Spacer(modifier = Modifier.height(24.dp))

        // --- Class 7: Step 2: draw whatever is in the list ---
        Text(
            // --- Lab 7: Task 2: 1 trail, not 1 trails ---
            text = if (music.size == 1) "1 song" else "${music.size} songs",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ---Class 9: Step 5: a way to the second screen ---
        Button(onClick = onSeeAll) {
            Text(text = "See all songs")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // --- Lab 9: Task 2: add about screen case ---
        TextButton(onClick = onAbout) {
           Text(text = "About")
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
    onRemove: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    // --- Class 9: Step 6: the phone's back button goes home too ---
    BackHandler { onBack() }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
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

        // --- Lab 9: Task 1: count on the list screen ---
        Text(
            text = if (music.size == 1) "1 song" else "${music.size} songs",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        //for (song in music){
            //Text(text = song, fontSize = 18.sp, modifier = Modifier.padding(vertical = 6.dp))
        //}


        // --- Class 10: Step 5: the empty case ---
        if(music.isEmpty()) {
            Text(
                text = "No songs yet. Add one on the home screen.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            // --- Class 10: Step 2: a list that scrolls ---
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(music) { music ->
                    //Text(text = music, fontSize = 18.sp)
                    MusicRow(
                        name = music,
                        onRemove = { onRemove(music) }
                    )
                }
            }
        }
    }
}


// --- Class 10: Step 3: one row, as its own composable ---
@Composable
fun MusicRow(
    name: String,
    onRemove: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )

            // --- Class 10: Step 4: a remove button on every row ---
            TextButton(onClick = onRemove) {
                Text("Remove")
            }
        }
    }
}

// --- Lab 9: Task 2: a third screen ---
@Composable
fun AboutScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ){
        TextButton(onClick = onBack) {
            Text("Back")
        }

        Text(
            text = "About",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Playlist Log keeps track of the songs I've listened to.")
        Text(text = "Built for CSC 441 by Ghita Hajjari.")
        Text(text = "Please enjoy your music!")
        Text(text = "Add any recommendations.")
    }
}


// --- Class 8: Step 1: one rule book for music names ---
const val MAX_NAME_LENGTH = 30
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
// --- Class 9: Step 3: previews need sample data now ---
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CampusAppTheme {
        HomeScreen(
            music = listOf(
                "Orbiter",
                "Purple",
                "4th of July",
                "Flemme",
                "In another life"
            ),
            onAddSong = {},
            onSeeAll = {},
            // --- Lab 9: Task 2: add about screen case ---
            onAbout = {}
        )
    }
}

// --- Lab 6: Task 4: dark mode preview ---
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDarkPreview() {
    CampusAppTheme {
        HomeScreen(
            music = listOf(
                    "Orbiter",
                    "Purple",
                    "4th of July",
                    "Flemme",
                    "In another life"
                ),
            onAddSong = {},
            onSeeAll = {},
            // --- Lab 9: Task 2: add about screen case ---
            onAbout = {}
        )
    }
}

// --- Class 9: Step 7: preview the list screen ---
@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    CampusAppTheme {
        ListScreen(
            music = listOf(
                    "Orbiter",
                    "Purple",
                    "4th of July",
                    "Flemme",
                    "In another life"
                ),
            onBack = {},
            onRemove = {}
        )
    }
}

// --- Class 10: Step 5: preview the empty case too ---
@Preview(showBackground = true)
@Composable
fun ListScreenEmptyPreview() {
    CampusAppTheme {
        ListScreen(
            music = emptyList(),
            onBack = {},
            onRemove = {}
        )
    }
}