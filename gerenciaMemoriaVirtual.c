#include <stdio.h>
#include <stdlib.h>

#define TAM_MEMORIA 3
#define TAM_DISCO 10
#define TOTAL_PAGINAS 10
#define TAM_REFERENCIAS 12

typedef struct {
    int paginas[TAM_MEMORIA];
    int contador;
} Memoria;

typedef struct {
    int pagina_virtual;
    int quadro_fisico;
    int presente;
} EntradaTabela;


int disco[TAM_DISCO];
EntradaTabela tabela_paginas[TOTAL_PAGINAS];

void inicializar_memoria(Memoria *memoria) {
    for (int i = 0; i < TAM_MEMORIA; i++)
        memoria->paginas[i] = -1;
    memoria->contador = 0;
    for (int i = 0; i < TAM_DISCO; i++)
        disco[i] = i;
}

void inicializar_tabela_paginas() {
    for (int i = 0; i < TOTAL_PAGINAS; i++) {
        tabela_paginas[i].pagina_virtual = i;
        tabela_paginas[i].quadro_fisico = -1;
        tabela_paginas[i].presente = 0;
    }
}

void exibir_memoria(Memoria *memoria) {
    printf("Memória: ");
    for (int i = 0; i < TAM_MEMORIA; i++)
        if (memoria->paginas[i] == -1)
            printf("[ ] ");
        else
            printf("[%d] ", memoria->paginas[i]);
    printf("\n");
}

void exibir_tabela_paginas() {
    printf("\n%-15s %-15s %-10s\n", "Página Virtual", "Quadro Físico", "Presente");
    printf("---------------------------------------------\n");
    for (int i = 0; i < TOTAL_PAGINAS; i++) {
        printf("%-15d %-15d %-10d\n",
               tabela_paginas[i].pagina_virtual,
               tabela_paginas[i].quadro_fisico,
               tabela_paginas[i].presente);
    }
}

int pagina_na_memoria(Memoria *memoria, int pagina) {
    for (int i = 0; i < TAM_MEMORIA; i++)
        if (memoria->paginas[i] == pagina)
            return 1;
    return 0;
}
