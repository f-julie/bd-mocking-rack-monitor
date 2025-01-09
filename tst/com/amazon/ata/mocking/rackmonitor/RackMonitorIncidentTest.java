package com.amazon.ata.mocking.rackmonitor;

import com.amazon.ata.mocking.rackmonitor.clients.warranty.Warranty;
import com.amazon.ata.mocking.rackmonitor.clients.warranty.WarrantyClient;
import com.amazon.ata.mocking.rackmonitor.clients.warranty.WarrantyNotFoundException;
import com.amazon.ata.mocking.rackmonitor.clients.wingnut.WingnutClient;
import com.amazon.ata.mocking.rackmonitor.exceptions.RackMonitorException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;  // initMocks is deprecated, hence the strikeout
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Mockito is a framework for mocking in tests
// A framework is a set of classes that make common programming requirements easier
// Examples of frameworks: Collection, JUnit, Mockito
// Mockito is popular because it's easy and free

public class RackMonitorIncidentTest {
    // Instantiate objects used in the test
    RackMonitor rackMonitor; // The class with the methods we are testing

    // BEFORE Mocking we need to define references to any external class objects
    // WingnutClient wingnutClient; // Reference that may be used in a test
    // WarrantyClient warrantyClient; // Reference that may be used in a test
    // Rack rack1; // Reference that may be used in a test

    // USING Mocks, we define external class objects as Mocks (@Mock)
    // Mockito will manage use of the Mocked objects in the tests
    @Mock
            WingnutClient wingnutClient; // Reference that may be used in a test
    @Mock
            WarrantyClient warrantyClient; // Reference that may be used in a test
    @Mock
            Rack rack1; // Reference that may be used in a test

    // Since we are using Mocking we no longer need multiple instances of test object
    // Server unhealthyServer = new Server("TEST0001"); // Unhealthy server object (healthfactor < 0.8)
    // Server shakyServer = new Server("TEST0067"); // Shaky server object (healthfactor between 0.8 and 0.9)

    // When using Mocking, we usually only need one instance of a test object
    // Set attributes the test object needs just before we use it
    Server aServer = new Server("TEST001"); // Using a test server returns a null warranty, which we will need

    // Define any return values from any Mock'd method calls
    // We plan on Mock'ing the Rack.getHealth() method call - it returns a Map<Server, Double>
    Map<Server, Double> serverHealth; // Hold the return value from Mock'd getHealth() call

    Map<Server, Integer> rack1ServerUnits; // Hold the servers we are testing

    @BeforeEach // Do this before each test is run
    void setUp() {
        // BEFORE Mocking - Instantiate objects used in the test and assign them to their reference
        // warrantyClient = new WarrantyClient(); // These are @Mock'd so we don't instantiate them
        // wingnutClient = new WingnutClient(); // These are @Mock'd so we don't instantiate them
        // rack1 = new Rack("RACK01", rack1ServerUnits); // These are @Mock'd so we don't instantiate them // Place a Rack with our server map in our Rack

        initMocks(this);   // Tells Mockito to prepare the @Mock'd objects for use
                                    // initMocks() is deprecated, hence the strikeout
        // MockitoAnnotations.openMocks(this); // replacement for deprecated initMocks(this)

        serverHealth = new HashMap<>(); // Instantiating the Map to be returned from Mock'd getHealth()

        rack1ServerUnits = new HashMap<>(); // Map fo servers in our Rack for testing

        rack1ServerUnits.put(aServer, 1); // replace unhealthyServer // Place our unhealthy server in server map with id 1

        // Define our RackMonitor with the Rack Object, WingnutClient Object, WarrantyClient Object, and health threshold
        rackMonitor = new RackMonitor(new HashSet<>(Arrays.asList(rack1)),
            wingnutClient, warrantyClient, 0.9D, 0.8D);
        //                                  shaky threshold, unhealthy threshold
    }

