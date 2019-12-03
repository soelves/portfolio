#include "protocol.h"

void pakk_inn(char melding[], struct Node* node, int fra){
    int str = (int) sizeof(int);
    if(fra == NODE){
        memcpy(&(melding[OFFSET_EGEN_ADRESSE]), &node->egen_adresse, str);
        memcpy(&(melding[OFFSET_ANTALL]), &node->antall_kanter, str);
        memcpy(&(melding[OFFSET_LISTE]), &node->kanter, str * node->antall_kanter);
        memcpy(&(melding[OFFSET_LISTE + (str * node->antall_kanter)]),
        &node->kanter_vekt, str * node->antall_kanter);

    }else if(fra == SERVER){
        memcpy(&(melding[OFFSET_EGEN_ADRESSE]), &node->egen_adresse, str);
        memcpy(&(melding[OFFSET_ANTALL]), &node->destinasjoner, str);
        memcpy(&(melding[OFFSET_LISTE]), &node->destinasjon, str * node->destinasjoner);
        memcpy(&(melding[OFFSET_LISTE + (str * node->destinasjoner)]),
        &node->neste_hopp, str * node->destinasjoner);

    }
}

void pakk_ut(char melding[], struct Node* node, int fra){
    int str = (int) sizeof(int);
    if(fra == NODE){
        memcpy(&node->egen_adresse, &(melding[OFFSET_EGEN_ADRESSE]), str);
        memcpy(&node->antall_kanter, &(melding[OFFSET_ANTALL]), str);
        memcpy(&node->kanter, &(melding[OFFSET_LISTE]), str * node->antall_kanter);
        memcpy(&node->kanter_vekt, &(melding[OFFSET_LISTE + (str * node->antall_kanter)]),
        str * node->antall_kanter);

    }else if(fra == SERVER){
        memcpy(&node->egen_adresse, &(melding[OFFSET_EGEN_ADRESSE]), str);
        memcpy(&node->destinasjoner, &(melding[OFFSET_ANTALL]), str);
        memcpy(&node->destinasjon, &(melding[OFFSET_LISTE]), str * node->destinasjoner);
        memcpy(&node->neste_hopp, &(melding[OFFSET_LISTE + (str * node->destinasjoner)]),
        str * node->destinasjoner);
    }
}
