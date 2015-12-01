package fr.ensisa.hassenforder.network;

public interface Protocol {

	public static final Object EXIT_TEXT = "exit";

	public static final int PROXIMITY_PORT_ID = 56789;

	public static final int SERVER_ANSWER_USER = 1;
	public static final int SERVER_ANSWER_KO = 2;
	public static final int SERVER_ANSWER_OK = 3;
	public static final int SERVER_ANSWER_USERS_LIST = 4;
	
	public static final int CLIENT_REQUEST_ESTABLISH = 1;
	public static final int CLIENT_REQUEST_SET = 2;
	public static final int CLIENT_REQUEST_FIND = 3;
		
	public static final int CONNECTION = 11;
	public static final int DISCONNECTION = 12;
	
	public static final int XY = 21;
	public static final int RADIUS = 22;
	public static final int MODE = 23;
	public static final int PREFERENCE_VISIBILITY = 24;
	public static final int PREFERENCE_LEVEL = 25;
}
