/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.adabooazeem.ifmatv;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import androidx.leanback.app.BackgroundManager;
import androidx.leanback.app.BrowseFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainFragment extends BrowseFragment {
    private static final String TAG = "MainFragment";

    private static final int BACKGROUND_UPDATE_DELAY = 300;
    private static final int GRID_ITEM_WIDTH = 200;
    private static final int GRID_ITEM_HEIGHT = 200;
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 15;

    private final Handler mHandler = new Handler();
    private Drawable mDefaultBackground;
    private DisplayMetrics mMetrics;
    private Timer mBackgroundTimer;
    private String mBackgroundUri;
    private BackgroundManager mBackgroundManager;
    ArrayList<String> title;
    ArrayList<String> cardImageUrl;
    ArrayList<String> Id;
    //arraylist
    private static List<Movie> list;



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onActivityCreated(savedInstanceState);

        prepareBackgroundManager();

        check();

        //loadRows();

        setupUIElements();

        setupEventListeners();
    }

    public static void longInfo(String str) {
        if(str.length() > 4000) {
            Log.i(TAG, str.substring(0, 4000));
            longInfo(str.substring(4000));
        } else
            Log.i(TAG, str);
    }


    //signup to take json format and return
    public void check() {


        // Map<String, String> postParam= new HashMap<String, String>();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Utils.GET_SHOWS_URL, new JSONObject(),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //longInfo(one + two);


                        Gson gson = new Gson();
                        JSONObject jsnobject = null;
                        JSONArray jsonArray = null;;
                        JsonObject jobj = new Gson().fromJson(response.toString(), JsonObject.class);

                        String results = jobj.get("result").toString();

                        try {
                             jsnobject = new JSONObject(results);
                             jsonArray = jsnobject.getJSONArray("objects");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        String url = null;

                        title = new ArrayList<String>();
                        cardImageUrl = new ArrayList<String>();
                        Id = new ArrayList<String>();



                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject row = null;
                            JSONObject thum = null;
                            JSONObject Movies = new JSONObject();

                            try {

                                row = jsonArray.getJSONObject(i);
                                JSONObject attrs = row.getJSONObject("attrs");


                                String showId = row.getString("showId");
                                String titlename = attrs.getString("title");
                                String descrip = attrs.getString("description");
                                String ageLimit = attrs.getString("ageLimit");
                                String type = attrs.getString("type");

                                //title.add(titlename);
                                //Id.add(showId);
                                //description.add(descrip);

                                Id.add(showId);
                                title.add(titlename);


                                JSONArray thumbnails = attrs.getJSONArray("thumbnails");
                                for (int a = 0; a < thumbnails.length(); a++) {
                                    thum = thumbnails.getJSONObject(a);
                                    url =  thum.getString("url");

                                }

                               String link = "https://api.ifmacinema.com:443";

                                cardImageUrl.add(link+url);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                       // Log.i("VOLLEY", cardImageUrl.toString());
                        //final place where json array is setup
                      // Log.i("VOLLEY", Movies.toString)

                        List<Movie> list = setupMoviess(Id,title,cardImageUrl);

                        loadRows(list);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Log.e("Volly Error", error.toString());

                    //  Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

                    // run a background job and once complete
                    //   pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof AuthFailureError) {
                    //TODO
                    // Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());

                    // run a background job and once complete
                    //    pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof ServerError) {
                    //TODO
                    //  Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());
                    // run a background job and once complete
                    //   pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof NetworkError) {
                    //TODO
                    //   Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());
                    // run a background job and once complete
                    //   pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof ParseError) {
                    //TODO
                    //  Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());

                    // run a background job and once complete
                    //  pb.setVisibility(ProgressBar.INVISIBLE);
                }
            }

        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        jsonObjReq.setTag(TAG);
        // Adding request to request queue
        queue.add(jsonObjReq);
        // Cancelling request

    }



    //Movielist
     List<Movie> setupMoviess(ArrayList<String> id, ArrayList<String> tile, ArrayList<String> cdImageUrl) {

         list = new ArrayList<>();

        for (int index = 0; index < tile.size(); ++index) {


            list.add(
                    buildMovieInfos(
                            id.get(index),
                            tile.get(index),
                            cdImageUrl.get(index)));
        }

        return list;
    }


    private static Movie buildMovieInfos(
            String Id,
            String title,
            String cardImageUrl)
    {
        Movie movie = new Movie();
        movie.setId(Id);
        movie.setTitle(title);
        movie.setCardImageUrl(cardImageUrl);
        return movie;
    }






    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mBackgroundTimer) {
            Log.d(TAG, "onDestroy: " + mBackgroundTimer.toString());
            mBackgroundTimer.cancel();
        }
    }

    private void loadRows(List<Movie> list) {

        ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        CardPresenter cardPresenter = new CardPresenter();

        int i;
        for (i = 0; i < NUM_ROWS; i++) {
            if (i != 0) {
                Collections.shuffle(list);
            }
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(cardPresenter);

            for (int j = 0; j < NUM_COLS; j++) {
                listRowAdapter.add(list.get(j % 5));
            }


            HeaderItem header = new HeaderItem(i, MovieList.MOVIE_CATEGORY[i]);
            rowsAdapter.add(new ListRow(header, listRowAdapter));
        }

        HeaderItem gridHeader = new HeaderItem(i, "PREFERENCES");

        GridItemPresenter mGridPresenter = new GridItemPresenter();
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
        gridRowAdapter.add(getResources().getString(R.string.grid_view));
        gridRowAdapter.add(getString(R.string.error_fragment));
        gridRowAdapter.add(getResources().getString(R.string.personal_settings));
        rowsAdapter.add(new ListRow(gridHeader, gridRowAdapter));

        setAdapter(rowsAdapter);
    }

    private void prepareBackgroundManager() {

        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());

        mDefaultBackground = ContextCompat.getDrawable(getActivity(), R.drawable.default_background);
        mMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    private void setupUIElements() {
        // setBadgeDrawable(getActivity().getResources().getDrawable(
        // R.drawable.videos_by_google_banner));
        setTitle(getString(R.string.browse_title)); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        // set fastLane (or headers) background color
        setBrandColor(ContextCompat.getColor(getActivity(), R.color.fastlane_background));
        // set search icon color
        setSearchAffordanceColor(ContextCompat.getColor(getActivity(), R.color.search_opaque));
    }

    private void setupEventListeners() {
        setOnSearchClickedListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Implement your own in-app search", Toast.LENGTH_LONG)
                        .show();
            }
        });

        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    private void updateBackground(String uri) {
        int width = mMetrics.widthPixels;
        int height = mMetrics.heightPixels;
        Glide.with(getActivity())
                .load(uri)
                .centerCrop()
                .error(mDefaultBackground)
                .into(new SimpleTarget<GlideDrawable>(width, height) {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable>
                                                        glideAnimation) {
                        mBackgroundManager.setDrawable(resource);
                    }
                });
        mBackgroundTimer.cancel();
    }

    private void startBackgroundTimer() {
        if (null != mBackgroundTimer) {
            mBackgroundTimer.cancel();
        }
        mBackgroundTimer = new Timer();
        mBackgroundTimer.schedule(new UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY);
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {

            if (item instanceof Movie) {
                Movie movie = (Movie) item;
                Log.d(TAG, "Item: " + item.toString());
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.MOVIE, movie);

                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        ((ImageCardView) itemViewHolder.view).getMainImageView(),
                        DetailsActivity.SHARED_ELEMENT_NAME)
                        .toBundle();
                getActivity().startActivity(intent, bundle);
            } else if (item instanceof String) {
                if (((String) item).contains(getString(R.string.error_fragment))) {
                    Intent intent = new Intent(getActivity(), BrowseErrorActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), ((String) item), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(
                Presenter.ViewHolder itemViewHolder,
                Object item,
                RowPresenter.ViewHolder rowViewHolder,
                Row row) {
            if (item instanceof Movie) {
                mBackgroundUri = ((Movie) item).getBackgroundImageUrl();
                startBackgroundTimer();
            }
        }
    }

    private class UpdateBackgroundTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    updateBackground(mBackgroundUri);
                }
            });
        }
    }

    private class GridItemPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            TextView view = new TextView(parent.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setBackgroundColor(
                    ContextCompat.getColor(getActivity(), R.color.default_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {
        }
    }

}
