package com.pigeonstudios.scavenj.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class PoweredByResponceFilter implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext req,
			ContainerResponseContext res) throws IOException {
		//no spaces here cause it wont work. apparently 
		res.getHeaders().add("Powered-By", "magic");
		
	}

}
