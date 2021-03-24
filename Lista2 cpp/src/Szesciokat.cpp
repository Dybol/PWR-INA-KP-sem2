#include "funs.hpp"
#include <math.h>

Szesciokat::Szesciokat(double a) {
    this->a = a;
}

double Szesciokat::obwod() {
    return 6*a;
}

double Szesciokat::pole() {
    return 3*sqrt(3)/2 * a * a;
}