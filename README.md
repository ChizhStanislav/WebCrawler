Web Crawler Application

About

The Web Crawler Application is a that traverses websites following predefined link depth (8 by default) and max visited pages limit (10000 by default). Web crawler starts from predefined URL (seed) and follows links found to dive deeper. The main purpose of this crawler to detect the presence of some terms on the page and collect statistics.

Tools/libraries

The application uses the jsoup library. It's a Java library for working with HTML. It provides an API for extracting and manipulating data, using the best of DOM, CSS, and jquery-like methods. Unlike working with other web parsers, it provides convenient methods that eliminate the manual using regular expressions. The web search application also used the Opencv library. This is a CSV parser library for Java, for example for creating, reading, and modifying CSV files. Log4j is also a reliable, fast, and flexible logging environment API.

Installation guideline (for windows)

-to install the Web Crawler Application, you need to download it from a remote repository via the link using a third-party application (Git Bash...) or download a ZIP
-archive, in the second case you need to unpack the archive after downloading; -you should install apache-maven (link: https://maven.apache.org/);
-to launch the Web Crawler, go to the root directory of the project from the command line (.../WebCrawler-master) and type the command (mvn clean package exec:java -Dexec.mainClass="by.chyzh.Main" -Durl=https://www.tut.by/ -Dwords=hello,welcome ), where -Durl= predefined URL, -Dwords=comma-separated list of terms;
-to change the standard values add the following lines to the command: 
-DmaxDepth= the number of maximum link depth,
-DmaxQuantityPage = the number of maximum visited pages
 -you can also run the application in any development environment.

email

stas23041991@gmail.com
