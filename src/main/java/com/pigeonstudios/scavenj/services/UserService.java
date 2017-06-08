package com.pigeonstudios.scavenj.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pigeonstudios.scavenj.database.Database;
import com.pigeonstudios.scavenj.model.User;

/**
 * This class provides is the logic between the database and the resources.
 * Resources access this to get the users from the database and this class
 * actually pulls the users from the database
 * @author Dennis Fedorchuk
 *
 */
public class UserService {

	private Map<Long, User> usersMap = Database.getUsers();
	
	/**
	 * A service that is used by User resource to get the list of users
	 * @return - list of users
	 */
	public List<User> getAllUsers(){
		return new ArrayList<User>(usersMap.values());		
	}
	
	/**
	 * A service that is used by User resource to get the user from the DB by id
	 * @param id - id of the desired user
	 * @return - user with specified id
	 */
	public User getUser(long id){
		return usersMap.get(id);
	}
	
	/**
	 * Service returns all users from a specific year and a specific amount of those users 
	 * @param year - year of the users
	 * @param start - starting from this user 
	 * @param size - this many users from starting location
	 * @return - the list of users
	 */
	public List<User> getAllUsersAddedForYearPaginated(int year, int start, int size){
		//TODO implement this method if needed
		return null;
	}
	
	/**
	 * Service returns all users that signed up in this year
	 * @param year - year when signed up
	 * @return - list of users that signed up in the specific year
	 */
	public List<User> getAllUsersAddedForYear(int year){
		//TODO implement this method if needed
		return null;
	}
	
	/**
	 * Service returns a list of users stating from the start parameter it returns a size parameter amount
	 * @param start - starting point to return users from
	 * @param size - amount of users
	 * @return - list of users
	 */
	public List<User> getAllUsersPaginated(int start, int size){
		//TODO implement this method if needed
		return null;
	}
	
	/**
	 * Used to add a new user to a database
	 * @param user - user that was passed from the resource
	 * @return - new created user
	 */
	public User addNewUser(User user){
		user.setCreated(Date.from(Instant.now()));
		user.setId(usersMap.size() + 1);
		usersMap.put(user.getId(), user);
		return user;
	}
	
	/**
	 * Method that updates the desired user values
	 * @param userId - user id of the user that needs to be updated
	 * @param user - the changed values of the user (id doesn't change)
	 * @return - the updated user
	 */
	public User updateUser(long userId, User user){
		if(userId <= 0){
			return null;
		}
		
		user.setId(userId);
		usersMap.put(userId, user);
		
		return user;
	}
	
	/**
	 * Method that deletes a specified user
	 * @param userId - user id to delete
	 * @return - deleted user
	 */
	public User deleteUser(long userId){
		return usersMap.remove(userId);
	}
}
