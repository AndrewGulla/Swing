all : SimpleVisual.class TableVisual.class TreeVisual.class clean.class

SimpleVisual.class :
	javac $ SimpleVisual.java
	java $ SimpleVisual

TableVisual.class :
	javac $ TableVisual.java
	java $ TableVisual

TreeVisual.class :
	javac $ TreeVisual.java
	java $ TreeVisual

clean.class : 
	rm -f $ *.class

clean :
	rm -f $ *.class
