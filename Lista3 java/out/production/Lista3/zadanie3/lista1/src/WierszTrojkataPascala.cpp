
#include <iostream>
#include "funs.hpp"

    WierszTrojkataPascala::WierszTrojkataPascala(int n) {
        wiersze = new int[n+1];

        obliczWiersz(n);
    }
    void WierszTrojkataPascala::obliczWiersz(int n) {
        int licznik = 0;
        while(licznik < n+1 ) {
            wiersze[licznik] = silnia(n) / (silnia(licznik) * silnia(n-licznik));
            licznik++;
        }
    }

    int WierszTrojkataPascala:: silnia(int n) {
        if(n==0) return 1;
        return silnia(n-1) * n;
    }

    int WierszTrojkataPascala::wspolczynnik(int m) {
        return wiersze[m];
    }
    

    WierszTrojkataPascala::~WierszTrojkataPascala() {

    }

