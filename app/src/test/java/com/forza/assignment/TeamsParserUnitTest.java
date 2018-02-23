package com.forza.assignment;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.*;

public class TeamsParserUnitTest {
    @Test
    public void testParseHardcodedTeams() throws Exception {
        //given
        String json = "[\n" +
                "    {\n" +
                "        \"name\": \"Arsenal FC\",\n" +
                "        \"national\": false,\n" +
                "        \"country_name\": \"England\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"FC Barcelona\",\n" +
                "        \"national\": false,\n" +
                "        \"country_name\": \"Spain\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"Sweden\",\n" +
                "        \"national\": true,\n" +
                "        \"country_name\": \"Sweden\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"Inter Milan\",\n" +
                "        \"national\": false,\n" +
                "        \"country_name\": \"Italy\"\n" +
                "    }\n" +
                "]";

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();

        //when
        Team[] teams;
        try (Reader reader = new BufferedReader(new StringReader(json))) {
            teams = gson.fromJson(reader, Team[].class);
        }

        //then
        assertEquals(teams.length, 4);

        Team team1 = teams[0];
        assertEquals(team1.name, "Arsenal FC");
        assertFalse(team1.national);
        assertEquals(team1.countryName, "England");

        Team team2 = teams[1];
        assertEquals(team2.name, "FC Barcelona");
        assertFalse(team2.national);
        assertEquals(team2.countryName, "Spain");

        Team team3 = teams[2];
        assertEquals(team3.name, "Sweden");
        assertTrue(team3.national);
        assertEquals(team3.countryName, "Sweden");

        Team team4 = teams[3];
        assertEquals(team4.name, "Inter Milan");
        assertFalse(team4.national);
        assertEquals(team4.countryName, "Italy");
    }
}