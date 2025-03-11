package trpl.nim234311040.scorem3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ScoreViewModel : ViewModel() {
    private val _scoreTeamRider = MutableStateFlow(0)
    val scoreTeamRider: StateFlow<Int> = _scoreTeamRider

    private val _scoreTeamMonster = MutableStateFlow(0)
    val scoreTeamMonster: StateFlow<Int> = _scoreTeamMonster

    private val _gameScoreRider = MutableStateFlow(0)
    val gameScoreRider: StateFlow<Int> = _gameScoreRider

    private val _gameScoreMonster = MutableStateFlow(0)
    val gameScoreMonster: StateFlow<Int> = _gameScoreMonster

    private val _winnerMessage = MutableStateFlow("")
    val winnerMessage: StateFlow<String> = _winnerMessage

    private val WINNING_SCORE = 10

    private fun updateScore(scoreFlow: MutableStateFlow<Int>, team: String, points: Int = 1) {
        scoreFlow.value += points

        if (scoreFlow.value >= WINNING_SCORE) {
            when (team) {
                "Rider" -> _gameScoreRider.value += 1
                "Monster" -> _gameScoreMonster.value += 1
            }
            _winnerMessage.value = "Team $team wins this set!"
            resetSet()
        }
    }

    fun addPoint(team: String) {
        when (team) {
            "Rider" -> updateScore(_scoreTeamRider, "Rider")
            "Monster" -> updateScore(_scoreTeamMonster, "Monster")
        }
    }

    fun addMorePoints(team: String) {
        when (team) {
            "Rider" -> updateScore(_scoreTeamRider, "Rider", 3)
            "Monster" -> updateScore(_scoreTeamMonster, "Monster", 3)
        }
    }

    private fun resetSet() {
        _scoreTeamRider.value = 0
        _scoreTeamMonster.value = 0
    }

    fun resetGame() {
        _gameScoreRider.value = 0
        _gameScoreMonster.value = 0
        _winnerMessage.value = ""
        resetSet()
    }
}
