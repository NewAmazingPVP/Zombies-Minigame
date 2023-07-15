package mc.minigame.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.scheduler.BukkitRunnable;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static mc.minigame.Zombies.zombies;

public class AutoUpload {
    private static final String RELEASE_URL = "https://api.github.com/repos/NewAmazingPVP/Zombies-Minigame/releases/latest";
    private static final String API_KEY_FILE_PATH = zombies.getDataFolder().getPath() + File.separator + "api.yml";
    private static String apiKey;
    private static int currentNumber = 0;
    private static int releaseNumber;

    public static void startReleaseChecker() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    checkForNewRelease();
                } catch (Exception ignored) {
                }
            }
        }.runTaskTimerAsynchronously(zombies, 0L, 18L);
    }

    private static synchronized void updateCurrentNumber(int newNumber) {
        currentNumber = newNumber;
    }

    private static synchronized int getCurrentNumber() {
        return currentNumber;
    }

    private static synchronized void updateReleaseNumber(int newNumber) {
        releaseNumber = newNumber;
    }

    private static synchronized int getReleaseNumber() {
        return releaseNumber;
    }

    private static void loadApiKey() {
        try {
            File file = new File(API_KEY_FILE_PATH);
            if (!file.exists()) {
                zombies.getLogger().info("api.yml file not found. Please create the file and add your API key.");
                return;
            }

            Yaml yaml = new Yaml();
            Map<String, String> data = yaml.load(new FileInputStream(file));
            apiKey = data.get("api_key");
        } catch (FileNotFoundException e) {
            zombies.getLogger().info("Failed to load api.yml file: " + e.getMessage());
        }
    }

    private static synchronized String getApiKey() {
        if (apiKey == null) {
            loadApiKey();
        }
        return apiKey;
    }

    private static void checkForNewRelease() {
        try {
            URL url = new URL(RELEASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Token " + getApiKey());

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode node = objectMapper.readTree(response.toString());

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
                        updateReleaseNumber(Integer.parseInt(releaseVersion));
                        break;
                    }
                }
                if (getReleaseNumber() > getCurrentNumber() && downloadUrl != null) {
                    zombies.getLogger().info("New plugin release available. Updating plugin...");
                    updatePlugin(downloadUrl);
                }
            } else {
                zombies.getLogger().info("Failed to check for new releases. Response code: " + connection.getResponseCode());
            }

            connection.disconnect();

        } catch (IOException e) {
            zombies.getLogger().info("Failed to check for new releases: " + e.getMessage());
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
            updateCurrentNumber(getReleaseNumber());
            zombies.getLogger().info("Plugin updated successfully.");
        } catch (IOException e) {
            zombies.getLogger().info("Failed to download plugin: " + e.getMessage());
        }
    }
}
