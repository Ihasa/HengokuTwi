CC = javac
APPNAME = HengokuTwi
TWEETAPP = TweetManager
DBAPP = RecordGetter
JARFILES=lib/sqlite-jdbc-3.8.10.1.jar;lib/twitter4j-core-4.0.4.jar
OPT = -cp ".;$(JARFILES)"
EXT = .java
SOURCE = $(APPNAME)$(EXT)
SOURCE_TWEET=$(TWEETAPP)$(EXT)
SOURCE_DB = $(DBAPP)$(EXT)
all:
	$(CC) $(OPT) $(SOURCE)
tweet:
	$(CC) $(OPT) $(SOURCE_TWEET)
db:
	$(CC) $(OPT) $(SOURCE_DB)
clean:
	rm *.class
