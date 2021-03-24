#include "funs.hpp"

Kolo::Kolo(double r) {
    this->r = r;
}

double Kolo::pole() {
    return 3.14 * r * r;
}

double Kolo::obwod() {
    return 2 * 3.14 * r;
}