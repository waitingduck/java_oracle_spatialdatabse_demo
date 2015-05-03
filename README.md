# java_oracle_spatialdatabse_demo
java application show spatial data from oracle spatial batabase

1.Whole Region:
This is to display all the features of the active feature types in the whole map. They should be displayed in the following way: Graphical representation of Students, Buildings and Transmission points if checked should show up when we click the submit button.

2.Point Query: 
When this radio button is checked, the user can select a point in the map. This point is displayed as a red square (5x5 pixels). You should also display a red circle centered at this point whose radius is 50 pixels. After pushing the Submit Query button, only the active features that are inside (or intersect with) of the circle will be displayed. Their shapes are specified in Table 1. Their colors are as follows: for each active feature type, the feature that is nearest to the selected point among all the features of this type inside the red circle is displayed in yellow. All the other features are displayed in green color. When the Point Query radio button is unchecked, the selected point and the associated red circle should disappear.



3.Range Query: 
When this radio button is checked, the user can draw a polygon in the map. After pushing the Submit Query button, only the features of the active feature types that are inside (or intersect with) the polygons are displayed. These features should be displayed in the same way as specified in Table 1. The user draws the polygon by clicking the left mouse button to select its vertices sequentially and then clicking the right mouse button to close the polygon. Red line segments on the screen should connect the vertices as they are being selected. When the Range Query radio button is unchecked, the selected
polygon should disappear.



4.Surrounding Students Query:
When this radio button is clicked the user can select a point in the map. The nearest Announcement System should be highlighted at this time. When the submit query button is pushed, the students in the region of the highlighted AS should be displayed.



5.Emergency Query: 
Imagine a AS breaks down. The students near this AS cannot hear the announcements and hence need to go to the next nearest AS. You need to help them. When this radio button is clicked, the user can select a point in the map. The nearest Announcement System should be highlighted at this time, indicating that this is the AS that is broken. When the submit query button is pushed, the students that were covered by the broken AS system should be color coordinated with the remaining announcement systems, i.e. a student should have the same color as its second nearest announcement system. You can use any color for the announcement system here, but all AS should have different colors.


How to use it?

Compelliation:
This program contains two part, first is population data, second is main application.

First you need to compile connectDB.java which is aim to connection.

Second you need to compile population class so you can insert data to your database.
populate.java
PopulateStudent.java
PopulateAS.java
PopulateBuilding.java
PopulateAll.java

Third, you can compile the main application
draw.java
query.java
mainUI.java
