package com.example.exo3;

public class MainActivity extends AppCompatActivity {

    private ThermometerView thermometerView;
    private EditText temperatureInput;
    private Spinner scaleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thermometerView = findViewById(R.id.thermometer);
        temperatureInput = findViewById(R.id.temperature_input);
        scaleSpinner = findViewById(R.id.scale_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.scale_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scaleSpinner.setAdapter(adapter);

        scaleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String scale = (String)parent.getItemAtPosition(pos);
                thermometerView.setScale(scale);  // call the newly added setScale method
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        temperatureInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals("")){
                    float temperature = Float.parseFloat(s.toString());
                    thermometerView.setTemperature(temperature);
                }
            }
        });
    }
}
