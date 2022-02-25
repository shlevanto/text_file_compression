Projekti kloonattu: 25.2.2022 16:07

Yleistä:
- Sovellus toimii ja käyttöliittymä on yksinkertainen ja helppokäyttöinen. 
- Komentoriviliittymän ääkköset eivät näy oikein, mutta se voi olla oman koneeni asetusten vika. Kannattaa kuitenkin tarkistaa.
- Pelin ratkaisutapa on mielenkiintoinen. Tekoälyt ovat sinällään hyvin yksinkertaisia, mutta niiden kierrättäminen pelimenestyksen perusteella on hauska idea.
- Kaikki tiedostot ovat nyt samassa kansiossa, rakennetta voisi selkeyttää viemällä tekoälyt omaan kansioonsa (package)
- Yksikkötestit ovat vielä kesken, joten testeistä ei saa selkeää kokonaiskuvaa tässä vaiheessa. Myös testikattavuuden raportointi puuttuu.
- Peli kysyy nyt jokaisen erän jälkeen, haluanko jatkaa. Pelaisin mielelläni monta erää putkeen, joten voisiko käyttöliittymää ajatella niin että se ottaa vastaan pelikomentoja, kunnes annetaan pyyntö pelin lopettamisesta (esim. X tai Q)?
- Admin -moodi on hauska yksityiskohta. On mielenkiintoista nähdä, mitä kone aikoo valita.

Yksityiskohtia:
- Pelikomennot toistuvat kaikissa luokissa merkkijonoina. Tämä on virhealtista, jos koodia muokataan. Tai jos vaikka haluaisi pelata jotakin hauskaa varianttia (kuten ufo-lehmä-bakteeri tai torakka-saapas-ydinpommi). Toimivampi ratkaisu voisi olla pelikomentojen säilöminen keskitetysti Enum -luokassa tai vaikka HashMapissa, joka annetaan parametrina peliolioille. Tällöin komentoja tarvitsisi ylläpitää vain yhdessä paikassa.
- Pistelaskuri olisi hauska lisä. Olisi mielenkiintoista nähdä miten pärjää ai:ta vastaan.
- Minulle aukeaa vähän huonosti, miten ai valikoituu. Miksi tekoälyolio luodaan "taikaluvulla" 4? 
- Nyt myös näyttää siltä, että tekoäly 2 ei pääse lainkaan pelaamaan, koska AI luokassa on ilmeisesti typo.
```
void paivitaAi(int i) {
        i--;
        //v�ltet��n nullPointerException
        if (i==-1) i=4;
        stats.setAiVastaukset(0, ai1.vastaus(stats.getPelaajaVastaukset()));
        stats.setAiVastaukset(1, ai1.vastaus(stats.getPelaajaVastaukset()));
        stats.setAiVastaukset(2, ai3.vastaus(stats.getPelaajaVastaukset(), i));
        stats.setAiVastaukset(3, ai4.vastaus(stats.getPelaajaVastaukset(), i));
}
```  

Yhteenveto
- Pelin idea on hausta ja helposti hahmotettavissa. Rakenteessa on muutamia minulle hieman hankalasti avautuvia ratkaisuja. Hyvää työtä!

