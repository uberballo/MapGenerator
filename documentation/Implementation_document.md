# Implementation  

### Noise algorithm  
The main point of these noise algorithms is to generate pseudo-random values. The applications purpose is to generate noise with different algorithms and the user may compare them. When the ideal noise has been found, user may turn the noise into a map.
We implement Value noise, Classic Perlin noise and OpenSimplex noise algorithms.  

### Value noise  
Value noise differs from the two by not using any predetermined gradient vectors. It functions by also calculating around the given X,Y coordinates. When given coordinates, we produce value noise on 8 different coordinates, which we then interpolate to be similar to its neighboring values.  

### Classic Perlin noise  
As the name points out, this is the classic Perlin noise, which uses the predetermined gradient vectors to calculate noise. 
Functions same way as the Value noise.  

### OpenSimplex noise  
OpenSimplex is a open version of the improved Perlin noise. It functions similarly as the classic Perlin, 
but is quite more complex. The idea is that it uses simplectic honeycomb that is turned into a direction in gradient vector array. 
When we calculate the noise, we skew the input coordinate, which then points to some value in the gradient vector array.  



### Input  
User may change the frequency and the amplitude. Frequency determines how often the wave changes direction. Amplitude determines how steep the value change is.  

### UI  
GUI is a must here, as otherwise the user would always need to re-open the produced picture. User may change the values, choose which noise algorithm to use and see how long did it take to produce the map.  
