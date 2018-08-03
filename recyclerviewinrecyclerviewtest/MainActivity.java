package recyclerviewtest.guiving.com.recyclerviewinrecyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    ArrayList<SectionDataModel> allSampleData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("RecyclerView Example");
        setSupportActionBar(toolbar);

        allSampleData = new ArrayList<SectionDataModel>();


        createDummyData();

        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);


    }

    public void createDummyData() {
        for (int i = 1; i <= 5; i++) {

            SectionDataModel dm = new SectionDataModel();

            dm.setHeaderTitle("Section " + i);

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            singleItem.add(new SingleItemModel("네이버", "http://m.naver.com/", "네이버" ));
            singleItem.add(new SingleItemModel("다음", "http://m.daum.net/", "다음" ));
            singleItem.add(new SingleItemModel("구글", "http://m.google.com/", "구글" ));

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
    }
}
