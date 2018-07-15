package in.demo.tapmedemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import in.demo.tapmedemo.Adapter.ViewPagerAdapter;
import in.demo.tapmedemo.Fragment.HomeFragment;
import in.demo.tapmedemo.Model.GetAllSetGet;
import in.demo.tapmedemo.Model.OfferArtwork;
import in.demo.tapmedemo.Model.OfferList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewpagerhomepage,pager;
    TabLayout tabshomepage;
    ArrayList<GetAllSetGet> getAllSetGetArrayList=null;
    ArrayList<String> tabArrayList=null;
    ViewPagerAdapter viewpager_adapter;
    Timer timer;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabshomepage = (TabLayout)findViewById(R.id.homescreen_tablayout);
        viewpagerhomepage = (ViewPager)findViewById(R.id.homescreen_viewpager);
        pager = (ViewPager)findViewById(R.id.pager);

        tabArrayList=new ArrayList<>();
        getAllSetGetArrayList= getAll();

        for (int i=0;i<getAllSetGetArrayList.get(0).getOfferList().size();i++) {

            if (getAllSetGetArrayList.get(0).getOfferList().get(i).getIsPromoted()== true) {
                tabArrayList.add(getAllSetGetArrayList.get(0).getOfferList().get(i).getOfferArtwork().get2x());
            }else {

            }

        }

        viewpager_adapter = new ViewPagerAdapter(MainActivity.this, tabArrayList);
        pager.setAdapter(viewpager_adapter);
        viewpager_adapter.notifyDataSetChanged();
        pager.setCurrentItem(0);
        pager.setOnPageChangeListener(new CircularViewPagerHandler(pager));

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= 5) {
                            pager.setCurrentItem(count);
                            count++;
                        } else {
                            count = 0;
                            pager.setCurrentItem(count);
                        }
                    }
                });
            }
        }, 7000, 5000);

        setupViewPager(viewpagerhomepage);
        tabshomepage.setupWithViewPager(viewpagerhomepage);

        setupTabIcons();
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("GetAll.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public ArrayList<GetAllSetGet> getAll(){

        ArrayList<GetAllSetGet> getAllSetGetArrayList=new ArrayList<>();

        try{
            JSONObject jsonObject=new JSONObject(loadJSONFromAsset());

            GetAllSetGet getAllSetGet=new GetAllSetGet();
            ArrayList<OfferList> arrayListofferLists=new ArrayList<>();
            JSONArray jsonArray= jsonObject.getJSONArray("offer_list");

            for(int i =0 ;i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);

                OfferList offerList=new OfferList();
                offerList.setOfferCategory(jsonObject1.getString("offer_category"));
                offerList.setIsPromoted(jsonObject1.getBoolean("is_promoted"));
                offerList.setOfferUrl(jsonObject1.getString("offer_url"));
                JSONObject offer_artwork_jsonobject= jsonObject1.getJSONObject("offer_artwork");

                OfferArtwork offerArtwork=new OfferArtwork();
                offerArtwork.set2x(offer_artwork_jsonobject.getString("2x"));

                offerList.setOfferArtwork(offerArtwork);

                arrayListofferLists.add(offerList);
            }

            getAllSetGet.setOfferList(arrayListofferLists);
            getAllSetGetArrayList.add(getAllSetGet);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return getAllSetGetArrayList;
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapterHomefragment adapter = new ViewPagerAdapterHomefragment(getSupportFragmentManager());
        ArrayList<String> stringArrayList=new ArrayList<>();

        try{
            for (int i = 0; i < getAllSetGetArrayList.get(0).getOfferList().size() ; i++)
            {
                if(getAllSetGetArrayList.get(0).getOfferList().size()!=0)
                {

                    if(i==0){
                        stringArrayList.add(getAllSetGetArrayList.get(0).getOfferList().get(i).getOfferCategory());
                        HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setData(getAllSetGetArrayList.get(0).getOfferList(),getAllSetGetArrayList.get(0).getOfferList().get(i).getOfferCategory());
                        adapter.addFragment(homeFragment, "");
                    }else {
                        if(stringArrayList.contains(getAllSetGetArrayList.get(0).getOfferList().get(i).getOfferCategory())){

                        }else {
                            stringArrayList.add(getAllSetGetArrayList.get(0).getOfferList().get(i).getOfferCategory());
                            HomeFragment homeFragment = new HomeFragment();
                            homeFragment.setData(getAllSetGetArrayList.get(0).getOfferList(),getAllSetGetArrayList.get(0).getOfferList().get(i).getOfferCategory());
                            adapter.addFragment(homeFragment, "");
                        }
                    }

                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }


        viewPager.setAdapter(adapter);
    }


    private class ViewPagerAdapterHomefragment extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapterHomefragment(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

       /* @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }*/
    }

    private void setupTabIcons() {
        ArrayList<String> stringArrayList=new ArrayList<>();
        try {

            for (int i = 0; i < getAllSetGetArrayList.get(0).getOfferList().size() ; i++) {

                try {

                    if(i==0){
                        stringArrayList.add(getAllSetGetArrayList.get(0).getOfferList().get(i).getOfferCategory());
                        tabshomepage.getTabAt(i).setText(getAllSetGetArrayList.get(0).getOfferList().get(i).getOfferCategory());

                    }else {
                        if(stringArrayList.contains(getAllSetGetArrayList.get(0).getOfferList().get(i).getOfferCategory())){

                        }else {
                            stringArrayList.add(getAllSetGetArrayList.get(0).getOfferList().get(i).getOfferCategory());
                            tabshomepage.getTabAt(i).setText(getAllSetGetArrayList.get(0).getOfferList().get(i).getOfferCategory());

                        }
                    }
                    //tabshomepage.getTabAt(i).setIcon(drawableFromUrl(all_list.get(i).getCategoryicon()));


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public class CircularViewPagerHandler implements ViewPager.OnPageChangeListener {
        private ViewPager   mViewPager;
        private int         mCurrentPosition;
        private int         mScrollState;

        public CircularViewPagerHandler(final ViewPager viewPager) {
            mViewPager = viewPager;
        }

        @Override
        public void onPageSelected(final int position) {
            mCurrentPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(final int state) {
            handleScrollState(state);
            mScrollState = state;
        }

        private void handleScrollState(final int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                setNextItemIfNeeded();
            }
        }

        private void setNextItemIfNeeded() {
            if (!isScrollStateSettling()) {
                handleSetNextItem();
            }
        }

        private boolean isScrollStateSettling() {
            return mScrollState == ViewPager.SCROLL_STATE_SETTLING;
        }

        private void handleSetNextItem() {
            final int lastPosition = mViewPager.getAdapter().getCount() - 1;
            if(mCurrentPosition == 0) {
                mViewPager.setCurrentItem(lastPosition, false);
            } else if(mCurrentPosition == lastPosition) {
                mViewPager.setCurrentItem(0, false);
            }
        }

        @Override
        public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
        }
    }
}
