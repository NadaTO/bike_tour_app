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

/**
 * The entity bean defining a tour in the VLibTour case study. A tour is a
 * sequence of points of interest ({@link POI}).
 * 
 * For the sake of simplicity, we suggest that you use named queries.
 * 
 * @author Denis Conan
 */
public class Tour implements Serializable {
	/**
	 * the serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * gets the name of the tour.
	 * 
	 * @return the name of the tour.
	 */
	public String getName() {
		throw new UnsupportedOperationException("Not implemented, yet.");
	}
}
