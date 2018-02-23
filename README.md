# Assignment
This is a simple project that fetches teams from url, parses the json format into POJOs and displays the team list in a recyclerview

<h3>Volley</h3>
The project uses Volley library to fetch content from the url: https://s3-eu-west-1.amazonaws.com/forza-assignment/android/teams.json

<h3>GSON</h3>
After the JSON content has been fetched, the app uses GSON library to convert the json format to an array of Team objects

The following is Team's class is used to parse the JSON fetched from the above url into POJOs. 

```
public class Team 
  {
    String name;
    Boolean national;

    @SerializedName("country_name")
    String countryName;
}
```

<h3>Testing</h3>
The project has a single method that tests the parsing of the JSON to POJOs.

```
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
```
