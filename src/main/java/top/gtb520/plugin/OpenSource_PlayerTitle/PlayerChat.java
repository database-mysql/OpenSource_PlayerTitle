package top.gtb520.plugin.OpenSource_PlayerTitle;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

import static top.gtb520.plugin.OpenSource_PlayerTitle.unity.tools.ColorMessage;

public class PlayerChat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String Message = event.getMessage();
        Player EventPlayer = event.getPlayer();
        String PlayerName = " <" + EventPlayer.getName() + "> ";
        String PlayerTitle;
        if (main.instance.getConfig().getString(event.getPlayer().getName() + "_Title") != null) {
            PlayerTitle = ColorMessage("&f[" + main.instance.getConfig().getString(event.getPlayer().getName() + "_Title") + "&f]");
        }else {
            if (Objects.equals(main.instance.getConfig().getString("Player.DefeatTitle"), "")) {
                PlayerTitle = ColorMessage("&f[" + main.instance.getConfig().getString("Player.DefeatTitle") + "&f]");
            }else {
                PlayerTitle = "";
            }
        }
        String PlayerSuffix;
        if (main.instance.getConfig().getString(event.getPlayer().getName() + "_Suffix") != null) {
            PlayerSuffix = ColorMessage("&f[" + main.instance.getConfig().getString(event.getPlayer().getName() + "_Suffix") + "&f] ");
        }else {
            if (!Objects.equals(main.instance.getConfig().getString("Player.DefeatSuffix"), "")) {
                PlayerSuffix = ColorMessage("&f[" + main.instance.getConfig().getString("Player.DefeatSuffix") + "&f] ");
            }else {
                PlayerSuffix = "";
            }
        }
        event.setFormat(PlayerTitle + PlayerName + PlayerSuffix + Message);
    }
}
