package trpl.nim234311040.scorem3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScoreScreen()
        }
    }
}

@Composable
fun ScoreScreen(viewModel: ScoreViewModel = viewModel()) {
    val scoreRider by viewModel.scoreTeamRider.collectAsState()
    val scoreMonster by viewModel.scoreTeamMonster.collectAsState()
    val gameScore by viewModel.gameScore.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Killing Score", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Game: $gameScore")

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Team Rider", style = MaterialTheme.typography.headlineLarge)
                Text(text = "$scoreRider", style = MaterialTheme.typography.headlineMedium)


                Button(onClick = { viewModel.addPoint("Rider") }) {
                    Text(text = "1 Point")
                }
                Button(onClick = { viewModel.addMorePoints("Rider") }) {
                    Text(text = "More Point!")
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Team Monster", style = MaterialTheme.typography.headlineLarge)
                Text(text = "$scoreMonster", style = MaterialTheme.typography.headlineMedium)

                Button(onClick = { viewModel.addPoint("Monster") }) {
                    Text(text = "1 Point")
                }
                Button(onClick = { viewModel.addMorePoints("Monster") }) {
                    Text(text = "More Point!")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.resetGame() }) {
            Text(text = "Reset Game")
        }
    }
}