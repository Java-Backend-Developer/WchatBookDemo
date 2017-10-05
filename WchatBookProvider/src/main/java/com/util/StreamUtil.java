package com.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.MalformedInputException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
public final class StreamUtil {
    private static final Logger LOG = Logger.getLogger(StreamUtil.class.getName());

    private StreamUtil() {
    }

    private static CharBuffer decodeHelper(byte[] byteArray, int numberOfBytes, Charset charset) throws IOException {
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = null;

        try {
            charBuffer = decoder.decode(ByteBuffer.wrap(byteArray, 0, numberOfBytes));
        } catch (MalformedInputException var6) {
            charBuffer = null;
        }

        return charBuffer;
    }

    public static String inputStream2String(InputStream is, Charset charset) throws IOException {
        String var18;
        try {
            StringBuilder out = new StringBuilder();
            byte[] b = new byte[4096];
            byte[] savedBytes = new byte[1];
            boolean hasSavedBytes = false;
            CharsetDecoder decoder = charset.newDecoder();

            int ex;
            while((ex = is.read(b)) != -1) {
                if(hasSavedBytes) {
                    byte[] charBuffer = new byte[savedBytes.length + b.length];
                    System.arraycopy(savedBytes, 0, charBuffer, 0, savedBytes.length);
                    System.arraycopy(b, 0, charBuffer, savedBytes.length, b.length);
                    b = charBuffer;
                    hasSavedBytes = false;
                    ex += savedBytes.length;
                }

                CharBuffer var19 = decodeHelper(b, ex, charset);
                if(var19 == null) {
                    int nrOfChars = 0;

                    while(var19 == null) {
                        ++nrOfChars;
                        var19 = decodeHelper(b, ex - nrOfChars, charset);
                        if(nrOfChars > 10 && nrOfChars < ex) {
                            try {
                                var19 = decoder.decode(ByteBuffer.wrap(b, 0, ex));
                            } catch (MalformedInputException var16) {
                                throw new IOException("File not in supported encoding (" + charset.displayName() + ")", var16);
                            }
                        }
                    }

                    savedBytes = new byte[nrOfChars];
                    hasSavedBytes = true;

                    for(int i = 0; i < nrOfChars; ++i) {
                        savedBytes[i] = b[ex - nrOfChars + i];
                    }
                }

                var19.rewind();
                out.append(var19.toString());
            }

            if(hasSavedBytes) {
                try {
                    decoder.decode(ByteBuffer.wrap(savedBytes, 0, savedBytes.length));
                } catch (MalformedInputException var15) {
                    throw new IOException("File not in supported encoding (" + charset.displayName() + ")", var15);
                }
            }

            var18 = out.toString();
        } finally {
            if(is != null) {
                is.close();
            }

        }

        return var18;
    }

    public static byte[] inputStream2Bytes(InputStream is) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            boolean len = true;

            int len1;
            while((len1 = is.read(buf)) != -1) {
                baos.write(buf, 0, len1);
            }

            byte[] var4 = baos.toByteArray();
            return var4;
        } finally {
            is.close();
        }
    }

    public static void copy(InputStream is, OutputStream os) throws IOException {
        ReadableByteChannel inChannel = Channels.newChannel(is);
        WritableByteChannel outChannel = Channels.newChannel(os);

        try {
            ByteBuffer ex = ByteBuffer.allocate(65536);

            while(true) {
                int bytesRead = inChannel.read(ex);
                if(bytesRead == -1) {
                    return;
                }

                ex.flip();

                while(ex.hasRemaining()) {
                    outChannel.write(ex);
                }

                ex.clear();
            }
        } finally {
            try {
                inChannel.close();
            } catch (IOException var14) {
                LOG.log(Level.SEVERE, (String)null, var14);
            }

            try {
                outChannel.close();
            } catch (IOException var13) {
                LOG.log(Level.SEVERE, (String)null, var13);
            }

        }
    }
}

