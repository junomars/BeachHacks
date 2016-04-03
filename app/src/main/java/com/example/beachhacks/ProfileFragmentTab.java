package com.example.beachhacks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragmentTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragmentTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragmentTab extends Fragment {
    private OnFragmentInteractionListener interactionListener;

    private TextView name;
    private TextView fname;
    private TextView lname;
    private TextView arange;
    private TextView gender;
    private TextView locale;
    private ImageView picture;

    public ProfileFragmentTab() {
        // Required empty constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static DashboardFragment newInstance(Bundle userInfo) {
        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(userInfo);

        // Send it away
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Init our views
        name = (TextView) view.findViewById(R.id.profile_fb_name);
        fname = (TextView) view.findViewById(R.id.profile_fb_fname);
        lname = (TextView) view.findViewById(R.id.profile_fb_lname);
        arange = (TextView) view.findViewById(R.id.profile_fb_agerange);
        gender = (TextView) view.findViewById(R.id.profile_gender);
        locale = (TextView) view.findViewById(R.id.profile_fb_locale);
        picture = (ImageView) view.findViewById(R.id.profile_fb_picture);

        // Use the bundled info to set our info
        if (getArguments() != null) {
            Bundle args = getArguments();
            name.setText(args.getString("name"));
            fname.setText(args.getString("first_name"));
            lname.setText(args.getString("last_name"));
            arange.setText(args.getString("age_range"));
            gender.setText(args.getString("gender"));
            String loc = args.getString("locale");
            if (loc != null)
                switch (loc) {
                    case "en_US":
                        locale.setText("English (US)");
                        break;
                }

            // TODO: progress of image loading?
            // Attempt to download image
            new ProfileFragmentTab.DownloadImageTask(picture).execute(args.getString("picture"));
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (interactionListener != null) {
            interactionListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProfileFragmentTab.OnFragmentInteractionListener) {
            interactionListener = (ProfileFragmentTab.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
