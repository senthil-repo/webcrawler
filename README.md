Disclaimer:
 Please install Java, Maven & other basic softwares that are needed to run any java based applications.
 I have used Java 8 and followed Test Driven Development to build the application.

Please follow the steps mentioned below to run the application
1) Download the source code from the URL below :
https://github.com/senthil-repo/webcrawler.git

2) Build the source code : Go to the folder /webcrawler/ (where you can find the pom.xml) and run the following command in a command prompt. This creates a jar file 'webcrawler-1.0.jar' under the folder '/webcrawler/target'
	
	mvn clean install

3) Run the application & start the service: Go to the folder '/webcrawler/target', and run the following command
	java -jar webcrawler-1.0.jar
  
  This should start-up the program and the user should be asked for an input with a following message :
         Please enter the search term: 
  
Other informations :
4) Please find below the approach i have followed to build the application :
 a) SearchEngine - Implemented the 'Bing' search engine to get all the webite links for the input 'search term'
 b) DownloadWebsite - Class to download the respective website page and get the java script libraries from it
 c) ExtractLibraries - Designed the java concurreny model (multi threading) using executor service. By this way, the websites are parallely crawled to retreive the java script libratries.
 d) WebCrawler - This is the main class that runs the whole program.
 
 e) I have used JSoup library to extract, manipulate the data from website/url. Also, used junit, mock libraries for unit testing & apache commons for get utility method.
 
Other clarificaitons :
5) I have implemeted the application with TDD. Due to the time constaint, i couldn't add more test cases, test classes to the respective program. 
I would love to expand that in future.

6) I have added 'TODO' in some places, to express my thoughts, constraints and considerations for future. 

7) As per the advise and the instruction given, i restrict the implementation to meet the time. 
For example - 
	a) I couldn't spend the time to work more on deduplication algorithms that filters same Javascript libraries with different names. 
	
	b) While extracing the JS library name, i am not 100% sure on what level of details we need here. 
	So i have restricted myself to keep it simple by just focusing on extracting 'javascript file name' as what we get from the website.
	
	c) I have spend a little time on handling the exceptions in the program. Would like to handle exceptions more gracefully in future.
	
8) I see there are scope to add more validations and corresponding test cases to the software, which i would desire to do that in near future.

Thanks for your time in assessing my software. In case i accidentally missed anything, please do not hesitate to notify me.


