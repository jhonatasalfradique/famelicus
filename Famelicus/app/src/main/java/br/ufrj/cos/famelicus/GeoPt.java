package br.ufrj.cos.famelicus;

import android.location.Location;

public class GeoPt {

    private Float lat;
    private Float lng;

    public GeoPt(Float lat, Float lng){
        set(lat, lng);
    }

    public Float getLat() { return lat;}
    public Float getLng(){return lng;}

    public void set(Float lat, Float lng){
        this.lat = lat;
        this.lng = lng;
    }

    public float distanceTo(GeoPt pt){
        float[] results = new float[]{};
        Location.distanceBetween(this.getLat(), this.getLng(), pt.getLat(), pt.getLng(), results);
        return results[0];
    }

    @Override
    public String toString(){
        return "latitude:" + lat +", longitude:"+lng;

    };
}
