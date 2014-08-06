// Generated code from Butter Knife. Do not modify!
package br.com.tinan.taxishare;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class CreateRoteFragment$$ViewInjector {
  public static void inject(Finder finder, final br.com.tinan.taxishare.CreateRoteFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230802, "field 'mDestiny'");
    target.mDestiny = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131230801, "field 'mOrigim'");
    target.mOrigim = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131230803, "field 'mTime'");
    target.mTime = (android.widget.TimePicker) view;
    view = finder.findRequiredView(source, 2131230804, "method 'createRote'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.createRote();
        }
      });
  }

  public static void reset(br.com.tinan.taxishare.CreateRoteFragment target) {
    target.mDestiny = null;
    target.mOrigim = null;
    target.mTime = null;
  }
}
