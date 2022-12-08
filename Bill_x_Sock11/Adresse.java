import java.net.InetAddress;
import java.net.UnknownHostException;

public class Adresse{
    public static void main(String[] args) throws UnknownHostException {
        InetAddress add = InetAddress.getLocalHost();
        System.out.println(add);
    }
}