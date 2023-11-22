import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String[] strings = { "Рядок 1", "Рядок 2", "Рядок 3", "Рядок 4", "Рядок 5" };

        moveStrings(strings);
    }

    public static void moveStrings(String[] strings) {
        Random random = new Random();
        int frameWidth = 100;

        int[] positions = new int[strings.length];

        while (true) {
            for (int i = 0; i < strings.length; i++) {
                StringBuilder spaces = new StringBuilder();
                for (int j = 0; j < positions[i]; j++) {
                    spaces.append(" ");
                }
                System.out.println(spaces.toString() + strings[i]);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < strings.length; i++) {
                positions[i] = (positions[i] + random.nextInt(5)) % frameWidth; // Зміна позиції випадковим чином
            }
        }
    }
}
