#include "helper.h"
#include "expression.h"
#include "lex.yy.h"
#include <iostream>
using namespace std;

double xval;
double yval;

Expr * parsedExpr = 0;

Expression::Expression(string expressionString)
{
	myExpr = 0;
	Inityylex();
	yy_scan_string(expressionString.c_str());

	syntaxOk = true;
	int scanningOk = false;
	while (1)
	{
		int result = yylex();
		scanningOk = (result != -1);
		if (result < 1)
			break;
	}

	if (! scanningOk)
		syntaxOk = false;
	else
	{
		Inityylex();
		yy_scan_string(expressionString.c_str());
		if (yyparse() != 0)
			syntaxOk = false;
		else
			myExpr = parsedExpr;
	}

}

bool Expression::CheckSyntax() const
{
	return (syntaxOk);
}

double Expression::Evaluate(double x, double y)
{
	xval = x;
	yval = y;
	return (myExpr->Evaluate());
}

Expression::~Expression()
{
	if (myExpr)
		delete myExpr;
}