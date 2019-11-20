package wsrest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Usuario;
import model.UsuarioDAO;

@Path("/wsusuario")
public class WSUsuario {
	
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(Usuario u) {
		UsuarioDAO dao = new UsuarioDAO();
		return Response.status(200).entity(dao.salvar(u)).build();
	}
	
	@GET
	@Path("/listartodos")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTodos() {
		UsuarioDAO dao = new UsuarioDAO();
		return Response.status(200).entity(dao.listarTodos()).build();
	}
	
	@GET
	@Path("/listarnome/{nome}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarNome(@PathParam("nome") String nome) {
		UsuarioDAO dao = new UsuarioDAO();
		return Response.status(200).entity(dao.listarNome(nome)).build();
	}
}
