package com.kay.douban;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class PagerFragment extends android.support.v4.app.Fragment {
    //页数
    private int pageNum;
    //页的GridView组件
    private GridView gv;
    //动物
     private int[] animal = new int[] {
          R.drawable.tiger,R.drawable.lion,
            R.drawable.bear,R.drawable.jingyu,
            R.drawable.laoying,R.drawable.liegou
    };

    //植物
    private int[] plant = new int[] {
            R.drawable.shu,R.drawable.cao,
            R.drawable.jue,R.drawable.hua,
            R.drawable.apple,R.drawable.mu
    };

    //飞机
    private int[] plane = new int[] {
      R.drawable.feiji_a, R.drawable.feiji_b,
            R.drawable.feiji_c,R.drawable.feiji_d,
            R.drawable.feiji_e,R.drawable.feiji_f,
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //得到布局View
        View pager = inflater.inflate(R.layout.pager_fragment, container, false);

        gv = (GridView) pager.findViewById(R.id.gv);
        Bundle bundle = getArguments();
        pageNum = bundle.getInt("page_num");
        if(pageNum == 1)
        {
            List<Map<String, Object>> listItems =
                    new ArrayList<>();
            for(int i = 0; i < animal.length; i++) {
                Map<String, Object> listItem = new HashMap<>();
                listItem.put("image",animal[i]);
                listItems.add(listItem);
            }

            SimpleAdapter sagv = new SimpleAdapter(getActivity(),
                    listItems,R.layout.pager_fragment,
                    new String[] {"image"},
                    new int[]{R.id.imageView});
            gv.setAdapter(sagv);
        }

        if(pageNum == 2)
        {
            List<Map<String, Object>> listItems =
                    new ArrayList<>();
            for(int i = 0; i < plant.length; i++) {
                Map<String, Object> listItem = new HashMap<>();
                listItem.put("image",plant[i]);
                listItems.add(listItem);
            }

            SimpleAdapter sagv = new SimpleAdapter(getActivity(),
                    listItems,R.layout.pager_fragment,
                    new String[] {"image"},
                    new int[]{R.id.imageView});
            gv.setAdapter(sagv);
        }

        if(pageNum == 3)
        {
            List<Map<String, Object>> listItems =
                    new ArrayList<>();
            for(int i = 0; i < plane.length; i++) {
                Map<String, Object> listItem = new HashMap<>();
                listItem.put("image",plane[i]);
                listItems.add(listItem);
            }

            SimpleAdapter sagv = new SimpleAdapter(getActivity(),
                    listItems,R.layout.pager_fragment,
                    new String[] {"image"},
                    new int[]{R.id.imageView});
            gv.setAdapter(sagv);
        }

        return pager;
    }


}
