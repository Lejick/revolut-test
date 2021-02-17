import java.net.URI;

public class ShortUrlService {
    private final URI baseUrl;

private  SequenceGenerator seqGenerator;


    public ShortUrlService(URI baseUrl,SequenceGenerator seqGenerator) {
        this.baseUrl=baseUrl;
        this.seqGenerator=seqGenerator;

    }

    public String generateUrl(String originalUrl) {
        return baseUrl+"/"+seqGenerator.generate();
    }
}
