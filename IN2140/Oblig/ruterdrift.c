#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Struct som opggitt i oppgaven, samt en liste kobligner på str 10, antall
//koblinger som er i lista, og en hjelpesjekk til dybdeførstsøk.
struct ruter{
    unsigned char id;
    unsigned char FLAGG;
    unsigned char modell_lengde;
    char modell[253];
    unsigned char koblinger[10];
    short antall_koblinger;
    unsigned char aktiv_dfs;
};

//Hjelp til dybdeførstsøk.
int fant_den = 0;

//Funksjonen tar inn en FILE peker, hvor filen vi snakker om er den første
//filen som blir brukt til å opprette ruterne.
//
//Funksjonen setter av plass til en struct ruter, fyllern den med \0,
//for så å scanne filen og sette verdiene til rutern.
struct ruter* opprett_ruter(FILE* rutere_fil){
    //Her allokeres 269 bytes, størrelsen av struct ruter.
    struct ruter* ruter_peker = malloc(sizeof(struct ruter));
    memset(ruter_peker, '\0', sizeof(struct ruter));

    size_t ret1 = fread(&ruter_peker->id, sizeof(unsigned char), 1, rutere_fil);
    if(ret1 != 1){
        perror("Ops, fread klarte ikke lese inn id");
        exit(EXIT_FAILURE);
    }
    size_t ret2 = fread(&ruter_peker->FLAGG, sizeof(unsigned char), 1, rutere_fil);
    if(ret2 != 1){
        perror("Ops, fread klarte ikke lese inn FLAGG");
        exit(EXIT_FAILURE);
    }
    size_t ret3 = fread(&ruter_peker->modell_lengde, sizeof(unsigned char), 1, rutere_fil);
    if(ret3 != 1){
        perror("Ops, fread klarte ikke lese inn modell_lengde");
        exit(EXIT_FAILURE);
    }

    size_t ret4 = fread(&ruter_peker->modell, sizeof(char), ruter_peker->modell_lengde, rutere_fil);
    if(ret4 != ruter_peker->modell_lengde){
        perror("Ops, fread klarte ikke lese inn modell");
        exit(EXIT_FAILURE);
    }

    ruter_peker->antall_koblinger = 0;
    ruter_peker->aktiv_dfs = 0;

    return ruter_peker;
}

//Funksjonen setter koblingene mellom ruterne inn hos hver av ruterne som har
//en kobling ut.
//Den tar inn opprettelsesfilen, ruter arrayen, og antall rutere.
void sett_koblinger(FILE* rutere_fil, struct ruter** ruter_array, int antall_rutere){
    size_t har_neste = 1;
    unsigned char kobling_fra;
    unsigned char kobling_til;
    int i;

    while(har_neste == 1){
        har_neste = fread(&kobling_fra, sizeof(unsigned char), 1, rutere_fil);
        if(har_neste == 1){
            har_neste = fread(&kobling_til, sizeof(unsigned char), 1, rutere_fil);
            if(har_neste != 1){
                perror("Ops, fread klarte ikke lese inn kobling_til(feil i fil?)");
                exit(EXIT_FAILURE);
            }
            fgetc(rutere_fil);

            for(i = 0; i < antall_rutere; i++){
                if(kobling_fra == ruter_array[i]->id){
                    (ruter_array[i]->koblinger[ruter_array[i]->antall_koblinger]
                    = kobling_til);
                    ruter_array[i]->antall_koblinger += 1;

                    i = antall_rutere;
                }
            }
        }
    }
}


//Funksjonen tar inn filnavn fra argv,  og en trippelpeker. Denne
//trippelpekern er en peker til et array av rutere.
//
//Funksjonen leser den første filen,  finner ut hvor mange rutere_fil_navn
//som er i filen, oppretter ruterne med funksjonen opprett_ruter, for så
//å lukke filen og returne antall rutere som ble opprettet.

