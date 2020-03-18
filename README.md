 # Coding exercise for Cisco 
 #### Devon Hockley - devon.hockley@ucalgary.ca

 Done using Kotlin, trying to be idiomatic with a functional/object-oriented hybrid approach with as much immutability as possible.

 Creating my own class that would just end up wrapping MutableMap with my own functions is silly in kotlin
 so I added the desired functionality using static extensions. (See https://kotlinlang.org/docs/reference/extensions.html)

 Somewhat over engineered but I found it easier to reason about tests and behaviour with each distinct step of the process being wrapped as its own function.

 I also found it easier to reason about some methods when they are implemented with generics.
 
 ## Running it
 The project includes a gradle wrapper, which expects a relativly recent versino of the JDK to be installed (works with 11 on my machine)
 
 To run it, use the commnad:
 
    gradlew run --args "\<filename\>" 
    
 To run it using standard input, or without gradle: 
 
    gradlew installDist
    
 Will install it to ./build/install. Navigate to:
 
    ./build/install/word-count/bin
 
 Then, run the word-count file for your system like one of the following:
 
    ./word-count <file-name> //from file
    ./word-count < <filename> //stdin from file
    
 ## Tests
 Run tests using:
 
    gradlew test
    
 ## Documentation
 Render documentation to html using:
 
    gradlew dokka
    
 HTML site can be found at:
 
    ./build/dokka
 
