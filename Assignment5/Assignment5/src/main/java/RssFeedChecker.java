import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * RSSFeedChecker class. Implements the Runnable interface and checks an RSS feed URL
 * for new items every 10 seconds. Keeps track of seen items to avoid repetition.
 */
public class RSSFeedChecker implements Runnable {
    private final String feedUrl;
    private final Set<RSSItem> seenItems;

    /**
     * Constructs an RSSFeedChecker for a feed URL.
     *
     * @param feedUrl the URL of the RSS feed.
     */
    public RSSFeedChecker(String feedUrl) {
        this.feedUrl = feedUrl;
        this.seenItems = new HashSet<>();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                checkFeed();
                Thread.sleep(10000); // 10 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * Checks the RSS feed for new items and prints them if they are new.
     */
    public void checkFeed() {
        try {
            URL url = new URL(feedUrl);
            // XML Document building
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url.openStream());

            // This is how you work with XML - you do not need to modify this!
            NodeList itemList = doc.getElementsByTagName("item");
            List<RSSItem> newItems = new ArrayList<>();

            for (int i = 0; i < itemList.getLength(); i++) {
                Node itemNode = itemList.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    String title = itemElement.getElementsByTagName("title").item(0).getTextContent();
                    String link = itemElement.getElementsByTagName("link").item(0).getTextContent();
                    String pubDate = itemElement.getElementsByTagName("pubDate").item(0).getTextContent();
                    RSSItem newItem = new RSSItem(title, link, pubDate);
                    if (!seenItems.contains(newItem)) {
                        newItems.add(newItem);
                        seenItems.add(newItem);
                    }
                }
            }

            synchronized (System.out) {
                System.out.println("Feed: " + feedUrl);
                for (int i = 0; i < Math.min(3, newItems.size()); i++) {
                    RSSItem item = newItems.get(i);
                    System.out.println("Title: " + item.getTitle());
                    System.out.println("Link: " + item.getLink());
                    System.out.println("Published: " + item.getPubDate());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



