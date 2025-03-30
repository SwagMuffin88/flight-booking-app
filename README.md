# Flight Booking App
Tegemist on algse mock-versiooniga fullstack rakendusest lennuistmete valimiseks ja broneerimiseks. Rakendus kasutab 
Postgres andmebaasi ning frontend on ehitatud React raamistiku peal.
Esimesel nädalal kulus ülesande lahendamisele ligikaudu 30-35h, teisel nädalal sain panustada terviseprobleemide
tõttu oluliselt vähem aega, ca 25h, seega kogu töö jaoks kulunud aeg oli umbes 55h. 

Viited dokumentatsioonile:
 - [Projekti lihtsutatud struktuur](doc/project-structure.md)
 - [API dokumentatsioon (Swagger UI)](http://localhost:8080/swagger-ui/index.html#/)
 - [OpenAPI YAML spetsifikatsioon](doc/openapi.yaml)
 - [Rakenduse käivitamise juhend](doc/setup-guide.md)

Kogu ülejäänud dokumentatsioon ja projekt on inglisekeelne.

Rakenduse testimiseks vajaminevate andmete (lennukid, lennud, istmed) loomine
toimub läbi `DataSeeder` klassi, mis käivitub koos rakendusega ning eemaldab vajaduse luua ning
esile kutsuda eraldi päringuid esialgsete andmete lisamiseks. Rakenduse käivitamisel 
on andmebaasis lennugraafikud juba olemas (eelduseks on andmebaasi `flight_db` olemasolu, 
pikemalt seletatud käivitamise juhendis).

Samuti on istmete genereerimine (koos saadaolevuse määramisega) lisatud `config` alla, 
kuna `Service` kiht on siin projetkis eelkätt mõeldud kasutajakogemusega seotud loogika jaoks ning nagu 
eelnevalt mainitud, on selle valiku eesmärk vähendada vajadust teha rakenduse töötamise ajal
eraldi päringuid esialgsete andmete sisestamiseks andmebaasi.


### Mõtteid ülesande lahenduse kohta

Oluline faktor ülesande raskusastme määramisel on ajalimiit, millest tulenevalt pani ülesanne oluliselt 
proovile aja- ning projektiplaneerimise oskused. Peab tunnistama, et rakenduse ehitamiseks on tegemist 
erakordselt lühikese ajaga, mistõttu pidin panustama lahenduse algusfaasis projekti etappide ning 
töögraafiku koostamise peale. Leidsin, et parim lähenemine oleks vähehaaval kõike üheaegselt arendada:
 - Luua IDE projektid koos algse skeletiga nii backendis, kui frontendis
 - Seadistada esimesel päeval andmebaas ning veenduda edukas ühenduse loomises backendiga
 - Igal tööpäeval lisada märkusi/dokumentatsiooni
 - jne.

Samuti tuli arvestada projekti planeerimisel ülesande kirjelduse lahtisust - lihtsam oleks olnud hakata otsast
ehitama rakendust koos kõigega, mis lennupiletite broneerimise rakenduses kasutajakogemust silmas
pidades olla võiks. Kuna aega oli vähe, oli oluline väga selgelt panna paika eesmärgid ning vältida
"ületegemist". Õnneks või paraku ma selle probleemini ei jõudnudki, kuna need kaks nädalat ülesande lahendamist
ei sujunud päris esialgse tööplaani päraselt. Töö planeerimine võib tihti leida aset justkui 
"steriilses" keskkonnas, kus töö kulgu mõjutavaid ootamatuid faktoreid ei pruugita osata ette näha.

#### Natuke tehnilisest poolest
Projekti "eelfaasina" konfigureerisin `DataSeeder` klassi, mille arendamise käigus oli palju 
vaja tähelepanu pöörata olemite omavaheliste suhetele ning veenduda, et need õigeaegselt erinevates
meetodites andmebaasi salvestuvad. Debugger oli seejuures kasulik tööriist, keerulisemate
vigade/erindite tõlgendamiseks kasutasin ChatGPT-d. 

Suurema rõhu panin projektis andmebaasi arhitektuurile ning sellega seonduvate vigade 
parandamisele. Esialgsel hinnangul võis selle peale minna umbes 50% kogu projektile kulunud
ajast. Eesmärk oli sagedaste testide abil tuvastada varakult vigu ning vältida probleemide
tekkimist HTTP requestidega seotud loogika kasvades.

Rakenduse frontend on algeline, kuid selle peamine eesmärk on selle projekti praeguses
faasis veenduda, et viited REST endpointidele toimivad korrektselt. Projekti teisel nädalal sattusin
ka raskuste otsa algsest ajaplaanist kinnipidamisega seetõttu, et mõndade probleemide lahendamise jaoks
lihtsalt kulus oodatust rohkem aega. See on lihtsalt arenduse protsessi olemus.

Kuigi mul ei olnud võimalik implementeerida kõiki nõudeid, panustsin eesmärki veenduda olemasolevate funktsionaalsuste toimimises.
