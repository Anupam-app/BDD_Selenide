package pageobjects.components;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class SpinnerComponent {

    public final SelenideElement spinnerIcon = $(By.className("spinner-circle"));
}
