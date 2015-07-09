CC = javac
APPNAME = HengokuTwi
JARFILES=../sqlite-jdbc-3.8.10.1.jar
OPT = -cp ".;$(JARFILES)"
EXT = .java
SOURCE = $(APPNAME)$(EXT)
all:
	$(CC) $(OPT) $(SOURCE)
