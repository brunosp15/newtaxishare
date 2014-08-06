package br.com.tinan.taxishare;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class Utils {
	public final static void changeFragment(FragmentManager manager, Fragment fragment, Bundle args) {
		if (args == null)
			args = new Bundle();

		FragmentManager fragmentManager = manager;
		FragmentTransaction ftransaction = fragmentManager.beginTransaction();
		fragment.setArguments(args);
		ftransaction.replace(R.id.frame_container, fragment);
		ftransaction.addToBackStack(null);
		ftransaction.commit();
		
		
		
		
		
	}
}
