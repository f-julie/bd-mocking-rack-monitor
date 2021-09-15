package com.amazon.ata.mocking.rackmonitor;

import java.util.Objects;

/**
 * An immutable record of a problem with a server.
 * Allows us to "dedupe" incidents.
 */
public class HealthIncident {
    private final Server server;
    private final Rack rack;
    private final int unit;
    private final RequestAction action;

    public HealthIncident(Server server, Rack rack, int unit, RequestAction action) {
        this.server = server;
        this.rack = rack;
        this.unit = unit;
        this.action = action;
    }

    public Server getServer() {
        return server;
    }

    public Rack getRack() {
        return rack;
    }

    public int getUnit() {
        return unit;
    }

    public RequestAction getAction() {
        return action;
    }

    @Override
    public String toString() {
        return String.format("{%s %s in %s unit %d}",
            action, server, rack, unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HealthIncident that = (HealthIncident) o;
        return unit == that.unit &&
            Objects.equals(server, that.server) &&
            Objects.equals(rack, that.rack) &&
            action == that.action;
    }

    @Override
    public int hashCode() {
        return Objects.hash(server, rack, unit, action);
    }
}
