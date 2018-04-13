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

public class AddCelphoneActivity extends AppCompatActivity {

    private EditText mMarkEditText;
    private EditText mModelEditText;
    private EditText mColorEditText;
    private EditText mMemoryEditText;
    private EditText mCostEditText;
    private EditText mCompanyEditText;
    private Button mAddBtn;

    private CelphoneDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_celphone);

        //init
        mMarkEditText = (EditText)findViewById(R.id.celphone_mark);
        mModelEditText = (EditText)findViewById(R.id.celphone_model);
        mColorEditText = (EditText)findViewById(R.id.celphone_color);
        mMemoryEditText = (EditText)findViewById(R.id.celphone_memory);
        mCostEditText = (EditText)findViewById(R.id.celphone_cost);
        mCompanyEditText = (EditText)findViewById(R.id.celphone_company);
        mAddBtn = (Button)findViewById(R.id.add_new_celphone_button);

        //listen to add button click
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save car method
                saveCelphone();
            }
        });

    }

    private void saveCelphone(){

        String mark = mMarkEditText.getText().toString().trim();
        String model = mModelEditText.getText().toString().trim();
        String color =mColorEditText.getText().toString().trim();
        String memory = mMemoryEditText.getText().toString().trim();
        String cost = mCostEditText.getText().toString().trim();
        String company = mCompanyEditText.getText().toString().trim();
        dbHelper = new CelphoneDBHelper(this);


        if(mark.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an mark", Toast.LENGTH_SHORT).show();
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

        //create new celphone
        Celphone celphone = new Celphone( mark, model, color, memory, cost, company);
        dbHelper.saveNewCelphone(celphone);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(AddCelphoneActivity.this, MainActivity.class));
    }
}
