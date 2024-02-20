package com.example.unscramble.ui

data class GameUiState(
    val currentScrambledWord: String = "", // 글자가 섞인 현재 단어를 위한 변수
    val currentWordCount: Int = 1, // 단어 수를 세는 변수
    val score: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false
)
