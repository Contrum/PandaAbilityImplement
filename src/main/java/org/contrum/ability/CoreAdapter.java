package org.contrum.ability;

import org.bukkit.entity.Player;

import java.util.List;

public interface CoreAdapter {
    String getPluginName();

    boolean isSafeZone(Player paramPlayer);

    List<Player> getTeammates(Player paramPlayer);
}