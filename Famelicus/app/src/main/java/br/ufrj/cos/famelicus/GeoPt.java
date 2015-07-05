package br.ufrj.cos.famelicus;

public class GeoPt {

    private  float latitude;
    private float longitude;

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString(){
        return "latitude:" + latitude +", longitude:"+longitude;

    };
}
