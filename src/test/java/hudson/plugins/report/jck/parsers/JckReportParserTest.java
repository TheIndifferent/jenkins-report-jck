package hudson.plugins.report.jck.parsers;

import hudson.plugins.report.jck.model.Test;
import hudson.plugins.report.jck.model.TestOutput;
import hudson.plugins.report.jck.model.TestStatus;
import hudson.plugins.report.jck.model.ReportFull;
import hudson.plugins.report.jck.model.Suite;
import org.junit.Assert;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JckReportParserTest {

    private final static String reportCompilerFileName = "report-compiler.xml.gz";
    private final static String reportDevtoolsFileName = "report-devtools.xml.gz";
    private final static String reportRuntimeFileName = "report-runtime.xml.gz";

    @org.junit.Test
    public void parseCompilerReportTest() {
        Suite actualSuite = null;
        try {
            final Path reportCompilerXmlGz = new File(this.getClass().getResource("/" + reportCompilerFileName)
                    .toURI()).toPath();
            final JckReportParser parser = new JckReportParser();
            actualSuite = parser.parsePath(reportCompilerXmlGz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final List<String> testsList = new ArrayList<>();
        testsList.add("api/java_rmi/Naming/index.html#Bind");
        testsList.add("api/javax_annotation/processing/Messager/index.html#PM1_NEG");
        testsList.add("api/javax_lang/model/element/ModuleElement/ModEleDirVisit.html");

        final List<TestOutput> outputs = new ArrayList<>();
        outputs.add(new TestOutput(
           "script_messages / messages",
           "Testing Java compiler...\nRunning with annotation processor..."
        ));
        outputs.add(new TestOutput(
                "testAnnoProcSrc.java / messages",
                "command: com.sun.javatest.lib.ProcessCommand "
                        + "/jck/10/jdk/bin/javac -classpath "
                        + "/jck/10/JCK-compiler-10-work/classes:/jck/10/JCK-compiler-10/classes:/jck/10/JCK-compiler-10/lib"
                        + "/javatest.jar -d /jck/10/JCK-compiler-10-work/classes -processor javasoft.sqe.jck.lib.javax"
                        + ".annotation.processing.ProcessorMultiTestWrapper "
                        + "-AjckTestWorkDir=file:/jck/10/JCK-compiler-10-work/api/javax_lang/model/element/ModuleElement/ "
                        + "-AjckApArgs=-testName+ModEleDirVisit+-testArgs+javasoft.sqe.tests.api.javax.lang.model.element"
                        + ".ModuleElement.DirVisitorVisitDefaultTests -s "
                        + "/jck/10/JCK-compiler-10-work/api/javax_lang/model/element/ModuleElement "
                        + "/jck/10/JCK-compiler-10/tests/api/javax_lang/model/element/ModuleElement"
                        + "/S_DirVisitorVisitDefaultTests.java"
        ));
        outputs.add(new TestOutput(
                "testAnnoProcSrc.java / out1",
                ""
        ));
        outputs.add(new TestOutput(
                "testAnnoProcSrc.java / out2",
                "testModuleFinder = null\ntestModuleName = null\nrootModules"
                        + " = []\nlocations = []\nchosen loader = java.net.URLClassLoader@4f6ee6e4\nchosen "
                        + "loader's parent = java.net.URLClassLoader@4466af20\nexecuteArgs = []\njava version "
                        + "\"10-internal\"\nOpenJDK Runtime Environment (build 10-internal+0-adhoc.tester.openjdk)\n"
                        + "OpenJDK 64-Bit Server VM (build 10-internal+0-adhoc.tester.openjdk, mixed mode)\n\ninvoking visit(d)"
                        + " ---\ntestVisitDefaultImpl: Failed. Implementation is not as per spec\nTest status: STATUS:Failed. "
                        + "test cases: 1; all failed; first test case failure: testVisitDefaultImpl"
        ));

        final List<Test> testProblems = new ArrayList<>();
        testProblems.add(new Test(
                "api/javax_lang/model/element/ModuleElement/ModEleDirVisit.html",
                TestStatus.FAILED,
                "Failed. compilation failed unexpectedly [test cases: 1; all failed; first test case failure: "
                        + "testVisitDefaultImpl]",
                outputs
        ));

        final ReportFull report = new ReportFull(
                2,
                0,
                1,
                0,
                3,
                testProblems,
                testsList
        );

        final Suite expectedSuite = new Suite(
                "report-compiler",
                report
        );

        Assert.assertEquals("Expected suite doesn\'t match the actual suite", expectedSuite, actualSuite);
    }

    @org.junit.Test
    public void parseDevtoolsReportTest() {
        Suite actualSuite = null;
        try {
            final Path reportCompilerXmlGz = new File(this.getClass().getResource("/" + reportDevtoolsFileName)
                    .toURI()).toPath();
            final JckReportParser parser = new JckReportParser();
            actualSuite = parser.parsePath(reportCompilerXmlGz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final List<String> testsList = new ArrayList<>();
        testsList.add("java2schema/CustomizedMapping/classes/XmlTransient/XmlTransient002.html#testCase0002");
        testsList.add("java2schema/CustomizedMapping/classes/XmlType/constraints/Constraint001.html");
        testsList.add("jaxws/mapping/w2jmapping/document/literal/annotations/HandlerChainAnnotationsTest."
                + "html#HandlerChainAnnotationsTest");
        testsList.add("jaxws/mapping/w2jmapping/document/literal/annotations/HelloOperationAnnotationsTest"
                + ".html#HelloOperationAnnotationsTest");

        final List<TestOutput> outputs0 = new ArrayList<>();
        outputs0.add(new TestOutput(
                "compile.java / messages",
                "command: com.sun.javatest.lib.ProcessCommand "
                        + "CLASSPATH=/jck/10/JCK-devtools-10/classes:/jck/10/JCK-devtools-10/lib/javatest"
                        + ".jar::/jck/10/JCK-devtools-10-work/mapping/w2jmapping/document/literal/annotations "
                        + "/jck/10/jdk/bin/javac -d /jck/10/JCK-devtools-10-work/mapping/w2jmapping/document/literal"
                        + "/annotations /jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations"
                        + "/Client.java /jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations"
                        + "/Handler.java"
        ));
        outputs0.add(new TestOutput(
                "compile.java / out1",
                "/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal"
                        + "/annotations/Handler.java:10: error: package javax.xml.soap is not visible\nimport javax.xml"
                        + ".soap.*;\n                ^\n  (package javax.xml.soap is declared in module java.xml.ws, "
                        + "which is not in the module graph)\n"
                        + "/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations/Handler.java:11:"
                        + " error: package javax.xml.ws.soap is not visible\nimport javax.xml.ws.soap.*;\n           "
                        + "        ^\n  (package javax.xml.ws.soap is declared in module java.xml.ws, which is not in the "
                        + "module graph)\n/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal"
                        + "/annotations/Handler.java:12: error: package javax.xml.ws.handler is not visible\nimport javax"
                        + ".xml.ws.handler.*;\n                   ^\n  (package javax.xml.ws.handler is declared in "
                        + "module java.xml.ws, which is not in the module graph)\n"
                        + "/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations/Handler.java:13:"
                        + " error: package javax.xml.ws is not visible\nimport javax.xml.ws.LogicalMessage;\n        "
                        + "        ^\n  (package javax.xml.ws is declared in module java.xml.ws, which is not in the "
                        + "module graph)\n/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal"
                        + "/annotations/Handler.java:15: error: package javax.annotation is not visible\nimport javax"
                        + ".annotation.PreDestroy;\n            ^\n  (package javax.annotation is declared in module "
                        + "java.xml.ws.annotation, which is not in the module graph)\n"
                        + "/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations/Handler.java:16:"
                        + " error: package javax.annotation is not visible\nimport javax.annotation.PostConstruct;\n "
                        + "           ^\n  (package javax.annotation is declared in module java.xml.ws.annotation, which "
                        + "is not in the module graph)\n"
                        + "/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations/Handler.java:19:"
                        + " error: package javax.xml.ws.handler is not visible\npublic class Handler implements javax.xml"
                        + ".ws.handler.LogicalHandler<LogicalMessageContext>\n                                            "
                        + "^\n  (package javax.xml.ws.handler is declared in module java.xml.ws, which is not in the "
                        + "module graph)\n7 errors"
        ));
        outputs0.add(new TestOutput(
                "compile.java / out2",
                ""
        ));
        outputs0.add(new TestOutput(
                "script_messages / messages",
                ""
        ));
        outputs0.add(new TestOutput(
                "wsimport / messages",
                "command: com.sun.javatest.lib.ProcessCommand JAVA_HOME=/jck/10/jdk /bin/sh "
                        + "/jck/10/JCK-devtools-10/linux/bin/wsimport.sh -d "
                        + "/jck/10/JCK-devtools-10-work/mapping/w2jmapping/document/literal/annotations -b "
                        + "file:/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations/customfile"
                        + ".xml file:/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations"
                        + "/W2JDLAnnotations.wsdl"
        ));
        outputs0.add(new TestOutput(
                "wsimport / out1",
                ""
        ));
        outputs0.add(new TestOutput(
                "wsimport / out2",
                "eval \"/jck/10/jdk/bin/wsimport\" -keep -d "
                        + "/jck/10/JCK-devtools-10-work/mapping/w2jmapping/document/literal/annotations -b "
                        + "\"file:/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations"
                        + "/customfile.xml\"  \"file:/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal"
                        + "/annotations/W2JDLAnnotations.wsdl\"\nparsing WSDL...\n\n\n\nGenerating "
                        + "code...\n\n\nCompiling code..."));

        final List<TestOutput> outputs1 = new ArrayList<>();
        outputs1.add(new TestOutput(
                "compile.java / messages",
                "command: com.sun.javatest.lib.ProcessCommand "
                        + "CLASSPATH=/jck/10/JCK-devtools-10/classes:/jck/10/JCK-devtools-10/lib/javatest"
                        + ".jar::/jck/10/JCK-devtools-10-work/mapping/w2jmapping/document/literal/annotations "
                        + "/jck/10/jdk/bin/javac -d /jck/10/JCK-devtools-10-work/mapping/w2jmapping/document/literal"
                        + "/annotations /jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations"
                        + "/Client.java /jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations"
                        + "/Handler.java"
        ));
        outputs1.add(new TestOutput(
                "compile.java / out1",
                "/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal"
                        + "/annotations/Handler.java:10: error: package javax.xml.soap is not visible\nimport javax.xml.soap.*;"
                        + "\n                ^\n  (package javax.xml.soap is declared in module java.xml.ws, which is not in "
                        + "the module graph)\n/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal"
                        + "/annotations/Handler.java:11: error: package javax.xml.ws.soap is not visible\nimport javax.xml"
                        + ".ws.soap.*;\n                   ^\n  (package javax.xml.ws.soap is declared in module java"
                        + ".xml.ws, which is not in the module graph)\n"
                        + "/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations/Handler.java:12:"
                        + " error: package javax.xml.ws.handler is not visible\nimport javax.xml.ws.handler.*;\n     "
                        + "              ^\n  (package javax.xml.ws.handler is declared in module java.xml.ws, which is "
                        + "not in the module graph)\n"
                        + "/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations/Handler.java:13:"
                        + " error: package javax.xml.ws is not visible\nimport javax.xml.ws.LogicalMessage;\n        "
                        + "        ^\n  (package javax.xml.ws is declared in module java.xml.ws, which is not in the "
                        + "module graph)\n/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal"
                        + "/annotations/Handler.java:15: error: package javax.annotation is not visible\nimport javax"
                        + ".annotation.PreDestroy;\n            ^\n  (package javax.annotation is declared in module "
                        + "java.xml.ws.annotation, which is not in the module graph)\n"
                        + "/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations/Handler.java:16:"
                        + " error: package javax.annotation is not visible\nimport javax.annotation.PostConstruct;\n "
                        + "           ^\n  (package javax.annotation is declared in module java.xml.ws.annotation, which "
                        + "is not in the module graph)\n"
                        + "/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations/Handler.java:19:"
                        + " error: package javax.xml.ws.handler is not visible\npublic class Handler implements javax.xml"
                        + ".ws.handler.LogicalHandler<LogicalMessageContext>\n                                            "
                        + "^\n  (package javax.xml.ws.handler is declared in module java.xml.ws, which is not in the "
                        + "module graph)\n7 errors"));
        outputs1.add(new TestOutput(
                "compile.java / out2",
                ""
        ));
        outputs1.add(new TestOutput(
                "script_messages / messages",
                ""
        ));
        outputs1.add(new TestOutput(
                "wsimport / messages",
                "command: com.sun.javatest.lib.ProcessCommand JAVA_HOME=/jck/10/jdk /bin/sh "
                        + "/jck/10/JCK-devtools-10/linux/bin/wsimport.sh -d "
                        + "/jck/10/JCK-devtools-10-work/mapping/w2jmapping/document/literal/annotations -b "
                        + "file:/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations/customfile"
                        + ".xml file:/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations"
                        + "/W2JDLAnnotations.wsdl"
        ));
        outputs1.add(new TestOutput(
                "wsimport / out1",
                ""
        ));
        outputs1.add(new TestOutput(
                "wsimport / out2",
                "eval \"/jck/10/jdk/bin/wsimport\" -keep -d "
                        + "/jck/10/JCK-devtools-10-work/mapping/w2jmapping/document/literal/annotations -b "
                        + "\"file:/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal/annotations"
                        + "/customfile.xml\"  \"file:/jck/10/JCK-devtools-10/tests/jaxws/mapping/w2jmapping/document/literal"
                        + "/annotations/W2JDLAnnotations.wsdl\"\nparsing WSDL...\n\n\n\nGenerating code...\n\n\n"
                        + "Compiling code..."
        ));

        final List<Test> testProblems = new ArrayList<>();
        testProblems.add(new Test(
                "jaxws/mapping/w2jmapping/document/literal/annotations/HandlerChainAnnotationsTest."
                        + "html#HandlerChainAnnotationsTest",
                TestStatus.FAILED,
                "Failed. exit code 1",
                outputs0
        ));
        testProblems.add(new Test(
                "jaxws/mapping/w2jmapping/document/literal/annotations/HelloOperationAnnotationsTest."
                        + "html#HelloOperationAnnotationsTest",
                TestStatus.FAILED,
                "Failed. exit code 1",
                outputs1
        ));

        final ReportFull report = new ReportFull(
                2,
                0,
                2,
                0,
                4,
                testProblems,
                testsList
        );

        final Suite expectedSuite = new Suite(
                "report-devtools",
                report
        );

        Assert.assertEquals("Expected suite doesn\'t match the actual suite", expectedSuite, actualSuite);
    }

    @org.junit.Test
    public void parseRuntimeReportTest() {
        Suite actualSuite = null;
        try {
            final Path reportCompilerXmlGz = new File(this.getClass().getResource("/" + reportRuntimeFileName)
                    .toURI()).toPath();
            final JckReportParser parser = new JckReportParser();
            actualSuite = parser.parsePath(reportCompilerXmlGz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final List<String> testsList = new ArrayList<>();
        testsList.add("api/java_applet/Applet/AccessibleApplet/serial/index.html#Constructor");
        testsList.add("api/java_awt/Image_pack/ComponentColorModel/index.html#ConstructorTesttestCase1");
        testsList.add("api/java_awt/awt_focus_subsystem/focus_vetoablechangelistener/index.html#VetoableChangeListener");

        final List<TestOutput> outputs0 = new ArrayList<>();
        outputs0.add(new TestOutput(
                "script_messages / messages",
                "Executing test class..."
        ));
        outputs0.add(new TestOutput(
                "testExecute / messages",
                "command: com.sun.jck.lib.ExecJCKTestOtherJVMCmd "
                        + "LD_LIBRARY_PATH=/jck/10/lib DISPLAY=:0 HOME=/home/tester /jck/10/jdk/bin/java "
                        + "--add-modules=ALL-SYSTEM -Djdk.xml.maxXMLNameLimit=4096 -Dmultitest.testcaseOrder=sorted -Djava.rmi"
                        + ".server.useCodebaseOnly=false -Djdk.attach.allowAttachSelf=true -Xmx999m -verify -classpath "
                        + "/jck/10/JCK-runtime-10/classes::/jck/10/JCK-runtime-10/lib/extensions/JCK-extensions"
                        + ".jar:/jck/10/JCK-runtime-10/lib/extensions -Djava.security.policy=/jck/10/JCK-runtime-10/lib/jck"
                        + ".policy -Djava.rmi.activation.port=2345 javasoft.sqe.tests.api.java.awt.Image_pack"
                        + ".ComponentColorModel.ConstructorTest -TestCaseID testCase1 testCase2"
        ));
        outputs0.add(new TestOutput(
                "testExecute / out1",
                "WARNING: Using incubator modules: jdk.incubator.httpclient\njava.lang"
                        + ".ArrayIndexOutOfBoundsException: 3\n        at javasoft.sqe.tests.api.java.awt.Image_pack"
                        + ".ComponentColorModel.ConstructorTest.construct(ConstructorTest.java:373)\n        at javasoft"
                        + ".sqe.tests.api.java.awt.Image_pack.ComponentColorModel.ConstructorTest.testCase1(ConstructorTest"
                        + ".java:136)\n        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native "
                        + "Method)\n        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke"
                        + "(NativeMethodAccessorImpl.java:62)\n        at java.base/jdk.internal.reflect"
                        + ".DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n        at java"
                        + ".base/java.lang.reflect.Method.invoke(Method.java:564)\n        at javasoft.sqe.javatest.lib"
                        + ".MultiTest.invokeTestCase(MultiTest.java:405)\n        at javasoft.sqe.javatest.lib.MultiTest"
                        + ".run(MultiTest.java:194)\n        at javasoft.sqe.javatest.lib.MultiTest.run(MultiTest"
                        + ".java:126)\n        at javasoft.sqe.tests.api.java.awt.Image_pack.ComponentColorModel"
                        + ".ConstructorTest.main(ConstructorTest.java:52)\ntestCase1: Failed. Test case throws exception: "
                        + "java.lang.ArrayIndexOutOfBoundsException: 3\ntestCase2: Passed. (ran 22 test cases)\n"
                        + "STATUS:Failed.test cases: 2; passed: 1; failed: 1; first test case failure: testCase1"
        ));
        outputs0.add(new TestOutput(
                "testExecute / out2",
                ""
        ));

        final List<TestOutput> outputs1 = new ArrayList<>();
        outputs1.add(new TestOutput(
                "script_messages / messages",
                "Executing test class..."
        ));
        outputs1.add(new TestOutput(
                "testExecute / messages",
                "command: com.sun.jck.lib.ExecJCKTestOtherJVMCmd DISPLAY=:0 "
                        + "HOME=/home/tester /jck/10/jdk/bin/java --add-modules=ALL-SYSTEM -Djdk.xml.maxXMLNameLimit=4096 "
                        + "-Dmultitest.testcaseOrder=sorted -Djava.rmi.server.useCodebaseOnly=false -Djdk.attach"
                        + ".allowAttachSelf=true -Xmx999m -verify -classpath "
                        + "/jck/10/JCK-runtime-10/classes::/jck/10/JCK-runtime-10/lib/extensions/JCK-extensions"
                        + ".jar:/jck/10/JCK-runtime-10/lib/extensions -Djava.security.policy=/jck/10/JCK-runtime-10/lib/jck"
                        + ".policy javasoft.sqe.tests.api.java.awt.awt_focus_subsystem.focus_vetoablechangelistener"
                        + ".VChangeListenerTests -timeoutFactor 2.0 -platform.crossWindowFocusTransferSupported true -platform"
                        + ".crossWindowFocusTransferSupported true"
        ));
        outputs1.add(new TestOutput(
                "testExecute / out1",
                "WARNING: Using incubator modules: jdk.incubator.httpclient"
        ));
        outputs1.add(new TestOutput(
                "testExecute / out2",
                ""
        ));

        final List<Test> testProblems = new ArrayList<>();
        testProblems.add(new Test(
                "api/java_awt/Image_pack/ComponentColorModel/index.html#ConstructorTesttestCase1",
                TestStatus.FAILED,
                "Failed. test cases: 2; passed: 1; failed: 1; first test case failure: testCase1",
                outputs0
        ));
        testProblems.add(new Test(
                "api/java_awt/awt_focus_subsystem/focus_vetoablechangelistener/index.html#VetoableChangeListener",
                TestStatus.FAILED,
                "Failed. unexpected exit code: exit code 143",
                outputs1
        ));

        final ReportFull report = new ReportFull(
                1,
                0,
                2,
                0,
                3,
                testProblems,
                testsList
        );

        final Suite expectedSuite = new Suite(
                "report-runtime",
                report
        );

        Assert.assertEquals("Expected suite doesn\'t match the actual suite", expectedSuite, actualSuite);
    }
}
