public void onMapReady(GoogleMap googleMap) {
 mMap = googleMap;
 mLocationRequest = new LocationRequest();
 mLocationRequest.setInterval(1000);
 mLocationRequest.setFastestInterval(1000);

mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY
);
 if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
 if (ContextCompat.checkSelfPermission(this,
Manifest.permission.ACCESS_FINE_LOCATION) ==
PackageManager.PERMISSION_GRANTED) {
 } else {
 checkLocationPermission();
 }
 }
}
LocationCallback mLocationCallback =new LocationCallback(){
 @Override
 public void onLocationResult(LocationResult locationResult) {
 for(Location location : locationResult.getLocations()) {
 mLastLocation = location;
 LatLng latLng = new LatLng(location.getLatitude(),
location.getLongitude());
 mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
 mMap.animateCamera(CameraUpdateFactory.zoomTo(15)); 

private void checkLocationPermission(){
 if(ContextCompat.checkSelfPermission(this,
Manifest.permission.ACCESS_FINE_LOCATION) !=
PackageManager.PERMISSION_GRANTED) {
 if (ActivityCompat.shouldShowRequestPermissionRationale(this,
Manifest.permission.ACCESS_FINE_LOCATION)) {
 new AlertDialog.Builder(this)
 .setTitle("give permission")
 .setMessage("give permission message")
 .setPositiveButton("OK", new
DialogInterface.OnClickListener() {
 @Override
public void onClick(DialogInterface
dialogInterface, int i) {

ActivityCompat.requestPermissions(RiderMapActivity.this, new
String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
 }
 })
 .create()
 .show();
 } else {
 ActivityCompat.requestPermissions(RiderMapActivity.this, new
String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
 }
 }
}
@Override
public void onRequestPermissionsResult(int
requestCode, @NonNull String[] permissions, @NonNull
int[] grantResults) {
 super.onRequestPermissionsResult(requestCode,
permissions, grantResults);
 switch(requestCode){
 case 1:{
 if(grantResults.length >0 &&
grantResults[0] == PackageManager.PERMISSION_GRANTED){

if(ContextCompat.checkSelfPermission(this,
Manifest.permission.ACCESS_FINE_LOCATION) ==
PackageManager.PERMISSION_GRANTED){

mFusedLocationClient.requestLocationUpdates(mLocationR
equest, mLocationCallback, Looper.myLooper());
 mMap.setMyLocationEnabled(true);
 }
 } else{

Toast.makeText(getApplicationContext(), "Please
provide the permission", Toast.LENGTH_LONG).show();
 }
 break;
 }
 }
} 
