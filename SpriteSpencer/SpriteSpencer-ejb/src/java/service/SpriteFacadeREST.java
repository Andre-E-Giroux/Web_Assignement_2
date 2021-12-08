
package service;

import cst8218.stro0115.entity.Sprite;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**Class Name: SpriteFacadeREST
 * @author : Spencer Stroud
 * Purpose: This class controls the methods for the rest pages(GET,POST,PUT,DELETE)
 * 
 */
@Stateless
@DeclareRoles({"Admin","RestGroup"})
@RolesAllowed({"Admin","RestGroup"})
@Path("cst8218.stro0115.entity.sprite")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public SpriteFacadeREST() {
        super(Sprite.class);
    }
/**This method will create a new entity, or if one with the id already exists, will modify it with non-null values
 * @param entity the entity to add
 */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createPost(Sprite entity) {
        if(super.find(entity.getId())!=null){
            Sprite old =super.find(entity.getId());
            old.overwrite(entity);
            super.edit(old);
            return Response.status(Response.Status.OK).entity(entity).build();
        }else{
        super.create(entity);
        return Response.status(Response.Status.CREATED).entity(entity).build();
        }
    }
    /**This method posts to an item with a specific id. If the id is invalid or non-matching it fails
     * If it is valid, the new value will override all non-null values and edit the object
     * 
     * @param id the id in the path
     * @param entity the entity in the post request
     * @return Response Status BAD_REQUEST if id's don't match, OK on success or NOT_FOUND if id doesn't exist
     */
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editPost(@PathParam("id") Long id, Sprite entity) {
        if(!id.equals(entity.getId())){
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }
        if(super.find(id)!=null){
        Sprite old =super.find(id);
            old.overwrite(entity);
            super.edit(old);
            return Response.status(Response.Status.OK).entity(entity).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity(entity).build();
        }
    }
    /**This method overwrites an entity with a given entity
    * @param id the id in the path
    * @param entity the entity in the post request
    * @return Response Status OK on success
    */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Sprite entity) {
        super.edit(entity);
        return Response.status(Response.Status.OK).entity(entity).build();
    }

    /**This method deleted a specified entity using its ID
     * 
     * @param id the id of entity to delete
     * @return Response status OK on success
     */
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        Sprite entity=super.find(id);
        super.remove(super.find(id));
        return Response.status(Response.Status.OK).entity(entity).build();
    }
    /**This method will get a specific entity using the path to search for it.
    * 
    * @param id, the id to search for
     * @return the entity if found
    */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Sprite find(@PathParam("id") Long id) {
        return super.find(id);
    }

    /**This method will find all sprite entities in the database
     * 
     * @return a list of all the sprites
     */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() {
        return super.findAll();
    }

    /**This method finds all entities with an id between a specific range
     * 
     * @param from, the low end of the range
     * @param to, the high end of the range
     * @return a list of all sprites in the range
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    /**This method counts how many sprites are in the database
    * 
    * @return string value of the sprite count
    */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
/**This method returns the entity manager
 * 
 * @return em, the entity manager
 */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
