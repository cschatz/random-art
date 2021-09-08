/************************************************
  THIS IS INTERNAL HELPER CODE
  DO NOT ATTEMPT TO UNDERSTAND IT... :)
 ************************************************/


#ifndef _H_helper
#define _H_helper

#include <stdlib.h>
#include <stdio.h>
#include <string>


const double PI = 3.14159;

enum eExprtype { eCos, eSin, eAvg, eMult, eVar };

class Expr
{
 private:
  eExprtype type;
  char whichVar;
  Expr * leftChild;
  Expr * rightChild;
 public:
  Expr (eExprtype kind, Expr * left = 0, Expr * right = 0, char what='.') :
  type(kind), leftChild(left), rightChild(right), whichVar(what) { }
  double Evaluate();
  ~Expr();
};

extern std::string scanned;
extern double value;
extern double xval;
extern double yval;
extern Expr * parsedExpr;

void yyerror(char * msg, ...);
void Failure(char * msg, ...);

// From lex.yy.c
void Inityylex(void);
int yylex(void);

extern char yytext[];
extern int yy_flex_debug;



// From y.tab.c
void Inityyparse(void);
int yyparse(void);

#endif
