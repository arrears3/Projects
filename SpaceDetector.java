import java.util.Random;

public class SpaceDetector {

    public static void main(String[] args) {
        // Simulate sensor readings
        double oxygenLevel = generateRandomDouble(0.0, 1.0);
        double humidityLevel = generateRandomDouble(0.0, 100.0);
        boolean hasPlants = generateRandomBoolean();

        // Analyze sensor data
        System.out.println("Sensor Readings:");
        System.out.println("Oxygen Level: " + oxygenLevel);
        System.out.println("Humidity Level: " + humidityLevel);
        System.out.println("Presence of Plants: " + (hasPlants ? "Yes" : "No"));

        // Detect air and plants
        if (oxygenLevel > 0.5) {
            System.out.println("Air detected!");
        } else {
            System.out.println("No air detected.");
        }

        if (hasPlants && humidityLevel > 50.0) {
            System.out.println("Plants detected!");
        } else {
            System.out.println("No plants detected.");
        }
    }

    // Generate a random double within a specified range
    private static double generateRandomDouble(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    // Generate a random boolean (true or false)
    private static boolean generateRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
