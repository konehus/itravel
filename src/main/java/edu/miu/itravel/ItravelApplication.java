package edu.miu.itravel;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.PrintStream;

@EntityScan(basePackageClasses = {ItravelApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
@EnableJpaAuditing
public class ItravelApplication {

    public static void main(String[] args) {



        SpringApplication app = new SpringApplication(ItravelApplication.class);


        app.setBanner(new Banner() {
            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                out.print("\n\n[..     [..                             [..        [.. ..                                             [.                       \n" +
                        "[..     [..                             [..      [..    [..                [.                        [. ..                     \n" +
                        "[..     [..   [..    [.. [..     [..    [..  [..  [..      [. [..  [. [...   [.. [..     [..        [.  [..    [. [..  [. [..  \n" +
                        "[...... [.. [.   [..  [..  [.. [..  [.. [.. [..     [..    [.  [..  [..   [.. [..  [.. [..  [..    [..   [..   [.  [.. [.  [.. \n" +
                        "[..     [..[..... [.. [..  [..[..    [..[.[..          [.. [.   [.. [..   [.. [..  [..[..   [..   [...... [..  [.   [..[.   [..\n" +
                        "[..     [..[.         [..  [.. [..  [.. [.. [..  [..    [..[.. [..  [..   [.. [..  [.. [..  [..  [..       [.. [.. [.. [.. [.. \n" +
                        "[..     [..  [....   [...  [..   [..    [..  [..   [.. ..  [..     [...   [..[...  [..     [..  [..         [..[..     [..     \n" +
                        "                                                           [..                          [..                    [..     [..     \n\n".toUpperCase());
            }
        });

        app.run(args);

    }
}
