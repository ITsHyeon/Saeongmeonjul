package com.example.saeongmeonjul.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saeongmeonjul.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class TrailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_trail, container, false);

        MapView mapView = new MapView(getActivity());

        ViewGroup mapViewContainer = rootView.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        // 중심점 변경 - 미지센터
        // TODO : 현재 위치 받아오기
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.558887, 126.991040), true);

        // 줌 레벨 변경
        mapView.setZoomLevel(4, true);

        // 마커찍기
        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(37.558887, 126.991040);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("미지센터");
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본적으로 제공하는 BluePin 마커 모양
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을 때, 기본으로 제공하는 RedPin 마커 모양

        return rootView;
    }
}
