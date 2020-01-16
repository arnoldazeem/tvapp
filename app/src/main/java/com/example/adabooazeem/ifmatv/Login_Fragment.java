package com.example.adabooazeem.ifmatv;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Login_Fragment extends Fragment{
	private View view;

	public Login_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_login, container, false);


		return view;
	}


	// This event is triggered soon after onCreateView().
	// Any view setup should occur here.  E.g., view lookups and attaching view listeners.
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// Setup any handles to view objects here
		TextView reset = (TextView) view.findViewById(R.id.textreset);

		String resetText = "Forgot password? click here to reset";
		SpannableString ss = new SpannableString(resetText);

		ClickableSpan clickhere = new ClickableSpan() {

			@Override
			public void updateDrawState(@NonNull TextPaint ds) {
				super.updateDrawState(ds);
				ds.setColor(Color.WHITE);
			}

			@Override
			public void onClick(@NonNull View widget) {

				Toast.makeText(getActivity(), "reset",Toast.LENGTH_LONG).show();

			}
		};


       ss.setSpan(clickhere, 17, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
       reset.setText(ss);
       reset.setMovementMethod(LinkMovementMethod.getInstance() ) ;

	}


}
