package mc.minigame.game;

import org.bukkit.entity.Player;

public class GameStart {


    public void clearCoins() {
        PlayerMoney.clearCoins();
    }

    public void onStart(Player player) {
        DisplayBoard.board(player);
    }
}
