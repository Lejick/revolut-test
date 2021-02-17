import java.util.Random;

public class RandomSeqGenerator implements SequenceGenerator{
    final static String ALPHABET="ABCDIFGHIJKLMNOPQRSTUVWXYZ";
    final static String ALPHABET_LOW="ABCDIFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
    final static String NUMERIC="0123456789";
    final static char[] source_char=(ALPHABET+ALPHABET_LOW+NUMERIC).toCharArray();
    final static int LENGTH_DEFAULT=4;

    public String generate() {
        StringBuilder sb=new StringBuilder();
        Random random=new Random();
        for(int i=0;i<LENGTH_DEFAULT;i++){
            sb.append(source_char[random.nextInt(source_char.length)]);
        }
        return sb.toString();
    }
}
