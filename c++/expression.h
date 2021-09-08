#include <iostream>
#include "helper.h"
#include "disallowcopy.h"
#include "simpio.h"
using namespace std;

/*
   This file is the interface to the Expression class.
   It is used to evaluate a single expression, given the values
   of x and y. The expression may only include the following
   possiblities:

   sin (pi * expr)
   cos (pi * expr)
   avg (expr, expr)
   expr * expr
   x
   y

 To use it, first create an Expression by passing giving it the expression as a string:
 
 string s = "avg(x, y)"
 Expression myExpr(s);

 Then, to evaluate it for given x and y values, use the Evaluate() method:

 double value = myExpr.Evaluate(somevalue1, somevalue2);

*/

class Expression
{
public:
	// Create an Expression object from the given string
	// (This will print errors if the string is invalid)
	Expression(string expressionString);

	// Check whether the string given was valid
	bool CheckSyntax() const;

	// Evalute this expressino given values of x and y
	double Evaluate(double x, double y);


/************************************************************
  Below here are internal implementation details.
  You don't really need to know about them.
*************************************************************/


	~Expression();
		DISALLOW_COPYING(Expression)
private:
	Expr * myExpr;
	bool syntaxOk;
};

