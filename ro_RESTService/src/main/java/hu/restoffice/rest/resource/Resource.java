/**
 *
 */
package hu.restoffice.rest.resource;

import hu.rest.hal.representation.Representation;

/**
 * @author kalmankostenszky
 *
 */
public abstract class Resource<T> {

    protected T resource;

    public Resource(final T stub) {
        this.resource = stub;
    }

    public abstract Representation asRepresentation();

    public abstract void toResource(Representation halRepresentation);

    public T getResource() {
        return resource;
    }
}
