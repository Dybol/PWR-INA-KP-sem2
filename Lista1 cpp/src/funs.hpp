//#pragma once   
#ifndef funs_h
#define funs_h

class WierszTrojkataPascala {
    private:
        int* wiersze;
    public:
        WierszTrojkataPascala(int n);
        ~WierszTrojkataPascala();

        void obliczWiersz(int n);
        int silnia(int n);
        int wspolczynnik(int m);

};

#endif