package br.com.tinan.taxishare;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {

	private Context mContext;
	private String[] navDrawerItems;

	public DrawerAdapter(Context context, String[] navDrawerItems) {
		this.mContext = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.length;
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.drawer_item, null);
		}

		// ImageView imgIcon = (ImageView)
		// convertView.findViewById(R.id.img_item_drawer);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_title_drawer);

		txtTitle.setText(navDrawerItems[position]);

		// displaying count
		// check whether it set visible or not
		// if (navDrawerItems.get(position).getCounterVisibility()) {
		// txtCount.setText(navDrawerItems.get(position).getCount());
		// } else {
		// // hide the counter view
		// txtCount.setVisibility(View.GONE);
		// }

		return convertView;
	}

}
