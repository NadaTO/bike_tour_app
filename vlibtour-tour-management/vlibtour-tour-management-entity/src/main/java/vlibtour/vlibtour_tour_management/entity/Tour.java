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
package vlibtour.vlibtour_tour_management.entity;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * The entity bean defining a tour in the VLibTour case study. A tour is a
 * sequence of points of interest ({@link POI}).
 * 
 * For the sake of simplicity, we suggest that you use named queries.
 * 
 * @author Denis Conan
 */
@Entity
public class Tour implements Serializable {
	/**
	 * the serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id of the tour.
	 */
	@GeneratedValue
	private int id;
	/**
	 * the name of the tour.
	 */
	private String name;
	/**
	 * the collection of POIs
	 */
	private Collection<POI> pois = new ArrayList <POI>();
	/**
	 * gets the identifier
	 * @return the identifier
	 */
	@Id 
	public int getId() {
		return id;
	}
	/**
	 * sets the identifier
     * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * sets the name
     * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gets the name of the tour.
	 * 
	 * @return the name of the tour.
	 */
	public String getName() {
		return name;
	}
	/**
	 * gets the collection of POIs.
	 * 
	 * @return the pois.
	 */
	@ManyToMany()	
	public Collection<POI> getPois() {
		return pois;
	}
	/**
	 *sets the collection of POIs.
	 * 
	 * @param pois.
	 */
	public void setPois(Collection<POI> pois) {
		this.pois = pois;
	}
	
	
	
	
}
