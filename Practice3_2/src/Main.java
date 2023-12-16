import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    private static void runApplication(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public static void main(String[] args) {
        runApplication(args);
    }
}