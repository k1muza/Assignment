package com.forza.assignment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamsListFragment extends Fragment {
        private static final String ENDPOINT = "https://s3-eu-west-1.amazonaws.com/forza-assignment/android/teams.json";

    private StringRequest request;
    private RequestQueue requestQueue;
    private Gson gson;
    private CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(new ArrayList<Item>());

    public TeamsListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TeamsListFragment newInstance(String param1, String param2) {
        TeamsListFragment fragment = new TeamsListFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1); TODO
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_teams_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        adapter = new CustomRecyclerViewAdapter(new ArrayList<Item>());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        //requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext(), new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        fetchPosts();
    }

    private void fetchPosts() {
        request = new StringRequest(Request.Method.GET, ENDPOINT, onPostsLoaded, onPostsError);
        requestQueue.add(request);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        requestQueue.cancelAll(request);
    }

    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            List<Team> teams = Arrays.asList(gson.fromJson(response, Team[].class));

            Log.i("TeamsListFragment", teams.size() + " posts loaded.");
            for (Team team : teams) {
                Log.i("TeamsListFragment", team.name + ": " + team.national);
                adapter.add(new TeamView(getActivity(), team));
                adapter.notifyDataSetChanged();
            }
        }
    };

    private final Response.ErrorListener onPostsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("TeamsListFragment Error", error.toString());
        }
    };
}
