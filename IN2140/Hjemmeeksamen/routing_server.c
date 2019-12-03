#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/select.h>
#include <sys/time.h>
#include <stdlib.h>

#include "util.h"
#include "protocol.h"

#define HJEM_NODE 1
#define BACKLOG 10

int baseport;

//Hjelpemetode til dijkstra, fyller ut routingtabell for nodene.
void legg_til_destinasjon(int d, int h, struct Node* node){

    node->destinasjon[node->destinasjoner] = d;
    node->neste_hopp[node->destinasjoner] = h;
    node->destinasjoner++;

    if(node->egen_adresse == 1){
        //Rekursjon ferdig.
    }else{
        legg_til_destinasjon(d, node->egen_adresse, node->parent);
    }
}

//dijkstra tar inn et array av noder, og regner ut korteste vei fra
//node med adresse 1 til alle andre noder i grafen.
//Først settes et 2d array opp for distansen. Deretter utføres dijkstra.
//Jeg brukte en lærerbok som hadde pseudo-kode som basis, men noen deler
//er ikke optimale iforhold til effektivitet, spessielt da når jeg bruker
//fire for loops. Men, det var den beste måten jeg fikk til å utføre algoritmen,
//og den føles ikke treig, hvertfall ikke for denne oppgaven.

void dijkstra(struct Node** node_array, int node_array_len){
    int distanse[2][node_array_len];
    struct Node* que[node_array_len];
    int tatt_ut_av_que = 0;
    int i, j, k, l;

    for(i = 0; i < node_array_len; i++){
        if(node_array[i]->egen_adresse == HJEM_NODE){
            if(i == 0){
                distanse[0][0] = HJEM_NODE;
                distanse[1][0] = 0;
                que[0] = node_array[i];
            }else{
                distanse[0][i] = distanse[0][0];
                distanse[1][i] = distanse[0][0];
                que[i] = que[0];

                distanse[0][0] = HJEM_NODE;
                distanse[1][0] = 0;
                que[0] = node_array[i];
            }
        }else{
            distanse[0][i] = node_array[i]->egen_adresse;
            distanse[1][i] = 99;
            que[i] = node_array[i];

        }
        node_array[i]->destinasjoner = 0;
    }

    node_array[0]->parent = node_array[0];

    while(tatt_ut_av_que < node_array_len){
        struct Node* en_node = que[tatt_ut_av_que];
        int en_node_vekt = distanse[1][tatt_ut_av_que];
        int en_kant;
        int en_kant_addr;

        for(i = 0; i < en_node->antall_kanter; i++){
            en_kant = en_node->kanter[i];
            for(j = 0; j < node_array_len; j++){
                if(distanse[0][j] == en_kant){
                    en_kant_addr = en_kant;

                    //Her går en_kant fra å være adressen til noden, til å
                    //være vekta til ruta for å komme til den.
                    en_kant = distanse[1][j];

                    if((en_node_vekt + en_node->kanter_vekt[i]) < en_kant){

                        for(k = 0; k < node_array_len; k++){
                            if(en_kant_addr == node_array[k]->egen_adresse){
                                for(l = 0; l < node_array_len; l++){
                                    if(distanse[0][tatt_ut_av_que] ==
                                        node_array[l]->egen_adresse){
                                            node_array[k]->parent = node_array[l];
                                        }
                                }
                            }
                        }
                        en_kant = (en_node_vekt + en_node->kanter_vekt[i]);
                        distanse[1][j] = en_kant;
                    }
                    //Fant kanten jeg lette etter, avslutter løkka.
                    j = node_array_len;
                }
            }
        }
        tatt_ut_av_que++;
    }

    for(i = 0; i < node_array_len; i++){
        int start = node_array[i]->egen_adresse;

        legg_til_destinasjon(start, start, node_array[i]);
    }



    for(i = 0; i < node_array_len; i++){
        for(j = 0; j  < node_array_len; j++){
            int sjekk = 0;
            for(k = 0; k < node_array_len; k++){
                for(l = 0; l < node_array[k]->destinasjoner; l++){
                    if(node_array[i]->egen_adresse == node_array[k]->neste_hopp[l]){
                        if(node_array[j]->egen_adresse == node_array[k]->destinasjon[l]
                        || node_array[i]->egen_adresse == 1){
                            print_weighted_edge(node_array[i]->egen_adresse,
                                node_array[j]->egen_adresse, distanse[1][i]);

                            sjekk = 1;
                        }
                    }
                }
            }
            if(sjekk == 0){
                print_weighted_edge(node_array[i]->egen_adresse,
                    node_array[j]->egen_adresse, -1);
            }
        }
    }
}

