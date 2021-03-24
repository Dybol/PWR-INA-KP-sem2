#include <iostream>
#include "funs.hpp"

using namespace std;

bool czy_liczba(const string& s){
   for(int i = 0; i < (int)s.length(); i++) {
      if(!((s[i] >= '0' && s[i] <= '9' )))
        return false;
   }
   return true;
}

void wypiszWszystkie() {
    for(int i = 1; i < 30; i++) {
        WierszTrojkataPascala *w = new WierszTrojkataPascala(i);
        cout<<"Dla i = " <<i<<endl;
        for(int j = 0; j <= i; j++) {
            cout<<w->wspolczynnik(j)<<" ";
        }
    }
}

int main(int argc, char* argv[]) {
    //tutaj zerowym argumentej jest "./main.exe", wiec zaczynam od pierwszefo

    if(argc <= 1) {
        cout<<"Podaj argumenty!"<<endl;
        return 0;
    }
    if(!czy_liczba(argv[1])) {
        cout<<argv[1]<<" - "<<"nieprawidłowa dana"<<endl;
        return 0;
        
    }

    int n = atoi(argv[1]);
    if(n < 0) {
        cout<<n<<" - "<<"nieprawidłowy numer wiersza"<<endl;
        return 0;
    }
    WierszTrojkataPascala * wiersz = new WierszTrojkataPascala(n);

    for(int i = 2; i < argc; i++) {
        if(!czy_liczba(argv[i])) {
            cout<<argv[i]<<" - "<<"nieprawidłowa dana"<<endl;
            continue;
        }
        int nr_wiersza = atoi(argv[i]);
        if(nr_wiersza > n) {
            cout<<nr_wiersza<<" - "<<"liczba spoza zakresu"<<endl;
            continue;
        }

        cout<<nr_wiersza<<" - "<<wiersz->wspolczynnik(nr_wiersza)<<endl;
        
    }

   // wypiszWszystkie(); //dla 15 juz zle wypisuje
}

