import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger three_length = new AtomicInteger();
    public static AtomicInteger four_length = new AtomicInteger();
    public static AtomicInteger five_length = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        threads.add(new Thread(() -> {
            palindromeCheck(texts);
        }));
        threads.add(new Thread(() -> {
            oneCharacter(texts);
        }));
        threads.add(new Thread(() -> {
            symbolAscending(texts);
        }));
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Красивых слов с длиной 3: " + three_length);
        System.out.println("Красивых слов с длиной 4: " + four_length);
        System.out.println("Красивых слов с длиной 5: " + five_length);
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void palindromeCheck(String[] text) {
        for (int i = 0; i < text.length; i++) {
            //System.out.println(text[i]);
            for (int j = 0; j < text[i].length(); j++) {
                if (text[i].charAt(j) != text[i].charAt(text[i].length() - j - 1)) {
                    break;
                }
                if (j == text[i].length() - 1) {
                    switch (text[i].length()) {
                        case 3:
                            three_length.incrementAndGet();
                            break;
                        case 4:
                            four_length.incrementAndGet();
                            break;
                        case 5:
                            five_length.incrementAndGet();
                            break;
                    }
                }
            }
        }
    }

    public static void oneCharacter(String[] text) {
        for (int i = 0; i < text.length; i++) {
            char oneSymbol = text[i].charAt(0);
            for (int j = 1; j < text[i].length(); j++) {
                if (oneSymbol != text[i].charAt(j)) {
                    break;
                }
                if (j == text[i].length() - 1) {
                    switch (text[i].length()) {
                        case 3:
                            three_length.incrementAndGet();
                            break;
                        case 4:
                            four_length.incrementAndGet();
                            break;
                        case 5:
                            five_length.incrementAndGet();
                            break;
                    }
                }
            }
        }
    }

    public static void symbolAscending(String[] text) {
        for (int i = 0; i < text.length; i++) {
            for (int j = 0; j < text[i].length() - 1; j++) {
                if (text[i].charAt(j) > text[i].charAt(j + 1)) {
                    break;
                }
                if (j == text[i].length() - 2) {
                    switch (text[i].length()) {
                        case 3:
                            three_length.incrementAndGet();
                            break;
                        case 4:
                            four_length.incrementAndGet();
                            break;
                        case 5:
                            five_length.incrementAndGet();
                            break;
                    }
                }
            }
        }
    }

}
