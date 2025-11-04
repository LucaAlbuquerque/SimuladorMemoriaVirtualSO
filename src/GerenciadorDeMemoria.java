import java.io.*;
import java.util.*;

public class GerenciadorDeMemoria {
    private int pageSize;
    private int numFrames;
    private int virtualMemorySize;
    private int numPages;
    private Map<Integer, String> pageTable;
    private Queue<Integer> frameQueue;
    private RandomAccessFile swapFile;

    public GerenciadorDeMemoria(int virtualMemorySize, int pageSize, int numFrames) throws IOException {
        this.virtualMemorySize = virtualMemorySize;
        this.pageSize = pageSize;
        this.numFrames = numFrames;
        this.numPages = virtualMemorySize / pageSize;
        this.pageTable = new HashMap<>();
        this.frameQueue = new LinkedList<>();

        this.swapFile = new RandomAccessFile("swap.bin", "rw");
        for (int i = 0; i < numPages * pageSize; i++) {
            swapFile.write(0);
        }
    }

    private void loadPage(int pageNumber) throws IOException {
        if (pageTable.size() >= numFrames) {
            int oldestPage = frameQueue.poll();
            swapOut(oldestPage);
            pageTable.remove(oldestPage);
        }

        pageTable.put(pageNumber, "Frame_" + pageTable.size());
        frameQueue.add(pageNumber);
    }

    private void swapOut(int pageNumber) throws IOException {
        int offset = pageNumber * pageSize;
        swapFile.seek(offset);
    }

    public void close() throws IOException {
        swapFile.close();
    }
}
