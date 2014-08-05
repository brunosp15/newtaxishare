// Generated code from Butter Knife. Do not modify!
package br.com.tinan.taxishare;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class RegisterActivity$$ViewInjector {
  public static void inject(Finder finder, final br.com.tinan.taxishare.RegisterActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034180, "field 'mEmail'");
    target.mEmail = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131034179, "field 'mUsername'");
    target.mUsername = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131034181, "field 'mPassword'");
    target.mPassword = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131034182, "method 'onSave'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onSave();
        }
      });
  }

  public static void reset(br.com.tinan.taxishare.RegisterActivity target) {
    target.mEmail = null;
    target.mUsername = null;
    target.mPassword = null;
  }
}
