package com.java.common;

public class GlobalParams {
    private static String projectPath = System.getProperty("user.dir");
    private static String confPath = projectPath+"\\test file\\cf.cfg";
    private static String sectionTestPath = "testPath";
    private static String sectionOpencvUtils = "opencvUtils";
    private static String sectionMachineLearning = "machineLearning";
    private static String sectionAppium = "appium";
    private static String sectionSelenium = "selenium";
    private static String sectionDatabases = "databases";

    // testPath section
    public static String testImagePath = projectPath + ConfigUtils.readConfFile(confPath, sectionTestPath, "testImagePath");
    public static String testVideoPath = projectPath + ConfigUtils.readConfFile(confPath, sectionTestPath, "testVideoPath");
    public static String testFilePath = projectPath + ConfigUtils.readConfFile(confPath, sectionTestPath, "testFilePath");

    // opencvUtils section
    public static String systemFontPath =  "" + ConfigUtils.readConfFile(confPath, sectionOpencvUtils, "systemFontPath");
    public static String tesseractPath = "" + ConfigUtils.readConfFile(confPath, sectionOpencvUtils, "tesseractPath");
    public static String imageInput = projectPath + ConfigUtils.readConfFile(confPath, sectionOpencvUtils, "imageInput");
    public static String imageOutput = projectPath + ConfigUtils.readConfFile(confPath, sectionOpencvUtils, "imageOutput");
    public static String characterOutput = projectPath + ConfigUtils.readConfFile(confPath, sectionOpencvUtils, "characterOutput");
    public static String sentenceOutput = projectPath + ConfigUtils.readConfFile(confPath, sectionOpencvUtils, "sentenceOutput");
    public static String videoInput = projectPath + ConfigUtils.readConfFile(confPath, sectionOpencvUtils, "videoInput");
    public static String videoOutput = projectPath + ConfigUtils.readConfFile(confPath, sectionOpencvUtils, "videoOutput");
    public static String faceDetectFaceXml = projectPath + ConfigUtils.readConfFile(confPath, sectionOpencvUtils, "faceDetectFaceXml");
    public static String faceDetectEyesXml = projectPath + ConfigUtils.readConfFile(confPath, sectionOpencvUtils, "faceDetectEyesXml");

    //appium section
    public static String aaptPath = projectPath + ConfigUtils.readConfFile(confPath, sectionAppium, "aaptPath");
    public static String androidApklist = projectPath + ConfigUtils.readConfFile(confPath, sectionAppium, "androidApkList");
    public static String appiumScreenshotPath = projectPath + ConfigUtils.readConfFile(confPath, sectionAppium, "appiumScreenshotPath");
    public static String appiumScreenrecordPath = projectPath + ConfigUtils.readConfFile(confPath, sectionAppium,
            "appiumScreenrecordPath");
    public static String qrCodeImagePath = projectPath + ConfigUtils.readConfFile(confPath, sectionAppium, "qrCodeImagePath");

    //selenium section
    public static String chromeDriverPath = projectPath + ConfigUtils.readConfFile(confPath, sectionSelenium, "chromeDriverPath");
    public static String edgeDriverPath = projectPath + ConfigUtils.readConfFile(confPath, sectionSelenium, "edgeDriverPath");
    public static String ieDriverPath =  projectPath + ConfigUtils.readConfFile(confPath, sectionSelenium, "ieDriverPath");
    public static String chromiumPath = "" + ConfigUtils.readConfFile(confPath, sectionSelenium, "chromiumPath");

    //databases section
    public static String mariaDbUrl = "" + ConfigUtils.readConfFile(confPath, sectionDatabases, "mariaDbUrl");
    public static String mariaDbUser = ""  + ConfigUtils.readConfFile(confPath, sectionDatabases, "mariaDbUser");
    public static String mariaDbPassword = ""  + ConfigUtils.readConfFile(confPath, sectionDatabases, "mariaDbPassword");
    public static String pgsqlUrl = "" + ConfigUtils.readConfFile(confPath, sectionDatabases, "pgsqlUrl");
    public static String pgsqlUser = ""  + ConfigUtils.readConfFile(confPath, sectionDatabases, "pgsqlUser");
    public static String pgsqlPassword = ""  + ConfigUtils.readConfFile(confPath, sectionDatabases, "pgsqlPassword");

}
