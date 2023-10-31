all: FrontendDeveloperTests runFDTests


FrontendDeveloperTests: Frontend.java FrontendCarOrganizer.java BackendInterface.java BackendSingleCarCarOrganizer.java BackendSingleCarInterface.java FrontendDeveloperTests.java FrontendInterface.java
	javac Frontend.java
	javac FrontendCarOrganizer.java
	javac BackendInterface.java
	javac BackendSingleCarCarOrganizer.java
	javac BackendSingleCarInterface.java
	javac FrontendInterface.java
	javac -cp .:../junit5.jar FrontendDeveloperTests.java

runFDTests: FrontendDeveloperTests.class
	java -jar ../junit5.jar -cp . -c FrontendDeveloperTests

clean:
	rm -f *.class
