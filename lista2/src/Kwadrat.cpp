#include "funs.hpp"

// Kwadrat::Kwadrat(double a, double b, double c, double d, double kat) {
    
// }
// Kwadrat::Kwadrat(double a) : Czworokat(a,a,a,a,90) {
//     //this-> a = a;
// }

double Kwadrat::pole() {
    return getA() * getA();
}
double Kwadrat::obwod() {
    return 4*getA();
}