    @Test
    public void getIncidents_withOneUnhealthyServer_createsOneReplaceIncident() throws Exception {
        // GIVEN
        // Since we're using Mock'd objects, we need to tell Mockito what the method calls should return
        // Since we no longer have real objects with real methods, there are no real methods to call
        // We tell Mockito when you see a method call with certain parameters, return a specific result

        // Need to define the value to be returned from the Mock'd method
        // The rack is set up with a single unhealthy server - healthfactor < 0.8
        serverHealth.put(aServer, 0.5); // Return data from Mock'd getHealth() call

        // What you're telling Mockito
        // When you see this method call.return-this-value
        // When Rack object is used to call getHealth, then return serverHealth object
        when(rack1.getHealth()).thenReturn(serverHealth);

        // When Rack object is used to call getUnitForServer(), then return test server id (1)
        when(rack1.getUnitForServer(aServer)).thenReturn(1);

        // When warrantyClient.getWarrantyForServer() is called with a server, then return a Warranty
        // Irrelevant that warranty is null
        when(warrantyClient.getWarrantyForServer(aServer)).thenReturn(Warranty.nullWarranty());

        // We've reported the unhealthy server to Wingnut
        rackMonitor.monitorRacks();

        // WHEN
        Set<HealthIncident> actualIncidents = rackMonitor.getIncidents();

        // THEN
        HealthIncident expected =
            new HealthIncident(aServer, rack1, 1, RequestAction.REPLACE); // Replace unhealthyServer
        assertTrue(actualIncidents.contains(expected),
            "Monitoring an unhealthy server should record a REPLACE incident!");
    }

    @Test
    public void getIncidents_withOneShakyServer_createsOneInspectIncident() throws Exception {
        // GIVEN

        // Because we are using Mock'd objects, there is no need to define real data
        // The rack is set up with a single shaky server
        // rack1ServerUnits = new HashMap<>();
        // rack1ServerUnits.put(aServer, 1); // Replace shakyServer
        // rack1 = new Rack("RACK01", rack1ServerUnits);

        // No need to define a RackMonitor object as it is already done in the SetUp() method @BeforeEach
        // rackMonitor = new RackMonitor(new HashSet<>(Arrays.asList(rack1)),
            // wingnutClient, warrantyClient, 0.9D, 0.8D);

        // Need to define the value to be returned from the Mock'd method
        // The rack is set up with a single shaky server - healthfactor between 0.81 and 0.9
        serverHealth.put(aServer, 0.85); // Return data from Mock'd getHealth() call

        // What you're telling Mockito
        // When you see this method call.return-this-value
        // When Rack object is used to call getHealth, then return serverHealth object
        when(rack1.getHealth()).thenReturn(serverHealth);

        // When Rack object is used to call getUnitForServer(), then return test server id (1)
        when(rack1.getUnitForServer(aServer)).thenReturn(1);

        // When warrantyClient.getWarrantyForServer() is called with a server, then return a Warranty
        // Irrelevant that warranty is null
        when(warrantyClient.getWarrantyForServer(aServer)).thenReturn(Warranty.nullWarranty());

        // We've reported the shaky server to Wingnut
        rackMonitor.monitorRacks();

        // WHEN
        Set<HealthIncident> actualIncidents = rackMonitor.getIncidents();

        // THEN
        HealthIncident expected =
            new HealthIncident(aServer, rack1, 1, RequestAction.INSPECT); // Replace shakyServer
        assertTrue(actualIncidents.contains(expected),
            "Monitoring a shaky server should record an INSPECT incident!");
    }

