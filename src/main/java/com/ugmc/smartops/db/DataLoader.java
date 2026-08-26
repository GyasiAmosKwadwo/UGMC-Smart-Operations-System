package com.ugmc.smartops.db;

import com.ugmc.smartops.datastructure.DynamicArray;
import com.ugmc.smartops.model.AlgorithmRun;
import com.ugmc.smartops.model.Location;
import com.ugmc.smartops.model.Resource;
import com.ugmc.smartops.model.Road;
import com.ugmc.smartops.model.ServiceRequest;
import com.ugmc.smartops.util.CsvReader;
import java.io.IOException;

/**
 * Loads the CSV template files into model objects and persists them through
 * a {@link Database} implementation.
 *
 * @author UGMC Smart Operations Team
 */
public class DataLoader {

    private final Database db;

    public DataLoader(Database db) {
        this.db = db;
    }

    /**
     * Loads all CSV templates from the given directory into the database.
     *
     * @param csvDir directory containing the CSV template files
     * @return a summary message of what was imported
     */
    public String loadTemplates(String csvDir) throws IOException, Exception {
        DynamicArray<Location> locations = loadLocations(csvDir);
        DynamicArray<Road> roads = loadRoads(csvDir);
        DynamicArray<ServiceRequest> requests = loadServiceRequests(csvDir);
        DynamicArray<Resource> resources = loadResources(csvDir);

        db.saveLocations(locations);
        db.saveRoads(roads);
        db.saveServiceRequests(requests);
        db.saveResources(resources);

        return "Imported " + locations.size() + " locations, " + roads.size()
                + " roads, " + requests.size() + " service requests, "
                + resources.size() + " resources.";
    }

    public DynamicArray<Location> loadLocations(String dir) throws IOException {
        DynamicArray<Location> out = new DynamicArray<>();
        DynamicArray<DynamicArray<String>> rows =
                CsvReader.readAll(dir + "/locations_template.csv", true);
        for (DynamicArray<String> row : rows) {
            requireColumns(row, 6, "location");
            out.add(new Location(
                    row.get(0), row.get(1), row.get(2), row.get(3),
                    Double.parseDouble(row.get(4)), Double.parseDouble(row.get(5))));
        }
        return out;
    }

    public DynamicArray<Road> loadRoads(String dir) throws IOException {
        DynamicArray<Road> out = new DynamicArray<>();
        DynamicArray<DynamicArray<String>> rows =
                CsvReader.readAll(dir + "/roads_template.csv", true);
        for (DynamicArray<String> row : rows) {
            requireColumns(row, 6, "road");
            out.add(new Road(
                    row.get(0), row.get(1), row.get(2),
                    Double.parseDouble(row.get(3)), Double.parseDouble(row.get(4)),
                    Double.parseDouble(row.get(5))));
        }
        return out;
    }

    public DynamicArray<ServiceRequest> loadServiceRequests(String dir) throws IOException {
        DynamicArray<ServiceRequest> out = new DynamicArray<>();
        DynamicArray<DynamicArray<String>> rows =
                CsvReader.readAll(dir + "/service_requests_template.csv", true);
        for (DynamicArray<String> row : rows) {
            requireColumns(row, 8, "service request");
            out.add(new ServiceRequest(
                    row.get(0), row.get(1), row.get(2), row.get(3),
                    Integer.parseInt(row.get(4)), row.get(5), row.get(6), row.get(7)));
        }
        return out;
    }

    public DynamicArray<Resource> loadResources(String dir) throws IOException {
        DynamicArray<Resource> out = new DynamicArray<>();
        DynamicArray<DynamicArray<String>> rows =
                CsvReader.readAll(dir + "/resources_template.csv", true);
        for (DynamicArray<String> row : rows) {
            requireColumns(row, 5, "resource");
            out.add(new Resource(
                    row.get(0), row.get(1), row.get(2),
                    Integer.parseInt(row.get(3)), row.get(4)));
        }
        return out;
    }

    public void recordRun(AlgorithmRun run) throws Exception {
        db.saveAlgorithmRun(run);
    }

    private void requireColumns(DynamicArray<String> row, int expected, String entity)
            throws IOException {
        if (row.size() < expected) {
            throw new IOException("Invalid " + entity + " row: expected "
                    + expected + " columns but found " + row.size());
        }
    }
}
