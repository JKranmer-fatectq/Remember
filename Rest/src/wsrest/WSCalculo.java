package wsrest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/wscalculo")
public class WSCalculo {

	@GET
	@Path("/getmensagem")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getMensagem() {
		String mensagem = "Exemplo web service REST";
		return Response.status(200).entity(mensagem).build();
	}
	
	@GET
	@Path("/getadicao/{valor1}/{valor2}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAdicao(@PathParam("valor1") double valor1, 
			@PathParam("valor2") double valor2) {
		double resultado = valor1 + valor2;
		return Response.status(200).entity(resultado).build();
	}
	
	@GET
	@Path("/getsubtracao")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getSubtracao(@QueryParam("valor1") double valor1, 
			@QueryParam("valor2") double valor2) {
		double resultado = valor1 - valor2;
		return Response.status(200).entity(resultado).build();
	}
	
	@GET
	@Path("/getcalculo/{valor1}/{valor2}/{operacao}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCalculo(@PathParam("valor1") double valor1, 
			@PathParam("valor2") double valor2, 
			@PathParam("operacao") int operacao) {
		double resultado = 0;
		switch (operacao) {
			case 1:
				resultado = valor1 + valor2;
				break;
			case 2:
				resultado = valor1 - valor2;
				break;
			case 3:
				resultado = valor1 * valor2;
				break;
			case 4:
				resultado = valor1 / valor2;
				break;
		}
		return Response.status(200).entity(resultado).build();
	}

	@POST
	@Path("/getcalculoform")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCalculoForm(@PathParam("valor1") double valor1, 
		@PathParam("valor2") double valor2, 
		@PathParam("operacao") int operacao) {
	double resultado = 0;
	switch (operacao) {
		case 1:
			resultado = valor1 + valor2; 
			break;
		case 2:
			resultado = valor1 - valor2;
			break;
		case 3:
			resultado = valor1 * valor2;
			break;
		case 4:
			resultado = valor1 / valor2;
			break;
	}
	return Response.status(200).entity(resultado).build();
}
}
