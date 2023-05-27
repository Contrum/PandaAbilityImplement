package org.contrum.ability;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.contrum.ability.impl.NoneImplementation;
import org.contrum.ability.util.ClassUtil;

import java.util.concurrent.atomic.AtomicReference;


public class CoreLoader {
    private final JavaPlugin plugin;

    public CoreLoader(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public CoreAdapter load() {
        AtomicReference<CoreAdapter> adapterRef = new AtomicReference(new NoneImplementation());

        ClassUtil.getClassesInPackage(this.plugin, "org.contrum.ability.impl").stream().filter(this::isAdapter).forEach(clazz -> {
            try {
                CoreAdapter adapter = (CoreAdapter) clazz.getConstructor().newInstance();

                if (adapter.getPluginName() != null && Bukkit.getPluginManager().getPlugin(adapter.getPluginName()) != null) {
                    adapterRef.set(adapter);
                }
            } catch (Exception e) {
                Bukkit.getLogger().info("Exception occured while loading a compatible core for PandaAbility, send this to a developer: " + e.getLocalizedMessage());
            }
        });

        return adapterRef.get();
    }

    private boolean isAdapter(Class<?> clazz) {
        return CoreAdapter.class.isAssignableFrom(clazz);
    }
}