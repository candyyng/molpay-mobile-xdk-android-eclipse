<!--
# license: Copyright © 2011-2016 MOLPay Sdn Bhd. All Rights Reserved. 
-->

#  molpay-mobile-xdk-android-eclipse

This is the complete and functional MOLPay Android (Eclipse) payment module that is ready to be implemented into the Eclipse IDE by importing the library molpayXdkEclipse into project workspace. An example application project (MOLPayXDKExample) is provided for MOLPayXDK framework integration reference.

## Recommended configurations

    - Minimum Android SDK Version: 23 ++
    
    - Minimum Android API level: 16 ++
    
    - Minimum Android target version: Android 4.1

## MOLPay Android Caveats

    Credit card payment channel is not available in Android 4.1, 4.2, and 4.3. due to lack of latest security (TLS 1.2) support on these Android platforms natively.

## Installation

    Step 1 - Download molpayXdkEclipse and import it into project workspace. Note that molpayXdkEclipse requires third party library 'android-support-v7-appcompat'.

    Step 2 - Add molpayXdkEclipse as reference library in project properties.

    Step 3 - Add MOLPayActivity declaration into project manifest.
    <activity android:name="com.molpay.molpayxdkeclipse.MOLPayActivity"
              android:label="MOLPay"/>

    Step 4 - Copy and paste molpay-mobile-xdk-www folder (can be separately downloaded at https://github.com/MOLPay/molpay-mobile-xdk-www) into the assets\ folder of your project.

    Step 5 - Copy and paste custom.css into the assets\ folder of your project.

    Step 6 - Add the result callback function to get returned results when the payment activity ended.
    
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
    
    =========================================
    Sample transaction result in JSON string:
    =========================================
    
    {"status_code":"11","amount":"1.01","chksum":"34a9ec11a5b79f31a15176ffbcac76cd","pInstruction":0,"msgType":"C6","paydate":1459240430,"order_id":"3q3rux7dj","err_desc":"","channel":"Credit","app_code":"439187","txn_ID":"6936766"}
    
    Parameter and meaning:
    
    "status_code" - "00" for Success, "11" for Failed, "22" for *Pending. 
    (*Pending status only applicable to cash channels only)
    "amount" - The transaction amount
    "paydate" - The transaction date
    "order_id" - The transaction order id
    "channel" - The transaction channel description
    "txn_ID" - The transaction id generated by MOLPay
    
    * Notes: You may ignore other parameters and values not stated above
    
    =====================================
    * Sample error result in JSON string:
    =====================================
    
    {"Error":"Communication Error"}
    
    Parameter and meaning:
    
    "Communication Error" - Error starting a payment process due to several possible reasons, please contact MOLPay support should the error persists.
    1) Internet not available
    2) API credentials (username, password, merchant id, verify key)
    3) MOLPay server offline.

