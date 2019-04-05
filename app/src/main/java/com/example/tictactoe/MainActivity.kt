package com.example.tictactoe

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    var playerTurn = 1
    var totalNumOfTurns = 0
    var winner = 0
    var playerOneMoves = ArrayList<Int>()
    var playerTwoMoves = ArrayList<Int>()
    var allMoves = ArrayList<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun takeTurn(view: View){
        var selectedPosition = view as Button
        var cellId = -1

        when(selectedPosition){
            pos_1 -> cellId = 1
            pos_2 -> cellId = 2
            pos_3 -> cellId = 3
            pos_4 -> cellId = 4
            pos_5 -> cellId = 5
            pos_6 -> cellId = 6
            pos_7 -> cellId = 7
            pos_8 -> cellId = 8
            pos_9 -> cellId = 9
        }

        allMoves.add(selectedPosition)
        takeTurn(cellId, selectedPosition)
    }

    fun takeTurn(cellId: Int, selectedPosition: Button){

        if(playerTurn == 1){
            selectedPosition.text = "X"
            playerTurn = 2
            playerOneMoves.add(cellId)
            winner = checkForWinner(playerOneMoves, 1)
        }else{
            selectedPosition.text = "O"
            playerTurn = 1
            playerTwoMoves.add(cellId)
            winner = checkForWinner(playerTwoMoves, 2)
        }

        selectedPosition.isClickable = false
        if(winner > 0){
            resetGame("PLAYER " + winner + " WINS")
        }else if(totalNumOfTurns < 8){
            totalNumOfTurns++
        }else{
            totalNumOfTurns = 0
            resetGame("DRAW!")
        }
    }

    fun resetGame(message: String){
        AlertDialog.Builder(this@MainActivity)
            ?.setTitle("GAME OVER")
            ?.setMessage(message )
            ?.setCancelable(true)
            ?.setPositiveButton("OK") { dialog, _ ->
                resetButtons()
                playerOneMoves.clear()
                playerTwoMoves.clear()
                winner = 0
                totalNumOfTurns = 0
                playerTurn = 1
                dialog.dismiss() }
            ?.create()
            ?.show()
    }

    fun resetButtons(){
        for(button in allMoves){
            button.isClickable = true
            button.text = ""
        }
        allMoves.clear()
    }

    fun checkForWinner(playerMoves: ArrayList<Int>, player: Int): Int{
        //Row One
        if(playerMoves.contains(1) && playerMoves.contains(2) && playerMoves.contains(3)){
            return player
        }
        //Row Two
        if(playerMoves.contains(4) && playerMoves.contains(5) && playerMoves.contains(6)){
            return player
        }
        //Row Three
        if(playerMoves.contains(7) && playerMoves.contains(8) && playerMoves.contains(9)){
            return player
        }

        //Column One
        if(playerMoves.contains(1) && playerMoves.contains(4) && playerMoves.contains(7)){
            return player
        }
        //Column Two
        if(playerMoves.contains(2) && playerMoves.contains(5) && playerMoves.contains(8)){
            return player
        }
        //Column Three
        if(playerMoves.contains(3) && playerMoves.contains(6) && playerMoves.contains(9)){
            return player
        }

        //Diagonal One
        if(playerMoves.contains(1) && playerMoves.contains(5) && playerMoves.contains(9)){
            return player
        }
        //Diagonal Two
        if(playerMoves.contains(3) && playerMoves.contains(5) && playerMoves.contains(7)){
            return player
        }

        return winner
    }
}
