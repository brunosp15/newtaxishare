// Generated code from Butter Knife. Do not modify!
package br.com.tinan.taxishare;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class LoginActivity$$ViewInjector {
  public static void inject(Finder finder, final br.com.tinan.taxishare.LoginActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034177, "field 'mPassword'");
    target.mPassword = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131034176, "field 'mLogin'");
    target.mLogin = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131034178, "method 'goToSignUp'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.goToSignUp();
        }
      });
  }

  public static void reset(br.com.tinan.taxishare.LoginActivity target) {
    target.mPassword = null;
    target.mLogin = null;
  }
}
