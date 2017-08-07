package chapter03;

public final class CityBuilder {

    private String name;

    private String zipCode;

    private int population;

    private Climate climate;

    private double averageRainfall;

    public CityBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CityBuilder zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public CityBuilder population(int population) {
        this.population = population;
        return this;
    }

    public CityBuilder climate(Climate climate) {
        this.climate = climate;
        return this;
    }

    public CityBuilder averageRainfall(double averageRainfall) {
        this.averageRainfall = averageRainfall;
        return this;
    }

    public City build() {
        return new City(name, zipCode, population, climate, averageRainfall);
    }


}
