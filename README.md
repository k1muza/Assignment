# Assignment
This is a simple project that fetches teams from url, parses the json format into POJOs and displays the team list in a recyclerview

<h3>Volley</h3>
The project uses Volley library to fetch content from the url: https://s3-eu-west-1.amazonaws.com/forza-assignment/android/teams.json

<h3>GSON</h3>
After the json content has been fetched, the app uses gson library to convert the json format to an array of Team objects

The following is Team's class is used to parse the JSON fetched from the above url into POJOs. 

<code>
<p>public class Team{</p>
<p>   String name;</p>
<p>   Boolean national;</p>

<p>   @SerializedName("country_name")</p>
<p>   String countryName;</p>
<p>}</p>
</code>

