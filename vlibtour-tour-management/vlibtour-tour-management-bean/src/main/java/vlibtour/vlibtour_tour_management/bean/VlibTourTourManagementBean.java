/**
This file is part of the course CSC5002.

Copyright (C) 2017-2019 Télécom SudParis

This is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This software platform is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with the muDEBS platform. If not, see <http://www.gnu.org/licenses/>.

Initial developer(s): Denis Conan
Contributor(s):
 */
package vlibtour.vlibtour_tour_management.bean;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import vlibtour.vlibtour_tour_management.api.VlibTourTourManagement;
import vlibtour.vlibtour_tour_management.entity.POI;
import vlibtour.vlibtour_tour_management.entity.Tour;

/**
 * This class defines the EJB Bean of the VLibTour tour management.
 * 
 * @author Denis Conan
 */
@Stateless
public class VlibTourTourManagementBean implements VlibTourTourManagement {
	
	/**
	 * the reference to the entity manager, which persistence context is "pu1".
	 */
	@PersistenceContext(unitName = "pu1")
	private EntityManager em;
	
	public Collection<Tour> listTours(){
		Query q = em.createQuery("select t from Tour t");
		@SuppressWarnings("unchecked")
		Collection<Tour> results = q.getResultList();
		return results;		
	}

	@Override
	public Tour getTour(String name) {
		Query q = em.createQuery("select t from Tour t where t.name = :name");
		q.setParameter("name", name);
		return (Tour) q.getSingleResult();
	}

	@Override
	public POI getPOI(String name) {
		Query q = em.createQuery("select p from POI p where p.name = :name");
		q.setParameter("name", name);
		return (POI) q.getSingleResult();
	}

	@Override
	public Tour createTour(String name, Collection<POI> pois) {
		Tour tour = new Tour();
		tour.setName(name);
		tour.setPois(pois);
		em.persist(tour);
		return tour;	
	}
	@Override
	public POI createPOI (String name, double latitude,double longitude) {
		POI poi = new POI();
		poi.setName(name);
		poi.setLatitude(latitude);
		poi.setLongitude(longitude);
		em.persist(poi);
		return poi;	
	}

	@Override
	public String testInsert() {
		// Create a new tour
		Tour tour1 = new Tour();
		tour1.setName("Paris Tour");
		// Persist the tour
		em.persist(tour1);
		// Create 2 POIs
		POI poi1 = new POI();
		poi1.setName("1 Champs-Elysees, Paris, France");
		POI poi2 = new POI();
		poi2.setName("99 Main Street, London, UK");

	   // Associate POIs with the tours. The association
	   // must be set on both sides of the relationship: on the
       // tour side for the POIs to be persisted when
     	// transaction commits, and on the order side because it			// is the owning side.
		tour1.getPois().add(poi1);
		poi1.getTours().add(tour1);
		tour1.getPois().add(poi2);
		poi2.getTours().add(tour1);
		return "OK";	
	}

	@Override
	public String verifyInsert() {
		Tour c = getTour("Paris Tour");
		Collection<POI> orders = c.getPois();
		if (orders == null || orders.size() != 2) {
			throw new RuntimeException(
					"Unexpected number of orders: " + ((orders == null) ? "null" : "" + orders.size()));
		}
		return "OK";
	}
	
	@Override
	public String testDelete(final Tour t) {
		// Merge the tour to the new persistence context
		Tour t0 = em.merge(t);
		// Delete all records.
		em.remove(t0);
		return "OK";
	}

	@Override
	public String verifyDelete() {
		Query q = em.createQuery("select t from Tour t");
		@SuppressWarnings("rawtypes")
		List results = q.getResultList();
		if (results == null || results.size() != 0) {
			throw new RuntimeException("Unexpected number of tours after delete results : " + results.size());
		}
		q = em.createQuery("select p from POI p");
		results = q.getResultList();
		if (results == null || results.size() != 0) {
			throw new RuntimeException("Unexpected number of pois after delete");
		}
		return "OK";
	}
	
}
