package com.example.tredertest.ViewModel

import android.graphics.Color
import android.os.Bundle
import com.example.tredertest.Model.Game.Player

class GameViewModel {
    var playerCross =
        Player("X", Color.BLUE, "Cross player")
    var playerNought =
        Player("O", Color.RED, "Nought player")
    var instantPlayer = playerCross

    init {
        playerCross.oponentName = playerNought.name
        playerNought.oponentName = playerCross.name
    }

    fun move(position: Int): Bundle {
        var result = instantPlayer.move(position)
        instantPlayer = if (instantPlayer == playerCross) playerNought else playerCross
        return result
    }

}