# Project specification  
The map generator should produce unique maps which are pseudorandom, but still resemble any games map.  

## Data structures and algorithms  
For the maps we only need two-dimensional arrays.  
Algorithms we use, are Perlin noise and OpenSimplex noise. If there is time, A\* may be used to test out these maps. We use OpenSimplex noise, as the Simplex noise is copyrighted.  

## Time and space complexity  
The array where the maps are stored, have both time and space complexity of O(n), O(1) when accessing.  
OpenSimplex noise time complexity is O(N^2) where the N is the dimension.
Value noise and Classic Perlin noise time complexity is O(X \* Y), where the X and Y are the width and height of the map. We loop trough the map with O(X \* Y) and for each (X,Y) element we calculate the weighted sum of its corner elements.   
Space complexity for all of them is O(1).  
 
# Input and output  
## Input 
X and Y cordinates, the size of the map.    
Amplification, how big the height difference is.  
Frequency, how frequent the height difference is.  

## Output  
A map  
Possibly a png picture, that shows the noise itself.  
Here's a few examples how the maps/pictures could look like:  
<img src="https://github.com/uberballo/MapGenerator/blob/master/documentation/pictures/gameMap.png" width="500">  
And as Perlin noise  
<img src="https://github.com/uberballo/MapGenerator/blob/master/documentation/pictures/perlinNoise.png" width="500">

# Sources  
Hugo elias, [Classic Perlin noise](http://web.archive.org/web/20160530124230/http://freespace.virgin.net/hugo.elias/models/m_perlin.htm)  
[OpenSimplex noise](https://gist.github.com/KdotJPG/b1270127455a94ac5d19) 
