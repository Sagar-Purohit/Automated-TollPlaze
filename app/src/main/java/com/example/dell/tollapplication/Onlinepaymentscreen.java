package com.example.dell.tollapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class Onlinepaymentscreen extends AppCompatActivity {
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX
            ;

    // note that these credentials will differ between live & sandbox environments.
    private static final String CONFIG_CLIENT_ID = "AaMLXwYyNsi3OrdW-cFN4rO77ybW-J8mKAIWB8iemq9Atss9QOTXWt0heC6ABKcDzTH2t4F3qzuzL99y";

    private static final int REQUEST_CODE_PAYMENT = 1;
    private static PayPalConfiguration config;
    String TAG="paypal";

    Button btnpayment;
    PayPalPayment thingToBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinepaymentscreen);
        final  int price=250;
        config = new PayPalConfiguration()
                .environment(CONFIG_ENVIRONMENT)
                .clientId(CONFIG_CLIENT_ID)
                // The following are only used in PayPalFuturePaymentActivity.
                .merchantName("iCreate")
                .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
                .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
        thingToBuy = new PayPalPayment(new BigDecimal(price+""), "USD", "Painting 1", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent1 = new Intent(Onlinepaymentscreen.this, PaymentActivity.class);
        intent1.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
        startActivityForResult(intent1, REQUEST_CODE_PAYMENT);
       /* btnpayment=(Button)findViewById(R.id.btnpayment);
        btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE_PAYMENT)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null)
                {
                    try
                    {
                        Log.i(TAG, confirm.toJSONObject().toString(4));
                        Log.i(TAG, confirm.getPayment().toJSONObject().toString(4));

                        Toast.makeText(getApplicationContext(),"PaymentConfirmation info received from PayPal", Toast.LENGTH_LONG)
                                .show();

                    } catch (JSONException e)
                    {
                        Log.e(TAG, "an extremely unlikely failure occurred: ", e);
                    }
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED)
            {
                Log.i(TAG, "The user canceled.");
            }
            else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            {
                Log.i(TAG,"An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }

    }


}
