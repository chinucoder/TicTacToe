package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var Player = true
    var turn_count = 0
    var boardStatus = Array(3){IntArray(3)}

    lateinit var board:Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(arrayOf(button,button2,button3), arrayOf(button4,button5,button6), arrayOf(button7,button8,button9))

        for(i in board)
        {
            for(button in i)
            {
                button.setOnClickListener(this)
            }
        }
        initialiseBoardStatus()

        ResetButton.setOnClickListener {
            Player=true
            turn_count=0
            initialiseBoardStatus()
        }

    }

    private fun initialiseBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1

            }
        }
        for (i in board) {
            for(button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
        updateDisplay ("Player X's turn!")
    }

    override fun onClick(view: View) {
        when ( view.id ) {
            R.id.button -> {
                updateStatus ( row = 0, col = 0, player = Player )
            }

            R.id.button2 -> {
                updateStatus ( row = 0, col = 1, player = Player )
            }

            R.id.button3 -> {
                updateStatus ( row = 0, col = 2, player = Player )
            }

            R.id.button4 -> {
                updateStatus ( row = 1, col = 0, player = Player )
            }

            R.id.button5 -> {
                updateStatus ( row = 1, col = 1, player = Player )
            }

            R.id.button6 -> {
                updateStatus ( row = 1, col = 2, player = Player )
            }

            R.id.button7 -> {
                updateStatus ( row = 2, col = 0, player = Player )
            }

            R.id.button8 -> {
                updateStatus ( row = 2, col = 1, player = Player )
            }

            R.id.button9 -> {
                updateStatus ( row = 2, col = 2, player = Player )
            }

        }
        turn_count++
        Player = !Player
        if(Player)
        {
            updateDisplay ("Player X's turn!")
        }

        else
        {
            updateDisplay ("Player O's turn!")
        }
        if(turn_count==9)
        {
            updateDisplay ("Draw!")
        }

        checkWinnner()
    }

    private fun checkWinnner() {
        for(i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    updateDisplay("Player X wins!")
                    break
                } else if (boardStatus[i][0] == 0) {
                    updateDisplay("Player O wins!")
                    break
                }
            }
            for (i in 0..2) {
                if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                    if (boardStatus[0][i] == 1) {
                        updateDisplay("Player X wins!")
                        break
                    } else if (boardStatus[0][i] == 0) {
                        updateDisplay("Player O wins!")
                        break
                    }
                }
            }
            for (i in 0..2) {
                if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
                    if (boardStatus[0][0] == 1) {
                        updateDisplay("Player X wins!")
                    } else if (boardStatus[0][0] == 0) {
                        updateDisplay("Player O wins!")

                    }
                }
            }
            for (i in 0..2) {
                if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
                    if (boardStatus[0][2] == 1) {
                        updateDisplay("Player X wins!")

                    } else if (boardStatus[0][2] == 0) {
                        updateDisplay("Player O wins!")

                    }
                }
            }
        }

    }

    private fun disableButton(){
        for(i in board)
        {
            for(button in i)
            {
                button.isEnabled = false
            }
        }

    }

    private fun updateDisplay(text: String) {
        displayTv.text = text
        if(text.contains("wins"))
        {
            disableButton()
        }

    }

    private fun updateStatus(row: Int, col: Int, player: Boolean) {
        val text = if (player) "X" else "O"
        val value = if (player)   1 else 0

        board[row][col].apply{
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }
}