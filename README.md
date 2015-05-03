# java_oracle_spatialdatabse_demo
java application show spatial data from oracle spatial batabase

1. Whole Region:
This is to display all the features of the active feature types in the whole map. They should be displayed in the following way: Graphical representation of Students, Buildings and Transmission points if checked should show up when we click the submit button.

2.Point Query: 
When this radio button is checked, the user can select a point in the map. This point is displayed as a red square (5x5 pixels). You should also display a red circle centered at this point whose radius is 50 pixels. After pushing the Submit Query button, only the active features that are inside (or intersect with) of the circle will be displayed. Their shapes are specified in Table 1. Their colors are as follows: for each active feature type, the feature that is nearest to the selected point among all the features of this type inside the red circle is displayed in yellow. All the other features are displayed in green color. When the Point Query radio button is unchecked, the selected point and the associated red circle should disappear.

