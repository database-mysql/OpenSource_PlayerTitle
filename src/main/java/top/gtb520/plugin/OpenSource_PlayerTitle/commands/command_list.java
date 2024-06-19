package top.gtb520.plugin.OpenSource_PlayerTitle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class command_list implements TabCompleter {
    private static List<String> CommandList = new ArrayList<>();
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        CommandList.add("help");
        CommandList.add("reload");
        CommandList.add("giveplayertitle");
        CommandList.add("giveplayersuffix");
        if (args.length == 0 || args.length == 1) {
            return CommandList;
        }
        return null;
    }
}
