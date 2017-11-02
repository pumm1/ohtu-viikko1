package ohtuesimerkki;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchWorks() {
        String search = "Kurri";
        Player result = null;
        result = stats.search(search);
        boolean works = false;
        if (result != null) {
            works = true;
        }
        assertEquals(true, works);
        works = false;
        search = "Jarmo";
        result = stats.search(search);
        if (result == null) {
            works = true;
        }
        assertEquals(true, works);
    }
    
    @Test
    public void topScorersWork(){
        List<Player> list = null;
        list = stats.topScorers(4);
        assertEquals("Gretzky", list.get(0).getName());
        assertEquals("Yzerman", list.get(2).getName());
        assertEquals("Lemieux", list.get(1).getName());
        assertEquals("Kurri", list.get(3).getName());
        assertEquals("Semenko", list.get(4).getName());
        
        list = stats.topScorers(0);
        assertEquals(1, list.size());
    }
    
    @Test
    public void teamWorks(){
        String team = "Jokerit";
        List<Player> list = null;
        list = stats.team(team);
        boolean works = false;
        if(list.size() == 0){
            works = true;
        }
        assertEquals(true, works);
        works = false;
        team = "EDM";
        list = stats.team(team);
        if(list.size() == 3){
            works = true;
        }
        assertEquals(true, works);
    }

    @Test
    public void testConstructor() {
        Reader r = stats.getReader();
        assertEquals(readerStub, r);

        //test we have all the players
        List<Player> list = stats.getPlayers();
        boolean works = false;
        Player p = list.get(0);
        if (p.getName().equals("Semenko")) {
            p = list.get(1);
            if (p.getName().equals("Lemieux")) {
                p = list.get(2);
                if (p.getName().equals("Kurri")) {
                    p = list.get(3);
                    if (p.getName().equals("Yzerman")) {
                        p = list.get(4);
                        if (p.getName().equals("Gretzky")) {
                            works = true;
                        }
                    }
                }
            }
        }
        assertEquals(true, works);
    }

}
