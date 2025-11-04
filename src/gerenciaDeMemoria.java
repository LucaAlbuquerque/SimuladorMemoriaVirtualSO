import java.io.*;
import java.util.*;

public class gerenciadorDeMemoria {
    static final String SWAP_FILE = "swap.bin";

    final int numVirtualPages;
    final int numPhysicalFrames;
    final int pageSize;

    static class PTE {
        boolean valid;
        boolean dirty;
        int frameNumber;
        long swapOffset;
        boolean everAllocated;
    }

    PTE[] pageTable;
    byte[][] physicalMemory;
    int[] frameToPage;
    Deque<Integer> fifoQueue;

    public VirtualMemoryManager(int numVirtualPages, int numPhysicalFrames, int pageSize) throws IOException {
        this.numVirtualPages = numVirtualPages;
        this.numPhysicalFrames = numPhysicalFrames;
        this.pageSize = pageSize;

        pageTable = new PTE[numVirtualPages];
        for (int i = 0; i < numVirtualPages; i++) {
            pageTable[i] = new PTE();
            pageTable[i].swapOffset = (long) i * pageSize;
        }

        physicalMemory = new byte[numPhysicalFrames][pageSize];
        frameToPage = new int[numPhysicalFrames];
        Arrays.fill(frameToPage, -1);
        fifoQueue = new ArrayDeque<>();
    }
}
