package edu.dtcc.nhollowa.jeansfactory;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";
    Spinner spinner;
    Spinner lengthSpinner;
    Spinner styleSpinner;
    String orderWaist = "";
    String orderLength = "";
    String orderStyle = "";
    Button orderButton;
    Button cancelButton;
    TextView orderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        orderView = (TextView)findViewById(R.id.orderView);

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.waist, R.layout.spinner_layout);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        lengthSpinner = (Spinner) findViewById(R.id.lengthSpinner);


        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.length, R.layout.spinner_layout);
        lengthSpinner.setAdapter(adapter2);
        lengthSpinner.setOnItemSelectedListener(this);

        styleSpinner = (Spinner) findViewById(R.id.styleSpinner);


        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.style, R.layout.spinner_layout);
        styleSpinner.setAdapter(adapter3);
        styleSpinner.setOnItemSelectedListener(this);

        orderButton = (Button)findViewById(R.id.orderButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);



        orderButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){



                if (v.equals(findViewById(R.id.orderButton))) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Confirm Order");

                    String orderMsg = "You Ordered: "+orderWaist+ " waist size\n\t"+orderLength+ " Length \n\t"+orderStyle+" Style";
                    orderView.setText(orderMsg);
                    dialog.setMessage(orderMsg).setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                            {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int which){
                                    String msg = "Thank you for your purchase,\ncome again!";
                                    orderView.setText(msg);
                                    dialogInterface.dismiss();

                                }
                            })

                            .setNegativeButton("No", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which){
                                    dialogInterface.cancel();

                                }
                            });

                    AlertDialog alert = dialog.create();
                    alert.show();

            }
        }});
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if (v.equals(findViewById(R.id.cancelButton))) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Confirm Cancellation");
                    String orderMsg = "Cancel this order?:\n "+orderWaist+ " waist size\n\t"+orderLength+ " Length \n\t"+orderStyle+" Style";
                    dialog.setMessage(orderMsg).setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                     {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int which){
                            String msg = "We're always here when\n you want to come back! \nBye!";
                            orderView.setText(msg);
                            dialogInterface.dismiss();

                        }
                            })

                            .setNegativeButton("No", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which){
                                    dialogInterface.cancel();

                                }
                            });


                    AlertDialog alert = dialog.create();
                    alert.show();

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        try {
            if (parent.equals(spinner)&& position != 0) {
                TextView myText = (TextView) view;
                orderWaist= (String)(parent.getItemAtPosition(position));
                Log.d(TAG, "onItemSelected: " + orderWaist);
                Toast.makeText(this, "You selected " + myText.getText().toString(), Toast.LENGTH_LONG).show();

            }
            if (parent.equals(lengthSpinner ) && position != 0) {
                 TextView myText = (TextView) view;
                orderLength=(String)(parent.getItemAtPosition(position));
                Toast.makeText(this, "You selected " + myText.getText().toString(), Toast.LENGTH_LONG).show();

            }
            if (parent.equals(styleSpinner) && position != 0) {
                TextView myText = (TextView) view;
                orderStyle=(String)(parent.getItemAtPosition(position));
                Toast.makeText(this, "You selected " + myText.getText().toString(), Toast.LENGTH_LONG).show();
            }


        } catch (Exception e) {
            onNothingSelected(parent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        //Toast.makeText(this, getString(R.string.emptyString), Toast.LENGTH_LONG).show();

    }
}