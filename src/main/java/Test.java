import com.prince.server.http.HttpServer;

/**
 * Created by zidong.wang on 2016/8/23.
 */
public class Test {
    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.listen(8080);
    }
}
