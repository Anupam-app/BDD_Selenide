package pageobjects.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SpinnerComponent {

    public final SelenideElement spinnerIcon = $(By.className("spinner-circle"));
}
