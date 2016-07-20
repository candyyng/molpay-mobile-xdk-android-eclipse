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
		
		HashMap<String, Object> paymentDetails = new HashMap<String, Object>();
		
		paymentDetails.put(MOLPayActivity.mp_amount, "");
        paymentDetails.put(MOLPayActivity.mp_username, "");
        paymentDetails.put(MOLPayActivity.mp_password, "");
        paymentDetails.put(MOLPayActivity.mp_merchant_ID, "");
        paymentDetails.put(MOLPayActivity.mp_app_name, "");
        paymentDetails.put(MOLPayActivity.mp_order_ID, "");
        paymentDetails.put(MOLPayActivity.mp_currency, "");
        paymentDetails.put(MOLPayActivity.mp_country, "");
        paymentDetails.put(MOLPayActivity.mp_verification_key, "");
        paymentDetails.put(MOLPayActivity.mp_channel, "");
        paymentDetails.put(MOLPayActivity.mp_bill_description, "");
        paymentDetails.put(MOLPayActivity.mp_bill_name, "");
        paymentDetails.put(MOLPayActivity.mp_bill_email, "");
        paymentDetails.put(MOLPayActivity.mp_bill_mobile, "");
        paymentDetails.put(MOLPayActivity.mp_channel_editing, false);
        paymentDetails.put(MOLPayActivity.mp_editing_enabled, false);
        paymentDetails.put(MOLPayActivity.mp_transaction_id, "");//6713246
        paymentDetails.put(MOLPayActivity.mp_request_type, "");
        
     // Optional, set the token id to nominate a preferred token as the default selection
     // paymentDetails.put(MOLPayActivity.mp_preferred_token, "");
        
        //String binlock[] = {"123456","234567"};
        //paymentDetails.put(MOLPayActivity.mp_bin_lock, binlock);
        //paymentDetails.put(MOLPayActivity.mp_bin_lock_err_msg, "Wrong BIN format");
        //paymentDetails.put(MOLPayActivity.mp_is_escrow, "");
        //paymentDetails.put(MOLPayActivity.mp_filter, "1");
        //paymentDetails.put(MOLPayActivity.mp_custom_css_url, "file:///android_asset/custom.css");
        
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
