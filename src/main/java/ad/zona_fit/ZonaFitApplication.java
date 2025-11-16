package ad.zona_fit;

import ad.zona_fit.modelo.Cliente;
import ad.zona_fit.servicio.ClienteServicio;
import ad.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {
		logger.info("INICIANDO LA APLICACION");

		SpringApplication.run(ZonaFitApplication.class, args);

		logger.info("APLICACION FINALIZADA");
	}

	@Override
	public void run(String... args ) throws Exception {
		var menu ="""
		
				BIENVENIDO A ZONA FIT
		1. Listar.
		2. Buscar cliente.
		3. Agregar.
		4. Mofificar.
		5. Eliminar.
		6. Salir.""";

		boolean salir = false;
		Scanner input = new Scanner(System.in);

		while (!salir){
			logger.info(menu);
			var opcion = pedirOpcion(input);
			salir = procesarOpcion(opcion, input);
		}

	}

	private boolean procesarOpcion(int opcion, Scanner input) {
		switch (opcion) {
			case 1 -> {
				List<Cliente> clientes = clienteServicio.listarClienes();
				clientes.forEach(cliente -> logger.info(cliente.toString()));
			}
			case 2 -> {
				System.out.println(" ");
				logger.info("Ingrese el id del cliente\n");
				var id = pedirOpcion(input);
				var clienteEcontrado = clienteServicio.buscarClienteId(id);
				logger.info(String.valueOf(clienteEcontrado));
			}
			case 3 -> {
				logger.info("Ingrese el nombre del cliente: ");
				var nombre = input.nextLine();
				logger.info("Ingrese el apellido del cliente: ");
				var apellido = input.nextLine();
				logger.info("Ingrese la membresia del cliente: ");
				var membresia = Integer.parseInt(input.nextLine());

				Cliente clienteNuevvo = new Cliente();
				clienteNuevvo.setNombre(nombre);
				clienteNuevvo.setApellido(apellido);
				clienteNuevvo.setMembresia(membresia);
				clienteServicio.guardarCliente(clienteNuevvo);
			}
			case 4 -> {
				logger.info("Ingrese el id del cliente: ");
				var id = Integer.parseInt(input.nextLine());
				var clienteModificar = clienteServicio.buscarClienteId(id);
				if (clienteModificar != null){
					logger.info("Ingrese el nombre del cliente: ");
					var nombre = input.nextLine();
					logger.info("Ingrese el apellido del cliente: ");
					var apellido = input.nextLine();
					logger.info("Ingrese la membresia del cliente: ");
					var membresia = Integer.parseInt(input.nextLine());

					clienteModificar.setNombre(nombre);
					clienteModificar.setApellido(apellido);
					clienteModificar.setMembresia(membresia);

					clienteServicio.guardarCliente(clienteModificar);
				}

			}
			case 5 -> {
				logger.info("Ingrese el id del cliente\n");
				var id = pedirOpcion(input);
				var clienteEliminar = clienteServicio.buscarClienteId(id);
				if (clienteEliminar != null) {
					clienteServicio.eliminarCliente(clienteEliminar);
				}
			}
			case 6 -> {
				logger.info("Saliendo...");
				return true;
			}
			default -> logger.info("Opcion Invalida");
		}
		return false;
	}

	private int pedirOpcion(Scanner input){
		System.out.print(">> ");
		var opcion = Integer.parseInt(input.nextLine());
		return opcion;
	}
}


