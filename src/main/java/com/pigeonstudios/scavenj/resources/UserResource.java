package com.pigeonstudios.scavenj.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.pigeonstudios.scavenj.database.Database;
import com.pigeonstudios.scavenj.model.User;
import com.pigeonstudios.scavenj.services.UserService;

@Path("/users")
public class UserResource {

	private UserService userService = new UserService();
	
	//temp constructor that adds users since there is no database yet
	public UserResource(){
		Database.getUsers().put(1L, new User(1, "Bob", Date.from(Instant.now())));
		Database.getUsers().put(2L, new User(2, "Rob", Date.from(Instant.now())));
	}
	
	/**
	 * This method returns all the users currently in the database.
	 * This request uses @QueryParam called "year". If the year was entered something
	 * can be done with this value. A query parameter can be entered in the link: "URL/users?year=2014"
	 * the parameter will = 2014. Else, the parameter will be 0.  
	 * @return - list of all users
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(@QueryParam("year") int year,
									@QueryParam("start") int  start,
									@QueryParam("size") int size){
		//if all 3 parameters have been entered
		if(year > 0 && start > 0 && size > 0){
			return userService.getAllUsersAddedForYearPaginated(year, start, size);
		}
		//if only year parameter was entered
		if(year > 0){
			return userService.getAllUsersAddedForYear(year);
		}
		//if pagination parameters were entered only
		if (start > 0 && size > 0){ 
			return userService.getAllUsersPaginated(start, size);
		}
		
		return userService.getAllUsers();
	}
	
	/**
	 * Get a specific user by specifying user id
	 * @param id - id of the user
	 * @return - The user with the specified id
	 */
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("userId") long id){
		return userService.getUser(id);
	}
	
	/**
	 * Add a new user to the database
	 * @param user - user that has been created from JSON from a post request
	 * @return - created user
	 * @throws URISyntaxException 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addNewUser(User user, @Context UriInfo uriInfo){
		user = userService.addNewUser(user);
		
		//this returns a user json
		//return user;
		
		//this returns a response instance
		//status 201 means created
		//entity is the user that we want to return
		//build when done
//		return Response.status(Status.CREATED)
//				.entity(userService.addNewUser(user))
//				.build();
		
		
		//using a created method of response posts a location of the created 
		//resource in the header
		//this builds a uri from the known values in the context
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(user.getId())).build();
		return Response.created(uri)
						.entity(user)
						.build();
	}
	
	/**
	 * A resource that updates the user values based on id that is specified
	 * @param userId - user id whose values are to be updated
	 * @param user - the new user values
	 * @return - updated user
	 */
	@PUT
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User updateUser(@PathParam("userId") long userId, User user){
		user = userService.updateUser(userId, user);
		return user;
	}
	
	/**
	 * A resource to delete a user with a specific id
	 * @param userId - id of the user to delete
	 * @return - deleted user
	 */
	@DELETE
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User deleteUser(@PathParam("userId") long userId){
		return userService.deleteUser(userId);
	}
	
	
}
