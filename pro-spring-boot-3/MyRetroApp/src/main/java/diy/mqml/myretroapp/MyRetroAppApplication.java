package diy.mqml.myretroapp;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import java.io.PrintStream;


/**
 *
 * .web(WebApplicationType): This method defines the type of web application. The possible values are WebApplicationType.NONE (instructs that the application should not run as a web application and should not start an embedded web server), WebApplicationType.SERVLET (means that the application should run as a servlet-based web app and start an embedded servlet web server; this will be true if you have the spring-boot-starter-web starter as a dependency/classpath), and WebApplicationType.REACTIVE (means that the application should run as a reactive web application and should start an embedded reactive web server, and you need the spring-boot-starter-webflux starter as a dependency/classpath).
 *
 * */

@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
public class MyRetroAppApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder()
                .sources(MyRetroAppApplication.class)
                .logStartupInfo(true)
                .bannerMode(Banner.Mode.CONSOLE)
                .lazyInitialization(false) //if set to true, then the bean creation will not happen until the bean is needed
                .web(WebApplicationType.SERVLET)
                .profiles("cloud") //lists Spring profiles that you use
                .run(args);

    }

}
