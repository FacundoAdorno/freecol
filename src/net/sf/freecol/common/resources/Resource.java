/**
 *  Copyright (C) 2002-2022   The FreeCol Team
 *
 *  This file is part of FreeCol.
 *
 *  FreeCol is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  FreeCol is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with FreeCol.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.freecol.common.resources;

import java.net.URI;
import java.util.Objects;


/**
 * Represents a resource that either has been or can be loaded using a
 * {@code URI}.  There can only be one instance of
 * {@code Resource} having the same (as in URI's equals, not the
 * identity) {@code URI}.
 */
public abstract class Resource {

    /**
     * Implement the Cleanable interface if a Resource has a use for
     * calls to a clean method.
     */
    public interface Cleanable {

        /**
         * Clean the caches inside the resource.
         */
        public void clean();
    }


    /**
     * The {@code URI} used when loading this resource.
     */
    private final URI resourceLocator;
    
    /**
     * The primary key for the resource to be used for caching purposes.
     */
    private final String primaryKey;


    /**
     * Trivial constructor.
     *
     * @param primaryKey The primary key.
     */
    protected Resource(String primaryKey) {
        this(primaryKey, null);
    }

    /**
     * Do not use directly.
     *
     * @param primaryKey The key for the primary version of the resource,
     *      that is the key when the {@code Resource} was created.
     * @param resourceLocator The {@code URI} used when loading this
     *      resource.
     */
    protected Resource(String primaryKey, URI resourceLocator) {
        this.primaryKey = Objects.requireNonNull(primaryKey);
        this.resourceLocator = resourceLocator;
    }

    
    /**
     * The primary key of the resource. This key can be used for caching
     * purposes since it will be the same for a resource no matter how
     * many keys point to the same resource.
     * 
     * Beware: All variations and sizes share the same primary key.
     * 
     * @return The key.
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Returns the {@code URI} used for loading the resource.
     * @return The {@code URI}.
     */
    public URI getResourceLocator() {
        return this.resourceLocator;
    }

    /**
     * Preload the resource if possible/meaningful.
     */
    public abstract void preload();
}
