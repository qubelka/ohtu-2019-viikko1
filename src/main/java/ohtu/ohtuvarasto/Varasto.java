package ohtu.ohtuvarasto;

public class Varasto {

    private double tilavuus;
    private double saldo;

    public Varasto(double tilavuus) {
        this(tilavuus, 0);
    }

    public Varasto(double tilavuus, double alkuSaldo) {
        if (tilavuus > 0.0) {
            this.tilavuus = tilavuus;
            setAlkuSaldo(tilavuus, alkuSaldo);
        } else {
            this.tilavuus = 0.0;
        }
    }

    private void setAlkuSaldo(double tilavuus, double alkuSaldo) {
        if (alkuSaldo > 0.0) {
            if (alkuSaldo > tilavuus) {
                this.saldo = tilavuus;
            } else {
                this.saldo = alkuSaldo;
            }
        } else {
            this.saldo = 0.0;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public double getTilavuus() {
        return tilavuus;
    }

    public double paljonkoMahtuu() {
        return tilavuus - saldo;
    }

    public void lisaaVarastoon(double maara) {
        if (maara < 0) {
            return;
        }
        if (maara <= paljonkoMahtuu()) {
            saldo = saldo + maara;
        } else {
            saldo = tilavuus;
        }
    }

    public double otaVarastosta(double maara) {
        if (maara < 0) {
            return 0.0;
        }
        if (maara > saldo) {
            double kaikkiMitaVoidaan = saldo;
            saldo = 0.0;
            return kaikkiMitaVoidaan;
        }

        saldo = saldo - maara;
        return maara;
    }

    @Override
    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}
