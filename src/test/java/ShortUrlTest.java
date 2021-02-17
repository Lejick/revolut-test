import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class ShortUrlTest {

    private static String BASE_URL_DEFAULT = "http://short.com";


    @Test
    public void test_case_success_1() {
        String originalUrl = "http://looooong.com/somepath";
        String seoWord = "MY-NEW-WS";

        SeoShortUrlService shortUrlService = new SeoShortUrlService(URI.create(BASE_URL_DEFAULT));
        String result = shortUrlService.createShortUrl(originalUrl, seoWord);

        assertEquals("http://short.com/MY-NEW-WS", result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"MY-NEW-WS",})
    public void test_case_success_2() {
        String originalUrl = "http://looooong.com/somepath";
        String seoWord = "POTATO";

        SeoShortUrlService shortUrlService = new SeoShortUrlService(URI.create(BASE_URL_DEFAULT));
        String result = shortUrlService.createShortUrl(originalUrl, seoWord);

        assertEquals("http://short.com/POTATO", result);
    }

    @Test
    public void test_case_more_20() {
        String originalUrl = "http://looooong.com/somepath";
        String seoWord = "POTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATO";

        SeoShortUrlService shortUrlService = new SeoShortUrlService(URI.create(BASE_URL_DEFAULT));
        assertThrows(IllegalArgumentException.class, () -> shortUrlService.createShortUrl(originalUrl, seoWord));
    }


    @Test
    public void test_case_wrong_uri() throws URISyntaxException {
        String originalUrl = "http://looooong.com/somepath";
        String seoWord = "POTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATOPOTATO";

        SeoShortUrlService shortUrlService = new SeoShortUrlService(new URI("random"));
        assertThrows(IllegalArgumentException.class, () -> shortUrlService.createShortUrl(originalUrl, seoWord));
    }

    @Test
    public void rand_seq_generator() {
        RandomSeqGenerator randomSeqGenerator = new RandomSeqGenerator();
        String seq1 = randomSeqGenerator.generate();
        String seq2 = randomSeqGenerator.generate();
        assertNotEquals(seq1, seq2);
    }

    @Test
    public void rand_seq_generator_lengh() {
        RandomSeqGenerator randomSeqGenerator = new RandomSeqGenerator();
        String seq1 = randomSeqGenerator.generate();
        assertEquals(4, seq1.length());
    }

    @Test
    public void rand_seq_service() {
        String originalUrl = "http://looooong.com/somepath";
        ShortUrlService randomShortUrlService = new ShortUrlService(URI.create(BASE_URL_DEFAULT),new RandomSeqGenerator());
        String short1 = randomShortUrlService.generateUrl(originalUrl);
        String short2 = randomShortUrlService.generateUrl(originalUrl);
        assertNotEquals(short1, short2);
    }

    @Test
    public void rand_seq_service_length() {
        String originalUrl = "http://looooong.com/somepath";
        ShortUrlService randomShortUrlService = new ShortUrlService(URI.create(BASE_URL_DEFAULT),new RandomSeqGenerator());
        String short1 = randomShortUrlService.generateUrl(originalUrl);
        String short2 = randomShortUrlService.generateUrl(originalUrl);
        assertEquals("http://short.com/ZfGd".length(), short2.length());
        assertEquals("http://short.com/ZfGd".length(), short1.length());
    }

    @Test
    public void incremental_seq_generator() {
        SequenceGenerator sequenceGenerator = new IncrementalSequenceGenerator();
        String url1 = sequenceGenerator.generate();
        String url2 = sequenceGenerator.generate();
        assertEquals("1", url1);
        assertEquals("2", url2);
    }

    @Test
    public void incremental_service_success() {
        String originalUrl="http://looooong.com/somepath";
        ShortUrlService shortUrlService=new ShortUrlService(URI.create(BASE_URL_DEFAULT),new IncrementalSequenceGenerator());
       String url1= shortUrlService.generateUrl(originalUrl);
        String url2= shortUrlService.generateUrl(originalUrl);
        assertEquals("http://short.com/1", url1);
        assertEquals("http://short.com/2", url2);
    }
}


/*
Given two different instances of the service, the second one must use a different policy for the automatic keyword generation: an incremental integer from 1 to N

Example:

Input:
URL: http://looooong.com/somepath
Output:
URL: http://short.com/1
 */