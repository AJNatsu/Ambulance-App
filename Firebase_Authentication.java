private EditText mEmail, mPassword;
private Button mLogin, mRegistration;
private FirebaseAuth mAuth;
private FirebaseAuth.AuthStateListener firebaseAuthListener;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_driver_login);

    mAuth = FirebaseAuth.getInstance();

    firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        }
    };

    mLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String email = mEmail.getText().toString();
            final String password = mPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(RiderLoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(DriverLoginActivity.this, "sign in error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    });

    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (!task.isSuccessful()) {
                Toast.makeText(DriverLoginActivity.this, "sign up error", Toast.LENGTH_SHORT).show();
            } else {
                String user_id = mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(user_id);
                current_user_db.setValue(true);
            }
        }
    });
}

@Override
protected void onStart() {
    super.onStart();
    mAuth.addAuthStateListener(firebaseAuthListener);
}
