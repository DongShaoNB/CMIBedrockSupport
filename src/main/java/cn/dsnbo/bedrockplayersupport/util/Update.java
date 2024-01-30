package cn.dsnbo.bedrockplayersupport.util;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import com.tcoded.folialib.FoliaLib;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @author DongShaoNB
 */
public class Update {
    private static final int spigotResourceId = 1145141919; // Spigot resource id
    public static void checkUpdate(Consumer<String> consumer) {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            FoliaLib foliaLib = new FoliaLib(BedrockPlayerSupport.getInstance());
            foliaLib.getImpl().runAsync((task) -> getVersion(consumer));
        } catch (ClassNotFoundException e) {
            Bukkit.getScheduler().runTaskAsynchronously(BedrockPlayerSupport.getInstance(), () -> getVersion(consumer));
        }
    }

    public static void getVersion(final Consumer<String> consumer) {
        try (InputStream is = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + spigotResourceId + "/~").openStream(); Scanner scann = new Scanner(is)) {
            if (scann.hasNext()) {
                consumer.accept(scann.next());
            }
        } catch (IOException e) {
            BedrockPlayerSupport.getInstance().getLogger().info("Unable to check for updates: " + e.getMessage());
        }
    }}

