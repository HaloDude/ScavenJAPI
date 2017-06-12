package com.pigeonstudios.scavenj.auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class BasicAuthExample {
	
	@GET
	@Path("/msg")
	@Produces(MediaType.TEXT_PLAIN)
	public String securedMSG() {
		return "Security test of basic";
	}
}
