package mc.minigame.game;

import org.bukkit.entity.Player;

public class GameStart {

    PlayerMoney playerMoney = new PlayerMoney();
    DisplayBoard displayBoard = new DisplayBoard();

    public GameStart() {
    }

    public void clearCoins() {
        playerMoney.clearCoins();
    }

    public void onStart(Player player) {
        playerMoney.setCoins(player, 0);
        displayBoard.board(player);
    }

}