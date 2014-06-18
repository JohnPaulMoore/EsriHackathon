package com.esri.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esri.model.Marker;
import com.esri.repository.MarkersRepository;
import com.google.gson.Gson;


@Path("marker")
public class MarkersResource {


	private MarkersRepository markerDataSource = new MarkersRepository();

	
	/**
	 * @return Response object ArrayList of Marker objects 
	 * @throws Exception
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response readBookings() throws Exception {

		ArrayList<Marker> markersList = markerDataSource.getAllMarkers();
		if (markersList == null) {
			return Response.status(500).entity("Error: No markersList returned").build(); 
		}
		return Response.ok(new Gson().toJson(markersList ), MediaType.APPLICATION_JSON).build();
	}	


	
	/**
	 * @param Marker object containing lat, lng, title, content text
	 * @return Response object - int of new Id of the created marker
	 * @throws Exception
	 */	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response create(Marker marker) throws Exception { 
		if (marker == null) {
			System.out.println("In null");
			return Response.status(400).entity("Error: No create data supplied").build(); 
		}		
		System.out.println("In create marker " + marker.getLatitude() + " : "  + marker.getLongitude() );

		int newId = markerDataSource.createMarker(marker);
		marker.setMarkerId(newId);
		
		if (newId == 0) {
			return Response.status(500).entity("Error: Create error").build(); 
		} 
		else {
			return Response.ok(new Gson().toJson(marker), MediaType.APPLICATION_JSON).build();
		}
	}	

		
	
	/**
	 * 
	 * @param markerId of marker to be deleted
	 * @return Response object - boolean of success status
	 * @throws Exception
	 */
	@DELETE
	@Path("/{markerId}") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response delete(@PathParam("markerId") Integer markerId) throws Exception {

		if (markerId == null) {
			System.out.println("Delete Null");
			return Response.status(400).entity("Error: No delete data supplied").build(); 
		}
		boolean success = markerDataSource.deleteBooking(markerId);

		if (success) {
			return Response.ok(new Gson().toJson(success), MediaType.APPLICATION_JSON).build();
		} 
		else {
			return Response.status(500).entity("Error: Delete error").build(); 
		}
	}	
	
	
	
	
	

}

