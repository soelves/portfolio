CC = gcc
CCFLAGS = -Wall -Wextra -Wconversion -Wpedantic -g

BINARIES = routing_server node

all: $(BINARIES)

node: node.c util.c print_lib.c protocol.c
		$(CC) $(CCFLAGS) $^ -o $@

routing_server: routing_server.c util.c print_lib.c protocol.c
		$(CC) $(CCFLAGS) $^ -o $@

run: all
		bash run_${NR}.sh ${BASEPORT}
		
clean:
		rm -f $(BINARIES)
