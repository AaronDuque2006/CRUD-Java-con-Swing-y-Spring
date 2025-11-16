package ad.zona_fit.gui;

import ad.zona_fit.modelo.Cliente;
import ad.zona_fit.servicio.ClienteServicio;
import ad.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class ZonaFitForma extends JFrame {
    private JPanel panelPrincipal;
    private JTable clientesTabla;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField membresiaTexto;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private JButton guardarButton;
    IClienteServicio clienteServicio;
    private DefaultTableModel tablaModeloCliente;
    private Integer idCliente;

    @Autowired
    ZonaFitForma(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
        runZonaFitForma();
        guardarButton.addActionListener(e -> guardarCliente());
        clientesTabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarClienteSeleccionado();
            }
        });
        eliminarButton.addActionListener(e -> eliminarCliente());
        limpiarButton.addActionListener(e -> limpiarFormulario());
    }

    private void runZonaFitForma(){
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
    }

    // CREACION TABLA PERSONALIZADA
    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tablaModeloCliente = new DefaultTableModel(0, 4){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        String[] cabeceros = {"Id", "Nombre", "Apellido", "Membresia"};
        this.tablaModeloCliente.setColumnIdentifiers(cabeceros);
        this.clientesTabla = new JTable(tablaModeloCliente);
    }

    public void mostrarVentana() {
        this.listarClientes();
        this.setVisible(true);
    }

    // LISTAR CLIENTES EN LA TABLA
    private void listarClientes(){
        this.tablaModeloCliente.setRowCount(0);
        var clientes = this.clienteServicio.listarClienes();
        clientes.forEach(cliente -> {
            Object[] filaCliente = {
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMembresia()
            };
            this.tablaModeloCliente.addRow(filaCliente);
        });

    }

    private void guardarCliente(){
        if (nombreTexto.getText().isEmpty()){
            mostrarMensaje("Nombre del cliente requerido.");
            nombreTexto.requestFocusInWindow();
            return;
        }
        if (membresiaTexto.getText().isEmpty()){
            mostrarMensaje("Membresia del clinte requerida.");
            membresiaTexto.requestFocusInWindow();
            return;
        }
        var id = this.idCliente;
        var nombre = nombreTexto.getText();
        var apellido = apellidoTexto.getText();
        var membresia = Integer.parseInt(membresiaTexto.getText());

        Cliente clienteNuevo = new Cliente(id, nombre, apellido, membresia);

        // INSERTAR O MODIFICAR DEPENDIENDO SI Id es null O TIENE VALOR.
        this.clienteServicio.guardarCliente(clienteNuevo);

        if (id == null){
            mostrarMensaje("Cliente agregado con exito.");
        }
        else{
            mostrarMensaje("Cliente modificado con exito.");
        }

        limpiarFormulario();
        listarClientes();
    }

    // MOSTRAR CLIENTE EN EL FORMULARIO
    private void cargarClienteSeleccionado(){
        var filaSeleccion = clientesTabla.getSelectedRow();
        if (filaSeleccion != -1){
            var id = clientesTabla.getModel().getValueAt(filaSeleccion, 0).toString();
            this.idCliente = Integer.parseInt(id);

            var nombre = clientesTabla.getModel().getValueAt(filaSeleccion, 1).toString();
            nombreTexto.setText(nombre);

            var apellido = clientesTabla.getModel().getValueAt(filaSeleccion, 2).toString();
            apellidoTexto.setText(apellido);

            var membresia = clientesTabla.getModel().getValueAt(filaSeleccion, 3).toString();
            membresiaTexto.setText(membresia);
        }
    }

    private void eliminarCliente() {
        var filaSeleccion = clientesTabla.getSelectedRow();
        if (filaSeleccion != -1) {
            var id = clientesTabla.getModel().getValueAt(filaSeleccion, 0).toString();
            this.idCliente = Integer.parseInt(id);
            var clienteEliminar = clienteServicio.buscarClienteId(this.idCliente);
            clienteServicio.eliminarCliente(clienteEliminar);
        }
        else{
            mostrarMensaje("Seleccione un cliente");
        }
        limpiarFormulario();
        listarClientes();
    }

    private void limpiarFormulario(){
        nombreTexto.setText("");
        apellidoTexto.setText("");
        membresiaTexto.setText("");
        this.idCliente = null;

        // DESELECCION DE LA FILA SELECCIONADA
        this.clientesTabla.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
