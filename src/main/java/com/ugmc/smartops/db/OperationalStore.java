package com.ugmc.smartops.db;

import com.ugmc.smartops.datastructure.DynamicArray;
import com.ugmc.smartops.datastructure.HashTable;
import com.ugmc.smartops.datastructure.LinkedList;
import com.ugmc.smartops.model.AlgorithmRun;
import com.ugmc.smartops.model.Location;
import com.ugmc.smartops.model.Resource;
import com.ugmc.smartops.model.Road;
import com.ugmc.smartops.model.ServiceRequest;

/**
 * In-memory operational repository backed entirely by our custom data
 * structures. This is the working data used by the scheduling, routing and
 * search engines before persistence.
 *
 * @author UGMC Smart Operations Team
 */
public class OperationalStore {

    private final DynamicArray<Location> locations = new DynamicArray<>();
    private final DynamicArray<Road> roads = new DynamicArray<>();
    private final DynamicArray<ServiceRequest> requests = new DynamicArray<>();
    private final DynamicArray<Resource> resources = new DynamicArray<>();
    private final LinkedList<AlgorithmRun> runs = new LinkedList<>();

    // Indexes for O(1) lookups.
    private final HashTable<String, Location> locationIndex = new HashTable<>();
    private final HashTable<String, Resource> resourceIndex = new HashTable<>();

    public void indexLocations(DynamicArray<Location> data) {
        locations.clear();
        locationIndex.clear();
        for (Location l : data) {
            locations.add(l);
        }
        for (Location l : data) {
            locationIndex.put(l.getLocationId(), l);
        }
    }

    public void indexResources(DynamicArray<Resource> data) {
        resources.clear();
        resourceIndex.clear();
        for (Resource r : data) {
            resources.add(r);
        }
        for (Resource r : data) {
            resourceIndex.put(r.getResourceId(), r);
        }
    }

    public void setRoads(DynamicArray<Road> data) {
        roads.clear();
        for (Road r : data) {
            roads.add(r);
        }
    }

    public void setRequests(DynamicArray<ServiceRequest> data) {
        requests.clear();
        for (ServiceRequest q : data) {
            requests.add(q);
        }
    }

    public void addRun(AlgorithmRun run) {
        runs.addLast(run);
    }

    public DynamicArray<Location> getLocations() { return locations; }
    public DynamicArray<Road> getRoads() { return roads; }
    public DynamicArray<ServiceRequest> getRequests() { return requests; }
    public DynamicArray<Resource> getResources() { return resources; }
    public LinkedList<AlgorithmRun> getRuns() { return runs; }

    public Location findLocation(String id) { return locationIndex.get(id); }
    public Resource findResource(String id) { return resourceIndex.get(id); }
    public int locationCount() { return locationIndex.size(); }
    public int resourceCount() { return resourceIndex.size(); }
}
