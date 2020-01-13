package com.example.adabooazeem.ifmatv;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
		// EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
	}


}
