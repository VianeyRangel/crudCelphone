package mx.edu.utng.dloza.crudcelphone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import mx.edu.utng.dloza.model.Celphone;
import mx.edu.utng.dloza.util.CelphoneDBHelper;

public class UpdateCelphoneActivity extends AppCompatActivity {

    private EditText mMarkEditText;
    private EditText mModelEditText;
    private EditText mColorEditText;
    private EditText mMemoryEditText;
    private EditText mCostEditText;
    private EditText mCompanyEditText;
    private Button mUpdateBtn;

    private CelphoneDBHelper dbHelper;
    private long receivedCelphoneId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_celphone);

        //init
        mMarkEditText = (EditText)findViewById(R.id.celphone_mark_update);
        mModelEditText = (EditText)findViewById(R.id.celphone_model_update);
        mColorEditText = (EditText)findViewById(R.id.celphone_color_update);
        mMemoryEditText = (EditText)findViewById(R.id.celphone_memory_update);
        mCostEditText = (EditText)findViewById(R.id.celphone_cost_update);
        mCompanyEditText = (EditText)findViewById(R.id.celphone_company_update);
        mUpdateBtn = (Button)findViewById(R.id.update_celphone_button);

        dbHelper = new CelphoneDBHelper(this);

        try {
            //get intent to get car id
            receivedCelphoneId = getIntent().getLongExtra("CELPHONE_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***populate car data before update***/
        Celphone queriedCelphone = dbHelper.getCelphone(receivedCelphoneId);
        //set field to this user data
        mMarkEditText.setText(queriedCelphone.getMark());
        mModelEditText.setText(queriedCelphone.getModel());
        mColorEditText.setText(queriedCelphone.getColor());
        mMemoryEditText.setText(queriedCelphone.getMemory());
        mCostEditText.setText(queriedCelphone.getCost());
        mCompanyEditText.setText(queriedCelphone.getCompany());

        //listen to add button click to update
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save person method
                updateCelphone();
            }
        });
    }

    private void updateCelphone(){
        String mark = mMarkEditText.getText().toString().trim();
        String model = mModelEditText.getText().toString().trim();
        String color = mColorEditText.getText().toString().trim();
        String memory = mMemoryEditText.getText().toString().trim();
        String cost = mCostEditText.getText().toString().trim();
        String company = mCompanyEditText.getText().toString().trim();

        if(mark.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a mark", Toast.LENGTH_SHORT).show();
        }

        if(model.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an model", Toast.LENGTH_SHORT).show();
        }

        if(color.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an color", Toast.LENGTH_SHORT).show();
        }

        if(memory.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an memory", Toast.LENGTH_SHORT).show();
        }

        if(cost.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an cost", Toast.LENGTH_SHORT).show();
        }

        if(company.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an company", Toast.LENGTH_SHORT).show();
        }


        Celphone updatedCelphone = new Celphone(mark, model, color, memory, cost, company);

        //call dbhelper update
        dbHelper.setUpdatedcelphoneRecord(receivedCelphoneId, this, updatedCelphone);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
