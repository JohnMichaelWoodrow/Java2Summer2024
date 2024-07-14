import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssFeedChecker implements Runnable {
    private final String feedUrl;

    public RssFeedChecker(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(feedUrl);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url.openStream());

            NodeList itemList = doc.getElementsByTagName("item");
            List<RssItem> items = new ArrayList<>();

            for (int i = 0; i < itemList.getLength(); i++) {
                Node itemNode = itemList.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    String title = itemElement.getElementsByTagName("title").item(0).getTextContent();
                    String link = itemElement.getElementsByTagName("link").item(0).getTextContent();
                    String pubDate = itemElement.getElementsByTagName("pubDate").item(0).getTextContent();
                    items.add(new RssItem(title, link, pubDate));
                }
            }

            synchronized (System.out) {
                System.out.println("Feed: " + feedUrl);
                for (int i = 0; i < Math.min(3, items.size()); i++) {
                    RssItem item = items.get(i);
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

    private static class RssItem {
        private final String title;
        private final String link;
        private final String pubDate;

        public RssItem(String title, String link, String pubDate) {
            this.title = title;
            this.link = link;
            this.pubDate = pubDate;
        }

        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }

        public String getPubDate() {
            return pubDate;
        }
    }
}
