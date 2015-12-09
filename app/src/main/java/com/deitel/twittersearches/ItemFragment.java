package com.deitel.twittersearches;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.deitel.twittersearches.dummy.DummyContent;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */

public class ItemFragment extends ListFragment {

    // callback methods implemented by MainActivity
    public interface OnFragmentInteractionListener
    {
        // called when user selects a tag
        public void onFragmentInteraction(int position);
    }

    // ***** Listener object *******
    private OnFragmentInteractionListener mListener;

    // comment out for now because I am only working on portrait
//    /**
//     * The fragment's ListView/GridView.
//     */
    private ListView mListView;
    //
//    /**
//     * The Adapter which will be used to populate the ListView/GridView with
//     * Views.
//     */
    private ListAdapter setListAdapter;

    private SharedPreferences savedSearches; // user's favorite searches
    //public static SharedPreferences savedSearches;
    public ArrayList<String> tags = new ArrayList<String>(); // list of tags for saved searches
    //public static ArrayList<String> tags;
    private ArrayAdapter<String> adapter; // binds tags to ListView

    // name of SharedPreferences XML file that stores the saved searches
    private static final String SEARCHES = "searches";



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // casting activity to Listener object
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        // get the SharedPreferences containing the user's saved searches
//        SharedPreferences savedSearches = getActivity().getSharedPreferences(SEARCHES, Context.MODE_PRIVATE);
//
//        // store the saved tags in an ArrayList then sort them
        // do the loop thru the key set
       //tags = new ArrayList<String>(savedSearches.getAll().keySet());
        //tags = new ArrayList<String>(savedSearches.getAll().keySet());

        //tags = (ItemFragment)getActivity().getTags();
        //Collections.sort(tags, String.CASE_INSENSITIVE_ORDER);

        // TODO: Change Adapter to display your content

        // create ArrayAdapter and use it to bind tags to the ListView
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_item, android.R.id.text1, tags));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            int i = bundle.getString(tag, query);
//        }

        // ******** I probably don't need this, so comment out for now ******************
//        // Set the adapter
//        mListView = (AbsListView) view.findViewById(android.R.id.list);
//        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
//
//        // Set OnItemClickListener so we can be notified on item clicks
//        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);

        // get ListView reference and configure ListView
        mListView = getListView();
        mListView.setOnItemClickListener(viewItemListener);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // map each tag to a TextView in the ListView layout
        //setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_item, android.R.id.text1, tags));
    }

    // responds to the user touching a tag in the ListView
    AdapterView.OnItemClickListener viewItemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mListener.onFragmentInteraction(position);
        }
    }; // end viewItemListener



//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (null != mListener) {
//            // Notify the active callbacks interface (the activity, if the
//            // fragment is attached to one) that an item has been selected.
//            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
//        }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            // ******* When the user clicks on a list item this method is called.
            // *******  The fragment uses the callback interface to deliver
            //            the event to the parent activity. ****************
           //mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
            mListener.onFragmentInteraction(position);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
