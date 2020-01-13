package com.example.adabooazeem.ifmatv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Initial_Page extends FragmentActivity {

    Button signup;
    TextView randomtext,signupText;
    Boolean signclick = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication);

        addFragment(new Login_Fragment(),false,"one");
        signup = findViewById(R.id.signup);
        randomtext = findViewById(R.id.randomtext);
        signupText = findViewById(R.id.signuptext);


        //signup page is called
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (signclick) {
                    addFragment(new Login_Fragment(),false,"one");
                    signup.setText("Sign Up");
                    signupText.setText("Sign In");
                    randomtext.setText("Every good movie deserves to be seen");
                    signclick = false;

                }else{
                    addFragment(new RegisterFragment(),false,"two");
                    signup.setText("Sign In");
                    signupText.setText("Sign Up");
                    randomtext.setText("Enjoy Movies, TV Show \n  \u0020 and more.");
                    signclick = true;
                }


            }
        });




    }







    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.frameContainer, fragment, tag);
        ft.commitAllowingStateLoss();


    }

}