int les_fil1(const char* rutere_fil_navn, struct ruter*** ruter_array_pointer){
    int antall_rutere = 0;

    FILE* rutere_fil = fopen(rutere_fil_navn, "r");
    if(rutere_fil == NULL){
        perror("Kunne ikke åpne fil");
        exit(EXIT_FAILURE);
    }

    size_t ret = fread(&antall_rutere, sizeof(int), 1, rutere_fil);
    if(ret != 1){
        perror("Ops, fread klarte ikke lese tallet i starten av filen");
        exit(EXIT_FAILURE);
    }
    fgetc(rutere_fil);

    //Her allokeres minne til ruter pekere, ganget med antall rutere.
    //Størrelsen vil variere ut ifra antall rutere, antall * 269.
    *ruter_array_pointer = malloc(sizeof(struct ruter*) * antall_rutere);

    struct ruter** ruter_array = *ruter_array_pointer;

    int i;

    for(i = 0; i < antall_rutere; i++){
        ruter_array[i] = opprett_ruter(rutere_fil);
    }

    sett_koblinger(rutere_fil, ruter_array, antall_rutere);

    fclose(rutere_fil);
    return antall_rutere;
}

//Hjelpemetode for å sette et bit.
//Tar inn to unsigned chars og en unsigned char peker.
void bit_sett(unsigned char bit, unsigned char* byte, unsigned char verdi){
    if(verdi == 0){
        bit = 1 << bit;
        bit ^= 0xff;
        *byte = *byte & bit;
    }else{
        bit = verdi << bit;
        *byte = *byte | bit;
    }
}

//Hjelpemetode for å sjekke om et bit er på.
//Tar inn to unsigned chars.
int bit_test(unsigned char bit, unsigned char byte){
    bit = 1 << bit;
    return (bit & byte);
}

//Funksjonen printer ut nyttig info om en ruter.
//Den tar inn kommandofilen, ruter arrayen, og dens lengde.
int print_ruter(FILE* kommando_fil, struct ruter** ruter_array, int ruter_array_lengde){
    int ruter_id;
    char* tall_template = "%d";
    size_t ret = fscanf(kommando_fil, tall_template, &ruter_id);
    if(ret != 1){
        perror("Ops, fread klarte ikke lese ruteren sin id");
        exit(EXIT_FAILURE);
    }
    fgetc(kommando_fil);

    int i;
    for(i = 0; i < ruter_array_lengde; i++){
        if(ruter_array[i] == NULL){
            continue;
        }
        if(ruter_id == ruter_array[i]->id){

            printf("\nRuter id: %d\nModell: %sKoblinger(id):",
            ruter_array[i]->id, ruter_array[i]->modell);
            int j;
            for(j = 0; j < ruter_array[i]->antall_koblinger; j++){
                printf(" %d", ruter_array[i]->koblinger[j]);
            }
            printf(".\n");

            printf("Er rutern aktiv: ");
            if(bit_test(0, ruter_array[i]->FLAGG) != 0){
                printf("Ja.\n");
            }else{
                printf("Nei.\n");
            }

            printf("Er rutern trådløs: ");
            if(bit_test(1, ruter_array[i]->FLAGG) != 0){
                printf("Ja.\n");
            }else{
                printf("Nei.\n");
            }

            printf("Støtter rutern 5GHz: ");
            if(bit_test(2, ruter_array[i]->FLAGG) != 0){
                printf("Ja.\n");
            }else{
                printf("Nei.\n");
            }
            printf("\n");
        }
    }
    return 0;
}

//Funksjonen legger til en ny kobling fra en til en annen ruter.
//Den tar inn kommandofilen, ruter arrayen, og dens lengde.
void legg_til_kobling(FILE* kommando_fil, struct ruter** ruter_array, int ruter_array_lengde){
    int kobling_fra;
    unsigned char kobling_til;
    char* tall_template = "%d";
    size_t ret = fscanf(kommando_fil, tall_template, &kobling_fra);
    if(ret != 1){
        perror("Ops, fread klarte ikke lese ruteren sin id");
        exit(EXIT_FAILURE);
    }
    fgetc(kommando_fil);

    ret = fscanf(kommando_fil, tall_template, &kobling_til);
    if(ret != 1){
        perror("Ops, fread klarte ikke lese ruteren sin id");
        exit(EXIT_FAILURE);
    }
    fgetc(kommando_fil);

    int i;
    for(i = 0; i < ruter_array_lengde; i++){
        if(ruter_array[i] == NULL){
            continue;
        }
        if(kobling_fra == ruter_array[i]->id){
            (ruter_array[i]->koblinger[ruter_array[i]->antall_koblinger]
            = kobling_til);
            ruter_array[i]->antall_koblinger += 1;

            i = ruter_array_lengde;
        }
    }
}

