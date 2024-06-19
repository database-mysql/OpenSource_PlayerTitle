package top.gtb520.plugin.OpenSource_PlayerTitle.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import top.gtb520.plugin.OpenSource_PlayerTitle.main;

import java.util.Objects;

import static top.gtb520.plugin.OpenSource_PlayerTitle.unity.tools.ColorMessage;

public class title implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, String label, String[] args) {
        String helpTitle = ColorMessage("&f-----------&eOpenSourceTitle&f-----------");
        String helpSuffix = ColorMessage("&f-----------&e当前页数&f:&a1&f-----------");
        String nopermission = ColorMessage("&d错误你没有权限执行这个命令");

        String help12 = ColorMessage("&f/" + command.getName() + " help &7<页数>&f &e打开帮助页面");
        String help13 = ColorMessage("&f/" + command.getName() + " reload &f &e重载配置文件");
        String help14 = ColorMessage("&f/" + command.getName() + " giveplayertitle &a[称号] &7<玩家ID> &f &e给予玩家称号");
        String help15 = ColorMessage("&f/" + command.getName() + " giveplayersuffix &a[后缀] &7<玩家ID> &f &e给予玩家后缀");

        if (args.length == 1) {
            String commands1 = args[0];
            if (Objects.equals(commands1, "help")) {
                sender.sendMessage(helpTitle);

                sender.sendMessage(help12);
                sender.sendMessage(help13);
                sender.sendMessage(help14);
                sender.sendMessage(help15);

                sender.sendMessage(helpSuffix);
                return false;
            }

            if (Objects.equals(commands1, "reload")) {
                if (sender.hasPermission("OpenSourceTitlePlayerTitle.reload")) {
                    main.instance.reloadConfig();
                    sender.sendMessage(ColorMessage("&a配置文件重载成功"));
                    return false;
                } else {
                    sender.sendMessage(nopermission);
                    return false;
                }
            }

            if (Objects.equals(commands1, "giveplayertitle")) {
                if (sender.hasPermission("OpenSourceTitlePlayerTitle.GivePlayerTitle")) {
                    sender.sendMessage(ColorMessage("&d错误,使用方法错误,正确用法:/" + command.getName() + " giveplayertitle &a[称号] &7<玩家ID>"));
                    return false;
                } else {
                    sender.sendMessage(nopermission);
                    return false;
                }
            }

            if (Objects.equals(commands1, "giveplayersuffix")) {
                if (sender.hasPermission("OpenSourceTitlePlayerTitle.GivePlayerSuffix")) {
                    sender.sendMessage(ColorMessage("&d错误,使用方法错误,正确用法:/" + command.getName() + " giveplayersuffix &a[后缀] &7<玩家ID>"));
                } else {
                    sender.sendMessage(nopermission);
                    return false;
                }
            }
        } else {
            if (args.length == 2) {
                String commands1 = args[0];
                String commands2 = args[1];
                if (Objects.equals(commands1, "help")) {
                    helpSuffix = ColorMessage("&f-----------&e当前页数&f:&a" + commands2 + "&f-----------");
                    if (Objects.equals(commands2, "1")) {
                        sender.sendMessage(helpTitle);

                        sender.sendMessage(help12);
                        sender.sendMessage(help13);
                        sender.sendMessage(help14);
                        sender.sendMessage(help15);

                        sender.sendMessage(helpSuffix);
                    } else {
                        sender.sendMessage(ColorMessage("&d错误,这个页数不存在"));
                    }
                    return false;
                }

                if (Objects.equals(commands1, "reload")) {
                    if (sender.hasPermission("OpenSourceTitlePlayerTitle.reload")) {
                        sender.sendMessage(ColorMessage("&d错误,使用方法错误,正确用法:/" + command.getName() + " reload"));
                        return false;
                    } else {
                        sender.sendMessage(nopermission);
                        return false;
                    }
                }

                if (Objects.equals(commands1, "giveplayertitle")) {
                    if (sender.hasPermission("OpenSourceTitlePlayerTitle.GivePlayerTitle")) {
                        if (commands2 == null) {
                            sender.sendMessage(ColorMessage("&d错误,称号不能为空"));
                            return false;
                        } else {
                            String PlayerTitleConfigName = sender.getName() + "_Title";
                            main.instance.getConfig().set(PlayerTitleConfigName, commands2);
                            main.instance.saveConfig();
                            main.instance.reloadConfig();
                            return false;
                        }
                    } else {
                        sender.sendMessage(nopermission);
                        return false;
                    }
                }


                if (Objects.equals(commands1, "giveplayersuffix")) {
                    if (sender.hasPermission("OpenSourceTitlePlayerTitle.GivePlayerSuffix")) {
                        if (commands2 == null) {
                            sender.sendMessage(ColorMessage("&d错误,称号不能为空"));
                            return false;
                        } else {
                            String PlayerTitleConfigName = sender.getName() + "_Suffix";
                            main.instance.getConfig().set(PlayerTitleConfigName, commands2);
                            main.instance.saveConfig();
                            main.instance.reloadConfig();
                            return false;
                        }
                    } else {
                        sender.sendMessage(nopermission);
                        return false;
                    }
                }
            } else {
                if (args.length == 3) {
                    String commands1 = args[0];
                    String commands2 = args[1];
                    String commands3 = args[2];
                    if (Objects.equals(commands1, "help")) {
                        sender.sendMessage(ColorMessage("&d错误,使用方法错误,正确用法:/" + command.getName() + " help &7<页数>"));
                        return false;
                    }

                    if (Objects.equals(commands1, "reload")) {
                        if (sender.hasPermission("OpenSourceTitlePlayerTitle.reload")) {
                            sender.sendMessage(ColorMessage("&d错误,使用方法错误,正确用法:/" + command.getName() + " reload"));
                            return false;
                        } else {
                            sender.sendMessage(nopermission);
                            return false;
                        }
                    }

                    if (Objects.equals(commands1, "giveplayertitle")) {
                        if (sender.hasPermission("OpenSourceTitlePlayerTitle.GivePlayerTitle")) {
                            if (commands2 == null) {
                                sender.sendMessage(ColorMessage("&d错误,称号不能为空"));
                                return false;
                            } else {
                                if (commands3 == null) {
                                    sender.sendMessage(ColorMessage("&d错误,玩家ID不能为空"));
                                    return false;
                                } else {
                                    if (Bukkit.getPlayer(commands3) == null) {
                                        sender.sendMessage(ColorMessage("&d错误,玩家不存在或不在线"));
                                        return false;
                                    } else {
                                        String PlayerTitleConfigName = commands3 + "_Title";
                                        main.instance.getConfig().set(PlayerTitleConfigName, commands2);
                                        main.instance.saveConfig();
                                        main.instance.reloadConfig();
                                        return false;
                                    }
                                }
                            }
                        } else {
                            sender.sendMessage(nopermission);
                            return false;
                        }
                    }

                    if (Objects.equals(commands1, "giveplayersuffix")) {
                        if (sender.hasPermission("OpenSourceTitlePlayerTitle.GivePlayerSuffix")) {
                            if (commands2 == null) {
                                sender.sendMessage(ColorMessage("&d错误,后缀不能为空"));
                                return false;
                            } else {
                                if (commands3 == null) {
                                    sender.sendMessage(ColorMessage("&d错误,玩家ID不能为空"));
                                    return false;
                                } else {
                                    if (Bukkit.getPlayer(commands3) == null) {
                                        sender.sendMessage(ColorMessage("&d错误,玩家不存在或不在线"));
                                        return false;
                                    } else {
                                        String PlayerTitleConfigName = commands3 + "_Suffix";
                                        main.instance.getConfig().set(PlayerTitleConfigName, commands2);
                                        main.instance.saveConfig();
                                        main.instance.reloadConfig();
                                        return false;
                                    }
                                }
                            }
                        } else {
                            sender.sendMessage(nopermission);
                            return false;
                        }
                    }

                } else {
                    sender.sendMessage(helpTitle);

                    sender.sendMessage(help12);
                    sender.sendMessage(help13);
                    sender.sendMessage(help14);
                    sender.sendMessage(help15);

                    sender.sendMessage(helpSuffix);
                }
            }
        }
        return false;
    }
}
