import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> feedUrls = new ArrayList<>();
        final String end = "done";

        System.out.println("Enter RSS Feed URLs (type 'done' to exit): ");
        while (true) {
            String url = scanner.nextLine();
            if (url.equalsIgnoreCase(end)) {
                break;
            }
            feedUrls.add(url);
        }

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(feedUrls.size());

        for (String feedUrl : feedUrls) {
            RssFeedChecker checker = new RssFeedChecker(feedUrl);
            executorService.scheduleAtFixedRate(checker, 0, 10, TimeUnit.SECONDS);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down executor service...");
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("Forcing shutdown...");
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }));

        System.out.println("RSS feed checking started. Press Ctrl+C to exit.");
    }
}
