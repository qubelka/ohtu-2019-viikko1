package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varastoAlkusaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoAlkusaldolla = new Varasto(10,5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonAlkusaldolla() {
        assertThat(varastoAlkusaldolla.getSaldo(), is(5.0));
    }
    
    @Test
    public void konstruktoriLuoVarastonJosTilavuusNegatiivinen() {
        Varasto kayttokelvoton = new Varasto(-20);
        assertThat(kayttokelvoton.getTilavuus(), is(0.0));
    }
    
    @Test
    public void kahdenParametrinKonstruktoriLuoVarastonJosTilavuusNegatiivinen() {
        Varasto kayttokelvoton = new Varasto(-20,-5);
        assertThat(kayttokelvoton.getTilavuus(), is(0.0));
    }
    
    @Test
    public void kahdenParametrinKonstruktoriNollaaSaldonJosNegatiivinen() {
        Varasto kayttokelvoton = new Varasto(20,-5);
        assertThat(kayttokelvoton.getSaldo(), is(0.0));
    }
    
    @Test
    public void josAlkusaldoIsompiKuinTilavuusSaldoksiAsetetaanTilavuus() {
        Varasto v = new Varasto(10,15);
        assertThat(v.getSaldo(), is(10.0));
    }

    @Test
    public void alkusaldoVarastollaOikeaTilavuus() {
        assertThat(varastoAlkusaldolla.getTilavuus(), is(10.0));
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoNegatiivisellaTilavuudellaJaPositiivisellaSaldollaKayttokelvoton() {
        Varasto kayttokelvoton = new Varasto(-10,10);
        assertThat(kayttokelvoton.getSaldo(), is(0.0));
        assertThat(kayttokelvoton.getTilavuus(), is(0.0));
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenMaaranLisaaminenEiVaikutaSaldoon() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-5);
        assertThat(varasto.getSaldo(), is(saldo));
    }
    
    @Test
    public void saldoksiTuleeTilavuusJosYritetaanLisataEnenmmanKuinTilavuusSallii() {
        varasto.lisaaVarastoon(11);
        assertThat(varasto.getSaldo(), is(10.0));
    }

    @Test
    public void varastostaEiVoiOttaaNegatiivistaMaaraa() {
        assertThat(varasto.otaVarastosta(-1), is(0.0));
    }
    
    @Test
    public void palautetaanSaldoJosOtettavaMaaraOnEnemman() {
        assertThat(varastoAlkusaldolla.otaVarastosta(6), is(5.0));
    }

    @Test
    public void merkkijonoesitysToimii() {
        String merkkijono = "saldo = 5.0, vielä tilaa 5";
        assertThat(varastoAlkusaldolla.toString(), is(merkkijono));
    }
}