package com.ugmc.smartops.db;

import com.ugmc.smartops.datastructure.DynamicArray;
import com.ugmc.smartops.model.AlgorithmRun;
import com.ugmc.smartops.model.Location;
import com.ugmc.smartops.model.Resource;
import com.ugmc.smartops.model.Road;
import com.ugmc.smartops.model.ServiceRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * File-based placeholder persistence used until the SQLite driver is added.
 * Records are stored as simple pipe-delimited text files under a data dir.
 *
 * This satisfies the "runnable today with no external dependencies" goal and
 * will be replaced by {@code SqliteDatabase} once the JDBC driver is available.
 *
 * @author UGMC Smart Operations Team
 */
public class FileDatabase implements Database {

    private final Path dataDir;

    public FileDatabase(String dataDir) {
        this.dataDir = Paths.get(dataDir);
    }

    public FileDatabase() {
        this("data");
    }

    @Override
    public void connect() throws IOException {
        Files.createDirectories(dataDir);
    }

    @Override
    public void disconnect() {
        // no-op for file persistence
    }

    // --- Writers ---

    @Override
    public void saveLocations(DynamicArray<Location> locations) throws IOException {
        try (BufferedWriter w = Files.newBufferedWriter(dataDir.resolve("locations.data"))) {
            for (Location l : locations) {
                w.write(l.getLocationId() + "|" + l.getName() + "|" + l.getArea()
                        + "|" + l.getLocationType() + "|" + l.getXCoord() + "|" + l.getYCoord());
                w.newLine();
            }
        }
    }

    @Override
    public void saveRoads(DynamicArray<Road> roads) throws IOException {
        try (BufferedWriter w = Files.newBufferedWriter(dataDir.resolve("roads.data"))) {
            for (Road r : roads) {
                w.write(r.getRoadId() + "|" + r.getFromLocationId() + "|" + r.getToLocationId()
                        + "|" + r.getDistanceKm() + "|" + r.getTravelTimeMin()
                        + "|" + r.getConditionWeight());
                w.newLine();
            }
        }
    }

    @Override
    public void saveServiceRequests(DynamicArray<ServiceRequest> requests) throws IOException {
        try (BufferedWriter w = Files.newBufferedWriter(dataDir.resolve("service_requests.data"))) {
            for (ServiceRequest q : requests) {
                w.write(q.getRequestId() + "|" + q.getSourceLocationId() + "|"
                        + q.getDestinationLocationId() + "|" + q.getCategory() + "|"
                        + q.getUrgency() + "|" + q.getTimeSubmitted() + "|"
                        + q.getDeadline() + "|" + q.getStatus());
                w.newLine();
            }
        }
    }

    @Override
    public void saveResources(DynamicArray<Resource> resources) throws IOException {
        try (BufferedWriter w = Files.newBufferedWriter(dataDir.resolve("resources.data"))) {
            for (Resource r : resources) {
                w.write(r.getResourceId() + "|" + r.getResourceType() + "|"
                        + r.getHomeLocationId() + "|" + r.getCapacity() + "|"
                        + r.getAvailabilityStatus());
                w.newLine();
            }
        }
    }

    @Override
    public void saveAlgorithmRun(AlgorithmRun run) throws IOException {
        Path file = dataDir.resolve("algorithm_runs.data");
        boolean exists = Files.exists(file);
        try (BufferedWriter w = Files.newBufferedWriter(
                file, java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND)) {
            if (!exists) {
                w.write("runId|algorithmName|inputSize|timeNs|memoryKb|dateRun");
                w.newLine();
            }
            w.write(run.getRunId() + "|" + run.getAlgorithmName() + "|"
                    + run.getInputSize() + "|" + run.getTimeNs() + "|"
                    + run.getMemoryKb() + "|" + run.getDateRun());
            w.newLine();
        }
    }

    // --- Readers ---

    @Override
    public DynamicArray<Location> loadLocations() throws IOException {
        DynamicArray<Location> out = new DynamicArray<>();
        Path f = dataDir.resolve("locations.data");
        if (!Files.exists(f)) return out;
        for (String line : Files.readAllLines(f)) {
            if (line.isBlank()) continue;
            String[] p = line.split("\\|");
            out.add(new Location(p[0], p[1], p[2], p[3],
                    Double.parseDouble(p[4]), Double.parseDouble(p[5])));
        }
        return out;
    }

    @Override
    public DynamicArray<Road> loadRoads() throws IOException {
        DynamicArray<Road> out = new DynamicArray<>();
        Path f = dataDir.resolve("roads.data");
        if (!Files.exists(f)) return out;
        for (String line : Files.readAllLines(f)) {
            if (line.isBlank()) continue;
            String[] p = line.split("\\|");
            out.add(new Road(p[0], p[1], p[2],
                    Double.parseDouble(p[3]), Double.parseDouble(p[4]), Double.parseDouble(p[5])));
        }
        return out;
    }

    @Override
    public DynamicArray<ServiceRequest> loadServiceRequests() throws IOException {
        DynamicArray<ServiceRequest> out = new DynamicArray<>();
        Path f = dataDir.resolve("service_requests.data");
        if (!Files.exists(f)) return out;
        for (String line : Files.readAllLines(f)) {
            if (line.isBlank()) continue;
            String[] p = line.split("\\|");
            out.add(new ServiceRequest(p[0], p[1], p[2], p[3], Integer.parseInt(p[4]),
                    p[5], p[6], p[7]));
        }
        return out;
    }

    @Override
    public DynamicArray<Resource> loadResources() throws IOException {
        DynamicArray<Resource> out = new DynamicArray<>();
        Path f = dataDir.resolve("resources.data");
        if (!Files.exists(f)) return out;
        for (String line : Files.readAllLines(f)) {
            if (line.isBlank()) continue;
            String[] p = line.split("\\|");
            out.add(new Resource(p[0], p[1], p[2], Integer.parseInt(p[3]), p[4]));
        }
        return out;
    }

    @Override
    public DynamicArray<AlgorithmRun> loadAlgorithmRuns() throws IOException {
        DynamicArray<AlgorithmRun> out = new DynamicArray<>();
        Path f = dataDir.resolve("algorithm_runs.data");
        if (!Files.exists(f)) return out;
        for (String line : Files.readAllLines(f)) {
            if (line.isBlank() || line.startsWith("runId")) continue;
            String[] p = line.split("\\|");
            out.add(new AlgorithmRun(p[0], p[1], Integer.parseInt(p[2]),
                    Long.parseLong(p[3]), Long.parseLong(p[4]), p[5]));
        }
        return out;
    }
}
