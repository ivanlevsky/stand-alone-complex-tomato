package com.junit.test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

/***
 * //select classes: @SelectClasses({StringUtilsTest.class, CryptoUtilsTest.class})
 *  annotation equals below
 * //select package: @SelectPackages("com.junit.test")
 * //exclude class endwith "WindowsOsUtilsTest": @ExcludeClassNamePatterns({"^.*WindowsOsUtilsTest?$"})
 */
@RunWith(JUnitPlatform.class)  
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectPackages("com.junit.test")
public class AllJunitTests {

}
