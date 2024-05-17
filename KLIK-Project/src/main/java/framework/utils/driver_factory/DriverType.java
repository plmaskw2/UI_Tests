package framework.utils.driver_factory;

public enum DriverType {
    FIREFOX("Firefox"),
    CHROME("Chrome");

    private final String driverName;

    DriverType(String driverName) {
        this.driverName = driverName;
    }
}

