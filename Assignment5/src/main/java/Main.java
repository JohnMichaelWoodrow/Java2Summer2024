import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class. Entry point of the program. It collects RSS feed URLs from the user,
 * starts a thread for each RSS feed checker, and ensures proper shutdown of all threads.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> feedUrls = new ArrayList<>();
        final String end = "done";

        System.out.println("Enter RSS Feed URLs (type 'done' to exit): ");
        String url;
        do {
            url = scanner.nextLine();
            if (url.equalsIgnoreCase(end)) {
                break;
            }
            feedUrls.add(url);
        } while (!url.equalsIgnoreCase(end));

        List<Thread> threads = new ArrayList<>();

        for (String feedUrl : feedUrls) {
            RSSFeedChecker checker = new RSSFeedChecker(feedUrl);
            Thread thread = new Thread(checker);
            thread.start();
            threads.add(thread);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down...");
            for (Thread thread : threads) {
                thread.interrupt();
            }
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Shutdown complete.");
        }));

        System.out.println("Started RSS feed checking. Press Ctrl+C to exit.");
    }
}

// Constant RSS Feed (updates every minute) https://lorem-rss.herokuapp.com/feed