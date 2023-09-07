package com.example.umo.data

enum class CapitalCoordinates(val lat: Double, val lon: Double, val capital: String) {
    Alabama(32.37, -86.30, "Montgomery"),
    Alaska(58.30, -134.42, "Juneau"),
    Arizona(33.45, -112.07, "Phoenix"),
    Arkansas(34.75, -92.29, "Little Rock"),
    California(38.58, -121.49, "Sacramento"),
    Colorado(39.74, -104.98, "Denver"),
    Connecticut(41.76, -72.69, "Hartford"),
    Delaware(39.16, -75.52, "Dover"),
    Florida(30.44, -84.28, "Tallahassee"),
    Georgia(33.75, -84.39, "Atlanta"),
    Hawaii(21.31, -157.86, "Honolulu"),
    Idaho(43.61, -116.20, "Boise"),
    Illinois(39.80, -89.64, "Springfield"),
    Indiana(39.77, -86.16, "Indianapolis"),
    Iowa(41.60, -93.61, "Des Moines"),
    Kansas(39.05, -95.68, "Topeka"),
    Kentucky(38.20, -84.87, "Frankfort"),
    Louisiana(30.44, -91.19, "Baton Rouge"),
    Maine(44.31, -69.78, "Augusta"),
    Maryland(38.98, -76.49, "Annapolis"),
    Massachusetts(42.36, -71.06, "Boston"),
    Michigan(42.73, -84.56, "Lansing"),
    Minnesota(44.94, -93.09, "Saint Paul"),
    Mississippi(32.30, -90.18, "Jackson"),
    Missouri(38.58, -92.17, "Jefferson City"),
    Montana(46.59, -112.04, "Helena"),
    Nebraska(40.80, -96.67, "Lincoln"),
    Nevada(39.16, -119.77, "Carson City"),
    New_Hampshire(43.21, -71.54, "Concord"),
    New_Jersey(40.22, -74.74, "Trenton"),
    New_Mexico(35.69, -105.94, "Santa Fe"),
    New_York(42.65, -73.76, "Albany"),
    North_Carolina(35.77, -78.64, "Raleigh"),
    North_Dakota(46.81, -100.78, "Bismarck"),
    Ohio(39.96, -83.00, "Columbus"),
    Oklahoma(35.47, -97.52, "Oklahoma City"),
    Oregon(44.94, -123.04, "Salem"),
    Pennsylvania(40.27, -76.88, "Harrisburg"),
    Rhode_Island(41.82, -71.41, "Providence"),
    South_Carloina(34.00, -81.03, "Columbia"),
    South_Dakota(44.37, -100.35, "Pierre"),
    Tennessee(36.17, -86.78, "Nashville"),
    Texas(30.27, -97.74, "Austin"),
    Utah(40.76, -111.89, "Salt Lake City"),
    Vermont(44.26, -72.58, "Montpelier"),
    Virginia(37.55, -77.46, "Richmond"),
    Washington(47.04, -122.90, "Olympia"),
    West_Virginia(38.35, -81.63, "Charleston"),
    Wisconsin(43.07, -89.40, "Madison"),
    Wyoming(41.14, -104.82, "Cheyenne"),
}

fun CapitalCoordinates.getState(): String {
    var result = name
    if (result.contains("_")) {
        val array = result.split("_")
        result = "${array[0]} ${array[1]}"
    }
    return result
}