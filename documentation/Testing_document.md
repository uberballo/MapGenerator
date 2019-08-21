## Unit testing

<img src="https://github.com/uberballo/MapGenerator/blob/master/documentation/pictures/coverage%20report.PNG" width="1000">  
We have good coverage on our noise methods. We don't test any of the UI methods.

### Classic noise  
We test that all the helper methods work properly and the basic functionality. Most important tests ones are the methodGradReturnsCorrectValue and octavePerlinNoiseReturnsCorrectValue.  
#### methodGradReturnsCorrectValue  
As we use the gradient vector, we want to ensure that the main functionality works correctly.  

#### octavePerlinNoiseReturnsCorrectValue  
If the value goes over 1 or under 0, theres a bug and the program won't function properly.  

### Value noise  
We have many different types of interpolate, but they all function in the same way. The most important tests are same as in the Classic noise test.   
interpolateNoiseReturnsCorrectValues and octaveValueNoiseReturnsCorrectValues both test that the value is in correct range.  

### OpenSimplex noise  

As the OpenSimplex noise generates values between -1 and 1, the test differ a bit from our previous test. Otherwise same idea.   

## Manual testing  
Here the user themself, have to judge the results. As all these noise generate different type of picture and react to the variables in different way.  
Using the sliders user may see and judge the generated noise, if the noise is fitting for them.  
Here are some examples:   
   
Value noise with 2 octave, 1 frequency and 1 amplitude:  
<img src="https://github.com/uberballo/MapGenerator/blob/master/documentation/pictures/value%20noise%20too%20high%20octaves.PNG" width="500">  

Here's Classic Perlin noise with same values:  
<img src="https://github.com/uberballo/MapGenerator/blob/master/documentation/pictures/classic%20noise%20with%20same%20values.PNG" width="500">  

We can see that there's a huge difference in quality.  
