package net.capsulestudio.facebookaccountkitprectice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;

public class SucessActivity extends AppCompatActivity {

    Button btnLogOut;
    TextView tvUserID, tvPhone, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess);

        btnLogOut = findViewById(R.id.btnLogout);

        tvUserID = findViewById(R.id.tvID);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountKit.logOut();
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                String userID = account.getId();

                if (account.getEmail() != null){
                    tvEmail.setText(account.getEmail());
                }

                if (account.getPhoneNumber() != null){
                    tvPhone.setText(account.getPhoneNumber().toString());
                }

                tvUserID.setText(userID);

            }

            @Override
            public void onError(final AccountKitError error) {
                Log.d("Error", error.getErrorType().getMessage());
            }
        });
    }
}