## Prepare the Payment detail object

    HashMap<String, Object> paymentDetails = new HashMap<>();

    // Mandatory String. A value more than '1.00'
    paymentDetails.put(MOLPayActivity.mp_amount, ""); 

    // Mandatory String. Values obtained from MOLPay
    paymentDetails.put(MOLPayActivity.mp_username, "");
    paymentDetails.put(MOLPayActivity.mp_password, "");
    paymentDetails.put(MOLPayActivity.mp_merchant_ID, "");
    paymentDetails.put(MOLPayActivity.mp_app_name, "");
    paymentDetails.put(MOLPayActivity.mp_verification_key, "");

    // Mandatory String. Payment values
    paymentDetails.put(MOLPayActivity.mp_order_ID, "");
    paymentDetails.put(MOLPayActivity.mp_currency, "MYR");
    paymentDetails.put(MOLPayActivity.mp_country, "MY");

    // Optional String.
    paymentDetails.put(MOLPayActivity.mp_channel, ""); // Use 'multi' for all available channels option. For individual channel seletion, please refer to "Channel Parameter" in "Channel Lists" in the MOLPay API Spec for Merchant pdf. 
    paymentDetails.put(MOLPayActivity.mp_bill_description, "");
    paymentDetails.put(MOLPayActivity.mp_bill_name, "");
    paymentDetails.put(MOLPayActivity.mp_bill_email, "");
    paymentDetails.put(MOLPayActivity.mp_bill_mobile, "");
    paymentDetails.put(MOLPayActivity.mp_channel_editing, false); // Option to allow channel selection.
    paymentDetails.put(MOLPayActivity.mp_editing_enabled, false); // Option to allow billing information editing.

    // Optional for Escrow
    paymentDetails.put(MOLPayActivity.mp_is_escrow, ""); // Put "1" to enable escrow

    // Optional for credit card BIN restrictions
    String binlock[] = { "", "" };
    paymentDetails.put(MOLPayActivity.mp_bin_lock, binlock);
    paymentDetails.put(MOLPayActivity.mp_bin_lock_err_msg, "");

    // For transaction request use only, do not use this on payment process
    paymentDetails.put(MOLPayActivity.mp_transaction_id, ""); // Optional, provide a valid cash channel transaction id here will display a payment instruction screen.
    paymentDetails.put(MOLPayActivity.mp_request_type, "");

    // Optional, use this to customize the UI theme for the payment info screen, the original XDK custom.css file is provided at Example project source for reference and implementation.
    paymentDetails.put(MOLPayActivity.mp_custom_css_url, "file:///android_asset/custom.css");

    // Optional, set the token id to nominate a preferred token as the default selection, set "new" to allow new card only
    paymentDetails.put(MOLPayActivity.mp_preferred_token, "");

    // Optional, credit card transaction type, set "AUTH" to authorize the transaction
    paymentDetails.put(MOLPayActivity.mp_tcctype, "");

    // Optional, set true to process this transaction through the recurring api, please refer the MOLPay Recurring API pdf 
    paymentDetails.put(MOLPayActivity.mp_is_recurring, false);

    // Optional for channels restriction
    String allowedchannels[] = { "", "" };
    paymentDetails.put(MOLPayActivity.mp_allowed_channels, allowedchannels);

    // Optional for sandboxed development environment, set boolean value to enable.
    paymentDetails.put(MOLPayActivity.mp_sandbox_mode, false);

    // Optional, required a valid mp_channel value, this will skip the payment info page and go direct to the payment screen.
    paymentDetails.put(MOLPayActivity.mp_express_mode, false);

    // Optional, enable this for extended email format validation based on W3C standards.
    paymentDetails.put(MOLPayActivity.mp_advanced_email_validation_enabled, false);

    // Optional, enable this for extended phone format validation based on Google i18n standards.
    paymentDetails.put(MOLPayActivity.mp_advanced_phone_validation_enabled, false);



## Start the payment module

    Intent intent = new Intent(MainActivity.this, MOLPayActivity.class);
    intent.putExtra(MOLPayActivity.MOLPayPaymentDetails, paymentDetails);
    startActivityForResult(intent, MOLPayActivity.MOLPayXDK);

## Cash channel payment process (How does it work?)

    This is how the cash channels work on XDK:
    
    1) The user initiate a cash payment, upon completed, the XDK will pause at the “Payment instruction” screen, the results would return a pending status.
    
    2) The user can then click on “Close” to exit the MOLPay XDK aka the payment screen.
    
    3) When later in time, the user would arrive at say 7-Eleven to make the payment, the host app then can call the XDK again to display the “Payment Instruction” again, then it has to pass in all the payment details like it will for the standard payment process, only this time, the host app will have to also pass in an extra value in the payment details, it’s the “mp_transaction_id”, the value has to be the same transaction returned in the results from the XDK earlier during the completion of the transaction. If the transaction id provided is accurate, the XDK will instead show the “Payment Instruction" in place of the standard payment screen.
    
    4) After the user done the paying at the 7-Eleven counter, they can close and exit MOLPay XDK by clicking the “Close” button again.

## XDK built-in checksum validator caveats 

    All XDK come with a built-in checksum validator to validate all incoming checksums and return the validation result through the "mp_secured_verified" parameter. However, this mechanism will fail and always return false if merchants are implementing the private secret key (which the latter is highly recommended and prefereable.) If you would choose to implement the private secret key, you may ignore the "mp_secured_verified" and send the checksum back to your server for validation. 

## Private Secret Key checksum validation formula

    chksum = MD5(mp_merchant_ID + results.msgType + results.txn_ID + results.amount + results.status_code + merchant_private_secret_key)

## Support

Submit issue to this repository or email to our support@molpay.com

Merchant Technical Support / Customer Care : support@molpay.com<br>
Sales/Reseller Enquiry : sales@molpay.com<br>
Marketing Campaign : marketing@molpay.com<br>
Channel/Partner Enquiry : channel@molpay.com<br>
Media Contact : media@molpay.com<br>
R&D and Tech-related Suggestion : technical@molpay.com<br>
Abuse Reporting : abuse@molpay.com<br>
