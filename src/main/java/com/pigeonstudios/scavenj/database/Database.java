package com.pigeonstudios.scavenj.database;

import java.util.HashMap;
import java.util.Map;

import com.pigeonstudios.scavenj.model.*;
/**
 * This class serves as the database replacement for now
 * @author Dennis Fedorchuk
 *
 */
public class Database {

	private static Map<Long, User> usersMap = new HashMap<>();
	
	public static Map<Long, User> getUsers(){
		return usersMap;
	}
}
