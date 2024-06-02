package at.fhtw.routplanner.Service;

import java.io.FileWriter;
import java.io.IOException;

public class DirectionMapFileWriter {
    public static void writeToFile(at.fhtw.routplanner.model.Tour tour) {

        String start = tour.getLatStartPoint().toString()+","+tour.getLongStartPoint().toString();
        String end = tour.getLatEndPoint().toString()+","+tour.getLongEndPoint().toString();

        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Leaflet Map with Directions</title>\n" +
                "    <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.9.4/dist/leaflet.css\"\n" +
                "          integrity=\"sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=\"\n" +
                "          crossorigin=\"\"/>\n" +
                "    <script src=\"https://unpkg.com/leaflet@1.9.4/dist/leaflet.js\"\n" +
                "            integrity=\"sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=\"\n" +
                "            crossorigin=\"\"></script>\n" +
                "    <script>\n" +
                "        document.addEventListener('DOMContentLoaded', (event) => {\n" +
                "            const apiKey = '5b3ce3597851110001cf624828d13c2fba8d4a7bbd0e73e720a65b4b';\n" +
                "            const start = \"" + start + "\";\n" +
                "            const end = \"" + end + "\";\n" +
                "            const url = `https://api.openrouteservice.org/v2/directions/driving-car?api_key=${apiKey}&start=${start}&end=${end}`;\n" +
                "\n" +
                "            async function getDirections() {\n" +
                "                try {\n" +
                "                    const response = await fetch(url);\n" +
                "                    if (!response.ok) {\n" +
                "                        throw new Error(`HTTP error! status: ${response.status}`);\n" +
                "                    }\n" +
                "                    const directions = await response.json();\n" +
                "                    console.log(directions);\n" +
                "\n" +
                "                    // Initialize the map after fetching directions\n" +
                "                    var map = L.map('map');\n" +
                "                    var bbox = directions.bbox;\n" +
                "                    map.fitBounds([[bbox[1], bbox[0]], [bbox[3], bbox[2]]]);\n" +
                "                    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {\n" +
                "                        attribution: '2024 © FH Technikum Wien'\n" +
                "                    }).addTo(map);\n" +
                "                    L.geoJSON(directions).addTo(map);\n" +
                "\n" +
                "                } catch (error) {\n" +
                "                    console.error('Error fetching directions:', error);\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "            getDirections();\n" +
                "        });\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0;\">\n" +
                "<div id=\"map\" style=\"height: 100vh\"></div>\n" +
                "</body>\n" +
                "</html>";

        try (FileWriter fileWriter = new FileWriter("src/main/resources/at/fhtw/routplanner/html/leaflet.html")) {
            fileWriter.write(htmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
