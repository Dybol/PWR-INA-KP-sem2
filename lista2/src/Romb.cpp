#include "funs.hpp"
#include <math.h>

double Romb::pole() {
    return getA() * getA() * sin(getKat()*3.14/180);
}
double Romb::obwod() {
    return 4*getA();
}