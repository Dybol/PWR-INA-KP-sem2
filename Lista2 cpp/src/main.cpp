#include <iostream>
#include <string.h>
#include "funs.hpp"

using namespace std;

bool sprawdzInput(char* figury_z_osobna, int ilosc_figur, int ilosc_argumentow) {
    int licznik = 0;
    for(int i = 0; i < ilosc_figur; i++) {
        if(figury_z_osobna[i]=='o')
            licznik++;
        else if(figury_z_osobna[i]=='c')
            licznik+=5;
        else if(figury_z_osobna[i]=='p')
            licznik++;
        else if(figury_z_osobna[i]=='s')
            licznik++;
        else
            return false;
    }
    return licznik == ilosc_argumentow;
}

int main(int argc, char* argv[]) {
    if(argc <= 1) {
        cout<<"Wprowadz argumenty!";
        return 0;
    }

    char* figury = argv[1];
    int ilosc_figur = strlen(figury);
    char* figury_z_osobna = strtok (figury,"");

    int licznikArgumentow = 2;
    //zerowy arg to ./main.exe
    //pierwszy argument to ilosc figury
    if(sprawdzInput(figury_z_osobna, ilosc_figur, argc-2)) {
        for(int i = 0; i < ilosc_figur; i++) {
            if(figury_z_osobna[i] == 'o') {
                Kolo* kolo = new Kolo(atof(argv[licznikArgumentow]));
                cout<<"Pole kola: " <<kolo->pole()<<endl;
                cout<<"Obwod kola: "<<kolo->obwod()<<endl;
                licznikArgumentow++;

            }
            else if(figury_z_osobna[i] == 'p') {
                Pieciokat* pieciokat = new Pieciokat(atof(argv[licznikArgumentow]));
                cout<<"Pole pieciokata: " <<pieciokat->pole()<<endl;
                cout<<"Obwod pieciokata: "<<pieciokat->obwod()<<endl;
                licznikArgumentow++;
            }
            else if(figury_z_osobna[i] == 's') {
                Szesciokat* szesciokat = new Szesciokat(atof(argv[licznikArgumentow]));
                cout<<"Pole szesciokata: " <<szesciokat->pole()<<endl;
                cout<<"Obwod szesciokata: "<<szesciokat->obwod()<<endl;
                licznikArgumentow++;
            }
            else if(figury_z_osobna[i] == 'c') {
                double a = atof(argv[licznikArgumentow]);
                double b = atof(argv[licznikArgumentow+1]);
                double c = atof(argv[licznikArgumentow+2]);
                double d = atof(argv[licznikArgumentow+3]);
                double kat = atof(argv[licznikArgumentow+4]);
                if(a == b && b == c && c == d) {
                    if(kat == 90) {
                        Kwadrat* kwadrat = new Kwadrat(a);
                        cout<<"Pole kwadratu: " <<kwadrat->pole()<<endl;
                        cout<<"Obwod kwadratu: "<<kwadrat->obwod()<<endl;
                    } else {
                        Romb* romb = new Romb(a, kat);
                        cout<<"Pole rombu: " <<romb->pole()<<endl;
                        cout<<"Obwod rombu: "<<romb->obwod()<<endl;
                    }
                } else if(kat != 90) {
                    cout<<"Wprowadz poprawne dane dla czworokata!"<<endl;
                    return 0;
                } else if(a==b && c == d) {
                    Prostokat* prostokat = new Prostokat(a, c);
                    cout<<"Pole prostokatu: " <<prostokat->pole()<<endl;
                    cout<<"Obwod prostokatu: "<<prostokat->obwod()<<endl;          
                } else if((a == c && b == d) || (a == d && b == c)) {
                    Prostokat* prostokat = new Prostokat(a, b);
                    cout<<"Pole prostokatu: " <<prostokat->pole()<<endl;
                    cout<<"Obwod prostokatu: "<<prostokat->obwod()<<endl;  
                } else {
                    cout<<"Wprowadzono bledne dane..."<<endl;
                }
                licznikArgumentow+=5;
            }
            if(i != ilosc_figur - 1)
                cout<<"-----------------------"<<endl;
        }
    }

     else {
        cout<<"Blednie wprowadzone dane..."<<endl;
        return 0;
    }

}