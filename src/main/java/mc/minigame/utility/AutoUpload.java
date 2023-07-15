package mc.minigame.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.net.URL;

import static mc.minigame.Zombies.zombies;

public class AutoUpload {
    private static final String RELEASE_URL = "https://api.github.com/repos/NewAmazingPVP/Zombies-Minigame/releases/latest";
    private static int currentNumber = 0;
    private static int releaseNumber;

    public static void startReleaseChecker() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    checkForNewRelease();
                } catch (Exception e) {}
            }
        }.runTaskTimerAsynchronously(zombies, 0L, 1L);
    }

    private static void checkForNewRelease(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(new URL(RELEASE_URL));

            String downloadUrl = null;
            for (JsonNode asset : node.get("assets")) {
                if (asset.has("name") && asset.get("name").asText().endsWith(".jar")) {
                    downloadUrl = asset.get("browser_download_url").asText();
                    break;
                }
            }
            for (JsonNode asset : node) {
                if (asset.has("tag_name")) {
                    String tagName = asset.get("tag_name").asText();
                    String releaseVersion = tagName.replace("v", "");
                    releaseNumber = Integer.parseInt(releaseVersion);
                    break;
                }
            }
            if(releaseNumber > currentNumber){
                updatePlugin(downloadUrl);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePlugin(String link) {
        String outputFilePath = "plugins/";

        try (InputStream in = new URL(link).openStream();
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            currentNumber = releaseNumber;
        } catch (IOException e) {
            System.out.println("Failed to download plugin: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
