package ru.ssau.tk.collectionclasses;

import java.util.*;

import ru.ssau.tk.personclasses.Gender;

public class CompanyModel {
    private final Collection<Location> allLocations;
    private final Collection<Route> allRoutes;
    private final Collection<Driver> allDrivers;
    private int maxId = 0;
    private final Map<Driver, Route> driverRouteMap;

    public CompanyModel() {
        allLocations = new LinkedHashSet<>();
        allRoutes = new LinkedHashSet<>();
        allDrivers = new LinkedHashSet<>();
        driverRouteMap = new LinkedHashMap<>();
    }

    public Collection<Location> getAllLocations() {
        return allLocations;
    }

    public Collection<Route> getAllRoutes() {
        return allRoutes;
    }

    public Collection<Driver> getAllDrivers() {
        return allDrivers;
    }

    public Settlement addSettlement(String name, double latitude, double longitude, SettlementType type, int population) {
        Settlement settlement = new Settlement();
        settlement.setId(++maxId);
        settlement.setName(name);
        settlement.setLatitude(latitude);
        settlement.setLongitude(longitude);
        settlement.setType(type);
        settlement.setSettlement(population);
        allLocations.add(settlement);
        return settlement;
    }

    public Waypoint addWaypoint(String name, double latitude, double longitude, WaypointType type, Settlement settlement) {
        Waypoint waypoint = new Waypoint();
        waypoint.setId(++maxId);
        waypoint.setName(name);
        waypoint.setLatitude(latitude);
        waypoint.setLongitude(longitude);
        waypoint.setType(type);
        waypoint.setSettlement(settlement);
        allLocations.add(waypoint);
        return waypoint;
    }

    public Driver addDriver(String name, Gender gender, Date licenseExpirationDate) {
        Driver driver = new Driver();
        driver.setId(++maxId);
        driver.setName(name);
        driver.setGender(gender);
        driver.setLicenseExpirationDate(licenseExpirationDate);
        allDrivers.add(driver);
        return driver;
    }

    public Route addRoute(List<Location> locations) {
        Route route = new Route();
        for (Location location : locations) {
            route.addLocation(location);
        }
        allRoutes.add(route);
        return route;
    }

    public Map<Driver, Route> getDriverRouteMap() {
        return driverRouteMap;
    }

    public void assignRoute(Driver driver, Route route) {
        driverRouteMap.put(driver, route);
    }

    public static void sort(List<Settlement> settlements, Comparator<? super Settlement> comparator) {
        Collections.sort(settlements, comparator);
    }

    public static void sortByName(List<? extends Location> locations) {
        Collections.sort(locations, ((o1, o2) -> CharSequence.compare(o1.getName(), o2.getName())));
    }
}
