package com.example.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() { // ViewModel 클래스 확장해서 사용

    // Game UI state, StateFlow 사용
    private val _uiState = MutableStateFlow(GameUiState()) // ViewModel 내부에서만 값에 접근 가능
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow() // asStateFlow()는 이 변경 가능 상태 흐름을 읽기 전용 상태 흐름으로 만듦

    var userGuess by mutableStateOf("")
        private set

    // Set of words used in the game
    private var usedWords: MutableSet<String> = mutableSetOf()

    private lateinit var currentWord: String // 글자가 섞인 현재 단어를 저장

    init {
        resetGame()
    }

    // 게임 초기화
    // usedWords 세트에 있는 모든 단어를 지우고 _uiState를 초기화
    // pickRandomWordAndShuffle()을 사용하여 currentScrambledWord의 새 단어를 선택
    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    // 사용자의 추측 단어 갱신
    fun updateUserGuess(guessedWord: String){
        userGuess = guessedWord
    }

    // 사용자가 추측한 단어를 확인한 후 게임 점수를 업데이트하거나 오류를 표시
    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            // 사용자의 추측이 올바르면 다음 라운드를 위해 게임을 준비하도록 업데이트된 점수로 updateGameState를 호출
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            // 추측이 틀린 경우
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        // Reset user guess
        updateUserGuess("")
    }

    // 사용자가 단어를 건너뛰면 게임 변수를 업데이트하고 다음 라운드를 위해 게임을 준비
    fun skipWord() {
        updateGameState(_uiState.value.score)
        // Reset user guess
        updateUserGuess("")
    }

    // 점수를 업데이트하고 현재 단어 수를 늘리고 WordsData.kt 파일에서 새 단어를 선택
    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS){
            //Last round in the game, update isGameOver to true, don't pick a new word
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        } else{
            // Normal round in the game
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    currentWordCount = currentState.currentWordCount.inc(),
                    score = updatedScore
                )
            }
        }
    }

    // 목록에서 임의 단어를 선택하여 글자를 섞는 메서드
    private fun pickRandomWordAndShuffle(): String {
        // Continue picking up a new random word until you get one that hasn't been used before
        currentWord = allWords.random()

        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    // String을 받아서 순서가 섞인 String을 반환하여 현재 단어의 순서를 섞는 도우미 메서
    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()

        // Scramble the word
        tempWord.shuffle()

        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }

        return String(tempWord)
    }

}