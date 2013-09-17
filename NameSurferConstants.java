/*
 * File: NameSurferConstants.java
 * ------------------------------
 * This file declares several constants that are shared by the
 * different modules in the NameSurfer application.  Any class
 * that implements this interface can use these constants.
 */

public interface NameSurferConstants {

/** The width of the application window */
	public static final int APPLICATION_WIDTH = 800;

/** The height of the application window */
	public static final int APPLICATION_HEIGHT = 600;

/** The first decade in the database */
	public static final int START_DECADE = 1900;

/** The number of decades */
	public static final int NDECADES = 11;
	
/** The name of data file */
	public static final String FILE_NAME = "names-data.txt";

/** The maximum rank in the database */
	public static final int MAX_RANK = 1000;

/** The number of pixels to reserve at the top and bottom */
	public static final int GRAPH_MARGIN_SIZE = 20;

}
