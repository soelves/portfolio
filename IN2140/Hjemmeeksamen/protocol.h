#ifndef __PROTOCOL_H
#define __PROTOCOL_H

#include <sys/types.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>

#define MELDING_VELLYKKET "Takk for at du koblet deg på!\n"
#define MELDING_VELLYKKET_LEN strlen(MELDING_VELLYKKET)

#define MELDING_AVSLUTTER "Takk for besøket, lukker tilkobligen!\n"
#define MELDING_AVSLUTTER_LEN strlen(MELDING_AVSLUTTER)

#define OFFSET_EGEN_ADRESSE 0
#define OFFSET_ANTALL 4
#define OFFSET_LISTE 8

#define NODE 0
#define SERVER 1
#define MAKS_ANTALL 32

struct Node{
    int egen_adresse;
    int socket;
    int antall_kanter;
    int kanter[MAKS_ANTALL];
    int kanter_vekt[MAKS_ANTALL];
    int destinasjoner;
    int destinasjon[MAKS_ANTALL];
    int neste_hopp[MAKS_ANTALL];
    struct Node* parent;
};

void pakk_inn(char melding[], struct Node* node, int fra);
void pakk_ut(char melding[], struct Node* node, int fra);

#endif
