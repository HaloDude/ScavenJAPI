package com.pigeonstudios.scavenj.auth;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter{

	private static final String AUTH_HEADER = "Authorization";
	private static final String BASIC_PREFIX = "Basic ";
	
	
	@Override
	public void filter(ContainerRequestContext req) throws IOException {
		List<String> authHeaders = req.getHeaders().get(AUTH_HEADER);
		if(authHeaders.size() > 0){ // if headers exist
			String auth = authHeaders.get(0);
			auth = auth.replace(BASIC_PREFIX, ""); // remove the prefix from the header
			String decode = Base64.decodeAsString(auth); // decode (Should be username:Pass)
			StringTokenizer tokenizer = new StringTokenizer(decode, ":");
			String username = tokenizer.nextToken();
			String pass = tokenizer.nextToken();
			
			if("user".equals(username) && "password".equals(pass)) {
				return; // all good jax-rs can work on whatever 
			}
		}
		
		//if auth did not go through
		Response unauthResponse = Response.status(Response.Status.UNAUTHORIZED)
											.entity("User cannot access")
											.build();
		req.abortWith(unauthResponse);
		
	}

}
