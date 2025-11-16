package ad.zona_fit.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class Login extends JFrame{
    private JTextField usuarioTexto;
    private JPasswordField passwordTexto;
    private JPanel panelPrincipal;
    private JButton enviarButton;
    private final String USUARIOS = "admin";
    private final String PASSWORD = "admin123";

    private ZonaFitForma zonaFitForma;

    @Autowired
    public Login(ZonaFitForma zonaFitForma) {
        this.zonaFitForma = zonaFitForma;
        runLogin();
        enviarButton.addActionListener(e -> iniciarSesion());
    }

    private void runLogin() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    private void iniciarSesion(){
        var datosValidos = validarDatos();
         if (datosValidos){
             zonaFitForma.mostrarVentana();
             this.setVisible(false);
         }
    }

    private boolean validarDatos(){
        var usuario = this.usuarioTexto.getText();
        var password = new String(this.passwordTexto.getPassword());
        if (usuario.equals(this.USUARIOS) && password.equals(this.PASSWORD)){
            return true;
        }
        else if (usuarioTexto.getText().equals(usuario)) {
            mostrarMensaje("Pasword Invalida.");
            return false;
        }
        else {
            mostrarMensaje("Datos Invalidos.");
            return false;
        }
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
