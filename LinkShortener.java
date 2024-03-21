import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private Map<String, String> shortToLongUrlMap;
    private Map<String, String> longToShortUrlMap;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    public LinkShortener() {
        shortToLongUrlMap = new HashMap<>();
        longToShortUrlMap = new HashMap<>();
    }

    public String shortenUrl(String longUrl) {
        if (longToShortUrlMap.containsKey(longUrl)) {
            return longToShortUrlMap.get(longUrl);
        }

        String shortUrl;
        do {
            shortUrl = generateRandomString(SHORT_URL_LENGTH);
        } while (shortToLongUrlMap.containsKey(shortUrl));

        shortToLongUrlMap.put(shortUrl, longUrl);
        longToShortUrlMap.put(longUrl, shortUrl);
        return shortUrl;
    }

    public String expandUrl(String shortUrl) {
        return shortToLongUrlMap.getOrDefault(shortUrl, "Short URL not found");
    }

    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        String longUrl = "https://www.google.co.in/";
        String shortUrl = linkShortener.shortenUrl(longUrl);
        System.out.println("Shortened URL: " + shortUrl);
        String expandedUrl = linkShortener.expandUrl(shortUrl);
        System.out.println("Expanded URL: " + expandedUrl);
    }
}
