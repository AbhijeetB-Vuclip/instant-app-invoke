import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Demo {
    private AppiumDriver driver;
    private URL url;
    private DesiredCapabilities caps;

    public Demo() throws MalformedURLException {
        url = new URL("http://0.0.0.0:4723/wd/hub");
        caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.0.0");
        caps.setCapability("deviceName", "Samsung A8 Plus");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", "/Users/abhijeetbhattacharjee/google-sample-instant-app/android-instant-apps/hello-java/instant/build/outputs/apk/debug/feature-debug.apk");

        driver = new AndroidDriver(url, caps);
    }

    public void quit() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    public void launchInstant(String instantUrl, String instantPkgName) {
        driver.executeScript("mobile: deepLink", ImmutableMap.of("url", instantUrl,
                "package", instantPkgName));
    }

    public void getDeviceInfo() {
        driver.executeScript("mobile:getDeviceInfo");
    }

    public static void main(String args[]) {
        String instantUrl = "https://multi-feature.instantappsample.com/main";
//        String instantUrl = "https://hello.instantappsample.com/bye";
        String instantPkgName = "com.example.android.unsplash";

        try {
            Demo demo = new Demo();
//            demo.getDeviceInfo();
            demo.launchInstant(instantUrl, instantPkgName);
            demo.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
