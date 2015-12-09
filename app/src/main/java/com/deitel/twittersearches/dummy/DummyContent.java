package com.deitel.twittersearches.dummy;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    // name of SharedPreferences XML file that stores the saved searches
    private static final String SEARCHES = "searches";

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

//    static {
//        // Add 3 sample items.
//        addItem(new DummyItem("1", "Item 1"));
//        addItem(new DummyItem("2", "Item 2"));
//        addItem(new DummyItem("3", "Item 3"));
//    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    // **** Added from https://github.com/gobelins-crm/crm14-hello/blob/master/app/src/main/java/crm/gobelins/helloandroid/fragment/dummy/DummyContent.java
    public static void addAndSaveItem(Context context, DummyItem item) {
        SharedPreferences prefs = context.getSharedPreferences(SEARCHES, Context.MODE_PRIVATE);
        prefs.edit()
                .putString(item.id, item.tag)
                .commit();
        addItem(item);
    }


    // *********** Added ***********
    public static void readFromPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(SEARCHES, Context.MODE_PRIVATE);

        HashMap<String, String> map = (HashMap<String, String>) prefs.getAll();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            addItem(new DummyItem(entry.getKey(), entry.getValue()));
        }
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String tag;
        //public String query_url;

        public DummyItem(String id, String tag) {
            this.id = id;
            this.tag = tag;
            //this.query_url= query_url;
        }

        @Override
        public String toString() {
            return tag;
        }
    }
}
