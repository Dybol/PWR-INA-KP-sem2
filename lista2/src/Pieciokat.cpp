#include "funs.hpp"
#include <math.h>

Pieciokat::Pieciokat(double a) {
    this->a = a;
}

double Pieciokat::obwod() {
    return 5 * a;
}

double Pieciokat::pole() {
    return sqrt(25+10*sqrt(5)/4) * a * a;
}
