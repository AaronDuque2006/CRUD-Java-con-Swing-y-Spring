package ad.zona_fit.servicio;

import ad.zona_fit.modelo.Cliente;
import ad.zona_fit.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio implements IClienteServicio{

    // INYECCION DE DEPENCIA PARA PODER USAR JPA
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> listarClienes() {
        List<Cliente> clientes = clienteRepositorio.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClienteId(Integer idCliente) {
        Cliente clienteEncontrado = clienteRepositorio.findById(idCliente).orElse(null);
        return clienteEncontrado;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepositorio.delete(cliente);

    }
}
