#include "helper.h"
#include <stdarg.h>
#include <stdio.h>
#include <string.h>
#include <math.h>
using namespace std;

/*
 * from lex.yy.c
 */
extern char yytext[];

#define BufferSize   1024
#define MaxDebugKeys  256

void yyerror(char * msg, ...) {
  va_list args;
  char errbuf[BufferSize];
  int errlen;
  
  va_start(args, msg);
  vsprintf(errbuf, msg, args);
  va_end(args);
  errlen = strlen(errbuf);
  if (errlen > BufferSize) {
    Failure("yyerror Message too long (%d)\n", errlen);
  } else {
    printf("\n*****************************************\n");
    printf("*** %s ***\nScanning stopped after this:\n", errbuf, yytext);
    printf("%s\n", scanned.c_str());
    printf("*****************************************\n");
  }
}


void Failure(char * msg, ...)
{
  va_list args;
  char errbuf[BufferSize];
  int errlen;
  
  va_start(args, msg);
  vsprintf(errbuf, msg, args);
  va_end(args);
  errlen = strlen(errbuf);
  if (errlen > BufferSize) {
    printf("\n*** Failure: Failure Message too long\n\n");
  } else {
    printf("\n*** Failure: %s\n\n", errbuf);
  }
  exit(1);
}

double value;


double Expr::Evaluate()
{
  if (type == eVar)
    {
      return (whichVar == 'x' ? xval : yval);
    }

  switch (type)
    {
    case eCos:
      return (cos(PI * leftChild->Evaluate()));
    case eSin:
      return (sin(PI * leftChild->Evaluate()));
    case eAvg:
      return ((leftChild->Evaluate() + rightChild->Evaluate()) / 2.0);
    case eMult:
      return (leftChild->Evaluate() * rightChild->Evaluate());
    default:
      Failure ("Fatal error in library - bogus expression type\n");
	  return 1729;
    }
}

Expr::~Expr()
{
	if (leftChild)
		delete leftChild;
	if (rightChild)
		delete rightChild;
}
