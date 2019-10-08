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

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import vlibtour.vlibtour_visit_emulation.GPSPosition;

/**
 * The entity bean defining a point of interest (POI). A {@link Tour} is a
 * sequence of points of interest.
 * 
 * For the sake of simplicity, we suggest that you use named queries.
 * 
 * @author Denis Conan
 */
@Entity
public class POI implements Serializable {
	/**
	 * the serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id of the POI.
	 */
	@GeneratedValue
	private int id;
	/**
	 * the name of the POI.
	 */
	private String name;
	/**
	 * the description of the POI.
	 */
	private String description;
	/**
	 * the collection of corresponding tour.
	 */
	private Collection<Tour> tours ;
	/**
	 * the position of the POI.
	 */
	private GPSPosition position;
	/**
	 * gets the name.
	 * 
	 * @return the name.
	 */


	public String getName() {
		return name;
	}
	/**
	 * sets the name.
	 * 
	 * @param name
	 *            the new name.
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * gets the description.
	 * 
	 * @return the description.
	 */


	public String getDescription() {
		return description;
	}
	/**
	 * sets the description.
	 * 
	 * @param description
	 *            the new description.
	 */
	public void setDescription(final String description) {
		this.description= description;
	}
	/**
	 * gets the position.
	 * 
	 * @return the position.
	 */


	public GPSPosition getPosition() {
		return position;
	}
	/**
	 * sets the position.
	 * 
	 * @param position
	 *            the new position.
	 */
	public void setPosition(final GPSPosition position) {
		this.position= position;
	}
	/**
	 * gets the identifier.
	 * 
	 * @return the identifier.
	 */

	@Id
	public int getId() {
		return id;
	}
	/**
	 * sets the identifier.
	 * 
	 * @param id
	 *            the new identifier.
	 */
	public void setId(final int id) {
		this.id = id;
	}
	
	/**
	 * gets the collection of tours.
	 * 
	 * @return the tour.
	 */

	@ManyToMany()
	public Collection<Tour> getTours() {
		return tours;
	}
	/**
	 * sets the corresponding tour.
	 * 
	 * @param tours
	 *            the new corresponding tour.
	 */
	public void setTour(final Collection<Tour> tours) {
		this.tours = tours;
	}

}
