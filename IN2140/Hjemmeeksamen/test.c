#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAKS_LENGDE 256
#define QUIT "QUIT"
#define PAKKE_OFFSETT_LENGDE 0
#define PAKKE_OFFSETT_DEST 2
#define PAKKE_OFFSETT_KILDE 4
#define PAKKE_OFFSETT_MELDING 6

struct Pakke{
    short lengde;
    short destinasjon;
    short kilde_adresse;
    char melding[MAKS_LENGDE];
};

void scan_eks(){
    char melding[MAKS_LENGDE] = "4 Dette er en melding!\n";
    int nummer = 0;
    char quit[4];
    int ret;

    sscanf(melding, "%d", &nummer);

    printf("Nummer fra melding: %d\n", nummer);
    printf("Hele meldingen: %s\n", melding);

    memset(melding, '\0', sizeof(MAKS_LENGDE));

    ret = sscanf(melding, "%d %s", &nummer, quit);
    if(ret == 2){
        printf("Nummer fra melding: %d\n", nummer);
        if(quit == QUIT){
            printf("Avlsutter...\n");
        }
    }else{
        perror("Klarte ikke lese fra tom_melding");
    }

    char quit_melding[MAKS_LENGDE] = "8 QUIT\n";
    int quit_nummer;

    sscanf(quit_melding, "%d %s", &quit_nummer, quit);

    ret = strncmp(quit, QUIT, 4);

    if(ret == 0){
        printf("%d\n", quit_nummer);
        printf("%s\n", quit);
    }
}

void lag_melding(char melding[], struct Pakke* pakke){
    size_t melding_len = strlen(pakke->melding);
    memcpy(&(melding[PAKKE_OFFSETT_LENGDE]), &pakke->lengde, sizeof(short));
    memcpy(&(melding[PAKKE_OFFSETT_DEST]), &pakke->destinasjon, sizeof(short));
    memcpy(&(melding[PAKKE_OFFSETT_KILDE]), &pakke->kilde_adresse, sizeof(short));
    strncpy(&(melding[PAKKE_OFFSETT_MELDING]), pakke->melding, melding_len);
}

void pakk_ut_melding(char melding[], struct Pakke* pakke){
    memcpy(&pakke->lengde, (&melding[PAKKE_OFFSETT_LENGDE]), sizeof(short));
    memcpy(&pakke->destinasjon, &(melding[PAKKE_OFFSETT_DEST]), sizeof(short));
    memcpy(&pakke->kilde_adresse, &(melding[PAKKE_OFFSETT_KILDE]), sizeof(short));

    size_t melding_len = (size_t)pakke->lengde - (size_t)(sizeof(short) * 3);
    strncpy(pakke->melding, &(melding[PAKKE_OFFSETT_MELDING]), melding_len);
}

void les_fil(FILE* fil){
    char melding[MAKS_LENGDE];
    struct Pakke* pakke;
    struct Pakke* pakke2;
    char char_pakke[MAKS_LENGDE];
    int quit;

    while(fgets(melding, MAKS_LENGDE, fil) != NULL){
        pakke = malloc(sizeof(struct Pakke));
        pakke2 = malloc(sizeof(struct Pakke));

        sscanf(melding, "%hd", &pakke->destinasjon);

        if(pakke->destinasjon < 10){
            strcpy(pakke->melding, &melding[2]);
        }else if(pakke->destinasjon < 100){
            strcpy(pakke->melding, &melding[3]);
        }else if(pakke->destinasjon < 1000){
            strcpy(pakke->melding, &melding[4]);
        }else{
            strcpy(pakke->melding, melding);
        }

        pakke->lengde = (short)(strlen(pakke->melding) + (sizeof(short)*3));
        pakke->kilde_adresse = 1;

        lag_melding(char_pakke, pakke);
        pakk_ut_melding(char_pakke, pakke2);

        quit = strncmp(pakke->melding, QUIT, 4);
        if(quit == 0){
            printf("Avslutter node %d\n", pakke->destinasjon);
        }else{
            printf("Lengde: %hd, Kilde: %hd, Destinasjon: %hd\nMelding: %s",
            pakke2->lengde, pakke2->kilde_adresse, pakke2->destinasjon, pakke2->melding);
        }
        
        memset(melding, '\0', sizeof(MAKS_LENGDE));
        memset(char_pakke, '\0', sizeof(MAKS_LENGDE));
    }
    free(pakke);
    free(pakke2);
}

void unsigned_eksempel(){
    short lengde, destinasjon, kilde_adresse;
    char melding[MAKS_LENGDE] = "Dette er en melding\n";

    unsigned char* unsigned_array_peker;

    lengde = 30;
    destinasjon = 8;
    kilde_adresse = 1;

    printf("Lengde: %hd, Dest: %hd, Kilde: %hd\nMelding: %s",
    lengde, destinasjon, kilde_adresse, melding);

    unsigned short lengde_un = lengde;
    unsigned char unsigned_array = melding;

    printf("%u\n", lengde_un);
    printf("%s\n", unsigned_array);

}

int main(void){

    //scan_eks();


    FILE* min_fil;
    min_fil = fopen("data.txt", "r");
    les_fil(min_fil);


    //unsigned_eksempel();

    return 0;
}
