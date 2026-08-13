package com.ugmc.smartops.db;

import com.ugmc.smartops.datastructure.DynamicArray;
import com.ugmc.smartops.model.AlgorithmRun;
import com.ugmc.smartops.model.Location;
import com.ugmc.smartops.model.Resource;
import com.ugmc.smartops.model.Road;
import com.ugmc.smartops.model.ServiceRequest;

/**
 * Persistence abstraction for the system. A SQLite-backed implementation
 * will be wired in (Maven install scheduled tomorrow). For now a file-based
 * placeholder keeps the skeleton runnable with zero external dependencies.
 *
 * @author UGMC Smart Operations Team
 */
public interface Database {

    /** Opens/initialises the database connection and schema. */
    void connect() throws Exception;

    /** Closes the database connection. */
    void disconnect() throws Exception;

    // --- Bulk operations ---
    void saveLocations(DynamicArray<Location> locations) throws Exception;
    void saveRoads(DynamicArray<Road> roads) throws Exception;
    void saveServiceRequests(DynamicArray<ServiceRequest> requests) throws Exception;
    void saveResources(DynamicArray<Resource> resources) throws Exception;
    void saveAlgorithmRun(AlgorithmRun run) throws Exception;

    // --- Query operations ---
    DynamicArray<Location> loadLocations() throws Exception;
    DynamicArray<Road> loadRoads() throws Exception;
    DynamicArray<ServiceRequest> loadServiceRequests() throws Exception;
    DynamicArray<Resource> loadResources() throws Exception;
    DynamicArray<AlgorithmRun> loadAlgorithmRuns() throws Exception;
}
