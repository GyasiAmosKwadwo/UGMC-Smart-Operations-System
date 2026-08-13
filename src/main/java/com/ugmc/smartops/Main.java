package com.ugmc.smartops;

import com.ugmc.smartops.db.Database;
import com.ugmc.smartops.db.FileDatabase;

/**
 * Entry point for the UGMC Smart Operations System.
 *
 * Usage:
 *   java -cp out com.ugmc.smartops.Main [dataDir]
 *
 * The optional argument overrides the persistence directory
 * (defaults to "data").
 *
 * @author UGMC Smart Operations Team
 */
public class Main {

    public static void main(String[] args) {
        Database db = new FileDatabase(args.length > 0 ? args[0] : "data");
        try {
            db.connect();
        } catch (Exception e) {
            System.err.println("Failed to initialise persistence: " + e.getMessage());
            System.exit(1);
        }

        ConsoleApp app = new ConsoleApp(db);
        app.run();

        try {
            db.disconnect();
        } catch (Exception e) {
            System.err.println("Failed to close persistence: " + e.getMessage());
        }
    }
}
