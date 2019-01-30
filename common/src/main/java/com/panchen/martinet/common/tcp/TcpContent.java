package com.panchen.martinet.common.tcp;

import java.io.IOException;
import java.io.OutputStream;
import com.panchen.martinet.common.io.Bytes;

public abstract class TcpContent {

    public abstract void writeBody();

    public static class TcpHeader {

        // "MT"
        public static final int MARKER_BYTES_SIZE = 2;

        // is full package?
        public static final int FULLSTATUS_SIZE = 1;

        public static final int CLIENTID_SIZE = 8;

        public static final int MESSAGELENGTH_SIZE = 4;

        public static final int HEADER_SIZE = MARKER_BYTES_SIZE + FULLSTATUS_SIZE + CLIENTID_SIZE + MESSAGELENGTH_SIZE;

        private static final byte[] markerBytes = "MT".getBytes();

        public static void writeHeader(OutputStream output, long clientId, byte status, int messageLength) throws IOException {
            output.write(markerBytes);
            output.write(status);
            output.write(messageLength);
            output.write(Bytes.long2Bytes(clientId));
        }

    }
}
