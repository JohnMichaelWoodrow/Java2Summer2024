/**
 * RSSItem class represents an item in an RSS feed. Contains the title, link, and publication date of the item.
 */
public class RSSItem {
    private final String title;
    private final String link;
    private final String pubDate;

    /**
     * Constructs an RSSItem with a title, link, and publication date.
     *
     * @param title   Title of the RSS item.
     * @param link    Link to the RSS item.
     * @param pubDate Publication date of the RSS item.
     */
    public RSSItem(String title, String link, String pubDate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RSSItem rssItem = (RSSItem) o;

        return link.equals(rssItem.link);
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }
}

