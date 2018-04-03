
TFL Driver
=================================================================================================
This APP the TfL API to locate London underground trains and show it's train id.

It is very buggy at the moment. But here it is anyway. Feel
free to improve, it shouldn't be hard!

Issues
-------------------------------------------------------------------------------------------------
* District, Hammersmith and City, Metropolitan and Piccadilly lines are not 
  working on the station search mode
* Shows all stations aside from the Central Line Stations where the list only
  shows crew pick up points.
  
Possible TODOs
-------------------------------------------------------------------------------------------------
* To show crew pick up points for all lines, this can be done by removing the 
  irrelevant stations from the strings file:
  TFLDriver_advanced\app\src\main\res\values\strings.xml
* To make compatiable with IOS
* To sort the train times in order and to clear the querys when starting a
  new search:
  TFLDriver_advanced\app\src\main\java\com\example\devan\tfldriver_advanced\linesactivity.java
* To add the feature to link with a drivers duty book
* To have service disruption status.The JSON for this can be found at:
  https://api.tfl.gov.uk/swagger/ui/index.html?url=/swagger/docs/v1#!/Line/Line_StatusByIds

This was made by:

Devan Nathvani

-------------------------------------------------------------------------------------------------
