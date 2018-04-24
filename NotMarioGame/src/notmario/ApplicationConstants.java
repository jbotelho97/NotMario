package notmario;

/** I use this interface to store a set of application-wide constants
 * 
 * @author jyh
 *
 */
public interface ApplicationConstants {

	//=====================================================
	//  WINDOW DIMENSIONS
	//=====================================================
	//  Dimensions of the window
	int WIN_WIDTH = 800, WIN_HEIGHT = 600;

	//=====================================================
	//  WORLD DIMENSIONS and SCALING
	//=====================================================
	//  origin of the world at middle bottom of the window
	float WORLD_ORIGIN_X = WIN_WIDTH/2, WORLD_ORIGIN_Y = WIN_HEIGHT/2;

	//  dimensions of my world
	float WORLD_WIDTH = 100;
	float WORLD_HEIGHT = 80;

	float XMAX = WORLD_WIDTH/2, XMIN = -WORLD_WIDTH/2;
	float YMAX = WORLD_HEIGHT/2, YMIN = -WORLD_HEIGHT/2;
	float VMAX = (XMAX - XMIN)/400;
	
	//====================================================
	//  WORLD GRAVITY
	//====================================================
	float gravity = -0.0005f;
	
	
	
	//  conversion factors
	float PIXEL_TO_WORLD = WORLD_WIDTH / WIN_WIDTH;
	float WORLD_TO_PIXEL = 1.0f / PIXEL_TO_WORLD;

	//=====================================================
}
