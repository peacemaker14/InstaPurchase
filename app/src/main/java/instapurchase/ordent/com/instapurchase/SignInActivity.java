package instapurchase.ordent.com.instapurchase;

/**
 * Created by bayu on 22/02/16.
 */
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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {
    private EditText passwordText;
    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        emailText = (EditText) findViewById(R.id.email_text);
        passwordText = (EditText) findViewById(R.id.password_text);
    }

    public void onSignIn(View view) {
        new Thread() {
            public void run() {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                HttpClient myClient = new DefaultHttpClient();
                HttpPost post = new HttpPost("insert login post url");
                try {
                    List<NameValuePair> myArgs = new ArrayList<NameValuePair>();
                    myArgs.add(new BasicNameValuePair("email", email));
                    myArgs.add(new BasicNameValuePair("password", password));
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