//Setter opp socketen for TCP forbindelsen mellom routing_server og node.
int lag_server_socket_tcp(){
    int ret;
    int ja, server_socket = 1;

    struct sockaddr_in server_addr;

    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if(server_socket == -1){
        perror("socket()");
        exit(EXIT_FAILURE);
    }

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(baseport);
    server_addr.sin_addr.s_addr = INADDR_ANY;

    setsockopt(server_socket, SOL_SOCKET, SO_REUSEADDR, &ja, sizeof(int));

    ret = bind(server_socket, (struct sockaddr*)&server_addr, sizeof(server_addr));
    if(ret){
        perror("bind()");
        exit(EXIT_FAILURE);
    }

    ret = listen(server_socket, BACKLOG);
    if(ret == -1){
        perror("listen()");
        exit(EXIT_FAILURE);
    }

    //Vi har nå startet å lytte på socketen.

    return server_socket;
}

//Starter serveren, og lytter og legger til noder til den har mottat alle.
//Deretter kjøres dijkstra, før routingtabellene sendes tilbake til nodene.
void start_server(int sock, int klienter){
    struct sockaddr_in klient_addr;
    socklen_t addrlen = sizeof(struct sockaddr_in);
    char ip_buffer[16];
    int i = 0;
    int siste;
    char buf[1024];
    struct Node** node_array = malloc(sizeof(struct Node*) * klienter);

    while(i < klienter){
        int j = 0;
        memset(buf, '\0', 1024);

        int klient_socket = accept(sock, (struct sockaddr*)&klient_addr, &addrlen);

        if(klient_socket == -1){
            perror("accept()");
            return;
        }

        if(!inet_ntop(klient_addr.sin_family, &(klient_addr.sin_addr), ip_buffer, addrlen)){
            perror("inet_ntop()");
            strcpy(ip_buffer, "N/A");
        }

        LOG(LOGGER_INFO, "Connection from IP address %s and port %u\n", ip_buffer, ntohs(klient_addr.sin_port));

        write(klient_socket, MELDING_VELLYKKET, MELDING_VELLYKKET_LEN);

        while(j == 0){
            ssize_t bytes = read(klient_socket, buf, sizeof(buf));
            if(bytes > 0){
                struct Node* node = malloc(sizeof(struct Node));
                pakk_ut(buf, node, NODE);
                node_array[i] = node;
                j++;
            }
        }

        node_array[i]->socket = klient_socket;
        printf("Node: %d, Antall kanter: %d\n", node_array[i]->egen_adresse,
            node_array[i]->antall_kanter);
        for(j = 0; j < node_array[i]->antall_kanter; j++){
            printf("Kant: %d, Vekt: %d\n", node_array[i]->kanter[j],
            node_array[i]->kanter_vekt[j]);
        }
        i++;
    }
    LOG(LOGGER_INFO, "Ferdig med å hente data fra nodene.");


    //dijkstra
    dijkstra(node_array, klienter);

    i = 0;
    while(i < klienter){
        memset(buf, '\0', 1024);
        pakk_inn(buf, node_array[i], SERVER);
        write(node_array[i]->socket, buf, sizeof(buf));
        i++;
    }

    //Her hopper jeg over å avlsutte Node 1, og avslutter den til slutt, så
    //de andre nodene står klare når den skal sende til de etterpå.
    i = 0;
    siste = 0;

    while(i < klienter){
        if(node_array[i]->egen_adresse == 1){
            siste = i;
        }else{
            write(node_array[i]->socket, MELDING_AVSLUTTER, MELDING_AVSLUTTER_LEN);
            close(node_array[i]->socket);
        }
        i++;
    }

    write(node_array[siste]->socket, MELDING_AVSLUTTER, MELDING_AVSLUTTER_LEN);
    close(node_array[siste]->socket);

    for(i = 0; i < klienter; i++){
        free(node_array[i]);
    }
    free(node_array);
}


int main(int argc, char const *argv[]) {
    int server_socket, i, klienter;

    sscanf(argv[1], "%d", &baseport);
    sscanf(argv[2], "%d", &klienter);

    server_socket = lag_server_socket_tcp();

    start_server(server_socket, klienter);

    close(server_socket);

    return 0;
}
