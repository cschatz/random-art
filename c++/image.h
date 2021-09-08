#ifndef _H_image
#define _H_image

#include "CImg.h"
using namespace cimg_library;

#include <string>

class Image
{
public:
	// Create an image of the given width and height
	Image(int width, int height);

	// Set pixel (x, y) to the given red, green, blue values,
	// each between 0.0 and 1.0.
	void SetPixel(int x, int y, double redFrac, double greenFrac, double blueFrac);
	
	// Display the image, or update the window if already being displayed.
	void Show();

	// Close the window with the image in it.
	void Close();

	// Save the current image to the given file
	void Save(std::string filename);

 /******************************************************
  Internal representation - do not manipulate directly
  ******************************************************/
	
	
	






		~Image();
	private:
		CImg<unsigned char> * theImg;		
		CImgDisplay * theDisp;
};










#endif