package com.amazon.ata.mocking.rackmonitor;

/**
 * Enumeration of actions we can request from Wingnut.
 * Used to differentiate HealthIncidents so we don't make
 * repeated requests.
 */
public enum RequestAction {
    INSPECT,
    REPLACE
}
