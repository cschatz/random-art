#include "image.h"

Image::Image(int width, int height)
{
	theImg = new CImg<unsigned char>(width, height, 1, 3);
	theDisp = new CImgDisplay;
}

void Image::SetPixel(int x, int y, double redFrac, double greenFrac, double blueFrac)
{
	(*theImg)(x, y, 0, 0) = redFrac*255;
	(*theImg)(x, y, 0, 1) = greenFrac*255;
	(*theImg)(x, y, 0, 2) = blueFrac*255;
}
	
void Image::Show()
{
	theDisp->display(*theImg);
	theDisp->show();
}

void Image::Save(std::string filename)
{
	theImg->save_bmp(filename.c_str());
}

void Image::Close()
{
	theDisp->close();
}

Image::~Image()
{
	if (theImg) delete theImg;
	if (theDisp) delete theDisp;
}