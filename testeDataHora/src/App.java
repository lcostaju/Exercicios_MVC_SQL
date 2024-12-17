import java.sql.Date;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        LocalDateTime agora = LocalDateTime.now();

        System.out.println(agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
}
