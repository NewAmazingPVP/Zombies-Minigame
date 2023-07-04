package mc.minigame.game;

import org.bukkit.entity.Player;

public class GameStart {


    public void clearCoins() {
        PlayerMoney.clearCoins();
    }

    public void onStart(Player player) {
        PlayerMoney.setCoins(player, 0);
        DisplayBoard.board(player);
    }
}