//Funksjonen sletter en ruter frigir minneområdet, og setter ned det totale
//antallet av rutere.
//Den tar inn kommandofilen, ruter arrayen, og dens lengde,  og det totale
//antallet av rutere, brukes når det skal skrives til fil.
void slett_ruter(FILE* kommando_fil, struct ruter** ruter_array,
    int ruter_array_lengde, int* antall_rutere){

    int ruter_id;
    char* tall_template = "%d";
    size_t ret = fscanf(kommando_fil, tall_template, &ruter_id);
    if(ret != 1){
        perror("Ops, fread klarte ikke lese ruteren sin id");
        exit(EXIT_FAILURE);
    }
    fgetc(kommando_fil);

    int i;
    for(i = 0; i < ruter_array_lengde; i++){
        if(ruter_array[i] == NULL){
            continue;
        }
        if(ruter_id == ruter_array[i]->id){
            memset(ruter_array[i], '\0', sizeof(struct ruter));
            //ruter_array[i] = NULL;
            free(ruter_array[i]);
            ruter_array[i] = NULL;
            (*antall_rutere)--;
        }
    }
}
//Funksjonen gir en ruter et nytt navn.
//Den tar inn kommandofilen, ruter arrayen, og dens lengde.
void sett_modell(FILE* kommando_fil, struct ruter** ruter_array, int ruter_array_lengde){
    int ruter_id;
    char* tall_template = "%d";
    size_t ret = fscanf(kommando_fil, tall_template, &ruter_id);
    if(ret != 1){
        perror("Ops, fscanf klarte ikke lese ruteren sin id");
        exit(EXIT_FAILURE);
    }
    fgetc(kommando_fil);

    int i;
    for(i = 0; i < ruter_array_lengde; i++){
        if(ruter_array[i] == NULL){
            continue;
        }
        if(ruter_id == ruter_array[i]->id){
            fgets(ruter_array[i]->modell, sizeof(char)*253, kommando_fil);
            ruter_array[i]->modell_lengde = strlen(ruter_array[i]->modell);
        }
    }
}

//Funksjonen setter flaggene til en ruter. Den bruker hjelpemetodene bit_sett
//og bit_test.
//Den tar inn kommandofilen, ruter arrayen, og dens lengde.
void sett_flagg(FILE* kommando_fil, struct ruter** ruter_array, int ruter_array_lengde){
    int ruter_id;
    int flagg;
    int verdi;

    char* tall_template = "%d";
    size_t ret = fscanf(kommando_fil, tall_template, &ruter_id);
    if(ret != 1){
        perror("Ops, fread klarte ikke lese ruteren sin id");
        exit(EXIT_FAILURE);
    }
    fgetc(kommando_fil);

    ret = fscanf(kommando_fil, tall_template, &flagg);
    if(ret != 1){
        perror("Ops, fread klarte ikke lese ruteren sin id");
        exit(EXIT_FAILURE);
    }
    fgetc(kommando_fil);

    ret = fscanf(kommando_fil, tall_template, &verdi);
    if(ret != 1){
        perror("Ops, fread klarte ikke lese ruteren sin id");
        exit(EXIT_FAILURE);
    }
    fgetc(kommando_fil);

    int i;
    for(i = 0; i < ruter_array_lengde; i++){
        if(ruter_array[i] == NULL){
            continue;
        }
        //Skjønte ikke helt hva flagg 4 var, så det heller ikke brukt i
        //noen av kommandoene. Tok den derfor ikke med her.
        if(ruter_id == ruter_array[i]->id){
            if(flagg == 0 | flagg == 1 | flagg == 2){
                bit_sett(flagg, &ruter_array[i]->FLAGG, verdi);
            }
        }
    }
}

