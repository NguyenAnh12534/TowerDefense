package vn.ha.tower_defense.adapters;

import java.io.File;

public class FileAdapter implements AutoCloseable {

    File file;

    public FileAdapter(File file) {
        this.file = file;
    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub
    }
    
}
