// Generated code from Butter Knife. Do not modify!
package br.com.tinan.taxishare;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ResetActivity$$ViewInjector {
  public static void inject(Finder finder, final br.com.tinan.taxishare.ResetActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230813, "field 'mEmail'");
    target.mEmail = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131230814, "method 'reset'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.reset();
        }
      });
  }

  public static void reset(br.com.tinan.taxishare.ResetActivity target) {
    target.mEmail = null;
  }
}