//Hjelpemetode til finnes_rute, gjør et dybde først søk på grafen.
//Hvis en ruter er aktiv(har aktiv_dfs aktivert), så vil metoden
//stoppe å repetere seg. Hvis ikke, så sjekker den om det er naboen den skal
//til. Hvis det er det, er den ferdig, og har funnet en vei, hvis ikke
//fortsetter den søket videre til sine naboer.
//Den tar inn ruter arrayen, ruter_id_fra og til.
void dfs(struct ruter** ruter_array, int ruter_id_fra, int ruter_id_til){

    if(ruter_array[ruter_id_fra]->aktiv_dfs == 1){

    }else{
        int i;
        for (i = 0; i < ruter_array[ruter_id_fra]->antall_koblinger; i++){
            if(ruter_array[ruter_array[ruter_id_fra]->koblinger[i]] == NULL){
                continue;
            }
            if(ruter_array[ruter_id_fra]->koblinger[i] == ruter_id_til){
                fant_den = 1;
            }
        }
        if(fant_den == 0){
            for(i = 0; i < ruter_array[ruter_id_fra]->antall_koblinger; i++){
                if(ruter_array[ruter_array[ruter_id_fra]->koblinger[i]] == NULL){
                    continue;
                }
                ruter_array[ruter_id_fra]->aktiv_dfs = 1;
                dfs(ruter_array, ruter_array[ruter_id_fra]->koblinger[i], ruter_id_til);
                ruter_array[ruter_id_fra]->aktiv_dfs = 0;
            }
        }

    }
}

//Funksjonen ser om det finnes en rute mellom to rutere. Den bruker dfs som
//hjelpemetode.
//Den tar inn kommandofilen, ruter arrayen, og dens lengde.
void finnes_rute(FILE* kommando_fil, struct ruter** ruter_array, int ruter_array_lengde){
    int ruter_id_fra;
    int ruter_id_til;
    char* tall_template = "%d";
    size_t ret = fscanf(kommando_fil, tall_template, &ruter_id_fra);
    if(ret != 1){
        perror("Ops, fscanf klarte ikke lese ruteren sin id");
        exit(EXIT_FAILURE);
    }
    fgetc(kommando_fil);

    ret = fscanf(kommando_fil, tall_template, &ruter_id_til);
    if(ret != 1){
        perror("Ops, fscanf klarte ikke lese ruteren sin id");
        exit(EXIT_FAILURE);
    }
    fgetc(kommando_fil);

    int i;
    fant_den = 0;
    for(i = 0; i < ruter_array_lengde; i++){
        if(ruter_array[i] == NULL){
            continue;
        }
        if(ruter_array[i]->id == ruter_id_fra){
            dfs(ruter_array, ruter_id_fra, ruter_id_til);
            if(fant_den == 1){
                printf("Det finnes en rute mellom %d og %d.\n",
                ruter_id_fra, ruter_id_til);
            }else{
                printf("Det finnes ikke en rute mellom %d og %d.\n",
                ruter_id_fra, ruter_id_til);
            }
        }
    }
}

