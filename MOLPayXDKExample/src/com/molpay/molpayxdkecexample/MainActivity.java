package com.molpay.molpayxdkecexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashMap;

import com.molpay.molpayxdkeclipse.MOLPayActivity;
public class MainActivity extends AppCompatActivity {
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MOLPayActivity.MOLPayXDK && resultCode == RESULT_OK){
            Log.d(MOLPayActivity.MOLPAY, "MOLPay result = " + data.getStringExtra(MOLPayActivity.MOLPayTransactionResult));
            TextView tw = (TextView)findViewById(R.id.resultTV);
            tw.setText(data.getStringExtra(MOLPayActivity.MOLPayTransactionResult));
        }

    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
		
		HashMap<String, Object> paymentDetails = new HashMap<>();
		
		paymentDetails.put(MOLPayActivity.mp_amount, "1.10");
        paymentDetails.put(MOLPayActivity.mp_username, "molpayapi");
        paymentDetails.put(MOLPayActivity.mp_password, "*M0Lp4y4p1!*");
        paymentDetails.put(MOLPayActivity.mp_merchant_ID, "molpaymerchant");
        paymentDetails.put(MOLPayActivity.mp_app_name, "wilwe_makan2");
        paymentDetails.put(MOLPayActivity.mp_order_ID, "XP012");
        paymentDetails.put(MOLPayActivity.mp_currency, "MYR");
        paymentDetails.put(MOLPayActivity.mp_country, "MY");
        paymentDetails.put(MOLPayActivity.mp_verification_key, "501c4f508cf1c3f486f4f5c820591f41");
        paymentDetails.put(MOLPayActivity.mp_channel, "");
        paymentDetails.put(MOLPayActivity.mp_bill_description, "X-Platform debug");
        paymentDetails.put(MOLPayActivity.mp_bill_name, "Developer");
        paymentDetails.put(MOLPayActivity.mp_bill_email, "clewlb@gmail.com");
        paymentDetails.put(MOLPayActivity.mp_bill_mobile, "+1234567");
        paymentDetails.put(MOLPayActivity.mp_channel_editing, false);
        paymentDetails.put(MOLPayActivity.mp_editing_enabled, false);
        paymentDetails.put(MOLPayActivity.mp_transaction_id, "");//6713246
        paymentDetails.put(MOLPayActivity.mp_request_type, "");
        
        Intent intent = new Intent(MainActivity.this, MOLPayActivity.class);
        intent.putExtra(MOLPayActivity.MOLPayPaymentDetails, paymentDetails);
        startActivityForResult(intent, MOLPayActivity.MOLPayXDK);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
