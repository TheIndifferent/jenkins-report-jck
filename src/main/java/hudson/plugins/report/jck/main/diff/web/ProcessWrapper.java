/*
 * The MIT License
 *
 * Copyright 2016 jvanek.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hudson.plugins.report.jck.main.diff.web;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jvanek
 */
public class ProcessWrapper implements Runnable {

    private final List<String> cmds;
    private final Writer out;
    private String errorResult;

    public ProcessWrapper(final Writer output,final String[] cmds) {
        this.cmds = Arrays.asList(cmds);
        this.out = output;
    }

    @Override
    public void run() {
        try {
            runImpl();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void runImpl() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(cmds);
        Process p = pb.start();
        ContentCopier sout = new ContentCopier(p.getInputStream(), out);
        ContentReader serr = new ContentReader(p.getErrorStream());
        new Thread(sout).start();
        new Thread(serr).start();
        Thread.sleep(100);
        p.waitFor();
        Thread.sleep(100);
        errorResult = serr.getContent();
    }

    public String getErrorResult() {
        return errorResult;
    }

}
