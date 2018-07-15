package in.demo.tapmedemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import in.demo.tapmedemo.Adapter.HomeScreenGridAdapter;
import in.demo.tapmedemo.Model.OfferList;
import in.demo.tapmedemo.R;


/**
 * Created by piyali on 07/04/17.
 */

public class HomeFragment extends Fragment {

    public static ListView listview_home_fragment;
    FrameLayout homescreen_framelayout;
    ArrayList<OfferList> subcatlist;
    public static ArrayList<OfferList> list;
    GridView homescreen_gridview;
    String name="";
    public HomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater infaltelayout = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        final View layout = infaltelayout.inflate(R.layout.layout_home_fragment, null);
        list=new ArrayList<OfferList>();
        homescreen_gridview = (GridView) layout.findViewById(R.id.gv_fragment);

        for (int i=0;i<subcatlist.size();i++){
            if(subcatlist.get(i).getOfferCategory().equalsIgnoreCase(name)){
                OfferList offerList=new OfferList();
                offerList.setOfferArtwork(subcatlist.get(i).getOfferArtwork());
                offerList.setOfferCategory(subcatlist.get(i).getOfferCategory());

                list.add(offerList);
            }
        }
        homescreen_gridview.setAdapter(new HomeScreenGridAdapter(getActivity(),R.layout.child_gridview,list));

        return layout;
    }

    public void setData(ArrayList<OfferList> subcatlist, String name)
    {
        this.subcatlist=subcatlist;
        this.name=name;
    }


    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        /*if(Filter_New.arrayList_filter!=null && Filter_New.arrayList_filter.size()>0){
            HomeScreenPage.homescreen_framelayout.setVisibility(View.VISIBLE);
            HomeScreenPage.ll_homescreen_offers.setVisibility(View.GONE);
            HomeScreenPage.homescreen_gridview.setVisibility(View.VISIBLE);

            HomeScreenPage.homescreen_gridview.setAdapter(new Offers_For_You_Adapter(getActivity(),R.layout.child_gridview_mainpage,Filter_New.arrayList_filter));
        }else  if (SearchActivity.subcatflag.equalsIgnoreCase("true"))
        {
            SearchActivity.subcatflag="";
            getActivity().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            );

            HomeScreenPage.homescreen_framelayout.setVisibility(View.VISIBLE);
            HomeScreenPage.ll_homescreen_offers.setVisibility(View.GONE);
            HomeScreenPage.homescreen_gridview.setVisibility(View.VISIBLE);

            GETPRODUCTSBYCATEGORYANDSUBCATEGORY(SearchActivity.subcatid);

        }*/


    }
}
