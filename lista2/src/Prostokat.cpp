#include "funs.hpp"

double Prostokat::pole() {
    return getA() * getB();
}
double Prostokat::obwod() {
    return 2*getA() + 2*getB();
}