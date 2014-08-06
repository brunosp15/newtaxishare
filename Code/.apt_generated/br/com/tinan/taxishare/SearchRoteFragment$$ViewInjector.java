// Generated code from Butter Knife. Do not modify!
package br.com.tinan.taxishare;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SearchRoteFragment$$ViewInjector {
  public static void inject(Finder finder, final br.com.tinan.taxishare.SearchRoteFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230818, "field 'mDestiny'");
    target.mDestiny = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131230817, "field 'mOrigin'");
    target.mOrigin = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131230816, "method 'search'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.search();
        }
      });
  }

  public static void reset(br.com.tinan.taxishare.SearchRoteFragment target) {
    target.mDestiny = null;
    target.mOrigin = null;
  }
}
