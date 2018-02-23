# Assignment
This is a simple project that fetches teams from url, parses the json format into POJOs and displays the team list in a recyclerview

Volley
The project uses Volley library to fetch content from the url: https://s3-eu-west-1.amazonaws.com/forza-assignment/android/teams.json

GSON
After the json content has been fetched, the app uses gson library to convert the json format to an array of Team objects

