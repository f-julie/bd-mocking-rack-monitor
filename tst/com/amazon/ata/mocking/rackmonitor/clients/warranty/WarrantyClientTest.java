package com.amazon.ata.mocking.rackmonitor.clients.warranty;

import com.amazon.ata.mocking.rackmonitor.Server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class WarrantyClientTest {
    WarrantyClient warrantyClient;

    @BeforeEach
    void setUp() {
        warrantyClient = new WarrantyClient();
    }

    @AfterEach
    void tearDown() {
    }

    void getWarrantyForServer_forManyServers_generatesAnUnwarrantiedServer() throws Exception {
        for (int n = 0; n < 10000; n++) {
            String serverId = String.format("TEST%04d", n);
            Server server = new Server(serverId);
            try {
                warrantyClient.getWarrantyForServer(server);
            } catch (WarrantyNotFoundException e) {
                assertEquals(52, n, "Server 52 should generate no warranty exception!");
            }
        }
    }
}
