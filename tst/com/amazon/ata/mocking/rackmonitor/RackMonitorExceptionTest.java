package com.amazon.ata.mocking.rackmonitor;

import com.amazon.ata.mocking.rackmonitor.clients.warranty.WarrantyClient;
import com.amazon.ata.mocking.rackmonitor.clients.wingnut.WingnutClient;
import com.amazon.ata.mocking.rackmonitor.exceptions.RackMonitorException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RackMonitorExceptionTest {
    RackMonitor rackMonitor;
    @Mock
    WingnutClient wingnutClient;
    @Mock
    WarrantyClient warrantyClient;
    @Mock
    Rack mockRack;
    Server testServer = new Server("TEST0001");
    Map<Server, Double> unwarrantiedServerResult = new HashMap<>();

    @BeforeEach
    void setUp() throws Exception {
        initMocks(this);
        unwarrantiedServerResult.put(testServer, 0.75D);
        when(mockRack.getUnitForServer(testServer)).thenReturn(1);
        rackMonitor = new RackMonitor(new HashSet<>(Arrays.asList(mockRack)),
            wingnutClient, warrantyClient, 0.9D, 0.8D);
    }

    @AfterEach
    void verifyNoDependencyCalls() {
        verifyNoMoreInteractions(wingnutClient, warrantyClient);
    }

    @Test
    public void monitorRacks_withUnhealthyUnwarrantiedServer_throwsRackMonitorException() throws Exception {
        // GIVEN
        // The rack is set up with a single unhealthy, unwarrantied server
        when(mockRack.getHealth()).thenReturn(unwarrantiedServerResult);
        // Getting the Warranty will throw an exception

        // WHEN and THEN
        assertThrows(RackMonitorException.class,
            () -> rackMonitor.monitorRacks(),
            "Unhealthy server without warranty should throw RackMonitorException!");
    }

}
