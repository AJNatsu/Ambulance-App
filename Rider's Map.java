@Override
protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_driver_map);
   // Obtain the SupportMapFragment and get notified when the map is ready to be used.
 mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

 SupportMapFragment mapFragment = (SupportMapFragment)
getSupportFragmentManager()
 .findFragmentById(R.id.map);
 mapFragment.getMapAsync(this); 
