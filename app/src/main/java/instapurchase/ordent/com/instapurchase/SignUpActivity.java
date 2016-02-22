package instapurchase.ordent.com.instapurchase;

import android.location.Address;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by bayu on 22/02/16.
 */
public class SignUpActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private EditText nameText;
    private EditText addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailText = (EditText) findViewById(R.id.email_text);
        passwordText = (EditText) findViewById(R.id.password_text);
        nameText = (EditText) findViewById(R.id.name_text);
        addressText = (EditText) findViewById(R.id.address_text);
    }

    public void onSignUp(View view) {
        new Thread() {
            public void run() {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                String name = nameText.getText().toString();
                String address = addressText.getText().toString();
                HttpClient myClient = new DefaultHttpClient();
                HttpPost post = new HttpPost("insert signup post url");
                try {
                    List<NameValuePair> myArgs = new ArrayList<NameValuePair>();
                    myArgs.add(new BasicNameValuePair("email", email));
                    myArgs.add(new BasicNameValuePair("password", password));
                    myArgs.add(new BasicNameValuePair("name", name));
                    myArgs.add(new BasicNameValuePair("address", address));
                    post.setEntity(new UrlEncodedFormEntity(myArgs));
                    HttpResponse myResponse = myClient.execute(post);
                    BufferedReader br = new BufferedReader( new InputStreamReader(myResponse.getEntity().getContent()));
                    String line = "";
                    while ((line = br.readLine()) != null)
                    {
                        Log.d("mytag", line);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
