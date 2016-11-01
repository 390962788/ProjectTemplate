package com.guoyizeng.projecttemplate.ui.fragment;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guoyizeng.alipay.AlipayUtil;
import com.guoyizeng.alipay.PermissionUtil;
import com.guoyizeng.projecttemplate.R;
import com.guoyizeng.projecttemplate.ui.activity.H5PayDemoActivity;

public class ExternalFragment extends Fragment {
	AlipayUtil AlipayUtil;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =inflater.inflate(R.layout.pay_external, container, false);
		AlipayUtil = new AlipayUtil(getContext());
		view.findViewById(R.id.pay).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				PermissionUtil.requestPermission(getActivity(), Manifest.permission.READ_PHONE_STATE);
				AlipayUtil.pay();
			}
		});

		view.findViewById(R.id.h5pay).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
					AlipayUtil.h5Pay(H5PayDemoActivity.class);
			}
		});

		return view;
	}
}
