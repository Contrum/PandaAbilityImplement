package org.contrum.ability.impl;

import org.bukkit.entity.Player;
import org.contrum.ability.CoreAdapter;

import java.util.Collections;
import java.util.List;


public class NoneImplementation
        implements CoreAdapter {
    public String getPluginName() {
        return null;
    }


    public boolean isSafeZone(Player player) {
        return false;
    }


    public List<Player> getTeammates(Player player) {
        return Collections.EMPTY_LIST;
    }
}
