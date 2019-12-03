#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/select.h>
#include <sys/time.h>

#include "util.h"
#include "print_lib.h"
#include "protocol.h"

#define HJEM_NODE 1
#define MAKS_LENGDE 1024
#define QUIT "QUIT"
#define PAKKE_OFFSETT_LENGDE 0
#define PAKKE_OFFSETT_DEST 2
#define PAKKE_OFFSETT_KILDE 4
#define PAKKE_OFFSETT_MELDING 6

int baseport;

struct Pakke{
    unsigned short lengde;
    unsigned short destinasjon;
    unsigned short kilde_adresse;
    char melding[MAKS_LENGDE];
};
//lag_melding og pakk_ut_melding brukes for å hjelpe til med å sende over UDP.
void lag_melding(char melding[], struct Pakke* pakke){
    size_t melding_len = strlen(pakke->melding);
    memcpy(&(melding[PAKKE_OFFSETT_LENGDE]), &pakke->lengde, sizeof(short));
    memcpy(&(melding[PAKKE_OFFSETT_DEST]), &pakke->destinasjon, sizeof(short));
    memcpy(&(melding[PAKKE_OFFSETT_KILDE]), &pakke->kilde_adresse, sizeof(short));
    strncpy(&(melding[PAKKE_OFFSETT_MELDING]), pakke->melding, melding_len);
}

void pakk_ut_melding(char melding[], struct Pakke* pakke){
    memcpy(&pakke->lengde, &(melding[PAKKE_OFFSETT_LENGDE]), sizeof(short));
    memcpy(&pakke->destinasjon, &(melding[PAKKE_OFFSETT_DEST]), sizeof(short));
    memcpy(&pakke->kilde_adresse, &(melding[PAKKE_OFFSETT_KILDE]), sizeof(short));

    size_t melding_len = (size_t)pakke->lengde - (size_t)(sizeof(short) * 3);
    strncpy(pakke->melding, &(melding[PAKKE_OFFSETT_MELDING]), melding_len);
}

//Setter opp forbindelsen til routing_server.
int lag_server_socket(int adresse){
    int ret, ja, server_socket = 1;
    struct sockaddr_in server_adresse;
    adresse += baseport;

    server_socket = socket(AF_INET, SOCK_DGRAM, 0);
    if(server_socket == -1){
        perror("server_socket");
    }

    server_adresse.sin_family = AF_INET;
    server_adresse.sin_port = htons(adresse);
    server_adresse.sin_addr.s_addr = INADDR_ANY;

    setsockopt(server_socket, SOL_SOCKET, SO_REUSEADDR, &ja, sizeof(int));

    ret = bind(server_socket, (struct sockaddr*)&server_adresse, sizeof(server_adresse));
    if(ret){
        perror("bind");
        exit(EXIT_FAILURE);
    }

    return server_socket;
}

//Metode for å sende over udp til andre noder, blir brukt av alle noder.
void send_udp(char udp_melding[], int adresse, unsigned short lengde){
    int klient_socket;
    struct sockaddr_in server_adresse;
    socklen_t server_adresse_len = sizeof(server_adresse);
    adresse += baseport;

    klient_socket = socket(AF_INET, SOCK_DGRAM, 0);
    if(klient_socket == -1){
        perror("klient_socket");
    }

    server_adresse.sin_family = AF_INET;
    server_adresse.sin_port = htons(adresse);
    server_adresse.sin_addr.s_addr = inet_addr("127.0.0.1");

    sendto(klient_socket, udp_melding, (size_t)lengde, NULL, &server_adresse, server_adresse_len);

    close(klient_socket);
}

//Metode som alle andre noder enn node 1 står i, mottar til de blir avsluttet.
void ta_imot(int sock, struct Node* node){
    struct sockaddr_in kilde;
    socklen_t kilde_len = sizeof(kilde);
    unsigned char buf[MAKS_LENGDE];
    int quit, i;
    struct Pakke* pakke = malloc(sizeof(struct Pakke));

    while(1){
        memset(buf,'\0', MAKS_LENGDE);
        memset(pakke, '\0', sizeof(struct Pakke));
        recvfrom(sock, buf, MAKS_LENGDE, NULL, &kilde, kilde_len);

        pakk_ut_melding(buf, pakke);
        char melding[pakke->lengde];
        lag_melding(melding, pakke);

        quit = strncmp(pakke->melding, QUIT, 4);
        if((int)pakke->destinasjon == node->egen_adresse){
            //Flipper før jeg sender til print.
            char til_print[pakke->lengde];
            pakke->lengde = htons(pakke->lengde);
            pakke->destinasjon = htons(pakke->destinasjon);
            pakke->kilde_adresse = htons(pakke->kilde_adresse);
            lag_melding(til_print, pakke);
            print_received_pkt((short)(node->egen_adresse), til_print);

            if(quit == 0){
                free(pakke);
                free(node);
                exit(EXIT_SUCCESS);
            }
        }else{
            for(i = 0; i < node->destinasjoner; i++){
                if(node->destinasjon[i] == pakke->destinasjon){
                    //Flipper før jeg sender til print.
                    char til_print[pakke->lengde];
                    pakke->lengde = htons(pakke->lengde);
                    pakke->destinasjon = htons(pakke->destinasjon);
                    pakke->kilde_adresse = htons(pakke->kilde_adresse);
                    lag_melding(til_print, pakke);
                    print_forwarded_pkt((short)(node->egen_adresse), til_print);

                    //Flipper for å få riktig lengde.
                    pakke->lengde = htons(pakke->lengde);
                    send_udp(melding, node->neste_hopp[i], pakke->lengde);
                }
            }
        }
    }
    free(pakke);
}

