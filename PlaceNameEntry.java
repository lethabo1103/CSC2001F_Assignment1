public class PlaceNameEntry {
    private String placeName;
    private String municipality;
    private String province;
    private int population;

   public PlaceNameEntry(String placeName, String municipality, String province, int population) {
        this.placeName = placeName;
        this.municipality = municipality;           //constructor
        this.province = province;
        this.population = population;
    }
      public String getPlaceName() {
        return placeName;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getProvince() {
        return province;
    }

    public int getPopulation() {
        return population;
    }
     @Override
    public String toString() {                      //string to output the details properly
        return placeName + ", " + municipality + ", " + province + ", " + population;
    }
}