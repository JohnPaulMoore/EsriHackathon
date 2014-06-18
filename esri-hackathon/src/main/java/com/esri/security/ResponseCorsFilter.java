package com.esri.security;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder; 
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
 
public class ResponseCorsFilter implements ContainerResponseFilter {

    @Override
    public ContainerResponse filter(ContainerRequest req, ContainerResponse contResp) {
 
        ResponseBuilder resp = Response.fromResponse(contResp.getResponse());
        
        resp.header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
 
        String reqHead = req.getHeaderValue("Access-Control-Request-Headers");
 
        if(null != reqHead && !reqHead.equals("")){
            resp.header("Access-Control-Allow-Headers", reqHead);
        }
 
        contResp.setResponse(resp.build());
        
        System.out.println("ResponseCorsFilter");
        return contResp;
    }
	
	
//    /**
//     * Add the cross domain data to the output if needed
//     *
//     * @param creq The container request (input)
//     * @param cres The container request (output)
//     * @return The output request with cross domain if needed
//     */
//    @Override
//    public ContainerResponse filter(ContainerRequest creq, ContainerResponse cres) {
//        cres.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
//        cres.getHttpHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
//        cres.getHttpHeaders().add("Access-Control-Allow-Credentials", "true");
//        cres.getHttpHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//        cres.getHttpHeaders().add("Access-Control-Max-Age", "1209600");
//        
//        System.out.println("ResponseCorsFilter alt");
//        return cres;
//    }
 
}
