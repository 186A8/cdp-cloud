package top.fosin.anan;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import top.fosin.anan.core.banner.AnanBanner;

/**
 * @author fosin
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminServerApplication.class)
                .banner(new AnanBanner("AnAn Admin Server"))
                .logStartupInfo(true)
                .run(args);
    }
}
