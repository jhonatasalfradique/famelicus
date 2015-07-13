package br.ufrj.cos.famelicus;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class InterfaceGPS {

	private boolean Ligado;
	private GoogleApiClient mClient;
	private Location mLastLocation;
	private LocationRequest mLocationRequest;
	private PendingIntent pendingIntent;
	private Context context;

	public InterfaceGPS(Context context){
		this.context = context;
//        buildGoogleAPI();
//        createLocationRequest();
	}

//
//
//    public void connect(){
//
//    }
//
//    public void disconnect(){
//
//    }
//
//	public void buildGoogleAPI(){
//		mClient = new GoogleApiClient.Builder(this.context)
//				.addConnectionCallbacks(this)
//				.addOnConnectionFailedListener(this)
//				.addApi(LocationServices.API)
//				.build();
//	}
//
//	protected void createLocationRequest() {
//		mLocationRequest = new LocationRequest();
//		mLocationRequest.setInterval(1000*10);
//		mLocationRequest.setFastestInterval(1000*5);
//		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//	}
//
//	protected void startUpdates(){
//		PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
//				mClient, mLocationRequest, this);
//	}
//	protected void stopUpdates(){
//		LocationServices.FusedLocationApi.removeLocationUpdates(
//				mClient, this);
//	}
//
//	protected void startBackgroundUpdates(){
//                LocationServices.FusedLocationApi.requestLocationUpdates(
//                mClient, mLocationRequest, pendingIntent);
//    }
//
//    protected void stopBackgroundUpdates(){
//        LocationServices.FusedLocationApi. removeLocationUpdates(
//                mClient, pendingIntent);
//    }
//
//	public GeoPt Localizacao() {
//		return null;
//	}
//
//	public void TestarSinal() {
//
//    }
//
//	public void AtivarGPS(boolean ligar) {
//
//	}
//
}
