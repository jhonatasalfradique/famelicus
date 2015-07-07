package br.ufrj.cos.famelicus;

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

    @Override
    public String toString(){
        return "latitude:" + lat +", longitude:"+lng;

    };
}
