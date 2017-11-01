package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void hassuParametriVarastolle() {
        varasto = new Varasto(10, 10);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);

        varasto = new Varasto(0, -1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);

        varasto = new Varasto(10, 11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        
        varasto = new Varasto(0);
        //0!!!!!!!!
        assertEquals(1, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void hassuSaldonLisays() {
        varasto = new Varasto(10);
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);

        varasto.lisaaVarastoon(5);
        saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(6);
        assertEquals(saldo + 5, varasto.getSaldo(), vertailuTarkkuus);
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
    public void otaVarastostaJuttuja(){
        varasto = new Varasto(10, 10);
        varasto.otaVarastosta(-1);
        double saldo = varasto.getSaldo();
        assertEquals(10, saldo, vertailuTarkkuus);
        
        varasto.otaVarastosta(11);
        saldo = varasto.getSaldo();
        assertEquals(0, saldo, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToemiipi(){
        varasto = new Varasto(10);
        double saldo = varasto.getSaldo();
        double mahtuu = varasto.paljonkoMahtuu();
        boolean toimii = false;
        String t = varasto.toString();
        String s = "saldo = " + saldo + ", vielä tilaa " + mahtuu;
        if(s.equals(t)){
            toimii=true;
        }
        assertEquals(true, toimii);
    }

}
