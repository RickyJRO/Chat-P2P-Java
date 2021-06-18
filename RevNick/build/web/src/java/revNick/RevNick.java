package revNick;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
/**
* REST Web Service
*
* @author amc
*/
@Path("bindnick")
public class RevNick {

 @Context
 private UriInfo context;
 /**
 * Creates a new instance of BindNick
 */
 public RevNick() {
 }
 /**
 * Retrieves representation of an instance of bindNick.BindNick
 * @param nick
 * @return an instance of java.lang.String
 * URL de teste
 * http://localhost:8080/BindNick/webresources/revnick?port=8080
 */
 @GET
 @Consumes("text/plain")
 @Produces("text/html")
 public Response getName(
 @QueryParam("port") int port){
 String res="0000";
 if(port == 8080){
 res="batata";
 }
 if(port == 8081){
 res="cebola";
 }
 if(port == 8082){
 res="alface";
 }
 if(port == 8083){
 res="cenoura";
 }
 return Response.status(200).entity(res).build();
 }

}