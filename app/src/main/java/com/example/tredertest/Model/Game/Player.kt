package com.example.tredertest.Model.Game

import android.graphics.Color
import android.os.Bundle

class Player(val sign: String, val color: Int, val name: String) {
    private var playerMoves = HashSet<Int>()
    lateinit var oponentName: String
    companion object {
        private val winCombinations = ArrayList<HashSet<Int>>()

        fun getWinCombinations(): ArrayList<HashSet<Int>> {
            if (winCombinations.isEmpty()) {
                for (i in 11..33 step 10) {
                    winCombinations.add(HashSet<Int>().apply{
                        add(i)
                        add(i + 1)
                        add(i + 2)
                    })
                }
                for (i in 11..13) {
                    winCombinations.add(HashSet<Int>().apply{
                        add(i)
                        add(i + 10)
                        add(i + 20)
                    })
                }
                winCombinations.add(HashSet<Int>().apply {
                    add(11)
                    add(22)
                    add(33)
                })
                winCombinations.add(HashSet<Int>().apply {
                    add(13)
                    add(22)
                    add(31)
                })
            }
            return winCombinations
        }
    }

    fun checkWin(): Boolean {
        for (combination in getWinCombinations()) {
            if (playerMoves.containsAll(combination)) {
                return true
            }
        }
        return false
    }

    fun move(position: Int): Bundle {
        playerMoves.add(position)
        var bundle = Bundle()
        bundle.putString("sign", this.sign)
        bundle.putInt("color", this.color)
        bundle.putString("name", this.name)
        bundle.putBoolean("isWin", checkWin())
        bundle.putString("oponentName", oponentName)
        return bundle
    }
}

fun main() {
    var player = Player("O", Color.BLUE, "#1")
    println(Player.getWinCombinations())
    player.move(12)
    player.move(22)
    player.move(31)
    player.move(21)
//    player.move(11)
    println(player.checkWin())
}