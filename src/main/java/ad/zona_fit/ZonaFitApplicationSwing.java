package ad.zona_fit;

import ad.zona_fit.gui.Login;
import com.formdev.flatlaf.FlatDarkLaf;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


import javax.swing.*;

@SpringBootApplication
public class ZonaFitApplicationSwing {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        // INTANCIAR LA FABRICA DE SPRING
        ConfigurableApplicationContext contextSpring =
                new SpringApplicationBuilder(ZonaFitApplicationSwing.class)
                        .headless(false)
                        .web(WebApplicationType.NONE)
                        .run(args);

        // CREAR DE UN OBJETO DE SWING
        SwingUtilities.invokeLater(()->{
            Login loginZonaFitForma = contextSpring.getBean(Login.class);
            loginZonaFitForma.setVisible(true);
        });
    }
}
