#include "expression.h"
#include "random.h"
#include "simpio.h"
#include "image.h"
#include <iostream>
using namespace std;

const int WIDTH = 900;
const int HEIGHT = 900;


void PrintInstructions()
{
	cout << "Welcome to Random Art.\n";
	cout << "Commands:\n";
	cout << "'grey'     Generate a random greyscale image\n";
	cout << "'color'    Generate a random color image\n";
	cout << "'quit'     Quit the program\n";
	cout << "'save'     Save the current image to a file\n";
	cout << "(other)    Generate a greyscale image from the string entered.\n";
}

void RenderGiven(Image * img, string s)
{

}


void RenderRandom(Image * img, bool color=false)
{
	
}

int main()
{	
	Randomize();	
	Image * img = new Image(WIDTH, HEIGHT);
	string lastinput = "";
	string input;
	PrintInstructions();
	while (1)
	{
		cout << "> ";
		input = GetLine();
		// TO DO: Add code to handle user input for all menu choices,
		//         including entering empty string to repeat last command. 
		cout << endl;
	}
	return 0;
}