    @Test
    public void getIncidents_withOneHealthyServer_createsNoIncidents() throws Exception {
        // GIVEN

        // Need to define the value to be returned from the Mock'd method
        // The rack is set up with a single healthy server - healthfactor > 0.9
        serverHealth.put(aServer, 0.91); // Return data from Mock'd getHealth() call

        // What you're telling Mockito
        // When you see this method call.return-this-value
        // When Rack object is used to call getHealth, then return serverHealth object
        when(rack1.getHealth()).thenReturn(serverHealth);

        // When Rack object is used to call getUnitForServer(), then return test server id (1)
        when(rack1.getUnitForServer(aServer)).thenReturn(1);

        // When warrantyClient.getWarrantyForServer() is called with a server, then return a Warranty
        // Irrelevant that warranty is null
        when(warrantyClient.getWarrantyForServer(aServer)).thenReturn(Warranty.nullWarranty());

        // monitorRacks() will find only healthy servers
        rackMonitor.monitorRacks();


        // WHEN
        Set<HealthIncident> actualIncidents = rackMonitor.getIncidents();

        // THEN
        assertEquals(0, actualIncidents.size(),
            "Monitoring a healthy server should record no incidents!");
    }

    @Test
    public void monitorRacks_withOneUnhealthyServer_replacesServer() throws Exception {
        // GIVEN
        // The rack is set up with a single unhealthy server

        // Need to define the value to be returned from the Mock'd method
        // The rack is set up with a single unhealthy server - healthfactor < 0.8
        serverHealth.put(aServer, 0.63); // Return data from Mock'd getHealth() call

        // What you're telling Mockito
        // When you see this method call.return-this-value
        // When Rack object is used to call getHealth, then return serverHealth object
        when(rack1.getHealth()).thenReturn(serverHealth);

        // When Rack object is used to call getUnitForServer(), then return test server id (1)
        when(rack1.getUnitForServer(aServer)).thenReturn(1);

        // When warrantyClient.getWarrantyForServer() is called with a server, then return a Warranty
        // Irrelevant that warranty is null
        when(warrantyClient.getWarrantyForServer(aServer)).thenReturn(Warranty.nullWarranty());

        // WHEN
        rackMonitor.monitorRacks();

        // THEN
        // There were no exceptions
        // No way to tell we called the warrantyClient for the server's Warranty
        // UNLESS you use Mockito's verify() method
        // Verify getWarrantyForServer() was called at least once
        verify(warrantyClient).getWarrantyForServer(aServer);

        // No way to tell we called Wingnut to replace the server
        // UNLESS we use Mockito's verify() method
        // Verify requestReplacement() was called at least once
        verify(wingnutClient).requestReplacement(rack1, 1, Warranty.nullWarranty());
    }

    @Test
    public void monitorRacks_withUnwarrantiedServer_throwsServerException() throws Exception {
        // GIVEN
        // No need to define real data since we are using Mock'd objects
        // Server noWarrantyServer = new Server("TEST0052");
        // rack1ServerUnits = new HashMap<>();
        // rack1ServerUnits.put(noWarrantyServer, 1);
        // rack1 = new Rack("RACK01", rack1ServerUnits);
        // rackMonitor = new RackMonitor(new HashSet<>(Arrays.asList(rack1)), wingnutClient, warrantyClient, 0.9D, 0.8D);

        // Need to define the value to be returned from the Mock'd method
        // The rack is set up with a single unhealthy server - healthfactor < 0.8
        serverHealth.put(aServer, 0.63); // Return data from Mock'd getHealth() call

        // What you're telling Mockito
        // When you see this method call.return-this-value
        // When Rack object is used to call getHealth, then return serverHealth object
        when(rack1.getHealth()).thenReturn(serverHealth);

        // When Rack object is used to call getUnitForServer(), then return test server id (1)
        when(rack1.getUnitForServer(aServer)).thenReturn(1);

        // When warrantyClient.getWarrantyForServer() is called with a server, then
        // throw a WarrantyNotFoundException (custom exception, not system exception)
        // Tell Mockito it's a class (.class)
        when(warrantyClient.getWarrantyForServer(aServer)).thenThrow(WarrantyNotFoundException.class);

        // WHEN and THEN
        assertThrows(RackMonitorException.class,
            () -> rackMonitor.monitorRacks(), // This uses a Java Lambda expression (Unit 6)
            "Monitoring a server with no warranty should throw exception!");
    }
}