//Funksjonen les_fil2 tar inn kommandofilen som argument, og er
//rammeverket for de ulike kommandofunksjonene.
//Den tar inn kommandofilnavnet, ruter arrayen, dens lengde, og en peker
//til det totale antallet av rutere.
void les_fil2(const char* kommando_fil_navn, struct ruter** ruter_array,
    int ruter_array_lengde, int* antall_rutere){

    FILE* kommando_fil = fopen(kommando_fil_navn, "r");
    if(kommando_fil == NULL){
        perror("Kunne ikke åpne fil");
        exit(EXIT_FAILURE);
    }

    size_t har_neste = 1;
    char* string_template = "%s ";
    char kommando [128];
    char* print_sjekk = "print";
    char* ltk_sjekk = "legg_til_kobling";
    char* slett_sjekk = "slett_router";
    char* modell_sjekk = "sett_modell";
    char* flagg_sjekk = "sett_flag";
    char* rute_sjekk = "finnes_rute";

    while(har_neste == 1){
        har_neste = fscanf(kommando_fil, string_template, &kommando);
        if(har_neste == 1){
            if(strncmp(kommando, print_sjekk, 5) == 0){
                print_ruter(kommando_fil, ruter_array, ruter_array_lengde);
            }
            if(strncmp(kommando, ltk_sjekk, 16) == 0){
                legg_til_kobling(kommando_fil, ruter_array, ruter_array_lengde);
            }
            if(strncmp(kommando, slett_sjekk, 12) == 0){
                slett_ruter(kommando_fil, ruter_array, ruter_array_lengde, antall_rutere);
            }
            if(strncmp(kommando, modell_sjekk, 11) == 0){
                sett_modell(kommando_fil, ruter_array, ruter_array_lengde);
            }
            if(strncmp(kommando, flagg_sjekk, 9) == 0){
                sett_flagg(kommando_fil, ruter_array, ruter_array_lengde);
            }
            if(strncmp(kommando, rute_sjekk, 11) == 0){
                finnes_rute(kommando_fil, ruter_array, ruter_array_lengde);
            }
        }
    }
    fclose(kommando_fil);
}

//Funksjonen skriver til filen den brukte til å opprette seg selv.
//Den tar inn filnavnet, ruter arreyen, dens lengde, og det toale antallet
//av rutere.
void skriv_til_fil(const char* rutere_fil_navn, struct ruter** ruter_array,
    int ruter_array_lengde, int antall_rutere){

    FILE* rutere_fil = fopen(rutere_fil_navn, "w");
    if(rutere_fil == NULL){
        perror("Klarte ikke åpne fil for skriving\n");
        exit(EXIT_FAILURE);
    }

    size_t ret = fwrite(&antall_rutere, sizeof(int), 1, rutere_fil);
    if (ret != 1) {
        perror("fread klarte ikke skrive tallet i starten av filen");
        exit(EXIT_FAILURE);
    }
    fprintf(rutere_fil, "\n");

    int i;
    for(i = 0; i < ruter_array_lengde; i++){
        if(ruter_array[i] == NULL){
            continue;
        }
        fprintf(rutere_fil, "%c%c%c", ruter_array[i]->id,
        ruter_array[i]->FLAGG, ruter_array[i]->modell_lengde);
        fprintf(rutere_fil, "%s", ruter_array[i]->modell);
    }

    for(i = 0; i < ruter_array_lengde; i++){
        if(ruter_array[i] == NULL){
            continue;
        }
        if(ruter_array[i]->antall_koblinger > 0){
            int j;
            for(j = 0; j < ruter_array[i]->antall_koblinger; j++){
                fprintf(rutere_fil, "%c %c\n", ruter_array[i]->id,
                ruter_array[i]->koblinger[j]);
            }
        }
    }
    fclose(rutere_fil);
}

//Metode som frigjør minne inne i ruter arrayen.
//Den tar inn ruter arrayen og den lengde.
void free_minne(struct ruter** ruter_array, int ruter_array_lengde){
    int i;
    for(i = 0; i < ruter_array_lengde; i++){
        free(ruter_array[i]);
    }
}

//I main lages det først en liste av structer.
//Deretter leses inn den første filen, og ruterne opprettes.
//Videre leses den andre filen inn, og kommandoene blir utført.
//Så skrives det nye ruter oppsettet til filen den brukte til å opprette.
//Til slutt frigis minne innenfra og ut.
int main(int argc, char const *argv[]) {
    struct ruter** ruter_array;

    int ruter_array_lengde = les_fil1(argv[1], &ruter_array);
    int antall_rutere = ruter_array_lengde;

    les_fil2(argv[2], ruter_array, ruter_array_lengde, &antall_rutere);
    skriv_til_fil(argv[1], ruter_array, ruter_array_lengde, antall_rutere);
    //Frigir minne til alle ruterne.
    free_minne(ruter_array, ruter_array_lengde);
    free(ruter_array);
    return argc;
}
