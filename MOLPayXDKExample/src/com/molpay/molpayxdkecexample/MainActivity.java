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
		paymentDetails.put(MOLPayActivity.mp_amount, ""); // Mandatory
		paymentDetails.put(MOLPayActivity.mp_username, ""); // Mandatory
		paymentDetails.put(MOLPayActivity.mp_password, ""); // Mandatory
		paymentDetails.put(MOLPayActivity.mp_merchant_ID, ""); // Mandatory
		paymentDetails.put(MOLPayActivity.mp_app_name, ""); // Mandatory
		paymentDetails.put(MOLPayActivity.mp_order_ID, ""); // Mandatory
		paymentDetails.put(MOLPayActivity.mp_currency, ""); // Mandatory
		paymentDetails.put(MOLPayActivity.mp_country, ""); // Mandatory
		paymentDetails.put(MOLPayActivity.mp_verification_key, ""); // Mandatory
		paymentDetails.put(MOLPayActivity.mp_channel, ""); // Optional
		paymentDetails.put(MOLPayActivity.mp_bill_description, ""); // Optional
		paymentDetails.put(MOLPayActivity.mp_bill_name, ""); // Optional
		paymentDetails.put(MOLPayActivity.mp_bill_email, ""); // Optional
		paymentDetails.put(MOLPayActivity.mp_bill_mobile, ""); // Optional
		paymentDetails.put(MOLPayActivity.mp_channel_editing, false); // Option to allow channel selection.
		paymentDetails.put(MOLPayActivity.mp_editing_enabled, false); // Option to allow billing information editing.
		paymentDetails.put(MOLPayActivity.mp_transaction_id, ""); // Optional, provide a valid cash channel transaction id here will display a payment instruction screen.
		paymentDetails.put(MOLPayActivity.mp_request_type, ""); // Optional, set 'Status' when performing a transactionRequest
		
//		paymentDetails.put(MOLPayActivity.mp_is_escrow, ""); // Optional for escrow Put "1" to enable escrow
//		String binlock[] = {"123456","234567"}; // Optional for credit card BIN restrictions
//		paymentDetails.put(MOLPayActivity.mp_bin_lock, binlock); // Optional for credit card BIN restrictions
//      paymentDetails.put(MOLPayActivity.mp_bin_lock_err_msg, "Only UOB allowed");	// Optional for credit card BIN restrictions   
//	    paymentDetails.put(MOLPayActivity.mp_preferred_token, ""); // Optional, set the token id to nominate a preferred token as the default selection
//		paymentDetails.put(MOLPayActivity.mp_tcctype, ""); // Optional, credit card transaction type, set "AUTH" to authorize the transaction
//	    paymentDetails.put(MOLPayActivity.mp_is_recurring, true); // Optional, set true to process this transaction through the recurring api, please refer the MOLPay Recurring API pdf
//		String allowedChannels[] = {"credit","credit3"}; // Optional for channels restriction
//		paymentDetails.put(MOLPayActivity.mp_allowed_channels, allowedChannels); // Optional for channels restriction
//		paymentDetails.put(MOLPayActivity.mp_express_mode, true); // Optional, required a valid mp_channel value, this will skip the payment info page and go direct to the payment screen.
//		paymentDetails.put(MOLPayActivity.mp_sandbox_mode, true); // Optional for sandboxed development environment, set boolean value to enable.
//		paymentDetails.put(MOLPayActivity.mp_custom_css_url, "file:///android_asset/custom.css");
        
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