//Brukes av node 1, leser fra data.txt og starter forsendelser til de andre
//nodene i grafen.
void les_fil(FILE* fil, struct Node* node){
    char melding[MAKS_LENGDE];
    char char_pakke[MAKS_LENGDE];
    char char_pakke_print[MAKS_LENGDE];
    int i;

    memset(char_pakke, '\0', MAKS_LENGDE);
    memset(char_pakke_print, '\0', MAKS_LENGDE);

    while(fgets(melding, MAKS_LENGDE, fil) != NULL){
        struct Pakke* pakke = malloc(sizeof(struct Pakke));

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

        pakke->lengde = (unsigned short)(strlen(pakke->melding) + (sizeof(short)*3));

        pakke->kilde_adresse = 1;

        //Flipper før jeg sender til print i loggen.
        pakke->lengde = htons(pakke->lengde);
        pakke->destinasjon = htons(pakke->destinasjon);
        pakke->kilde_adresse = htons(pakke->kilde_adresse);
        lag_melding(char_pakke_print, pakke);
        print_pkt(char_pakke_print);

        //Flipper tilbake før jeg sender videre.
        pakke->lengde = htons(pakke->lengde);
        pakke->destinasjon = htons(pakke->destinasjon);
        pakke->kilde_adresse = htons(pakke->kilde_adresse);
        lag_melding(char_pakke, pakke);

        for(i = 0; i < node->destinasjoner; i++){
            if(node->destinasjon[i] == pakke->destinasjon){
                int neste = node->neste_hopp[i];
                unsigned short len = pakke->lengde;
                free(pakke);
                send_udp(char_pakke, neste, len);
            }
        }

        memset(melding, '\0', MAKS_LENGDE);
        memset(char_pakke, '\0', MAKS_LENGDE);
        memset(char_pakke_print, '\0', MAKS_LENGDE);
    }
    free(node);
}

//Brukes for å prate med routing_server.
void send_og_motta(struct Node* node){
    int ret, klient_socket, i;
    char buf[1024];
    struct sockaddr_in server_addr;

    klient_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (klient_socket == -1) {
        perror("socket");
    }

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(baseport);
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");

    ret = connect(klient_socket, (struct sockaddr *)&server_addr, sizeof(struct sockaddr_in));
    if(ret == -1){
        perror("connect()");
    }

    pakk_inn(buf, node, NODE);
    size_t buf_len = sizeof(buf);
    ret = (int) write(klient_socket, buf, buf_len);
    if(ret == -1){
        perror("write()");
        exit(EXIT_FAILURE);
    }

    i = 0;
    while(i == 0){
        memset(buf, '\0', 1024);
        ssize_t bytes = read(klient_socket, buf, sizeof(buf));
        if(bytes > 0){
            printf("%s", buf);
            i++;
        }
    }
    i = 0;
    while(i == 0){
        memset(buf, '\0', 1024);
        ssize_t bytes = read(klient_socket, buf, sizeof(buf));
        if(bytes > 0){
            pakk_ut(buf, node, SERVER);
            i++;
        }
    }

    i = 0;
    while(i == 0){
        memset(buf, '\0', 1024);
        ssize_t bytes = read(klient_socket, buf, sizeof(buf));
        if(bytes > 0){
            printf("%s", buf);
            i++;
        }
    }

    close(klient_socket);
}

int main(int argc, char const *argv[]){

    struct Node* node = malloc(sizeof(struct Node));
    int i;

    sscanf(argv[1], "%d", &baseport);
    sscanf(argv[2], "%d", &node->egen_adresse);
    node->antall_kanter = argc - 3;
    for(i = 0; i < node->antall_kanter; i++){
        sscanf(argv[i + 3], "%d:%d", &node->kanter[i], &node->kanter_vekt[i]);
    }

    if(node->egen_adresse == 1){
        send_og_motta(node);
        FILE* fil;
        fil = fopen("data.txt", "r");
        les_fil(fil, node);
        fclose(fil);
    }else if(node->egen_adresse > 1){
        send_og_motta(node);
        int server_socket;
        server_socket = lag_server_socket(node->egen_adresse);
        ta_imot(server_socket, node);
        close(server_socket);
    }
    free(node);
    return 0;
}
