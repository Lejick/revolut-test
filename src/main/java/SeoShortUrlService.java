import java.net.URI;

public class SeoShortUrlService {
private final URI baseUrl;
private int MAX_SIZE=20;
    public SeoShortUrlService(URI baseUrl) {
        this.baseUrl=baseUrl;
    }

    public String createShortUrl(String originalUrl, String seoWord) {
        if(seoWord.length()>MAX_SIZE){
            throw new IllegalArgumentException();
        }
        return baseUrl+"/"+seoWord;
    }
}
