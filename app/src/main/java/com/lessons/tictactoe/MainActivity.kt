package com.lessons.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: Array<Button>
    private var currentPlayer = "X"
    private var board = Array(9) { "" }
    private var gameActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val statusText = findViewById<TextView>(R.id.status)
        val playAgainButton = findViewById<Button>(R.id.play_again_button)

        playAgainButton.visibility = View.GONE

        buttons = arrayOf(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9),
        )

        for (button in buttons) {
            button.setOnClickListener {
                if (!gameActive) return@setOnClickListener
                if (button.text.isNotEmpty()) return@setOnClickListener

                button.text = currentPlayer
                val index = button.tag.toString().toInt()
                board[index] = currentPlayer

                currentPlayer = if (currentPlayer == "X") "O" else "X"
                statusText.text = getString(R.string.player_s_turn, currentPlayer)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}