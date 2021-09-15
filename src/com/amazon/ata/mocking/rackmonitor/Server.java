package com.amazon.ata.mocking.rackmonitor;

import java.util.Objects;

/**
 * Represents a Server in a Rack in a data center.
 */
public class Server {
    final String serverId;
    // An actual Server would include lots of other information:
    // uptime, CPUs, memory, OS, etc

    public Server(String serverId) {
        this.serverId = serverId;
    }

    public String getServerId() {
        return this.serverId;
    }

    @Override
    public String toString() {
        return String.format("server: %s", serverId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Server server = (Server) o;
        return Objects.equals(serverId, server.serverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverId);
    }
}
