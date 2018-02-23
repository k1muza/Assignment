package com.forza.assignment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by axa on 23/2/2018.
 */

public class Team{

    String name;
    Boolean national;

    @SerializedName("country_name")
    String countryName;
}
