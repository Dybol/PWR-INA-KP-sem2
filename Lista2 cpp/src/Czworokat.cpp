#include "funs.hpp"

Czworokat::Czworokat(double a, double b, double c, double d, double kat) {
    this->a = a;
    this->b = a;
    this->c = c;
    this->d = d;
    this->kat = kat;
    if(kat > 180 || kat < 0)
        throw("Nieprawidlowa wartosc kata!");

}

double Czworokat::obwod() {
    return a+b+c+d;
}

double Czworokat::getA() {
    return a;
}

double Czworokat::getB() {
    return b;
}

double Czworokat::getC() {
    return c;
}

double Czworokat::getD() {
    return d;
}

double Czworokat::getKat() {
    return kat;
}