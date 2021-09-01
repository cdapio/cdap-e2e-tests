package pages.actions;

import java.io.IOException;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pages.locators.CdfGCSLocators;
import utils.SeleniumDriver;
import utils.SeleniumHelper;

public class CdfGcsActions {

    public static CdfGCSLocators cdfGCSLocators = null;
    static {
        cdfGCSLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CdfGCSLocators.class);
    }

    public static void enterReferenceName()  {
        CdfGCSLocators.referenceName.sendKeys(UUID.randomUUID().toString());
    }


    public static void enterGcsBucket(String bucket) throws IOException {
        CdfGCSLocators.gcsPath.sendKeys(SeleniumHelper.readParameters(bucket));
    }

    public static void enterFormat()  {
        CdfGCSLocators.format.click();
        SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(text(),'csv')]")).click();

    }

    public static void enterSamplesize()  {
        CdfGCSLocators.samplesize.sendKeys("1000");
    }

    public static void closeButton()  {
        CdfGCSLocators.closeButton.click();
    }

    public static void gcsProperties()  { CdfGCSLocators.gcsProperties.click(); }

    public static void skipHeader()  { CdfGCSLocators.skipHeader.click(); }

    public static void getSchema()  { CdfGCSLocators.getSchemaButton.click(); }

    public static void clickJsonServiceAccountRadio()  { CdfGCSLocators.serviceAccountJsonRadio.click(); }

    public static void textJsonServiceAccount()  { CdfGCSLocators.serviceAccountJsonText.sendKeys("{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"cdf-athena\",\n" +
            "  \"private_key_id\": \"090a9c1e6db51ad8eae047b4081ecdad3fb10dcc\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDlIZnUm51S+qnu\\nSFF9JKNOmnY7YwiK+cY+PFAAgD6y888LtZoIQXR8PveieehdWR5YYodnrROsY1Lb\\nJlsVzeGJiV3jkJqFnjpSxQcHoAVhsSRpfrgcDJwmav7EIugGgfDiRGqz29TgdVi/\\nrTDlurAqPwcyhranmPMiczuT2JBxnDvmYZgwdCkCpkBH0bGty8lV5saZy+F3XZPw\\nO4a+Jv/mDNlqTocSilKSkyUKCn2u8zgQFVD8fJDyogWVT8/OZ/4ObPEV0bqZiQoT\\nctst2RtWV8n1VcMWb8bJQPrkOtqRouVHgEewF0fr8kv/olRSTGcHbmBlZCL+/YL8\\nO2uOCsVRAgMBAAECggEACgzgYRyeyNkdG0oxhdhVr/DTQw1X3nWwggIV/cGiTmnm\\nkORLMHXDiOAfr1Ei1bZfIAAZYZ0sPd1g8OMkr2cHj6d2dnD9MCN1ujPxeB6HIeb5\\n2oVACN3uOvqqOFryG7f3I4ud9QvtUVegWA7+FDONPtsjIw6Y5C7PqlkM2dIv2sXK\\n9SOM1dAnlDDhNxtE+GksiiDWG2XAcryKVVq1WrQvXLV/a6UGg5VKcSHYXdJbYFbb\\nDktrUgwwy21p8r9GPo/N7JzM8sd0KCW1Codn8pZPHgdcMTVAiOKXrwj5RQy+J4xQ\\nDCDN8d3oe5g911otuoemQK0cxZUYmruQGY69MGTdqQKBgQDyied4G+dSfBvA7YOi\\nDYcJK9xVKKfSVT3KlGHe7ybWKXMexAxA73stH7xjdbmUr71laDjyC99GzG87eOpu\\ntq27WIi3WpGsDdBCzwE1tRTBMUXzNO/pIQ6ToaVRgYCzVgMp98eWbLc0zLK7DO7T\\nBf+y+uNIOLUAIaVhb7SkGc1cSQKBgQDx2TKxvfedELxmIQ4USQ9ELwn3PM+xcslu\\nJ0r4fTvB3Qdu9lMAkGn4F89l6mmVQatRchW7/ooXDfIaH6vVb3yuI//ODoaEKAOz\\nk4eRMKdvf8NfbxWaLs8TmHirVOZ2VRuWcqfGz3X+4lCMTDX/OcPHOPXgSUhWvkst\\nZMcZdkLQyQKBgQDxIeQsiOl6G4PoUvutMO/RUXcUf4AiYLc3YkDPyUr+X2Vh8B4S\\nrUX0ffnplJC2X+bYHD+Jff41QGlSYowJGLwvZ/089eGggndNBMu6HteMn7Vg8H+Z\\nc1Evu+VF3JAdPGWW4w7e4iVfbChCScBjszIeOsue8SobAFiRP+MDx9dhaQKBgH2l\\nWbD+AKaZC0gD5EylqEjEJ2lLLpwCBqQzxb9TULhnHHWJg5XHcUZzGEzafAAPHfIy\\nUxhYJWXU2PK5gYxhX2UZTsy+vyPppgKlosjLfjFvLxFlpNN1fh+wFwDsGs9fZ/XB\\nm2iuX58L9Mzuyx7N4+icryDW8b4fHu92a/nOdugBAoGAM28qdxDl0d4qByOUeoCk\\nZhpiIItjo+IPpAjoAfm3yp0llNCM/WPHwW4fw7SQx00ntKR1uXGAuiVgnDGpsX8U\\nyeuRVALDdbJK0RgePhjmHG0AD5zJKqv/OEvCIsuwwmi/YfzLnwQAt8QAmoAkRoYa\\nmmS5JPtdOZNDU7cEVff/WeQ=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"bq-bqmtb@cdf-athena.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"111999633220878599592\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/bq-bqmtb%40cdf-athena.iam.gserviceaccount.com\"\n" +
            "}\n"); }
}
