package trpl.nim234311040.scorem3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ScoreViewModel : ViewModel() {
    private val _scoreTeamRider = MutableStateFlow(0)
    val scoreTeamRider: StateFlow<Int> = _scoreTeamRider

    private val _scoreTeamMonster = MutableStateFlow(0)
    val scoreTeamMonster: StateFlow<Int> = _scoreTeamMonster

    private val _gameScore = MutableStateFlow(0)
    val gameScore: MutableStateFlow<Int> = _gameScore

    private fun updateScore(scoreFlow: MutableStateFlow<Int>, points: Int = 1) {
        val newScore = scoreFlow.value + points
        scoreFlow.value = newScore

        if (newScore / 10 > (newScore - points) / 10) {
            _gameScore.value += 1
        }
    }

    fun addPoint(team: String) {
        when (team) {
            "A" -> updateScore(_scoreTeamRider)
            "B" -> updateScore(_scoreTeamMonster)
        }
    }

    fun addMorePoints(team: String) {
        when (team) {
            "A" -> updateScore(_scoreTeamRider, 3)
            "B" -> updateScore(_scoreTeamMonster, 3)
        }
    }

    fun resetSet() {
        _scoreTeamRider.value = 0
        _scoreTeamMonster.value = 0
    }

    fun resetGame() {
        _gameScore.value = 0
        resetSet()
    }
}