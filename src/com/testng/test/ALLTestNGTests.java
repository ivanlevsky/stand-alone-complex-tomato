package com.testng.test;

import com.java.common.GlobalParams;
import org.testng.TestNG;
import org.testng.xml.*;
import java.util.ArrayList;
import java.util.List;

public class ALLTestNGTests {
    public static void main(String[] args) {

        TestNG testNG = new TestNG();
        /*
        *below codes use testNG class methods, or use xmlSuite and other xml classes to set testNG
            testNG.setDefaultTestName("test name");
            testNG.setDefaultSuiteName("TestNG Suite Demo");

            testNG.setTestClasses(new Class[]{
                    WindowsOsUtilsTest.class,
                    CryptoUtilsTest.class,
                    StringUtilsTest.class});
            testNG.setExcludedGroups("not run group test");
         */

        XmlSuite suite = new XmlSuite();
        suite.setName("TestNG Suite Demo");
        XmlTest test = new XmlTest(suite);
        test.setName("test name");
        /*
        *use xmlClass to select test classes or use package and exclude groups
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass("com.testng.test.StringUtilsTest"));
        classes.add(new XmlClass("com.testng.test.CryptoUtilsTest"));
        test.setXmlClasses(classes);
         */
        List<XmlPackage> xmlPackages = new ArrayList<>();
        List<String> groups = new ArrayList<>();
        xmlPackages.add(new XmlPackage("com.testng.test"));
        test.setXmlPackages(xmlPackages);
        groups.add("not run group test");
//        test.setExcludedGroups(groups);
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNG.setXmlSuites(suites);

        testNG.setOutputDirectory(GlobalParams.testNGReports);
        testNG.run();

    }

}
