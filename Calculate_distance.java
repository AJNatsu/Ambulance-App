private Marker mDriverMarker;
private DatabaseReference driverLocationRef;
private ValueEventListener driverLocationRefListener;
private void getDriverLocation() {
 DatabaseReference driverLocationRef =
FirebaseDatabase.getInstance().getReference().child("DriversMoving").child(driverFoundID).chil
d("l");
 driverLocationRef.addValueEventListener(new ValueEventListener() {
 @Override
 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
 if (dataSnapshot.exists()) {
double locationLat = 0;
 double locationLng = 0;
 if (map.get(0) != null) {
 locationLat =
Double.parseDouble(map.get(0).toString());
 }
 if (map.get(1) != null) {
 locationLng =
Double.parseDouble(map.get(1).toString());
 }
 LatLng driverLatLng = new LatLng(locationLat,
locationLng);
 if (mDriverMarker != null) {
 mDriverMarker.remove();
 }
 Location loc1 = new Location("");
 loc1.setLatitude(Locationdriving.latitude);
 loc1.setLongitude(Locationdriving.longitude);
 Location loc2 = new Location("");
 loc2.setLatitude(driverLatLng.latitude);
 loc2.setLongitude(driverLatLng.longitude);
 float distance = loc1.distanceTo(loc2);
 if (distance < 100) {
 mSearch.setText("Ambulance is just " +
String.valueOf(distance));
 displayNotification();
 } else {
 mSearch.setText("Driver Found: " +
String.valueOf(distance));
 }
 mDriverMarker = mMap.addMarker(new
MarkerOptions().position(driverLatLng).title("Ambulance Driver is
Moving"));
 }
 }
 @Override
 public void onCancelled(@NonNull DatabaseError databaseError) {
 }
 });
