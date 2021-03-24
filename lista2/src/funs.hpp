class Figura {
    public:
        double pole();
        double obwod();
};
class Czworokat : Figura {
    public: 
        Czworokat(double a, double b, double c, double d, double kat);
        ~Czworokat();
        double obwod();
        double getA();
        double getB();
        double getC();
        double getD();
        double getKat();
    private:
        double a;
        double b;
        double c;
        double d;
        double kat;
};
class Kwadrat : Czworokat {
    public:
        Kwadrat(double a) : Czworokat(a,a,a,a,90) {

        }
        ~Kwadrat();
        double pole();
        double obwod();
};
class Prostokat : Czworokat {
    public:
        Prostokat(double a, double b) : Czworokat(a,b,a,b,90) {

        }
        ~Prostokat();
        double obwod();
        double pole();
};
class Romb : Czworokat {
    public:
        Romb(double a, double kat) : Czworokat(a,a,a,a,kat) {

        }
        ~Romb();
        double pole();
        double obwod();
};
class Kolo : Figura {
    private: 
        double r;
    public:
        Kolo(double r);
        ~Kolo();
        double obwod();
        double pole();
};
class Pieciokat : Figura {
    private: 
        double a;
    public: 
        Pieciokat(double a);
        ~Pieciokat();
        double obwod();
        double pole();
};

class Szesciokat : Figura {
    private: 
        double a;
    public: 
        Szesciokat(double a);
        ~Szesciokat();
        double obwod();
        double pole();
};
